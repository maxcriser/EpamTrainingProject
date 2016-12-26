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
import com.wakdev.libs.commons.C0502l;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class bf extends C0316b {
    private static final int f2066n;
    private boolean f2067o;
    private String f2068p;
    private Spinner f2069q;
    private TextView f2070r;
    private SeekBar f2071s;
    private Spinner f2072t;
    private int f2073u;
    private int f2074v;
    private int f2075w;

    /* renamed from: com.wakdev.nfctools.bf.1 */
    class C05741 implements OnSeekBarChangeListener {
        final /* synthetic */ bf f2065a;

        C05741(bf bfVar) {
            this.f2065a = bfVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < this.f2065a.f2073u) {
                i = this.f2065a.f2073u;
                seekBar.setProgress(this.f2065a.f2073u);
            }
            this.f2065a.f2070r.setText(String.valueOf(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f2066n = C0481m.TASK_COND_IS_SOUND_LEVEL_4.cM;
    }

    public bf() {
        this.f2067o = false;
        this.f2068p = null;
        this.f2069q = null;
        this.f2070r = null;
        this.f2071s = null;
        this.f2073u = 1;
        this.f2074v = 100;
        this.f2075w = 1;
    }

    private void m2773j() {
        try {
            this.f2075w = C0502l.m2106b(1);
            this.f2074v = C0502l.m2104a(1);
            C0490e.m2077a(this.f2071s, String.valueOf(this.f2075w), this.f2075w);
            this.f2070r.setText(String.valueOf(this.f2075w));
        } catch (Exception e) {
        }
        this.f2071s.setMax(this.f2074v);
        this.f2071s.setProgress(this.f2075w);
        Intent intent = getIntent();
        this.f2067o = intent.getBooleanExtra("itemUpdate", false);
        this.f2068p = intent.getStringExtra("itemHash");
        if (this.f2067o && this.f2068p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2069q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f2071s, (String) hashMap.get("field2"), this.f2074v);
                C0490e.m2078a(this.f2072t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2774k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2069q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2071s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f2072t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2775l() {
        String valueOf = String.valueOf(this.f2069q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f2071s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f2072t.getSelectedItemPosition());
    }

    private String m2776m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.level_state_arrays);
        if (this.f2072t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2069q.getSelectedItemPosition()] + " " + String.valueOf(this.f2071s.getProgress()) + "\n" + string;
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
        setContentView(C0622e.task_cond_sound_level_4);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2069q = (Spinner) findViewById(C0621d.mySpinner);
        this.f2070r = (TextView) findViewById(C0621d.myPercent);
        this.f2071s = (SeekBar) findViewById(C0621d.mySeekbar);
        this.f2071s.setOnSeekBarChangeListener(new C05741(this));
        this.f2072t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2072t.setSelection(1);
        m2773j();
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
        intent.putExtra("requestType", f2066n);
        intent.putExtra("itemTask", m2775l());
        intent.putExtra("itemDescription", m2776m());
        intent.putExtra("itemHash", this.f2068p);
        intent.putExtra("itemUpdate", this.f2067o);
        intent.putExtra("itemFields", m2774k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
