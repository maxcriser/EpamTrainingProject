package com.maxcriser.cards;

import android.app.Application;
import android.content.Context;

import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.loader.image.ImageLoader;

public class CoreApplication extends Application {

    private ImageLoader mImageLoader;
    private DatabaseHelper mDatabaseHelper;

    public DatabaseHelper getDatabaseHelper(final Context pContext) {
        if (mDatabaseHelper == null) {
            mDatabaseHelper = DatabaseHelper.Impl.newInstance(pContext);
        }
        return mDatabaseHelper;
    }

    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = ImageLoader.Impl.newInstance();
        }
        return mImageLoader;
    }

    public CoreApplication() {

        ContextHolder.set(this);

    }
}