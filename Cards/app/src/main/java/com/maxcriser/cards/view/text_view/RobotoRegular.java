package com.maxcriser.cards.view.text_view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoRegular extends TextView {

    public RobotoRegular(Context context) {
        super(context);
        init();
    }

    public RobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/NotoSansUI-Bold.ttf");
            setTypeface(tf);
        }
    }
}
