package com.wakdev.nfctools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.commons.C0503m;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class TaskScreenshotActivity extends C0316b {
    private static final int f1717n;
    private boolean f1718o;
    private String f1719p;
    private EditText f1720q;
    private EditText f1721r;

    static {
        f1717n = C0481m.TASK_SCREENSHOT.cM;
    }

    public TaskScreenshotActivity() {
        this.f1718o = false;
        this.f1719p = null;
    }

    private void m2560j() {
        Intent intent = getIntent();
        this.f1718o = intent.getBooleanExtra("itemUpdate", false);
        this.f1719p = intent.getStringExtra("itemHash");
        if (this.f1718o && this.f1719p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1720q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1721r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2561k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1720q.getText().toString());
        hashMap.put("field2", this.f1721r.getText().toString());
        return hashMap;
    }

    private boolean m2562l() {
        String obj = this.f1720q.getText().toString();
        String obj2 = this.f1721r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return false;
        } else if (C0503m.m2108b(obj2)) {
            return true;
        } else {
            C0493f.m2081a(this, getString(C0625h.task_write_err_incorrect_folder_path));
            return false;
        }
    }

    private String m2563m() {
        return this.f1721r.getText().toString() + "/" + this.f1720q.getText().toString() + getString(C0625h.task_screenshot_ext);
    }

    private String m2564n() {
        return this.f1721r.getText().toString() + "/" + this.f1720q.getText().toString() + getString(C0625h.task_screenshot_ext);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (!(stringExtra == null || stringExtra.isEmpty())) {
                this.f1721r.setText(stringExtra);
                this.f1721r.setSelection(stringExtra.length());
            }
        }
        if (i2 == -1 && i == 2 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra2 = intent.getStringExtra("kResultValue");
            String stringExtra3 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra2 != null && !stringExtra2.isEmpty() && stringExtra3 != null && !stringExtra3.isEmpty() && "field1".equals(stringExtra3)) {
                if (intExtra != -1) {
                    this.f1720q.setText(new StringBuffer(this.f1720q.getText().toString()).insert(intExtra, stringExtra2).toString());
                    this.f1720q.setSelection(stringExtra2.length() + intExtra);
                    return;
                }
                this.f1720q.setText(this.f1720q.getText().toString() + stringExtra2);
                this.f1720q.setSelection(this.f1720q.length());
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
        setContentView(C0622e.task_screenshot);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1720q = (EditText) findViewById(C0621d.myFileName);
        this.f1721r = (EditText) findViewById(C0621d.myFolderPath);
        m2560j();
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
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_write_file_select));
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.FILE_MANAGER");
            intent.putExtra("kIntentKeySelectionType", 2);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_write_file_select));
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
        intent.putExtra("kTargetField", "field1");
        intent.putExtra("kSelectionField", this.f1720q.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    @SuppressLint({"DefaultLocale"})
    public void onValidateButtonClick(View view) {
        if (m2562l()) {
            String m = m2563m();
            String n = m2564n();
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1717n);
            intent.putExtra("itemTask", m);
            intent.putExtra("itemDescription", n);
            intent.putExtra("itemHash", this.f1719p);
            intent.putExtra("itemUpdate", this.f1718o);
            intent.putExtra("itemFields", m2561k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }
}
