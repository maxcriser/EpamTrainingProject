package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class af {
    static final C0171f f408a;
    private WeakReference<View> f409b;
    private Runnable f410c;
    private Runnable f411d;
    private int f412e;

    /* renamed from: android.support.v4.view.af.f */
    interface C0171f {
        void m785a(af afVar, View view);

        void m786a(af afVar, View view, float f);

        void m787a(af afVar, View view, long j);

        void m788a(af afVar, View view, aj ajVar);

        void m789a(af afVar, View view, al alVar);

        void m790a(af afVar, View view, Interpolator interpolator);

        void m791b(af afVar, View view);

        void m792b(af afVar, View view, float f);

        void m793c(af afVar, View view, float f);

        void m794d(af afVar, View view, float f);
    }

    /* renamed from: android.support.v4.view.af.a */
    static class C0172a implements C0171f {
        WeakHashMap<View, Runnable> f405a;

        /* renamed from: android.support.v4.view.af.a.a */
        class C0170a implements Runnable {
            WeakReference<View> f402a;
            af f403b;
            final /* synthetic */ C0172a f404c;

            private C0170a(C0172a c0172a, af afVar, View view) {
                this.f404c = c0172a;
                this.f402a = new WeakReference(view);
                this.f403b = afVar;
            }

            public void run() {
                this.f404c.m797c(this.f403b, (View) this.f402a.get());
            }
        }

        C0172a() {
            this.f405a = null;
        }

        private void m796a(View view) {
            if (this.f405a != null) {
                Runnable runnable = (Runnable) this.f405a.get(view);
                if (runnable != null) {
                    view.removeCallbacks(runnable);
                }
            }
        }

        private void m797c(af afVar, View view) {
            Object tag = view.getTag(2113929216);
            aj ajVar = tag instanceof aj ? (aj) tag : null;
            Runnable a = afVar.f410c;
            Runnable b = afVar.f411d;
            if (a != null) {
                a.run();
            }
            if (ajVar != null) {
                ajVar.onAnimationStart(view);
                ajVar.onAnimationEnd(view);
            }
            if (b != null) {
                b.run();
            }
            if (this.f405a != null) {
                this.f405a.remove(view);
            }
        }

        private void m798d(af afVar, View view) {
            Runnable runnable = this.f405a != null ? (Runnable) this.f405a.get(view) : null;
            if (runnable == null) {
                runnable = new C0170a(afVar, view, null);
                if (this.f405a == null) {
                    this.f405a = new WeakHashMap();
                }
                this.f405a.put(view, runnable);
            }
            view.removeCallbacks(runnable);
            view.post(runnable);
        }

        public void m799a(af afVar, View view) {
            m798d(afVar, view);
        }

        public void m800a(af afVar, View view, float f) {
            m798d(afVar, view);
        }

        public void m801a(af afVar, View view, long j) {
        }

        public void m802a(af afVar, View view, aj ajVar) {
            view.setTag(2113929216, ajVar);
        }

        public void m803a(af afVar, View view, al alVar) {
        }

        public void m804a(af afVar, View view, Interpolator interpolator) {
        }

        public void m805b(af afVar, View view) {
            m796a(view);
            m797c(afVar, view);
        }

        public void m806b(af afVar, View view, float f) {
            m798d(afVar, view);
        }

        public void m807c(af afVar, View view, float f) {
            m798d(afVar, view);
        }

        public void m808d(af afVar, View view, float f) {
            m798d(afVar, view);
        }
    }

    /* renamed from: android.support.v4.view.af.b */
    static class C0174b extends C0172a {
        WeakHashMap<View, Integer> f407b;

        /* renamed from: android.support.v4.view.af.b.a */
        static class C0173a implements aj {
            af f406a;

            C0173a(af afVar) {
                this.f406a = afVar;
            }

            public void onAnimationCancel(View view) {
                Object tag = view.getTag(2113929216);
                aj ajVar = tag instanceof aj ? (aj) tag : null;
                if (ajVar != null) {
                    ajVar.onAnimationCancel(view);
                }
            }

            public void onAnimationEnd(View view) {
                if (this.f406a.f412e >= 0) {
                    C0234u.m1076a(view, this.f406a.f412e, null);
                    this.f406a.f412e = -1;
                }
                if (this.f406a.f411d != null) {
                    this.f406a.f411d.run();
                }
                Object tag = view.getTag(2113929216);
                aj ajVar = tag instanceof aj ? (aj) tag : null;
                if (ajVar != null) {
                    ajVar.onAnimationEnd(view);
                }
            }

            public void onAnimationStart(View view) {
                if (this.f406a.f412e >= 0) {
                    C0234u.m1076a(view, 2, null);
                }
                if (this.f406a.f410c != null) {
                    this.f406a.f410c.run();
                }
                Object tag = view.getTag(2113929216);
                aj ajVar = tag instanceof aj ? (aj) tag : null;
                if (ajVar != null) {
                    ajVar.onAnimationStart(view);
                }
            }
        }

        C0174b() {
            this.f407b = null;
        }

        public void m809a(af afVar, View view) {
            ag.m834a(view);
        }

        public void m810a(af afVar, View view, float f) {
            ag.m835a(view, f);
        }

        public void m811a(af afVar, View view, long j) {
            ag.m836a(view, j);
        }

        public void m812a(af afVar, View view, aj ajVar) {
            view.setTag(2113929216, ajVar);
            ag.m837a(view, new C0173a(afVar));
        }

        public void m813a(af afVar, View view, Interpolator interpolator) {
            ag.m838a(view, interpolator);
        }

        public void m814b(af afVar, View view) {
            ag.m839b(view);
        }

        public void m815b(af afVar, View view, float f) {
            ag.m840b(view, f);
        }

        public void m816c(af afVar, View view, float f) {
            ag.m841c(view, f);
        }

        public void m817d(af afVar, View view, float f) {
            ag.m842d(view, f);
        }
    }

    /* renamed from: android.support.v4.view.af.d */
    static class C0175d extends C0174b {
        C0175d() {
        }

        public void m818a(af afVar, View view, aj ajVar) {
            ah.m843a(view, ajVar);
        }
    }

    /* renamed from: android.support.v4.view.af.c */
    static class C0176c extends C0175d {
        C0176c() {
        }
    }

    /* renamed from: android.support.v4.view.af.e */
    static class C0177e extends C0176c {
        C0177e() {
        }

        public void m819a(af afVar, View view, al alVar) {
            ai.m844a(view, alVar);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 19) {
            f408a = new C0177e();
        } else if (i >= 18) {
            f408a = new C0176c();
        } else if (i >= 16) {
            f408a = new C0175d();
        } else if (i >= 14) {
            f408a = new C0174b();
        } else {
            f408a = new C0172a();
        }
    }

    af(View view) {
        this.f410c = null;
        this.f411d = null;
        this.f412e = -1;
        this.f409b = new WeakReference(view);
    }

    public af m824a(float f) {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m786a(this, view, f);
        }
        return this;
    }

    public af m825a(long j) {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m787a(this, view, j);
        }
        return this;
    }

    public af m826a(aj ajVar) {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m788a(this, view, ajVar);
        }
        return this;
    }

    public af m827a(al alVar) {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m789a(this, view, alVar);
        }
        return this;
    }

    public af m828a(Interpolator interpolator) {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m790a(this, view, interpolator);
        }
        return this;
    }

    public void m829a() {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m785a(this, view);
        }
    }

    public af m830b(float f) {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m792b(this, view, f);
        }
        return this;
    }

    public void m831b() {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m791b(this, view);
        }
    }

    public af m832c(float f) {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m793c(this, view, f);
        }
        return this;
    }

    public af m833d(float f) {
        View view = (View) this.f409b.get();
        if (view != null) {
            f408a.m794d(this, view, f);
        }
        return this;
    }
}
