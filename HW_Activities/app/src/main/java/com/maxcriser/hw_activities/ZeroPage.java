package com.maxcriser.hw_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class ZeroPage extends AppCompatActivity {

    static boolean flag = true;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (flag) {
            intent = new Intent(this, FirstPage.class);

        }
        else
        {
            intent = new Intent(this, FirstPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }
    }
}
