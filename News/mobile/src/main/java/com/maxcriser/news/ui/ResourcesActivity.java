package com.maxcriser.news.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maxcriser.news.R;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
    }

    public void doneClick(View view) {
        CheckActivity.cacheReg = getSharedPreferences(CheckActivity.KEY_SETTINGS, MODE_PRIVATE);
        SharedPreferences.Editor editCacheReg = CheckActivity.cacheReg.edit();

        editCacheReg.putBoolean(CheckActivity.KEY_SETTINGS, true);
        editCacheReg.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    public void backOnClick(View view) {
        super.onBackPressed();
    }
}
