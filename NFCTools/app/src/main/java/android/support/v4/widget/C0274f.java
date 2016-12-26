package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

/* renamed from: android.support.v4.widget.f */
class C0274f {
    public static Object m1305a(Context context) {
        return new EdgeEffect(context);
    }

    public static void m1306a(Object obj, int i, int i2) {
        ((EdgeEffect) obj).setSize(i, i2);
    }

    public static boolean m1307a(Object obj) {
        return ((EdgeEffect) obj).isFinished();
    }

    public static boolean m1308a(Object obj, float f) {
        ((EdgeEffect) obj).onPull(f);
        return true;
    }

    public static boolean m1309a(Object obj, Canvas canvas) {
        return ((EdgeEffect) obj).draw(canvas);
    }

    public static void m1310b(Object obj) {
        ((EdgeEffect) obj).finish();
    }

    public static boolean m1311c(Object obj) {
        EdgeEffect edgeEffect = (EdgeEffect) obj;
        edgeEffect.onRelease();
        return edgeEffect.isFinished();
    }
}
