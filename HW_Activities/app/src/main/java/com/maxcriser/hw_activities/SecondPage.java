package com.maxcriser.hw_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class SecondPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void NextPress(View view) {
        Intent intent = new Intent(this, ThirdPage.class);
        startActivity(intent);
    }
}
