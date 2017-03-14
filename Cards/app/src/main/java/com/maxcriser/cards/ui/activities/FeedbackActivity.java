package com.maxcriser.cards.ui.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.manager.ProfileManager;

public class FeedbackActivity extends Activity {

    private static final String FEEDBACK_FROM_EMERGALLS = "Feedback from CKeeper";
    private static final String MV_MAXCRISER_GMAIL_COM = "mv.maxcriser@gmail.com";
    private static final String MESSAGE_RFC822 = "message/rfc822";
    private static final String MAILTO = "mailto:";
    private static final int CODE_GET_ACCOUNTS = 0;
    private EditText feedback;
    private EditText email;
    private CheckBox includeScreenshot;
    private ImageView screenshot;

    @TargetApi(23)
    private void getPermission(final int CODE, final String PERMISSION) {
        if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, CODE);
        } else {
            initViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (grantResults.length == 0) {
            initViews();
            return;
        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            onBackPressed();
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initViews();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void sendMessage() {
        final Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType(MESSAGE_RFC822);
        i.putExtra(Intent.EXTRA_EMAIL, email.getText());
        i.putExtra(Intent.EXTRA_SUBJECT, FEEDBACK_FROM_EMERGALLS);
        i.putExtra(Intent.EXTRA_TEXT, feedback.getText());
        i.setData(Uri.parse(MAILTO + MV_MAXCRISER_GMAIL_COM));
        try {
            startActivity(i);
        } catch (final android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, R.string.no_email_clients_installed, Toast.LENGTH_SHORT).show();
        }
        onBackPressed();
    }

    public void onBackPressed(final View view) {
        onBackPressed();
    }

    public void onSendClicked(final View view) {
        sendMessage();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getPermission(CODE_GET_ACCOUNTS, Manifest.permission.GET_ACCOUNTS);
    }

    private void initViews() {
        screenshot = (ImageView) findViewById(R.id.screenshot);
        screenshot.setDrawingCacheEnabled(false);
        email = (EditText) findViewById(R.id.email_profile);
        email.setText(ProfileManager.getUserMail(this));
        feedback = (EditText) findViewById(R.id.text_feedback);
        feedback.requestFocus();
        includeScreenshot = (CheckBox) findViewById(R.id.include_screenshot);
    }
}