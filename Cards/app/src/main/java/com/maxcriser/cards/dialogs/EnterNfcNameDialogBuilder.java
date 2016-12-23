//TODO move to dialog package
package com.maxcriser.cards.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.maxcriser.cards.R;

class EnterNfcNameDialogBuilder extends AlertDialog {

    private final Context context;
    private EditText titleField;

    EnterNfcNameDialogBuilder(final Context context) {
        super(context);
        this.context = context;
    }

    void startDialog() {
        final LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.fragment_input_name_nfc, null);
        titleField = (EditText) layout.findViewById(R.id.title_nfc_card);
        final Builder builder = new Builder(context);
        builder.setView(layout);
        builder.setTitle(R.string.new_nfc_item);
        builder.setPositiveButton(R.string.save, new OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int pI) {
                final String title = titleField.getText().toString();
                if (!title.isEmpty()) {
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, R.string.fill_all_fields, Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int pI) {
                dialog.dismiss();
            }
        });
        final AlertDialog aDialog = builder.create();
        aDialog.show();
    }
}