package com.maxcriser.news.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CheckActivity extends AppCompatActivity {

    public static final String KEY_SETTINGS = "Registration";
    public static boolean FLAG_REG;

    public static SharedPreferences cacheReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cacheReg = getSharedPreferences(KEY_SETTINGS, MODE_PRIVATE);
        FLAG_REG = cacheReg.getBoolean(KEY_SETTINGS, false);

        Intent intent;

//        if (FLAG_REG) {
//            intent = new Intent(this, MainActivity.class);
//        } else {
            intent = new Intent(this, HelloActivity.class);
//        }

        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}
