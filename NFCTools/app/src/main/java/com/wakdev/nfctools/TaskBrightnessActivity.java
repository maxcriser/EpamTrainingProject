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

public class TaskBrightnessActivity extends C0316b {
    private static final int f1454n;
    private boolean f1455o;
    private String f1456p;
    private TextView f1457q;
    private SeekBar f1458r;

    /* renamed from: com.wakdev.nfctools.TaskBrightnessActivity.1 */
    class C05391 implements OnSeekBarChangeListener {
        final /* synthetic */ TaskBrightnessActivity f1453a;

        C05391(TaskBrightnessActivity taskBrightnessActivity) {
            this.f1453a = taskBrightnessActivity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f1453a.f1457q.setText(this.f1453a.getString(C0625h.task_brightness_level) + " " + i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1454n = C0481m.TASK_SCREEN_BRIGHTNESS.cM;
    }

    public TaskBrightnessActivity() {
        this.f1455o = false;
        this.f1456p = null;
        this.f1457q = null;
        this.f1458r = null;
    }

    private void m2423j() {
        Intent intent = getIntent();
        this.f1455o = intent.getBooleanExtra("itemUpdate", false);
        this.f1456p = intent.getStringExtra("itemHash");
        if (this.f1455o && this.f1456p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2077a(this.f1458r, (String) hashMap.get("field1"), 255);
            }
        }
    }

    private HashMap<String, String> m2424k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1458r.getProgress()));
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
        setContentView(C0622e.task_brightness);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1458r = (SeekBar) findViewById(C0621d.seekBarBrightness);
        this.f1457q = (TextView) findViewById(C0621d.textViewLevel);
        this.f1458r.setOnSeekBarChangeListener(new C05391(this));
        this.f1458r.setMax(255);
        this.f1458r.setProgress(100);
        m2423j();
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
        String valueOf = String.valueOf(this.f1458r.getProgress());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1454n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1456p);
        intent.putExtra("itemUpdate", this.f1455o);
        intent.putExtra("itemFields", m2424k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
