package com.maxcriser.cards.loader.image;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.maxcriser.cards.async.OnResultCallback;

public interface ImageLoader {

    void downloadToView(final String pUrl, final ImageView pView,
                        @Nullable final OnResultCallback<Bitmap, Void> pCallback, final int... pArgs);

    final class Impl {
        public static ImageLoader newInstance(){
            return new ImageLoaderImpl();
        }
    }

}
