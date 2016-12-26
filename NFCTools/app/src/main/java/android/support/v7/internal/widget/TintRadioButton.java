package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class TintRadioButton extends RadioButton {
    private static final int[] TINT_ATTRS;
    private final TintManager mTintManager;

    static {
        TINT_ATTRS = new int[]{16843015};
    }

    public TintRadioButton(Context context) {
        this(context, null);
    }

    public TintRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842878);
    }

    public TintRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, TINT_ATTRS, i, 0);
        setButtonDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
        this.mTintManager = obtainStyledAttributes.getTintManager();
    }

    public void setButtonDrawable(int i) {
        setButtonDrawable(this.mTintManager.getDrawable(i));
    }
}
