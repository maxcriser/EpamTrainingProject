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

public class TaskZenModeActivity extends C0316b {
    private static final int f1848n;
    private boolean f1849o;
    private String f1850p;
    private Spinner f1851q;

    static {
        f1848n = C0481m.TASK_ZEN_MODE.cM;
    }

    public TaskZenModeActivity() {
        this.f1849o = false;
        this.f1850p = null;
    }

    private void m2621j() {
        Intent intent = getIntent();
        this.f1849o = intent.getBooleanExtra("itemUpdate", false);
        this.f1850p = intent.getStringExtra("itemHash");
        if (this.f1849o && this.f1850p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1851q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2622k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1851q.getSelectedItemPosition()));
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
        setContentView(C0622e.task_zen_mode);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1851q = (Spinner) findViewById(C0621d.state_spinner);
        m2621j();
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
        String valueOf = String.valueOf(this.f1851q.getSelectedItemPosition());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1848n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", getResources().getStringArray(C0619b.zen_mode_arrays)[this.f1851q.getSelectedItemPosition()]);
        intent.putExtra("itemHash", this.f1850p);
        intent.putExtra("itemUpdate", this.f1849o);
        intent.putExtra("itemFields", m2622k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
