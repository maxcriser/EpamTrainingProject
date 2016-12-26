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
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.net.URLEncoder;
import java.util.HashMap;

public class RecordSearchActivity extends C0316b {
    private boolean f1337n;
    private String f1338o;
    private EditText f1339p;
    private Spinner f1340q;

    public RecordSearchActivity() {
        this.f1337n = false;
        this.f1338o = null;
    }

    private void m2354j() {
        Intent intent = getIntent();
        this.f1337n = intent.getBooleanExtra("itemUpdate", false);
        this.f1338o = intent.getStringExtra("itemHash");
        if (this.f1337n && this.f1338o != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1340q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1339p, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2355k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1340q.getSelectedItemPosition()));
        hashMap.put("field2", this.f1339p.getText().toString());
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
        setContentView(C0622e.record_search);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1339p = (EditText) findViewById(C0621d.myKeyword);
        this.f1340q = (Spinner) findViewById(C0621d.searchEngineSpinner);
        m2354j();
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
        String obj = this.f1339p.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        try {
            String encode = URLEncoder.encode(obj, "utf-8");
            int selectedItemPosition = this.f1340q.getSelectedItemPosition();
            String[] stringArray = getResources().getStringArray(C0619b.record_search_engine_arrays);
            String[] stringArray2 = getResources().getStringArray(C0619b.record_search_urls_arrays);
            obj = stringArray[selectedItemPosition] + " : " + obj;
            encode = String.format(stringArray2[selectedItemPosition], new Object[]{encode});
            Intent intent = new Intent();
            intent.putExtra("requestMode", 1);
            intent.putExtra("requestType", 38);
            intent.putExtra("itemRecord", encode);
            intent.putExtra("itemDescription", obj);
            intent.putExtra("itemHash", this.f1338o);
            intent.putExtra("itemUpdate", this.f1337n);
            intent.putExtra("itemFields", m2355k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } catch (Exception e) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
        }
    }
}
