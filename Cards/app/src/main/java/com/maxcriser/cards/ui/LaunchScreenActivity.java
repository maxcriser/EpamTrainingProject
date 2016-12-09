package com.maxcriser.cards.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.test.espresso.core.deps.guava.collect.ImmutableMap;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.maxcriser.cards.holder.ContextHolder;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.reader.PreviewReader;

import java.util.List;
import java.util.Map;

public class LaunchScreenActivity extends AppCompatActivity {

    public static final byte REQUEST_CAMERA = 0;
    public static final byte REQUEST_WRITE_STORAGE = 1;
    public static final byte REQUEST_CALENDAR = 3;
    public static final Map<Byte, String> REQUESTS = ImmutableMap.of(
            REQUEST_WRITE_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            REQUEST_CAMERA, Manifest.permission.CAMERA,
            REQUEST_CALENDAR, Manifest.permission.WRITE_CALENDAR
    );

    public static List<Colors> previewColors;
    public static List<String> previewTypes;
    public static String loadPassword;
    public static final String UNDEFENDED = "undefended";
    public static final String PASSWORD_TAG = "shared_password";
    public static SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadPassword();

        //TODO APP
        ContextHolder.getInstance().setContext(getApplicationContext());

        final PreviewReader tcReader = PreviewReader.getInstance();
        tcReader.setPreviewColors();

        previewColors = tcReader.getPreviewColors();
        tcReader.setTypeCard();
        previewTypes = tcReader.getTypeCard();

        Intent intent;
        if (loadPassword.equals(UNDEFENDED)) {
            intent = new Intent(LaunchScreenActivity.this, SetupPIN.class);
        } else {
            intent = new Intent(LaunchScreenActivity.this, MenuActivity.class);
        }
        startActivity(intent);
        finish();
    }

    void loadPassword() {
        mSharedPreferences = getSharedPreferences(PASSWORD_TAG, MODE_PRIVATE);
        loadPassword = mSharedPreferences.getString(PASSWORD_TAG, UNDEFENDED);
        Toast.makeText(this, loadPassword, Toast.LENGTH_SHORT).show();
    }
}