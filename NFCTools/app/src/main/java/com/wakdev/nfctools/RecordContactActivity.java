package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0506o;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class RecordContactActivity extends C0316b {
    private EditText f1294n;
    private EditText f1295o;
    private EditText f1296p;
    private EditText f1297q;
    private EditText f1298r;
    private EditText f1299s;
    private boolean f1300t;
    private String f1301u;

    public RecordContactActivity() {
        this.f1300t = false;
        this.f1301u = null;
    }

    private void m2336j() {
        Intent intent = getIntent();
        this.f1300t = intent.getBooleanExtra("itemUpdate", false);
        this.f1301u = intent.getStringExtra("itemHash");
        if (this.f1300t && this.f1301u != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1294n, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1295o, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f1296p, (String) hashMap.get("field3"));
                C0490e.m2075a(this.f1297q, (String) hashMap.get("field4"));
                C0490e.m2075a(this.f1298r, (String) hashMap.get("field5"));
                C0490e.m2075a(this.f1299s, (String) hashMap.get("field6"));
            }
        }
    }

    private HashMap<String, String> m2337k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1294n.getText().toString());
        hashMap.put("field2", this.f1295o.getText().toString());
        hashMap.put("field3", this.f1296p.getText().toString());
        hashMap.put("field4", this.f1297q.getText().toString());
        hashMap.put("field5", this.f1298r.getText().toString());
        hashMap.put("field6", this.f1299s.getText().toString());
        return hashMap;
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCancelButtonClick(View view) {
        setResult(0, null);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.record_contact);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1294n = (EditText) findViewById(C0621d.myContactName);
        this.f1295o = (EditText) findViewById(C0621d.myCompany);
        this.f1296p = (EditText) findViewById(C0621d.myTel);
        this.f1297q = (EditText) findViewById(C0621d.myMail);
        this.f1298r = (EditText) findViewById(C0621d.myWebsite);
        this.f1299s = (EditText) findViewById(C0621d.myAddress);
        m2336j();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onValidateButtonClick(View view) {
        int i = 0;
        String obj = this.f1294n.getText().toString();
        String obj2 = this.f1295o.getText().toString();
        String obj3 = this.f1296p.getText().toString();
        CharSequence obj4 = this.f1297q.getText().toString();
        String obj5 = this.f1298r.getText().toString();
        String obj6 = this.f1299s.getText().toString();
        String str = "";
        String str2 = "";
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
        } else {
            int i2;
            if (obj3.isEmpty() && obj4.isEmpty() && obj5.isEmpty()) {
                C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (obj4.isEmpty() || C0506o.m2169a(obj4)) {
                i = i2;
            } else {
                C0493f.m2081a(this, getString(C0625h.err_incorrect_mail));
            }
        }
        if (i != 0) {
            String str3 = ((str + "BEGIN:VCARD\n") + "VERSION:3.0\n") + "FN:" + obj + "\n";
            String str4 = str2 + obj + "\n";
            if (!obj2.isEmpty()) {
                str3 = str3 + "ORG:" + obj2 + "\n";
                str4 = str4 + obj2 + "\n";
            }
            if (!obj6.isEmpty()) {
                str3 = str3 + "ADR:;;" + obj6.replace(",", "\\,").replace(";", "\\;") + ";;;;;\n";
                str4 = str4 + obj6 + "\n";
            }
            if (!obj3.isEmpty()) {
                str3 = str3 + "TEL:" + obj3 + "\n";
                str4 = str4 + obj3 + "\n";
            }
            if (!obj4.isEmpty()) {
                str3 = str3 + "EMAIL:" + obj4 + "\n";
                str4 = str4 + obj4 + "\n";
            }
            if (!obj5.isEmpty()) {
                str3 = str3 + "URL:" + obj5 + "\n";
                str4 = str4 + obj5;
            }
            str3 = str3 + "END:VCARD";
            Intent intent = new Intent();
            intent.putExtra("requestMode", 1);
            intent.putExtra("requestType", 5);
            intent.putExtra("itemRecord", str3);
            intent.putExtra("itemDescription", str4);
            intent.putExtra("itemHash", this.f1301u);
            intent.putExtra("itemUpdate", this.f1300t);
            intent.putExtra("itemFields", m2337k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }
}
