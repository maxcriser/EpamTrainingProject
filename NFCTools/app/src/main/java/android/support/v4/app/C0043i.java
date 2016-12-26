package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.C0039h.C0038a;
import android.support.v4.p007f.C0099c;
import android.support.v4.p007f.C0100d;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import com.wakdev.nfctools.C0628m.C0627j;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: android.support.v4.app.i */
final class C0043i extends C0039h implements Factory {
    static final Interpolator f173A;
    static final Interpolator f174B;
    static final Interpolator f175C;
    static boolean f176a;
    static final boolean f177b;
    static final Interpolator f178z;
    ArrayList<Runnable> f179c;
    Runnable[] f180d;
    boolean f181e;
    ArrayList<Fragment> f182f;
    ArrayList<Fragment> f183g;
    ArrayList<Integer> f184h;
    ArrayList<C0033e> f185i;
    ArrayList<Fragment> f186j;
    ArrayList<C0033e> f187k;
    ArrayList<Integer> f188l;
    ArrayList<C0038a> f189m;
    int f190n;
    C0037f f191o;
    C0013g f192p;
    Fragment f193q;
    boolean f194r;
    boolean f195s;
    boolean f196t;
    String f197u;
    boolean f198v;
    Bundle f199w;
    SparseArray<Parcelable> f200x;
    Runnable f201y;

    /* renamed from: android.support.v4.app.i.1 */
    class C00401 implements Runnable {
        final /* synthetic */ C0043i f169a;

        C00401(C0043i c0043i) {
            this.f169a = c0043i;
        }

        public void run() {
            this.f169a.m223e();
        }
    }

    /* renamed from: android.support.v4.app.i.2 */
    class C00412 implements AnimationListener {
        final /* synthetic */ Fragment f170a;
        final /* synthetic */ C0043i f171b;

        C00412(C0043i c0043i, Fragment fragment) {
            this.f171b = c0043i;
            this.f170a = fragment;
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f170a.f49c != null) {
                this.f170a.f49c = null;
                this.f171b.m199a(this.f170a, this.f170a.f50d, 0, 0, false);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: android.support.v4.app.i.a */
    static class C0042a {
        public static final int[] f172a;

        static {
            f172a = new int[]{16842755, 16842960, 16842961};
        }
    }

    static {
        boolean z = false;
        f176a = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        f177b = z;
        f178z = new DecelerateInterpolator(2.5f);
        f173A = new DecelerateInterpolator(1.5f);
        f174B = new AccelerateInterpolator(2.5f);
        f175C = new AccelerateInterpolator(1.5f);
    }

    C0043i() {
        this.f190n = 0;
        this.f199w = null;
        this.f200x = null;
        this.f201y = new C00401(this);
    }

    static Animation m179a(Context context, float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(f173A);
        alphaAnimation.setDuration(220);
        return alphaAnimation;
    }

    static Animation m180a(Context context, float f, float f2, float f3, float f4) {
        Animation animationSet = new AnimationSet(false);
        Animation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f178z);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(f3, f4);
        scaleAnimation.setInterpolator(f173A);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    private void m181a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new C0100d("FragmentManager"));
        if (this.f191o != null) {
            try {
                this.f191o.dump("  ", null, printWriter, new String[0]);
            } catch (Throwable e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                m203a("  ", null, printWriter, new String[0]);
            } catch (Throwable e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        }
        throw runtimeException;
    }

    public static int m182b(int i, boolean z) {
        switch (i) {
            case 4097:
                return z ? 1 : 2;
            case 4099:
                return z ? 5 : 6;
            case 8194:
                return z ? 3 : 4;
            default:
                return -1;
        }
    }

    public static int m183c(int i) {
        switch (i) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    private void m184u() {
        if (this.f195s) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.f197u != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.f197u);
        }
    }

    public int m185a(C0033e c0033e) {
        int size;
        synchronized (this) {
            if (this.f188l == null || this.f188l.size() <= 0) {
                if (this.f187k == null) {
                    this.f187k = new ArrayList();
                }
                size = this.f187k.size();
                if (f176a) {
                    Log.v("FragmentManager", "Setting back stack index " + size + " to " + c0033e);
                }
                this.f187k.add(c0033e);
            } else {
                size = ((Integer) this.f188l.remove(this.f188l.size() - 1)).intValue();
                if (f176a) {
                    Log.v("FragmentManager", "Adding back stack index " + size + " with " + c0033e);
                }
                this.f187k.set(size, c0033e);
            }
        }
        return size;
    }

