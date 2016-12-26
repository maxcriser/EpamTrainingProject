package com.wakdev.libs.p015a;

import android.annotation.SuppressLint;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.util.Log;

/* renamed from: com.wakdev.libs.a.f */
public class C0474f extends AsyncTask<C0478j, Void, C0478j> {
    private C0469a f1037a;
    private C0478j f1038b;

    public C0474f(C0469a c0469a) {
        this.f1037a = c0469a;
    }

    @SuppressLint({"NewApi"})
    protected C0478j m1919a(C0478j... c0478jArr) {
        int i = 0;
        this.f1038b = c0478jArr[0];
        Ndef c = this.f1038b.m1971c();
        if (c == null) {
            this.f1037a.m1898d().m1912d(-11);
            Log.e("NFCTools", "NDEF is not supported by this Tag");
            return null;
        }
        Log.d("NFCTools", "Reading NFC Records.");
        NdefMessage cachedNdefMessage = c.getCachedNdefMessage();
        if (cachedNdefMessage != null) {
            NdefRecord[] records = cachedNdefMessage.getRecords();
            try {
                this.f1038b.m1972c(cachedNdefMessage.getByteArrayLength());
            } catch (NoSuchMethodError e) {
                this.f1038b.m1972c(0);
            }
            int length = records.length;
            while (i < length) {
                this.f1038b.m1964a(records[i]);
                i++;
            }
        }
        return this.f1038b;
    }

    protected void m1920a(C0478j c0478j) {
        if (c0478j != null) {
            try {
                this.f1037a.m1898d().m1909b(c0478j);
                return;
            } catch (Exception e) {
                return;
            }
        }
        Log.e("NFCTools", "Reading error");
        this.f1037a.m1898d().m1912d(-5);
    }

    @SuppressLint({"NewApi"})
    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1919a((C0478j[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m1920a((C0478j) obj);
    }
}
