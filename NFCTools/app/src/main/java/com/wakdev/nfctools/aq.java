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

public class aq extends C0316b {
    private static final int f1959n;
    private boolean f1960o;
    private String f1961p;
    private Spinner f1962q;
    private Spinner f1963r;

    static {
        f1959n = C0481m.TASK_COND_IS_HAPTIC_FEEDBACK.cM;
    }

    public aq() {
        this.f1960o = false;
        this.f1961p = null;
    }

    private void m2701j() {
        Intent intent = getIntent();
        this.f1960o = intent.getBooleanExtra("itemUpdate", false);
        this.f1961p = intent.getStringExtra("itemHash");
        if (this.f1960o && this.f1961p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1962q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1963r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2702k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1962q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f1963r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2703l() {
        String valueOf = String.valueOf(this.f1962q.getSelectedItemPosition());
        return C0489d.m2070c(valueOf + String.valueOf(this.f1963r.getSelectedItemPosition()));
    }

    private String m2704m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.states_cond_arrays);
        if (this.f1963r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f1962q.getSelectedItemPosition()] + " : " + string;
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
        setContentView(C0622e.task_cond_haptic_feedback);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1962q = (Spinner) findViewById(C0621d.state_spinner);
        this.f1963r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1963r.setSelection(1);
        m2701j();
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
        intent.putExtra("requestType", f1959n);
        intent.putExtra("itemTask", m2703l());
        intent.putExtra("itemDescription", m2704m());
        intent.putExtra("itemHash", this.f1961p);
        intent.putExtra("itemUpdate", this.f1960o);
        intent.putExtra("itemFields", m2702k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
