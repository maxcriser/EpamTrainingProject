package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TimeZone;

public class cr extends C0316b {
    private static final int f2318n;
    private boolean f2319o;
    private String f2320p;
    private Spinner f2321q;

    static {
        f2318n = C0481m.TASK_CONFIG_TIMEZONE.cM;
    }

    public cr() {
        this.f2319o = false;
        this.f2320p = null;
    }

    private void m2910j() {
        Intent intent = getIntent();
        this.f2319o = intent.getBooleanExtra("itemUpdate", false);
        this.f2320p = intent.getStringExtra("itemHash");
        if (this.f2319o && this.f2320p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                String str = (String) hashMap.get("field1");
                for (int i = 0; i < this.f2321q.getCount(); i++) {
                    if (str.equals((String) this.f2321q.getItemAtPosition(i))) {
                        this.f2321q.setSelection(i);
                        return;
                    }
                }
            }
        }
    }

    private HashMap<String, String> m2911k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", (String) this.f2321q.getSelectedItem());
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
        setContentView(C0622e.task_timezone);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2321q = (Spinner) findViewById(C0621d.state_spinner);
        this.f2321q.setAdapter(new ArrayAdapter(this, 17367049, new ArrayList(Arrays.asList(TimeZone.getAvailableIDs()))));
        m2910j();
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
        String str = (String) this.f2321q.getSelectedItem();
        if (str.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2318n);
        intent.putExtra("itemTask", str);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f2320p);
        intent.putExtra("itemUpdate", this.f2319o);
        intent.putExtra("itemFields", m2911k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
