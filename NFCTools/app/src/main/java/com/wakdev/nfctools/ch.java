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
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class ch extends C0316b {
    private static final int f2265n;
    private boolean f2266o;
    private String f2267p;
    private EditText f2268q;

    static {
        f2265n = C0481m.TASK_NETWORK_PING.cM;
    }

    public ch() {
        this.f2266o = false;
        this.f2267p = null;
    }

    private void m2886j() {
        Intent intent = getIntent();
        this.f2266o = intent.getBooleanExtra("itemUpdate", false);
        this.f2267p = intent.getStringExtra("itemHash");
        if (this.f2266o && this.f2267p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2268q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2887k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2268q.getText().toString());
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
        setContentView(C0622e.task_ping);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2268q = (EditText) findViewById(C0621d.myHostname);
        m2886j();
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
        String obj = this.f2268q.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2265n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f2267p);
        intent.putExtra("itemUpdate", this.f2266o);
        intent.putExtra("itemFields", m2887k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
