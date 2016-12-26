package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: android.support.v4.view.b */
class C0182b {

    /* renamed from: android.support.v4.view.b.a */
    public interface C0126a {
        void m534a(View view, int i);

        void m535a(View view, Object obj);

        boolean m536a(View view, AccessibilityEvent accessibilityEvent);

        boolean m537a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void m538b(View view, AccessibilityEvent accessibilityEvent);

        void m539c(View view, AccessibilityEvent accessibilityEvent);

        void m540d(View view, AccessibilityEvent accessibilityEvent);
    }

    /* renamed from: android.support.v4.view.b.1 */
    static class C01811 extends AccessibilityDelegate {
        final /* synthetic */ C0126a f420a;

        C01811(C0126a c0126a) {
            this.f420a = c0126a;
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f420a.m536a(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f420a.m538b(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f420a.m535a(view, (Object) accessibilityNodeInfo);
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f420a.m539c(view, accessibilityEvent);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f420a.m537a(viewGroup, view, accessibilityEvent);
        }

        public void sendAccessibilityEvent(View view, int i) {
            this.f420a.m534a(view, i);
        }

        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f420a.m540d(view, accessibilityEvent);
        }
    }

    public static Object m857a() {
        return new AccessibilityDelegate();
    }

    public static Object m858a(C0126a c0126a) {
        return new C01811(c0126a);
    }

    public static void m859a(Object obj, View view, int i) {
        ((AccessibilityDelegate) obj).sendAccessibilityEvent(view, i);
    }

    public static void m860a(Object obj, View view, Object obj2) {
        ((AccessibilityDelegate) obj).onInitializeAccessibilityNodeInfo(view, (AccessibilityNodeInfo) obj2);
    }

    public static boolean m861a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        return ((AccessibilityDelegate) obj).dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean m862a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return ((AccessibilityDelegate) obj).onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public static void m863b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public static void m864c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static void m865d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
