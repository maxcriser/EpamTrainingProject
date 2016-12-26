package com.wakdev.nfctools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class TaskOpenFileActivity extends C0316b {
    private static final int f1667n;
    private boolean f1668o;
    private String f1669p;
    private EditText f1670q;
    private Button f1671r;

    static {
        f1667n = C0481m.TASK_MISC_OPEN_FILE.cM;
    }

    public TaskOpenFileActivity() {
        this.f1668o = false;
        this.f1669p = null;
    }

    private void m2530j() {
        Intent intent = getIntent();
        this.f1668o = intent.getBooleanExtra("itemUpdate", false);
        this.f1669p = intent.getStringExtra("itemHash");
        if (this.f1668o && this.f1669p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1670q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2531k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1670q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1670q.setText(stringExtra);
                this.f1670q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_open_file);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1670q = (EditText) findViewById(C0621d.myFilePath);
        this.f1671r = (Button) findViewById(C0621d.mySearchButton);
        m2530j();
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

    @SuppressLint({"DefaultLocale"})
    public void onValidateButtonClick(View view) {
        String obj = this.f1670q.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1667n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1669p);
        intent.putExtra("itemUpdate", this.f1668o);
        intent.putExtra("itemFields", m2531k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
