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

/* renamed from: com.wakdev.nfctools.w */
public class C0645w extends C0316b {
    private static final int f2436n;
    private boolean f2437o;
    private String f2438p;
    private Spinner f2439q;
    private Spinner f2440r;

    static {
        f2436n = C0481m.TASK_COND_IS_AIRPLANE.cM;
    }

    public C0645w() {
        this.f2437o = false;
        this.f2438p = null;
    }

    private void m3076j() {
        Intent intent = getIntent();
        this.f2437o = intent.getBooleanExtra("itemUpdate", false);
        this.f2438p = intent.getStringExtra("itemHash");
        if (this.f2437o && this.f2438p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2439q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2440r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m3077k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2439q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2440r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m3078l() {
        String valueOf = String.valueOf(this.f2439q.getSelectedItemPosition());
        return C0489d.m2070c(valueOf + String.valueOf(this.f2440r.getSelectedItemPosition()));
    }

    private String m3079m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.states_cond_arrays);
        if (this.f2440r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2439q.getSelectedItemPosition()] + " : " + string;
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
        setContentView(C0622e.task_cond_airplane);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2439q = (Spinner) findViewById(C0621d.state_spinner);
        this.f2440r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2440r.setSelection(1);
        m3076j();
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
        intent.putExtra("requestType", f2436n);
        intent.putExtra("itemTask", m3078l());
        intent.putExtra("itemDescription", m3079m());
        intent.putExtra("itemHash", this.f2438p);
        intent.putExtra("itemUpdate", this.f2437o);
        intent.putExtra("itemFields", m3077k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
