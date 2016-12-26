package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;

/* renamed from: android.support.v4.widget.e */
public class C0273e {
    private static final C0270c f543b;
    private Object f544a;

    /* renamed from: android.support.v4.widget.e.c */
    interface C0270c {
        Object m1278a(Context context);

        void m1279a(Object obj, int i, int i2);

        boolean m1280a(Object obj);

        boolean m1281a(Object obj, float f);

        boolean m1282a(Object obj, Canvas canvas);

        void m1283b(Object obj);

        boolean m1284c(Object obj);
    }

    /* renamed from: android.support.v4.widget.e.a */
    static class C0271a implements C0270c {
        C0271a() {
        }

        public Object m1285a(Context context) {
            return null;
        }

        public void m1286a(Object obj, int i, int i2) {
        }

        public boolean m1287a(Object obj) {
            return true;
        }

        public boolean m1288a(Object obj, float f) {
            return false;
        }

        public boolean m1289a(Object obj, Canvas canvas) {
            return false;
        }

        public void m1290b(Object obj) {
        }

        public boolean m1291c(Object obj) {
            return false;
        }
    }

    /* renamed from: android.support.v4.widget.e.b */
    static class C0272b implements C0270c {
        C0272b() {
        }

        public Object m1292a(Context context) {
            return C0274f.m1305a(context);
        }

        public void m1293a(Object obj, int i, int i2) {
            C0274f.m1306a(obj, i, i2);
        }

        public boolean m1294a(Object obj) {
            return C0274f.m1307a(obj);
        }

        public boolean m1295a(Object obj, float f) {
            return C0274f.m1308a(obj, f);
        }

        public boolean m1296a(Object obj, Canvas canvas) {
            return C0274f.m1309a(obj, canvas);
        }

        public void m1297b(Object obj) {
            C0274f.m1310b(obj);
        }

        public boolean m1298c(Object obj) {
            return C0274f.m1311c(obj);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            f543b = new C0272b();
        } else {
            f543b = new C0271a();
        }
    }

    public C0273e(Context context) {
        this.f544a = f543b.m1278a(context);
    }

    public void m1299a(int i, int i2) {
        f543b.m1279a(this.f544a, i, i2);
    }

    public boolean m1300a() {
        return f543b.m1280a(this.f544a);
    }

    public boolean m1301a(float f) {
        return f543b.m1281a(this.f544a, f);
    }

    public boolean m1302a(Canvas canvas) {
        return f543b.m1282a(this.f544a, canvas);
    }

    public void m1303b() {
        f543b.m1283b(this.f544a);
    }

    public boolean m1304c() {
        return f543b.m1284c(this.f544a);
    }
}
