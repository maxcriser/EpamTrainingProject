package android.support.v4.widget;

import android.support.v4.view.C0216o;
import android.support.v4.view.C0222s;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.Arrays;

/* renamed from: android.support.v4.widget.m */
public class C0289m {
    private static final Interpolator f552v;
    private int f553a;
    private int f554b;
    private int f555c;
    private float[] f556d;
    private float[] f557e;
    private float[] f558f;
    private float[] f559g;
    private int[] f560h;
    private int[] f561i;
    private int[] f562j;
    private int f563k;
    private VelocityTracker f564l;
    private float f565m;
    private float f566n;
    private int f567o;
    private int f568p;
    private C0286k f569q;
    private final C0248a f570r;
    private View f571s;
    private boolean f572t;
    private final ViewGroup f573u;
    private final Runnable f574w;

    /* renamed from: android.support.v4.widget.m.a */
    public static abstract class C0248a {
        public int m1137a(View view) {
            return 0;
        }

        public int m1138a(View view, int i, int i2) {
            return 0;
        }

        public void m1139a(int i) {
        }

        public void m1140a(int i, int i2) {
        }

        public void m1141a(View view, float f, float f2) {
        }

        public void m1142a(View view, int i, int i2, int i3, int i4) {
        }

        public abstract boolean m1143a(View view, int i);

        public int m1144b(View view) {
            return 0;
        }

        public int m1145b(View view, int i, int i2) {
            return 0;
        }

        public void m1146b(int i, int i2) {
        }

        public void m1147b(View view, int i) {
        }

        public boolean m1148b(int i) {
            return false;
        }

        public int m1149c(int i) {
            return i;
        }
    }

    /* renamed from: android.support.v4.widget.m.1 */
    static class C02881 implements Interpolator {
        C02881() {
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    }

    static {
        f552v = new C02881();
    }

    private float m1368a(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    private float m1369a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        return abs < f2 ? 0.0f : abs > f3 ? f <= 0.0f ? -f3 : f3 : f;
    }

    private int m1370a(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.f573u.getWidth();
        int i4 = width / 2;
        float a = (m1368a(Math.min(1.0f, ((float) Math.abs(i)) / ((float) width))) * ((float) i4)) + ((float) i4);
        i4 = Math.abs(i2);
        return Math.min(i4 > 0 ? Math.round(Math.abs(a / ((float) i4)) * 1000.0f) * 4 : (int) (((((float) Math.abs(i)) / ((float) i3)) + 1.0f) * 256.0f), 600);
    }

    private int m1371a(View view, int i, int i2, int i3, int i4) {
        int b = m1377b(i3, (int) this.f566n, (int) this.f565m);
        int b2 = m1377b(i4, (int) this.f566n, (int) this.f565m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        return (int) (((b2 != 0 ? ((float) abs4) / ((float) i5) : ((float) abs2) / ((float) i6)) * ((float) m1370a(i2, b2, this.f570r.m1144b(view)))) + ((b != 0 ? ((float) abs3) / ((float) i5) : ((float) abs) / ((float) i6)) * ((float) m1370a(i, b, this.f570r.m1137a(view)))));
    }

    private void m1372a(float f, float f2) {
        this.f572t = true;
        this.f570r.m1141a(this.f571s, f, f2);
        this.f572t = false;
        if (this.f553a == 1) {
            m1400c(0);
        }
    }

    private void m1373a(float f, float f2, int i) {
        m1383f(i);
        float[] fArr = this.f556d;
        this.f558f[i] = f;
        fArr[i] = f;
        fArr = this.f557e;
        this.f559g[i] = f2;
        fArr[i] = f2;
        this.f560h[i] = m1381e((int) f, (int) f2);
        this.f563k |= 1 << i;
    }

    private boolean m1374a(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.f560h[i] & i2) != i2 || (this.f568p & i2) == 0 || (this.f562j[i] & i2) == i2 || (this.f561i[i] & i2) == i2) {
            return false;
        }
        if (abs <= ((float) this.f554b) && abs2 <= ((float) this.f554b)) {
            return false;
        }
        if (abs >= abs2 * 0.5f || !this.f570r.m1148b(i2)) {
            return (this.f561i[i] & i2) == 0 && abs > ((float) this.f554b);
        } else {
            int[] iArr = this.f562j;
            iArr[i] = iArr[i] | i2;
            return false;
        }
    }

