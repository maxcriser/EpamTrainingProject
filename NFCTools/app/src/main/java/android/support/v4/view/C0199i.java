package android.support.v4.view;

import android.view.KeyEvent;

/* renamed from: android.support.v4.view.i */
class C0199i {
    public static int m891a(int i) {
        return KeyEvent.normalizeMetaState(i);
    }

    public static boolean m892a(int i, int i2) {
        return KeyEvent.metaStateHasModifiers(i, i2);
    }

    public static boolean m893b(int i) {
        return KeyEvent.metaStateHasNoModifiers(i);
    }
}
