package com.wakdev.libs.p015a;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Build.VERSION;
import android.util.Log;

/* renamed from: com.wakdev.libs.a.a */
public class C0469a {
    public boolean f1017a;
    public boolean f1018b;
    private NfcAdapter f1019c;
    private Activity f1020d;
    private C0471c f1021e;
    private String f1022f;
    private PendingIntent f1023g;
    private String f1024h;
    private String[][] f1025i;
    private IntentFilter[] f1026j;

    public C0469a(Activity activity) {
        this.f1021e = null;
        this.f1020d = activity;
        this.f1017a = false;
        this.f1024h = "*/*";
        this.f1022f = this.f1020d.getPackageName();
        this.f1019c = NfcAdapter.getDefaultAdapter(this.f1020d);
    }

    public void m1888a() {
        this.f1017a = false;
        this.f1018b = false;
        m1900e();
    }

    public void m1889a(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ("android.nfc.action.NDEF_DISCOVERED".equals(action) || "android.nfc.action.TAG_DISCOVERED".equals(action) || "android.nfc.action.TECH_DISCOVERED".equals(action)) {
                Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
                if (tag == null) {
                    Log.e("NFCTools", "Get Tag from intent error");
                    this.f1021e.m1912d(-4);
                    return;
                }
                C0478j c0478j = new C0478j(tag, this.f1017a);
                Log.d("NFCTools", "NFC Tag detected : " + c0478j.m1985o());
                this.f1021e.m1907a(c0478j);
            }
        }
    }

    public void m1890a(C0471c c0471c) {
        this.f1021e = c0471c;
    }

    public void m1891a(C0478j c0478j) {
        if (!m1897c()) {
            Log.e("NFCTools", "You are not in reading mode !");
            this.f1021e.m1915g(-7);
        } else if (c0478j != null) {
            new C0474f(this).execute(new C0478j[]{c0478j});
        } else {
            Log.e("NFCTools", "Tag is null");
            this.f1021e.m1912d(-4);
        }
    }

    public void m1892a(C0478j c0478j, boolean z) {
        if (!m1895b()) {
            Log.e("NFCTools", "You are not in writing mode !");
            this.f1021e.m1915g(-7);
        } else if (c0478j == null) {
            Log.e("NFCTools", "Tag is null");
            this.f1021e.m1915g(-4);
        } else if (c0478j.m1979i() || z || c0478j.m1978h()) {
            new C0483o(this).execute(new C0478j[]{c0478j});
        } else if (VERSION.SDK_INT < 25 || c0478j.m1958a(true) <= 0) {
            Log.e("NFCTools", "Tag is not writable");
            this.f1021e.m1915g(-8);
        } else {
            this.f1021e.m1915g(-14);
        }
    }

    public void m1893a(String str) {
        this.f1024h = str;
    }

    public void m1894b(C0478j c0478j) {
        if (!m1897c()) {
            Log.e("NFCTools", "You are not in reading mode !");
            this.f1021e.m1914f(-7);
        } else if (c0478j != null) {
            new C0475g(this).execute(new C0478j[]{c0478j});
        } else {
            Log.e("NFCTools", "Tag is null");
            this.f1021e.m1914f(-4);
        }
    }

    public boolean m1895b() {
        return this.f1017a;
    }

    public void m1896c(C0478j c0478j) {
        if (!m1897c()) {
            Log.e("NFCTools", "You are not in reading mode !");
            this.f1021e.m1913e(-7);
        } else if (c0478j != null) {
            new C0473e(this).execute(new C0478j[]{c0478j});
        } else {
            Log.e("NFCTools", "Tag is null");
            this.f1021e.m1913e(-4);
        }
    }

    public boolean m1897c() {
        return !this.f1017a;
    }

    public C0471c m1898d() {
        return this.f1021e;
    }

    public void m1899d(C0478j c0478j) {
        if (!m1895b()) {
            Log.e("NFCTools", "You are not in writing mode !");
            this.f1021e.m1916h(-7);
        } else if (c0478j != null) {
            new C0482n(this).execute(new C0478j[]{c0478j});
        } else {
            Log.e("NFCTools", "Tag is null");
            this.f1021e.m1916h(-4);
        }
    }

    public void m1900e() {
        if (this.f1019c == null) {
            Log.e("NFCTools", "This device doesn't support NFC");
            this.f1021e.m1910c(-2);
            return;
        }
        if (!this.f1019c.isEnabled()) {
            Log.w("NFCTools", "NFC is disabled");
            this.f1021e.m1910c(-3);
        }
        Intent intent = new Intent(this.f1020d.getApplicationContext(), this.f1020d.getClass());
        intent.setFlags(536870912);
        this.f1023g = PendingIntent.getActivity(this.f1020d.getApplicationContext(), 0, intent, 0);
        this.f1026j = new IntentFilter[1];
        String[][] strArr = new String[1][];
        strArr[0] = new String[]{Ndef.class.getName()};
        this.f1025i = strArr;
        if (this.f1017a) {
            this.f1026j[0] = new IntentFilter();
            this.f1026j[0].addAction("android.nfc.action.TAG_DISCOVERED");
            this.f1026j[0].addCategory("android.intent.category.DEFAULT");
        } else {
            this.f1026j[0] = new IntentFilter();
            this.f1026j[0].addAction("android.nfc.action.TAG_DISCOVERED");
            this.f1026j[0].addCategory("android.intent.category.DEFAULT");
            if (this.f1024h != null) {
                try {
                    this.f1026j[0].addDataType(this.f1024h);
                } catch (MalformedMimeTypeException e) {
                    throw new RuntimeException("Check your mime type.");
                }
            }
        }
        Log.d("NFCTools", "Setting foreground dispatch");
    }

    public void m1901e(C0478j c0478j) {
        m1892a(c0478j, false);
    }

    public void m1902f() {
        if (this.f1019c != null) {
            this.f1019c.enableForegroundDispatch(this.f1020d, this.f1023g, this.f1026j, this.f1025i);
        }
    }

    public void m1903g() {
        if (this.f1019c != null) {
            this.f1019c.disableForegroundDispatch(this.f1020d);
        }
    }
}
