package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.view.C0216o;
import android.support.v4.view.C0234u;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.wakdev.nfctools.C0628m.C0627j;

/* renamed from: android.support.v4.widget.a */
public abstract class C0262a implements OnTouchListener {
    private static final int f513r;
    private final C0260a f514a;
    private final Interpolator f515b;
    private final View f516c;
    private Runnable f517d;
    private float[] f518e;
    private float[] f519f;
    private int f520g;
    private int f521h;
    private float[] f522i;
    private float[] f523j;
    private float[] f524k;
    private boolean f525l;
    private boolean f526m;
    private boolean f527n;
    private boolean f528o;
    private boolean f529p;
    private boolean f530q;

    /* renamed from: android.support.v4.widget.a.a */
    private static class C0260a {
        private int f501a;
        private int f502b;
        private float f503c;
        private float f504d;
        private long f505e;
        private long f506f;
        private int f507g;
        private int f508h;
        private long f509i;
        private float f510j;
        private int f511k;

        public C0260a() {
            this.f505e = Long.MIN_VALUE;
            this.f509i = -1;
            this.f506f = 0;
            this.f507g = 0;
            this.f508h = 0;
        }

        private float m1214a(float f) {
            return ((-4.0f * f) * f) + (4.0f * f);
        }

        private float m1215a(long j) {
            if (j < this.f505e) {
                return 0.0f;
            }
            if (this.f509i < 0 || j < this.f509i) {
                return C0262a.m1234b(((float) (j - this.f505e)) / ((float) this.f501a), 0.0f, 1.0f) * 0.5f;
            }
            long j2 = j - this.f509i;
            return (C0262a.m1234b(((float) j2) / ((float) this.f511k), 0.0f, 1.0f) * this.f510j) + (1.0f - this.f510j);
        }

        public void m1216a() {
            this.f505e = AnimationUtils.currentAnimationTimeMillis();
            this.f509i = -1;
            this.f506f = this.f505e;
            this.f510j = 0.5f;
            this.f507g = 0;
            this.f508h = 0;
        }

        public void m1217a(float f, float f2) {
            this.f503c = f;
            this.f504d = f2;
        }

        public void m1218a(int i) {
            this.f501a = i;
        }

        public void m1219b() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f511k = C0262a.m1235b((int) (currentAnimationTimeMillis - this.f505e), 0, this.f502b);
            this.f510j = m1215a(currentAnimationTimeMillis);
            this.f509i = currentAnimationTimeMillis;
        }

        public void m1220b(int i) {
            this.f502b = i;
        }

        public boolean m1221c() {
            return this.f509i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f509i + ((long) this.f511k);
        }

        public void m1222d() {
            if (this.f506f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float a = m1214a(m1215a(currentAnimationTimeMillis));
            long j = currentAnimationTimeMillis - this.f506f;
            this.f506f = currentAnimationTimeMillis;
            this.f507g = (int) ((((float) j) * a) * this.f503c);
            this.f508h = (int) ((((float) j) * a) * this.f504d);
        }

        public int m1223e() {
            return (int) (this.f503c / Math.abs(this.f503c));
        }

        public int m1224f() {
            return (int) (this.f504d / Math.abs(this.f504d));
        }

        public int m1225g() {
            return this.f507g;
        }

        public int m1226h() {
            return this.f508h;
        }
    }

    /* renamed from: android.support.v4.widget.a.b */
    private class C0261b implements Runnable {
        final /* synthetic */ C0262a f512a;

        private C0261b(C0262a c0262a) {
            this.f512a = c0262a;
        }

        public void run() {
            if (this.f512a.f528o) {
                if (this.f512a.f526m) {
                    this.f512a.f526m = false;
                    this.f512a.f514a.m1216a();
                }
                C0260a c = this.f512a.f514a;
                if (c.m1221c() || !this.f512a.m1231a()) {
                    this.f512a.f528o = false;
                    return;
                }
                if (this.f512a.f527n) {
                    this.f512a.f527n = false;
                    this.f512a.m1242d();
                }
                c.m1222d();
                this.f512a.m1251a(c.m1225g(), c.m1226h());
                C0234u.m1080a(this.f512a.f516c, (Runnable) this);
            }
        }
    }

    static {
        f513r = ViewConfiguration.getTapTimeout();
    }

    public C0262a(View view) {
        this.f514a = new C0260a();
        this.f515b = new AccelerateInterpolator();
        this.f518e = new float[]{0.0f, 0.0f};
        this.f519f = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
        this.f522i = new float[]{0.0f, 0.0f};
        this.f523j = new float[]{0.0f, 0.0f};
        this.f524k = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
        this.f516c = view;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int i = (int) ((1575.0f * displayMetrics.density) + 0.5f);
        int i2 = (int) ((displayMetrics.density * 315.0f) + 0.5f);
        m1248a((float) i, (float) i);
        m1252b((float) i2, (float) i2);
        m1249a(1);
        m1258e(Float.MAX_VALUE, Float.MAX_VALUE);
        m1256d(0.2f, 0.2f);
        m1254c(1.0f, 1.0f);
        m1253b(f513r);
        m1255c(500);
        m1257d(500);
    }

