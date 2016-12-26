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
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class bp extends C0316b {
    private static final int f2142n;
    private boolean f2143o;
    private String f2144p;
    private EditText f2145q;
    private Spinner f2146r;

    static {
        f2142n = C0481m.TASK_COND_WIFI_NETWORK.cM;
    }

    public bp() {
        this.f2143o = false;
        this.f2144p = null;
    }

    private void m2820j() {
        Intent intent = getIntent();
        this.f2143o = intent.getBooleanExtra("itemUpdate", false);
        this.f2144p = intent.getStringExtra("itemHash");
        if (this.f2143o && this.f2144p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2145q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2146r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2821k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2145q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f2146r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2822l() {
        String obj = this.f2145q.getText().toString();
        return obj + "|" + String.valueOf(this.f2146r.getSelectedItemPosition());
    }

    private String m2823m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = getString(C0625h.task_cond_wifi_network_ssid) + " " + this.f2145q.getText().toString();
        if (this.f2146r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("WiFiSSID");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f2145q.setText(stringExtra);
                this.f2145q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_cond_wifi_network);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2145q = (EditText) findViewById(C0621d.mySSID);
        this.f2146r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2146r.setSelection(1);
        m2820j();
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

    public void onSelectWiFiSSIDClick(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.SELECT_WIFI_NETWORK");
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_WIFI_NETWORK");
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e2) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
            }
        } else {
            C0493f.m2081a(this, getString(C0625h.need_nfctasks));
        }
    }

    public void onValidateButtonClick(View view) {
        if (this.f2145q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2142n);
        intent.putExtra("itemTask", m2822l());
        intent.putExtra("itemDescription", m2823m());
        intent.putExtra("itemHash", this.f2144p);
        intent.putExtra("itemUpdate", this.f2143o);
        intent.putExtra("itemFields", m2821k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
