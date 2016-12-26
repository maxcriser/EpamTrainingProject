package android.support.v4.p001b.p002a;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;

/* renamed from: android.support.v4.b.a.d */
class C0077d {
    public static void m378a(Drawable drawable, float f, float f2) {
        drawable.setHotspot(f, f2);
    }

    public static void m379a(Drawable drawable, int i) {
        drawable.setTint(i);
    }

    public static void m380a(Drawable drawable, int i, int i2, int i3, int i4) {
        drawable.setHotspotBounds(i, i2, i3, i4);
    }

    public static void m381a(Drawable drawable, ColorStateList colorStateList) {
        drawable.setTintList(colorStateList);
    }

    public static void m382a(Drawable drawable, Mode mode) {
        drawable.setTintMode(mode);
    }
}
