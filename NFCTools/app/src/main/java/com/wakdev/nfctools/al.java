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

public class al extends C0316b {
    private static final int f1931n;
    private boolean f1932o;
    private String f1933p;
    private EditText f1934q;
    private Spinner f1935r;

    static {
        f1931n = C0481m.TASK_COND_IS_DIRECTORY_EXIST.cM;
    }

    public al() {
        this.f1932o = false;
        this.f1933p = null;
    }

    private void m2681j() {
        Intent intent = getIntent();
        this.f1932o = intent.getBooleanExtra("itemUpdate", false);
        this.f1933p = intent.getStringExtra("itemHash");
        if (this.f1932o && this.f1933p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1934q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1935r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2682k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1934q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1935r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2683l() {
        String obj = this.f1934q.getText().toString();
        return obj + "|" + String.valueOf(this.f1935r.getSelectedItemPosition());
    }

    private String m2684m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = getString(C0625h.task_cond_if_directory_exist_title) + " : " + this.f1934q.getText().toString();
        if (this.f1935r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1934q.setText(stringExtra);
                this.f1934q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_cond_dir_exist);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1934q = (EditText) findViewById(C0621d.myFilePath);
        this.f1935r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1935r.setSelection(1);
        m2681j();
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
            intent.putExtra("kIntentKeySelectionType", 2);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_cond_if_directory_select));
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.FILE_MANAGER");
            intent.putExtra("kIntentKeySelectionType", 2);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_cond_if_directory_select));
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
        if (this.f1934q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1931n);
        intent.putExtra("itemTask", m2683l());
        intent.putExtra("itemDescription", m2684m());
        intent.putExtra("itemHash", this.f1933p);
        intent.putExtra("itemUpdate", this.f1932o);
        intent.putExtra("itemFields", m2682k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
