package com.maxcriser.cards.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.maxcriser.cards.R;

public class PinProtectedActivity extends AppCompatActivity {

    EditText editTextPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_protected);

        editTextPin = (EditText) findViewById(R.id.editText_pin);
        //editTextPin.setTextIsSelectable(true);
    }

    public void onCancelClicked(View view) {
        super.onBackPressed();
    }
}