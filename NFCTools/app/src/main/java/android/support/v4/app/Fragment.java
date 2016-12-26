package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.p007f.C0096g;
import android.support.v4.p007f.C0099c;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Fragment implements ComponentCallbacks, OnCreateContextMenuListener {
    static final Object f21a;
    private static final C0096g<String, Class<?>> aa;
    boolean f22A;
    boolean f23B;
    boolean f24C;
    boolean f25D;
    boolean f26E;
    boolean f27F;
    boolean f28G;
    int f29H;
    ViewGroup f30I;
    View f31J;
    View f32K;
    boolean f33L;
    boolean f34M;
    C0055n f35N;
    boolean f36O;
    boolean f37P;
    Object f38Q;
    Object f39R;
    Object f40S;
    Object f41T;
    Object f42U;
    Object f43V;
    Boolean f44W;
    Boolean f45X;
    C0062r f46Y;
    C0062r f47Z;
    int f48b;
    View f49c;
    int f50d;
    Bundle f51e;
    SparseArray<Parcelable> f52f;
    int f53g;
    String f54h;
    Bundle f55i;
    Fragment f56j;
    int f57k;
    int f58l;
    boolean f59m;
    boolean f60n;
    boolean f61o;
    boolean f62p;
    boolean f63q;
    boolean f64r;
    int f65s;
    C0043i f66t;
    C0037f f67u;
    C0043i f68v;
    Fragment f69w;
    int f70x;
    int f71y;
    String f72z;

    /* renamed from: android.support.v4.app.Fragment.1 */
    class C00141 implements C0013g {
        final /* synthetic */ Fragment f19a;

        C00141(Fragment fragment) {
            this.f19a = fragment;
        }

        public View m25a(int i) {
            if (this.f19a.f31J != null) {
                return this.f19a.f31J.findViewById(i);
            }
            throw new IllegalStateException("Fragment does not have a view");
        }

        public boolean m26a() {
            return this.f19a.f31J != null;
        }
    }

    public static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        final Bundle f20a;

        /* renamed from: android.support.v4.app.Fragment.SavedState.1 */
        static class C00151 implements Creator<SavedState> {
            C00151() {
            }

            public SavedState m27a(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            public SavedState[] m28a(int i) {
                return new SavedState[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m27a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m28a(i);
            }
        }

        static {
            CREATOR = new C00151();
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            this.f20a = parcel.readBundle();
            if (classLoader != null && this.f20a != null) {
                this.f20a.setClassLoader(classLoader);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.f20a);
        }
    }

    /* renamed from: android.support.v4.app.Fragment.a */
    public static class C0016a extends RuntimeException {
        public C0016a(String str, Exception exception) {
            super(str, exception);
        }
    }

    static {
        aa = new C0096g();
        f21a = new Object();
    }

    public Fragment() {
        this.f48b = 0;
        this.f53g = -1;
        this.f57k = -1;
        this.f27F = true;
        this.f34M = true;
        this.f38Q = null;
        this.f39R = f21a;
        this.f40S = null;
        this.f41T = f21a;
        this.f42U = null;
        this.f43V = f21a;
        this.f46Y = null;
        this.f47Z = null;
    }

    public static Fragment m29a(Context context, String str) {
        return m30a(context, str, null);
    }

    public static Fragment m30a(Context context, String str, Bundle bundle) {
        try {
            Class cls = (Class) aa.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                aa.put(str, cls);
            }
            Fragment fragment = (Fragment) cls.newInstance();
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.f55i = bundle;
            }
            return fragment;
        } catch (Exception e) {
            throw new C0016a("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e);
        } catch (Exception e2) {
            throw new C0016a("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e2);
        } catch (Exception e22) {
            throw new C0016a("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e22);
        }
    }

    static boolean m31b(Context context, String str) {
        try {
            Class cls = (Class) aa.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                aa.put(str, cls);
            }
            return Fragment.class.isAssignableFrom(cls);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    void m32A() {
        if (this.f68v != null) {
            this.f68v.m228i();
            this.f68v.m223e();
        }
        this.f28G = false;
        m80i();
        if (this.f28G) {
            if (this.f68v != null) {
                this.f68v.m231l();
            }
            if (this.f35N != null) {
                this.f35N.m307g();
                return;
            }
            return;
        }
        throw new C0063s("Fragment " + this + " did not call through to super.onStart()");
    }

    void m33B() {
        if (this.f68v != null) {
            this.f68v.m228i();
            this.f68v.m223e();
        }
        this.f28G = false;
        m82j();
        if (!this.f28G) {
            throw new C0063s("Fragment " + this + " did not call through to super.onResume()");
        } else if (this.f68v != null) {
            this.f68v.m232m();
            this.f68v.m223e();
        }
    }

    void m34C() {
        onLowMemory();
        if (this.f68v != null) {
            this.f68v.m238s();
        }
    }

    void m35D() {
        if (this.f68v != null) {
            this.f68v.m233n();
        }
        this.f28G = false;
        m84k();
        if (!this.f28G) {
            throw new C0063s("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    void m36E() {
        if (this.f68v != null) {
            this.f68v.m234o();
        }
        this.f28G = false;
        m85l();
        if (!this.f28G) {
            throw new C0063s("Fragment " + this + " did not call through to super.onStop()");
        }
    }

    void m37F() {
        if (this.f68v != null) {
            this.f68v.m235p();
        }
        if (this.f36O) {
            this.f36O = false;
            if (!this.f37P) {
                this.f37P = true;
                this.f35N = this.f67u.m169a(this.f54h, this.f36O, false);
            }
            if (this.f35N == null) {
                return;
            }
            if (this.f67u.f163h) {
                this.f35N.m304d();
            } else {
                this.f35N.m303c();
            }
        }
    }

    void m38G() {
        if (this.f68v != null) {
            this.f68v.m236q();
        }
        this.f28G = false;
        m86m();
        if (!this.f28G) {
            throw new C0063s("Fragment " + this + " did not call through to super.onDestroyView()");
        } else if (this.f35N != null) {
            this.f35N.m306f();
        }
    }

    void m39H() {
        if (this.f68v != null) {
            this.f68v.m237r();
        }
        this.f28G = false;
        m87n();
        if (!this.f28G) {
            throw new C0063s("Fragment " + this + " did not call through to super.onDestroy()");
        }
    }

    public View m40a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public Animation m41a(int i, boolean z, int i2) {
        return null;
    }

    public final String m42a(int i) {
        return m63c().getString(i);
    }

    public void m43a(int i, int i2, Intent intent) {
    }

    final void m44a(int i, Fragment fragment) {
        this.f53g = i;
        if (fragment != null) {
            this.f54h = fragment.f54h + ":" + this.f53g;
        } else {
            this.f54h = "android:fragment:" + this.f53g;
        }
    }

    public void m45a(Activity activity) {
        this.f28G = true;
    }

    public void m46a(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.f28G = true;
    }

    void m47a(Configuration configuration) {
        onConfigurationChanged(configuration);
        if (this.f68v != null) {
            this.f68v.m194a(configuration);
        }
    }

    final void m48a(Bundle bundle) {
        if (this.f52f != null) {
            this.f32K.restoreHierarchyState(this.f52f);
            this.f52f = null;
        }
        this.f28G = false;
        m74f(bundle);
        if (!this.f28G) {
            throw new C0063s("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public void m49a(Menu menu) {
    }

    public void m50a(Menu menu, MenuInflater menuInflater) {
    }

    public void m51a(View view, Bundle bundle) {
    }

    public void m52a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.f70x));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.f71y));
        printWriter.print(" mTag=");
        printWriter.println(this.f72z);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f48b);
        printWriter.print(" mIndex=");
        printWriter.print(this.f53g);
        printWriter.print(" mWho=");
        printWriter.print(this.f54h);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.f65s);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.f59m);
        printWriter.print(" mRemoving=");
        printWriter.print(this.f60n);
        printWriter.print(" mResumed=");
        printWriter.print(this.f61o);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.f62p);
        printWriter.print(" mInLayout=");
        printWriter.println(this.f63q);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.f22A);
        printWriter.print(" mDetached=");
        printWriter.print(this.f23B);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.f27F);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.f26E);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.f24C);
        printWriter.print(" mRetaining=");
        printWriter.print(this.f25D);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.f34M);
        if (this.f66t != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.f66t);
        }
        if (this.f67u != null) {
            printWriter.print(str);
            printWriter.print("mActivity=");
            printWriter.println(this.f67u);
        }
        if (this.f69w != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.f69w);
        }
        if (this.f55i != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f55i);
        }
        if (this.f51e != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f51e);
        }
        if (this.f52f != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f52f);
        }
        if (this.f56j != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.f56j);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.f58l);
        }
        if (this.f29H != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(this.f29H);
        }
        if (this.f30I != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.f30I);
        }
        if (this.f31J != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.f31J);
        }
        if (this.f32K != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.f31J);
        }
        if (this.f49c != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(this.f49c);
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(this.f50d);
        }
        if (this.f35N != null) {
            printWriter.print(str);
            printWriter.println("Loader Manager:");
            this.f35N.m300a(str + "  ", fileDescriptor, printWriter, strArr);
        }
        if (this.f68v != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.f68v + ":");
            this.f68v.m203a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    public void m53a(boolean z) {
    }

    final boolean m54a() {
        return this.f65s > 0;
    }

    public boolean m55a(MenuItem menuItem) {
        return false;
    }

    public final C0037f m56b() {
        return this.f67u;
    }

    View m57b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f68v != null) {
            this.f68v.m228i();
        }
        return m40a(layoutInflater, viewGroup, bundle);
    }

    public void m58b(Bundle bundle) {
        if (this.f53g >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        this.f55i = bundle;
    }

    public void m59b(Menu menu) {
    }

    public void m60b(boolean z) {
        if (this.f27F != z) {
            this.f27F = z;
            if (this.f26E && m73e() && !m77g()) {
                this.f67u.b_();
            }
        }
    }

    boolean m61b(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.f22A) {
            return false;
        }
        if (this.f26E && this.f27F) {
            z = true;
            m50a(menu, menuInflater);
        }
        return this.f68v != null ? z | this.f68v.m206a(menu, menuInflater) : z;
    }

    public boolean m62b(MenuItem menuItem) {
        return false;
    }

    public final Resources m63c() {
        if (this.f67u != null) {
            return this.f67u.getResources();
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public LayoutInflater m64c(Bundle bundle) {
        LayoutInflater cloneInContext = this.f67u.getLayoutInflater().cloneInContext(this.f67u);
        m68d();
        cloneInContext.setFactory(this.f68v.m239t());
        return cloneInContext;
    }

    public void m65c(boolean z) {
        if (!this.f34M && z && this.f48b < 4) {
            this.f66t.m197a(this);
        }
        this.f34M = z;
        this.f33L = !z;
    }

    boolean m66c(Menu menu) {
        boolean z = false;
        if (this.f22A) {
            return false;
        }
        if (this.f26E && this.f27F) {
            z = true;
            m49a(menu);
        }
        return this.f68v != null ? z | this.f68v.m205a(menu) : z;
    }

    boolean m67c(MenuItem menuItem) {
        if (!this.f22A) {
            if (this.f26E && this.f27F && m55a(menuItem)) {
                return true;
            }
            if (this.f68v != null && this.f68v.m207a(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public final C0039h m68d() {
        if (this.f68v == null) {
            m99z();
            if (this.f48b >= 5) {
                this.f68v.m232m();
            } else if (this.f48b >= 4) {
                this.f68v.m231l();
            } else if (this.f48b >= 2) {
                this.f68v.m230k();
            } else if (this.f48b >= 1) {
                this.f68v.m229j();
            }
        }
        return this.f68v;
    }

    public void m69d(Bundle bundle) {
        this.f28G = true;
    }

    void m70d(Menu menu) {
        if (!this.f22A) {
            if (this.f26E && this.f27F) {
                m59b(menu);
            }
            if (this.f68v != null) {
                this.f68v.m212b(menu);
            }
        }
    }

    boolean m71d(MenuItem menuItem) {
        if (!this.f22A) {
            if (m62b(menuItem)) {
                return true;
            }
            if (this.f68v != null && this.f68v.m214b(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void m72e(Bundle bundle) {
        this.f28G = true;
    }

    public final boolean m73e() {
        return this.f67u != null && this.f59m;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void m74f(Bundle bundle) {
        this.f28G = true;
    }

    public final boolean m75f() {
        return this.f23B;
    }

    public void m76g(Bundle bundle) {
    }

    public final boolean m77g() {
        return this.f22A;
    }

    public View m78h() {
        return this.f31J;
    }

    void m79h(Bundle bundle) {
        if (this.f68v != null) {
            this.f68v.m228i();
        }
        this.f28G = false;
        m69d(bundle);
        if (!this.f28G) {
            throw new C0063s("Fragment " + this + " did not call through to super.onCreate()");
        } else if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            if (parcelable != null) {
                if (this.f68v == null) {
                    m99z();
                }
                this.f68v.m196a(parcelable, null);
                this.f68v.m229j();
            }
        }
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void m80i() {
        this.f28G = true;
        if (!this.f36O) {
            this.f36O = true;
            if (!this.f37P) {
                this.f37P = true;
                this.f35N = this.f67u.m169a(this.f54h, this.f36O, false);
            }
            if (this.f35N != null) {
                this.f35N.m302b();
            }
        }
    }

    void m81i(Bundle bundle) {
        if (this.f68v != null) {
            this.f68v.m228i();
        }
        this.f28G = false;
        m72e(bundle);
        if (!this.f28G) {
            throw new C0063s("Fragment " + this + " did not call through to super.onActivityCreated()");
        } else if (this.f68v != null) {
            this.f68v.m230k();
        }
    }

    public void m82j() {
        this.f28G = true;
    }

    void m83j(Bundle bundle) {
        m76g(bundle);
        if (this.f68v != null) {
            Parcelable h = this.f68v.m227h();
            if (h != null) {
                bundle.putParcelable("android:support:fragments", h);
            }
        }
    }

    public void m84k() {
        this.f28G = true;
    }

    public void m85l() {
        this.f28G = true;
    }

    public void m86m() {
        this.f28G = true;
    }

    public void m87n() {
        this.f28G = true;
        if (!this.f37P) {
            this.f37P = true;
            this.f35N = this.f67u.m169a(this.f54h, this.f36O, false);
        }
        if (this.f35N != null) {
            this.f35N.m308h();
        }
    }

    void m88o() {
        this.f53g = -1;
        this.f54h = null;
        this.f59m = false;
        this.f60n = false;
        this.f61o = false;
        this.f62p = false;
        this.f63q = false;
        this.f64r = false;
        this.f65s = 0;
        this.f66t = null;
        this.f68v = null;
        this.f67u = null;
        this.f70x = 0;
        this.f71y = 0;
        this.f72z = null;
        this.f22A = false;
        this.f23B = false;
        this.f25D = false;
        this.f35N = null;
        this.f36O = false;
        this.f37P = false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.f28G = true;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        m56b().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onLowMemory() {
        this.f28G = true;
    }

    public void m89p() {
        this.f28G = true;
    }

    public void m90q() {
    }

    public Object m91r() {
        return this.f38Q;
    }

    public Object m92s() {
        return this.f39R == f21a ? m91r() : this.f39R;
    }

    public Object m93t() {
        return this.f40S;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        C0099c.m445a(this, stringBuilder);
        if (this.f53g >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.f53g);
        }
        if (this.f70x != 0) {
            stringBuilder.append(" id=0x");
            stringBuilder.append(Integer.toHexString(this.f70x));
        }
        if (this.f72z != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.f72z);
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public Object m94u() {
        return this.f41T == f21a ? m93t() : this.f41T;
    }

    public Object m95v() {
        return this.f42U;
    }

    public Object m96w() {
        return this.f43V == f21a ? m95v() : this.f43V;
    }

    public boolean m97x() {
        return this.f45X == null ? true : this.f45X.booleanValue();
    }

    public boolean m98y() {
        return this.f44W == null ? true : this.f44W.booleanValue();
    }

    void m99z() {
        this.f68v = new C0043i();
        this.f68v.m201a(this.f67u, new C00141(this), this);
    }
}
