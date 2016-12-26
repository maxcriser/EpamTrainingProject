package com.wakdev.nfctools;

import android.content.Intent;
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
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class TaskAdaptiveBrightnessActivity extends C0316b {
    private static final int f1389n;
    private boolean f1390o;
    private String f1391p;
    private TextView f1392q;
    private SeekBar f1393r;

    /* renamed from: com.wakdev.nfctools.TaskAdaptiveBrightnessActivity.1 */
    class C05361 implements OnSeekBarChangeListener {
        final /* synthetic */ TaskAdaptiveBrightnessActivity f1388a;

        C05361(TaskAdaptiveBrightnessActivity taskAdaptiveBrightnessActivity) {
            this.f1388a = taskAdaptiveBrightnessActivity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f1388a.f1392q.setText(this.f1388a.getString(C0625h.task_brightness_level) + " " + i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1389n = C0481m.TASK_SCREEN_ADAPTIVE_BRIGHTNESS.cM;
    }

    public TaskAdaptiveBrightnessActivity() {
        this.f1390o = false;
        this.f1391p = null;
        this.f1392q = null;
        this.f1393r = null;
    }

    private void m2393j() {
        Intent intent = getIntent();
        this.f1390o = intent.getBooleanExtra("itemUpdate", false);
        this.f1391p = intent.getStringExtra("itemHash");
        if (this.f1390o && this.f1391p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2077a(this.f1393r, (String) hashMap.get("field1"), 255);
            }
        }
    }

    private HashMap<String, String> m2394k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1393r.getProgress()));
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
        setContentView(C0622e.task_adaptive_brightness);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1393r = (SeekBar) findViewById(C0621d.seekBarBrightness);
        this.f1392q = (TextView) findViewById(C0621d.textViewLevel);
        this.f1393r.setOnSeekBarChangeListener(new C05361(this));
        this.f1393r.setMax(255);
        this.f1393r.setProgress(100);
        m2393j();
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
        String valueOf = String.valueOf(this.f1393r.getProgress());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1389n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1391p);
        intent.putExtra("itemUpdate", this.f1390o);
        intent.putExtra("itemFields", m2394k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
