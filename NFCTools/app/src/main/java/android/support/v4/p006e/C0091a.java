package android.support.v4.p006e;

import android.os.Build.VERSION;

/* renamed from: android.support.v4.e.a */
public class C0091a {
    private static final C0088a f256a;

    /* renamed from: android.support.v4.e.a.a */
    interface C0088a {
        String m389a(String str);

        String m390b(String str);
    }

    /* renamed from: android.support.v4.e.a.b */
    static class C0089b implements C0088a {
        C0089b() {
        }

        public String m391a(String str) {
            return null;
        }

        public String m392b(String str) {
            return str;
        }
    }

    /* renamed from: android.support.v4.e.a.c */
    static class C0090c implements C0088a {
        C0090c() {
        }

        public String m393a(String str) {
            return C0092b.m397a(str);
        }

        public String m394b(String str) {
            return C0092b.m398b(str);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            f256a = new C0090c();
        } else {
            f256a = new C0089b();
        }
    }

    public static String m395a(String str) {
        return f256a.m389a(str);
    }

    public static String m396b(String str) {
        return f256a.m390b(str);
    }
}