    private boolean m1375a(int i, int i2, int i3, int i4) {
        int left = this.f571s.getLeft();
        int top = this.f571s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.f569q.m1358f();
            m1400c(0);
            return false;
        }
        this.f569q.m1352a(left, top, i5, i6, m1371a(this.f571s, i5, i6, i3, i4));
        m1400c(2);
        return true;
    }

    private boolean m1376a(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.f570r.m1137a(view) > 0;
        boolean z2 = this.f570r.m1144b(view) > 0;
        return (z && z2) ? (f * f) + (f2 * f2) > ((float) (this.f554b * this.f554b)) : z ? Math.abs(f) > ((float) this.f554b) : z2 ? Math.abs(f2) > ((float) this.f554b) : false;
    }

    private int m1377b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        return abs < i2 ? 0 : abs > i3 ? i <= 0 ? -i3 : i3 : i;
    }

    private void m1378b(float f, float f2, int i) {
        int i2 = 1;
        if (!m1374a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (m1374a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (m1374a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (m1374a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.f561i;
            iArr[i] = iArr[i] | i2;
            this.f570r.m1146b(i2, i);
        }
    }

    private void m1379b(int i, int i2, int i3, int i4) {
        int a;
        int b;
        int left = this.f571s.getLeft();
        int top = this.f571s.getTop();
        if (i3 != 0) {
            a = this.f570r.m1138a(this.f571s, i, i3);
            this.f571s.offsetLeftAndRight(a - left);
        } else {
            a = i;
        }
        if (i4 != 0) {
            b = this.f570r.m1145b(this.f571s, i2, i4);
            this.f571s.offsetTopAndBottom(b - top);
        } else {
            b = i2;
        }
        if (i3 != 0 || i4 != 0) {
            this.f570r.m1142a(this.f571s, a, b, a - left, b - top);
        }
    }

    private void m1380c(MotionEvent motionEvent) {
        int c = C0216o.m959c(motionEvent);
        for (int i = 0; i < c; i++) {
            int b = C0216o.m957b(motionEvent, i);
            float c2 = C0216o.m958c(motionEvent, i);
            float d = C0216o.m960d(motionEvent, i);
            this.f558f[b] = c2;
            this.f559g[b] = d;
        }
    }

    private int m1381e(int i, int i2) {
        int i3 = 0;
        if (i < this.f573u.getLeft() + this.f567o) {
            i3 = 1;
        }
        if (i2 < this.f573u.getTop() + this.f567o) {
            i3 |= 4;
        }
        if (i > this.f573u.getRight() - this.f567o) {
            i3 |= 2;
        }
        return i2 > this.f573u.getBottom() - this.f567o ? i3 | 8 : i3;
    }

    private void m1382e(int i) {
        if (this.f556d != null) {
            this.f556d[i] = 0.0f;
            this.f557e[i] = 0.0f;
            this.f558f[i] = 0.0f;
            this.f559g[i] = 0.0f;
            this.f560h[i] = 0;
            this.f561i[i] = 0;
            this.f562j[i] = 0;
            this.f563k &= (1 << i) ^ -1;
        }
    }

    private void m1383f(int i) {
        if (this.f556d == null || this.f556d.length <= i) {
            Object obj = new float[(i + 1)];
            Object obj2 = new float[(i + 1)];
            Object obj3 = new float[(i + 1)];
            Object obj4 = new float[(i + 1)];
            Object obj5 = new int[(i + 1)];
            Object obj6 = new int[(i + 1)];
            Object obj7 = new int[(i + 1)];
            if (this.f556d != null) {
                System.arraycopy(this.f556d, 0, obj, 0, this.f556d.length);
                System.arraycopy(this.f557e, 0, obj2, 0, this.f557e.length);
                System.arraycopy(this.f558f, 0, obj3, 0, this.f558f.length);
                System.arraycopy(this.f559g, 0, obj4, 0, this.f559g.length);
                System.arraycopy(this.f560h, 0, obj5, 0, this.f560h.length);
                System.arraycopy(this.f561i, 0, obj6, 0, this.f561i.length);
                System.arraycopy(this.f562j, 0, obj7, 0, this.f562j.length);
            }
            this.f556d = obj;
            this.f557e = obj2;
            this.f558f = obj3;
            this.f559g = obj4;
            this.f560h = obj5;
            this.f561i = obj6;
            this.f562j = obj7;
        }
    }

    private void m1384g() {
        if (this.f556d != null) {
            Arrays.fill(this.f556d, 0.0f);
            Arrays.fill(this.f557e, 0.0f);
            Arrays.fill(this.f558f, 0.0f);
            Arrays.fill(this.f559g, 0.0f);
            Arrays.fill(this.f560h, 0);
            Arrays.fill(this.f561i, 0);
            Arrays.fill(this.f562j, 0);
            this.f563k = 0;
        }
    }

    private void m1385h() {
        this.f564l.computeCurrentVelocity(1000, this.f565m);
        m1372a(m1369a(C0222s.m973a(this.f564l, this.f555c), this.f566n, this.f565m), m1369a(C0222s.m974b(this.f564l, this.f555c), this.f566n, this.f565m));
    }

    public int m1386a() {
        return this.f553a;
    }

    public void m1387a(int i) {
        this.f568p = i;
    }

    public void m1388a(View view, int i) {
        if (view.getParent() != this.f573u) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f573u + ")");
        }
        this.f571s = view;
        this.f555c = i;
        this.f570r.m1147b(view, i);
        m1400c(1);
    }

    public boolean m1389a(int i, int i2) {
        if (this.f572t) {
            return m1375a(i, i2, (int) C0222s.m973a(this.f564l, this.f555c), (int) C0222s.m974b(this.f564l, this.f555c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m1390a(MotionEvent r14) {
        /*
        r13 = this;
        r0 = android.support.v4.view.C0216o.m954a(r14);
        r1 = android.support.v4.view.C0216o.m956b(r14);
        if (r0 != 0) goto L_0x000d;
    L_0x000a:
        r13.m1405e();
    L_0x000d:
        r2 = r13.f564l;
        if (r2 != 0) goto L_0x0017;
    L_0x0011:
        r2 = android.view.VelocityTracker.obtain();
        r13.f564l = r2;
    L_0x0017:
        r2 = r13.f564l;
        r2.addMovement(r14);
        switch(r0) {
            case 0: goto L_0x0026;
            case 1: goto L_0x0119;
            case 2: goto L_0x0092;
            case 3: goto L_0x0119;
            case 4: goto L_0x001f;
            case 5: goto L_0x005a;
            case 6: goto L_0x0110;
            default: goto L_0x001f;
        };
    L_0x001f:
        r0 = r13.f553a;
        r1 = 1;
        if (r0 != r1) goto L_0x011e;
    L_0x0024:
        r0 = 1;
    L_0x0025:
        return r0;
    L_0x0026:
        r0 = r14.getX();
        r1 = r14.getY();
        r2 = 0;
        r2 = android.support.v4.view.C0216o.m957b(r14, r2);
        r13.m1373a(r0, r1, r2);
        r0 = (int) r0;
        r1 = (int) r1;
        r0 = r13.m1403d(r0, r1);
        r1 = r13.f571s;
        if (r0 != r1) goto L_0x0048;
    L_0x0040:
        r1 = r13.f553a;
        r3 = 2;
        if (r1 != r3) goto L_0x0048;
    L_0x0045:
        r13.m1397b(r0, r2);
    L_0x0048:
        r0 = r13.f560h;
        r0 = r0[r2];
        r1 = r13.f568p;
        r1 = r1 & r0;
        if (r1 == 0) goto L_0x001f;
    L_0x0051:
        r1 = r13.f570r;
        r3 = r13.f568p;
        r0 = r0 & r3;
        r1.m1140a(r0, r2);
        goto L_0x001f;
    L_0x005a:
        r0 = android.support.v4.view.C0216o.m957b(r14, r1);
        r2 = android.support.v4.view.C0216o.m958c(r14, r1);
        r1 = android.support.v4.view.C0216o.m960d(r14, r1);
        r13.m1373a(r2, r1, r0);
        r3 = r13.f553a;
        if (r3 != 0) goto L_0x007f;
    L_0x006d:
        r1 = r13.f560h;
        r1 = r1[r0];
        r2 = r13.f568p;
        r2 = r2 & r1;
        if (r2 == 0) goto L_0x001f;
    L_0x0076:
        r2 = r13.f570r;
        r3 = r13.f568p;
        r1 = r1 & r3;
        r2.m1140a(r1, r0);
        goto L_0x001f;
    L_0x007f:
        r3 = r13.f553a;
        r4 = 2;
        if (r3 != r4) goto L_0x001f;
    L_0x0084:
        r2 = (int) r2;
        r1 = (int) r1;
        r1 = r13.m1403d(r2, r1);
        r2 = r13.f571s;
        if (r1 != r2) goto L_0x001f;
    L_0x008e:
        r13.m1397b(r1, r0);
        goto L_0x001f;
    L_0x0092:
        r2 = android.support.v4.view.C0216o.m959c(r14);
        r0 = 0;
        r1 = r0;
    L_0x0098:
        if (r1 >= r2) goto L_0x00f5;
    L_0x009a:
        r3 = android.support.v4.view.C0216o.m957b(r14, r1);
        r0 = android.support.v4.view.C0216o.m958c(r14, r1);
        r4 = android.support.v4.view.C0216o.m960d(r14, r1);
        r5 = r13.f556d;
        r5 = r5[r3];
        r5 = r0 - r5;
        r6 = r13.f557e;
        r6 = r6[r3];
        r6 = r4 - r6;
        r0 = (int) r0;
        r4 = (int) r4;
        r4 = r13.m1403d(r0, r4);
        if (r4 == 0) goto L_0x00fa;
    L_0x00ba:
        r0 = r13.m1376a(r4, r5, r6);
        if (r0 == 0) goto L_0x00fa;
    L_0x00c0:
        r0 = 1;
    L_0x00c1:
        if (r0 == 0) goto L_0x00fc;
    L_0x00c3:
        r7 = r4.getLeft();
        r8 = (int) r5;
        r8 = r8 + r7;
        r9 = r13.f570r;
        r10 = (int) r5;
        r8 = r9.m1138a(r4, r8, r10);
        r9 = r4.getTop();
        r10 = (int) r6;
        r10 = r10 + r9;
        r11 = r13.f570r;
        r12 = (int) r6;
        r10 = r11.m1145b(r4, r10, r12);
        r11 = r13.f570r;
        r11 = r11.m1137a(r4);
        r12 = r13.f570r;
        r12 = r12.m1144b(r4);
        if (r11 == 0) goto L_0x00ef;
    L_0x00eb:
        if (r11 <= 0) goto L_0x00fc;
    L_0x00ed:
        if (r8 != r7) goto L_0x00fc;
    L_0x00ef:
        if (r12 == 0) goto L_0x00f5;
    L_0x00f1:
        if (r12 <= 0) goto L_0x00fc;
    L_0x00f3:
        if (r10 != r9) goto L_0x00fc;
    L_0x00f5:
        r13.m1380c(r14);
        goto L_0x001f;
    L_0x00fa:
        r0 = 0;
        goto L_0x00c1;
    L_0x00fc:
        r13.m1378b(r5, r6, r3);
        r5 = r13.f553a;
        r6 = 1;
        if (r5 == r6) goto L_0x00f5;
    L_0x0104:
        if (r0 == 0) goto L_0x010c;
    L_0x0106:
        r0 = r13.m1397b(r4, r3);
        if (r0 != 0) goto L_0x00f5;
    L_0x010c:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0098;
    L_0x0110:
        r0 = android.support.v4.view.C0216o.m957b(r14, r1);
        r13.m1382e(r0);
        goto L_0x001f;
    L_0x0119:
        r13.m1405e();
        goto L_0x001f;
    L_0x011e:
        r0 = 0;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.m.a(android.view.MotionEvent):boolean");
    }

    public boolean m1391a(View view, int i, int i2) {
        this.f571s = view;
        this.f555c = -1;
        boolean a = m1375a(i, i2, 0, 0);
        if (!(a || this.f553a != 0 || this.f571s == null)) {
            this.f571s = null;
        }
        return a;
    }

    public boolean m1392a(boolean z) {
        if (this.f553a == 2) {
            int i;
            boolean e = this.f569q.m1357e();
            int a = this.f569q.m1351a();
            int b = this.f569q.m1354b();
            int left = a - this.f571s.getLeft();
            int top = b - this.f571s.getTop();
            if (left != 0) {
                this.f571s.offsetLeftAndRight(left);
            }
            if (top != 0) {
                this.f571s.offsetTopAndBottom(top);
            }
            if (!(left == 0 && top == 0)) {
                this.f570r.m1142a(this.f571s, a, b, left, top);
            }
            if (e && a == this.f569q.m1355c() && b == this.f569q.m1356d()) {
                this.f569q.m1358f();
                i = 0;
            } else {
                boolean z2 = e;
            }
            if (i == 0) {
                if (z) {
                    this.f573u.post(this.f574w);
                } else {
                    m1400c(0);
                }
            }
        }
        return this.f553a == 2;
    }

    public int m1393b() {
        return this.f567o;
    }

    public void m1394b(MotionEvent motionEvent) {
        int i = 0;
        int a = C0216o.m954a(motionEvent);
        int b = C0216o.m956b(motionEvent);
        if (a == 0) {
            m1405e();
        }
        if (this.f564l == null) {
            this.f564l = VelocityTracker.obtain();
        }
        this.f564l.addMovement(motionEvent);
        float x;
        float y;
        View d;
        int i2;
        switch (a) {
            case C0627j.View_android_focusable /*0*/:
                x = motionEvent.getX();
                y = motionEvent.getY();
                i = C0216o.m957b(motionEvent, 0);
                d = m1403d((int) x, (int) y);
                m1373a(x, y, i);
                m1397b(d, i);
                i2 = this.f560h[i];
                if ((this.f568p & i2) != 0) {
                    this.f570r.m1140a(i2 & this.f568p, i);
                }
            case C0627j.View_paddingStart /*1*/:
                if (this.f553a == 1) {
                    m1385h();
                }
                m1405e();
            case C0627j.View_paddingEnd /*2*/:
                if (this.f553a == 1) {
                    i = C0216o.m955a(motionEvent, this.f555c);
                    x = C0216o.m958c(motionEvent, i);
                    i2 = (int) (x - this.f558f[this.f555c]);
                    i = (int) (C0216o.m960d(motionEvent, i) - this.f559g[this.f555c]);
                    m1379b(this.f571s.getLeft() + i2, this.f571s.getTop() + i, i2, i);
                    m1380c(motionEvent);
                    return;
                }
                i2 = C0216o.m959c(motionEvent);
                while (i < i2) {
                    a = C0216o.m957b(motionEvent, i);
                    float c = C0216o.m958c(motionEvent, i);
                    float d2 = C0216o.m960d(motionEvent, i);
                    float f = c - this.f556d[a];
                    float f2 = d2 - this.f557e[a];
                    m1378b(f, f2, a);
                    if (this.f553a != 1) {
                        d = m1403d((int) c, (int) d2);
                        if (!m1376a(d, f, f2) || !m1397b(d, a)) {
                            i++;
                        }
                    }
                    m1380c(motionEvent);
                }
                m1380c(motionEvent);
            case C0627j.Toolbar_subtitle /*3*/:
                if (this.f553a == 1) {
                    m1372a(0.0f, 0.0f);
                }
                m1405e();
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                i = C0216o.m957b(motionEvent, b);
                x = C0216o.m958c(motionEvent, b);
                y = C0216o.m960d(motionEvent, b);
                m1373a(x, y, i);
                if (this.f553a == 0) {
                    m1397b(m1403d((int) x, (int) y), i);
                    i2 = this.f560h[i];
                    if ((this.f568p & i2) != 0) {
                        this.f570r.m1140a(i2 & this.f568p, i);
                    }
                } else if (m1401c((int) x, (int) y)) {
                    m1397b(this.f571s, i);
                }
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                a = C0216o.m957b(motionEvent, b);
                if (this.f553a == 1 && a == this.f555c) {
                    b = C0216o.m959c(motionEvent);
                    while (i < b) {
                        int b2 = C0216o.m957b(motionEvent, i);
                        if (b2 != this.f555c) {
                            if (m1403d((int) C0216o.m958c(motionEvent, i), (int) C0216o.m960d(motionEvent, i)) == this.f571s && m1397b(this.f571s, b2)) {
                                i = this.f555c;
                                if (i == -1) {
                                    m1385h();
                                }
                            }
                        }
                        i++;
                    }
                    i = -1;
                    if (i == -1) {
                        m1385h();
                    }
                }
                m1382e(a);
            default:
        }
    }

    public boolean m1395b(int i) {
        return (this.f563k & (1 << i)) != 0;
    }

    public boolean m1396b(int i, int i2) {
        if (!m1395b(i2)) {
            return false;
        }
        boolean z = (i & 1) == 1;
        boolean z2 = (i & 2) == 2;
        float f = this.f558f[i2] - this.f556d[i2];
        float f2 = this.f559g[i2] - this.f557e[i2];
        return (z && z2) ? (f * f) + (f2 * f2) > ((float) (this.f554b * this.f554b)) : z ? Math.abs(f) > ((float) this.f554b) : z2 ? Math.abs(f2) > ((float) this.f554b) : false;
    }

    boolean m1397b(View view, int i) {
        if (view == this.f571s && this.f555c == i) {
            return true;
        }
        if (view == null || !this.f570r.m1143a(view, i)) {
            return false;
        }
        this.f555c = i;
        m1388a(view, i);
        return true;
    }

    public boolean m1398b(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }

    public View m1399c() {
        return this.f571s;
    }

    void m1400c(int i) {
        if (this.f553a != i) {
            this.f553a = i;
            this.f570r.m1139a(i);
            if (this.f553a == 0) {
                this.f571s = null;
            }
        }
    }

    public boolean m1401c(int i, int i2) {
        return m1398b(this.f571s, i, i2);
    }

    public int m1402d() {
        return this.f554b;
    }

    public View m1403d(int i, int i2) {
        for (int childCount = this.f573u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f573u.getChildAt(this.f570r.m1149c(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public boolean m1404d(int i) {
        int length = this.f556d.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (m1396b(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public void m1405e() {
        this.f555c = -1;
        m1384g();
        if (this.f564l != null) {
            this.f564l.recycle();
            this.f564l = null;
        }
    }

    public void m1406f() {
        m1405e();
        if (this.f553a == 2) {
            int a = this.f569q.m1351a();
            int b = this.f569q.m1354b();
            this.f569q.m1358f();
            int a2 = this.f569q.m1351a();
            int b2 = this.f569q.m1354b();
            this.f570r.m1142a(this.f571s, a2, b2, a2 - a, b2 - b);
        }
        m1400c(0);
    }
}
