package android.support.v4.view;

import android.view.View;

class aa {
    public static void m762a(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void m763a(View view, int i) {
        view.setImportantForAccessibility(i);
    }

    public static void m764a(View view, int i, int i2, int i3, int i4) {
        view.postInvalidate(i, i2, i3, i4);
    }

    public static void m765a(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void m766a(View view, Runnable runnable, long j) {
        view.postOnAnimationDelayed(runnable, j);
    }

    public static int m767b(View view) {
        return view.getImportantForAccessibility();
    }

    public static int m768c(View view) {
        return view.getMinimumHeight();
    }

    public static void m769d(View view) {
        view.requestFitSystemWindows();
    }

    public static boolean m770e(View view) {
        return view.getFitsSystemWindows();
    }
}
