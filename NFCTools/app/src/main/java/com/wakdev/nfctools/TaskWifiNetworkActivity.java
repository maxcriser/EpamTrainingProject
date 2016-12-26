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

public class TaskWifiNetworkActivity extends C0316b {
    private static final int f1838n;
    private boolean f1839o;
    private String f1840p;
    private EditText f1841q;
    private EditText f1842r;
    private Spinner f1843s;

    static {
        f1838n = C0481m.TASK_WIFI_NETWORK.cM;
    }

    public TaskWifiNetworkActivity() {
        this.f1839o = false;
        this.f1840p = null;
    }

    private void m2617j() {
        Intent intent = getIntent();
        this.f1839o = intent.getBooleanExtra("itemUpdate", false);
        this.f1840p = intent.getStringExtra("itemHash");
        if (this.f1839o && this.f1840p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1843s, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1841q, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f1842r, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2618k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1843s.getSelectedItemPosition()));
        hashMap.put("field2", this.f1841q.getText().toString());
        hashMap.put("field3", this.f1842r.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("WiFiSSID");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1841q.setText(stringExtra);
                this.f1841q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_wifi_network);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1843s = (Spinner) findViewById(C0621d.networkTypeSpinner);
        this.f1841q = (EditText) findViewById(C0621d.myNetworkSSID);
        this.f1842r = (EditText) findViewById(C0621d.myNetworkPwd);
        m2617j();
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
        Object obj;
        String str = "";
        String str2 = "";
        int selectedItemPosition = this.f1843s.getSelectedItemPosition();
        String obj2 = this.f1841q.getText().toString();
        String obj3 = this.f1842r.getText().toString();
        int i;
        if (selectedItemPosition > 0) {
            if (obj2.isEmpty() || obj3.isEmpty()) {
                obj2 = str;
                obj = null;
            } else {
                str2 = obj2 + " / " + obj3;
                obj2 = obj2 + "/" + obj3;
                i = 1;
            }
        } else if (obj2.isEmpty()) {
            obj2 = str;
            obj = null;
        } else {
            str2 = obj2;
            i = 1;
        }
        if (obj != null) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1838n);
            intent.putExtra("itemTask", obj2);
            intent.putExtra("itemTaskExtra", String.valueOf(selectedItemPosition));
            intent.putExtra("itemDescription", str2);
            intent.putExtra("itemHash", this.f1840p);
            intent.putExtra("itemUpdate", this.f1839o);
            intent.putExtra("itemFields", m2618k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
    }
}
