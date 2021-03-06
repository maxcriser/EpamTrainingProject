package com.maxcriser.hw_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class ZeroActivity extends AppCompatActivity {

    public static final String KEY_SETTINGS = "Registration";
    public static boolean FLAG_REG;

    public static SharedPreferences cacheReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cacheReg = getSharedPreferences(KEY_SETTINGS, MODE_PRIVATE);
        FLAG_REG = cacheReg.getBoolean(KEY_SETTINGS, false);

        Intent intent;

        if (FLAG_REG) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, FirstActivity.class);
        }

        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}
