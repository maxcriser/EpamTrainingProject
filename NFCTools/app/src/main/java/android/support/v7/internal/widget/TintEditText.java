package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class TintEditText extends EditText {
    private static final int[] TINT_ATTRS;

    static {
        TINT_ATTRS = new int[]{16842964};
    }

    public TintEditText(Context context) {
        this(context, null);
    }

    public TintEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public TintEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, TINT_ATTRS, i, 0);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
    }
}
