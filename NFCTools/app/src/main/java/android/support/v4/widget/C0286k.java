package android.support.v4.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* renamed from: android.support.v4.widget.k */
public class C0286k {
    Object f550a;
    C0282a f551b;

    /* renamed from: android.support.v4.widget.k.a */
    interface C0282a {
        int m1322a(Object obj);

        Object m1323a(Context context, Interpolator interpolator);

        void m1324a(Object obj, int i, int i2, int i3, int i4, int i5);

        void m1325a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

        int m1326b(Object obj);

        boolean m1327c(Object obj);

        void m1328d(Object obj);

        int m1329e(Object obj);

        int m1330f(Object obj);
    }

    /* renamed from: android.support.v4.widget.k.b */
    static class C0283b implements C0282a {
        C0283b() {
        }

        public int m1331a(Object obj) {
            return ((Scroller) obj).getCurrX();
        }

        public Object m1332a(Context context, Interpolator interpolator) {
            return interpolator != null ? new Scroller(context, interpolator) : new Scroller(context);
        }

        public void m1333a(Object obj, int i, int i2, int i3, int i4, int i5) {
            ((Scroller) obj).startScroll(i, i2, i3, i4, i5);
        }

        public void m1334a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ((Scroller) obj).fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public int m1335b(Object obj) {
            return ((Scroller) obj).getCurrY();
        }

        public boolean m1336c(Object obj) {
            return ((Scroller) obj).computeScrollOffset();
        }

        public void m1337d(Object obj) {
            ((Scroller) obj).abortAnimation();
        }

        public int m1338e(Object obj) {
            return ((Scroller) obj).getFinalX();
        }

        public int m1339f(Object obj) {
            return ((Scroller) obj).getFinalY();
        }
    }

    /* renamed from: android.support.v4.widget.k.c */
    static class C0284c implements C0282a {
        C0284c() {
        }

        public int m1340a(Object obj) {
            return C0287l.m1359a(obj);
        }

        public Object m1341a(Context context, Interpolator interpolator) {
            return C0287l.m1360a(context, interpolator);
        }

        public void m1342a(Object obj, int i, int i2, int i3, int i4, int i5) {
            C0287l.m1361a(obj, i, i2, i3, i4, i5);
        }

        public void m1343a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            C0287l.m1362a(obj, i, i2, i3, i4, i5, i6, i7, i8);
        }

        public int m1344b(Object obj) {
            return C0287l.m1363b(obj);
        }

        public boolean m1345c(Object obj) {
            return C0287l.m1364c(obj);
        }

        public void m1346d(Object obj) {
            C0287l.m1365d(obj);
        }

        public int m1347e(Object obj) {
            return C0287l.m1366e(obj);
        }

        public int m1348f(Object obj) {
            return C0287l.m1367f(obj);
        }
    }

    /* renamed from: android.support.v4.widget.k.d */
    static class C0285d extends C0284c {
        C0285d() {
        }
    }

    private C0286k(int i, Context context, Interpolator interpolator) {
        if (i >= 14) {
            this.f551b = new C0285d();
        } else if (i >= 9) {
            this.f551b = new C0284c();
        } else {
            this.f551b = new C0283b();
        }
        this.f550a = this.f551b.m1323a(context, interpolator);
    }

    C0286k(Context context, Interpolator interpolator) {
        this(VERSION.SDK_INT, context, interpolator);
    }

    public static C0286k m1349a(Context context) {
        return C0286k.m1350a(context, null);
    }

    public static C0286k m1350a(Context context, Interpolator interpolator) {
        return new C0286k(context, interpolator);
    }

    public int m1351a() {
        return this.f551b.m1322a(this.f550a);
    }

    public void m1352a(int i, int i2, int i3, int i4, int i5) {
        this.f551b.m1324a(this.f550a, i, i2, i3, i4, i5);
    }

    public void m1353a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.f551b.m1325a(this.f550a, i, i2, i3, i4, i5, i6, i7, i8);
    }

    public int m1354b() {
        return this.f551b.m1326b(this.f550a);
    }

    public int m1355c() {
        return this.f551b.m1329e(this.f550a);
    }

    public int m1356d() {
        return this.f551b.m1330f(this.f550a);
    }

    public boolean m1357e() {
        return this.f551b.m1327c(this.f550a);
    }

    public void m1358f() {
        this.f551b.m1328d(this.f550a);
    }
}
