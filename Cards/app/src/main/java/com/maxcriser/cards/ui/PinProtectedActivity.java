package com.maxcriser.cards.ui;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.handler.FingerprintHandler;
import com.maxcriser.cards.reader.CorrectPassword;
import com.maxcriser.cards.ui.cards.ProtectedBankCards;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class PinProtectedActivity extends AppCompatActivity {

    ImageView firstCircle;
    ImageView secondCircle;
    ImageView thirdCircle;
    ImageView fourthCircle;

    CorrectPassword check;
    public static EditText editTextPin;

    private static final String KEY_NAME = "example_key";

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_protected);

        check = CorrectPassword.getInstance();
        check.setPassword();

        firstCircle = (ImageView)findViewById(R.id.crlcOne);
        secondCircle = (ImageView)findViewById(R.id.crlcTwo);
        thirdCircle = (ImageView)findViewById(R.id.crlcThree);
        fourthCircle = (ImageView)findViewById(R.id.crlcFour);

        //editTextPin = (EditText) findViewById(R.id.editText_pin);
        //editTextPin.setTextIsSelectable(true);
        //editTextPin.addTextChangedListener(new onPasswordInputListener());

        keyguardManager =
                (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager =
                (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (!keyguardManager.isKeyguardSecure()) {

            Toast.makeText(this,
                    "Lock screen security not enabled in Settings",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,
                    "Fingerprint authentication permission not enabled",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this,
                    "Register at least one fingerprint in Settings",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        generateKey();

        if (cipherInit()) {
            cryptoObject =
                    new FingerprintManager.CryptoObject(cipher);
        }

        if (cipherInit()) {
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintHandler helper = new FingerprintHandler(this);
            helper.startAuth(fingerprintManager, cryptoObject);
        }
    }

    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    "AndroidKeyStore");
        } catch (NoSuchAlgorithmException |
                NoSuchProviderException e) {
            throw new RuntimeException(
                    "Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    public void onCancelClicked(View view) {
        super.onBackPressed();
    }

    public void inputPassword(String number) {
      //  editTextPin.setText(editTextPin.getText() + number);
    }

    public void zeroInput(View view) {
        inputPassword("0");
    }

    public void oneInput(View view) {
        inputPassword("1");
    }

    public void twoInput(View view) {
        inputPassword("2");
    }

    public void threeInput(View view) {
        inputPassword("3");
    }

    public void fourInput(View view) {
        inputPassword("4");
    }

    public void fiveInput(View view) {
        inputPassword("5");
    }

    public void sixInput(View view) {
        inputPassword("6");
    }

    public void sevenInput(View view) {
        inputPassword("7");
    }

    public void eightInput(View view) {
        inputPassword("8");
    }

    public void nineInput(View view) {
        inputPassword("9");
    }

    public void onDeleteClicked(View view) {
//        if (editTextPin.getText().length() != 0) {
//            // delete circles
//            editTextPin.setText("");
//        }
    }

    private class onPasswordInputListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
//            if (editTextPin.length() == 4) {
//                if (check.getPassword())
//                    accessPassword();
//                else {
//                    editTextPin.setText("");
//                }
//            }
        }
    }

    public void accessPassword(){
        startActivity(new Intent(PinProtectedActivity.this, ProtectedBankCards.class));
    }
}