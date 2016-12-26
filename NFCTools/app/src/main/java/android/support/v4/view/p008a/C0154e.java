package android.support.v4.view.p008a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* renamed from: android.support.v4.view.a.e */
class C0154e {

    /* renamed from: android.support.v4.view.a.e.a */
    interface C0145a {
        Object m713a(int i);

        List<Object> m714a(String str, int i);

        boolean m715a(int i, int i2, Bundle bundle);
    }

    /* renamed from: android.support.v4.view.a.e.1 */
    static class C01531 extends AccessibilityNodeProvider {
        final /* synthetic */ C0145a f397a;

        C01531(C0145a c0145a) {
            this.f397a = c0145a;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            return (AccessibilityNodeInfo) this.f397a.m713a(i);
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            return this.f397a.m714a(str, i);
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f397a.m715a(i, i2, bundle);
        }
    }

    public static Object m735a(C0145a c0145a) {
        return new C01531(c0145a);
    }
}
