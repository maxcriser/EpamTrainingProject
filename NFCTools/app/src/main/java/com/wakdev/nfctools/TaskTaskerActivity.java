package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.TaskerIntent;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class TaskTaskerActivity extends C0316b {
    private static final int f1807n;
    private boolean f1808o;
    private String f1809p;
    private EditText f1810q;

    static {
        f1807n = C0481m.TASK_RUN_TASKER.cM;
    }

    public TaskTaskerActivity() {
        this.f1808o = false;
        this.f1809p = null;
    }

    private void m2603j() {
        Intent intent = getIntent();
        this.f1808o = intent.getBooleanExtra("itemUpdate", false);
        this.f1809p = intent.getStringExtra("itemHash");
        if (this.f1808o && this.f1809p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1810q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2604k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1810q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            try {
                Object uri = intent.getData().toString();
                if (uri != null) {
                    this.f1810q.setText(uri);
                    this.f1810q.setSelection(uri.length());
                }
            } catch (Exception e) {
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
        setContentView(C0622e.task_tasker);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1810q = (EditText) findViewById(C0621d.myTaskerTaskRecord);
        m2603j();
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

    public void onSelectTaskButtonClick(View view) {
        if (TaskerIntent.m2040b(this)) {
            startActivityForResult(TaskerIntent.m2036a(), 1);
        } else {
            C0493f.m2081a(this, getString(C0625h.task_run_tasker_err_not_installed));
        }
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1810q.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1807n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1809p);
        intent.putExtra("itemUpdate", this.f1808o);
        intent.putExtra("itemFields", m2604k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
