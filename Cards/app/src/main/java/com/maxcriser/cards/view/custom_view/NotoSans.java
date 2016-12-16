package com.maxcriser.cards.view.custom_view;

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

    //Roboto-Light.tff
    //Roboto-LightItalic.tff in name Cards
    //Roboto-Regular in title toolbar
    //Thin and ThinItalic

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/NotoSans-Bold.ttf");
            setTypeface(tf);
        }
    }
}
