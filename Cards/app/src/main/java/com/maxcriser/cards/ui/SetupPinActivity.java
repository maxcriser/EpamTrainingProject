package com.maxcriser.cards.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.constants;

public class SetupPinActivity extends AppCompatActivity {

    private TextView title;
    private EditText inputText;
    private Button btnContinue;
    private Button btnCancel;
    private String password;
    private Page page;

    private enum Page {
        FIRST,
        SECOND
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_pin);
        initViews();
    }

    private void initViews() {
        title = (TextView) findViewById(R.id.title_setup_pin);
        btnContinue = (Button) findViewById(R.id.btn_continue_setup);
        btnCancel = (Button) findViewById(R.id.btn_cancel_setup);
        inputText = (EditText) findViewById(R.id.setup_pin_edit_text);
        inputText.addTextChangedListener(new PinChangeListener());
        btnContinue.setOnClickListener(new ContinueClickListener());
        btnCancel.setOnClickListener(new CancelClickListener());
        page = Page.FIRST;
    }

    private class PinChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (inputText.getText().length() == 4) {
                btnContinue.setEnabled(true);
            } else {
                btnContinue.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class ContinueClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (page.equals(Page.FIRST)) {
                password = inputText.getText().toString();
                Toast.makeText(SetupPinActivity.this, "first: " + password, Toast.LENGTH_LONG).show();
                inputText.setText(constants.EMPTY_STRING);
                page = Page.SECOND;
                btnContinue.setText(R.string.ok);
                title.setText(R.string.confirm_your_pin);
            } else if (page.equals(Page.SECOND)) {
                if (password.equals(inputText.getText().toString())) {
                    //TODO statics
                    LaunchScreenActivity.loadPassword = password;
                    LaunchScreenActivity.mSharedPreferences =
                            getSharedPreferences(LaunchScreenActivity.PASSWORD_TAG, MODE_PRIVATE);
                    SharedPreferences.Editor editSharedPassword = LaunchScreenActivity.mSharedPreferences.edit();
                    editSharedPassword.putString(LaunchScreenActivity.PASSWORD_TAG, password).apply();

                    Toast.makeText(SetupPinActivity.this, "password: " + password + " tag: " + LaunchScreenActivity.PASSWORD_TAG, Toast.LENGTH_LONG).show();
                    Toast.makeText(SetupPinActivity.this, "save: " + LaunchScreenActivity.loadPassword, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SetupPinActivity.this, MenuActivity.class);
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else {
                    Toast.makeText(SetupPinActivity.this, R.string.passwords_dont_match, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private class CancelClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    }
}