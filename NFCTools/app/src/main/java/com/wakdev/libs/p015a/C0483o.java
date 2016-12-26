package com.wakdev.libs.p015a;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.wakdev.libs.a.o */
public class C0483o extends AsyncTask<C0478j, Void, Boolean> {
    private C0469a f1116a;
    private C0478j f1117b;

    public C0483o(C0469a c0469a) {
        this.f1116a = c0469a;
    }

    private boolean m2034a() {
        boolean e = this.f1117b.m1975e();
        Ndef c = this.f1117b.m1971c();
        NdefFormatable d = this.f1117b.m1974d();
        boolean z = c == null && this.f1117b.m1978h();
        boolean z2 = z && d != null;
        if (!(z || c == null)) {
            z2 = true;
        }
        if (z2) {
            Log.d("NFCTools", "Writing NFC Records.");
            ArrayList g = this.f1117b.m1977g();
            NdefRecord[] ndefRecordArr = new NdefRecord[g.size()];
            Iterator it = g.iterator();
            int i = 0;
            while (it.hasNext()) {
                ndefRecordArr[i] = ((C0479k) it.next()).m2000a();
                i++;
            }
            if (ndefRecordArr.length == 0) {
                Log.e("NFCTools", "Must have at least record");
                this.f1116a.m1898d().m1915g(-12);
                return false;
            }
            NdefMessage ndefMessage = new NdefMessage(ndefRecordArr);
            i = ndefMessage.toByteArray().length;
            if (z || this.f1117b.m1982l() >= i) {
                if (z) {
                    try {
                        d.connect();
                    } catch (IOException e2) {
                        Log.e("NFCTools", "I/O NFC error");
                        this.f1116a.m1898d().m1915g(-6);
                        return false;
                    }
                }
                c.connect();
                if (z) {
                    try {
                        d.format(ndefMessage);
                    } catch (FormatException e3) {
                        Log.e("NFCTools", "NFC Format error");
                        this.f1116a.m1898d().m1915g(-10);
                        return false;
                    }
                }
                c.writeNdefMessage(ndefMessage);
                if (e && !z) {
                    if (c.canMakeReadOnly()) {
                        Log.d("NFCTools", "NFC Lock tag");
                        c.makeReadOnly();
                    } else {
                        this.f1116a.m1898d().m1915g(-6);
                        return false;
                    }
                }
                if (z) {
                    d.close();
                } else {
                    c.close();
                }
                this.f1116a.m1898d().m1905a();
                return true;
            }
            Log.e("NFCTools", "Message exceeds the max tag size");
            this.f1116a.m1898d().m1915g(-9);
            return false;
        }
        this.f1116a.m1898d().m1915g(-11);
        Log.e("NFCTools", "NDEF is not supported by this Tag");
        return false;
    }

    private boolean eraseTag() {
        return true;
    }

    protected Boolean m2035a(C0478j... c0478jArr) {
        boolean z = false;
        this.f1117b = c0478jArr[0];
        if (!this.f1117b.m1976f()) {
            z = m2034a();
        }
        return Boolean.valueOf(z);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m2035a((C0478j[]) objArr);
    }
}
