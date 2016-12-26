package com.wakdev.libs.p015a;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

/* renamed from: com.wakdev.libs.a.n */
public class C0482n extends AsyncTask<C0478j, Void, Integer> {
    private C0469a f1115a;

    public C0482n(C0469a c0469a) {
        this.f1115a = c0469a;
    }

    @SuppressLint({"NewApi"})
    protected Integer m2032a(C0478j... c0478jArr) {
        Log.d("NFCTools", "Formatting Data");
        C0478j c0478j = c0478jArr[0];
        int i = -1;
        if (c0478j.m1969b() != null) {
            i = c0478j.formatTagMemory();
        }
        return Integer.valueOf(i);
    }

    protected void m2033a(Integer num) {
        try {
            if (num.intValue() == 1) {
                this.f1115a.m1898d().m1908b();
                return;
            }
            Log.e("NFCTools", "Formatting error");
            this.f1115a.m1898d().m1916h(num.intValue());
        } catch (Exception e) {
        }
    }

    @SuppressLint({"NewApi"})
    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m2032a((C0478j[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m2033a((Integer) obj);
    }
}
