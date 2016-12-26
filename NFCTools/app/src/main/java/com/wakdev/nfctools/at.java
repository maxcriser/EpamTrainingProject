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

public class at extends C0316b {
    private static final int f1974n;
    private boolean f1975o;
    private String f1976p;
    private Spinner f1977q;
    private Spinner f1978r;

    static {
        f1974n = C0481m.TASK_COND_INTERNET_AVAILABILITY.cM;
    }

    public at() {
        this.f1975o = false;
        this.f1976p = null;
    }

    private void m2713j() {
        Intent intent = getIntent();
        this.f1975o = intent.getBooleanExtra("itemUpdate", false);
        this.f1976p = intent.getStringExtra("itemHash");
        if (this.f1975o && this.f1976p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1977q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1978r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2714k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1977q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f1978r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2715l() {
        String valueOf = String.valueOf(this.f1977q.getSelectedItemPosition());
        return C0489d.m2070c(valueOf + String.valueOf(this.f1978r.getSelectedItemPosition()));
    }

    private String m2716m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.task_cond_internet_availability_state);
        if (this.f1978r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f1977q.getSelectedItemPosition()] + " : " + string;
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
        setContentView(C0622e.task_cond_internet_availability);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1977q = (Spinner) findViewById(C0621d.state_spinner);
        this.f1978r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1978r.setSelection(1);
        m2713j();
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
        intent.putExtra("requestType", f1974n);
        intent.putExtra("itemTask", m2715l());
        intent.putExtra("itemDescription", m2716m());
        intent.putExtra("itemHash", this.f1976p);
        intent.putExtra("itemUpdate", this.f1975o);
        intent.putExtra("itemFields", m2714k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
