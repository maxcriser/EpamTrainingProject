package com.maxcriser.copytwitter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by IstiN on 03.10.2016.
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageButton keyboard = (ImageButton) findViewById(R.id.btn_three_dots);
        keyboard.setRotation(90);

        ImageView imgView = (ImageView) findViewById(R.id.photo);
        imgView.setScaleType(ImageView.ScaleType.CENTER);
    }

    public void BackPress(View view) {
        super.onBackPressed();
    }

    public void SettingsPress(View view) {
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
    }
}