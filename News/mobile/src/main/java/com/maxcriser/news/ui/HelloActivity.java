package com.maxcriser.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maxcriser.news.R;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, LanguagesActivity.class);
        startActivity(intent);
    }
}
