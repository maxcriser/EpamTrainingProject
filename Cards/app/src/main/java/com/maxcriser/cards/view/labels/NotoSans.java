package com.maxcriser.cards.view.labels;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class NotoSans extends TextView {

    public NotoSans(final Context context) {
        super(context);
        init();
    }

    public NotoSans(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NotoSans(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            final Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/NotoSans-Bold.ttf");
            setTypeface(tf);
        }
    }
}
