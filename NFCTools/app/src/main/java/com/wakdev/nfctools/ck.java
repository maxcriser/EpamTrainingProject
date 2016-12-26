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

public class ck extends C0316b {
    private static final int f2279n;
    private EditText f2280o;
    private boolean f2281p;
    private String f2282q;

    static {
        f2279n = C0481m.TASK_MISC_RUN_PROFILE.cM;
    }

    public ck() {
        this.f2281p = false;
        this.f2282q = null;
    }

    private void m2892j() {
        Intent intent = getIntent();
        this.f2281p = intent.getBooleanExtra("itemUpdate", false);
        this.f2282q = intent.getStringExtra("itemHash");
        if (this.f2281p && this.f2282q != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2280o, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2893k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2280o.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            Object stringExtra = intent.getStringExtra("intentResultProfileName");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f2280o.setText(stringExtra);
                this.f2280o.setSelection(stringExtra.length());
            }
        }
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
        setContentView(C0622e.task_run_profile);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2280o = (EditText) findViewById(C0621d.myProfileName);
        m2892j();
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

    public void onSelectProfileClick(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.pro.action.CHOOSE_PROFILE");
            try {
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
                return;
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
                return;
            }
        }
        intent = new Intent();
        intent.setAction("com.wakdev.nfctools.pro.action.CHOOSE_PROFILE");
        try {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        } catch (Exception e2) {
            C0493f.m2081a(this, getString(C0625h.error));
        }
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f2280o.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_package_name_is_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2279n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f2282q);
        intent.putExtra("itemUpdate", this.f2281p);
        intent.putExtra("itemFields", m2893k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
