package com.wakdev.nfctools;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;
import org.json.JSONArray;

public class RecordWifiActivity extends C0316b {
    private boolean f1373n;
    private String f1374o;
    private EditText f1375p;
    private EditText f1376q;
    private Spinner f1377r;
    private Spinner f1378s;

    /* renamed from: com.wakdev.nfctools.RecordWifiActivity.1 */
    class C05341 implements OnClickListener {
        final /* synthetic */ RecordWifiActivity f1372a;

        C05341(RecordWifiActivity recordWifiActivity) {
            this.f1372a = recordWifiActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    C0495h.m2085a("com.wakdev.nfctasks", 1);
                default:
            }
        }
    }

    public RecordWifiActivity() {
        this.f1373n = false;
        this.f1374o = null;
    }

    private void m2374j() {
        Intent intent = getIntent();
        this.f1373n = intent.getBooleanExtra("itemUpdate", false);
        this.f1374o = intent.getStringExtra("itemHash");
        if (this.f1373n && this.f1374o != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1377r, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1378s, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f1375p, (String) hashMap.get("field3"));
                C0490e.m2075a(this.f1376q, (String) hashMap.get("field4"));
            }
        }
    }

    private HashMap<String, String> m2375k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1377r.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f1378s.getSelectedItemPosition()));
        hashMap.put("field3", this.f1375p.getText().toString());
        hashMap.put("field4", this.f1376q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("WiFiSSID");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1375p.setText(stringExtra);
                this.f1375p.setSelection(stringExtra.length());
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
        setContentView(C0622e.record_wifi);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1377r = (Spinner) findViewById(C0621d.networkAuthTypeSpinner);
        this.f1378s = (Spinner) findViewById(C0621d.networkEncTypeSpinner);
        this.f1375p = (EditText) findViewById(C0621d.myNetworkSSID);
        this.f1376q = (EditText) findViewById(C0621d.myNetworkPwd);
        m2374j();
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
        if (C0499j.m2096a("com.wakdev.nfctasks")) {
            Intent intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_WIFI_NETWORK");
            try {
                startActivityForResult(intent, 1);
                return;
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
                return;
            }
        }
        OnClickListener c05341 = new C05341(this);
        new Builder(this).setTitle(getString(C0625h.dialog_require_nfc_tasks_title)).setMessage(getString(C0625h.dialog_require_nfc_tasks_description)).setPositiveButton(getString(C0625h.dialog_require_nfc_tasks_yes), c05341).setNegativeButton(getString(C0625h.dialog_require_nfc_tasks_no), c05341).setIcon(C0620c.info_icon).show();
    }

    public void onValidateButtonClick(View view) {
        String valueOf = String.valueOf(this.f1377r.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f1378s.getSelectedItemPosition());
        String obj = this.f1375p.getText().toString();
        String obj2 = this.f1376q.getText().toString();
        if (valueOf.isEmpty() || valueOf2.isEmpty() || obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(valueOf);
            jSONArray.put(valueOf2);
            jSONArray.put(obj);
            jSONArray.put(obj2);
            intent.putExtra("requestMode", 1);
            intent.putExtra("requestType", 13);
            intent.putExtra("itemRecord", jSONArray.toString());
            intent.putExtra("itemDescription", obj);
            intent.putExtra("itemHash", this.f1374o);
            intent.putExtra("itemUpdate", this.f1373n);
            intent.putExtra("itemFields", m2375k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } catch (Exception e) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
        }
    }
}
