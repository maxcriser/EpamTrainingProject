package com.maxcriser.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maxcriser.news.R;
import com.maxcriser.news.ui.version.TitleVersionActivity;

public class HelloActivity extends AppCompatActivity {

    com.maxcriser.news.view.RobotoThinTextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        title = (com.maxcriser.news.view.RobotoThinTextView) findViewById(R.id.title_news);
        title.setText(TitleVersionActivity.setTitle());
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, LanguagesActivity.class);
        startActivity(intent);
    }
}
