package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
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

public class TaskCondMonthActivity extends C0316b {
    private static final int f1500n;
    private boolean f1501o;
    private String f1502p;
    private Spinner f1503q;
    private Spinner f1504r;

    static {
        f1500n = C0481m.TASK_COND_IS_MONTH.cM;
    }

    public TaskCondMonthActivity() {
        this.f1501o = false;
        this.f1502p = null;
    }

    private void m2446j() {
        Intent intent = getIntent();
        this.f1501o = intent.getBooleanExtra("itemUpdate", false);
        this.f1502p = intent.getStringExtra("itemHash");
        if (this.f1501o && this.f1502p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1503q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1504r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2447k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1503q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f1504r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2448l() {
        String valueOf = String.valueOf(this.f1503q.getSelectedItemPosition());
        return valueOf + "|" + String.valueOf(this.f1504r.getSelectedItemPosition());
    }

    private String m2449m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.task_cond_month_array);
        if (this.f1504r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f1503q.getSelectedItemPosition()] + " : " + string;
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
        setContentView(C0622e.task_cond_month);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1503q = (Spinner) findViewById(C0621d.state_spinner);
        this.f1504r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1504r.setSelection(1);
        m2446j();
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
        intent.putExtra("requestType", f1500n);
        intent.putExtra("itemTask", m2448l());
        intent.putExtra("itemDescription", m2449m());
        intent.putExtra("itemHash", this.f1502p);
        intent.putExtra("itemUpdate", this.f1501o);
        intent.putExtra("itemFields", m2447k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
