package com.maxcriser.news;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.FrameLayout;

public class LanguagesActivity extends AppCompatActivity {

    static boolean pressUA=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ResourcesActivity.class);
        startActivity(intent);
    }

    public void backOnClick(View view) {
        super.onBackPressed();
    }

    public void choice(View country){
        if(pressUA) {
            country.setBackgroundColor(getResources().getColor(R.color.bluefog_700));
        }
        else{
            country.setBackgroundColor(getResources().getColor(R.color.bluefog_700));
        }
    }

    public void ukraineClick(View view) {
        pressUA=!pressUA;
        FrameLayout ua = (FrameLayout) findViewById(R.id.ukraine);
        choice(ua);
    }
}
