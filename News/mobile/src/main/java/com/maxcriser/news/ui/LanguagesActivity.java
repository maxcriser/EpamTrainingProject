package com.maxcriser.news.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.maxcriser.news.R;

public class LanguagesActivity extends AppCompatActivity {

    public static final String BLUE_SELECT = "#0b8c8f";
    public static final String GRAY_3 = "#1e1e23";
    static boolean pressUA = false;
    static boolean pressRU = false;
    static boolean pressUS = false;
    private FrameLayout us;
    private TextView textUs;
    private FrameLayout ru;
    private TextView textRu;
    private FrameLayout ua;
    private TextView textUa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        us = (FrameLayout) findViewById(R.id.us);
        textUs = (TextView) findViewById(R.id.text_us);

        ru = (FrameLayout) findViewById(R.id.ru);
        textRu = (TextView) findViewById(R.id.text_ru);

        ua = (FrameLayout) findViewById(R.id.ua);
        textUa = (TextView) findViewById(R.id.text_ua);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ResourcesActivity.class);
        startActivity(intent);
    }

    public void backOnClick(View view) {
        super.onBackPressed();
    }

    public void choice(View country, boolean flag, TextView text) {
        if (flag) {
            country.setBackgroundColor(Color.parseColor(BLUE_SELECT));
            text.setTextColor(Color.parseColor(GRAY_3));
        } else {
            country.setBackground(null);
            text.setTextColor(Color.parseColor(BLUE_SELECT));
        }
    }

    public void usClicked(View view) {
        pressUS = !pressUS;
        choice(us, pressUS, textUs);
    }

    public void ruClicked(View view) {
        pressRU = !pressRU;
        choice(ru, pressRU, textRu);
    }

    public void uaClicked(View view) {
        pressUA = !pressUA;
        choice(ua, pressUA, textUa);
    }
}
