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

public class bg extends C0316b {
    private static final int f2077n;
    private boolean f2078o;
    private String f2079p;
    private Spinner f2080q;
    private TextView f2081r;
    private SeekBar f2082s;
    private Spinner f2083t;
    private int f2084u;
    private int f2085v;
    private int f2086w;

    /* renamed from: com.wakdev.nfctools.bg.1 */
    class C05751 implements OnSeekBarChangeListener {
        final /* synthetic */ bg f2076a;

        C05751(bg bgVar) {
            this.f2076a = bgVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < this.f2076a.f2084u) {
                i = this.f2076a.f2084u;
                seekBar.setProgress(this.f2076a.f2084u);
            }
            this.f2076a.f2081r.setText(String.valueOf(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f2077n = C0481m.TASK_COND_IS_SOUND_LEVEL_5.cM;
    }

    public bg() {
        this.f2078o = false;
        this.f2079p = null;
        this.f2080q = null;
        this.f2081r = null;
        this.f2082s = null;
        this.f2084u = 1;
        this.f2085v = 100;
        this.f2086w = 1;
    }

    private void m2779j() {
        try {
            this.f2086w = C0502l.m2106b(5);
            this.f2085v = C0502l.m2104a(5);
            C0490e.m2077a(this.f2082s, String.valueOf(this.f2086w), this.f2086w);
            this.f2081r.setText(String.valueOf(this.f2086w));
        } catch (Exception e) {
        }
        this.f2082s.setMax(this.f2085v);
        this.f2082s.setProgress(this.f2086w);
        Intent intent = getIntent();
        this.f2078o = intent.getBooleanExtra("itemUpdate", false);
        this.f2079p = intent.getStringExtra("itemHash");
        if (this.f2078o && this.f2079p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2080q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f2082s, (String) hashMap.get("field2"), this.f2085v);
                C0490e.m2078a(this.f2083t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2780k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2080q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2082s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f2083t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2781l() {
        String valueOf = String.valueOf(this.f2080q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f2082s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f2083t.getSelectedItemPosition());
    }

    private String m2782m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.level_state_arrays);
        if (this.f2083t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2080q.getSelectedItemPosition()] + " " + String.valueOf(this.f2082s.getProgress()) + "\n" + string;
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
        setContentView(C0622e.task_cond_sound_level_5);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2080q = (Spinner) findViewById(C0621d.mySpinner);
        this.f2081r = (TextView) findViewById(C0621d.myPercent);
        this.f2082s = (SeekBar) findViewById(C0621d.mySeekbar);
        this.f2082s.setOnSeekBarChangeListener(new C05751(this));
        this.f2083t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2083t.setSelection(1);
        m2779j();
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
        intent.putExtra("requestType", f2077n);
        intent.putExtra("itemTask", m2781l());
        intent.putExtra("itemDescription", m2782m());
        intent.putExtra("itemHash", this.f2079p);
        intent.putExtra("itemUpdate", this.f2078o);
        intent.putExtra("itemFields", m2780k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
