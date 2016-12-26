package com.wakdev.nfctools;

import android.annotation.SuppressLint;
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
import com.wakdev.libs.commons.C0503m;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class cv extends C0316b {
    private static final int f2336n;
    private boolean f2337o;
    private String f2338p;
    private EditText f2339q;
    private EditText f2340r;
    private EditText f2341s;
    private Spinner f2342t;

    static {
        f2336n = C0481m.TASK_MISC_WRITE_FILE.cM;
    }

    public cv() {
        this.f2337o = false;
        this.f2338p = null;
    }

    private void m2919j() {
        Intent intent = getIntent();
        this.f2337o = intent.getBooleanExtra("itemUpdate", false);
        this.f2338p = intent.getStringExtra("itemHash");
        if (this.f2337o && this.f2338p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2339q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2340r, (String) hashMap.get("field2"));
                C0490e.m2078a(this.f2342t, (String) hashMap.get("field3"));
                C0490e.m2075a(this.f2341s, (String) hashMap.get("field4"));
            }
        }
    }

    private HashMap<String, String> m2920k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2339q.getText().toString());
        hashMap.put("field2", this.f2340r.getText().toString());
        hashMap.put("field3", String.valueOf(this.f2342t.getSelectedItemPosition()));
        hashMap.put("field4", this.f2341s.getText().toString());
        return hashMap;
    }

    private boolean m2921l() {
        String obj = this.f2339q.getText().toString();
        String obj2 = this.f2340r.getText().toString();
        String obj3 = this.f2341s.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty() || obj3.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return false;
        } else if (C0503m.m2108b(obj2)) {
            return true;
        } else {
            C0493f.m2081a(this, getString(C0625h.task_write_err_incorrect_folder_path));
            return false;
        }
    }

    private String m2922m() {
        String obj = this.f2339q.getText().toString();
        String obj2 = this.f2340r.getText().toString();
        return obj2 + "/" + obj + "|" + String.valueOf(this.f2342t.getSelectedItemPosition()) + "|" + this.f2341s.getText().toString();
    }

    private String m2923n() {
        String obj = this.f2339q.getText().toString();
        String obj2 = this.f2340r.getText().toString();
        return getResources().getStringArray(C0619b.task_write_file_mode_arrays)[this.f2342t.getSelectedItemPosition()] + " : " + obj2 + "/" + obj + "\n" + this.f2341s.getText().toString();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (!(stringExtra == null || stringExtra.isEmpty())) {
                this.f2340r.setText(stringExtra);
                this.f2340r.setSelection(stringExtra.length());
            }
        }
        if (i2 == -1 && i == 2 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra2 = intent.getStringExtra("kResultValue");
            String stringExtra3 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra2 != null && !stringExtra2.isEmpty() && stringExtra3 != null && !stringExtra3.isEmpty()) {
                if ("field1".equals(stringExtra3)) {
                    if (intExtra != -1) {
                        this.f2339q.setText(new StringBuffer(this.f2339q.getText().toString()).insert(intExtra, stringExtra2).toString());
                        this.f2339q.setSelection(stringExtra2.length() + intExtra);
                    } else {
                        this.f2339q.setText(this.f2339q.getText().toString() + stringExtra2);
                        this.f2339q.setSelection(this.f2339q.length());
                    }
                }
                if (!"field4".equals(stringExtra3)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f2341s.setText(new StringBuffer(this.f2341s.getText().toString()).insert(intExtra, stringExtra2).toString());
                    this.f2341s.setSelection(stringExtra2.length() + intExtra);
                    return;
                }
                this.f2341s.setText(this.f2341s.getText().toString() + stringExtra2);
                this.f2341s.setSelection(this.f2341s.length());
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
        setContentView(C0622e.task_write_file);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2339q = (EditText) findViewById(C0621d.myFileName);
        this.f2340r = (EditText) findViewById(C0621d.myFolderPath);
        this.f2342t = (Spinner) findViewById(C0621d.modeSpinner);
        this.f2341s = (EditText) findViewById(C0621d.myContent);
        m2919j();
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
        intent.putExtra("kSelectionField", this.f2339q.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field4");
        intent.putExtra("kSelectionField", this.f2341s.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    @SuppressLint({"DefaultLocale"})
    public void onValidateButtonClick(View view) {
        if (m2921l()) {
            String m = m2922m();
            String n = m2923n();
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2336n);
            intent.putExtra("itemTask", m);
            intent.putExtra("itemDescription", n);
            intent.putExtra("itemHash", this.f2338p);
            intent.putExtra("itemUpdate", this.f2337o);
            intent.putExtra("itemFields", m2920k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }
}