    private float m1228a(float f, float f2, float f3, float f4) {
        float f5;
        float b = C0262a.m1234b(f * f2, 0.0f, f3);
        b = m1245f(f2 - f4, b) - m1245f(f4, b);
        if (b < 0.0f) {
            f5 = -this.f515b.getInterpolation(-b);
        } else if (b <= 0.0f) {
            return 0.0f;
        } else {
            f5 = this.f515b.getInterpolation(b);
        }
        return C0262a.m1234b(f5, -1.0f, 1.0f);
    }

    private float m1229a(int i, float f, float f2, float f3) {
        float a = m1228a(this.f518e[i], f2, this.f519f[i], f);
        if (a == 0.0f) {
            return 0.0f;
        }
        float f4 = this.f522i[i];
        float f5 = this.f523j[i];
        float f6 = this.f524k[i];
        f4 *= f3;
        return a > 0.0f ? C0262a.m1234b(a * f4, f5, f6) : -C0262a.m1234b((-a) * f4, f5, f6);
    }

    private boolean m1231a() {
        C0260a c0260a = this.f514a;
        int f = c0260a.m1224f();
        int e = c0260a.m1223e();
        return (f != 0 && m1260f(f)) || (e != 0 && m1259e(e));
    }

    private static float m1234b(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    private static int m1235b(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    private void m1236b() {
        if (this.f517d == null) {
            this.f517d = new C0261b();
        }
        this.f528o = true;
        this.f526m = true;
        if (this.f525l || this.f521h <= 0) {
            this.f517d.run();
        } else {
            C0234u.m1081a(this.f516c, this.f517d, (long) this.f521h);
        }
        this.f525l = true;
    }

    private void m1240c() {
        if (this.f526m) {
            this.f528o = false;
        } else {
            this.f514a.m1219b();
        }
    }

    private void m1242d() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f516c.onTouchEvent(obtain);
        obtain.recycle();
    }

    private float m1245f(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        switch (this.f520g) {
            case C0627j.View_android_focusable /*0*/:
            case C0627j.View_paddingStart /*1*/:
                return f < f2 ? f >= 0.0f ? 1.0f - (f / f2) : (this.f528o && this.f520g == 1) ? 1.0f : 0.0f : 0.0f;
            case C0627j.View_paddingEnd /*2*/:
                return f < 0.0f ? f / (-f2) : 0.0f;
            default:
                return 0.0f;
        }
    }

    public C0262a m1248a(float f, float f2) {
        this.f524k[0] = f / 1000.0f;
        this.f524k[1] = f2 / 1000.0f;
        return this;
    }

    public C0262a m1249a(int i) {
        this.f520g = i;
        return this;
    }

    public C0262a m1250a(boolean z) {
        if (this.f529p && !z) {
            m1240c();
        }
        this.f529p = z;
        return this;
    }

    public abstract void m1251a(int i, int i2);

    public C0262a m1252b(float f, float f2) {
        this.f523j[0] = f / 1000.0f;
        this.f523j[1] = f2 / 1000.0f;
        return this;
    }

    public C0262a m1253b(int i) {
        this.f521h = i;
        return this;
    }

    public C0262a m1254c(float f, float f2) {
        this.f522i[0] = f / 1000.0f;
        this.f522i[1] = f2 / 1000.0f;
        return this;
    }

    public C0262a m1255c(int i) {
        this.f514a.m1218a(i);
        return this;
    }

    public C0262a m1256d(float f, float f2) {
        this.f518e[0] = f;
        this.f518e[1] = f2;
        return this;
    }

    public C0262a m1257d(int i) {
        this.f514a.m1220b(i);
        return this;
    }

    public C0262a m1258e(float f, float f2) {
        this.f519f[0] = f;
        this.f519f[1] = f2;
        return this;
    }

    public abstract boolean m1259e(int i);

    public abstract boolean m1260f(int i);

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = true;
        if (!this.f529p) {
            return false;
        }
        switch (C0216o.m954a(motionEvent)) {
            case C0627j.View_android_focusable /*0*/:
                this.f527n = true;
                this.f525l = false;
                break;
            case C0627j.View_paddingStart /*1*/:
            case C0627j.Toolbar_subtitle /*3*/:
                m1240c();
                break;
            case C0627j.View_paddingEnd /*2*/:
                break;
        }
        this.f514a.m1217a(m1229a(0, motionEvent.getX(), (float) view.getWidth(), (float) this.f516c.getWidth()), m1229a(1, motionEvent.getY(), (float) view.getHeight(), (float) this.f516c.getHeight()));
        if (!this.f528o && m1231a()) {
            m1236b();
        }
        if (!(this.f530q && this.f528o)) {
            z = false;
        }
        return z;
    }
}
