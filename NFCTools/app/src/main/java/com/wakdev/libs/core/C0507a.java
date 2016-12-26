package com.wakdev.libs.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import com.wakdev.libs.commons.C0488c;
import com.wakdev.libs.p015a.C0479k;
import com.wakdev.p014a.C0446a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

@SuppressLint({"NewApi"})
/* renamed from: com.wakdev.libs.core.a */
public class C0507a implements Serializable {
    private static C0507a f1145a;
    private ArrayList<C0446a> f1146b;
    private ArrayList<C0446a> f1147c;
    private ArrayList<C0446a> f1148d;
    private int f1149e;
    private int f1150f;
    private int f1151g;
    private boolean f1152h;
    private int f1153i;
    private int f1154j;
    private boolean f1155k;
    private SharedPreferences f1156l;
    private transient HashMap<String, C0479k> f1157m;
    private boolean f1158n;

    private C0507a() {
        this.f1146b = new ArrayList();
        this.f1147c = new ArrayList();
        this.f1148d = new ArrayList();
        this.f1149e = 0;
        this.f1150f = 1;
        this.f1151g = 0;
        this.f1152h = false;
        this.f1153i = 0;
        this.f1154j = "com.wakdev.nfctasks".length() + 18;
        this.f1155k = false;
        this.f1157m = new HashMap();
        this.f1158n = false;
    }

    public static synchronized C0507a m2175a() {
        C0507a c0507a;
        synchronized (C0507a.class) {
            if (f1145a == null) {
                f1145a = new C0507a();
            }
            c0507a = f1145a;
        }
        return c0507a;
    }

    public static int m2176g() {
        return 1;
    }

    public static boolean m2177n() {
        return "samsung".equalsIgnoreCase(Build.MANUFACTURER) && VERSION.RELEASE.startsWith("4.2.2");
    }

    public int m2178a(Context context) {
        if (m2214m()) {
            return -1;
        }
        this.f1156l = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return this.f1156l.getInt("keyPreferenceOrientation", 1);
    }

    public C0446a m2179a(String str) {
        if (str != null) {
            Iterator it = this.f1147c.iterator();
            while (it.hasNext()) {
                C0446a c0446a = (C0446a) it.next();
                if (str.equals(c0446a.m1820i())) {
                    return c0446a;
                }
            }
        }
        return null;
    }

    public void m2180a(int i) {
        this.f1151g = i;
    }

    public void m2181a(C0446a c0446a) {
        this.f1146b.add(c0446a);
    }

    public void m2182a(C0446a c0446a, C0479k c0479k) {
        this.f1146b.add(c0446a);
        m2184a(c0446a.m1820i(), c0479k, false, false);
    }

    public void m2183a(String str, C0446a c0446a, C0479k c0479k) {
        Iterator it = this.f1147c.iterator();
        while (it.hasNext()) {
            C0446a c0446a2 = (C0446a) it.next();
            if (str.equals(c0446a2.m1820i())) {
                c0446a.m1815d(str);
                this.f1147c.set(this.f1147c.indexOf(c0446a2), c0446a);
                m2193b(str, c0479k, true, false);
                return;
            }
        }
    }

    public void m2184a(String str, C0479k c0479k, boolean z, boolean z2) {
        if (z) {
            this.f1153i += c0479k.m2013f();
        }
        if (z2) {
            this.f1154j += c0479k.m2013f();
        }
        this.f1157m.put(str, c0479k);
    }

    public void m2185a(String str, boolean z, boolean z2) {
        C0479k c0479k = (C0479k) this.f1157m.get(str);
        if (c0479k != null) {
            if (z) {
                this.f1153i -= c0479k.m2013f();
            }
            if (z2) {
                this.f1154j -= c0479k.m2013f();
            }
            this.f1157m.remove(str);
        }
    }

    public void m2186a(boolean z) {
        this.f1158n = z;
    }

    public int m2187b(Context context) {
        this.f1156l = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return this.f1156l.getInt("keyPreferenceRateModal", 0);
    }

    public C0446a m2188b(String str) {
        if (str != null) {
            Iterator it = this.f1148d.iterator();
            while (it.hasNext()) {
                C0446a c0446a = (C0446a) it.next();
                if (str.equals(c0446a.m1820i())) {
                    return c0446a;
                }
            }
        }
        return null;
    }

    public void m2189b(int i) {
        this.f1149e = i;
    }

    public void m2190b(C0446a c0446a) {
        this.f1147c.remove(c0446a);
        m2185a(c0446a.m1820i(), true, false);
    }

    public void m2191b(C0446a c0446a, C0479k c0479k) {
        c0446a.m1815d(C0488c.m2054a());
        this.f1147c.add(c0446a);
        m2184a(c0446a.m1820i(), c0479k, true, false);
    }

