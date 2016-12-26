package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class TintCheckBox extends CheckBox {
    private static final int[] TINT_ATTRS;
    private final TintManager mTintManager;

    static {
        TINT_ATTRS = new int[]{16843015};
    }

    public TintCheckBox(Context context) {
        this(context, null);
    }

    public TintCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842860);
    }

    public TintCheckBox(Context context, AttributeSet attributeSet, int i) {
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
