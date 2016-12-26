package android.support.v4.view.p008a;

import android.graphics.Rect;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: android.support.v4.view.a.b */
class C0142b {
    public static int m690a(Object obj) {
        return ((AccessibilityNodeInfo) obj).getActions();
    }

    public static void m691a(Object obj, int i) {
        ((AccessibilityNodeInfo) obj).addAction(i);
    }

    public static void m692a(Object obj, Rect rect) {
        ((AccessibilityNodeInfo) obj).getBoundsInParent(rect);
    }

    public static void m693a(Object obj, View view) {
        ((AccessibilityNodeInfo) obj).setParent(view);
    }

    public static void m694a(Object obj, CharSequence charSequence) {
        ((AccessibilityNodeInfo) obj).setClassName(charSequence);
    }

    public static void m695a(Object obj, boolean z) {
        ((AccessibilityNodeInfo) obj).setScrollable(z);
    }

    public static CharSequence m696b(Object obj) {
        return ((AccessibilityNodeInfo) obj).getClassName();
    }

    public static void m697b(Object obj, Rect rect) {
        ((AccessibilityNodeInfo) obj).getBoundsInScreen(rect);
    }

    public static CharSequence m698c(Object obj) {
        return ((AccessibilityNodeInfo) obj).getContentDescription();
    }

    public static CharSequence m699d(Object obj) {
        return ((AccessibilityNodeInfo) obj).getPackageName();
    }

    public static CharSequence m700e(Object obj) {
        return ((AccessibilityNodeInfo) obj).getText();
    }

    public static boolean m701f(Object obj) {
        return ((AccessibilityNodeInfo) obj).isCheckable();
    }

    public static boolean m702g(Object obj) {
        return ((AccessibilityNodeInfo) obj).isChecked();
    }

    public static boolean m703h(Object obj) {
        return ((AccessibilityNodeInfo) obj).isClickable();
    }

    public static boolean m704i(Object obj) {
        return ((AccessibilityNodeInfo) obj).isEnabled();
    }

    public static boolean m705j(Object obj) {
        return ((AccessibilityNodeInfo) obj).isFocusable();
    }

    public static boolean m706k(Object obj) {
        return ((AccessibilityNodeInfo) obj).isFocused();
    }

    public static boolean m707l(Object obj) {
        return ((AccessibilityNodeInfo) obj).isLongClickable();
    }

    public static boolean m708m(Object obj) {
        return ((AccessibilityNodeInfo) obj).isPassword();
    }

    public static boolean m709n(Object obj) {
        return ((AccessibilityNodeInfo) obj).isScrollable();
    }

    public static boolean m710o(Object obj) {
        return ((AccessibilityNodeInfo) obj).isSelected();
    }
}
