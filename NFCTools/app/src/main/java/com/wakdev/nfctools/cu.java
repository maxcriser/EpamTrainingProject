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

public class cu extends C0316b {
    private static final int f2331n;
    private boolean f2332o;
    private String f2333p;
    private EditText f2334q;
    private EditText f2335r;

    static {
        f2331n = C0481m.TASK_MISC_WEAR_NOTIFICATION.cM;
    }

    public cu() {
        this.f2332o = false;
        this.f2333p = null;
    }

    private void m2917j() {
        Intent intent = getIntent();
        this.f2332o = intent.getBooleanExtra("itemUpdate", false);
        this.f2333p = intent.getStringExtra("itemHash");
        if (this.f2332o && this.f2333p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2334q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2335r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2918k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2334q.getText().toString());
        hashMap.put("field2", this.f2335r.getText().toString());
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
                        this.f2334q.setText(new StringBuffer(this.f2334q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2334q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2334q.setText(this.f2334q.getText().toString() + stringExtra);
                        this.f2334q.setSelection(this.f2334q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f2335r.setText(new StringBuffer(this.f2335r.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f2335r.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f2335r.setText(this.f2335r.getText().toString() + stringExtra);
                this.f2335r.setSelection(this.f2335r.length());
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
        setContentView(C0622e.task_wear_notification);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2334q = (EditText) findViewById(C0621d.myTitle);
        this.f2335r = (EditText) findViewById(C0621d.myMessage);
        m2917j();
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
        intent.putExtra("kSelectionField", this.f2334q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f2335r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f2334q.getText().toString();
        String obj2 = this.f2335r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        String str = obj + "\n" + obj2;
        obj = "[" + obj + "]" + obj2;
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2331n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f2333p);
        intent.putExtra("itemUpdate", this.f2332o);
        intent.putExtra("itemFields", m2918k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
