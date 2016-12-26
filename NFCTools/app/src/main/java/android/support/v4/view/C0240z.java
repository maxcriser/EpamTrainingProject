package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;

/* renamed from: android.support.v4.view.z */
class C0240z {
    public static void m1119a(View view, Object obj) {
        view.setAccessibilityDelegate((AccessibilityDelegate) obj);
    }

    public static boolean m1120a(View view, int i) {
        return view.canScrollHorizontally(i);
    }
}
