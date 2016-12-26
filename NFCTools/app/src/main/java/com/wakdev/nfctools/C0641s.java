package com.wakdev.nfctools;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0502l;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0624g;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

/* renamed from: com.wakdev.nfctools.s */
public class C0641s extends C0316b {
    private static final int f2415n;
    private boolean f2416o;
    private String f2417p;
    private SeekBar f2418q;
    private SeekBar f2419r;
    private TextView f2420s;
    private TextView f2421t;
    private AudioManager f2422u;

    /* renamed from: com.wakdev.nfctools.s.1 */
    class C06391 implements OnSeekBarChangeListener {
        final /* synthetic */ C0641s f2413a;

        C06391(C0641s c0641s) {
            this.f2413a = c0641s;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < 1) {
                seekBar.setProgress(1);
                i = 1;
            }
            this.f2413a.m3064a(i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /* renamed from: com.wakdev.nfctools.s.2 */
    class C06402 implements OnSeekBarChangeListener {
        final /* synthetic */ C0641s f2414a;

        C06402(C0641s c0641s) {
            this.f2414a = c0641s;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < 1) {
                seekBar.setProgress(1);
                i = 1;
            }
            this.f2414a.m3066b(i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f2415n = C0481m.TASK_SOUND_BEEP.cM;
    }

    public C0641s() {
        this.f2416o = false;
        this.f2417p = null;
        this.f2418q = null;
        this.f2419r = null;
        this.f2420s = null;
        this.f2421t = null;
        this.f2422u = null;
    }

    private void m3064a(int i) {
        this.f2420s.setText(getString(C0625h.task_beep_freq_title) + " " + i + " " + getString(C0625h.task_beep_hz));
    }

    private void m3066b(int i) {
        this.f2421t.setText(getString(C0625h.task_beep_duration_title) + " " + getResources().getQuantityString(C0624g.task_beep_seconds, i, new Object[]{Integer.valueOf(i)}));
    }

    private void m3068j() {
        Intent intent = getIntent();
        this.f2416o = intent.getBooleanExtra("itemUpdate", false);
        this.f2417p = intent.getStringExtra("itemHash");
        if (this.f2416o && this.f2417p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2077a(this.f2418q, (String) hashMap.get("field1"), 5000);
                C0490e.m2077a(this.f2419r, (String) hashMap.get("field2"), 20);
            }
        }
    }

    private HashMap<String, String> m3069k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2418q.getProgress()));
        hashMap.put("field2", String.valueOf(this.f2419r.getProgress()));
        return hashMap;
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
        setContentView(C0622e.task_beep);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2422u = (AudioManager) getSystemService("audio");
        this.f2418q = (SeekBar) findViewById(C0621d.seekBarFreq);
        this.f2419r = (SeekBar) findViewById(C0621d.seekBarDuration);
        this.f2418q.setMax(5000);
        this.f2419r.setMax(20);
        this.f2418q.setProgress(660);
        this.f2419r.setProgress(2);
        this.f2420s = (TextView) findViewById(C0621d.textViewFreq);
        this.f2421t = (TextView) findViewById(C0621d.textViewDuration);
        m3064a(660);
        m3066b(2);
        this.f2418q.setOnSeekBarChangeListener(new C06391(this));
        this.f2419r.setOnSeekBarChangeListener(new C06402(this));
        m3068j();
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

    public void onTestButtonClick(View view) {
        try {
            C0502l.m2105a((double) this.f2418q.getProgress(), 8000, this.f2419r.getProgress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onValidateButtonClick(View view) {
        int progress = this.f2419r.getProgress();
        String valueOf = String.valueOf(this.f2418q.getProgress());
        String valueOf2 = String.valueOf(progress);
        if (valueOf.isEmpty() || valueOf2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        String str = valueOf + getString(C0625h.task_beep_hz) + " / " + getResources().getQuantityString(C0624g.task_beep_seconds, progress, new Object[]{Integer.valueOf(progress)});
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2415n);
        intent.putExtra("itemTask", valueOf + ";" + valueOf2);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f2417p);
        intent.putExtra("itemUpdate", this.f2416o);
        intent.putExtra("itemFields", m3069k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
