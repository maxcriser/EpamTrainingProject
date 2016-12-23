package com.maxcriser.cards.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.maxcriser.cards.R;
//TODO rename extends Dialog
public class AlertNfcOutput extends AlertDialog {

    private Context context;
    private ImageView posMachine;
    private ImageView longHand;
    private Animation animLongHand;


    public AlertNfcOutput(Context context) {
        super(context);
        this.context = context;
        this.animLongHand = AnimationUtils.loadAnimation(context, R.anim.anim_long_hand);
    }

    public void startDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.fragment_nfc_output, null);
        Builder builder = new Builder(context);
        builder.setView(layout);
        AlertDialog aDialog = builder.create();
        aDialog.show();
        posMachine = (ImageView) layout.findViewById(R.id.pos_machine);
        longHand = (ImageView) layout.findViewById(R.id.long_hand);
        longHand.startAnimation(animLongHand);
        animLongHand.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation pAnimation) {

            }

            @Override
            public void onAnimationEnd(Animation pAnimation) {
                longHand.startAnimation(animLongHand);
            }

            @Override
            public void onAnimationRepeat(Animation pAnimation) {

            }
        });
    }
}