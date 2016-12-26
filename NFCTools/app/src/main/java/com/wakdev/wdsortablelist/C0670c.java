package com.wakdev.wdsortablelist;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;

/* renamed from: com.wakdev.wdsortablelist.c */
public class C0670c extends C0669b implements Checkable {
    public C0670c(Context context) {
        super(context);
    }

    public boolean isChecked() {
        View childAt = getChildAt(0);
        return childAt instanceof Checkable ? ((Checkable) childAt).isChecked() : false;
    }

    public void setChecked(boolean z) {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).setChecked(z);
        }
    }

    public void toggle() {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).toggle();
        }
    }
}
