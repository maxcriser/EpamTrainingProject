package com.wakdev.libs.p015a;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

/* renamed from: com.wakdev.libs.a.g */
public class C0475g extends AsyncTask<C0478j, Void, C0478j> {
    private C0469a f1039a;
    private C0478j f1040b;

    public C0475g(C0469a c0469a) {
        this.f1039a = c0469a;
    }

    @SuppressLint({"NewApi"})
    protected C0478j m1921a(C0478j... c0478jArr) {
        Log.d("NFCTools", "Reading Technical Data");
        this.f1040b = c0478jArr[0];
        this.f1040b.m1963a();
        return this.f1040b;
    }

    protected void m1922a(C0478j c0478j) {
        if (c0478j != null) {
            try {
                this.f1039a.m1898d().m1911c(c0478j);
                return;
            } catch (Exception e) {
                return;
            }
        }
        Log.e("NFCTools", "Reading Tech error");
        this.f1039a.m1898d().m1914f(-5);
    }

    @SuppressLint({"NewApi"})
    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1921a((C0478j[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m1922a((C0478j) obj);
    }
}
