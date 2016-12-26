package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

/* renamed from: android.support.v4.view.y */
class C0239y {
    public static int m1107a(int i, int i2, int i3) {
        return View.resolveSizeAndState(i, i2, i3);
    }

    public static int m1108a(View view) {
        return view.getLayerType();
    }

    static long m1109a() {
        return ValueAnimator.getFrameDelay();
    }

    public static void m1110a(View view, float f) {
        view.setTranslationX(f);
    }

    public static void m1111a(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }

    public static int m1112b(View view) {
        return view.getMeasuredWidthAndState();
    }

    public static void m1113b(View view, float f) {
        view.setTranslationY(f);
    }

    public static int m1114c(View view) {
        return view.getMeasuredState();
    }

    public static void m1115c(View view, float f) {
        view.setAlpha(f);
    }

    public static float m1116d(View view) {
        return view.getTranslationY();
    }

    public static void m1117d(View view, float f) {
        view.setScaleY(f);
    }

    public static void m1118e(View view) {
        view.jumpDrawablesToCurrentState();
    }
}
