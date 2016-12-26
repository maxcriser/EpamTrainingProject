package com.wakdev.wdsortablelist;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;

/* renamed from: com.wakdev.wdsortablelist.b */
public class C0669b extends ViewGroup {
    private int f2589a;

    public C0669b(Context context) {
        super(context);
        this.f2589a = 48;
        setLayoutParams(new LayoutParams(-1, -2));
    }

    public int getGravity() {
        return this.f2589a;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            if (this.f2589a == 48) {
                childAt.layout(0, 0, getMeasuredWidth(), childAt.getMeasuredHeight());
            } else {
                childAt.layout(0, getMeasuredHeight() - childAt.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        int size2 = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i2);
        View childAt = getChildAt(0);
        if (childAt == null) {
            setMeasuredDimension(0, size2);
            return;
        }
        if (childAt.isLayoutRequested()) {
            measureChild(childAt, i, MeasureSpec.makeMeasureSpec(0, 0));
        }
        if (mode == 0) {
            LayoutParams layoutParams = getLayoutParams();
            size = layoutParams.height > 0 ? layoutParams.height : childAt.getMeasuredHeight();
        }
        setMeasuredDimension(size2, size);
    }

    public void setGravity(int i) {
        this.f2589a = i;
    }
}
