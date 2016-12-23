package com.maxcriser.cards.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.maxcriser.cards.R;

public class NfcOutputDialogBuilder extends AlertDialog {

    private final Context context;
    private ImageView longHand;
    private final Animation animLongHand;

    public NfcOutputDialogBuilder(final Context context) {
        super(context);
        this.context = context;
        this.animLongHand = AnimationUtils.loadAnimation(context, R.anim.anim_long_hand);
    }

    public void startDialog() {
        final LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.fragment_nfc_output, null);
        final Builder builder = new Builder(context);
        builder.setView(layout);
        final AlertDialog aDialog = builder.create();
        aDialog.show();
        longHand = (ImageView) layout.findViewById(R.id.long_hand);
        longHand.startAnimation(animLongHand);
        animLongHand.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(final Animation pAnimation) {

            }

            @Override
            public void onAnimationEnd(final Animation pAnimation) {
                longHand.startAnimation(animLongHand);
            }

            @Override
            public void onAnimationRepeat(final Animation pAnimation) {

            }
        });
    }
}