package com.wakdev.p014a;

import android.graphics.drawable.Drawable;
import java.io.Serializable;
import java.util.HashMap;

/* renamed from: com.wakdev.a.a */
public class C0446a implements Serializable {
    private String f931a;
    private String f932b;
    private String f933c;
    private int f934d;
    private int f935e;
    private int f936f;
    private String f937g;
    private HashMap<String, String> f938h;
    private HashMap<String, String> f939i;
    private transient Drawable f940j;
    private transient Drawable f941k;
    private int f942l;
    private int f943m;
    private int f944n;
    private String f945o;

    public C0446a() {
        this.f934d = -1;
        this.f935e = -1;
        this.f936f = -1;
        this.f937g = null;
        this.f940j = null;
        this.f941k = null;
        this.f943m = -1;
        this.f944n = -1;
    }

    public int m1801a() {
        return this.f934d;
    }

    public void m1802a(int i) {
        this.f934d = i;
    }

    public void m1803a(Drawable drawable) {
        this.f940j = drawable;
    }

    public void m1804a(String str) {
        this.f931a = str;
    }

    public void m1805a(HashMap<String, String> hashMap) {
        this.f938h = hashMap;
    }

    public int m1806b() {
        return this.f935e;
    }

    public void m1807b(int i) {
        this.f935e = i;
    }

    public void m1808b(String str) {
        this.f932b = str;
    }

    public void m1809b(HashMap<String, String> hashMap) {
        this.f939i = hashMap;
    }

    public int m1810c() {
        return this.f943m;
    }

    public void m1811c(int i) {
        this.f936f = i;
    }

    public void m1812c(String str) {
        this.f933c = str;
    }

    public int m1813d() {
        return this.f944n;
    }

    public void m1814d(int i) {
        this.f942l = i;
    }

    public void m1815d(String str) {
        this.f937g = str;
    }

    public Drawable m1816e() {
        return this.f940j;
    }

    public Drawable m1817f() {
        return this.f941k;
    }

    public String m1818g() {
        return this.f945o;
    }

    public int m1819h() {
        return this.f936f;
    }

    public String m1820i() {
        return this.f937g;
    }

    public String m1821j() {
        return this.f931a;
    }

    public String m1822k() {
        return this.f932b;
    }

    public String m1823l() {
        return this.f933c;
    }

    public HashMap<String, String> m1824m() {
        return this.f938h;
    }

    public HashMap<String, String> m1825n() {
        return this.f939i;
    }

    public int m1826o() {
        return this.f942l;
    }
}
