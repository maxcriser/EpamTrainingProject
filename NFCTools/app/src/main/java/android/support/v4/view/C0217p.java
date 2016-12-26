package android.support.v4.view;

import android.view.MotionEvent;

/* renamed from: android.support.v4.view.p */
class C0217p {
    public static int m961a(MotionEvent motionEvent) {
        return motionEvent.getPointerCount();
    }

    public static int m962a(MotionEvent motionEvent, int i) {
        return motionEvent.findPointerIndex(i);
    }

    public static int m963b(MotionEvent motionEvent, int i) {
        return motionEvent.getPointerId(i);
    }

    public static float m964c(MotionEvent motionEvent, int i) {
        return motionEvent.getX(i);
    }

    public static float m965d(MotionEvent motionEvent, int i) {
        return motionEvent.getY(i);
    }
}
