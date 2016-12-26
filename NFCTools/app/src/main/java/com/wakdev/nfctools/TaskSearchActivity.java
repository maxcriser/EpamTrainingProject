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

public class TaskSearchActivity extends C0316b {
    private static final int f1722n;
    private boolean f1723o;
    private String f1724p;
    private EditText f1725q;
    private Spinner f1726r;

    static {
        f1722n = C0481m.TASK_MISC_SEARCH.cM;
    }

    public TaskSearchActivity() {
        this.f1723o = false;
        this.f1724p = null;
    }

    private void m2565j() {
        Intent intent = getIntent();
        this.f1723o = intent.getBooleanExtra("itemUpdate", false);
        this.f1724p = intent.getStringExtra("itemHash");
        if (this.f1723o && this.f1724p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1726r, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1725q, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2566k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1726r.getSelectedItemPosition()));
        hashMap.put("field2", this.f1725q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2 && i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field2".equals(stringExtra2)) {
                if (intExtra != -1) {
                    this.f1725q.setText(new StringBuffer(this.f1725q.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1725q.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1725q.setText(this.f1725q.getText().toString() + stringExtra);
                this.f1725q.setSelection(this.f1725q.length());
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
        setContentView(C0622e.task_search);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1725q = (EditText) findViewById(C0621d.myKeyword);
        this.f1726r = (Spinner) findViewById(C0621d.searchEngineSpinner);
        m2565j();
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
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f1725q.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1725q.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        try {
            int selectedItemPosition = this.f1726r.getSelectedItemPosition();
            String str = getResources().getStringArray(C0619b.record_search_engine_arrays)[selectedItemPosition] + " : " + obj;
            obj = String.valueOf(selectedItemPosition) + "|" + obj;
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1722n);
            intent.putExtra("itemTask", obj);
            intent.putExtra("itemDescription", str);
            intent.putExtra("itemHash", this.f1724p);
            intent.putExtra("itemUpdate", this.f1723o);
            intent.putExtra("itemFields", m2566k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } catch (Exception e) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
        }
    }
}
