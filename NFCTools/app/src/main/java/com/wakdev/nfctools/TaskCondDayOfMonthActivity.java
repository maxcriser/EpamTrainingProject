package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TaskCondDayOfMonthActivity extends C0316b {
    private static final int f1494n;
    private static final String[] f1495o;
    private boolean f1496p;
    private String f1497q;
    private Spinner f1498r;
    private Spinner f1499s;

    static {
        f1494n = C0481m.TASK_COND_IS_DAYOFMONTH.cM;
        f1495o = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    }

    public TaskCondDayOfMonthActivity() {
        this.f1496p = false;
        this.f1497q = null;
    }

    private void m2442j() {
        Intent intent = getIntent();
        this.f1496p = intent.getBooleanExtra("itemUpdate", false);
        this.f1497q = intent.getStringExtra("itemHash");
        if (this.f1496p && this.f1497q != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1498r, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1499s, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2443k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1498r.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f1499s.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2444l() {
        String valueOf = String.valueOf(this.f1498r.getSelectedItemPosition());
        return valueOf + "|" + String.valueOf(this.f1499s.getSelectedItemPosition());
    }

    private String m2445m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = f1495o[this.f1498r.getSelectedItemPosition()];
        if (this.f1499s.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + " : " + string;
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
        setContentView(C0622e.task_cond_dayofmonth);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1498r = (Spinner) findViewById(C0621d.state_spinner);
        this.f1499s = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1499s.setSelection(1);
        this.f1498r.setAdapter(new ArrayAdapter(this, 17367049, new ArrayList(Arrays.asList(f1495o))));
        m2442j();
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
        intent.putExtra("requestType", f1494n);
        intent.putExtra("itemTask", m2444l());
        intent.putExtra("itemDescription", m2445m());
        intent.putExtra("itemHash", this.f1497q);
        intent.putExtra("itemUpdate", this.f1496p);
        intent.putExtra("itemFields", m2443k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
