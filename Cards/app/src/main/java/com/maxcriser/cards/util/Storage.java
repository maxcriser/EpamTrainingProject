package com.maxcriser.cards.util;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.Constants;

import java.io.File;

public class Storage extends AppCompatActivity {


    public static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public static Uri getPhotoFileUri(File file, String fileName) {
        if (isExternalStorageAvailable()) {
            if (file != null) {
                File mediaStorageDir = new File(
                        file, Constants.APP_TAG);
                if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                    Log.d(Constants.APP_TAG, "Error creating directory");
                }
                File imagePath = new File(mediaStorageDir.getPath() + File.separator + fileName);
                if (imagePath.exists()) {
                    return Uri.fromFile(imagePath);
                }
            }
        }
        return null;
    }
}
