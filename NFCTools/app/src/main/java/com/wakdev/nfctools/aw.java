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
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class aw extends C0316b {
    private static final int f1991n;
    private boolean f1992o;
    private String f1993p;
    private EditText f1994q;
    private Spinner f1995r;
    private Spinner f1996s;

    static {
        f1991n = C0481m.TASK_COND_IS_WEBSITE_REACHABLE.cM;
    }

    public aw() {
        this.f1992o = false;
        this.f1993p = null;
    }

    private void m2725j() {
        Intent intent = getIntent();
        this.f1992o = intent.getBooleanExtra("itemUpdate", false);
        this.f1993p = intent.getStringExtra("itemHash");
        if (this.f1992o && this.f1993p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1994q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1995r, (String) hashMap.get("field2"));
                C0490e.m2078a(this.f1996s, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2726k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1994q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1995r.getSelectedItemPosition()));
        hashMap.put("field3", String.valueOf(this.f1996s.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2727l() {
        String obj = this.f1994q.getText().toString();
        String valueOf = String.valueOf(this.f1995r.getSelectedItemPosition());
        return obj + "|" + valueOf + "|" + String.valueOf(this.f1996s.getSelectedItemPosition());
    }

    private String m2728m() {
        String string = getString(C0625h.cond_desc_exclude);
        String obj = this.f1994q.getText().toString();
        String[] stringArray = getResources().getStringArray(C0619b.task_cond_is_website_reachable_array);
        if (this.f1996s.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return obj + "\n" + stringArray[this.f1995r.getSelectedItemPosition()] + " : " + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field1".equals(stringExtra2)) {
                if (intExtra != -1) {
                    this.f1994q.setText(new StringBuffer(this.f1994q.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1994q.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1994q.setText(this.f1994q.getText().toString() + stringExtra);
                this.f1994q.setSelection(this.f1994q.length());
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
        setContentView(C0622e.task_cond_is_website_reachable);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1994q = (EditText) findViewById(C0621d.myUrl);
        this.f1995r = (Spinner) findViewById(C0621d.state_spinner);
        this.f1996s = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1996s.setSelection(1);
        m2725j();
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
        intent.putExtra("kSelectionField", this.f1994q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (this.f1994q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1991n);
        intent.putExtra("itemTask", m2727l());
        intent.putExtra("itemDescription", m2728m());
        intent.putExtra("itemHash", this.f1993p);
        intent.putExtra("itemUpdate", this.f1992o);
        intent.putExtra("itemFields", m2726k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
