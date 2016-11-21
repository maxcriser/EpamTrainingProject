package com.maxcriser.cards.view.TextViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoThin extends TextView {

    public RobotoThin(Context context) {
        super(context);
        init();
    }

    public RobotoThin(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoThin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //TODO may be
    //Roboto-Light.tff
    //Roboto-LightItalic.tff in name Cards
    //Roboto-Regular in title toolbar
    //Thin and ThinItalic
    //
    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Thin.ttf");
            setTypeface(tf);
        }
    }
}
