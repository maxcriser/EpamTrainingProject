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

public class TaskShowAppDetailsActivity extends C0316b {
    private static final int f1733n;
    private EditText f1734o;
    private boolean f1735p;
    private String f1736q;

    static {
        f1733n = C0481m.TASK_MISC_SHOW_APP_DETAILS.cM;
    }

    public TaskShowAppDetailsActivity() {
        this.f1735p = false;
        this.f1736q = null;
    }

    private void m2569j() {
        Intent intent = getIntent();
        this.f1735p = intent.getBooleanExtra("itemUpdate", false);
        this.f1736q = intent.getStringExtra("itemHash");
        if (this.f1735p && this.f1736q != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1734o, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2570k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1734o.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            Object stringExtra = intent.getStringExtra("packageName");
            this.f1734o.setText(stringExtra);
            this.f1734o.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_show_app_details);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1734o = (EditText) findViewById(C0621d.myAppRecord);
        m2569j();
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

    public void onSelectPackageButtonClick(View view) {
        startActivityForResult(new Intent(this, ChoosePackageActivity.class), 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1734o.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_package_name_is_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1733n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1736q);
        intent.putExtra("itemUpdate", this.f1735p);
        intent.putExtra("itemFields", m2570k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
