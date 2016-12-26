package android.support.v4.app;

import android.os.Build.VERSION;
import android.support.v4.app.C0051l.C0026b;
import android.support.v4.app.C0051l.C0050a;
import android.support.v4.p007f.C0097a;
import android.support.v4.p007f.C0100d;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.wakdev.nfctools.C0628m.C0627j;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: android.support.v4.app.e */
final class C0033e extends C0032k implements Runnable {
    final C0043i f128a;
    C0030a f129b;
    C0030a f130c;
    int f131d;
    int f132e;
    int f133f;
    int f134g;
    int f135h;
    int f136i;
    int f137j;
    boolean f138k;
    boolean f139l;
    String f140m;
    boolean f141n;
    int f142o;
    int f143p;
    CharSequence f144q;
    int f145r;
    CharSequence f146s;
    ArrayList<String> f147t;
    ArrayList<String> f148u;

    /* renamed from: android.support.v4.app.e.1 */
    class C00271 implements C0026b {
        final /* synthetic */ Fragment f99a;
        final /* synthetic */ C0033e f100b;

        C00271(C0033e c0033e, Fragment fragment) {
            this.f100b = c0033e;
            this.f99a = fragment;
        }

        public View m119a() {
            return this.f99a.m78h();
        }
    }

    /* renamed from: android.support.v4.app.e.2 */
    class C00282 implements OnPreDrawListener {
        final /* synthetic */ View f101a;
        final /* synthetic */ Object f102b;
        final /* synthetic */ ArrayList f103c;
        final /* synthetic */ C0031b f104d;
        final /* synthetic */ boolean f105e;
        final /* synthetic */ Fragment f106f;
        final /* synthetic */ Fragment f107g;
        final /* synthetic */ C0033e f108h;

        C00282(C0033e c0033e, View view, Object obj, ArrayList arrayList, C0031b c0031b, boolean z, Fragment fragment, Fragment fragment2) {
            this.f108h = c0033e;
            this.f101a = view;
            this.f102b = obj;
            this.f103c = arrayList;
            this.f104d = c0031b;
            this.f105e = z;
            this.f106f = fragment;
            this.f107g = fragment2;
        }

