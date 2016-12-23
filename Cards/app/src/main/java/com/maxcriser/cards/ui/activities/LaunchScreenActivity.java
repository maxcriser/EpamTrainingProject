package com.maxcriser.cards.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.maxcriser.cards.model.PreviewColor;
import com.maxcriser.cards.model.PreviewListColorsSetter;

import java.util.List;

public class LaunchScreenActivity extends AppCompatActivity {

    //TODO remove all statics
    public static List<PreviewColor> previewColors;
    public static List<String> previewTypes;
    public static String loadPassword;
    public static final String UNDEFENDED = "undefended";
    public static final String PASSWORD_TAG = "shared_password";
    public static SharedPreferences mSharedPreferences;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadPassword();

        final PreviewListColorsSetter tcReader = PreviewListColorsSetter.getInstance();
        tcReader.setPreviewColors();
        previewColors = tcReader.getPreviewColorSetters();
        tcReader.setTypeCard();
        previewTypes = tcReader.getTypeCard();

        final Intent intent;
        if (loadPassword.equals(UNDEFENDED)) {
            intent = new Intent(this, SetupPinActivity.class);
        } else {
            intent = new Intent(this, MenuActivity.class);
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