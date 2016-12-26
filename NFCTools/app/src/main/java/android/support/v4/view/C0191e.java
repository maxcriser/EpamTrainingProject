package android.support.v4.view;

import android.os.Build.VERSION;

/* renamed from: android.support.v4.view.e */
public class C0191e {
    static final C0188a f422a;

    /* renamed from: android.support.v4.view.e.a */
    interface C0188a {
        int m870a(int i, int i2);
    }

    /* renamed from: android.support.v4.view.e.b */
    static class C0189b implements C0188a {
        C0189b() {
        }

        public int m871a(int i, int i2) {
            return -8388609 & i;
        }
    }

    /* renamed from: android.support.v4.view.e.c */
    static class C0190c implements C0188a {
        C0190c() {
        }

        public int m872a(int i, int i2) {
            return C0192f.m874a(i, i2);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            f422a = new C0190c();
        } else {
            f422a = new C0189b();
        }
    }

    public static int m873a(int i, int i2) {
        return f422a.m870a(i, i2);
    }
}
