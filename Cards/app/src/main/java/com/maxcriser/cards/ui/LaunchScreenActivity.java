package com.maxcriser.cards.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.holder.ContextHolder;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.reader.PreviewReader;

import java.util.List;

public class LaunchScreenActivity extends AppCompatActivity {

    public static final String UNDEFENDED = "undefended";
    public static List<Colors> previewColors;
    public static List<String> previewTypes;
    public static String loadPassword;

    SharedPreferences mSharedPreferences;
    final String PASSWORD_TAG = "shared_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch_screen);

        savePassword();
        loadPassword();

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
                Intent intent;
                if (loadPassword.equals(UNDEFENDED)) {
                    intent = new Intent(LaunchScreenActivity.this, SetupPIN.class);
                } else {
                    intent = new Intent(LaunchScreenActivity.this, MainActivity.class);
                }
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        }, 1500);
    }

    void savePassword() {
        mSharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = mSharedPreferences.edit();
        ed.putString(PASSWORD_TAG, UNDEFENDED).apply();
    }

    void loadPassword() {
        mSharedPreferences = getPreferences(MODE_PRIVATE);
        loadPassword = mSharedPreferences.getString(PASSWORD_TAG, UNDEFENDED);
        Toast.makeText(this, loadPassword, Toast.LENGTH_SHORT).show();
    }
}