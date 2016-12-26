package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewGroup.MarginLayoutParams;

/* renamed from: android.support.v4.view.j */
public class C0203j {
    static final C0200a f424a;

    /* renamed from: android.support.v4.view.j.a */
    interface C0200a {
        int m894a(MarginLayoutParams marginLayoutParams);

        int m895b(MarginLayoutParams marginLayoutParams);
    }

    /* renamed from: android.support.v4.view.j.b */
    static class C0201b implements C0200a {
        C0201b() {
        }

        public int m896a(MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.leftMargin;
        }

        public int m897b(MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.rightMargin;
        }
    }

    /* renamed from: android.support.v4.view.j.c */
    static class C0202c implements C0200a {
        C0202c() {
        }

        public int m898a(MarginLayoutParams marginLayoutParams) {
            return C0204k.m902a(marginLayoutParams);
        }

        public int m899b(MarginLayoutParams marginLayoutParams) {
            return C0204k.m903b(marginLayoutParams);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            f424a = new C0202c();
        } else {
            f424a = new C0201b();
        }
    }

    public static int m900a(MarginLayoutParams marginLayoutParams) {
        return f424a.m894a(marginLayoutParams);
    }

    public static int m901b(MarginLayoutParams marginLayoutParams) {
        return f424a.m895b(marginLayoutParams);
    }
}
