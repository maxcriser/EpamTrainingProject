package com.maxcriser.cards.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.maxcriser.cards.holder.ContextHolder;
import com.maxcriser.cards.setter.PreviewColorsSetter;
import com.maxcriser.cards.setter.PreviewListColorsSetter;

import java.util.List;

public class LaunchScreenActivity extends AppCompatActivity {

    public static List<PreviewColorsSetter> sPreviewColorSetters;
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

        final PreviewListColorsSetter tcReader = PreviewListColorsSetter.getInstance();
        tcReader.setPreviewColors();

        sPreviewColorSetters = tcReader.getPreviewColorSetters();
        tcReader.setTypeCard();
        previewTypes = tcReader.getTypeCard();

        Intent intent;
        if (loadPassword.equals(UNDEFENDED)) {
            intent = new Intent(LaunchScreenActivity.this, SetupPinActivity.class);
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