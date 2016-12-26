package com.wakdev.libs.p015a;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

/* renamed from: com.wakdev.libs.a.e */
public class C0473e extends AsyncTask<C0478j, Void, C0472d> {
    private C0469a f1034a;
    private C0478j f1035b;
    private C0472d f1036c;

    public C0473e(C0469a c0469a) {
        this.f1036c = null;
        this.f1034a = c0469a;
    }

    @SuppressLint({"NewApi"})
    protected C0472d m1917a(C0478j... c0478jArr) {
        Log.d("NFCTools", "Reading Memory");
        this.f1035b = c0478jArr[0];
        if (this.f1035b.m1969b() != null) {
            this.f1036c = new C0472d();
            try {
                this.f1036c.f1033c = this.f1035b.m1953J();
                this.f1036c.f1031a = this.f1035b.m1970b(this.f1036c.f1033c);
                this.f1036c.f1032b = this.f1035b.m1957a(this.f1036c.f1033c);
                if (this.f1036c.f1031a == null || this.f1036c.f1032b <= 0) {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return this.f1036c;
    }

    protected void m1918a(C0472d c0472d) {
        if (c0472d != null) {
            try {
                this.f1034a.m1898d().m1906a(c0472d);
                return;
            } catch (Exception e) {
                return;
            }
        }
        Log.e("NFCTools", "Reading Memory error");
        this.f1034a.m1898d().m1913e(-5);
    }

    @SuppressLint({"NewApi"})
    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1917a((C0478j[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m1918a((C0472d) obj);
    }
}
