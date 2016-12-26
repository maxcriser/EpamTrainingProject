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

public class bc extends C0316b {
    private static final int f2033n;
    private boolean f2034o;
    private String f2035p;
    private Spinner f2036q;
    private TextView f2037r;
    private SeekBar f2038s;
    private Spinner f2039t;
    private int f2040u;
    private int f2041v;
    private int f2042w;

    /* renamed from: com.wakdev.nfctools.bc.1 */
    class C05711 implements OnSeekBarChangeListener {
        final /* synthetic */ bc f2032a;

        C05711(bc bcVar) {
            this.f2032a = bcVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < this.f2032a.f2040u) {
                i = this.f2032a.f2040u;
                seekBar.setProgress(this.f2032a.f2040u);
            }
            this.f2032a.f2037r.setText(String.valueOf(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f2033n = C0481m.TASK_COND_IS_SOUND_LEVEL_1.cM;
    }

    public bc() {
        this.f2034o = false;
        this.f2035p = null;
        this.f2036q = null;
        this.f2037r = null;
        this.f2038s = null;
        this.f2040u = 1;
        this.f2041v = 100;
        this.f2042w = 1;
    }

    private void m2755j() {
        try {
            this.f2042w = C0502l.m2106b(2);
            this.f2041v = C0502l.m2104a(2);
            C0490e.m2077a(this.f2038s, String.valueOf(this.f2042w), this.f2042w);
            this.f2037r.setText(String.valueOf(this.f2042w));
        } catch (Exception e) {
        }
        this.f2038s.setMax(this.f2041v);
        this.f2038s.setProgress(this.f2042w);
        Intent intent = getIntent();
        this.f2034o = intent.getBooleanExtra("itemUpdate", false);
        this.f2035p = intent.getStringExtra("itemHash");
        if (this.f2034o && this.f2035p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2036q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f2038s, (String) hashMap.get("field2"), this.f2041v);
                C0490e.m2078a(this.f2039t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2756k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2036q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2038s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f2039t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2757l() {
        String valueOf = String.valueOf(this.f2036q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f2038s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f2039t.getSelectedItemPosition());
    }

    private String m2758m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.level_state_arrays);
        if (this.f2039t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2036q.getSelectedItemPosition()] + " " + String.valueOf(this.f2038s.getProgress()) + "\n" + string;
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
        setContentView(C0622e.task_cond_sound_level_1);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2036q = (Spinner) findViewById(C0621d.mySpinner);
        this.f2037r = (TextView) findViewById(C0621d.myPercent);
        this.f2038s = (SeekBar) findViewById(C0621d.mySeekbar);
        this.f2038s.setOnSeekBarChangeListener(new C05711(this));
        this.f2039t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2039t.setSelection(1);
        m2755j();
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
        intent.putExtra("requestType", f2033n);
        intent.putExtra("itemTask", m2757l());
        intent.putExtra("itemDescription", m2758m());
        intent.putExtra("itemHash", this.f2035p);
        intent.putExtra("itemUpdate", this.f2034o);
        intent.putExtra("itemFields", m2756k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
