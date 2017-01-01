package com.maxcriser.cards.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.create.CreateBankActivity;

public class MatchesFountDialogBuilder extends AlertDialog {

    private final Context context;
    private final String message;
    private final String creditNumber;
    private final String creditCardholder;
    private final String creditName;
    private final String creditType;
    private final String creditValid;

    public MatchesFountDialogBuilder(final Context context, final String message, final String... pArgs) {
        super(context);
        this.context = context;
        this.message = message;
        this.creditNumber = pArgs[0];
        this.creditCardholder = pArgs[1];
        this.creditName = pArgs[2];
        this.creditType = pArgs[3];
        this.creditValid = pArgs[4];
    }

    public void startDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.matches_found)
                .setMessage(message)
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {

                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(context.getString(R.string.apply), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int pI) {
                        ((CreateBankActivity) context).pasteRecognizeTextToViews(creditNumber, creditCardholder, creditName,
                                creditType, creditValid);
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}