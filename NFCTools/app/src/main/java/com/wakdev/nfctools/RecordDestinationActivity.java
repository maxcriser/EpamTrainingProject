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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class RecordDestinationActivity extends C0316b {
    private EditText f1310n;
    private boolean f1311o;
    private String f1312p;

    public RecordDestinationActivity() {
        this.f1311o = false;
        this.f1312p = null;
    }

    private void m2342j() {
        Intent intent = getIntent();
        this.f1311o = intent.getBooleanExtra("itemUpdate", false);
        this.f1312p = intent.getStringExtra("itemHash");
        if (this.f1311o && this.f1312p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1310n, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2343k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1310n.getText().toString());
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
        setContentView(C0622e.record_destination);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1310n = (EditText) findViewById(C0621d.myAddressRecord);
        m2342j();
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
        String obj = this.f1310n.getText().toString();
        String str = "";
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_address_is_empty));
            return;
        }
        try {
            str = "google.navigation:q=" + URLEncoder.encode(obj, "utf-8");
            Intent intent = new Intent();
            intent.putExtra("requestMode", 1);
            intent.putExtra("requestType", 39);
            intent.putExtra("itemRecord", str);
            intent.putExtra("itemDescription", obj);
            intent.putExtra("itemHash", this.f1312p);
            intent.putExtra("itemUpdate", this.f1311o);
            intent.putExtra("itemFields", m2343k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            C0493f.m2081a(this, getString(C0625h.err_address_is_empty));
        }
    }
}
