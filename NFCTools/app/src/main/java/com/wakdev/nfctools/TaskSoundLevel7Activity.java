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

public class TaskSoundLevel7Activity extends C0316b {
    private static final int f1784n;
    private boolean f1785o;
    private String f1786p;
    private SeekBar f1787q;
    private TextView f1788r;
    private AudioManager f1789s;

    /* renamed from: com.wakdev.nfctools.TaskSoundLevel7Activity.1 */
    class C05581 implements OnSeekBarChangeListener {
        final /* synthetic */ TaskSoundLevel7Activity f1783a;

        C05581(TaskSoundLevel7Activity taskSoundLevel7Activity) {
            this.f1783a = taskSoundLevel7Activity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f1783a.f1788r.setText(this.f1783a.getString(C0625h.task_sound_level_volume_title) + " " + i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1784n = C0481m.TASK_SOUND_LEVEL_7.cM;
    }

    public TaskSoundLevel7Activity() {
        this.f1785o = false;
        this.f1786p = null;
        this.f1787q = null;
        this.f1788r = null;
        this.f1789s = null;
    }

    private void m2593j() {
        int streamMaxVolume;
        int streamVolume;
        Intent intent = getIntent();
        if (this.f1789s != null) {
            streamMaxVolume = this.f1789s.getStreamMaxVolume(0);
            streamVolume = this.f1789s.getStreamVolume(0);
        } else {
            streamMaxVolume = 7;
            streamVolume = 0;
        }
        this.f1787q.setMax(streamMaxVolume);
        this.f1787q.setProgress(streamVolume);
        this.f1785o = intent.getBooleanExtra("itemUpdate", false);
        this.f1786p = intent.getStringExtra("itemHash");
        if (this.f1785o && this.f1786p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2077a(this.f1787q, (String) hashMap.get("field1"), streamMaxVolume);
            }
        }
    }

    private HashMap<String, String> m2594k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1787q.getProgress()));
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
        setContentView(C0622e.task_sound_level_7);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1789s = (AudioManager) getSystemService("audio");
        this.f1787q = (SeekBar) findViewById(C0621d.seekBarSoundLevel);
        this.f1788r = (TextView) findViewById(C0621d.textViewVolume);
        this.f1787q.setOnSeekBarChangeListener(new C05581(this));
        m2593j();
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
        String valueOf = String.valueOf(this.f1787q.getProgress());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1784n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1786p);
        intent.putExtra("itemUpdate", this.f1785o);
        intent.putExtra("itemFields", m2594k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
