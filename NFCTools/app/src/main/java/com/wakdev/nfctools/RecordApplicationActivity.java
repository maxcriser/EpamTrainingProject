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
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class RecordApplicationActivity extends C0316b {
    private EditText f1282n;
    private boolean f1283o;
    private String f1284p;

    public RecordApplicationActivity() {
        this.f1283o = false;
        this.f1284p = null;
    }

    private void m2330j() {
        Intent intent = getIntent();
        this.f1283o = intent.getBooleanExtra("itemUpdate", false);
        this.f1284p = intent.getStringExtra("itemHash");
        if (this.f1283o && this.f1284p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1282n, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2331k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1282n.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            Object stringExtra = intent.getStringExtra("packageName");
            this.f1282n.setText(stringExtra);
            this.f1282n.setSelection(stringExtra.length());
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
        setContentView(C0622e.record_application);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1282n = (EditText) findViewById(C0621d.myAppRecord);
        m2330j();
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
        String obj = this.f1282n.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_package_name_is_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 1);
        intent.putExtra("requestType", 3);
        intent.putExtra("itemRecord", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1284p);
        intent.putExtra("itemUpdate", this.f1283o);
        intent.putExtra("itemFields", m2331k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
