package com.maxcriser.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maxcriser.news.R;
import com.maxcriser.news.ui.version.TitleVersion;
import com.maxcriser.news.view.RobotoThinTextView;

public class HelloActivity extends AppCompatActivity {

    RobotoThinTextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        title = (com.maxcriser.news.view.RobotoThinTextView) findViewById(R.id.title_news);
        title.setText(TitleVersion.setTitle());
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, LanguagesActivity.class);
        startActivity(intent);
    }
}
