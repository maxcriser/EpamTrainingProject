package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class am extends C0316b {
    private static final int f1936n;
    private boolean f1937o;
    private String f1938p;
    private EditText f1939q;
    private EditText f1940r;
    private CheckBox f1941s;
    private Spinner f1942t;

    static {
        f1936n = C0481m.TASK_COND_IS_FILE_CONTENT.cM;
    }

    public am() {
        this.f1937o = false;
        this.f1938p = null;
    }

    private void m2685j() {
        Intent intent = getIntent();
        this.f1937o = intent.getBooleanExtra("itemUpdate", false);
        this.f1938p = intent.getStringExtra("itemHash");
        if (this.f1937o && this.f1938p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1939q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1940r, (String) hashMap.get("field2"));
                C0490e.m2074a(this.f1941s, (String) hashMap.get("field3"));
                C0490e.m2078a(this.f1942t, (String) hashMap.get("field4"));
            }
        }
    }

    private HashMap<String, String> m2686k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1939q.getText().toString());
        hashMap.put("field2", this.f1940r.getText().toString());
        hashMap.put("field3", String.valueOf(this.f1941s.isChecked()));
        hashMap.put("field4", String.valueOf(this.f1942t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2687l() {
        String obj = this.f1939q.getText().toString();
        String obj2 = this.f1940r.getText().toString();
        return obj + "|" + obj2 + "|" + (this.f1941s.isChecked() ? "1" : "0") + "|" + String.valueOf(this.f1942t.getSelectedItemPosition());
    }

    private String m2688m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = this.f1939q.getText().toString() + "\n" + getString(C0625h.task_cond_if_file_content_contains) + " " + this.f1940r.getText().toString();
        str = this.f1941s.isChecked() ? str + "\n" + getString(C0625h.task_cond_if_file_content_match) + " : " + getString(C0625h.yes) : str + "\n" + getString(C0625h.task_cond_if_file_content_match) + " : " + getString(C0625h.no);
        if (this.f1942t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (!(stringExtra == null || stringExtra.isEmpty())) {
                this.f1939q.setText(stringExtra);
                this.f1939q.setSelection(stringExtra.length());
            }
        }
        if (i2 == -1 && i == 2 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra2 = intent.getStringExtra("kResultValue");
            String stringExtra3 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra2 != null && !stringExtra2.isEmpty() && stringExtra3 != null && !stringExtra3.isEmpty() && "field2".equals(stringExtra3)) {
                if (intExtra != -1) {
                    this.f1940r.setText(new StringBuffer(this.f1940r.getText().toString()).insert(intExtra, stringExtra2).toString());
                    this.f1940r.setSelection(stringExtra2.length() + intExtra);
                    return;
                }
                this.f1940r.setText(this.f1940r.getText().toString() + stringExtra2);
                this.f1940r.setSelection(this.f1940r.length());
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
        setContentView(C0622e.task_cond_file_content);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1939q = (EditText) findViewById(C0621d.myFilePath);
        this.f1940r = (EditText) findViewById(C0621d.myContent);
        this.f1941s = (CheckBox) findViewById(C0621d.checkBoxExactMatch);
        this.f1942t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1942t.setSelection(1);
        m2685j();
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

    public void onSelectFileClick(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.FILE_MANAGER");
            intent.putExtra("kIntentKeySelectionType", 1);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_open_file_select));
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.FILE_MANAGER");
            intent.putExtra("kIntentKeySelectionType", 1);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_open_file_select));
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e2) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
            }
        } else {
            C0493f.m2081a(this, getString(C0625h.need_nfctasks));
        }
    }

    public void onSelectVarsButtonClick1(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f1940r.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (this.f1939q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1936n);
        intent.putExtra("itemTask", m2687l());
        intent.putExtra("itemDescription", m2688m());
        intent.putExtra("itemHash", this.f1938p);
        intent.putExtra("itemUpdate", this.f1937o);
        intent.putExtra("itemFields", m2686k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
