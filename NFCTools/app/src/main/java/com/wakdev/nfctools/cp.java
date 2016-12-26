package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class cp extends C0316b {
    private static final int f2310n;
    private boolean f2311o;
    private String f2312p;
    private Spinner f2313q;

    static {
        f2310n = C0481m.TASK_CONFIG_SYNC_STATE.cM;
    }

    public cp() {
        this.f2311o = false;
        this.f2312p = null;
    }

    private void m2906j() {
        Intent intent = getIntent();
        this.f2311o = intent.getBooleanExtra("itemUpdate", false);
        this.f2312p = intent.getStringExtra("itemHash");
        if (this.f2311o && this.f2312p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2313q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2907k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2313q.getSelectedItemPosition()));
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
        setContentView(C0622e.task_sync_state);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2313q = (Spinner) findViewById(C0621d.state_spinner);
        m2906j();
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
        String valueOf = String.valueOf(this.f2313q.getSelectedItemPosition());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2310n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", getResources().getStringArray(C0619b.state_arrays)[this.f2313q.getSelectedItemPosition()]);
        intent.putExtra("itemHash", this.f2312p);
        intent.putExtra("itemUpdate", this.f2311o);
        intent.putExtra("itemFields", m2907k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
