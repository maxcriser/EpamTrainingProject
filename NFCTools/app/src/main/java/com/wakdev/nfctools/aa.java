package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0500k;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class aa extends C0316b {
    private static final int f1863n;
    private boolean f1864o;
    private String f1865p;
    private Spinner f1866q;
    private TextView f1867r;
    private SeekBar f1868s;
    private Spinner f1869t;

    /* renamed from: com.wakdev.nfctools.aa.1 */
    class C05641 implements OnSeekBarChangeListener {
        final /* synthetic */ aa f1862a;

        C05641(aa aaVar) {
            this.f1862a = aaVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < 1) {
                seekBar.setProgress(1);
                i = 1;
            }
            this.f1862a.f1867r.setText(String.valueOf(i) + " %");
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1863n = C0481m.TASK_COND_IS_BATTERY_LEVEL.cM;
    }

    public aa() {
        this.f1864o = false;
        this.f1865p = null;
        this.f1866q = null;
        this.f1867r = null;
        this.f1868s = null;
    }

    private void m2634j() {
        this.f1868s.setMax(100);
        this.f1868s.setProgress(Math.round(C0500k.m2098a() * 100.0f));
        Intent intent = getIntent();
        this.f1864o = intent.getBooleanExtra("itemUpdate", false);
        this.f1865p = intent.getStringExtra("itemHash");
        if (this.f1864o && this.f1865p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1866q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f1868s, (String) hashMap.get("field2"), 100);
                C0490e.m2078a(this.f1869t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2635k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1866q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f1868s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f1869t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2636l() {
        String valueOf = String.valueOf(this.f1866q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f1868s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f1869t.getSelectedItemPosition());
    }

    private String m2637m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.battery_level_state_arrays);
        if (this.f1869t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f1866q.getSelectedItemPosition()] + " " + String.valueOf(this.f1868s.getProgress()) + "%\n" + string;
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
        setContentView(C0622e.task_cond_battery_level);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1866q = (Spinner) findViewById(C0621d.myBatterySpinner);
        this.f1867r = (TextView) findViewById(C0621d.myBatteryPercent);
        this.f1868s = (SeekBar) findViewById(C0621d.myBatterySeekbar);
        this.f1868s.setOnSeekBarChangeListener(new C05641(this));
        this.f1869t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1869t.setSelection(1);
        m2634j();
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
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1863n);
        intent.putExtra("itemTask", m2636l());
        intent.putExtra("itemDescription", m2637m());
        intent.putExtra("itemHash", this.f1865p);
        intent.putExtra("itemUpdate", this.f1864o);
        intent.putExtra("itemFields", m2635k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
