package com.maxcriser.cards.view.EditTextViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class RobotoThinEditText extends EditText {
    public RobotoThinEditText(Context context) {
        super(context);
        init();
    }

    public RobotoThinEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoThinEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
