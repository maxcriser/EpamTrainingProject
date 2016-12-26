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
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class TaskNotificationAlertActivity extends C0316b {
    private static final int f1654n;
    private boolean f1655o;
    private String f1656p;
    private EditText f1657q;
    private EditText f1658r;

    static {
        f1654n = C0481m.TASK_MISC_NOTIFICATION_ALERT.cM;
    }

    public TaskNotificationAlertActivity() {
        this.f1655o = false;
        this.f1656p = null;
    }

    private void m2524j() {
        Intent intent = getIntent();
        this.f1655o = intent.getBooleanExtra("itemUpdate", false);
        this.f1656p = intent.getStringExtra("itemHash");
        if (this.f1655o && this.f1656p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1657q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1658r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2525k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1657q.getText().toString());
        hashMap.put("field2", this.f1658r.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty()) {
                if ("field1".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f1657q.setText(new StringBuffer(this.f1657q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f1657q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f1657q.setText(this.f1657q.getText().toString() + stringExtra);
                        this.f1657q.setSelection(this.f1657q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f1658r.setText(new StringBuffer(this.f1658r.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1658r.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1658r.setText(this.f1658r.getText().toString() + stringExtra);
                this.f1658r.setSelection(this.f1658r.length());
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
        setContentView(C0622e.task_notification_alert);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1657q = (EditText) findViewById(C0621d.myTitle);
        this.f1658r = (EditText) findViewById(C0621d.myMessage);
        m2524j();
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
        intent.putExtra("kTargetField", "field1");
        intent.putExtra("kSelectionField", this.f1657q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f1658r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1657q.getText().toString();
        String obj2 = this.f1658r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        String str = obj + "\n" + obj2;
        obj = "[" + obj + "]" + obj2;
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1654n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f1656p);
        intent.putExtra("itemUpdate", this.f1655o);
        intent.putExtra("itemFields", m2525k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
