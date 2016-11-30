package com.maxcriser.cards.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.maxcriser.cards.R;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.holder.ContextHolder;
import com.maxcriser.cards.reader.MainItem;
import com.maxcriser.cards.reader.PreviewReader;

import java.util.List;

public class LaunchScreenActivity extends AppCompatActivity {

    public static List<Colors> previewColors;
    public static List<String> previewTypes;
    public static List<MainItem> sMainItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch_screen);

        //TODO APP
        ContextHolder.getInstance().setContext(getApplicationContext());

        final PreviewReader tcReader = PreviewReader.getInstance();
        tcReader.setPreviewColors();


        previewColors = tcReader.getPreviewColors();
        tcReader.setTypeCard();
        previewTypes = tcReader.getTypeCard();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchScreenActivity.this, MainActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        }, 1500);
    }
}