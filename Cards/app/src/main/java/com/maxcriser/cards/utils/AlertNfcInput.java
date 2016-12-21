package com.maxcriser.cards.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.maxcriser.cards.R;

public class AlertNfcInput extends AlertDialog {

    private Context context;
    private ImageView card;
    private Animation animCard;


    public AlertNfcInput(Context context) {
        super(context);
        this.context = context;
        this.animCard = AnimationUtils.loadAnimation(context, R.anim.anim_card);
    }

    public void startDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.fragment_nfc_input, null);
        Builder builder = new Builder(context);
        builder.setView(layout);
        final AlertDialog aDialog = builder.create();
        aDialog.show();
        card = (ImageView) layout.findViewById(R.id.card);
        card.startAnimation(animCard);
        animCard.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation pAnimation) {
            }

            @Override
            public void onAnimationEnd(Animation pAnimation) {
                card.startAnimation(animCard);
            }

            @Override
            public void onAnimationRepeat(Animation pAnimation) {
            }
        });

        aDialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface pDialogInterface) {
                AlertEnterNfcName dialog = new AlertEnterNfcName(context);
                dialog.startDialog();
            }
        });
    }
}