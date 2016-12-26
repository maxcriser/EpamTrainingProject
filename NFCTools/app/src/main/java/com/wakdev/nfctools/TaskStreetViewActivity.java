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

public class TaskStreetViewActivity extends C0316b {
    private static final int f1798n;
    private EditText f1799o;
    private EditText f1800p;
    private boolean f1801q;
    private String f1802r;

    static {
        f1798n = C0481m.TASK_MISC_STREETVIEW.cM;
    }

    public TaskStreetViewActivity() {
        this.f1801q = false;
        this.f1802r = null;
    }

    private void m2599j() {
        Intent intent = getIntent();
        this.f1801q = intent.getBooleanExtra("itemUpdate", false);
        this.f1802r = intent.getStringExtra("itemHash");
        if (this.f1801q && this.f1802r != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1799o, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1800p, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2600k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1799o.getText().toString());
        hashMap.put("field2", this.f1800p.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object valueOf = String.valueOf(intent.getDoubleExtra("GPSLocationLat", 47.321472d));
            Object valueOf2 = String.valueOf(intent.getDoubleExtra("GPSLocationLng", 5.041382d));
            this.f1799o.setText(valueOf);
            this.f1799o.setSelection(valueOf.length());
            this.f1800p.setText(valueOf2);
            this.f1800p.setSelection(valueOf2.length());
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
        setContentView(C0622e.task_streetview);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1799o = (EditText) findViewById(C0621d.myLatRecord);
        this.f1800p = (EditText) findViewById(C0621d.myLngRecord);
        m2599j();
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
        Intent intent;
        String obj;
        String obj2;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.SELECT_GPS_LOCATION");
            obj = this.f1799o.getText().toString();
            obj2 = this.f1800p.getText().toString();
            if (C0506o.m2172c(obj) && C0506o.m2172c(obj2)) {
                intent.putExtra("GPSLocationLat", Double.valueOf(obj));
                intent.putExtra("GPSLocationLng", Double.valueOf(obj2));
            }
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_GPS_LOCATION");
            obj = this.f1799o.getText().toString();
            obj2 = this.f1800p.getText().toString();
            if (C0506o.m2172c(obj) && C0506o.m2172c(obj2)) {
                intent.putExtra("GPSLocationLat", Double.valueOf(obj));
                intent.putExtra("GPSLocationLng", Double.valueOf(obj2));
            }
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
        String obj = this.f1799o.getText().toString();
        String obj2 = this.f1800p.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_lat_is_empty));
        } else if (!C0506o.m2172c(obj)) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_lat));
        } else if (obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_lng_is_empty));
        } else if (C0506o.m2173d(obj2)) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1798n);
            intent.putExtra("itemTask", obj + "," + obj2);
            intent.putExtra("itemDescription", obj + "," + obj2);
            intent.putExtra("itemHash", this.f1802r);
            intent.putExtra("itemUpdate", this.f1801q);
            intent.putExtra("itemFields", m2600k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } else {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_lng));
        }
    }
}
