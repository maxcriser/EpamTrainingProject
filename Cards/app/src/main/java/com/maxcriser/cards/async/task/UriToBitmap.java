package com.maxcriser.cards.async.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;
import com.maxcriser.cards.constant.Constants;

import java.io.File;

public class UriToBitmap implements Task<String, Void, Bitmap> {

    File file;

    public UriToBitmap(File pFile) {
        this.file = pFile;
    }

    @Override
    public Bitmap doInBackground(String pS, ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        Uri imageUri = getPhotoFileUri(pS);
        if (imageUri != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(getPhotoFileUri(pS).getPath());
            if (bitmap != null) {
                return bitmap;
            } else {
                return null;
            }
        } else {
        }
        return null;
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public Uri getPhotoFileUri(String fileName) {
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