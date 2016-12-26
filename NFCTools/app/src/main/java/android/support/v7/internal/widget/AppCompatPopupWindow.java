package android.support.v7.internal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v7.p009a.C0301a.C0300k;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

public class AppCompatPopupWindow extends PopupWindow {
    private final boolean mOverlapAnchor;

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0300k.PopupWindow, i, 0);
        this.mOverlapAnchor = obtainStyledAttributes.getBoolean(C0300k.PopupWindow_overlapAnchor, false);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(C0300k.PopupWindow_android_popupBackground));
        obtainStyledAttributes.recycle();
    }

    public void showAsDropDown(View view, int i, int i2) {
        if (VERSION.SDK_INT < 21 && this.mOverlapAnchor) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @TargetApi(19)
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (VERSION.SDK_INT < 21 && this.mOverlapAnchor) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    public void update(View view, int i, int i2, int i3, int i4) {
        int height = (VERSION.SDK_INT >= 21 || !this.mOverlapAnchor) ? i2 : i2 - view.getHeight();
        super.update(view, i, height, i3, i4);
    }
}
