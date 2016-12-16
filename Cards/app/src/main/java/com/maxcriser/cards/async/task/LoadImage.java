package com.maxcriser.cards.async.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;

import java.io.File;

import static com.maxcriser.cards.util.Storage.getPhotoFileUri;

public class LoadImage implements Task<String, Void, Void> {

    File file;
    ImageView view;

    public LoadImage(File pFile, ImageView pView) {
        this.file = pFile;
        this.view = pView;
    }

    @Override
    public Void doInBackground(String pS, ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        Uri imageUri = getPhotoFileUri(file, pS);
        if (imageUri != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath());
            if (bitmap != null) {
                view.setImageBitmap(bitmap);
            } else {
                // TODO cannot load
            }
        } else {
            // TODO you have no photo this card
        }
        return null;
    }
}