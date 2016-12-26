package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

/* renamed from: android.support.v4.view.c */
class C0184c {

    /* renamed from: android.support.v4.view.c.a */
    public interface C0131a {
        Object m579a(View view);

        void m580a(View view, int i);

        void m581a(View view, Object obj);

        boolean m582a(View view, int i, Bundle bundle);

        boolean m583a(View view, AccessibilityEvent accessibilityEvent);

        boolean m584a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void m585b(View view, AccessibilityEvent accessibilityEvent);

        void m586c(View view, AccessibilityEvent accessibilityEvent);

        void m587d(View view, AccessibilityEvent accessibilityEvent);
    }

    /* renamed from: android.support.v4.view.c.1 */
    static class C01831 extends AccessibilityDelegate {
        final /* synthetic */ C0131a f421a;

        C01831(C0131a c0131a) {
            this.f421a = c0131a;
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f421a.m583a(view, accessibilityEvent);
        }

        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            return (AccessibilityNodeProvider) this.f421a.m579a(view);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f421a.m585b(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f421a.m581a(view, (Object) accessibilityNodeInfo);
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f421a.m586c(view, accessibilityEvent);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f421a.m584a(viewGroup, view, accessibilityEvent);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.f421a.m582a(view, i, bundle);
        }

        public void sendAccessibilityEvent(View view, int i) {
            this.f421a.m580a(view, i);
        }

        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f421a.m587d(view, accessibilityEvent);
        }
    }

    public static Object m866a(C0131a c0131a) {
        return new C01831(c0131a);
    }

    public static Object m867a(Object obj, View view) {
        return ((AccessibilityDelegate) obj).getAccessibilityNodeProvider(view);
    }

    public static boolean m868a(Object obj, View view, int i, Bundle bundle) {
        return ((AccessibilityDelegate) obj).performAccessibilityAction(view, i, bundle);
    }
}
