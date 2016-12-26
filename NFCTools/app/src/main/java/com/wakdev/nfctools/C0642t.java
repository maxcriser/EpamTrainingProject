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

/* renamed from: com.wakdev.nfctools.t */
public class C0642t extends C0316b {
    private static final int f2423n;
    private boolean f2424o;
    private String f2425p;
    private Spinner f2426q;

    static {
        f2423n = C0481m.TASK_MOBILE_CALL_LOG.cM;
    }

    public C0642t() {
        this.f2424o = false;
        this.f2425p = null;
    }

    private void m3070j() {
        Intent intent = getIntent();
        this.f2424o = intent.getBooleanExtra("itemUpdate", false);
        this.f2425p = intent.getStringExtra("itemHash");
        if (this.f2424o && this.f2425p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2426q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m3071k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2426q.getSelectedItemPosition()));
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
        setContentView(C0622e.task_calllog);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2426q = (Spinner) findViewById(C0621d.state_spinner);
        m3070j();
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
        int selectedItemPosition = this.f2426q.getSelectedItemPosition();
        if (selectedItemPosition != -1) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2423n);
            intent.putExtra("itemTask", String.valueOf(selectedItemPosition));
            intent.putExtra("itemDescription", getResources().getStringArray(C0619b.task_call_log_action_arrays)[this.f2426q.getSelectedItemPosition()]);
            intent.putExtra("itemHash", this.f2425p);
            intent.putExtra("itemUpdate", this.f2424o);
            intent.putExtra("itemFields", m3071k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
