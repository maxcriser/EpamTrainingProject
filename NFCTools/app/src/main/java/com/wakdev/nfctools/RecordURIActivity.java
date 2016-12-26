package com.wakdev.nfctools;

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
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class RecordURIActivity extends C0316b {
    private boolean f1360n;
    private String f1361o;
    private EditText f1362p;
    private Spinner f1363q;

    public RecordURIActivity() {
        this.f1360n = false;
        this.f1361o = null;
    }

    private void m2367j() {
        Intent intent = getIntent();
        this.f1360n = intent.getBooleanExtra("itemUpdate", false);
        this.f1361o = intent.getStringExtra("itemHash");
        if (this.f1360n && this.f1361o != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1362p, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1363q, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2368k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1362p.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1363q.getSelectedItemPosition()));
        return hashMap;
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
        setContentView(C0622e.record_uri);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1362p = (EditText) findViewById(C0621d.myURIRecord);
        this.f1363q = (Spinner) findViewById(C0621d.uri_type_spinner);
        m2367j();
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

    public void onValidateButtonClick(View view) {
        String obj = this.f1362p.getText().toString();
        String obj2 = this.f1363q.getSelectedItem().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_url));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 1);
        intent.putExtra("requestType", 2);
        intent.putExtra("itemRecord", obj2 + obj);
        intent.putExtra("itemDescription", obj2 + obj);
        intent.putExtra("itemHash", this.f1361o);
        intent.putExtra("itemUpdate", this.f1360n);
        intent.putExtra("itemFields", m2368k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
