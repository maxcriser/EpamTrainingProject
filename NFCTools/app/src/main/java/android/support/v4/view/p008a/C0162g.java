package android.support.v4.view.p008a;

import android.os.Build.VERSION;

/* renamed from: android.support.v4.view.a.g */
public class C0162g {
    private static final C0157c f399a;
    private final Object f400b;

    /* renamed from: android.support.v4.view.a.g.c */
    interface C0157c {
        Object m737a();

        void m738a(Object obj, int i);

        void m739a(Object obj, boolean z);

        void m740b(Object obj, int i);

        void m741c(Object obj, int i);
    }

    /* renamed from: android.support.v4.view.a.g.e */
    static class C0158e implements C0157c {
        C0158e() {
        }

        public Object m742a() {
            return null;
        }

        public void m743a(Object obj, int i) {
        }

        public void m744a(Object obj, boolean z) {
        }

        public void m745b(Object obj, int i) {
        }

        public void m746c(Object obj, int i) {
        }
    }

    /* renamed from: android.support.v4.view.a.g.a */
    static class C0159a extends C0158e {
        C0159a() {
        }

        public Object m747a() {
            return C0163h.m757a();
        }

        public void m748a(Object obj, int i) {
            C0163h.m758a(obj, i);
        }

        public void m749a(Object obj, boolean z) {
            C0163h.m759a(obj, z);
        }

        public void m750b(Object obj, int i) {
            C0163h.m760b(obj, i);
        }

        public void m751c(Object obj, int i) {
            C0163h.m761c(obj, i);
        }
    }

    /* renamed from: android.support.v4.view.a.g.b */
    static class C0160b extends C0159a {
        C0160b() {
        }
    }

    /* renamed from: android.support.v4.view.a.g.d */
    static class C0161d extends C0160b {
        C0161d() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f399a = new C0161d();
        } else if (VERSION.SDK_INT >= 15) {
            f399a = new C0160b();
        } else if (VERSION.SDK_INT >= 14) {
            f399a = new C0159a();
        } else {
            f399a = new C0158e();
        }
    }

    public C0162g(Object obj) {
        this.f400b = obj;
    }

    public static C0162g m752a() {
        return new C0162g(f399a.m737a());
    }

    public void m753a(int i) {
        f399a.m740b(this.f400b, i);
    }

    public void m754a(boolean z) {
        f399a.m739a(this.f400b, z);
    }

    public void m755b(int i) {
        f399a.m738a(this.f400b, i);
    }

    public void m756c(int i) {
        f399a.m741c(this.f400b, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        C0162g c0162g = (C0162g) obj;
        return this.f400b == null ? c0162g.f400b == null : this.f400b.equals(c0162g.f400b);
    }

    public int hashCode() {
        return this.f400b == null ? 0 : this.f400b.hashCode();
    }
}
