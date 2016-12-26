package android.support.v7.internal.view;

import android.support.v4.view.af;
import android.support.v4.view.aj;
import android.support.v4.view.ak;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: android.support.v7.internal.view.e */
public class C0345e {
    private final ArrayList<af> f750a;
    private long f751b;
    private Interpolator f752c;
    private aj f753d;
    private boolean f754e;
    private final ak f755f;

    /* renamed from: android.support.v7.internal.view.e.1 */
    class C03441 extends ak {
        final /* synthetic */ C0345e f747a;
        private boolean f748b;
        private int f749c;

        C03441(C0345e c0345e) {
            this.f747a = c0345e;
            this.f748b = false;
            this.f749c = 0;
        }

        void m1697a() {
            this.f749c = 0;
            this.f748b = false;
            this.f747a.m1701c();
        }

        public void onAnimationEnd(View view) {
            int i = this.f749c + 1;
            this.f749c = i;
            if (i == this.f747a.f750a.size()) {
                if (this.f747a.f753d != null) {
                    this.f747a.f753d.onAnimationEnd(null);
                }
                m1697a();
            }
        }

        public void onAnimationStart(View view) {
            if (!this.f748b) {
                this.f748b = true;
                if (this.f747a.f753d != null) {
                    this.f747a.f753d.onAnimationStart(null);
                }
            }
        }
    }

    public C0345e() {
        this.f751b = -1;
        this.f755f = new C03441(this);
        this.f750a = new ArrayList();
    }

    private void m1701c() {
        this.f754e = false;
    }

    public C0345e m1702a(long j) {
        if (!this.f754e) {
            this.f751b = j;
        }
        return this;
    }

    public C0345e m1703a(af afVar) {
        if (!this.f754e) {
            this.f750a.add(afVar);
        }
        return this;
    }

    public C0345e m1704a(aj ajVar) {
        if (!this.f754e) {
            this.f753d = ajVar;
        }
        return this;
    }

    public C0345e m1705a(Interpolator interpolator) {
        if (!this.f754e) {
            this.f752c = interpolator;
        }
        return this;
    }

    public void m1706a() {
        if (!this.f754e) {
            Iterator it = this.f750a.iterator();
            while (it.hasNext()) {
                af afVar = (af) it.next();
                if (this.f751b >= 0) {
                    afVar.m825a(this.f751b);
                }
                if (this.f752c != null) {
                    afVar.m828a(this.f752c);
                }
                if (this.f753d != null) {
                    afVar.m826a(this.f755f);
                }
                afVar.m831b();
            }
            this.f754e = true;
        }
    }

    public void m1707b() {
        if (this.f754e) {
            Iterator it = this.f750a.iterator();
            while (it.hasNext()) {
                ((af) it.next()).m829a();
            }
            this.f754e = false;
        }
    }
}
