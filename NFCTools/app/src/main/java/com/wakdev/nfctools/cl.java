package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

public class cl extends C0316b {
    private static final int f2283n;
    private boolean f2284o;
    private String f2285p;
    private EditText f2286q;

    static {
        f2283n = C0481m.TASK_CONFIG_SECRET_CODE.cM;
    }

    public cl() {
        this.f2284o = false;
        this.f2285p = null;
    }

    private void m2894j() {
        Intent intent = getIntent();
        this.f2284o = intent.getBooleanExtra("itemUpdate", false);
        this.f2285p = intent.getStringExtra("itemHash");
        if (this.f2284o && this.f2285p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2286q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2895k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2286q.getText().toString());
        return hashMap;
    }

    private String m2896l() {
        return this.f2286q.getText().toString();
    }

    private String m2897m() {
        return getString(C0625h.task_secret_code_start) + this.f2286q.getText().toString() + getString(C0625h.task_secret_code_end);
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
        setContentView(C0622e.task_secret_code);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2286q = (EditText) findViewById(C0621d.myText);
        m2894j();
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
        if (this.f2286q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2283n);
        intent.putExtra("itemTask", m2896l());
        intent.putExtra("itemDescription", m2897m());
        intent.putExtra("itemHash", this.f2285p);
        intent.putExtra("itemUpdate", this.f2284o);
        intent.putExtra("itemFields", m2895k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
