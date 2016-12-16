package com.maxcriser.cards.view.custom_view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class BarcodeEan extends TextView {

    public BarcodeEan(Context context) {
        super(context);
        init();
    }

    public BarcodeEan(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarcodeEan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/EANP72-1.TTF");
            setTypeface(tf);
        }
    }
}
