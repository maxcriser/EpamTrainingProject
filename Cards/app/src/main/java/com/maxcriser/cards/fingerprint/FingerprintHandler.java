package com.maxcriser.cards.fingerprint;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.ItemsActivity;
import com.maxcriser.cards.ui.LockerActivity;
import com.maxcriser.cards.ui.MenuActivity;

import java.util.concurrent.locks.Lock;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context appContext;

    //TODO ???
    private LockerActivity mLockerActivity = new LockerActivity();

    public FingerprintHandler(Context context) {
        appContext = context;
    }

    public void startAuth(FingerprintManager manager,
                          FingerprintManager.CryptoObject cryptoObject) {

        CancellationSignal cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(appContext,
                Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId,
                                      CharSequence errString) {
        Toast.makeText(appContext,
                "Authentication error\n" + errString,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId,
                                     CharSequence helpString) {
        Toast.makeText(appContext,
                "Authentication help\n" + helpString,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
//        mLockerActivity.error();
        Toast.makeText(appContext,
                "Authentication failed.",
                Toast.LENGTH_LONG).show();
        if(appContext instanceof LockerActivity){
            ((LockerActivity)appContext).error();
        }
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
//        mLockerActivity.start();
        Toast.makeText(appContext,
                "Authentication succeeded.",
                Toast.LENGTH_LONG).show();
        if(appContext instanceof LockerActivity){
            ((LockerActivity)appContext).start();
        }
    }
}