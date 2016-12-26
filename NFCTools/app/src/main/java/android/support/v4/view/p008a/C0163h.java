package android.support.v4.view.p008a;

import android.view.accessibility.AccessibilityRecord;

/* renamed from: android.support.v4.view.a.h */
class C0163h {
    public static Object m757a() {
        return AccessibilityRecord.obtain();
    }

    public static void m758a(Object obj, int i) {
        ((AccessibilityRecord) obj).setFromIndex(i);
    }

    public static void m759a(Object obj, boolean z) {
        ((AccessibilityRecord) obj).setScrollable(z);
    }

    public static void m760b(Object obj, int i) {
        ((AccessibilityRecord) obj).setItemCount(i);
    }

    public static void m761c(Object obj, int i) {
        ((AccessibilityRecord) obj).setToIndex(i);
    }
}
