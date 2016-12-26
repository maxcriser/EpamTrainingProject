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
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class TaskSoundLevel4Activity extends C0316b {
    private static final int f1763n;
    private boolean f1764o;
    private String f1765p;
    private SeekBar f1766q;
    private TextView f1767r;
    private AudioManager f1768s;

    /* renamed from: com.wakdev.nfctools.TaskSoundLevel4Activity.1 */
    class C05551 implements OnSeekBarChangeListener {
        final /* synthetic */ TaskSoundLevel4Activity f1762a;

        C05551(TaskSoundLevel4Activity taskSoundLevel4Activity) {
            this.f1762a = taskSoundLevel4Activity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f1762a.f1767r.setText(this.f1762a.getString(C0625h.task_sound_level_volume_title) + " " + i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1763n = C0481m.TASK_SOUND_LEVEL_4.cM;
    }

    public TaskSoundLevel4Activity() {
        this.f1764o = false;
        this.f1765p = null;
        this.f1766q = null;
        this.f1767r = null;
        this.f1768s = null;
    }

    private void m2584j() {
        int streamMaxVolume;
        int streamVolume;
        Intent intent = getIntent();
        if (this.f1768s != null) {
            streamMaxVolume = this.f1768s.getStreamMaxVolume(1);
            streamVolume = this.f1768s.getStreamVolume(1);
        } else {
            streamMaxVolume = 7;
            streamVolume = 0;
        }
        this.f1766q.setMax(streamMaxVolume);
        this.f1766q.setProgress(streamVolume);
        this.f1764o = intent.getBooleanExtra("itemUpdate", false);
        this.f1765p = intent.getStringExtra("itemHash");
        if (this.f1764o && this.f1765p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2077a(this.f1766q, (String) hashMap.get("field1"), streamMaxVolume);
            }
        }
    }

    private HashMap<String, String> m2585k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1766q.getProgress()));
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
        setContentView(C0622e.task_sound_level_4);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1768s = (AudioManager) getSystemService("audio");
        this.f1766q = (SeekBar) findViewById(C0621d.seekBarSoundLevel);
        this.f1767r = (TextView) findViewById(C0621d.textViewVolume);
        this.f1766q.setOnSeekBarChangeListener(new C05551(this));
        m2584j();
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
        String valueOf = String.valueOf(this.f1766q.getProgress());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1763n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1765p);
        intent.putExtra("itemUpdate", this.f1764o);
        intent.putExtra("itemFields", m2585k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