    public void m2192b(String str, C0446a c0446a, C0479k c0479k) {
        Iterator it = this.f1148d.iterator();
        while (it.hasNext()) {
            C0446a c0446a2 = (C0446a) it.next();
            if (str.equals(c0446a2.m1820i())) {
                c0446a.m1815d(str);
                this.f1148d.set(this.f1148d.indexOf(c0446a2), c0446a);
                m2193b(str, c0479k, false, true);
                return;
            }
        }
    }

    public void m2193b(String str, C0479k c0479k, boolean z, boolean z2) {
        C0479k c0479k2 = (C0479k) this.f1157m.get(str);
        if (c0479k2 != null) {
            if (z) {
                this.f1153i = (this.f1153i - c0479k2.m2013f()) + c0479k.m2013f();
            }
            if (z2) {
                this.f1154j = (this.f1154j - c0479k2.m2013f()) + c0479k.m2013f();
            }
            this.f1157m.put(str, c0479k);
        }
    }

    public boolean m2194b() {
        return this.f1158n;
    }

    public C0479k m2195c(String str) {
        return (C0479k) this.f1157m.get(str);
    }

    public void m2196c() {
        this.f1152h = false;
    }

    public void m2197c(int i) {
        this.f1156l.edit().putInt("keyPreferenceOrientation", i).commit();
    }

    public void m2198c(C0446a c0446a) {
        int indexOf = this.f1147c.indexOf(c0446a);
        if (indexOf != -1) {
            Collections.swap(this.f1147c, indexOf, indexOf - 1);
        }
    }

    public void m2199c(C0446a c0446a, C0479k c0479k) {
        c0446a.m1815d(C0488c.m2054a());
        this.f1148d.add(c0446a);
        m2184a(c0446a.m1820i(), c0479k, false, true);
    }

    public void m2200d() {
        this.f1152h = true;
    }

    public void m2201d(int i) {
        this.f1156l.edit().putInt("keyPreferenceRateModal", i).commit();
    }

    public void m2202d(C0446a c0446a) {
        int indexOf = this.f1147c.indexOf(c0446a);
        if (indexOf != -1) {
            Collections.swap(this.f1147c, indexOf, indexOf + 1);
        }
    }

    public int m2203e(C0446a c0446a) {
        return this.f1147c.indexOf(c0446a);
    }

    public boolean m2204e() {
        return this.f1152h;
    }

    public int m2205f() {
        return this.f1151g;
    }

    public void m2206f(C0446a c0446a) {
        this.f1148d.remove(c0446a);
        m2185a(c0446a.m1820i(), false, true);
    }

    public void m2207g(C0446a c0446a) {
        int indexOf = this.f1148d.indexOf(c0446a);
        if (indexOf != -1) {
            Collections.swap(this.f1148d, indexOf, indexOf - 1);
        }
    }

    public int m2208h() {
        return this.f1149e;
    }

    public void m2209h(C0446a c0446a) {
        int indexOf = this.f1148d.indexOf(c0446a);
        if (indexOf != -1) {
            Collections.swap(this.f1148d, indexOf, indexOf + 1);
        }
    }

    public int m2210i() {
        return this.f1153i;
    }

    public void m2211j() {
        this.f1150f = 1;
    }

    public void m2212k() {
        this.f1150f++;
    }

    public int m2213l() {
        return this.f1150f;
    }

    public boolean m2214m() {
        return "com.wakdev.droidautomation.free".equals(WDCore.m2174a().getPackageName()) || "com.wakdev.droidautomation.pro".equals(WDCore.m2174a().getPackageName());
    }

    public ArrayList<C0446a> m2215o() {
        return this.f1147c;
    }

    public int m2216p() {
        return this.f1147c.size();
    }

    public ArrayList<C0446a> m2217q() {
        return this.f1146b;
    }

    public void m2218r() {
        this.f1146b = new ArrayList();
    }

    public boolean m2219s() {
        return this.f1147c.isEmpty();
    }

    public boolean m2220t() {
        return this.f1146b.isEmpty();
    }

    public int m2221u() {
        return this.f1154j;
    }

    public ArrayList<C0446a> m2222v() {
        return this.f1148d;
    }

    public boolean m2223w() {
        return this.f1148d.isEmpty();
    }

    public void m2224x() {
        Iterator it = this.f1147c.iterator();
        while (it.hasNext()) {
            m2185a(((C0446a) it.next()).m1820i(), true, false);
        }
        this.f1147c.clear();
    }

    public void m2225y() {
        Iterator it = this.f1148d.iterator();
        while (it.hasNext()) {
            m2185a(((C0446a) it.next()).m1820i(), false, true);
        }
        this.f1148d.clear();
    }
}
