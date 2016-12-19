package com.maxcriser.cards.view.text_view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class NotoSans extends TextView {

    public NotoSans(Context context) {
        super(context);
        init();
    }

    public NotoSans(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NotoSans(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/NotoSans-Bold.ttf");
            setTypeface(tf);
        }
    }
}
