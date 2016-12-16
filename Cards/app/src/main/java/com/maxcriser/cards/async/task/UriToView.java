package com.maxcriser.cards.async.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;

public class UriToView implements Task<Uri, Void, Void> {

    private ImageView view;

    public UriToView(ImageView view) {
        this.view = view;
    }

    @Override
    public Void doInBackground(Uri pUri, ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        if (pUri != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(pUri.getPath());
            if (bitmap != null) {
                view.setImageBitmap(bitmap);
            }
        }
        return null;
    }
}