package com.maxcriser.cards.async.task;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;
import com.maxcriser.cards.model.CreditCard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ScanCreditCard implements Task<Uri, String, CreditCard> {

    private final String DATA_PATH = Environment.getExternalStorageDirectory() + "/TesseractSample/";
    private final AssetManager mAssetManager;
    private final Context mContext;

    public ScanCreditCard(final AssetManager pAssetManager, final Context pContext) {
        this.mAssetManager = pAssetManager;
        this.mContext = pContext;
    }

    @Override
    public CreditCard doInBackground(final Uri uri, final ProgressCallback<String> pStringProgressCallback) throws Exception {
        prepareTesseract();
        return startOCR(uri);
    }

    private CreditCard startOCR(final Uri imgUri) {
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4; // 1 - means max size. 4 - means maxsize/4 size. Don't use value <4, because you need more memory in the heap to store your data.
            final Bitmap bitmap = BitmapFactory.decodeFile(imgUri.getPath(), options);
            final String result = extractText(bitmap);
            return new CreditCard(result);
        } catch (final Exception e) {
            return null;
        }
    }

    private void copyTessDataFiles(final String path) throws Exception {
        try {
            final String[] fileList = mAssetManager.list(path);
            for (final String fileName : fileList) {
                final String pathToDataFile = DATA_PATH + path + "/" + fileName;
                if (!(new File(pathToDataFile)).exists()) {
                    final InputStream in = mAssetManager.open(path + "/" + fileName);
                    final OutputStream out = new FileOutputStream(pathToDataFile);
                    final byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();
                }
            }
        } catch (final IOException e) {
            throw new Exception(e);
        }
    }

    private void prepareTesseract() throws Exception {
        final String tessdata = "tessdata";
        try {
            prepareDirectory(DATA_PATH + tessdata);
        } catch (final Exception e) {
            throw new Exception(e);
        }
        try {
            copyTessDataFiles(tessdata);
        } catch (final Exception pE) {
            throw new Exception(pE);
        }
    }

    private void prepareDirectory(final String path) {

        final File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Toast.makeText(mContext, R.string.filed_to_create_directory, Toast.LENGTH_LONG).show();
            }
        }
    }

    private String extractText(final Bitmap bitmap) throws Exception {
        final TessBaseAPI tessBaseApi;
        try {
            tessBaseApi = new TessBaseAPI();
        } catch (final Exception e) {
            throw new Exception(e);
        }
        final String lang = "eng";
        tessBaseApi.init(DATA_PATH, lang);
        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST,
                "0123456789" + "AaBbCcDdEeFfGgHhIiJiKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz /&,.-");
        tessBaseApi.setImage(bitmap);
        final String extractedText;
        try {
            extractedText = tessBaseApi.getUTF8Text();
        } catch (final Exception e) {
            throw new Exception(e);
        }
        tessBaseApi.end();
        return extractedText;
    }
}