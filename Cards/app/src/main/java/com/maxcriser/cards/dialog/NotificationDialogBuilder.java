package com.maxcriser.cards.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.maxcriser.cards.R;

public class NotificationDialogBuilder extends AlertDialog {

    private final Context context;
    private final String message;
    private final String title;
    private final String url;
    private boolean showPositiveButton;
    private boolean showNegativaButton;

    public NotificationDialogBuilder(final Context context, final String title, final String message,
                                     final String url, final boolean pShowPositiveButton, final boolean pShowNegativaButton) {
        super(context);
        this.context = context;
        this.message = message;
        this.title = title;
        this.url = url;
        this.showNegativaButton = pShowNegativaButton;
        this.showPositiveButton = pShowPositiveButton;
    }

    public void startDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (showNegativaButton) {
            builder.setNegativeButton(R.string.cancel,
                    new OnClickListener() {

                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
        }
        if (showPositiveButton) {
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(final DialogInterface dialog, final int pI) {
                    if (url != null) {
                        goToUrl(url);
                    }
                    dialog.cancel();
                }
            });
        }
        builder.setTitle(title).setMessage(message);
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void goToUrl(final String url) {
        final Uri uriUrl = Uri.parse(url);
        final Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        context.startActivity(launchBrowser);
    }
}