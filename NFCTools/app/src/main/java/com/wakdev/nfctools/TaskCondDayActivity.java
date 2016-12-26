package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.ToggleButton;
import com.wakdev.libs.commons.C0489d;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class TaskCondDayActivity extends C0316b {
    private static final int f1483n;
    private boolean f1484o;
    private String f1485p;
    private ToggleButton f1486q;
    private ToggleButton f1487r;
    private ToggleButton f1488s;
    private ToggleButton f1489t;
    private ToggleButton f1490u;
    private ToggleButton f1491v;
    private ToggleButton f1492w;
    private Spinner f1493x;

    static {
        f1483n = C0481m.TASK_COND_DAY.cM;
    }

    public TaskCondDayActivity() {
        this.f1484o = false;
        this.f1485p = null;
    }

    private void m2438j() {
        Intent intent = getIntent();
        this.f1484o = intent.getBooleanExtra("itemUpdate", false);
        this.f1485p = intent.getStringExtra("itemHash");
        if (this.f1484o && this.f1485p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2080a(this.f1486q, (String) hashMap.get("field1"));
                C0490e.m2080a(this.f1487r, (String) hashMap.get("field2"));
                C0490e.m2080a(this.f1488s, (String) hashMap.get("field3"));
                C0490e.m2080a(this.f1489t, (String) hashMap.get("field4"));
                C0490e.m2080a(this.f1490u, (String) hashMap.get("field5"));
                C0490e.m2080a(this.f1491v, (String) hashMap.get("field6"));
                C0490e.m2080a(this.f1492w, (String) hashMap.get("field7"));
                C0490e.m2078a(this.f1493x, (String) hashMap.get("field8"));
            }
        }
    }

    private HashMap<String, String> m2439k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1486q.isChecked()));
        hashMap.put("field2", String.valueOf(this.f1487r.isChecked()));
        hashMap.put("field3", String.valueOf(this.f1488s.isChecked()));
        hashMap.put("field4", String.valueOf(this.f1489t.isChecked()));
        hashMap.put("field5", String.valueOf(this.f1490u.isChecked()));
        hashMap.put("field6", String.valueOf(this.f1491v.isChecked()));
        hashMap.put("field7", String.valueOf(this.f1492w.isChecked()));
        hashMap.put("field8", String.valueOf(this.f1493x.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2440l() {
        String str = "0";
        String str2 = "0";
        String str3 = "0";
        String str4 = "0";
        String str5 = "0";
        String str6 = "0";
        String str7 = "0";
        String valueOf = String.valueOf(this.f1493x.getSelectedItemPosition());
        if (this.f1486q.isChecked()) {
            str = "1";
        }
        if (this.f1487r.isChecked()) {
            str2 = "1";
        }
        if (this.f1488s.isChecked()) {
            str3 = "1";
        }
        if (this.f1489t.isChecked()) {
            str4 = "1";
        }
        if (this.f1490u.isChecked()) {
            str5 = "1";
        }
        if (this.f1491v.isChecked()) {
            str6 = "1";
        }
        if (this.f1492w.isChecked()) {
            str7 = "1";
        }
        return C0489d.m2059a(str + str2 + str3 + str4 + str5 + str6 + str7 + valueOf);
    }

    private String m2441m() {
        String str = "";
        if (this.f1486q.isChecked()) {
            str = str + getString(C0625h.toggle_monday) + ",";
        }
        if (this.f1487r.isChecked()) {
            str = str + getString(C0625h.toggle_tuesday) + ",";
        }
        if (this.f1488s.isChecked()) {
            str = str + getString(C0625h.toggle_wednesday) + ",";
        }
        if (this.f1489t.isChecked()) {
            str = str + getString(C0625h.toggle_thursday) + ",";
        }
        if (this.f1490u.isChecked()) {
            str = str + getString(C0625h.toggle_friday) + ",";
        }
        if (this.f1491v.isChecked()) {
            str = str + getString(C0625h.toggle_saturday) + ",";
        }
        if (this.f1492w.isChecked()) {
            str = str + getString(C0625h.toggle_sunday) + ",";
        }
        if (!str.isEmpty()) {
            str = str.substring(0, str.length() - 1);
        }
        String string = getString(C0625h.cond_desc_exclude);
        if (this.f1493x.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
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
        setContentView(C0622e.task_cond_day);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1486q = (ToggleButton) findViewById(C0621d.toggleButton0);
        this.f1487r = (ToggleButton) findViewById(C0621d.toggleButton1);
        this.f1488s = (ToggleButton) findViewById(C0621d.toggleButton2);
        this.f1489t = (ToggleButton) findViewById(C0621d.toggleButton3);
        this.f1490u = (ToggleButton) findViewById(C0621d.toggleButton4);
        this.f1491v = (ToggleButton) findViewById(C0621d.toggleButton5);
        this.f1492w = (ToggleButton) findViewById(C0621d.toggleButton6);
        this.f1493x = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1493x.setSelection(1);
        m2438j();
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
        if (this.f1486q.isChecked() || this.f1487r.isChecked() || this.f1488s.isChecked() || this.f1489t.isChecked() || this.f1490u.isChecked() || this.f1491v.isChecked() || this.f1492w.isChecked()) {
            String l = m2440l();
            String m = m2441m();
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1483n);
            intent.putExtra("itemTask", l);
            intent.putExtra("itemDescription", m);
            intent.putExtra("itemHash", this.f1485p);
            intent.putExtra("itemUpdate", this.f1484o);
            intent.putExtra("itemFields", m2439k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
