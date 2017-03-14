package com.maxcriser.cards.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import static com.maxcriser.cards.constant.ListConstants.PASSWORD_TAG;
import static com.maxcriser.cards.constant.ListConstants.UNDEFENDED;

public class LaunchScreenActivity extends AppCompatActivity {

    public String loadPassword;
    public SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadPassword();

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
    }
}