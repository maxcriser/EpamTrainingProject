package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

public class TintCheckedTextView extends CheckedTextView {
    private static final int[] TINT_ATTRS;
    private final TintManager mTintManager;

    static {
        TINT_ATTRS = new int[]{16843016};
    }

    public TintCheckedTextView(Context context) {
        this(context, null);
    }

    public TintCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public TintCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, TINT_ATTRS, i, 0);
        setCheckMarkDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
        this.mTintManager = obtainStyledAttributes.getTintManager();
    }

    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(this.mTintManager.getDrawable(i));
    }
}
