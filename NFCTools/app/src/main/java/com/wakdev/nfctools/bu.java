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

public class bu extends C0316b {
    private static final int f2167n;
    private boolean f2168o;
    private String f2169p;
    private EditText f2170q;
    private EditText f2171r;

    static {
        f2167n = C0481m.TASK_DOWNLOAD_FILE.cM;
    }

    public bu() {
        this.f2168o = false;
        this.f2169p = null;
    }

    private void m2838j() {
        Intent intent = getIntent();
        this.f2168o = intent.getBooleanExtra("itemUpdate", false);
        this.f2169p = intent.getStringExtra("itemHash");
        if (this.f2168o && this.f2169p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2170q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2171r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2839k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2170q.getText().toString());
        hashMap.put("field2", this.f2171r.getText().toString());
        return hashMap;
    }

    private boolean m2840l() {
        String obj = this.f2170q.getText().toString();
        String obj2 = this.f2171r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return false;
        } else if (C0503m.m2108b(obj2)) {
            return true;
        } else {
            C0493f.m2081a(this, getString(C0625h.task_download_file_err_incorrect_folder_path));
            return false;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (!(stringExtra == null || stringExtra.isEmpty())) {
                this.f2171r.setText(stringExtra);
                this.f2171r.setSelection(stringExtra.length());
            }
        }
        if (i2 == -1 && i == 2 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra2 = intent.getStringExtra("kResultValue");
            String stringExtra3 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra2 != null && !stringExtra2.isEmpty() && stringExtra3 != null && !stringExtra3.isEmpty() && "field1".equals(stringExtra3)) {
                if (intExtra != -1) {
                    this.f2170q.setText(new StringBuffer(this.f2170q.getText().toString()).insert(intExtra, stringExtra2).toString());
                    this.f2170q.setSelection(stringExtra2.length() + intExtra);
                    return;
                }
                this.f2170q.setText(this.f2170q.getText().toString() + stringExtra2);
                this.f2170q.setSelection(this.f2170q.length());
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
        setContentView(C0622e.task_download_file);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2170q = (EditText) findViewById(C0621d.myRequest);
        this.f2171r = (EditText) findViewById(C0621d.myFolderPath);
        m2838j();
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
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_download_file_folder_select));
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.FILE_MANAGER");
            intent.putExtra("kIntentKeySelectionType", 2);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_download_file_folder_select));
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
        intent.putExtra("kSelectionField", this.f2170q.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (m2840l()) {
            String obj = this.f2170q.getText().toString();
            String obj2 = this.f2171r.getText().toString();
            String str = obj + "|" + obj2;
            obj = (getString(C0625h.task_download_file_address_title) + " " + obj + "\n") + getString(C0625h.task_download_file_folder_title) + " " + obj2;
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2167n);
            intent.putExtra("itemTask", str);
            intent.putExtra("itemDescription", obj);
            intent.putExtra("itemHash", this.f2169p);
            intent.putExtra("itemUpdate", this.f2168o);
            intent.putExtra("itemFields", m2839k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }
}
