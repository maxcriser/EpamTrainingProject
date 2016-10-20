package com.maxcriser.hw_jsonpareser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    View progressBar;
    View parseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseBtn = findViewById(R.id.parse);
        progressBar = findViewById(R.id.progress_bar);
    }

    public void onParseClicked(View view) {
        progressBar.setVisibility(View.VISIBLE);

        JSONReader reader = new JSONReader("http://androiddocs.ru/api/friends.json");
        reader.execute();
    }
}
