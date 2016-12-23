//TODO clear the code
package com.maxcriser.cards.utils;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

public class Storage extends AppCompatActivity {

    public static boolean isExternalStorageAvailable() {
        final String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}