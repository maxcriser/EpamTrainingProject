package com.maxcriser.cards.view.TextViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CreditNumber extends TextView {

    public CreditNumber(Context context) {
        super(context);
        init();
    }

    public CreditNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CreditNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //Roboto-Light.tff
    //Roboto-LightItalic.tff in name Cards
    //Roboto-Regular in title toolbar
    //Thin and ThinItalic

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/credit_number.ttf");
            setTypeface(tf);
        }
    }
}
