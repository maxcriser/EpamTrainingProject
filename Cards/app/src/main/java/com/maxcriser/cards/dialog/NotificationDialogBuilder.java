package com.maxcriser.cards.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.maxcriser.cards.R;

import static com.maxcriser.cards.utils.manager.NetworkManager.goToUrl;

public class NotificationDialogBuilder extends AlertDialog {

    private final Context context;
    private final String message;
    private final String title;
    private final String url;
    private final String titleNeg;
    private final String titlePos;
    private final boolean showPositiveButton;
    private final boolean showNegativaButton;

    public NotificationDialogBuilder(final Context context, final String title, final String message,
                                     final String url, final boolean pShowPositiveButton, final boolean pShowNegativaButton,
                                     final String pTitlePos, final String pTitleNeg) {
        super(context);
        this.context = context;
        this.message = message;
        this.title = title;
        this.url = url;
        this.showNegativaButton = pShowNegativaButton;
        this.showPositiveButton = pShowPositiveButton;
        this.titleNeg = pTitleNeg;
        this.titlePos = pTitlePos;
    }

    public void startDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (showNegativaButton) {
            String btnNeg = context.getString(R.string.cancel);
            if (titlePos != null) {
                btnNeg = titleNeg;
            }
            builder.setNegativeButton(btnNeg,
                    new OnClickListener() {

                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
        }
        if (showPositiveButton) {
            String btnPos = context.getString(R.string.ok);
            if (titlePos != null) {
                btnPos = titlePos;
            }
            builder.setPositiveButton(btnPos, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(final DialogInterface dialog, final int pI) {
                    if (url != null) {
                        goToUrl(context, url);
                    }
                    dialog.cancel();
                }
            });
        }
        builder.setTitle(title).setMessage(message);
        final AlertDialog alert = builder.create();
        alert.show();
    }
}