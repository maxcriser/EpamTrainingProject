package com.maxcriser.hw_unitstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Version setVersion;
    TextView nameVersion;
    android.widget.Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVersion = new Version();

        nameVersion = (TextView) findViewById(R.id.version);
        buttonUpdate = (android.widget.Button) findViewById(R.id.update);

        nameVersion.setText(setVersion.versionAndroid());

        setVisibility();
    }

    public void setVisibility() {
        if (setVersion.isUpdate()) {
            buttonUpdate.setVisibility(View.VISIBLE);
        } else {
            buttonUpdate.setVisibility(View.INVISIBLE);
        }
    }
}