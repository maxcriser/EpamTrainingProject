package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewConfiguration;

public class ac {
    static final C0164e f401a;

    /* renamed from: android.support.v4.view.ac.e */
    interface C0164e {
        int m774a(ViewConfiguration viewConfiguration);

        boolean m775b(ViewConfiguration viewConfiguration);
    }

    /* renamed from: android.support.v4.view.ac.a */
    static class C0165a implements C0164e {
        C0165a() {
        }

        public int m776a(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledTouchSlop();
        }

        public boolean m777b(ViewConfiguration viewConfiguration) {
            return true;
        }
    }

    /* renamed from: android.support.v4.view.ac.b */
    static class C0166b extends C0165a {
        C0166b() {
        }

        public int m778a(ViewConfiguration viewConfiguration) {
            return ad.m783a(viewConfiguration);
        }
    }

    /* renamed from: android.support.v4.view.ac.c */
    static class C0167c extends C0166b {
        C0167c() {
        }

        public boolean m779b(ViewConfiguration viewConfiguration) {
            return false;
        }
    }

    /* renamed from: android.support.v4.view.ac.d */
    static class C0168d extends C0167c {
        C0168d() {
        }

        public boolean m780b(ViewConfiguration viewConfiguration) {
            return ae.m784a(viewConfiguration);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            f401a = new C0168d();
        } else if (VERSION.SDK_INT >= 11) {
            f401a = new C0167c();
        } else if (VERSION.SDK_INT >= 8) {
            f401a = new C0166b();
        } else {
            f401a = new C0165a();
        }
    }

    public static int m781a(ViewConfiguration viewConfiguration) {
        return f401a.m774a(viewConfiguration);
    }

    public static boolean m782b(ViewConfiguration viewConfiguration) {
        return f401a.m775b(viewConfiguration);
    }
}
