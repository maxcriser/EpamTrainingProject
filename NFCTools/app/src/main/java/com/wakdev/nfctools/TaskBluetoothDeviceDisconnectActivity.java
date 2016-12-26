package com.wakdev.nfctools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class TaskBluetoothDeviceDisconnectActivity extends C0316b {
    private static final int f1435n;
    private boolean f1436o;
    private String f1437p;
    private EditText f1438q;
    private Button f1439r;

    static {
        f1435n = C0481m.TASK_BLUETOOTH_DEVICE_DISCONNECT.cM;
    }

    public TaskBluetoothDeviceDisconnectActivity() {
        this.f1436o = false;
        this.f1437p = null;
    }

    private void m2413j() {
        Intent intent = getIntent();
        this.f1436o = intent.getBooleanExtra("itemUpdate", false);
        this.f1437p = intent.getStringExtra("itemHash");
        if (this.f1436o && this.f1437p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1438q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2414k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1438q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("BluetoothMacAddress");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1438q.setText(stringExtra);
                this.f1438q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_bluetooth_device_disconnect);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1438q = (EditText) findViewById(C0621d.myMAC);
        this.f1439r = (Button) findViewById(C0621d.mySearchButton);
        m2413j();
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

    @SuppressLint({"DefaultLocale"})
    public void onValidateButtonClick(View view) {
        String toUpperCase = this.f1438q.getText().toString().toUpperCase();
        if (toUpperCase.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_mac_address_is_empty));
        } else if (C0506o.m2170a(toUpperCase) || toUpperCase.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1435n);
            intent.putExtra("itemTask", toUpperCase);
            intent.putExtra("itemDescription", toUpperCase);
            intent.putExtra("itemHash", this.f1437p);
            intent.putExtra("itemUpdate", this.f1436o);
            intent.putExtra("itemFields", m2414k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } else {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_mac_address));
        }
    }
}
