package com.wakdev.nfctools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0469a;
import com.wakdev.libs.p015a.C0471c;
import com.wakdev.libs.p015a.C0472d;
import com.wakdev.libs.p015a.C0478j;
import com.wakdev.libs.p015a.C0479k;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.Iterator;

public class ScanActivity extends Activity implements C0471c {
    private static final String f1379c;
    public C0469a f1380a;
    private String f1381b;

    static {
        f1379c = null;
    }

    public ScanActivity() {
        this.f1381b = "";
    }

    private void m2376d(C0478j c0478j) {
        Iterator it = c0478j.m1977g().iterator();
        while (it.hasNext()) {
            C0479k c0479k = (C0479k) it.next();
            if (c0479k.m2017j() == 2) {
                try {
                    this.f1381b = this.f1381b.replace("{NDEF-TEXT}", c0479k.m2016i());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (c0479k.m2017j() == 3) {
                try {
                    this.f1381b = this.f1381b.replace("{NDEF-URI}", c0479k.m2019l().toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.f1381b = this.f1381b.replace("{TAG-ID}", "");
        this.f1381b = this.f1381b.replace("{TAG-SIZE}", "");
        this.f1381b = this.f1381b.replace("{TAG-MAXSIZE}", "");
        this.f1381b = this.f1381b.replace("{TAG-TYPE}", "");
        this.f1381b = this.f1381b.replace("{TAG-ISWRITABLE}", "");
        this.f1381b = this.f1381b.replace("{TAG-CANMAKEREADONLY}", "");
        this.f1381b = this.f1381b.replace("{NDEF-TEXT}", "");
        this.f1381b = this.f1381b.replace("{NDEF-URI}", "");
        C0495h.m2084a(this.f1381b);
        finish();
    }

    private void m2377e(C0478j c0478j) {
        if (c0478j != null) {
            CharSequence charSequence = "undefined";
            CharSequence charSequence2 = "undefined";
            CharSequence charSequence3 = "undefined";
            CharSequence charSequence4 = "undefined";
            CharSequence charSequence5 = "undefined";
            CharSequence charSequence6 = "undefined";
            try {
                charSequence = c0478j.m1985o();
            } catch (Exception e) {
            }
            try {
                charSequence2 = String.valueOf(c0478j.m1950G());
            } catch (Exception e2) {
            }
            try {
                charSequence3 = String.valueOf(c0478j.m1982l());
            } catch (Exception e3) {
            }
            try {
                charSequence4 = c0478j.m1961a((Context) this);
            } catch (Exception e4) {
            }
            try {
                charSequence5 = String.valueOf(c0478j.m1979i());
            } catch (Exception e5) {
            }
            try {
                charSequence6 = String.valueOf(c0478j.m1981k());
            } catch (Exception e6) {
            }
            this.f1381b = this.f1381b.replace("{TAG-ID}", charSequence);
            this.f1381b = this.f1381b.replace("{TAG-SIZE}", charSequence2);
            this.f1381b = this.f1381b.replace("{TAG-MAXSIZE}", charSequence3);
            this.f1381b = this.f1381b.replace("{TAG-TYPE}", charSequence4);
            this.f1381b = this.f1381b.replace("{TAG-ISWRITABLE}", charSequence5);
            this.f1381b = this.f1381b.replace("{TAG-CANMAKEREADONLY}", charSequence6);
            if (c0478j.m1971c() != null) {
                this.f1380a.m1891a(c0478j);
                return;
            }
            C0495h.m2084a(this.f1381b);
            finish();
        }
    }

    public void m2378a() {
    }

    public void m2379a(C0472d c0472d) {
    }

    public void m2380a(C0478j c0478j) {
        m2377e(c0478j);
    }

    public void m2381b() {
    }

    public void m2382b(C0478j c0478j) {
        m2376d(c0478j);
    }

    public void m2383c(int i) {
        String string;
        switch (i) {
            case -3:
                string = getString(C0625h.err_adapter_disable);
                break;
            default:
                string = getString(C0625h.err_adapter_unknow);
                break;
        }
        C0493f.m2081a(this, string);
    }

    public void m2384c(C0478j c0478j) {
    }

    public void m2385d(int i) {
    }

    public void m2386e(int i) {
    }

    public void m2387f(int i) {
    }

    public void m2388g(int i) {
    }

    public void m2389h(int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.scan_activity);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.f1381b = Uri.parse(intent.getDataString()).getQueryParameter("callback");
                if (this.f1381b == null) {
                    throw new Exception();
                } else if (this.f1381b.isEmpty()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                C0493f.m2081a(this, "Bad NFC URL");
                finish();
            }
        } else {
            C0493f.m2081a(this, "Bad NFC URL");
            finish();
        }
        this.f1380a = new C0469a(this);
        this.f1380a.m1890a((C0471c) this);
        this.f1380a.m1893a(f1379c);
        this.f1380a.m1888a();
        this.f1380a.f1017a = false;
    }

    protected void onNewIntent(Intent intent) {
        this.f1380a.m1889a(intent);
    }

    protected void onPause() {
        this.f1380a.m1903g();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.f1380a.m1902f();
    }
}
