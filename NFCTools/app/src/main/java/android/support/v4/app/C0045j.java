package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.C0044r;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.app.j */
public abstract class C0045j extends C0044r {
    private final C0039h f203a;
    private C0032k f204b;
    private Fragment f205c;

    public C0045j(C0039h c0039h) {
        this.f204b = null;
        this.f205c = null;
        this.f203a = c0039h;
    }

    private static String m259a(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }

    public Parcelable m260a() {
        return null;
    }

    public abstract Fragment m261a(int i);

    public Object m262a(ViewGroup viewGroup, int i) {
        if (this.f204b == null) {
            this.f204b = this.f203a.m177a();
        }
        long b = m267b(i);
        Fragment a = this.f203a.m176a(C0045j.m259a(viewGroup.getId(), b));
        if (a != null) {
            this.f204b.m124b(a);
        } else {
            a = m261a(i);
            this.f204b.m121a(viewGroup.getId(), a, C0045j.m259a(viewGroup.getId(), b));
        }
        if (a != this.f205c) {
            a.m60b(false);
            a.m65c(false);
        }
        return a;
    }

    public void m263a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public void m264a(ViewGroup viewGroup) {
    }

    public void m265a(ViewGroup viewGroup, int i, Object obj) {
        if (this.f204b == null) {
            this.f204b = this.f203a.m177a();
        }
        this.f204b.m122a((Fragment) obj);
    }

    public boolean m266a(View view, Object obj) {
        return ((Fragment) obj).m78h() == view;
    }

    public long m267b(int i) {
        return (long) i;
    }

    public void m268b(ViewGroup viewGroup) {
        if (this.f204b != null) {
            this.f204b.m123b();
            this.f204b = null;
            this.f203a.m178b();
        }
    }

    public void m269b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.f205c) {
            if (this.f205c != null) {
                this.f205c.m60b(false);
                this.f205c.m65c(false);
            }
            if (fragment != null) {
                fragment.m60b(true);
                fragment.m65c(true);
            }
            this.f205c = fragment;
        }
    }
}
