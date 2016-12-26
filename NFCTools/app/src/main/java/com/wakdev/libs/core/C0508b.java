package com.wakdev.libs.core;

import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0454c;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.wakdev.libs.core.b */
public class C0508b {
    private static C0508b f1159a;
    private HashMap<String, String> f1160b;
    private HashMap<String, Integer> f1161c;
    private HashMap<String, Boolean> f1162d;
    private HashMap<String, C0454c> f1163e;
    private HashMap<String, ArrayList<C0446a>> f1164f;

    private C0508b() {
        this.f1160b = new HashMap();
        this.f1161c = new HashMap();
        this.f1162d = new HashMap();
        this.f1163e = new HashMap();
        this.f1164f = new HashMap();
    }

    public static synchronized C0508b m2226a() {
        C0508b c0508b;
        synchronized (C0508b.class) {
            if (f1159a == null) {
                f1159a = new C0508b();
            }
            c0508b = f1159a;
        }
        return c0508b;
    }

    public C0454c m2227a(String str) {
        return (C0454c) this.f1163e.get(str);
    }

    public void m2228a(String str, C0454c c0454c) {
        this.f1163e.put(str, c0454c);
    }
}
