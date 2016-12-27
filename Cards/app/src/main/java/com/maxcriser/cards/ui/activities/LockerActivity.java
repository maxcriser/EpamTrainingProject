package com.maxcriser.cards.ui.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.ListConstants;
import com.maxcriser.cards.fingerprint.FingerprintHandler;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_TITLE_TO_ITEMS;
import static com.maxcriser.cards.constant.Extras.EXTRA_CHECK_ITEMS;
import static com.maxcriser.cards.constant.ListConstants.CREDIT_CARD;
import static com.maxcriser.cards.constant.ListConstants.KEY_NAME;
import static com.maxcriser.cards.constant.ListConstants.PASSWORD_TAG;
import static com.maxcriser.cards.constant.ListConstants.TYPE_LOCKED_SCREEN;
import static com.maxcriser.cards.constant.ListConstants.UNDEFENDED;

public class LockerActivity extends AppCompatActivity {

    private ImageView firstCircle;
    private ImageView secondCircle;
    private ImageView thirdCircle;
    private ImageView fourthCircle;
    private Vibrator mVibrator;
    private Intent intent;
    private String intentLockedPage = ListConstants.EMPTY_STRING;
    private String builderPassword = ListConstants.EMPTY_STRING;
    private final Integer durationVibrateInput = 10;
    private KeyStore keyStore;
    private Cipher cipher;
    private String loadPassword;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_protected);
        final SharedPreferences sharedPreferences = getSharedPreferences(PASSWORD_TAG, MODE_PRIVATE);
        loadPassword = sharedPreferences.getString(PASSWORD_TAG, UNDEFENDED);
        initViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            final KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            final FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {

                if (!keyguardManager.isKeyguardSecure()) {
                    return;
                }

                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.USE_FINGERPRINT) !=
                        PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    return;
                }

                generateKey();

                final FingerprintManager.CryptoObject cryptoObject;
                if (cipherInit()) {
                    cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    final FingerprintHandler helper = new FingerprintHandler(this);
                    helper.startAuth(fingerprintManager, cryptoObject);
                }
            }
        }
    }

    private void initViews() {
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        intent = getIntent();
        intentLockedPage = intent.getStringExtra(TYPE_LOCKED_SCREEN);
        builderPassword = ListConstants.EMPTY_STRING;
        firstCircle = (ImageView) findViewById(R.id.crlcOne);
        secondCircle = (ImageView) findViewById(R.id.crlcTwo);
        thirdCircle = (ImageView) findViewById(R.id.crlcThree);
        fourthCircle = (ImageView) findViewById(R.id.crlcFour);
    }

    @TargetApi(23)
    protected void generateKey() {
        final String keyStore = "AndroidKeyStore";
        try {
            this.keyStore = KeyStore.getInstance(keyStore);
        } catch (final Exception e) {
            throw new RuntimeException(
                    getString(R.string.filed_to_getInstance_androidKeyStore), e);
        }

        final KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, keyStore);
        } catch (NoSuchAlgorithmException |
                NoSuchProviderException e) {
            throw new RuntimeException(
                    getString(R.string.filed_to_get_keygenerator_instance), e);
        }

        try {
            this.keyStore.load(null);
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

    @TargetApi(23)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException(getString(R.string.filed_to_get_cipher), e);
        }

        try {
            keyStore.load(null);
            final Key key = keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (final KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(getString(R.string.filed_to_init_cipher), e);
        }
    }

    public void setBackgroundCircle(final ImageView... args) {
        for (final ImageView v : args) {
            v.setBackgroundResource(R.drawable.ic_lens_black_24dp);
        }
    }

    public void inputPassword(final String number) {
        builderPassword += number;
        final Integer length = builderPassword.length();
        if (length == 1) {
            setBackgroundCircle(firstCircle);
        } else if (length == 2) {
            setBackgroundCircle(secondCircle);
        } else if (length == 3) {
            setBackgroundCircle(thirdCircle);
        } else if (length == 4) {
            setBackgroundCircle(fourthCircle);
            if (builderPassword.equals(loadPassword)) {
                start();
            } else {
                final Integer durationVibrateError = 200;
                mVibrator.vibrate(durationVibrateError);
                setBackgroundCircles(false, firstCircle, secondCircle, thirdCircle, fourthCircle);
                final int delayMillis = 350;
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        error();
                    }
                }, delayMillis);
            }
        }
    }

    public void zeroInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_ZERO);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void oneInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_ONE);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void twoInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_TWO);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void threeInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_THREE);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void fourInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_FOUR);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void fiveInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_FIVE);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void sixInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_SIX);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void sevenInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_SEVEN);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void eightInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_EIGHT);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void nineInput(final View view) {
        inputPassword(ListConstants.Keyboard.BUTTON_NINE);
        mVibrator.vibrate(durationVibrateInput);
    }

    public void setBackgroundCircles(final boolean flag, final ImageView... args) {
        for (final ImageView v : args) {
            if (!flag) {
                v.setBackgroundResource(R.drawable.ic_lens_red_24dp);
            } else {
                v.setBackgroundResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
        }
    }

    public void onDeleteClicked(final View view) {
        if (!builderPassword.isEmpty()) {
            builderPassword = ListConstants.EMPTY_STRING;
            setBackgroundCircles(true, firstCircle, secondCircle, thirdCircle, fourthCircle);
        }
    }

    public void error() {
        onDeleteClicked(null);
    }

    public void start() {
        if (intentLockedPage.equals(CREDIT_CARD)) {
            setBackgroundCircle(firstCircle, secondCircle, thirdCircle, fourthCircle);
            intent = new Intent(this, ItemsActivity.class);
            intent.putExtra(EXTRA_CHECK_ITEMS, EXTRA_BANK_TITLE_TO_ITEMS);
            startActivity(intent);
        } else {
            final Intent intent = new Intent(this, SetupPinActivity.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY));
        }
    }
}