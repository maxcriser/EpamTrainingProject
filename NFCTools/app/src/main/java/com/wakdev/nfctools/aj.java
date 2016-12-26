package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.commons.C0506o;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class aj extends C0316b {
    private static final int f1919n;
    private boolean f1920o;
    private String f1921p;
    private EditText f1922q;
    private Spinner f1923r;
    private Button f1924s;

    static {
        f1919n = C0481m.TASK_COND_IS_BLUETOOTH_DEVICE_CONNECTED.cM;
    }

    public aj() {
        this.f1920o = false;
        this.f1921p = null;
    }

    private void m2673j() {
        Intent intent = getIntent();
        this.f1920o = intent.getBooleanExtra("itemUpdate", false);
        this.f1921p = intent.getStringExtra("itemHash");
        if (this.f1920o && this.f1921p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1922q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1923r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2674k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1922q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1923r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2675l() {
        String toUpperCase = this.f1922q.getText().toString().toUpperCase();
        return toUpperCase + "|" + String.valueOf(this.f1923r.getSelectedItemPosition());
    }

    private String m2676m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = getString(C0625h.task_cond_device_connected_title) + " " + this.f1922q.getText().toString().toUpperCase();
        if (this.f1923r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("BluetoothMacAddress");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1922q.setText(stringExtra);
                this.f1922q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_cond_device_connected);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1922q = (EditText) findViewById(C0621d.myMAC);
        this.f1924s = (Button) findViewById(C0621d.mySearchButton);
        this.f1923r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1923r.setSelection(1);
        m2673j();
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

    public void onSelectBluetoothDeviceClick(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.SELECT_BLUETOOTH_DEVICE");
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_BLUETOOTH_DEVICE");
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
        String toUpperCase = this.f1922q.getText().toString().toUpperCase();
        if (toUpperCase.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_mac_address_is_empty));
        } else if (C0506o.m2170a(toUpperCase) || toUpperCase.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1919n);
            intent.putExtra("itemTask", m2675l());
            intent.putExtra("itemDescription", m2676m());
            intent.putExtra("itemHash", this.f1921p);
            intent.putExtra("itemUpdate", this.f1920o);
            intent.putExtra("itemFields", m2674k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } else {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_mac_address));
        }
    }
}
