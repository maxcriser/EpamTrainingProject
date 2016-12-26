package com.wakdev.nfctools;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class RecordFileActivity extends C0316b {
    private boolean f1314n;
    private String f1315o;
    private EditText f1316p;

    /* renamed from: com.wakdev.nfctools.RecordFileActivity.1 */
    class C05281 implements OnClickListener {
        final /* synthetic */ RecordFileActivity f1313a;

        C05281(RecordFileActivity recordFileActivity) {
            this.f1313a = recordFileActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    C0495h.m2085a("com.wakdev.nfctasks", 1);
                default:
            }
        }
    }

    public RecordFileActivity() {
        this.f1314n = false;
        this.f1315o = null;
    }

    private void m2344j() {
        Intent intent = getIntent();
        this.f1314n = intent.getBooleanExtra("itemUpdate", false);
        this.f1315o = intent.getStringExtra("itemHash");
        if (this.f1314n && this.f1315o != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1316p, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2345k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1316p.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1316p.setText(stringExtra);
                this.f1316p.setSelection(stringExtra.length());
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
        setContentView(C0622e.record_file);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1316p = (EditText) findViewById(C0621d.myFilePath);
        m2344j();
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
        if (C0499j.m2096a("com.wakdev.nfctasks")) {
            Intent intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.FILE_MANAGER");
            intent.putExtra("kIntentKeySelectionType", 1);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_open_file_select));
            try {
                startActivityForResult(intent, 1);
                return;
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
                return;
            }
        }
        OnClickListener c05281 = new C05281(this);
        new Builder(this).setTitle(getString(C0625h.dialog_require_nfc_tasks_title)).setMessage(getString(C0625h.dialog_require_nfc_tasks_description)).setPositiveButton(getString(C0625h.dialog_require_nfc_tasks_yes), c05281).setNegativeButton(getString(C0625h.dialog_require_nfc_tasks_no), c05281).setIcon(C0620c.info_icon).show();
    }

    @SuppressLint({"DefaultLocale"})
    public void onValidateButtonClick(View view) {
        String obj = this.f1316p.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        obj = "file://" + obj;
        Intent intent = new Intent();
        intent.putExtra("requestMode", 1);
        intent.putExtra("requestType", 24);
        intent.putExtra("itemRecord", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1315o);
        intent.putExtra("itemUpdate", this.f1314n);
        intent.putExtra("itemFields", m2345k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
