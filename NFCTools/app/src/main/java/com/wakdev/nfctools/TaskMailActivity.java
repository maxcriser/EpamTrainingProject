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
import com.wakdev.libs.commons.C0506o;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class TaskMailActivity extends C0316b {
    private static final int f1632n;
    private EditText f1633o;
    private EditText f1634p;
    private EditText f1635q;
    private boolean f1636r;
    private String f1637s;

    static {
        f1632n = C0481m.TASK_MISC_MAIL.cM;
    }

    public TaskMailActivity() {
        this.f1636r = false;
        this.f1637s = null;
    }

    private void m2514j() {
        Intent intent = getIntent();
        this.f1636r = intent.getBooleanExtra("itemUpdate", false);
        this.f1637s = intent.getStringExtra("itemHash");
        if (this.f1636r && this.f1637s != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1633o, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1634p, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f1635q, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2515k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1633o.getText().toString());
        hashMap.put("field2", this.f1634p.getText().toString());
        hashMap.put("field3", this.f1635q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty()) {
                if ("field2".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f1634p.setText(new StringBuffer(this.f1634p.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f1634p.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f1634p.setText(this.f1634p.getText().toString() + stringExtra);
                        this.f1634p.setSelection(this.f1634p.length());
                    }
                }
                if (!"field3".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f1635q.setText(new StringBuffer(this.f1635q.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1635q.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1635q.setText(this.f1635q.getText().toString() + stringExtra);
                this.f1635q.setSelection(this.f1635q.length());
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
        setContentView(C0622e.task_mail);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1633o = (EditText) findViewById(C0621d.myMailTo);
        this.f1634p = (EditText) findViewById(C0621d.myMailObject);
        this.f1635q = (EditText) findViewById(C0621d.myMailMessage);
        m2514j();
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
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f1634p.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field3");
        intent.putExtra("kSelectionField", this.f1635q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1633o.getText().toString();
        String obj2 = this.f1634p.getText().toString();
        String obj3 = this.f1635q.getText().toString();
        if (obj.isEmpty() || !C0506o.m2169a((CharSequence) obj)) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_mail));
            return;
        }
        String str = "mailto:" + obj;
        String str2 = "?";
        if (!obj2.isEmpty()) {
            obj = obj + "\n" + obj2;
            str = str + str2 + "subject=" + obj2;
            str2 = "&";
        }
        if (!obj3.isEmpty()) {
            obj = obj + "\n" + obj3;
            str = str + str2 + "body=" + obj3;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1632n);
        intent.putExtra("itemTask", str);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1637s);
        intent.putExtra("itemUpdate", this.f1636r);
        intent.putExtra("itemFields", m2515k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
