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

public class bi extends C0316b {
    private static final int f2099n;
    private boolean f2100o;
    private String f2101p;
    private Spinner f2102q;
    private TextView f2103r;
    private SeekBar f2104s;
    private Spinner f2105t;
    private int f2106u;
    private int f2107v;
    private int f2108w;

    /* renamed from: com.wakdev.nfctools.bi.1 */
    class C05771 implements OnSeekBarChangeListener {
        final /* synthetic */ bi f2098a;

        C05771(bi biVar) {
            this.f2098a = biVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < this.f2098a.f2106u) {
                i = this.f2098a.f2106u;
                seekBar.setProgress(this.f2098a.f2106u);
            }
            this.f2098a.f2103r.setText(String.valueOf(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f2099n = C0481m.TASK_COND_IS_SOUND_LEVEL_7.cM;
    }

    public bi() {
        this.f2100o = false;
        this.f2101p = null;
        this.f2102q = null;
        this.f2103r = null;
        this.f2104s = null;
        this.f2106u = 1;
        this.f2107v = 100;
        this.f2108w = 1;
    }

    private void m2791j() {
        try {
            this.f2108w = C0502l.m2106b(0);
            this.f2107v = C0502l.m2104a(0);
            C0490e.m2077a(this.f2104s, String.valueOf(this.f2108w), this.f2108w);
            this.f2103r.setText(String.valueOf(this.f2108w));
        } catch (Exception e) {
        }
        this.f2104s.setMax(this.f2107v);
        this.f2104s.setProgress(this.f2108w);
        Intent intent = getIntent();
        this.f2100o = intent.getBooleanExtra("itemUpdate", false);
        this.f2101p = intent.getStringExtra("itemHash");
        if (this.f2100o && this.f2101p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2102q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f2104s, (String) hashMap.get("field2"), this.f2107v);
                C0490e.m2078a(this.f2105t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2792k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2102q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2104s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f2105t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2793l() {
        String valueOf = String.valueOf(this.f2102q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f2104s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f2105t.getSelectedItemPosition());
    }

    private String m2794m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.level_state_arrays);
        if (this.f2105t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f2102q.getSelectedItemPosition()] + " " + String.valueOf(this.f2104s.getProgress()) + "\n" + string;
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
        setContentView(C0622e.task_cond_sound_level_7);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2102q = (Spinner) findViewById(C0621d.mySpinner);
        this.f2103r = (TextView) findViewById(C0621d.myPercent);
        this.f2104s = (SeekBar) findViewById(C0621d.mySeekbar);
        this.f2104s.setOnSeekBarChangeListener(new C05771(this));
        this.f2105t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2105t.setSelection(1);
        m2791j();
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
        intent.putExtra("requestType", f2099n);
        intent.putExtra("itemTask", m2793l());
        intent.putExtra("itemDescription", m2794m());
        intent.putExtra("itemHash", this.f2101p);
        intent.putExtra("itemUpdate", this.f2100o);
        intent.putExtra("itemFields", m2792k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
