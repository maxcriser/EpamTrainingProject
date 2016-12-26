package com.wakdev.nfctools;

import android.annotation.SuppressLint;
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
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.commons.C0506o;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class RecordBluetoothActivity extends C0316b {
    private EditText f1291n;
    private boolean f1292o;
    private String f1293p;

    /* renamed from: com.wakdev.nfctools.RecordBluetoothActivity.1 */
    class C05271 implements OnClickListener {
        final /* synthetic */ RecordBluetoothActivity f1290a;

        C05271(RecordBluetoothActivity recordBluetoothActivity) {
            this.f1290a = recordBluetoothActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    C0495h.m2085a("com.wakdev.nfctasks", 1);
                default:
            }
        }
    }

    public RecordBluetoothActivity() {
        this.f1292o = false;
        this.f1293p = null;
    }

    private void m2334j() {
        Intent intent = getIntent();
        this.f1292o = intent.getBooleanExtra("itemUpdate", false);
        this.f1293p = intent.getStringExtra("itemHash");
        if (this.f1292o && this.f1293p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1291n, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2335k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1291n.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("BluetoothMacAddress");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f1291n.setText(stringExtra);
                this.f1291n.setSelection(stringExtra.length());
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
        setContentView(C0622e.record_bluetooth);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1291n = (EditText) findViewById(C0621d.myMACRecord);
        m2334j();
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
        if (C0499j.m2096a("com.wakdev.nfctasks")) {
            Intent intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_BLUETOOTH_DEVICE");
            try {
                startActivityForResult(intent, 1);
                return;
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
                return;
            }
        }
        OnClickListener c05271 = new C05271(this);
        new Builder(this).setTitle(getString(C0625h.dialog_require_nfc_tasks_title)).setMessage(getString(C0625h.dialog_require_nfc_tasks_description)).setPositiveButton(getString(C0625h.dialog_require_nfc_tasks_yes), c05271).setNegativeButton(getString(C0625h.dialog_require_nfc_tasks_no), c05271).setIcon(C0620c.info_icon).show();
    }

    @SuppressLint({"DefaultLocale"})
    public void onValidateButtonClick(View view) {
        String toUpperCase = this.f1291n.getText().toString().toUpperCase();
        if (toUpperCase.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_mac_address_is_empty));
        } else if (C0506o.m2170a(toUpperCase) || toUpperCase.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 1);
            intent.putExtra("requestType", 11);
            intent.putExtra("itemRecord", toUpperCase);
            intent.putExtra("itemDescription", toUpperCase);
            intent.putExtra("itemHash", this.f1293p);
            intent.putExtra("itemUpdate", this.f1292o);
            intent.putExtra("itemFields", m2335k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } else {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_mac_address));
        }
    }
}
