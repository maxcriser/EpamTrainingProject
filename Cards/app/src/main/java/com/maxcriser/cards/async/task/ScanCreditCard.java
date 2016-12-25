package com.maxcriser.cards.async.task;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;
import com.maxcriser.cards.model.CreditCard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ScanCreditCard implements Task<Uri, String, CreditCard> {

    private final String TAG = "TAG";
    private TessBaseAPI tessBaseApi;
    private final String DATA_PATH = Environment.getExternalStorageDirectory() + "/TesseractSample/";
    private final AssetManager mAssetManager;

    public ScanCreditCard(final AssetManager pAssetManager) {
        this.mAssetManager = pAssetManager;
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
            Log.d("Result OCR", result);
            return new CreditCard(result);
        } catch (final Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    private void copyTessDataFiles(final String path) {
        try {
            final String[] fileList = mAssetManager.list(path);
            for (final String fileName : fileList) {
                Log.d("fileName", fileName);
                final String pathToDataFile = DATA_PATH + path + "/" + fileName;
                Log.d("pathToDataFile", DATA_PATH + path + "/" + fileName);

                if (!(new File(pathToDataFile)).exists()) {
                    final InputStream in = mAssetManager.open(path + "/" + fileName);
                    Log.d("path + / + fileName", path + "/" + fileName);
                    final OutputStream out = new FileOutputStream(pathToDataFile);
                    final byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();
                    Log.d(TAG, "Copied " + fileName + "to tessdata");
                }
            }
        } catch (final IOException e) {
            Log.e(TAG, "Unable to copy files to tessdata " + e);
        }
    }

    private void prepareTesseract() {
        final String TESSDATA = "tessdata";
        try {
            prepareDirectory(DATA_PATH + TESSDATA);
            Log.d("DATA_PATH + PATH", DATA_PATH + TESSDATA);
        } catch (final Exception e) {
            Log.d("TAG", e.toString());
        }
        copyTessDataFiles(TESSDATA);
    }

    private void prepareDirectory(final String path) {

        final File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e(TAG, "ERROR: Creation of directory " + path + " failed, check does Android Manifest have permission to write to external storage.");
            }
        } else {
            Log.i(TAG, "Created directory " + path);
        }
    }

    private String extractText(final Bitmap bitmap) {
        try {
            tessBaseApi = new TessBaseAPI();
        } catch (final Exception e) {
            Log.e(TAG, e.getMessage());
            if (tessBaseApi == null) {
                Log.e(TAG, "TessBaseAPI is null. TessFactory not returning tess object.");
            }
        }
        final String lang = "eng";
        tessBaseApi.init(DATA_PATH, lang);
//        For example if we only want to detect numbers
        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST,
                "0123456789" + "AaBbCcDdEeFfGgHhIiJiKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz /&,.-");

        Log.d(TAG, "Training file loaded");
        tessBaseApi.setImage(bitmap);
        String extractedText = "empty result";
        try {
            extractedText = tessBaseApi.getUTF8Text();
        } catch (final Exception e) {
            Log.e(TAG, "Error in recognizing text.");
        }
        tessBaseApi.end();
        return extractedText;
    }
}