        public boolean onPreDraw() {
            this.f101a.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.f102b != null) {
                C0051l.m280a(this.f102b, this.f103c);
                this.f103c.clear();
                C0097a a = this.f108h.m127a(this.f104d, this.f105e, this.f106f);
                if (a.isEmpty()) {
                    this.f103c.add(this.f104d.f126d);
                } else {
                    this.f103c.addAll(a.values());
                }
                C0051l.m284b(this.f102b, this.f103c);
                this.f108h.m142a(a, this.f104d);
                this.f108h.m135a(this.f104d, this.f106f, this.f107g, this.f105e, a);
            }
            return true;
        }
    }

    /* renamed from: android.support.v4.app.e.3 */
    class C00293 implements OnPreDrawListener {
        final /* synthetic */ View f109a;
        final /* synthetic */ C0031b f110b;
        final /* synthetic */ int f111c;
        final /* synthetic */ Object f112d;
        final /* synthetic */ C0033e f113e;

        C00293(C0033e c0033e, View view, C0031b c0031b, int i, Object obj) {
            this.f113e = c0033e;
            this.f109a = view;
            this.f110b = c0031b;
            this.f111c = i;
            this.f112d = obj;
        }

        public boolean onPreDraw() {
            this.f109a.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f113e.m134a(this.f110b, this.f111c, this.f112d);
            return true;
        }
    }

    /* renamed from: android.support.v4.app.e.a */
    static final class C0030a {
        C0030a f114a;
        C0030a f115b;
        int f116c;
        Fragment f117d;
        int f118e;
        int f119f;
        int f120g;
        int f121h;
        ArrayList<Fragment> f122i;

        C0030a() {
        }
    }

    /* renamed from: android.support.v4.app.e.b */
    public class C0031b {
        public C0097a<String, String> f123a;
        public ArrayList<View> f124b;
        public C0050a f125c;
        public View f126d;
        final /* synthetic */ C0033e f127e;

        public C0031b(C0033e c0033e) {
            this.f127e = c0033e;
            this.f123a = new C0097a();
            this.f124b = new ArrayList();
            this.f125c = new C0050a();
        }
    }

    public C0033e(C0043i c0043i) {
        this.f139l = true;
        this.f142o = -1;
        this.f128a = c0043i;
    }

    private C0031b m125a(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2, boolean z) {
        int i = 0;
        C0031b c0031b = new C0031b(this);
        c0031b.f126d = new View(this.f128a.f191o);
        int i2 = 0;
        int i3 = 0;
        while (i2 < sparseArray.size()) {
            int i4 = m146a(sparseArray.keyAt(i2), c0031b, z, (SparseArray) sparseArray, (SparseArray) sparseArray2) ? 1 : i3;
            i2++;
            i3 = i4;
        }
        while (i < sparseArray2.size()) {
            i4 = sparseArray2.keyAt(i);
            if (sparseArray.get(i4) == null && m146a(i4, c0031b, z, (SparseArray) sparseArray, (SparseArray) sparseArray2)) {
                i3 = 1;
            }
            i++;
        }
        return i3 == 0 ? null : c0031b;
    }

    private C0097a<String, View> m126a(C0031b c0031b, Fragment fragment, boolean z) {
        C0097a c0097a = new C0097a();
        if (this.f147t != null) {
            C0051l.m282a((Map) c0097a, fragment.m78h());
            if (z) {
                c0097a.m440a(this.f148u);
            } else {
                c0097a = C0033e.m129a(this.f147t, this.f148u, c0097a);
            }
        }
        if (z) {
            if (fragment.f46Y != null) {
                fragment.f46Y.m334a(this.f148u, c0097a);
            }
            m136a(c0031b, c0097a, false);
        } else {
            if (fragment.f47Z != null) {
                fragment.f47Z.m334a(this.f148u, c0097a);
            }
            m149b(c0031b, c0097a, false);
        }
        return c0097a;
    }

    private C0097a<String, View> m127a(C0031b c0031b, boolean z, Fragment fragment) {
        C0097a b = m147b(c0031b, fragment, z);
        if (z) {
            if (fragment.f47Z != null) {
                fragment.f47Z.m334a(this.f148u, b);
            }
            m136a(c0031b, b, true);
        } else {
            if (fragment.f46Y != null) {
                fragment.f46Y.m334a(this.f148u, b);
            }
            m149b(c0031b, b, true);
        }
        return b;
    }

    private static C0097a<String, View> m129a(ArrayList<String> arrayList, ArrayList<String> arrayList2, C0097a<String, View> c0097a) {
        if (c0097a.isEmpty()) {
            return c0097a;
        }
        C0097a<String, View> c0097a2 = new C0097a();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = (View) c0097a.get(arrayList.get(i));
            if (view != null) {
                c0097a2.put(arrayList2.get(i), view);
            }
        }
        return c0097a2;
    }

    private static Object m130a(Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return C0051l.m270a(z ? fragment2.m96w() : fragment.m95v());
    }

    private static Object m131a(Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return C0051l.m270a(z ? fragment.m94u() : fragment.m91r());
    }

    private static Object m132a(Object obj, Fragment fragment, ArrayList<View> arrayList, C0097a<String, View> c0097a) {
        return obj != null ? C0051l.m271a(obj, fragment.m78h(), (ArrayList) arrayList, (Map) c0097a) : obj;
    }

    private void m133a(int i, Fragment fragment, String str, int i2) {
        fragment.f66t = this.f128a;
        if (str != null) {
            if (fragment.f72z == null || str.equals(fragment.f72z)) {
                fragment.f72z = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.f72z + " now " + str);
            }
        }
        if (i != 0) {
            if (fragment.f70x == 0 || fragment.f70x == i) {
                fragment.f70x = i;
                fragment.f71y = i;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.f70x + " now " + i);
            }
        }
        C0030a c0030a = new C0030a();
        c0030a.f116c = i2;
        c0030a.f117d = fragment;
        m158a(c0030a);
    }

    private void m134a(C0031b c0031b, int i, Object obj) {
        if (this.f128a.f183g != null) {
            for (int i2 = 0; i2 < this.f128a.f183g.size(); i2++) {
                Fragment fragment = (Fragment) this.f128a.f183g.get(i2);
                if (!(fragment.f31J == null || fragment.f30I == null || fragment.f71y != i)) {
                    if (!fragment.f22A) {
                        C0051l.m278a(obj, fragment.f31J, false);
                        c0031b.f124b.remove(fragment.f31J);
                    } else if (!c0031b.f124b.contains(fragment.f31J)) {
                        C0051l.m278a(obj, fragment.f31J, true);
                        c0031b.f124b.add(fragment.f31J);
                    }
                }
            }
        }
    }

    private void m135a(C0031b c0031b, Fragment fragment, Fragment fragment2, boolean z, C0097a<String, View> c0097a) {
        C0062r c0062r = z ? fragment2.f46Y : fragment.f46Y;
        if (c0062r != null) {
            c0062r.m335b(new ArrayList(c0097a.keySet()), new ArrayList(c0097a.values()), null);
        }
    }

    private void m136a(C0031b c0031b, C0097a<String, View> c0097a, boolean z) {
        int size = this.f148u == null ? 0 : this.f148u.size();
        for (int i = 0; i < size; i++) {
            String str = (String) this.f147t.get(i);
            View view = (View) c0097a.get((String) this.f148u.get(i));
            if (view != null) {
                String a = C0051l.m273a(view);
                if (z) {
                    C0033e.m143a(c0031b.f123a, str, a);
                } else {
                    C0033e.m143a(c0031b.f123a, a, str);
                }
            }
        }
    }

    private void m137a(C0031b c0031b, View view, Object obj, Fragment fragment, Fragment fragment2, boolean z, ArrayList<View> arrayList) {
        view.getViewTreeObserver().addOnPreDrawListener(new C00282(this, view, obj, arrayList, c0031b, z, fragment, fragment2));
    }

    private static void m138a(C0031b c0031b, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                C0033e.m143a(c0031b.f123a, (String) arrayList.get(i), (String) arrayList2.get(i));
            }
        }
    }

    private void m142a(C0097a<String, View> c0097a, C0031b c0031b) {
        if (this.f148u != null && !c0097a.isEmpty()) {
            View view = (View) c0097a.get(this.f148u.get(0));
            if (view != null) {
                c0031b.f125c.f226a = view;
            }
        }
    }

    private static void m143a(C0097a<String, String> c0097a, String str, String str2) {
        if (str != null && str2 != null && !str.equals(str2)) {
            for (int i = 0; i < c0097a.size(); i++) {
                if (str.equals(c0097a.m437c(i))) {
                    c0097a.m433a(i, (Object) str2);
                    return;
                }
            }
            c0097a.put(str, str2);
        }
    }

    private static void m144a(SparseArray<Fragment> sparseArray, Fragment fragment) {
        if (fragment != null) {
            int i = fragment.f71y;
            if (i != 0 && !fragment.m77g() && fragment.m73e() && fragment.m78h() != null && sparseArray.get(i) == null) {
                sparseArray.put(i, fragment);
            }
        }
    }

    private void m145a(View view, C0031b c0031b, int i, Object obj) {
        view.getViewTreeObserver().addOnPreDrawListener(new C00293(this, view, c0031b, i, obj));
    }

    private boolean m146a(int i, C0031b c0031b, boolean z, SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        View view = (ViewGroup) this.f128a.f192p.m23a(i);
        if (view == null) {
            return false;
        }
        Fragment fragment = (Fragment) sparseArray2.get(i);
        Fragment fragment2 = (Fragment) sparseArray.get(i);
        Object a = C0033e.m131a(fragment, z);
        Object a2 = C0033e.m130a(fragment, fragment2, z);
        Object b = C0033e.m148b(fragment2, z);
        if (a == null && a2 == null && b == null) {
            return false;
        }
        C0097a c0097a = null;
        ArrayList arrayList = new ArrayList();
        if (a2 != null) {
            c0097a = m126a(c0031b, fragment2, z);
            if (c0097a.isEmpty()) {
                arrayList.add(c0031b.f126d);
            } else {
                arrayList.addAll(c0097a.values());
            }
            C0062r c0062r = z ? fragment2.f46Y : fragment.f46Y;
            if (c0062r != null) {
                c0062r.m333a(new ArrayList(c0097a.keySet()), new ArrayList(c0097a.values()), null);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Object a3 = C0033e.m132a(b, fragment2, arrayList2, c0097a);
        if (!(this.f148u == null || c0097a == null)) {
            View view2 = (View) c0097a.get(this.f148u.get(0));
            if (view2 != null) {
                if (a3 != null) {
                    C0051l.m277a(a3, view2);
                }
                if (a2 != null) {
                    C0051l.m277a(a2, view2);
                }
            }
        }
        C0026b c00271 = new C00271(this, fragment);
        if (a2 != null) {
            m137a(c0031b, view, a2, fragment, fragment2, z, arrayList);
        }
        ArrayList arrayList3 = new ArrayList();
        Map c0097a2 = new C0097a();
        Object a4 = C0051l.m272a(a, a3, a2, z ? fragment.m98y() : fragment.m97x());
        if (a4 != null) {
            C0051l.m279a(a, a2, view, c00271, c0031b.f126d, c0031b.f125c, c0031b.f123a, arrayList3, c0097a2, arrayList);
            m145a(view, c0031b, i, a4);
            C0051l.m278a(a4, c0031b.f126d, true);
            m134a(c0031b, i, a4);
            C0051l.m276a((ViewGroup) view, a4);
            C0051l.m275a(view, c0031b.f126d, a, arrayList3, a3, arrayList2, a2, arrayList, a4, c0031b.f124b, c0097a2);
        }
        return a4 != null;
    }

    private C0097a<String, View> m147b(C0031b c0031b, Fragment fragment, boolean z) {
        C0097a c0097a = new C0097a();
        View h = fragment.m78h();
        if (h == null || this.f147t == null) {
            return c0097a;
        }
        C0051l.m282a((Map) c0097a, h);
        if (z) {
            return C0033e.m129a(this.f147t, this.f148u, c0097a);
        }
        c0097a.m440a(this.f148u);
        return c0097a;
    }

    private static Object m148b(Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return C0051l.m270a(z ? fragment.m92s() : fragment.m93t());
    }

    private void m149b(C0031b c0031b, C0097a<String, View> c0097a, boolean z) {
        int size = c0097a.size();
        for (int i = 0; i < size; i++) {
            String str = (String) c0097a.m436b(i);
            String a = C0051l.m273a((View) c0097a.m437c(i));
            if (z) {
                C0033e.m143a(c0031b.f123a, str, a);
            } else {
                C0033e.m143a(c0031b.f123a, a, str);
            }
        }
    }

    private void m150b(SparseArray<Fragment> sparseArray, Fragment fragment) {
        if (fragment != null) {
            int i = fragment.f71y;
            if (i != 0) {
                sparseArray.put(i, fragment);
            }
        }
    }

    private void m151b(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (this.f128a.f192p.m24a()) {
            for (C0030a c0030a = this.f129b; c0030a != null; c0030a = c0030a.f114a) {
                switch (c0030a.f116c) {
                    case C0627j.View_paddingStart /*1*/:
                        m150b((SparseArray) sparseArray2, c0030a.f117d);
                        break;
                    case C0627j.View_paddingEnd /*2*/:
                        Fragment fragment;
                        Fragment fragment2 = c0030a.f117d;
                        if (this.f128a.f183g != null) {
                            fragment = fragment2;
                            for (int i = 0; i < this.f128a.f183g.size(); i++) {
                                Fragment fragment3 = (Fragment) this.f128a.f183g.get(i);
                                if (fragment == null || fragment3.f71y == fragment.f71y) {
                                    if (fragment3 == fragment) {
                                        fragment = null;
                                    } else {
                                        C0033e.m144a((SparseArray) sparseArray, fragment3);
                                    }
                                }
                            }
                        } else {
                            fragment = fragment2;
                        }
                        m150b((SparseArray) sparseArray2, fragment);
                        break;
                    case C0627j.Toolbar_subtitle /*3*/:
                        C0033e.m144a((SparseArray) sparseArray, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        C0033e.m144a((SparseArray) sparseArray, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_contentInsetEnd /*5*/:
                        m150b((SparseArray) sparseArray2, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_contentInsetLeft /*6*/:
                        C0033e.m144a((SparseArray) sparseArray, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_contentInsetRight /*7*/:
                        m150b((SparseArray) sparseArray2, c0030a.f117d);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public int m152a() {
        return m153a(false);
    }

    int m153a(boolean z) {
        if (this.f141n) {
            throw new IllegalStateException("commit already called");
        }
        if (C0043i.f176a) {
            Log.v("FragmentManager", "Commit: " + this);
            m160a("  ", null, new PrintWriter(new C0100d("FragmentManager")), null);
        }
        this.f141n = true;
        if (this.f138k) {
            this.f142o = this.f128a.m185a(this);
        } else {
            this.f142o = -1;
        }
        this.f128a.m202a((Runnable) this, z);
        return this.f142o;
    }

    public C0031b m154a(boolean z, C0031b c0031b, SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (C0043i.f176a) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            m160a("  ", null, new PrintWriter(new C0100d("FragmentManager")), null);
        }
        if (c0031b == null) {
            if (!(sparseArray.size() == 0 && sparseArray2.size() == 0)) {
                c0031b = m125a((SparseArray) sparseArray, (SparseArray) sparseArray2, true);
            }
        } else if (!z) {
            C0033e.m138a(c0031b, this.f148u, this.f147t);
        }
        m157a(-1);
        int i = c0031b != null ? 0 : this.f137j;
        int i2 = c0031b != null ? 0 : this.f136i;
        C0030a c0030a = this.f130c;
        while (c0030a != null) {
            int i3 = c0031b != null ? 0 : c0030a.f120g;
            int i4 = c0031b != null ? 0 : c0030a.f121h;
            Fragment fragment;
            Fragment fragment2;
            switch (c0030a.f116c) {
                case C0627j.View_paddingStart /*1*/:
                    fragment = c0030a.f117d;
                    fragment.f29H = i4;
                    this.f128a.m198a(fragment, C0043i.m183c(i2), i);
                    break;
                case C0627j.View_paddingEnd /*2*/:
                    fragment = c0030a.f117d;
                    if (fragment != null) {
                        fragment.f29H = i4;
                        this.f128a.m198a(fragment, C0043i.m183c(i2), i);
                    }
                    if (c0030a.f122i == null) {
                        break;
                    }
                    for (int i5 = 0; i5 < c0030a.f122i.size(); i5++) {
                        fragment2 = (Fragment) c0030a.f122i.get(i5);
                        fragment2.f29H = i3;
                        this.f128a.m200a(fragment2, false);
                    }
                    break;
                case C0627j.Toolbar_subtitle /*3*/:
                    fragment2 = c0030a.f117d;
                    fragment2.f29H = i3;
                    this.f128a.m200a(fragment2, false);
                    break;
                case C0627j.Toolbar_contentInsetStart /*4*/:
                    fragment2 = c0030a.f117d;
                    fragment2.f29H = i3;
                    this.f128a.m216c(fragment2, C0043i.m183c(i2), i);
                    break;
                case C0627j.Toolbar_contentInsetEnd /*5*/:
                    fragment = c0030a.f117d;
                    fragment.f29H = i4;
                    this.f128a.m210b(fragment, C0043i.m183c(i2), i);
                    break;
                case C0627j.Toolbar_contentInsetLeft /*6*/:
                    fragment2 = c0030a.f117d;
                    fragment2.f29H = i3;
                    this.f128a.m222e(fragment2, C0043i.m183c(i2), i);
                    break;
                case C0627j.Toolbar_contentInsetRight /*7*/:
                    fragment2 = c0030a.f117d;
                    fragment2.f29H = i3;
                    this.f128a.m220d(fragment2, C0043i.m183c(i2), i);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + c0030a.f116c);
            }
            c0030a = c0030a.f115b;
        }
        if (z) {
            this.f128a.m191a(this.f128a.f190n, C0043i.m183c(i2), i, true);
            c0031b = null;
        }
        if (this.f142o >= 0) {
            this.f128a.m208b(this.f142o);
            this.f142o = -1;
        }
        return c0031b;
    }

    public C0032k m155a(int i, Fragment fragment, String str) {
        m133a(i, fragment, str, 1);
        return this;
    }

    public C0032k m156a(Fragment fragment) {
        C0030a c0030a = new C0030a();
        c0030a.f116c = 6;
        c0030a.f117d = fragment;
        m158a(c0030a);
        return this;
    }

    void m157a(int i) {
        if (this.f138k) {
            if (C0043i.f176a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            for (C0030a c0030a = this.f129b; c0030a != null; c0030a = c0030a.f114a) {
                Fragment fragment;
                if (c0030a.f117d != null) {
                    fragment = c0030a.f117d;
                    fragment.f65s += i;
                    if (C0043i.f176a) {
                        Log.v("FragmentManager", "Bump nesting of " + c0030a.f117d + " to " + c0030a.f117d.f65s);
                    }
                }
                if (c0030a.f122i != null) {
                    for (int size = c0030a.f122i.size() - 1; size >= 0; size--) {
                        fragment = (Fragment) c0030a.f122i.get(size);
                        fragment.f65s += i;
                        if (C0043i.f176a) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.f65s);
                        }
                    }
                }
            }
        }
    }

    void m158a(C0030a c0030a) {
        if (this.f129b == null) {
            this.f130c = c0030a;
            this.f129b = c0030a;
        } else {
            c0030a.f115b = this.f130c;
            this.f130c.f114a = c0030a;
            this.f130c = c0030a;
        }
        c0030a.f118e = this.f132e;
        c0030a.f119f = this.f133f;
        c0030a.f120g = this.f134g;
        c0030a.f121h = this.f135h;
        this.f131d++;
    }

    public void m159a(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (this.f128a.f192p.m24a()) {
            for (C0030a c0030a = this.f129b; c0030a != null; c0030a = c0030a.f114a) {
                switch (c0030a.f116c) {
                    case C0627j.View_paddingStart /*1*/:
                        C0033e.m144a((SparseArray) sparseArray, c0030a.f117d);
                        break;
                    case C0627j.View_paddingEnd /*2*/:
                        if (c0030a.f122i != null) {
                            for (int size = c0030a.f122i.size() - 1; size >= 0; size--) {
                                m150b((SparseArray) sparseArray2, (Fragment) c0030a.f122i.get(size));
                            }
                        }
                        C0033e.m144a((SparseArray) sparseArray, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_subtitle /*3*/:
                        m150b((SparseArray) sparseArray2, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        m150b((SparseArray) sparseArray2, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_contentInsetEnd /*5*/:
                        C0033e.m144a((SparseArray) sparseArray, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_contentInsetLeft /*6*/:
                        m150b((SparseArray) sparseArray2, c0030a.f117d);
                        break;
                    case C0627j.Toolbar_contentInsetRight /*7*/:
                        C0033e.m144a((SparseArray) sparseArray, c0030a.f117d);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void m160a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        m161a(str, printWriter, true);
    }

    public void m161a(String str, PrintWriter printWriter, boolean z) {
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f140m);
            printWriter.print(" mIndex=");
            printWriter.print(this.f142o);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f141n);
            if (this.f136i != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f136i));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.f137j));
            }
            if (!(this.f132e == 0 && this.f133f == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f132e));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f133f));
            }
            if (!(this.f134g == 0 && this.f135h == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f134g));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f135h));
            }
            if (!(this.f143p == 0 && this.f144q == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f143p));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f144q);
            }
            if (!(this.f145r == 0 && this.f146s == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f145r));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f146s);
            }
        }
        if (this.f129b != null) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str2 = str + "    ";
            int i = 0;
            C0030a c0030a = this.f129b;
            while (c0030a != null) {
                String str3;
                switch (c0030a.f116c) {
                    case C0627j.View_android_focusable /*0*/:
                        str3 = "NULL";
                        break;
                    case C0627j.View_paddingStart /*1*/:
                        str3 = "ADD";
                        break;
                    case C0627j.View_paddingEnd /*2*/:
                        str3 = "REPLACE";
                        break;
                    case C0627j.Toolbar_subtitle /*3*/:
                        str3 = "REMOVE";
                        break;
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        str3 = "HIDE";
                        break;
                    case C0627j.Toolbar_contentInsetEnd /*5*/:
                        str3 = "SHOW";
                        break;
                    case C0627j.Toolbar_contentInsetLeft /*6*/:
                        str3 = "DETACH";
                        break;
                    case C0627j.Toolbar_contentInsetRight /*7*/:
                        str3 = "ATTACH";
                        break;
                    default:
                        str3 = "cmd=" + c0030a.f116c;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str3);
                printWriter.print(" ");
                printWriter.println(c0030a.f117d);
                if (z) {
                    if (!(c0030a.f118e == 0 && c0030a.f119f == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(c0030a.f118e));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(c0030a.f119f));
                    }
                    if (!(c0030a.f120g == 0 && c0030a.f121h == 0)) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(c0030a.f120g));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(c0030a.f121h));
                    }
                }
                if (c0030a.f122i != null && c0030a.f122i.size() > 0) {
                    for (int i2 = 0; i2 < c0030a.f122i.size(); i2++) {
                        printWriter.print(str2);
                        if (c0030a.f122i.size() == 1) {
                            printWriter.print("Removed: ");
                        } else {
                            if (i2 == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(str2);
                            printWriter.print("  #");
                            printWriter.print(i2);
                            printWriter.print(": ");
                        }
                        printWriter.println(c0030a.f122i.get(i2));
                    }
                }
                c0030a = c0030a.f114a;
                i++;
            }
        }
    }

    public int m162b() {
        return m153a(true);
    }

    public C0032k m163b(Fragment fragment) {
        C0030a c0030a = new C0030a();
        c0030a.f116c = 7;
        c0030a.f117d = fragment;
        m158a(c0030a);
        return this;
    }

    public String m164c() {
        return this.f140m;
    }

    public void run() {
        if (C0043i.f176a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (!this.f138k || this.f142o >= 0) {
            C0031b a;
            m157a(1);
            if (VERSION.SDK_INT >= 21) {
                SparseArray sparseArray = new SparseArray();
                SparseArray sparseArray2 = new SparseArray();
                m151b(sparseArray, sparseArray2);
                a = m125a(sparseArray, sparseArray2, false);
            } else {
                Object obj = null;
            }
            int i = a != null ? 0 : this.f137j;
            int i2 = a != null ? 0 : this.f136i;
            C0030a c0030a = this.f129b;
            while (c0030a != null) {
                int i3 = a != null ? 0 : c0030a.f118e;
                int i4 = a != null ? 0 : c0030a.f119f;
                Fragment fragment;
                switch (c0030a.f116c) {
                    case C0627j.View_paddingStart /*1*/:
                        fragment = c0030a.f117d;
                        fragment.f29H = i3;
                        this.f128a.m200a(fragment, false);
                        break;
                    case C0627j.View_paddingEnd /*2*/:
                        Fragment fragment2;
                        fragment = c0030a.f117d;
                        if (this.f128a.f183g != null) {
                            fragment2 = fragment;
                            for (int i5 = 0; i5 < this.f128a.f183g.size(); i5++) {
                                fragment = (Fragment) this.f128a.f183g.get(i5);
                                if (C0043i.f176a) {
                                    Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment);
                                }
                                if (fragment2 == null || fragment.f71y == fragment2.f71y) {
                                    if (fragment == fragment2) {
                                        c0030a.f117d = null;
                                        fragment2 = null;
                                    } else {
                                        if (c0030a.f122i == null) {
                                            c0030a.f122i = new ArrayList();
                                        }
                                        c0030a.f122i.add(fragment);
                                        fragment.f29H = i4;
                                        if (this.f138k) {
                                            fragment.f65s++;
                                            if (C0043i.f176a) {
                                                Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.f65s);
                                            }
                                        }
                                        this.f128a.m198a(fragment, i2, i);
                                    }
                                }
                            }
                        } else {
                            fragment2 = fragment;
                        }
                        if (fragment2 == null) {
                            break;
                        }
                        fragment2.f29H = i3;
                        this.f128a.m200a(fragment2, false);
                        break;
                    case C0627j.Toolbar_subtitle /*3*/:
                        fragment = c0030a.f117d;
                        fragment.f29H = i4;
                        this.f128a.m198a(fragment, i2, i);
                        break;
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        fragment = c0030a.f117d;
                        fragment.f29H = i4;
                        this.f128a.m210b(fragment, i2, i);
                        break;
                    case C0627j.Toolbar_contentInsetEnd /*5*/:
                        fragment = c0030a.f117d;
                        fragment.f29H = i3;
                        this.f128a.m216c(fragment, i2, i);
                        break;
                    case C0627j.Toolbar_contentInsetLeft /*6*/:
                        fragment = c0030a.f117d;
                        fragment.f29H = i4;
                        this.f128a.m220d(fragment, i2, i);
                        break;
                    case C0627j.Toolbar_contentInsetRight /*7*/:
                        fragment = c0030a.f117d;
                        fragment.f29H = i3;
                        this.f128a.m222e(fragment, i2, i);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + c0030a.f116c);
                }
                c0030a = c0030a.f114a;
            }
            this.f128a.m191a(this.f128a.f190n, i2, i, true);
            if (this.f138k) {
                this.f128a.m211b(this);
                return;
            }
            return;
        }
        throw new IllegalStateException("addToBackStack() called after commit()");
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("BackStackEntry{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f142o >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.f142o);
        }
        if (this.f140m != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.f140m);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
