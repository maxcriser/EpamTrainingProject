package android.support.v4.view.p008a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* renamed from: android.support.v4.view.a.f */
class C0156f {

    /* renamed from: android.support.v4.view.a.f.a */
    interface C0149a {
        Object m721a(int i);

        List<Object> m722a(String str, int i);

        boolean m723a(int i, int i2, Bundle bundle);

        Object m724b(int i);
    }

    /* renamed from: android.support.v4.view.a.f.1 */
    static class C01551 extends AccessibilityNodeProvider {
        final /* synthetic */ C0149a f398a;

        C01551(C0149a c0149a) {
            this.f398a = c0149a;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            return (AccessibilityNodeInfo) this.f398a.m721a(i);
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            return this.f398a.m722a(str, i);
        }

        public AccessibilityNodeInfo findFocus(int i) {
            return (AccessibilityNodeInfo) this.f398a.m724b(i);
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f398a.m723a(i, i2, bundle);
        }
    }

    public static Object m736a(C0149a c0149a) {
        return new C01551(c0149a);
    }
}
