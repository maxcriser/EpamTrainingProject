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

public class bn extends C0316b {
    private static final int f2130n;
    private boolean f2131o;
    private String f2132p;
    private EditText f2133q;
    private EditText f2134r;
    private Spinner f2135s;
    private Spinner f2136t;

    static {
        f2130n = C0481m.TASK_COND_IS_VAR_RANGE.cM;
    }

    public bn() {
        this.f2131o = false;
        this.f2132p = null;
    }

    private void m2811j() {
        Intent intent = getIntent();
        this.f2131o = intent.getBooleanExtra("itemUpdate", false);
        this.f2132p = intent.getStringExtra("itemHash");
        if (this.f2131o && this.f2132p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2133q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2134r, (String) hashMap.get("field2"));
                C0490e.m2078a(this.f2135s, (String) hashMap.get("field3"));
                C0490e.m2078a(this.f2136t, (String) hashMap.get("field4"));
            }
        }
    }

    private HashMap<String, String> m2812k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2133q.getText().toString());
        hashMap.put("field2", this.f2134r.getText().toString());
        hashMap.put("field3", String.valueOf(this.f2135s.getSelectedItemPosition()));
        hashMap.put("field4", String.valueOf(this.f2136t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2813l() {
        String obj = this.f2133q.getText().toString();
        String obj2 = this.f2134r.getText().toString();
        String valueOf = String.valueOf(this.f2135s.getSelectedItemPosition());
        return obj + "|" + obj2 + "|" + valueOf + "|" + String.valueOf(this.f2136t.getSelectedItemPosition());
    }

    private String m2814m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.task_cond_if_var_range_array);
        String str = String.format(getString(C0625h.task_cond_if_var_range_if), new Object[]{this.f2133q.getText().toString()}) + " " + stringArray[this.f2135s.getSelectedItemPosition()].toLowerCase() + " " + this.f2134r.getText().toString();
        if (this.f2136t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    private boolean m2815n() {
        String obj = this.f2133q.getText().toString();
        String obj2 = this.f2134r.getText().toString();
        if (!obj.isEmpty() && !obj2.isEmpty()) {
            return true;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
        return false;
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
                        this.f2133q.setText(new StringBuffer(this.f2133q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2133q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2133q.setText(this.f2133q.getText().toString() + stringExtra);
                        this.f2133q.setSelection(this.f2133q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f2134r.setText(new StringBuffer(this.f2134r.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f2134r.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f2134r.setText(this.f2134r.getText().toString() + stringExtra);
                this.f2134r.setSelection(this.f2134r.length());
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
        setContentView(C0622e.task_cond_var_range);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2133q = (EditText) findViewById(C0621d.myValue1);
        this.f2134r = (EditText) findViewById(C0621d.myValue2);
        this.f2135s = (Spinner) findViewById(C0621d.myRangeSpinner);
        this.f2136t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2136t.setSelection(1);
        m2811j();
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
        intent.putExtra("kSelectionField", this.f2133q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f2134r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (m2815n()) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2130n);
            intent.putExtra("itemTask", m2813l());
            intent.putExtra("itemDescription", m2814m());
            intent.putExtra("itemHash", this.f2132p);
            intent.putExtra("itemUpdate", this.f2131o);
            intent.putExtra("itemFields", m2812k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }
}
