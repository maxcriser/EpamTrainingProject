package android.support.v4.view;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

/* renamed from: android.support.v4.view.v */
class C0236v {

    /* renamed from: android.support.v4.view.v.1 */
    static class C02351 implements OnApplyWindowInsetsListener {
        final /* synthetic */ C0218q f431a;

        C02351(C0218q c0218q) {
            this.f431a = c0218q;
        }

        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return ((an) this.f431a.m966a(view, new an(windowInsets))).m856e();
        }
    }

    public static void m1102a(View view) {
        view.requestApplyInsets();
    }

    public static void m1103a(View view, float f) {
        view.setElevation(f);
    }

    public static void m1104a(View view, C0218q c0218q) {
        view.setOnApplyWindowInsetsListener(new C02351(c0218q));
    }
}
