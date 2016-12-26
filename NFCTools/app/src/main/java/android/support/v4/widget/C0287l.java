package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

/* renamed from: android.support.v4.widget.l */
class C0287l {
    public static int m1359a(Object obj) {
        return ((OverScroller) obj).getCurrX();
    }

    public static Object m1360a(Context context, Interpolator interpolator) {
        return interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
    }

    public static void m1361a(Object obj, int i, int i2, int i3, int i4, int i5) {
        ((OverScroller) obj).startScroll(i, i2, i3, i4, i5);
    }

    public static void m1362a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        ((OverScroller) obj).fling(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static int m1363b(Object obj) {
        return ((OverScroller) obj).getCurrY();
    }

    public static boolean m1364c(Object obj) {
        return ((OverScroller) obj).computeScrollOffset();
    }

    public static void m1365d(Object obj) {
        ((OverScroller) obj).abortAnimation();
    }

    public static int m1366e(Object obj) {
        return ((OverScroller) obj).getFinalX();
    }

    public static int m1367f(Object obj) {
        return ((OverScroller) obj).getFinalY();
    }
}
