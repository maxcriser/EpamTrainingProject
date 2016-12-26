package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class cg extends C0316b {
    private static final int f2259n;
    private boolean f2260o;
    private String f2261p;
    private EditText f2262q;
    private Spinner f2263r;
    private Button f2264s;

    static {
        f2259n = C0481m.TASK_OPENVPN.cM;
    }

    public cg() {
        this.f2260o = false;
        this.f2261p = null;
    }

    private void m2882j() {
        Intent intent = getIntent();
        this.f2260o = intent.getBooleanExtra("itemUpdate", false);
        this.f2261p = intent.getStringExtra("itemHash");
        if (this.f2260o && this.f2261p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2262q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2263r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2883k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2262q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f2263r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2884l() {
        String obj = this.f2262q.getText().toString();
        return obj + "|" + String.valueOf(this.f2263r.getSelectedItemPosition());
    }

    private String m2885m() {
        return getResources().getStringArray(C0619b.openvpn_action_arrays)[this.f2263r.getSelectedItemPosition()] + " : " + this.f2262q.getText().toString();
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
        setContentView(C0622e.task_openvpn);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2262q = (EditText) findViewById(C0621d.myText);
        this.f2264s = (Button) findViewById(C0621d.myOpenVPNDownload);
        this.f2263r = (Spinner) findViewById(C0621d.myActionSpinner);
        if (C0507a.m2176g() != 1) {
            this.f2264s.setVisibility(8);
        }
        m2882j();
    }

    public void onDownloadOfficialAppButtonClick(View view) {
        C0495h.m2085a("net.openvpn.openvpn", 1);
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
        if (this.f2262q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2259n);
        intent.putExtra("itemTask", m2884l());
        intent.putExtra("itemDescription", m2885m());
        intent.putExtra("itemHash", this.f2261p);
        intent.putExtra("itemUpdate", this.f2260o);
        intent.putExtra("itemFields", m2883k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
