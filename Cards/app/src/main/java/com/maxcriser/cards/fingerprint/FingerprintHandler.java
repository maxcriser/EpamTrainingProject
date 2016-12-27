package com.maxcriser.cards.fingerprint;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.maxcriser.cards.ui.activities.LockerActivity;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private final Context appContext;

    public FingerprintHandler(final Context context) {
        appContext = context;
    }

    public void startAuth(final FingerprintManager manager,
                          final FingerprintManager.CryptoObject cryptoObject) {

        final CancellationSignal cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(appContext,
                Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(final int errMsgId,
                                      final CharSequence errString) {
    }

    @Override
    public void onAuthenticationHelp(final int helpMsgId,
                                     final CharSequence helpString) {
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(appContext,
                "Authentication failed.",
                Toast.LENGTH_LONG).show();
        if (appContext instanceof LockerActivity) {
            ((LockerActivity) appContext).error();
        }
    }

    @Override
    public void onAuthenticationSucceeded(final FingerprintManager.AuthenticationResult result) {
        if (appContext instanceof LockerActivity) {
            ((LockerActivity) appContext).start();
        }
    }
}