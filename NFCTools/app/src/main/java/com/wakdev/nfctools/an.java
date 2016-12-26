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
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class an extends C0316b {
    private static final int f1943n;
    private boolean f1944o;
    private String f1945p;
    private EditText f1946q;
    private Spinner f1947r;

    static {
        f1943n = C0481m.TASK_COND_IS_FILE_EXIST.cM;
    }

    public an() {
        this.f1944o = false;
        this.f1945p = null;
    }

    private void m2689j() {
        Intent intent = getIntent();
        this.f1944o = intent.getBooleanExtra("itemUpdate", false);
        this.f1945p = intent.getStringExtra("itemHash");
        if (this.f1944o && this.f1945p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1946q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1947r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2690k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1946q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1947r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2691l() {
        String obj = this.f1946q.getText().toString();
        return obj + "|" + String.valueOf(this.f1947r.getSelectedItemPosition());
    }

    private String m2692m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = getString(C0625h.task_cond_if_file_exist_title) + " : " + this.f1946q.getText().toString();
        if (this.f1947r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1946q.setText(stringExtra);
                this.f1946q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_cond_file_exist);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1946q = (EditText) findViewById(C0621d.myFilePath);
        this.f1947r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1947r.setSelection(1);
        m2689j();
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

    public void onValidateButtonClick(View view) {
        if (this.f1946q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1943n);
        intent.putExtra("itemTask", m2691l());
        intent.putExtra("itemDescription", m2692m());
        intent.putExtra("itemHash", this.f1945p);
        intent.putExtra("itemUpdate", this.f1944o);
        intent.putExtra("itemFields", m2690k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
