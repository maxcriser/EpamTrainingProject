package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.VelocityTracker;

/* renamed from: android.support.v4.view.s */
public class C0222s {
    static final C0219c f427a;

    /* renamed from: android.support.v4.view.s.c */
    interface C0219c {
        float m967a(VelocityTracker velocityTracker, int i);

        float m968b(VelocityTracker velocityTracker, int i);
    }

    /* renamed from: android.support.v4.view.s.a */
    static class C0220a implements C0219c {
        C0220a() {
        }

        public float m969a(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getXVelocity();
        }

        public float m970b(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getYVelocity();
        }
    }

    /* renamed from: android.support.v4.view.s.b */
    static class C0221b implements C0219c {
        C0221b() {
        }

        public float m971a(VelocityTracker velocityTracker, int i) {
            return C0223t.m975a(velocityTracker, i);
        }

        public float m972b(VelocityTracker velocityTracker, int i) {
            return C0223t.m976b(velocityTracker, i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f427a = new C0221b();
        } else {
            f427a = new C0220a();
        }
    }

    public static float m973a(VelocityTracker velocityTracker, int i) {
        return f427a.m967a(velocityTracker, i);
    }

    public static float m974b(VelocityTracker velocityTracker, int i) {
        return f427a.m968b(velocityTracker, i);
    }
}
