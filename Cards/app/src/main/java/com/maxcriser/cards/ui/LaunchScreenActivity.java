package com.maxcriser.cards.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.maxcriser.cards.R;
import com.maxcriser.cards.database.custom.ListTableItems;
import com.maxcriser.cards.holder.ContextHolder;
import com.maxcriser.cards.reader.PreviewReader;

import java.util.List;

public class LaunchScreenActivity extends AppCompatActivity {

    public static List<ListTableItems> previewColors;
    public static List<String> previewTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContextHolder.getInstance().setContext(getApplicationContext());

        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);

        final PreviewReader tcReader = PreviewReader.getInstance();
        tcReader.setPreviewColors();
        previewColors = tcReader.getPreviewColors();
        tcReader.setTypeCard();
        previewTypes = tcReader.getTypeCard();

        setContentView(R.layout.activity_launch_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchScreenActivity.this, MainActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        }, 1500);

    }
}