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

public class bw extends C0316b {
    private static final int f2177n;
    private boolean f2178o;
    private String f2179p;
    private EditText f2180q;
    private EditText f2181r;

    static {
        f2177n = C0481m.TASK_NETWORK_HTTP_AUTH.cM;
    }

    public bw() {
        this.f2178o = false;
        this.f2179p = null;
    }

    private void m2843j() {
        Intent intent = getIntent();
        this.f2178o = intent.getBooleanExtra("itemUpdate", false);
        this.f2179p = intent.getStringExtra("itemHash");
        if (this.f2178o && this.f2179p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2180q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2181r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2844k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2180q.getText().toString());
        hashMap.put("field2", this.f2181r.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty()) {
                if ("field1".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f2180q.setText(new StringBuffer(this.f2180q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2180q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2180q.setText(this.f2180q.getText().toString() + stringExtra);
                        this.f2180q.setSelection(this.f2180q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f2181r.setText(new StringBuffer(this.f2181r.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f2181r.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f2181r.setText(this.f2181r.getText().toString() + stringExtra);
                this.f2181r.setSelection(this.f2181r.length());
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
        setContentView(C0622e.task_http_auth);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2180q = (EditText) findViewById(C0621d.myLogin);
        this.f2181r = (EditText) findViewById(C0621d.myPassword);
        m2843j();
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

    public void onSelectVarsButtonClick1(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field1");
        intent.putExtra("kSelectionField", this.f2180q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f2181r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f2180q.getText().toString();
        String obj2 = this.f2181r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        String str = getString(C0625h.task_http_auth_login) + " " + obj + "\n" + getString(C0625h.task_http_auth_password) + " " + obj2;
        obj = obj + "|" + obj2;
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2177n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f2179p);
        intent.putExtra("itemUpdate", this.f2178o);
        intent.putExtra("itemFields", m2844k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
