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

public class be extends C0316b {
    private static final int f2055n;
    private boolean f2056o;
    private String f2057p;
    private Spinner f2058q;
    private TextView f2059r;
    private SeekBar f2060s;
    private Spinner f2061t;
    private int f2062u;
    private int f2063v;
    private int f2064w;

    /* renamed from: com.wakdev.nfctools.be.1 */
    class C05731 implements OnSeekBarChangeListener {
        final /* synthetic */ be f2054a;

        C05731(be beVar) {
            this.f2054a = beVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < this.f2054a.f2062u) {
                i = this.f2054a.f2062u;
                seekBar.setProgress(this.f2054a.f2062u);
            }
            this.f2054a.f2059r.setText(String.valueOf(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f2055n = C0481m.TASK_COND_IS_SOUND_LEVEL_3.cM;
    }

    public be() {
        this.f2056o = false;
        this.f2057p = null;
        this.f2058q = null;
        this.f2059r = null;
        this.f2060s = null;
        this.f2062u = 1;
        this.f2063v = 100;
        this.f2064w = 1;
    }

    private void m2767j() {
        try {
            this.f2064w = C0502l.m2106b(4);
            this.f2063v = C0502l.m2104a(4);
            C0490e.m2077a(this.f2060s, String.valueOf(this.f2064w), this.f2064w);
            this.f2059r.setText(String.valueOf(this.f2064w));
        } catch (Exception e) {
        }
        this.f2060s.setMax(this.f2063v);
        this.f2060s.setProgress(this.f2064w);
        Intent intent = getIntent();
        this.f2056o = intent.getBooleanExtra("itemUpdate", false);
        this.f2057p = intent.getStringExtra("itemHash");
        if (this.f2056o && this.f2057p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2058q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f2060s, (String) hashMap.get("field2"), this.f2063v);
                C0490e.m2078a(this.f2061t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2768k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2058q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2060s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f2061t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2769l() {
        String valueOf = String.valueOf(this.f2058q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f2060s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f2061t.getSelectedItemPosition());
    }

    private String m2770m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.level_state_arrays);
        if (this.f2061t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2058q.getSelectedItemPosition()] + " " + String.valueOf(this.f2060s.getProgress()) + "\n" + string;
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
        setContentView(C0622e.task_cond_sound_level_3);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2058q = (Spinner) findViewById(C0621d.mySpinner);
        this.f2059r = (TextView) findViewById(C0621d.myPercent);
        this.f2060s = (SeekBar) findViewById(C0621d.mySeekbar);
        this.f2060s.setOnSeekBarChangeListener(new C05731(this));
        this.f2061t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2061t.setSelection(1);
        m2767j();
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
        intent.putExtra("requestType", f2055n);
        intent.putExtra("itemTask", m2769l());
        intent.putExtra("itemDescription", m2770m());
        intent.putExtra("itemHash", this.f2057p);
        intent.putExtra("itemUpdate", this.f2056o);
        intent.putExtra("itemFields", m2768k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
