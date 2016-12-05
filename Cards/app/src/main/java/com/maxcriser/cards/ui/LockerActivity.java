package com.maxcriser.cards.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.handler.FingerprintHandler;
import com.maxcriser.cards.ui.cards.BankCardsActivity;

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

import static com.maxcriser.cards.constant.constants.BUTTON_EIGHT;
import static com.maxcriser.cards.constant.constants.BUTTON_FIVE;
import static com.maxcriser.cards.constant.constants.BUTTON_FOUR;
import static com.maxcriser.cards.constant.constants.BUTTON_NINE;
import static com.maxcriser.cards.constant.constants.BUTTON_ONE;
import static com.maxcriser.cards.constant.constants.BUTTON_SEVEN;
import static com.maxcriser.cards.constant.constants.BUTTON_SIX;
import static com.maxcriser.cards.constant.constants.BUTTON_THREE;
import static com.maxcriser.cards.constant.constants.BUTTON_TWO;
import static com.maxcriser.cards.constant.constants.BUTTON_ZERO;

public class LockerActivity extends AppCompatActivity {

    Handler mHandler;

    ImageView firstCircle;
    ImageView secondCircle;
    ImageView thirdCircle;
    ImageView fourthCircle;
    Vibrator mVibrator;
    Intent intent;
    String intentLockedPage;
    String builderPassword;

    private static final String KEY_NAME = "finger_key";

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;

    Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            error();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_protected);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        intent = getIntent();
        intentLockedPage = intent.getStringExtra(MainActivity.TYPE_LOCKED_SCREEN);

        Toast.makeText(this, LaunchScreenActivity.loadPassword, Toast.LENGTH_SHORT).show();
        mHandler = new Handler(hc);
        builderPassword = "";

        firstCircle = (ImageView) findViewById(R.id.crlcOne);
        secondCircle = (ImageView) findViewById(R.id.crlcTwo);
        thirdCircle = (ImageView) findViewById(R.id.crlcThree);
        fourthCircle = (ImageView) findViewById(R.id.crlcFour);

//        TODO FIX BUG At least finger
        if (Build.VERSION.SDK_INT >= 23) {

            keyguardManager =
                    (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            fingerprintManager =
                    (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {

                if (!keyguardManager.isKeyguardSecure()) {
                    Toast.makeText(this,
                            "Lock screen security not enabled in Settings",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.USE_FINGERPRINT) !=
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,
                            "Fingerprint authentication permission not enabled",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                if (!fingerprintManager.hasEnrolledFingerprints()) {

                    // This happens when no fingerprints are registered.
                    Toast.makeText(this,
                            "Register at least one fingerprint in Settings",
                            Toast.LENGTH_LONG).show();
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
        }
    }

    @TargetApi(23)
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

    @TargetApi(23)
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

    public void setBackgroundCircle(ImageView v) {
        v.setBackgroundResource(R.drawable.ic_lens_black_24dp);
    }

    public void inputPassword(String number) {

        builderPassword += number;

        // TODO length
        Integer length = builderPassword.length();

        if (length == 1) {
            setBackgroundCircle(firstCircle);
        } else if (length == 2) {
            setBackgroundCircle(secondCircle);
        } else if (length == 3) {
            setBackgroundCircle(thirdCircle);
        } else if (length == 4) {
            setBackgroundCircle(fourthCircle);
            if (builderPassword.equals(LaunchScreenActivity.loadPassword)) {
                start();
            } else {
                //TODO animation
                mVibrator.vibrate(200);
                setBackgroundCircles(false, firstCircle, secondCircle, thirdCircle, fourthCircle);
                mHandler.sendEmptyMessageDelayed(1, 350);
            }
        }
    }

    public void zeroInput(View view) {
        inputPassword(BUTTON_ZERO);
    }

    public void oneInput(View view) {
        inputPassword(BUTTON_ONE);
    }

    public void twoInput(View view) {
        inputPassword(BUTTON_TWO);
    }

    public void threeInput(View view) {
        inputPassword(BUTTON_THREE);
    }

    public void fourInput(View view) {
        inputPassword(BUTTON_FOUR);
    }

    public void fiveInput(View view) {
        inputPassword(BUTTON_FIVE);
    }

    public void sixInput(View view) {
        inputPassword(BUTTON_SIX);
    }

    public void sevenInput(View view) {
        inputPassword(BUTTON_SEVEN);
    }

    public void eightInput(View view) {
        inputPassword(BUTTON_EIGHT);
    }

    public void nineInput(View view) {
        inputPassword(BUTTON_NINE);
    }

    public void setBackgroundCircles(boolean flag, ImageView... args) {
        for (ImageView v : args) {
            if (!flag) {
                v.setBackgroundResource(R.drawable.ic_lens_red_24dp);
            } else {
                v.setBackgroundResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
        }
    }

    public void onDeleteClicked(View view) {
        if (builderPassword.length() != 0) {
            builderPassword = "";
            setBackgroundCircles(true, firstCircle, secondCircle, thirdCircle, fourthCircle);
        }
    }

    public void error() {
        onDeleteClicked(null);
    }

    public void start() {
        if (intentLockedPage.equals(MainActivity.CREDIT_CARD)) {
            startActivity(new Intent(LockerActivity.this, BankCardsActivity.class));
        } else {
            Intent intent = new Intent(LockerActivity.this, SetupPIN.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY));
        }
    }
}