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
import java.util.HashMap;

public class as extends C0316b {
    private static final int f1969n;
    private boolean f1970o;
    private String f1971p;
    private EditText f1972q;
    private Spinner f1973r;

    static {
        f1969n = C0481m.TASK_COND_IMEI.cM;
    }

    public as() {
        this.f1970o = false;
        this.f1971p = null;
    }

    private void m2709j() {
        Intent intent = getIntent();
        this.f1970o = intent.getBooleanExtra("itemUpdate", false);
        this.f1971p = intent.getStringExtra("itemHash");
        if (this.f1970o && this.f1971p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1972q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1973r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2710k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1972q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1973r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2711l() {
        String obj = this.f1972q.getText().toString();
        return obj + "|" + String.valueOf(this.f1973r.getSelectedItemPosition());
    }

    private String m2712m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = getString(C0625h.task_cond_imei_title) + " " + this.f1972q.getText().toString();
        if (this.f1973r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
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
        setContentView(C0622e.task_cond_imei);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1972q = (EditText) findViewById(C0621d.myText);
        this.f1973r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1973r.setSelection(1);
        m2709j();
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
        if (this.f1972q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1969n);
        intent.putExtra("itemTask", m2711l());
        intent.putExtra("itemDescription", m2712m());
        intent.putExtra("itemHash", this.f1971p);
        intent.putExtra("itemUpdate", this.f1970o);
        intent.putExtra("itemFields", m2710k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
