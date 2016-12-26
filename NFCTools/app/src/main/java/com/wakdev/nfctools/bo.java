package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0489d;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class bo extends C0316b {
    private static final int f2137n;
    private boolean f2138o;
    private String f2139p;
    private Spinner f2140q;
    private Spinner f2141r;

    static {
        f2137n = C0481m.TASK_COND_WIFI.cM;
    }

    public bo() {
        this.f2138o = false;
        this.f2139p = null;
    }

    private void m2816j() {
        Intent intent = getIntent();
        this.f2138o = intent.getBooleanExtra("itemUpdate", false);
        this.f2139p = intent.getStringExtra("itemHash");
        if (this.f2138o && this.f2139p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2140q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2141r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2817k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2140q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2141r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2818l() {
        String valueOf = String.valueOf(this.f2140q.getSelectedItemPosition());
        return C0489d.m2070c(valueOf + String.valueOf(this.f2141r.getSelectedItemPosition()));
    }

    private String m2819m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.states_cond_arrays);
        if (this.f2141r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2140q.getSelectedItemPosition()] + " : " + string;
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
        setContentView(C0622e.task_cond_wifi);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2140q = (Spinner) findViewById(C0621d.state_spinner);
        this.f2141r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2141r.setSelection(1);
        m2816j();
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
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2137n);
        intent.putExtra("itemTask", m2818l());
        intent.putExtra("itemDescription", m2819m());
        intent.putExtra("itemHash", this.f2139p);
        intent.putExtra("itemUpdate", this.f2138o);
        intent.putExtra("itemFields", m2817k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
