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

public class bm extends C0316b {
    private static final int f2125n;
    private boolean f2126o;
    private String f2127p;
    private EditText f2128q;
    private Spinner f2129r;

    static {
        f2125n = C0481m.TASK_COND_IS_VAR_EXIST.cM;
    }

    public bm() {
        this.f2126o = false;
        this.f2127p = null;
    }

    private void m2807j() {
        Intent intent = getIntent();
        this.f2126o = intent.getBooleanExtra("itemUpdate", false);
        this.f2127p = intent.getStringExtra("itemHash");
        if (this.f2126o && this.f2127p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2128q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2129r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2808k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2128q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f2129r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2809l() {
        String obj = this.f2128q.getText().toString();
        return obj + "|" + String.valueOf(this.f2129r.getSelectedItemPosition());
    }

    private String m2810m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = getString(C0625h.task_cond_if_var_exist_title) + " : " + this.f2128q.getText().toString();
        if (this.f2129r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            CharSequence stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field1".equals(stringExtra2)) {
                this.f2128q.setText(stringExtra);
                this.f2128q.setSelection(this.f2128q.length());
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
        setContentView(C0622e.task_cond_var_exist);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2128q = (EditText) findViewById(C0621d.myText);
        this.f2129r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2129r.setSelection(1);
        m2807j();
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
        intent.putExtra("kSelectionField", this.f2128q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (this.f2128q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2125n);
        intent.putExtra("itemTask", m2809l());
        intent.putExtra("itemDescription", m2810m());
        intent.putExtra("itemHash", this.f2127p);
        intent.putExtra("itemUpdate", this.f2126o);
        intent.putExtra("itemFields", m2808k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