    public Fragment m186a(int i) {
        int size;
        Fragment fragment;
        if (this.f183g != null) {
            for (size = this.f183g.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f183g.get(size);
                if (fragment != null && fragment.f70x == i) {
                    return fragment;
                }
            }
        }
        if (this.f182f != null) {
            for (size = this.f182f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f182f.get(size);
                if (fragment != null && fragment.f70x == i) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment m187a(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        if (i >= this.f182f.size()) {
            m181a(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        }
        Fragment fragment = (Fragment) this.f182f.get(i);
        if (fragment != null) {
            return fragment;
        }
        m181a(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        return fragment;
    }

    public Fragment m188a(String str) {
        int size;
        Fragment fragment;
        if (!(this.f183g == null || str == null)) {
            for (size = this.f183g.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f183g.get(size);
                if (fragment != null && str.equals(fragment.f72z)) {
                    return fragment;
                }
            }
        }
        if (!(this.f182f == null || str == null)) {
            for (size = this.f182f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f182f.get(size);
                if (fragment != null && str.equals(fragment.f72z)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public C0032k m189a() {
        return new C0033e(this);
    }

    Animation m190a(Fragment fragment, int i, boolean z, int i2) {
        Animation a = fragment.m41a(i, z, fragment.f29H);
        if (a != null) {
            return a;
        }
        if (fragment.f29H != 0) {
            a = AnimationUtils.loadAnimation(this.f191o, fragment.f29H);
            if (a != null) {
                return a;
            }
        }
        if (i == 0) {
            return null;
        }
        int b = C0043i.m182b(i, z);
        if (b < 0) {
            return null;
        }
        switch (b) {
            case C0627j.View_paddingStart /*1*/:
                return C0043i.m180a(this.f191o, 1.125f, 1.0f, 0.0f, 1.0f);
            case C0627j.View_paddingEnd /*2*/:
                return C0043i.m180a(this.f191o, 1.0f, 0.975f, 1.0f, 0.0f);
            case C0627j.Toolbar_subtitle /*3*/:
                return C0043i.m180a(this.f191o, 0.975f, 1.0f, 0.0f, 1.0f);
            case C0627j.Toolbar_contentInsetStart /*4*/:
                return C0043i.m180a(this.f191o, 1.0f, 1.075f, 1.0f, 0.0f);
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                return C0043i.m179a(this.f191o, 0.0f, 1.0f);
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                return C0043i.m179a(this.f191o, 1.0f, 0.0f);
            default:
                if (i2 == 0 && this.f191o.getWindow() != null) {
                    i2 = this.f191o.getWindow().getAttributes().windowAnimations;
                }
                return i2 == 0 ? null : null;
        }
    }

    void m191a(int i, int i2, int i3, boolean z) {
        if (this.f191o == null && i != 0) {
            throw new IllegalStateException("No activity");
        } else if (z || this.f190n != i) {
            this.f190n = i;
            if (this.f182f != null) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < this.f182f.size()) {
                    int a;
                    Fragment fragment = (Fragment) this.f182f.get(i4);
                    if (fragment != null) {
                        m199a(fragment, i, i2, i3, false);
                        if (fragment.f35N != null) {
                            a = i5 | fragment.f35N.m301a();
                            i4++;
                            i5 = a;
                        }
                    }
                    a = i5;
                    i4++;
                    i5 = a;
                }
                if (i5 == 0) {
                    m218d();
                }
                if (this.f194r && this.f191o != null && this.f190n == 5) {
                    this.f191o.b_();
                    this.f194r = false;
                }
            }
        }
    }

    public void m192a(int i, C0033e c0033e) {
        synchronized (this) {
            if (this.f187k == null) {
                this.f187k = new ArrayList();
            }
            int size = this.f187k.size();
            if (i < size) {
                if (f176a) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + c0033e);
                }
                this.f187k.set(i, c0033e);
            } else {
                while (size < i) {
                    this.f187k.add(null);
                    if (this.f188l == null) {
                        this.f188l = new ArrayList();
                    }
                    if (f176a) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.f188l.add(Integer.valueOf(size));
                    size++;
                }
                if (f176a) {
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + c0033e);
                }
                this.f187k.add(c0033e);
            }
        }
    }

    void m193a(int i, boolean z) {
        m191a(i, 0, 0, z);
    }

    public void m194a(Configuration configuration) {
        if (this.f183g != null) {
            for (int i = 0; i < this.f183g.size(); i++) {
                Fragment fragment = (Fragment) this.f183g.get(i);
                if (fragment != null) {
                    fragment.m47a(configuration);
                }
            }
        }
    }

    public void m195a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.f53g < 0) {
            m181a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, fragment.f53g);
    }

    void m196a(Parcelable parcelable, ArrayList<Fragment> arrayList) {
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.f73a != null) {
                int i;
                Fragment fragment;
                int i2;
                if (arrayList != null) {
                    for (i = 0; i < arrayList.size(); i++) {
                        fragment = (Fragment) arrayList.get(i);
                        if (f176a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                        }
                        FragmentState fragmentState = fragmentManagerState.f73a[fragment.f53g];
                        fragmentState.f86k = fragment;
                        fragment.f52f = null;
                        fragment.f65s = 0;
                        fragment.f63q = false;
                        fragment.f59m = false;
                        fragment.f56j = null;
                        if (fragmentState.f85j != null) {
                            fragmentState.f85j.setClassLoader(this.f191o.getClassLoader());
                            fragment.f52f = fragmentState.f85j.getSparseParcelableArray("android:view_state");
                            fragment.f51e = fragmentState.f85j;
                        }
                    }
                }
                this.f182f = new ArrayList(fragmentManagerState.f73a.length);
                if (this.f184h != null) {
                    this.f184h.clear();
                }
                for (i2 = 0; i2 < fragmentManagerState.f73a.length; i2++) {
                    FragmentState fragmentState2 = fragmentManagerState.f73a[i2];
                    if (fragmentState2 != null) {
                        Fragment a = fragmentState2.m104a(this.f191o, this.f193q);
                        if (f176a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i2 + ": " + a);
                        }
                        this.f182f.add(a);
                        fragmentState2.f86k = null;
                    } else {
                        this.f182f.add(null);
                        if (this.f184h == null) {
                            this.f184h = new ArrayList();
                        }
                        if (f176a) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + i2);
                        }
                        this.f184h.add(Integer.valueOf(i2));
                    }
                }
                if (arrayList != null) {
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        fragment = (Fragment) arrayList.get(i3);
                        if (fragment.f57k >= 0) {
                            if (fragment.f57k < this.f182f.size()) {
                                fragment.f56j = (Fragment) this.f182f.get(fragment.f57k);
                            } else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.f57k);
                                fragment.f56j = null;
                            }
                        }
                    }
                }
                if (fragmentManagerState.f74b != null) {
                    this.f183g = new ArrayList(fragmentManagerState.f74b.length);
                    for (i = 0; i < fragmentManagerState.f74b.length; i++) {
                        fragment = (Fragment) this.f182f.get(fragmentManagerState.f74b[i]);
                        if (fragment == null) {
                            m181a(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.f74b[i]));
                        }
                        fragment.f59m = true;
                        if (f176a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + fragment);
                        }
                        if (this.f183g.contains(fragment)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.f183g.add(fragment);
                    }
                } else {
                    this.f183g = null;
                }
                if (fragmentManagerState.f75c != null) {
                    this.f185i = new ArrayList(fragmentManagerState.f75c.length);
                    for (i2 = 0; i2 < fragmentManagerState.f75c.length; i2++) {
                        C0033e a2 = fragmentManagerState.f75c[i2].m22a(this);
                        if (f176a) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a2.f142o + "): " + a2);
                            a2.m161a("  ", new PrintWriter(new C0100d("FragmentManager")), false);
                        }
                        this.f185i.add(a2);
                        if (a2.f142o >= 0) {
                            m192a(a2.f142o, a2);
                        }
                    }
                    return;
                }
                this.f185i = null;
            }
        }
    }

    public void m197a(Fragment fragment) {
        if (!fragment.f33L) {
            return;
        }
        if (this.f181e) {
            this.f198v = true;
            return;
        }
        fragment.f33L = false;
        m199a(fragment, this.f190n, 0, 0, false);
    }

    public void m198a(Fragment fragment, int i, int i2) {
        if (f176a) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.f65s);
        }
        boolean z = !fragment.m54a();
        if (!fragment.f23B || z) {
            if (this.f183g != null) {
                this.f183g.remove(fragment);
            }
            if (fragment.f26E && fragment.f27F) {
                this.f194r = true;
            }
            fragment.f59m = false;
            fragment.f60n = true;
            m199a(fragment, z ? 0 : 1, i, i2, false);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m199a(Fragment r10, int r11, int r12, int r13, boolean r14) {
        /*
        r9 = this;
        r8 = 4;
        r6 = 3;
        r3 = 0;
        r5 = 1;
        r7 = 0;
        r0 = r10.f59m;
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = r10.f23B;
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        if (r11 <= r5) goto L_0x0010;
    L_0x000f:
        r11 = r5;
    L_0x0010:
        r0 = r10.f60n;
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r10.f48b;
        if (r11 <= r0) goto L_0x001a;
    L_0x0018:
        r11 = r10.f48b;
    L_0x001a:
        r0 = r10.f33L;
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r10.f48b;
        if (r0 >= r8) goto L_0x0025;
    L_0x0022:
        if (r11 <= r6) goto L_0x0025;
    L_0x0024:
        r11 = r6;
    L_0x0025:
        r0 = r10.f48b;
        if (r0 >= r11) goto L_0x024b;
    L_0x0029:
        r0 = r10.f62p;
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r10.f63q;
        if (r0 != 0) goto L_0x0032;
    L_0x0031:
        return;
    L_0x0032:
        r0 = r10.f49c;
        if (r0 == 0) goto L_0x0040;
    L_0x0036:
        r10.f49c = r7;
        r2 = r10.f50d;
        r0 = r9;
        r1 = r10;
        r4 = r3;
        r0.m199a(r1, r2, r3, r4, r5);
    L_0x0040:
        r0 = r10.f48b;
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0131;
            case 2: goto L_0x01fa;
            case 3: goto L_0x01fa;
            case 4: goto L_0x021b;
            default: goto L_0x0045;
        };
    L_0x0045:
        r10.f48b = r11;
        goto L_0x0031;
    L_0x0048:
        r0 = f176a;
        if (r0 == 0) goto L_0x0064;
    L_0x004c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0064:
        r0 = r10.f51e;
        if (r0 == 0) goto L_0x00a8;
    L_0x0068:
        r0 = r10.f51e;
        r1 = r9.f191o;
        r1 = r1.getClassLoader();
        r0.setClassLoader(r1);
        r0 = r10.f51e;
        r1 = "android:view_state";
        r0 = r0.getSparseParcelableArray(r1);
        r10.f52f = r0;
        r0 = r10.f51e;
        r1 = "android:target_state";
        r0 = r9.m187a(r0, r1);
        r10.f56j = r0;
        r0 = r10.f56j;
        if (r0 == 0) goto L_0x0095;
    L_0x008b:
        r0 = r10.f51e;
        r1 = "android:target_req_state";
        r0 = r0.getInt(r1, r3);
        r10.f58l = r0;
    L_0x0095:
        r0 = r10.f51e;
        r1 = "android:user_visible_hint";
        r0 = r0.getBoolean(r1, r5);
        r10.f34M = r0;
        r0 = r10.f34M;
        if (r0 != 0) goto L_0x00a8;
    L_0x00a3:
        r10.f33L = r5;
        if (r11 <= r6) goto L_0x00a8;
    L_0x00a7:
        r11 = r6;
    L_0x00a8:
        r0 = r9.f191o;
        r10.f67u = r0;
        r0 = r9.f193q;
        r10.f69w = r0;
        r0 = r9.f193q;
        if (r0 == 0) goto L_0x00e4;
    L_0x00b4:
        r0 = r9.f193q;
        r0 = r0.f68v;
    L_0x00b8:
        r10.f66t = r0;
        r10.f28G = r3;
        r0 = r9.f191o;
        r10.m45a(r0);
        r0 = r10.f28G;
        if (r0 != 0) goto L_0x00e9;
    L_0x00c5:
        r0 = new android.support.v4.app.s;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " did not call through to super.onAttach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00e4:
        r0 = r9.f191o;
        r0 = r0.f157b;
        goto L_0x00b8;
    L_0x00e9:
        r0 = r10.f69w;
        if (r0 != 0) goto L_0x00f2;
    L_0x00ed:
        r0 = r9.f191o;
        r0.m170a(r10);
    L_0x00f2:
        r0 = r10.f25D;
        if (r0 != 0) goto L_0x00fb;
    L_0x00f6:
        r0 = r10.f51e;
        r10.m79h(r0);
    L_0x00fb:
        r10.f25D = r3;
        r0 = r10.f62p;
        if (r0 == 0) goto L_0x0131;
    L_0x0101:
        r0 = r10.f51e;
        r0 = r10.m64c(r0);
        r1 = r10.f51e;
        r0 = r10.m57b(r0, r7, r1);
        r10.f31J = r0;
        r0 = r10.f31J;
        if (r0 == 0) goto L_0x0244;
    L_0x0113:
        r0 = r10.f31J;
        r10.f32K = r0;
        r0 = r10.f31J;
        r0 = android.support.v4.app.C0061q.m332a(r0);
        r10.f31J = r0;
        r0 = r10.f22A;
        if (r0 == 0) goto L_0x012a;
    L_0x0123:
        r0 = r10.f31J;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x012a:
        r0 = r10.f31J;
        r1 = r10.f51e;
        r10.m51a(r0, r1);
    L_0x0131:
        if (r11 <= r5) goto L_0x01fa;
    L_0x0133:
        r0 = f176a;
        if (r0 == 0) goto L_0x014f;
    L_0x0137:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x014f:
        r0 = r10.f62p;
        if (r0 != 0) goto L_0x01ea;
    L_0x0153:
        r0 = r10.f71y;
        if (r0 == 0) goto L_0x03a6;
    L_0x0157:
        r0 = r9.f192p;
        r1 = r10.f71y;
        r0 = r0.m23a(r1);
        r0 = (android.view.ViewGroup) r0;
        if (r0 != 0) goto L_0x01a6;
    L_0x0163:
        r1 = r10.f64r;
        if (r1 != 0) goto L_0x01a6;
    L_0x0167:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "No view found for id 0x";
        r2 = r2.append(r3);
        r3 = r10.f71y;
        r3 = java.lang.Integer.toHexString(r3);
        r2 = r2.append(r3);
        r3 = " (";
        r2 = r2.append(r3);
        r3 = r10.m63c();
        r4 = r10.f71y;
        r3 = r3.getResourceName(r4);
        r2 = r2.append(r3);
        r3 = ") for fragment ";
        r2 = r2.append(r3);
        r2 = r2.append(r10);
        r2 = r2.toString();
        r1.<init>(r2);
        r9.m181a(r1);
    L_0x01a6:
        r10.f30I = r0;
        r1 = r10.f51e;
        r1 = r10.m64c(r1);
        r2 = r10.f51e;
        r1 = r10.m57b(r1, r0, r2);
        r10.f31J = r1;
        r1 = r10.f31J;
        if (r1 == 0) goto L_0x0248;
    L_0x01ba:
        r1 = r10.f31J;
        r10.f32K = r1;
        r1 = r10.f31J;
        r1 = android.support.v4.app.C0061q.m332a(r1);
        r10.f31J = r1;
        if (r0 == 0) goto L_0x01d8;
    L_0x01c8:
        r1 = r9.m190a(r10, r12, r5, r13);
        if (r1 == 0) goto L_0x01d3;
    L_0x01ce:
        r2 = r10.f31J;
        r2.startAnimation(r1);
    L_0x01d3:
        r1 = r10.f31J;
        r0.addView(r1);
    L_0x01d8:
        r0 = r10.f22A;
        if (r0 == 0) goto L_0x01e3;
    L_0x01dc:
        r0 = r10.f31J;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x01e3:
        r0 = r10.f31J;
        r1 = r10.f51e;
        r10.m51a(r0, r1);
    L_0x01ea:
        r0 = r10.f51e;
        r10.m81i(r0);
        r0 = r10.f31J;
        if (r0 == 0) goto L_0x01f8;
    L_0x01f3:
        r0 = r10.f51e;
        r10.m48a(r0);
    L_0x01f8:
        r10.f51e = r7;
    L_0x01fa:
        if (r11 <= r6) goto L_0x021b;
    L_0x01fc:
        r0 = f176a;
        if (r0 == 0) goto L_0x0218;
    L_0x0200:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0218:
        r10.m32A();
    L_0x021b:
        if (r11 <= r8) goto L_0x0045;
    L_0x021d:
        r0 = f176a;
        if (r0 == 0) goto L_0x0239;
    L_0x0221:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0239:
        r10.f61o = r5;
        r10.m33B();
        r10.f51e = r7;
        r10.f52f = r7;
        goto L_0x0045;
    L_0x0244:
        r10.f32K = r7;
        goto L_0x0131;
    L_0x0248:
        r10.f32K = r7;
        goto L_0x01ea;
    L_0x024b:
        r0 = r10.f48b;
        if (r0 <= r11) goto L_0x0045;
    L_0x024f:
        r0 = r10.f48b;
        switch(r0) {
            case 1: goto L_0x0256;
            case 2: goto L_0x02d6;
            case 3: goto L_0x02b5;
            case 4: goto L_0x0294;
            case 5: goto L_0x0270;
            default: goto L_0x0254;
        };
    L_0x0254:
        goto L_0x0045;
    L_0x0256:
        if (r11 >= r5) goto L_0x0045;
    L_0x0258:
        r0 = r9.f196t;
        if (r0 == 0) goto L_0x0267;
    L_0x025c:
        r0 = r10.f49c;
        if (r0 == 0) goto L_0x0267;
    L_0x0260:
        r0 = r10.f49c;
        r10.f49c = r7;
        r0.clearAnimation();
    L_0x0267:
        r0 = r10.f49c;
        if (r0 == 0) goto L_0x0343;
    L_0x026b:
        r10.f50d = r11;
        r11 = r5;
        goto L_0x0045;
    L_0x0270:
        r0 = 5;
        if (r11 >= r0) goto L_0x0294;
    L_0x0273:
        r0 = f176a;
        if (r0 == 0) goto L_0x028f;
    L_0x0277:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x028f:
        r10.m35D();
        r10.f61o = r3;
    L_0x0294:
        if (r11 >= r8) goto L_0x02b5;
    L_0x0296:
        r0 = f176a;
        if (r0 == 0) goto L_0x02b2;
    L_0x029a:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02b2:
        r10.m36E();
    L_0x02b5:
        if (r11 >= r6) goto L_0x02d6;
    L_0x02b7:
        r0 = f176a;
        if (r0 == 0) goto L_0x02d3;
    L_0x02bb:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STOPPED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02d3:
        r10.m37F();
    L_0x02d6:
        r0 = 2;
        if (r11 >= r0) goto L_0x0256;
    L_0x02d9:
        r0 = f176a;
        if (r0 == 0) goto L_0x02f5;
    L_0x02dd:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02f5:
        r0 = r10.f31J;
        if (r0 == 0) goto L_0x0308;
    L_0x02f9:
        r0 = r9.f191o;
        r0 = r0.isFinishing();
        if (r0 != 0) goto L_0x0308;
    L_0x0301:
        r0 = r10.f52f;
        if (r0 != 0) goto L_0x0308;
    L_0x0305:
        r9.m221e(r10);
    L_0x0308:
        r10.m38G();
        r0 = r10.f31J;
        if (r0 == 0) goto L_0x033b;
    L_0x030f:
        r0 = r10.f30I;
        if (r0 == 0) goto L_0x033b;
    L_0x0313:
        r0 = r9.f190n;
        if (r0 <= 0) goto L_0x03a3;
    L_0x0317:
        r0 = r9.f196t;
        if (r0 != 0) goto L_0x03a3;
    L_0x031b:
        r0 = r9.m190a(r10, r12, r3, r13);
    L_0x031f:
        if (r0 == 0) goto L_0x0334;
    L_0x0321:
        r1 = r10.f31J;
        r10.f49c = r1;
        r10.f50d = r11;
        r1 = new android.support.v4.app.i$2;
        r1.<init>(r9, r10);
        r0.setAnimationListener(r1);
        r1 = r10.f31J;
        r1.startAnimation(r0);
    L_0x0334:
        r0 = r10.f30I;
        r1 = r10.f31J;
        r0.removeView(r1);
    L_0x033b:
        r10.f30I = r7;
        r10.f31J = r7;
        r10.f32K = r7;
        goto L_0x0256;
    L_0x0343:
        r0 = f176a;
        if (r0 == 0) goto L_0x035f;
    L_0x0347:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x035f:
        r0 = r10.f25D;
        if (r0 != 0) goto L_0x0366;
    L_0x0363:
        r10.m39H();
    L_0x0366:
        r10.f28G = r3;
        r10.m89p();
        r0 = r10.f28G;
        if (r0 != 0) goto L_0x038e;
    L_0x036f:
        r0 = new android.support.v4.app.s;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " did not call through to super.onDetach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x038e:
        if (r14 != 0) goto L_0x0045;
    L_0x0390:
        r0 = r10.f25D;
        if (r0 != 0) goto L_0x0399;
    L_0x0394:
        r9.m219d(r10);
        goto L_0x0045;
    L_0x0399:
        r10.f67u = r7;
        r10.f69w = r7;
        r10.f66t = r7;
        r10.f68v = r7;
        goto L_0x0045;
    L_0x03a3:
        r0 = r7;
        goto L_0x031f;
    L_0x03a6:
        r0 = r7;
        goto L_0x01a6;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.i.a(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    public void m200a(Fragment fragment, boolean z) {
        if (this.f183g == null) {
            this.f183g = new ArrayList();
        }
        if (f176a) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        m215c(fragment);
        if (!fragment.f23B) {
            if (this.f183g.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            this.f183g.add(fragment);
            fragment.f59m = true;
            fragment.f60n = false;
            if (fragment.f26E && fragment.f27F) {
                this.f194r = true;
            }
            if (z) {
                m209b(fragment);
            }
        }
    }

    public void m201a(C0037f c0037f, C0013g c0013g, Fragment fragment) {
        if (this.f191o != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f191o = c0037f;
        this.f192p = c0013g;
        this.f193q = fragment;
    }

    public void m202a(Runnable runnable, boolean z) {
        if (!z) {
            m184u();
        }
        synchronized (this) {
            if (this.f196t || this.f191o == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
            if (this.f179c == null) {
                this.f179c = new ArrayList();
            }
            this.f179c.add(runnable);
            if (this.f179c.size() == 1) {
                this.f191o.f156a.removeCallbacks(this.f201y);
                this.f191o.f156a.post(this.f201y);
            }
        }
    }

    public void m203a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int i;
        Fragment fragment;
        int i2 = 0;
        String str2 = str + "    ";
        if (this.f182f != null) {
            size = this.f182f.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(":");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f182f.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment);
                    if (fragment != null) {
                        fragment.m52a(str2, fileDescriptor, printWriter, strArr);
                    }
                }
            }
        }
        if (this.f183g != null) {
            size = this.f183g.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Added Fragments:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f183g.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.f186j != null) {
            size = this.f186j.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Fragments Created Menus:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f186j.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.f185i != null) {
            size = this.f185i.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack:");
                for (i = 0; i < size; i++) {
                    C0033e c0033e = (C0033e) this.f185i.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(c0033e.toString());
                    c0033e.m160a(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        synchronized (this) {
            if (this.f187k != null) {
                int size2 = this.f187k.size();
                if (size2 > 0) {
                    printWriter.print(str);
                    printWriter.println("Back Stack Indices:");
                    for (i = 0; i < size2; i++) {
                        c0033e = (C0033e) this.f187k.get(i);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i);
                        printWriter.print(": ");
                        printWriter.println(c0033e);
                    }
                }
            }
            if (this.f188l != null && this.f188l.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.f188l.toArray()));
            }
        }
        if (this.f179c != null) {
            i = this.f179c.size();
            if (i > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                while (i2 < i) {
                    Runnable runnable = (Runnable) this.f179c.get(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i2);
                    printWriter.print(": ");
                    printWriter.println(runnable);
                    i2++;
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mActivity=");
        printWriter.println(this.f191o);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f192p);
        if (this.f193q != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f193q);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f190n);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.f195s);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.f196t);
        if (this.f194r) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f194r);
        }
        if (this.f197u != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.f197u);
        }
        if (this.f184h != null && this.f184h.size() > 0) {
            printWriter.print(str);
            printWriter.print("  mAvailIndices: ");
            printWriter.println(Arrays.toString(this.f184h.toArray()));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean m204a(android.os.Handler r12, String r13, int r14, int r15) {
        /*
        r11 = this;
        r4 = 0;
        r2 = 1;
        r3 = 0;
        r0 = r11.f185i;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r3;
    L_0x0008:
        if (r13 != 0) goto L_0x0037;
    L_0x000a:
        if (r14 >= 0) goto L_0x0037;
    L_0x000c:
        r0 = r15 & 1;
        if (r0 != 0) goto L_0x0037;
    L_0x0010:
        r0 = r11.f185i;
        r0 = r0.size();
        r0 = r0 + -1;
        if (r0 < 0) goto L_0x0007;
    L_0x001a:
        r1 = r11.f185i;
        r0 = r1.remove(r0);
        r0 = (android.support.v4.app.C0033e) r0;
        r1 = new android.util.SparseArray;
        r1.<init>();
        r3 = new android.util.SparseArray;
        r3.<init>();
        r0.m159a(r1, r3);
        r0.m154a(r2, r4, r1, r3);
        r11.m225f();
    L_0x0035:
        r3 = r2;
        goto L_0x0007;
    L_0x0037:
        r0 = -1;
        if (r13 != 0) goto L_0x003c;
    L_0x003a:
        if (r14 < 0) goto L_0x008b;
    L_0x003c:
        r0 = r11.f185i;
        r0 = r0.size();
        r1 = r0 + -1;
    L_0x0044:
        if (r1 < 0) goto L_0x005a;
    L_0x0046:
        r0 = r11.f185i;
        r0 = r0.get(r1);
        r0 = (android.support.v4.app.C0033e) r0;
        if (r13 == 0) goto L_0x0081;
    L_0x0050:
        r5 = r0.m164c();
        r5 = r13.equals(r5);
        if (r5 == 0) goto L_0x0081;
    L_0x005a:
        if (r1 < 0) goto L_0x0007;
    L_0x005c:
        r0 = r15 & 1;
        if (r0 == 0) goto L_0x008a;
    L_0x0060:
        r1 = r1 + -1;
    L_0x0062:
        if (r1 < 0) goto L_0x008a;
    L_0x0064:
        r0 = r11.f185i;
        r0 = r0.get(r1);
        r0 = (android.support.v4.app.C0033e) r0;
        if (r13 == 0) goto L_0x0078;
    L_0x006e:
        r5 = r0.m164c();
        r5 = r13.equals(r5);
        if (r5 != 0) goto L_0x007e;
    L_0x0078:
        if (r14 < 0) goto L_0x008a;
    L_0x007a:
        r0 = r0.f142o;
        if (r14 != r0) goto L_0x008a;
    L_0x007e:
        r1 = r1 + -1;
        goto L_0x0062;
    L_0x0081:
        if (r14 < 0) goto L_0x0087;
    L_0x0083:
        r0 = r0.f142o;
        if (r14 == r0) goto L_0x005a;
    L_0x0087:
        r1 = r1 + -1;
        goto L_0x0044;
    L_0x008a:
        r0 = r1;
    L_0x008b:
        r1 = r11.f185i;
        r1 = r1.size();
        r1 = r1 + -1;
        if (r0 == r1) goto L_0x0007;
    L_0x0095:
        r6 = new java.util.ArrayList;
        r6.<init>();
        r1 = r11.f185i;
        r1 = r1.size();
        r1 = r1 + -1;
    L_0x00a2:
        if (r1 <= r0) goto L_0x00b0;
    L_0x00a4:
        r5 = r11.f185i;
        r5 = r5.remove(r1);
        r6.add(r5);
        r1 = r1 + -1;
        goto L_0x00a2;
    L_0x00b0:
        r0 = r6.size();
        r7 = r0 + -1;
        r8 = new android.util.SparseArray;
        r8.<init>();
        r9 = new android.util.SparseArray;
        r9.<init>();
        r1 = r3;
    L_0x00c1:
        if (r1 > r7) goto L_0x00d0;
    L_0x00c3:
        r0 = r6.get(r1);
        r0 = (android.support.v4.app.C0033e) r0;
        r0.m159a(r8, r9);
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x00c1;
    L_0x00d0:
        r5 = r4;
        r4 = r3;
    L_0x00d2:
        if (r4 > r7) goto L_0x0108;
    L_0x00d4:
        r0 = f176a;
        if (r0 == 0) goto L_0x00f4;
    L_0x00d8:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r10 = "Popping back stack state: ";
        r1 = r1.append(r10);
        r10 = r6.get(r4);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x00f4:
        r0 = r6.get(r4);
        r0 = (android.support.v4.app.C0033e) r0;
        if (r4 != r7) goto L_0x0106;
    L_0x00fc:
        r1 = r2;
    L_0x00fd:
        r1 = r0.m154a(r1, r5, r8, r9);
        r0 = r4 + 1;
        r4 = r0;
        r5 = r1;
        goto L_0x00d2;
    L_0x0106:
        r1 = r3;
        goto L_0x00fd;
    L_0x0108:
        r11.m225f();
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.i.a(android.os.Handler, java.lang.String, int, int):boolean");
    }

    public boolean m205a(Menu menu) {
        if (this.f183g == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.f183g.size(); i++) {
            Fragment fragment = (Fragment) this.f183g.get(i);
            if (fragment != null && fragment.m66c(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean m206a(Menu menu, MenuInflater menuInflater) {
        boolean z;
        Fragment fragment;
        int i = 0;
        ArrayList arrayList = null;
        if (this.f183g != null) {
            int i2 = 0;
            z = false;
            while (i2 < this.f183g.size()) {
                fragment = (Fragment) this.f183g.get(i2);
                if (fragment != null && fragment.m61b(menu, menuInflater)) {
                    z = true;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                }
                i2++;
                z = z;
            }
        } else {
            z = false;
        }
        if (this.f186j != null) {
            while (i < this.f186j.size()) {
                fragment = (Fragment) this.f186j.get(i);
                if (arrayList == null || !arrayList.contains(fragment)) {
                    fragment.m90q();
                }
                i++;
            }
        }
        this.f186j = arrayList;
        return z;
    }

    public boolean m207a(MenuItem menuItem) {
        if (this.f183g == null) {
            return false;
        }
        for (int i = 0; i < this.f183g.size(); i++) {
            Fragment fragment = (Fragment) this.f183g.get(i);
            if (fragment != null && fragment.m67c(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void m208b(int i) {
        synchronized (this) {
            this.f187k.set(i, null);
            if (this.f188l == null) {
                this.f188l = new ArrayList();
            }
            if (f176a) {
                Log.v("FragmentManager", "Freeing back stack index " + i);
            }
            this.f188l.add(Integer.valueOf(i));
        }
    }

    void m209b(Fragment fragment) {
        m199a(fragment, this.f190n, 0, 0, false);
    }

    public void m210b(Fragment fragment, int i, int i2) {
        if (f176a) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.f22A) {
            fragment.f22A = true;
            if (fragment.f31J != null) {
                Animation a = m190a(fragment, i, false, i2);
                if (a != null) {
                    fragment.f31J.startAnimation(a);
                }
                fragment.f31J.setVisibility(8);
            }
            if (fragment.f59m && fragment.f26E && fragment.f27F) {
                this.f194r = true;
            }
            fragment.m53a(true);
        }
    }

    void m211b(C0033e c0033e) {
        if (this.f185i == null) {
            this.f185i = new ArrayList();
        }
        this.f185i.add(c0033e);
        m225f();
    }

    public void m212b(Menu menu) {
        if (this.f183g != null) {
            for (int i = 0; i < this.f183g.size(); i++) {
                Fragment fragment = (Fragment) this.f183g.get(i);
                if (fragment != null) {
                    fragment.m70d(menu);
                }
            }
        }
    }

    public boolean m213b() {
        return m223e();
    }

    public boolean m214b(MenuItem menuItem) {
        if (this.f183g == null) {
            return false;
        }
        for (int i = 0; i < this.f183g.size(); i++) {
            Fragment fragment = (Fragment) this.f183g.get(i);
            if (fragment != null && fragment.m71d(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void m215c(Fragment fragment) {
        if (fragment.f53g < 0) {
            if (this.f184h == null || this.f184h.size() <= 0) {
                if (this.f182f == null) {
                    this.f182f = new ArrayList();
                }
                fragment.m44a(this.f182f.size(), this.f193q);
                this.f182f.add(fragment);
            } else {
                fragment.m44a(((Integer) this.f184h.remove(this.f184h.size() - 1)).intValue(), this.f193q);
                this.f182f.set(fragment.f53g, fragment);
            }
            if (f176a) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }

    public void m216c(Fragment fragment, int i, int i2) {
        if (f176a) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.f22A) {
            fragment.f22A = false;
            if (fragment.f31J != null) {
                Animation a = m190a(fragment, i, true, i2);
                if (a != null) {
                    fragment.f31J.startAnimation(a);
                }
                fragment.f31J.setVisibility(0);
            }
            if (fragment.f59m && fragment.f26E && fragment.f27F) {
                this.f194r = true;
            }
            fragment.m53a(false);
        }
    }

    public boolean m217c() {
        m184u();
        m213b();
        return m204a(this.f191o.f156a, null, -1, 0);
    }

    void m218d() {
        if (this.f182f != null) {
            for (int i = 0; i < this.f182f.size(); i++) {
                Fragment fragment = (Fragment) this.f182f.get(i);
                if (fragment != null) {
                    m197a(fragment);
                }
            }
        }
    }

    void m219d(Fragment fragment) {
        if (fragment.f53g >= 0) {
            if (f176a) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.f182f.set(fragment.f53g, null);
            if (this.f184h == null) {
                this.f184h = new ArrayList();
            }
            this.f184h.add(Integer.valueOf(fragment.f53g));
            this.f191o.m171a(fragment.f54h);
            fragment.m88o();
        }
    }

    public void m220d(Fragment fragment, int i, int i2) {
        if (f176a) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.f23B) {
            fragment.f23B = true;
            if (fragment.f59m) {
                if (this.f183g != null) {
                    if (f176a) {
                        Log.v("FragmentManager", "remove from detach: " + fragment);
                    }
                    this.f183g.remove(fragment);
                }
                if (fragment.f26E && fragment.f27F) {
                    this.f194r = true;
                }
                fragment.f59m = false;
                m199a(fragment, 1, i, i2, false);
            }
        }
    }

    void m221e(Fragment fragment) {
        if (fragment.f32K != null) {
            if (this.f200x == null) {
                this.f200x = new SparseArray();
            } else {
                this.f200x.clear();
            }
            fragment.f32K.saveHierarchyState(this.f200x);
            if (this.f200x.size() > 0) {
                fragment.f52f = this.f200x;
                this.f200x = null;
            }
        }
    }

    public void m222e(Fragment fragment, int i, int i2) {
        if (f176a) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.f23B) {
            fragment.f23B = false;
            if (!fragment.f59m) {
                if (this.f183g == null) {
                    this.f183g = new ArrayList();
                }
                if (this.f183g.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (f176a) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                this.f183g.add(fragment);
                fragment.f59m = true;
                if (fragment.f26E && fragment.f27F) {
                    this.f194r = true;
                }
                m199a(fragment, this.f190n, i, i2, false);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m223e() {
        /*
        r6 = this;
        r0 = 1;
        r2 = 0;
        r1 = r6.f181e;
        if (r1 == 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalStateException;
        r1 = "Recursive entry to executePendingTransactions";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        r1 = android.os.Looper.myLooper();
        r3 = r6.f191o;
        r3 = r3.f156a;
        r3 = r3.getLooper();
        if (r1 == r3) goto L_0x0024;
    L_0x001c:
        r0 = new java.lang.IllegalStateException;
        r1 = "Must be called from main thread of process";
        r0.<init>(r1);
        throw r0;
    L_0x0024:
        r1 = r2;
    L_0x0025:
        monitor-enter(r6);
        r3 = r6.f179c;	 Catch:{ all -> 0x0097 }
        if (r3 == 0) goto L_0x0032;
    L_0x002a:
        r3 = r6.f179c;	 Catch:{ all -> 0x0097 }
        r3 = r3.size();	 Catch:{ all -> 0x0097 }
        if (r3 != 0) goto L_0x005a;
    L_0x0032:
        monitor-exit(r6);	 Catch:{ all -> 0x0097 }
        r0 = r6.f198v;
        if (r0 == 0) goto L_0x00a5;
    L_0x0037:
        r3 = r2;
        r4 = r2;
    L_0x0039:
        r0 = r6.f182f;
        r0 = r0.size();
        if (r3 >= r0) goto L_0x009e;
    L_0x0041:
        r0 = r6.f182f;
        r0 = r0.get(r3);
        r0 = (android.support.v4.app.Fragment) r0;
        if (r0 == 0) goto L_0x0056;
    L_0x004b:
        r5 = r0.f35N;
        if (r5 == 0) goto L_0x0056;
    L_0x004f:
        r0 = r0.f35N;
        r0 = r0.m301a();
        r4 = r4 | r0;
    L_0x0056:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0039;
    L_0x005a:
        r1 = r6.f179c;	 Catch:{ all -> 0x0097 }
        r3 = r1.size();	 Catch:{ all -> 0x0097 }
        r1 = r6.f180d;	 Catch:{ all -> 0x0097 }
        if (r1 == 0) goto L_0x0069;
    L_0x0064:
        r1 = r6.f180d;	 Catch:{ all -> 0x0097 }
        r1 = r1.length;	 Catch:{ all -> 0x0097 }
        if (r1 >= r3) goto L_0x006d;
    L_0x0069:
        r1 = new java.lang.Runnable[r3];	 Catch:{ all -> 0x0097 }
        r6.f180d = r1;	 Catch:{ all -> 0x0097 }
    L_0x006d:
        r1 = r6.f179c;	 Catch:{ all -> 0x0097 }
        r4 = r6.f180d;	 Catch:{ all -> 0x0097 }
        r1.toArray(r4);	 Catch:{ all -> 0x0097 }
        r1 = r6.f179c;	 Catch:{ all -> 0x0097 }
        r1.clear();	 Catch:{ all -> 0x0097 }
        r1 = r6.f191o;	 Catch:{ all -> 0x0097 }
        r1 = r1.f156a;	 Catch:{ all -> 0x0097 }
        r4 = r6.f201y;	 Catch:{ all -> 0x0097 }
        r1.removeCallbacks(r4);	 Catch:{ all -> 0x0097 }
        monitor-exit(r6);	 Catch:{ all -> 0x0097 }
        r6.f181e = r0;
        r1 = r2;
    L_0x0086:
        if (r1 >= r3) goto L_0x009a;
    L_0x0088:
        r4 = r6.f180d;
        r4 = r4[r1];
        r4.run();
        r4 = r6.f180d;
        r5 = 0;
        r4[r1] = r5;
        r1 = r1 + 1;
        goto L_0x0086;
    L_0x0097:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0097 }
        throw r0;
    L_0x009a:
        r6.f181e = r2;
        r1 = r0;
        goto L_0x0025;
    L_0x009e:
        if (r4 != 0) goto L_0x00a5;
    L_0x00a0:
        r6.f198v = r2;
        r6.m218d();
    L_0x00a5:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.i.e():boolean");
    }

    Bundle m224f(Fragment fragment) {
        Bundle bundle;
        if (this.f199w == null) {
            this.f199w = new Bundle();
        }
        fragment.m83j(this.f199w);
        if (this.f199w.isEmpty()) {
            bundle = null;
        } else {
            bundle = this.f199w;
            this.f199w = null;
        }
        if (fragment.f31J != null) {
            m221e(fragment);
        }
        if (fragment.f52f != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.f52f);
        }
        if (!fragment.f34M) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.f34M);
        }
        return bundle;
    }

    void m225f() {
        if (this.f189m != null) {
            for (int i = 0; i < this.f189m.size(); i++) {
                ((C0038a) this.f189m.get(i)).m175a();
            }
        }
    }

    ArrayList<Fragment> m226g() {
        ArrayList<Fragment> arrayList = null;
        if (this.f182f != null) {
            for (int i = 0; i < this.f182f.size(); i++) {
                Fragment fragment = (Fragment) this.f182f.get(i);
                if (fragment != null && fragment.f24C) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                    fragment.f25D = true;
                    fragment.f57k = fragment.f56j != null ? fragment.f56j.f53g : -1;
                    if (f176a) {
                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                    }
                }
            }
        }
        return arrayList;
    }

    Parcelable m227h() {
        BackStackState[] backStackStateArr = null;
        m223e();
        if (f177b) {
            this.f195s = true;
        }
        if (this.f182f == null || this.f182f.size() <= 0) {
            return null;
        }
        int size = this.f182f.size();
        FragmentState[] fragmentStateArr = new FragmentState[size];
        int i = 0;
        boolean z = false;
        while (i < size) {
            boolean z2;
            Fragment fragment = (Fragment) this.f182f.get(i);
            if (fragment != null) {
                if (fragment.f53g < 0) {
                    m181a(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.f53g));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                fragmentStateArr[i] = fragmentState;
                if (fragment.f48b <= 0 || fragmentState.f85j != null) {
                    fragmentState.f85j = fragment.f51e;
                } else {
                    fragmentState.f85j = m224f(fragment);
                    if (fragment.f56j != null) {
                        if (fragment.f56j.f53g < 0) {
                            m181a(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.f56j));
                        }
                        if (fragmentState.f85j == null) {
                            fragmentState.f85j = new Bundle();
                        }
                        m195a(fragmentState.f85j, "android:target_state", fragment.f56j);
                        if (fragment.f58l != 0) {
                            fragmentState.f85j.putInt("android:target_req_state", fragment.f58l);
                        }
                    }
                }
                if (f176a) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.f85j);
                }
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        if (z) {
            int[] iArr;
            int i2;
            FragmentManagerState fragmentManagerState;
            if (this.f183g != null) {
                i = this.f183g.size();
                if (i > 0) {
                    iArr = new int[i];
                    for (i2 = 0; i2 < i; i2++) {
                        iArr[i2] = ((Fragment) this.f183g.get(i2)).f53g;
                        if (iArr[i2] < 0) {
                            m181a(new IllegalStateException("Failure saving state: active " + this.f183g.get(i2) + " has cleared index: " + iArr[i2]));
                        }
                        if (f176a) {
                            Log.v("FragmentManager", "saveAllState: adding fragment #" + i2 + ": " + this.f183g.get(i2));
                        }
                    }
                    if (this.f185i != null) {
                        i = this.f185i.size();
                        if (i > 0) {
                            backStackStateArr = new BackStackState[i];
                            for (i2 = 0; i2 < i; i2++) {
                                backStackStateArr[i2] = new BackStackState(this, (C0033e) this.f185i.get(i2));
                                if (f176a) {
                                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f185i.get(i2));
                                }
                            }
                        }
                    }
                    fragmentManagerState = new FragmentManagerState();
                    fragmentManagerState.f73a = fragmentStateArr;
                    fragmentManagerState.f74b = iArr;
                    fragmentManagerState.f75c = backStackStateArr;
                    return fragmentManagerState;
                }
            }
            iArr = null;
            if (this.f185i != null) {
                i = this.f185i.size();
                if (i > 0) {
                    backStackStateArr = new BackStackState[i];
                    for (i2 = 0; i2 < i; i2++) {
                        backStackStateArr[i2] = new BackStackState(this, (C0033e) this.f185i.get(i2));
                        if (f176a) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f185i.get(i2));
                        }
                    }
                }
            }
            fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.f73a = fragmentStateArr;
            fragmentManagerState.f74b = iArr;
            fragmentManagerState.f75c = backStackStateArr;
            return fragmentManagerState;
        } else if (!f176a) {
            return null;
        } else {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
    }

    public void m228i() {
        this.f195s = false;
    }

    public void m229j() {
        this.f195s = false;
        m193a(1, false);
    }

    public void m230k() {
        this.f195s = false;
        m193a(2, false);
    }

    public void m231l() {
        this.f195s = false;
        m193a(4, false);
    }

    public void m232m() {
        this.f195s = false;
        m193a(5, false);
    }

    public void m233n() {
        m193a(4, false);
    }

    public void m234o() {
        this.f195s = true;
        m193a(3, false);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View view = null;
        if (!"fragment".equals(str)) {
            return view;
        }
        String attributeValue = attributeSet.getAttributeValue(view, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0042a.f172a);
        String string = attributeValue == null ? obtainStyledAttributes.getString(0) : attributeValue;
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string2 = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.m31b(this.f191o, string)) {
            return view;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string2 == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + string);
        }
        Fragment fragment;
        if (resourceId != -1) {
            Fragment a = m186a(resourceId);
        } else {
            Object obj = view;
        }
        if (a == null && string2 != null) {
            a = m188a(string2);
        }
        if (a == null && id != -1) {
            a = m186a(id);
        }
        if (f176a) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + string + " existing=" + a);
        }
        if (a == null) {
            Fragment a2 = Fragment.m29a(context, string);
            a2.f62p = true;
            a2.f70x = resourceId != 0 ? resourceId : id;
            a2.f71y = id;
            a2.f72z = string2;
            a2.f63q = true;
            a2.f66t = this;
            a2.m46a(this.f191o, attributeSet, a2.f51e);
            m200a(a2, true);
            fragment = a2;
        } else if (a.f63q) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + string);
        } else {
            a.f63q = true;
            if (!a.f25D) {
                a.m46a(this.f191o, attributeSet, a.f51e);
            }
            fragment = a;
        }
        if (this.f190n >= 1 || !fragment.f62p) {
            m209b(fragment);
        } else {
            m199a(fragment, 1, 0, 0, false);
        }
        if (fragment.f31J == null) {
            throw new IllegalStateException("Fragment " + string + " did not create a view.");
        }
        if (resourceId != 0) {
            fragment.f31J.setId(resourceId);
        }
        if (fragment.f31J.getTag() == null) {
            fragment.f31J.setTag(string2);
        }
        return fragment.f31J;
    }

    public void m235p() {
        m193a(2, false);
    }

    public void m236q() {
        m193a(1, false);
    }

    public void m237r() {
        this.f196t = true;
        m223e();
        m193a(0, false);
        this.f191o = null;
        this.f192p = null;
        this.f193q = null;
    }

    public void m238s() {
        if (this.f183g != null) {
            for (int i = 0; i < this.f183g.size(); i++) {
                Fragment fragment = (Fragment) this.f183g.get(i);
                if (fragment != null) {
                    fragment.m34C();
                }
            }
        }
    }

    Factory m239t() {
        return this;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("FragmentManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        if (this.f193q != null) {
            C0099c.m445a(this.f193q, stringBuilder);
        } else {
            C0099c.m445a(this.f191o, stringBuilder);
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }
}
