package com.wakdev.nfctools;

import android.annotation.SuppressLint;
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
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class ct extends C0316b {
    private static final int f2326n;
    private boolean f2327o;
    private String f2328p;
    private EditText f2329q;
    private EditText f2330r;

    static {
        f2326n = C0481m.TASK_NETWORK_WOL.cM;
    }

    public ct() {
        this.f2327o = false;
        this.f2328p = null;
    }

    private void m2915j() {
        Intent intent = getIntent();
        this.f2327o = intent.getBooleanExtra("itemUpdate", false);
        this.f2328p = intent.getStringExtra("itemHash");
        if (this.f2327o && this.f2328p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2329q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2330r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2916k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2329q.getText().toString());
        hashMap.put("field2", this.f2330r.getText().toString());
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
        setContentView(C0622e.task_wol);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2329q = (EditText) findViewById(C0621d.myBroadcastIP);
        this.f2330r = (EditText) findViewById(C0621d.myMAC);
        m2915j();
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

    @SuppressLint({"DefaultLocale"})
    public void onValidateButtonClick(View view) {
        String obj = this.f2329q.getText().toString();
        String toUpperCase = this.f2330r.getText().toString().toUpperCase();
        if (toUpperCase.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_mac_address_is_empty));
        } else if (!C0506o.m2170a(toUpperCase) && !toUpperCase.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_mac_address));
        } else if (C0506o.m2171b(obj)) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2326n);
            intent.putExtra("itemTask", obj + "|" + toUpperCase);
            intent.putExtra("itemDescription", obj + " - " + toUpperCase);
            intent.putExtra("itemHash", this.f2328p);
            intent.putExtra("itemUpdate", this.f2327o);
            intent.putExtra("itemFields", m2916k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } else {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_broadcast_ip));
        }
    }
}
