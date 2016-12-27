package com.maxcriser.cards.manager;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

public class StorageManager extends AppCompatActivity {

    public static boolean isExternalStorageAvailable() {
        final String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}