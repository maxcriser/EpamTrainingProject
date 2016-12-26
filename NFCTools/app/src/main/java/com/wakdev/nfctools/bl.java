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

public class bl extends C0316b {
    private static final int f2119n;
    private boolean f2120o;
    private String f2121p;
    private EditText f2122q;
    private EditText f2123r;
    private Spinner f2124s;

    static {
        f2119n = C0481m.TASK_COND_IS_VAR_EQUAL.cM;
    }

    public bl() {
        this.f2120o = false;
        this.f2121p = null;
    }

    private void m2803j() {
        Intent intent = getIntent();
        this.f2120o = intent.getBooleanExtra("itemUpdate", false);
        this.f2121p = intent.getStringExtra("itemHash");
        if (this.f2120o && this.f2121p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2122q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2123r, (String) hashMap.get("field2"));
                C0490e.m2078a(this.f2124s, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2804k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2122q.getText().toString());
        hashMap.put("field2", this.f2123r.getText().toString());
        hashMap.put("field3", String.valueOf(this.f2124s.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2805l() {
        String obj = this.f2122q.getText().toString();
        String obj2 = this.f2123r.getText().toString();
        return obj + "|" + obj2 + "|" + String.valueOf(this.f2124s.getSelectedItemPosition());
    }

    private String m2806m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = (getString(C0625h.task_cond_if_var_equal_equal) + "\n" + this.f2122q.getText().toString()) + "\n" + this.f2123r.getText().toString();
        if (this.f2124s.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
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
                        this.f2122q.setText(new StringBuffer(this.f2122q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2122q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2122q.setText(this.f2122q.getText().toString() + stringExtra);
                        this.f2122q.setSelection(this.f2122q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f2123r.setText(new StringBuffer(this.f2123r.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f2123r.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f2123r.setText(this.f2123r.getText().toString() + stringExtra);
                this.f2123r.setSelection(this.f2123r.length());
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
        setContentView(C0622e.task_cond_var_equal);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2122q = (EditText) findViewById(C0621d.myValue1);
        this.f2123r = (EditText) findViewById(C0621d.myValue2);
        this.f2124s = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2124s.setSelection(1);
        m2803j();
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
        intent.putExtra("kSelectionField", this.f2122q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f2123r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f2122q.getText().toString();
        String obj2 = this.f2123r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2119n);
        intent.putExtra("itemTask", m2805l());
        intent.putExtra("itemDescription", m2806m());
        intent.putExtra("itemHash", this.f2121p);
        intent.putExtra("itemUpdate", this.f2120o);
        intent.putExtra("itemFields", m2804k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
