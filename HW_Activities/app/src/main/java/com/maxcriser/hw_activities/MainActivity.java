package com.maxcriser.hw_activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static final String KEY_SETTINGS = "Registration";

    SharedPreferences cacheReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cacheReg = getSharedPreferences(KEY_SETTINGS, MODE_PRIVATE);
        SharedPreferences.Editor editCacheReg = cacheReg.edit();

        editCacheReg.putBoolean(KEY_SETTINGS, true);
        editCacheReg.commit();
    }

    public void ExitPress(View view) {
        this.finish();
    }
}
