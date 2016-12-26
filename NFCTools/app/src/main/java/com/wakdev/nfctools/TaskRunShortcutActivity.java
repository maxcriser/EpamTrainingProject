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

public class TaskRunShortcutActivity extends C0316b {
    private static final int f1702n;
    private boolean f1703o;
    private String f1704p;
    private EditText f1705q;
    private EditText f1706r;

    static {
        f1702n = C0481m.TASK_MISC_RUN_SHORTCUT.cM;
    }

    public TaskRunShortcutActivity() {
        this.f1703o = false;
        this.f1704p = null;
    }

    private void m2546j() {
        Intent intent = getIntent();
        this.f1703o = intent.getBooleanExtra("itemUpdate", false);
        this.f1704p = intent.getStringExtra("itemHash");
        if (this.f1703o && this.f1704p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1705q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1706r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2547k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1705q.getText().toString());
        hashMap.put("field2", this.f1706r.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            CharSequence stringExtra = intent.getStringExtra("kShortcutName");
            CharSequence stringExtra2 = intent.getStringExtra("kShortcutEncodedIntent");
            if (stringExtra != null && stringExtra2 != null) {
                this.f1705q.setText(stringExtra);
                this.f1706r.setText(stringExtra2);
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
        setContentView(C0622e.task_run_shortcut);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1705q = (EditText) findViewById(C0621d.myText);
        this.f1706r = (EditText) findViewById(C0621d.myEncIntent);
        m2546j();
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

    public void onSelectShortcutButtonClick(View view) {
        startActivityForResult(new Intent(this, ChooseShortcutActivity.class), 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1705q.getText().toString();
        String obj2 = this.f1706r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        obj2 = obj.replace("|", "") + "|" + obj2;
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1702n);
        intent.putExtra("itemTask", obj2);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1704p);
        intent.putExtra("itemUpdate", this.f1703o);
        intent.putExtra("itemFields", m2547k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
