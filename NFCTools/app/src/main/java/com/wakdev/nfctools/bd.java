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

public class bd extends C0316b {
    private static final int f2044n;
    private boolean f2045o;
    private String f2046p;
    private Spinner f2047q;
    private TextView f2048r;
    private SeekBar f2049s;
    private Spinner f2050t;
    private int f2051u;
    private int f2052v;
    private int f2053w;

    /* renamed from: com.wakdev.nfctools.bd.1 */
    class C05721 implements OnSeekBarChangeListener {
        final /* synthetic */ bd f2043a;

        C05721(bd bdVar) {
            this.f2043a = bdVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < this.f2043a.f2051u) {
                i = this.f2043a.f2051u;
                seekBar.setProgress(this.f2043a.f2051u);
            }
            this.f2043a.f2048r.setText(String.valueOf(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f2044n = C0481m.TASK_COND_IS_SOUND_LEVEL_2.cM;
    }

    public bd() {
        this.f2045o = false;
        this.f2046p = null;
        this.f2047q = null;
        this.f2048r = null;
        this.f2049s = null;
        this.f2051u = 1;
        this.f2052v = 100;
        this.f2053w = 1;
    }

    private void m2761j() {
        try {
            this.f2053w = C0502l.m2106b(3);
            this.f2052v = C0502l.m2104a(3);
            C0490e.m2077a(this.f2049s, String.valueOf(this.f2053w), this.f2053w);
            this.f2048r.setText(String.valueOf(this.f2053w));
        } catch (Exception e) {
        }
        this.f2049s.setMax(this.f2052v);
        this.f2049s.setProgress(this.f2053w);
        Intent intent = getIntent();
        this.f2045o = intent.getBooleanExtra("itemUpdate", false);
        this.f2046p = intent.getStringExtra("itemHash");
        if (this.f2045o && this.f2046p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2047q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f2049s, (String) hashMap.get("field2"), this.f2052v);
                C0490e.m2078a(this.f2050t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2762k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2047q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2049s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f2050t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2763l() {
        String valueOf = String.valueOf(this.f2047q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f2049s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f2050t.getSelectedItemPosition());
    }

    private String m2764m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.level_state_arrays);
        if (this.f2050t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2047q.getSelectedItemPosition()] + " " + String.valueOf(this.f2049s.getProgress()) + "\n" + string;
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
        setContentView(C0622e.task_cond_sound_level_2);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2047q = (Spinner) findViewById(C0621d.mySpinner);
        this.f2048r = (TextView) findViewById(C0621d.myPercent);
        this.f2049s = (SeekBar) findViewById(C0621d.mySeekbar);
        this.f2049s.setOnSeekBarChangeListener(new C05721(this));
        this.f2050t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2050t.setSelection(1);
        m2761j();
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
        intent.putExtra("requestType", f2044n);
        intent.putExtra("itemTask", m2763l());
        intent.putExtra("itemDescription", m2764m());
        intent.putExtra("itemHash", this.f2046p);
        intent.putExtra("itemUpdate", this.f2045o);
        intent.putExtra("itemFields", m2762k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
