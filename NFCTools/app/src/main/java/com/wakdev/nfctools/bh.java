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

public class bh extends C0316b {
    private static final int f2088n;
    private boolean f2089o;
    private String f2090p;
    private Spinner f2091q;
    private TextView f2092r;
    private SeekBar f2093s;
    private Spinner f2094t;
    private int f2095u;
    private int f2096v;
    private int f2097w;

    /* renamed from: com.wakdev.nfctools.bh.1 */
    class C05761 implements OnSeekBarChangeListener {
        final /* synthetic */ bh f2087a;

        C05761(bh bhVar) {
            this.f2087a = bhVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < this.f2087a.f2095u) {
                i = this.f2087a.f2095u;
                seekBar.setProgress(this.f2087a.f2095u);
            }
            this.f2087a.f2092r.setText(String.valueOf(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f2088n = C0481m.TASK_COND_IS_SOUND_LEVEL_6.cM;
    }

    public bh() {
        this.f2089o = false;
        this.f2090p = null;
        this.f2091q = null;
        this.f2092r = null;
        this.f2093s = null;
        this.f2095u = 1;
        this.f2096v = 100;
        this.f2097w = 1;
    }

    private void m2785j() {
        try {
            this.f2097w = C0502l.m2106b(8);
            this.f2096v = C0502l.m2104a(8);
            C0490e.m2077a(this.f2093s, String.valueOf(this.f2097w), this.f2097w);
            this.f2092r.setText(String.valueOf(this.f2097w));
        } catch (Exception e) {
        }
        this.f2093s.setMax(this.f2096v);
        this.f2093s.setProgress(this.f2097w);
        Intent intent = getIntent();
        this.f2089o = intent.getBooleanExtra("itemUpdate", false);
        this.f2090p = intent.getStringExtra("itemHash");
        if (this.f2089o && this.f2090p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2091q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f2093s, (String) hashMap.get("field2"), this.f2096v);
                C0490e.m2078a(this.f2094t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2786k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2091q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2093s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f2094t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2787l() {
        String valueOf = String.valueOf(this.f2091q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f2093s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f2094t.getSelectedItemPosition());
    }

    private String m2788m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.level_state_arrays);
        if (this.f2094t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2091q.getSelectedItemPosition()] + " " + String.valueOf(this.f2093s.getProgress()) + "\n" + string;
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
        setContentView(C0622e.task_cond_sound_level_6);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2091q = (Spinner) findViewById(C0621d.mySpinner);
        this.f2092r = (TextView) findViewById(C0621d.myPercent);
        this.f2093s = (SeekBar) findViewById(C0621d.mySeekbar);
        this.f2093s.setOnSeekBarChangeListener(new C05761(this));
        this.f2094t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2094t.setSelection(1);
        m2785j();
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
        intent.putExtra("requestType", f2088n);
        intent.putExtra("itemTask", m2787l());
        intent.putExtra("itemDescription", m2788m());
        intent.putExtra("itemHash", this.f2090p);
        intent.putExtra("itemUpdate", this.f2089o);
        intent.putExtra("itemFields", m2786k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
