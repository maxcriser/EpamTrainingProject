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

public class SetupPIN extends AppCompatActivity {

    enum Page {
        FIRST,
        SECOND
    }

    TextView title;
    EditText inputText;
    Button btnContinue;
    Button btnCancel;
    String password;
    Page page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_pin);

        initViews();

        inputText.addTextChangedListener(new PinChangeListener());
        btnContinue.setOnClickListener(new ContinueClickListener());
        btnCancel.setOnClickListener(new CancelClickListener());

        page = Page.FIRST;
    }

    private void initViews(){
        title = (TextView) findViewById(R.id.title_setup_pin);
        btnContinue = (Button) findViewById(R.id.btn_continue_setup);
        btnCancel = (Button) findViewById(R.id.btn_cancel_setup);
        inputText = (EditText) findViewById(R.id.setup_pin_edit_text);
    }

    private class PinChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (inputText.getText().length() == 4) {
                if (page.equals(Page.FIRST)) {
                    btnContinue.setEnabled(true);
                } else if (page.equals(Page.SECOND)) {
                    if (password.equals(inputText.getText().toString())) {
                        btnContinue.setEnabled(true);
                    } else {
                        btnContinue.setEnabled(false);
                    }
                }
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
                Toast.makeText(SetupPIN.this, "first: " + password, Toast.LENGTH_LONG).show();
                inputText.setText("");
                page = Page.SECOND;
                btnContinue.setText(R.string.ok);
                btnContinue.setEnabled(false);
                title.setText(R.string.confirm_your_pin);
            } else if (page.equals(Page.SECOND)) {
                if (password.equals(inputText.getText().toString())) {
                    btnContinue.setEnabled(true);
                    LaunchScreenActivity.loadPassword = password;
                    LaunchScreenActivity.mSharedPreferences =
                            getSharedPreferences(LaunchScreenActivity.PASSWORD_TAG, MODE_PRIVATE);
                    SharedPreferences.Editor editSharedPassword = LaunchScreenActivity.mSharedPreferences.edit();
                    editSharedPassword.putString(LaunchScreenActivity.PASSWORD_TAG, password).apply();

                    Toast.makeText(SetupPIN.this, "password: " + password + " tag: " + LaunchScreenActivity.PASSWORD_TAG, Toast.LENGTH_LONG).show();
                    Toast.makeText(SetupPIN.this, "save: " + LaunchScreenActivity.loadPassword, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SetupPIN.this, MenuActivity.class);
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
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