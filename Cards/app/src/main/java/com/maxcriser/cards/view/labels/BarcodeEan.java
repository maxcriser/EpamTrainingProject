package com.maxcriser.cards.view.labels;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class BarcodeEan extends TextView {

    public BarcodeEan(final Context context) {
        super(context);
        init();
    }

    public BarcodeEan(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarcodeEan(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            final Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Barcode.ttf");
            setTypeface(tf);
        }
    }
}
