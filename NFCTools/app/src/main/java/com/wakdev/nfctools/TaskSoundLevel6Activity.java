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

public class TaskSoundLevel6Activity extends C0316b {
    private static final int f1777n;
    private boolean f1778o;
    private String f1779p;
    private SeekBar f1780q;
    private TextView f1781r;
    private AudioManager f1782s;

    /* renamed from: com.wakdev.nfctools.TaskSoundLevel6Activity.1 */
    class C05571 implements OnSeekBarChangeListener {
        final /* synthetic */ TaskSoundLevel6Activity f1776a;

        C05571(TaskSoundLevel6Activity taskSoundLevel6Activity) {
            this.f1776a = taskSoundLevel6Activity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f1776a.f1781r.setText(this.f1776a.getString(C0625h.task_sound_level_volume_title) + " " + i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1777n = C0481m.TASK_SOUND_LEVEL_6.cM;
    }

    public TaskSoundLevel6Activity() {
        this.f1778o = false;
        this.f1779p = null;
        this.f1780q = null;
        this.f1781r = null;
        this.f1782s = null;
    }

    private void m2590j() {
        int streamMaxVolume;
        int streamVolume;
        Intent intent = getIntent();
        if (this.f1782s != null) {
            streamMaxVolume = this.f1782s.getStreamMaxVolume(8);
            streamVolume = this.f1782s.getStreamVolume(8);
        } else {
            streamMaxVolume = 7;
            streamVolume = 0;
        }
        this.f1780q.setMax(streamMaxVolume);
        this.f1780q.setProgress(streamVolume);
        this.f1778o = intent.getBooleanExtra("itemUpdate", false);
        this.f1779p = intent.getStringExtra("itemHash");
        if (this.f1778o && this.f1779p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2077a(this.f1780q, (String) hashMap.get("field1"), streamMaxVolume);
            }
        }
    }

    private HashMap<String, String> m2591k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1780q.getProgress()));
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
        setContentView(C0622e.task_sound_level_6);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1782s = (AudioManager) getSystemService("audio");
        this.f1780q = (SeekBar) findViewById(C0621d.seekBarSoundLevel);
        this.f1781r = (TextView) findViewById(C0621d.textViewVolume);
        this.f1780q.setOnSeekBarChangeListener(new C05571(this));
        m2590j();
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
        String valueOf = String.valueOf(this.f1780q.getProgress());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1777n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1779p);
        intent.putExtra("itemUpdate", this.f1778o);
        intent.putExtra("itemFields", m2591k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
