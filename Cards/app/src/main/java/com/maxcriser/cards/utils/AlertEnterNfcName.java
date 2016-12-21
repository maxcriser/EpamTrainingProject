package com.maxcriser.cards.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.maxcriser.cards.R;

public class AlertEnterNfcName extends AlertDialog {

    private Context context;
    private EditText titleField;

    public AlertEnterNfcName(Context context) {
        super(context);
        this.context = context;
    }

    public void startDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.fragment_input_name_nfc, null);
        titleField = (EditText) layout.findViewById(R.id.title_nfc_card);
        Builder builder = new Builder(context);
        builder.setView(layout);
        builder.setTitle(R.string.new_nfc_item);
        builder.setPositiveButton(R.string.save, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pI) {
                String title = titleField.getText().toString();
                if (!title.isEmpty()) {
                    Toast.makeText(context, "TODO: Save to database nfc item", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Please fill title of the card and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pI) {
                Toast.makeText(context, "TODO: clear all variables", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        AlertDialog aDialog = builder.create();
        aDialog.show();
    }
}