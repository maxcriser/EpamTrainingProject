package com.maxcriser.cards.async.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;

import java.io.File;

import static com.maxcriser.cards.util.Storage.getPhotoFileUri;

public class UriToBitmap implements Task<Uri, Void, Bitmap> {

    @Override
    public Bitmap doInBackground(Uri imageUri, ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        if (imageUri != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath());
            if (bitmap != null) {
                return bitmap;
            }
        }
        return null;
    }
}