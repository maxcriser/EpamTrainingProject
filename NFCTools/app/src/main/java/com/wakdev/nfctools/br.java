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

public class br extends C0316b {
    private static final int f2152n;
    private boolean f2153o;
    private String f2154p;
    private EditText f2155q;
    private Spinner f2156r;

    static {
        f2152n = C0481m.TASK_COND_YES_NO_DIALOG.cM;
    }

    public br() {
        this.f2153o = false;
        this.f2154p = null;
    }

    private void m2828j() {
        Intent intent = getIntent();
        this.f2153o = intent.getBooleanExtra("itemUpdate", false);
        this.f2154p = intent.getStringExtra("itemHash");
        if (this.f2153o && this.f2154p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2155q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2156r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2829k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2155q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f2156r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2830l() {
        String obj = this.f2155q.getText().toString();
        return obj + "|" + String.valueOf(this.f2156r.getSelectedItemPosition());
    }

    private String m2831m() {
        String string = getString(C0625h.cond_desc_exclude);
        String obj = this.f2155q.getText().toString();
        if (this.f2156r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return obj + "\n" + getString(C0625h.task_cond_yes_no_dialog_title) + " " + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field1".equals(stringExtra2)) {
                if (intExtra != -1) {
                    this.f2155q.setText(new StringBuffer(this.f2155q.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f2155q.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f2155q.setText(this.f2155q.getText().toString() + stringExtra);
                this.f2155q.setSelection(this.f2155q.length());
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
        setContentView(C0622e.task_cond_yes_no_dialog);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2155q = (EditText) findViewById(C0621d.myQuestion);
        this.f2156r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2156r.setSelection(1);
        m2828j();
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
        intent.putExtra("kSelectionField", this.f2155q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (this.f2155q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2152n);
        intent.putExtra("itemTask", m2830l());
        intent.putExtra("itemDescription", m2831m());
        intent.putExtra("itemHash", this.f2154p);
        intent.putExtra("itemUpdate", this.f2153o);
        intent.putExtra("itemFields", m2829k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
