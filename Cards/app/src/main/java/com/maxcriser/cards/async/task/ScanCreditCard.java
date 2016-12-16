package com.maxcriser.cards.async.task;

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

public class ScanCreditCard implements Task<Uri, String, CreditCard> {

    private static final String TAG = "TAG";
    private TessBaseAPI tessBaseApi;
    private static final String lang = "eng";
    private static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/TesseractSample/";
    private static final String TESSDATA = "tessdata";

    public ScanCreditCard() {

    }

    @Override
    public CreditCard doInBackground(Uri uri, ProgressCallback<String> pStringProgressCallback) throws Exception {
        try {
            File dir = new File(DATA_PATH + TESSDATA);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.e(TAG, "ERROR: Creation of directory " + DATA_PATH + TESSDATA + " failed, check does Android Manifest have permission to write to external storage.");
                }
            } else {
                Log.i(TAG, "Created directory " + DATA_PATH + TESSDATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            String fileList[] = getAssets().list(TESSDATA);
//            for (String fileName : fileList) {
//                String pathToDataFile = DATA_PATH + TESSDATA + "/" + fileName;
//                if (!(new File(pathToDataFile)).exists()) {
//                    InputStream in = getAssets().open(TESSDATA + "/" + fileName);
//                    OutputStream out = new FileOutputStream(pathToDataFile);
//                    byte[] buf = new byte[1024];
//                    int len;
//                    while ((len = in.read(buf)) > 0) {
//                        out.write(buf, 0, len);
//                    }
//                    in.close();
//                    out.close();
//                    Log.d(TAG, "Copied " + fileName + "to tessdata");
//                }
//            }
//        } catch (IOException e) {
//            Log.e(TAG, "Unable to copy files to tessdata " + e.toString());
//        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4; // 1 - means max size. 4 - means maxsize/4 size. Don't use value <4, because you need more memory in the heap to store your data.
            Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath(), options);
            String result = extractText(bitmap);
            Log.d("result", result);
            return new CreditCard(result);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    private String extractText(Bitmap bitmap) {
        try {
            tessBaseApi = new TessBaseAPI();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            if (tessBaseApi == null) {
                Log.e(TAG, "TessBaseAPI is null. TessFactory not returning tess object.");
            }
        }

        tessBaseApi.init(DATA_PATH, lang);

//        For example if we only want to detect numbers
        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST,
                "0123456789" + "AaBbCcDdEeFfGgHhIiJiKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz /&,.-");

        Log.d(TAG, "Training file loaded");
        tessBaseApi.setImage(bitmap);
        String extractedText = "empty result";
        try {
            extractedText = tessBaseApi.getUTF8Text();
        } catch (Exception e) {
            Log.e(TAG, "Error in recognizing text.");
        }
        tessBaseApi.end();
        return extractedText;
    }
}