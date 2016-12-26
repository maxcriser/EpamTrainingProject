package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.Calendar;
import java.util.HashMap;

public class TaskCondYearActivity extends C0316b {
    private static final int f1520n;
    private boolean f1521o;
    private String f1522p;
    private EditText f1523q;
    private Spinner f1524r;

    static {
        f1520n = C0481m.TASK_COND_IS_YEAR.cM;
    }

    public TaskCondYearActivity() {
        this.f1521o = false;
        this.f1522p = null;
    }

    private void m2460j() {
        Intent intent = getIntent();
        this.f1521o = intent.getBooleanExtra("itemUpdate", false);
        this.f1522p = intent.getStringExtra("itemHash");
        if (this.f1521o && this.f1522p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1523q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1524r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2461k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1523q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1524r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2462l() {
        String obj = this.f1523q.getText().toString();
        return obj + "|" + String.valueOf(this.f1524r.getSelectedItemPosition());
    }

    private String m2463m() {
        String string = getString(C0625h.cond_desc_exclude);
        if (this.f1524r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return this.f1523q.getText().toString() + " : " + string;
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
        setContentView(C0622e.task_cond_year);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1523q = (EditText) findViewById(C0621d.myText);
        this.f1524r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1524r.setSelection(1);
        this.f1523q.setHint(String.valueOf(Calendar.getInstance().get(1)));
        m2460j();
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
        if (this.f1523q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1520n);
        intent.putExtra("itemTask", m2462l());
        intent.putExtra("itemDescription", m2463m());
        intent.putExtra("itemHash", this.f1522p);
        intent.putExtra("itemUpdate", this.f1521o);
        intent.putExtra("itemFields", m2461k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
