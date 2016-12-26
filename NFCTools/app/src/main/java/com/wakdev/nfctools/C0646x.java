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

/* renamed from: com.wakdev.nfctools.x */
public class C0646x extends C0316b {
    private static final int f2441n;
    private boolean f2442o;
    private String f2443p;
    private EditText f2444q;
    private Spinner f2445r;

    static {
        f2441n = C0481m.TASK_COND_IS_APP_INSTALLED.cM;
    }

    public C0646x() {
        this.f2442o = false;
        this.f2443p = null;
    }

    private void m3080j() {
        Intent intent = getIntent();
        this.f2442o = intent.getBooleanExtra("itemUpdate", false);
        this.f2443p = intent.getStringExtra("itemHash");
        if (this.f2442o && this.f2443p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2444q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2445r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m3081k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2444q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f2445r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m3082l() {
        String obj = this.f2444q.getText().toString();
        return obj + "|" + String.valueOf(this.f2445r.getSelectedItemPosition());
    }

    private String m3083m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = getString(C0625h.task_if_app_title) + " : " + this.f2444q.getText().toString();
        if (this.f2445r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            Object stringExtra = intent.getStringExtra("packageName");
            this.f2444q.setText(stringExtra);
            this.f2444q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_cond_app_installed);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2444q = (EditText) findViewById(C0621d.myPackage);
        this.f2445r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2445r.setSelection(1);
        m3080j();
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

    public void onSelectPackageButtonClick(View view) {
        startActivityForResult(new Intent(this, ChoosePackageActivity.class), 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (this.f2444q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2441n);
        intent.putExtra("itemTask", m3082l());
        intent.putExtra("itemDescription", m3083m());
        intent.putExtra("itemHash", this.f2443p);
        intent.putExtra("itemUpdate", this.f2442o);
        intent.putExtra("itemFields", m3081k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
