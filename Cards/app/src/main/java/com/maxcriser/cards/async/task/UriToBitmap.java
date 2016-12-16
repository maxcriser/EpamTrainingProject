package com.maxcriser.cards.async.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;

import java.io.File;

import static com.maxcriser.cards.util.Storage.getPhotoFileUri;

public class UriToBitmap implements Task<String, Void, Bitmap> {

    File file;

    public UriToBitmap(File pFile) {
        this.file = pFile;
    }

    @Override
    public Bitmap doInBackground(String pS, ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        Uri imageUri = getPhotoFileUri(file, pS);
        if (imageUri != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath());
            if (bitmap != null) {
                return bitmap;
            }
        }
        return null;
    }
}