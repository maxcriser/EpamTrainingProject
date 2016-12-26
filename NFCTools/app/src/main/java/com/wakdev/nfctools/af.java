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
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class af extends C0316b {
    private static final int f1894n;
    private boolean f1895o;
    private String f1896p;
    private Spinner f1897q;
    private TextView f1898r;
    private SeekBar f1899s;
    private Spinner f1900t;
    private int f1901u;
    private int f1902v;
    private int f1903w;

    /* renamed from: com.wakdev.nfctools.af.1 */
    class C05661 implements OnSeekBarChangeListener {
        final /* synthetic */ af f1893a;

        C05661(af afVar) {
            this.f1893a = afVar;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < this.f1893a.f1901u) {
                i = this.f1893a.f1901u;
                seekBar.setProgress(this.f1893a.f1901u);
            }
            this.f1893a.f1898r.setText(String.valueOf(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1894n = C0481m.TASK_COND_IS_BRIGHTNESS_LEVEL.cM;
    }

    public af() {
        this.f1895o = false;
        this.f1896p = null;
        this.f1897q = null;
        this.f1898r = null;
        this.f1899s = null;
        this.f1901u = 0;
        this.f1902v = 255;
        this.f1903w = 100;
    }

    private void m2657j() {
        this.f1899s.setMax(this.f1902v);
        this.f1899s.setProgress(this.f1903w);
        this.f1898r.setText(String.valueOf(this.f1903w));
        Intent intent = getIntent();
        this.f1895o = intent.getBooleanExtra("itemUpdate", false);
        this.f1896p = intent.getStringExtra("itemHash");
        if (this.f1895o && this.f1896p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1897q, (String) hashMap.get("field1"));
                C0490e.m2077a(this.f1899s, (String) hashMap.get("field2"), this.f1902v);
                C0490e.m2078a(this.f1900t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2658k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1897q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f1899s.getProgress()));
        hashMap.put("field3", String.valueOf(this.f1900t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2659l() {
        String valueOf = String.valueOf(this.f1897q.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f1899s.getProgress());
        return valueOf + "|" + valueOf2 + "|" + String.valueOf(this.f1900t.getSelectedItemPosition());
    }

    private String m2660m() {
        String string = getString(C0625h.cond_desc_exclude);
        String[] stringArray = getResources().getStringArray(C0619b.level_state_arrays);
        if (this.f1900t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return stringArray[this.f1897q.getSelectedItemPosition()] + " " + String.valueOf(this.f1899s.getProgress()) + "\n" + string;
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
        setContentView(C0622e.task_cond_brightness_level);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1897q = (Spinner) findViewById(C0621d.mySpinner);
        this.f1898r = (TextView) findViewById(C0621d.myPercent);
        this.f1899s = (SeekBar) findViewById(C0621d.mySeekbar);
        this.f1899s.setOnSeekBarChangeListener(new C05661(this));
        this.f1900t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1900t.setSelection(1);
        m2657j();
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
        intent.putExtra("requestType", f1894n);
        intent.putExtra("itemTask", m2659l());
        intent.putExtra("itemDescription", m2660m());
        intent.putExtra("itemHash", this.f1896p);
        intent.putExtra("itemUpdate", this.f1895o);
        intent.putExtra("itemFields", m2658k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
