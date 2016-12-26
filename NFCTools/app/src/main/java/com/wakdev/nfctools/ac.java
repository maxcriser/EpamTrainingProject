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
import com.wakdev.libs.commons.C0489d;
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

public class ac extends C0316b {
    private static final int f1876n;
    private boolean f1877o;
    private String f1878p;
    private Spinner f1879q;
    private TextView f1880r;
    private SeekBar f1881s;
    private Spinner f1882t;

    /* renamed from: com.wakdev.nfctools.ac.1 */
    class C05651 implements OnSeekBarChangeListener {
        final /* synthetic */ ac f1875a;

        C05651(ac acVar) {
            this.f1875a = acVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < 1) {
                seekBar.setProgress(1);
                i = 1;
            }
            this.f1875a.f1880r.setText(String.valueOf(i) + " \u00b0C / " + String.valueOf(C0489d.m2067b(C0489d.m2056a((float) i))) + " \u00b0F");
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1876n = C0481m.TASK_COND_IS_BATTERY_TEMP.cM;
    }

    public ac() {
        this.f1877o = false;
        this.f1878p = null;
        this.f1879q = null;
        this.f1880r = null;
        this.f1881s = null;
    }

    private void m2643j() {
        this.f1881s.setMax(100);
        this.f1881s.setProgress(Math.round(C0500k.m2101c()));
        Intent intent = getIntent();
        this.f1877o = intent.getBooleanExtra("itemUpdate", false);
        this.f1878p = intent.getStringExtra("itemHash");
        if (this.f1877o && this.f1878p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1879q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f1881s, (String) hashMap.get("field2"), 100);
                C0490e.m2078a(this.f1882t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2644k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1879q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f1881s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f1882t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2645l() {
        String valueOf = String.valueOf(this.f1879q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f1881s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f1882t.getSelectedItemPosition());
    }

    private String m2646m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.battery_temp_state_arrays);
        if (this.f1882t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        int progress = this.f1881s.getProgress();
        return stringArray[this.f1879q.getSelectedItemPosition()] + " " + String.valueOf(progress) + " \u00b0C / " + String.valueOf(C0489d.m2067b(C0489d.m2056a((float) progress))) + " \u00b0F \n" + string;
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
        setContentView(C0622e.task_cond_battery_temp);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1879q = (Spinner) findViewById(C0621d.myBatterySpinner);
        this.f1880r = (TextView) findViewById(C0621d.myBatteryTempField);
        this.f1881s = (SeekBar) findViewById(C0621d.myBatterySeekbar);
        this.f1881s.setOnSeekBarChangeListener(new C05651(this));
        this.f1882t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1882t.setSelection(1);
        m2643j();
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
        intent.putExtra("requestType", f1876n);
        intent.putExtra("itemTask", m2645l());
        intent.putExtra("itemDescription", m2646m());
        intent.putExtra("itemHash", this.f1878p);
        intent.putExtra("itemUpdate", this.f1877o);
        intent.putExtra("itemFields", m2644k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
