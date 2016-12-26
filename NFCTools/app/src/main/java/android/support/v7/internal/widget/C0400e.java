package android.support.v7.internal.widget;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* renamed from: android.support.v7.internal.widget.e */
class C0400e extends Resources {
    private final TintManager f851a;

    public C0400e(Resources resources, TintManager tintManager) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f851a = tintManager;
    }

    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        if (drawable != null) {
            this.f851a.tintDrawable(i, drawable);
        }
        return drawable;
    }
}
