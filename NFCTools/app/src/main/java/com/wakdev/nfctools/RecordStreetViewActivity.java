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

public class RecordStreetViewActivity extends C0316b {
    private EditText f1350n;
    private EditText f1351o;
    private boolean f1352p;
    private String f1353q;

    /* renamed from: com.wakdev.nfctools.RecordStreetViewActivity.1 */
    class C05321 implements OnClickListener {
        final /* synthetic */ RecordStreetViewActivity f1349a;

        C05321(RecordStreetViewActivity recordStreetViewActivity) {
            this.f1349a = recordStreetViewActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    C0495h.m2085a("com.wakdev.nfctasks", 1);
                default:
            }
        }
    }

    public RecordStreetViewActivity() {
        this.f1352p = false;
        this.f1353q = null;
    }

    private void m2361j() {
        Intent intent = getIntent();
        this.f1352p = intent.getBooleanExtra("itemUpdate", false);
        this.f1353q = intent.getStringExtra("itemHash");
        if (this.f1352p && this.f1353q != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1350n, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1351o, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2362k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1350n.getText().toString());
        hashMap.put("field2", this.f1351o.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object valueOf = String.valueOf(intent.getDoubleExtra("GPSLocationLat", 47.321472d));
            Object valueOf2 = String.valueOf(intent.getDoubleExtra("GPSLocationLng", 5.041382d));
            this.f1350n.setText(valueOf);
            this.f1350n.setSelection(valueOf.length());
            this.f1351o.setText(valueOf2);
            this.f1351o.setSelection(valueOf2.length());
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
        setContentView(C0622e.record_streetview);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1350n = (EditText) findViewById(C0621d.myLatRecord);
        this.f1351o = (EditText) findViewById(C0621d.myLngRecord);
        m2361j();
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

    public void onSelectGPSLocationClick(View view) {
        if (C0499j.m2096a("com.wakdev.nfctasks")) {
            Intent intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_GPS_LOCATION");
            String obj = this.f1350n.getText().toString();
            String obj2 = this.f1351o.getText().toString();
            if (C0506o.m2172c(obj) && C0506o.m2172c(obj2)) {
                intent.putExtra("GPSLocationLat", Double.valueOf(obj));
                intent.putExtra("GPSLocationLng", Double.valueOf(obj2));
            }
            try {
                startActivityForResult(intent, 1);
                return;
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
                return;
            }
        }
        OnClickListener c05321 = new C05321(this);
        new Builder(this).setTitle(getString(C0625h.dialog_require_nfc_tasks_title)).setMessage(getString(C0625h.dialog_require_nfc_tasks_description)).setPositiveButton(getString(C0625h.dialog_require_nfc_tasks_yes), c05321).setNegativeButton(getString(C0625h.dialog_require_nfc_tasks_no), c05321).setIcon(C0620c.info_icon).show();
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1350n.getText().toString();
        String obj2 = this.f1351o.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_lat_is_empty));
        } else if (!C0506o.m2172c(obj)) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_lat));
        } else if (obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_lng_is_empty));
        } else if (C0506o.m2173d(obj2)) {
            String str = "google.streetview:cbll=" + obj + "," + obj2;
            Intent intent = new Intent();
            intent.putExtra("requestMode", 1);
            intent.putExtra("requestType", 41);
            intent.putExtra("itemRecord", str);
            intent.putExtra("itemDescription", obj + "," + obj2);
            intent.putExtra("itemHash", this.f1353q);
            intent.putExtra("itemUpdate", this.f1352p);
            intent.putExtra("itemFields", m2362k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } else {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_lng));
        }
    }
}
