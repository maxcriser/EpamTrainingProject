package com.maxcriser.cards.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoThinTextView extends TextView {

    public RobotoThinTextView(Context context) {
        super(context);
        init();
    }

    public RobotoThinTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoThinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Thin.ttf");
            setTypeface(tf);
        }
    }
}
