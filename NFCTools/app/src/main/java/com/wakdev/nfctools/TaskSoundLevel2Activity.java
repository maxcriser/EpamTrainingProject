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

public class TaskSoundLevel2Activity extends C0316b {
    private static final int f1749n;
    private boolean f1750o;
    private String f1751p;
    private SeekBar f1752q;
    private TextView f1753r;
    private AudioManager f1754s;

    /* renamed from: com.wakdev.nfctools.TaskSoundLevel2Activity.1 */
    class C05531 implements OnSeekBarChangeListener {
        final /* synthetic */ TaskSoundLevel2Activity f1748a;

        C05531(TaskSoundLevel2Activity taskSoundLevel2Activity) {
            this.f1748a = taskSoundLevel2Activity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f1748a.f1753r.setText(this.f1748a.getString(C0625h.task_sound_level_volume_title) + " " + i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1749n = C0481m.TASK_SOUND_LEVEL_2.cM;
    }

    public TaskSoundLevel2Activity() {
        this.f1750o = false;
        this.f1751p = null;
        this.f1752q = null;
        this.f1753r = null;
        this.f1754s = null;
    }

    private void m2578j() {
        int streamMaxVolume;
        int streamVolume;
        Intent intent = getIntent();
        if (this.f1754s != null) {
            streamMaxVolume = this.f1754s.getStreamMaxVolume(3);
            streamVolume = this.f1754s.getStreamVolume(3);
        } else {
            streamMaxVolume = 15;
            streamVolume = 0;
        }
        this.f1752q.setMax(streamMaxVolume);
        this.f1752q.setProgress(streamVolume);
        this.f1750o = intent.getBooleanExtra("itemUpdate", false);
        this.f1751p = intent.getStringExtra("itemHash");
        if (this.f1750o && this.f1751p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2077a(this.f1752q, (String) hashMap.get("field1"), streamMaxVolume);
            }
        }
    }

    private HashMap<String, String> m2579k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1752q.getProgress()));
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
        setContentView(C0622e.task_sound_level_2);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1754s = (AudioManager) getSystemService("audio");
        this.f1752q = (SeekBar) findViewById(C0621d.seekBarSoundLevel);
        this.f1753r = (TextView) findViewById(C0621d.textViewVolume);
        this.f1752q.setOnSeekBarChangeListener(new C05531(this));
        m2578j();
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
        String valueOf = String.valueOf(this.f1752q.getProgress());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1749n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1751p);
        intent.putExtra("itemUpdate", this.f1750o);
        intent.putExtra("itemFields", m2579k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
