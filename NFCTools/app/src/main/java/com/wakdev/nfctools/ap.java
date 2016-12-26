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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class ap extends C0316b {
    private static final int f1953n;
    private boolean f1954o;
    private String f1955p;
    private EditText f1956q;
    private EditText f1957r;
    private Spinner f1958s;

    static {
        f1953n = C0481m.TASK_COND_HTTP_GET.cM;
    }

    public ap() {
        this.f1954o = false;
        this.f1955p = null;
    }

    private void m2697j() {
        Intent intent = getIntent();
        this.f1954o = intent.getBooleanExtra("itemUpdate", false);
        this.f1955p = intent.getStringExtra("itemHash");
        if (this.f1954o && this.f1955p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1956q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1957r, (String) hashMap.get("field2"));
                C0490e.m2078a(this.f1958s, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2698k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1956q.getText().toString());
        hashMap.put("field2", this.f1957r.getText().toString());
        hashMap.put("field3", String.valueOf(this.f1958s.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2699l() {
        String obj = this.f1956q.getText().toString();
        String obj2 = this.f1957r.getText().toString();
        String valueOf = String.valueOf(this.f1958s.getSelectedItemPosition());
        try {
            obj = obj.replace("|", URLEncoder.encode("|", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }
        return obj + "|" + obj2 + "|" + valueOf;
    }

    private String m2700m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = this.f1956q.getText().toString() + "\n" + getString(C0625h.task_cond_http_get_return_value) + " " + this.f1957r.getText().toString();
        if (this.f1958s.getSelectedItemPosition() == 1) {
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
                        this.f1956q.setText(new StringBuffer(this.f1956q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f1956q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f1956q.setText(this.f1956q.getText().toString() + stringExtra);
                        this.f1956q.setSelection(this.f1956q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f1957r.setText(new StringBuffer(this.f1957r.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1957r.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1957r.setText(this.f1957r.getText().toString() + stringExtra);
                this.f1957r.setSelection(this.f1957r.length());
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
        setContentView(C0622e.task_cond_http_get);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1956q = (EditText) findViewById(C0621d.myUrl);
        this.f1957r = (EditText) findViewById(C0621d.myValue);
        this.f1958s = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1958s.setSelection(1);
        m2697j();
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
        intent.putExtra("kSelectionField", this.f1956q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f1957r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1956q.getText().toString();
        String obj2 = this.f1957r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1953n);
        intent.putExtra("itemTask", m2699l());
        intent.putExtra("itemDescription", m2700m());
        intent.putExtra("itemHash", this.f1955p);
        intent.putExtra("itemUpdate", this.f1954o);
        intent.putExtra("itemFields", m2698k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
