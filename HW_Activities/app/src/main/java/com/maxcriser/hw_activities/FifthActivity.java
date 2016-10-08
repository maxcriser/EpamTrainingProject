package com.maxcriser.hw_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
    }

    public void nextPress(View view) {
        ZeroActivity.cacheReg = getSharedPreferences(ZeroActivity.KEY_SETTINGS, MODE_PRIVATE);
        SharedPreferences.Editor editCacheReg = ZeroActivity.cacheReg.edit();

        editCacheReg.putBoolean(ZeroActivity.KEY_SETTINGS, true);
        editCacheReg.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}
