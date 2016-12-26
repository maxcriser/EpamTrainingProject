package android.support.v7.internal.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;

/* renamed from: android.support.v7.internal.widget.d */
class C0399d extends C0379c {
    private final ColorStateList f848a;
    private final Mode f849b;
    private int f850c;

    public C0399d(Drawable drawable, ColorStateList colorStateList) {
        this(drawable, colorStateList, TintManager.DEFAULT_MODE);
    }

    public C0399d(Drawable drawable, ColorStateList colorStateList, Mode mode) {
        super(drawable);
        this.f848a = colorStateList;
        this.f849b = mode;
    }

    private boolean m1764a(int[] iArr) {
        if (this.f848a != null) {
            int colorForState = this.f848a.getColorForState(iArr, this.f850c);
            if (colorForState != this.f850c) {
                if (colorForState != 0) {
                    setColorFilter(colorForState, this.f849b);
                } else {
                    clearColorFilter();
                }
                this.f850c = colorForState;
                return true;
            }
        }
        return false;
    }

    public boolean isStateful() {
        return (this.f848a != null && this.f848a.isStateful()) || super.isStateful();
    }

    public boolean setState(int[] iArr) {
        return m1764a(iArr) || super.setState(iArr);
    }
}
