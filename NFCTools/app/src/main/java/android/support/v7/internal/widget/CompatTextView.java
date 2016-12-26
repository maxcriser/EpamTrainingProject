package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.internal.p012b.C0335a;
import android.support.v7.p009a.C0301a.C0300k;
import android.util.AttributeSet;
import android.widget.TextView;

public class CompatTextView extends TextView {
    public CompatTextView(Context context) {
        this(context, null);
    }

    public CompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0300k.CompatTextView, i, 0);
        boolean z = obtainStyledAttributes.getBoolean(C0300k.CompatTextView_textAllCaps, false);
        obtainStyledAttributes.recycle();
        if (z) {
            setTransformationMethod(new C0335a(context));
        }
    }
}
