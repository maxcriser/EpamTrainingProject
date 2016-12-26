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

public class TaskProximitySearchActivity extends C0316b {
    private static final int f1684n;
    private EditText f1685o;
    private EditText f1686p;
    private EditText f1687q;
    private boolean f1688r;
    private String f1689s;

    static {
        f1684n = C0481m.TASK_MISC_GEO_SEARCH.cM;
    }

    public TaskProximitySearchActivity() {
        this.f1688r = false;
        this.f1689s = null;
    }

    private void m2538j() {
        Intent intent = getIntent();
        this.f1688r = intent.getBooleanExtra("itemUpdate", false);
        this.f1689s = intent.getStringExtra("itemHash");
        if (this.f1688r && this.f1689s != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1685o, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1686p, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f1687q, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2539k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1685o.getText().toString());
        hashMap.put("field2", this.f1686p.getText().toString());
        hashMap.put("field3", this.f1687q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object valueOf = String.valueOf(intent.getDoubleExtra("GPSLocationLat", 47.321472d));
            Object valueOf2 = String.valueOf(intent.getDoubleExtra("GPSLocationLng", 5.041382d));
            this.f1686p.setText(valueOf);
            this.f1686p.setSelection(valueOf.length());
            this.f1687q.setText(valueOf2);
            this.f1687q.setSelection(valueOf2.length());
        }
        if (i == 2 && i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field1".equals(stringExtra2)) {
                if (intExtra != -1) {
                    this.f1685o.setText(new StringBuffer(this.f1685o.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1685o.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1685o.setText(this.f1685o.getText().toString() + stringExtra);
                this.f1685o.setSelection(this.f1685o.length());
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
        setContentView(C0622e.task_proximity_search);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1685o = (EditText) findViewById(C0621d.myKeywordRecord);
        this.f1686p = (EditText) findViewById(C0621d.myLatRecord);
        this.f1687q = (EditText) findViewById(C0621d.myLngRecord);
        m2538j();
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
            obj = this.f1686p.getText().toString();
            obj2 = this.f1687q.getText().toString();
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
            obj = this.f1686p.getText().toString();
            obj2 = this.f1687q.getText().toString();
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

    public void onSelectVarsButtonClick1(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field1");
        intent.putExtra("kSelectionField", this.f1685o.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1685o.getText().toString();
        String obj2 = this.f1686p.getText().toString();
        String obj3 = this.f1687q.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
        } else if (obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_lat_is_empty));
        } else if (!C0506o.m2172c(obj2)) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_lat));
        } else if (obj3.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_lng_is_empty));
        } else if (C0506o.m2173d(obj3)) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1684n);
            intent.putExtra("itemTask", obj2 + "|" + obj3 + "|" + obj);
            intent.putExtra("itemDescription", obj + "\n" + obj2 + "," + obj3);
            intent.putExtra("itemHash", this.f1689s);
            intent.putExtra("itemUpdate", this.f1688r);
            intent.putExtra("itemFields", m2539k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } else {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_lng));
        }
    }
}
