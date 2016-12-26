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

public class TaskSoundLevel3Activity extends C0316b {
    private static final int f1756n;
    private boolean f1757o;
    private String f1758p;
    private SeekBar f1759q;
    private TextView f1760r;
    private AudioManager f1761s;

    /* renamed from: com.wakdev.nfctools.TaskSoundLevel3Activity.1 */
    class C05541 implements OnSeekBarChangeListener {
        final /* synthetic */ TaskSoundLevel3Activity f1755a;

        C05541(TaskSoundLevel3Activity taskSoundLevel3Activity) {
            this.f1755a = taskSoundLevel3Activity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f1755a.f1760r.setText(this.f1755a.getString(C0625h.task_sound_level_volume_title) + " " + i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        f1756n = C0481m.TASK_SOUND_LEVEL_3.cM;
    }

    public TaskSoundLevel3Activity() {
        this.f1757o = false;
        this.f1758p = null;
        this.f1759q = null;
        this.f1760r = null;
        this.f1761s = null;
    }

    private void m2581j() {
        int streamMaxVolume;
        int streamVolume;
        Intent intent = getIntent();
        if (this.f1761s != null) {
            streamMaxVolume = this.f1761s.getStreamMaxVolume(4);
            streamVolume = this.f1761s.getStreamVolume(4);
        } else {
            streamMaxVolume = 15;
            streamVolume = 0;
        }
        this.f1759q.setMax(streamMaxVolume);
        this.f1759q.setProgress(streamVolume);
        this.f1757o = intent.getBooleanExtra("itemUpdate", false);
        this.f1758p = intent.getStringExtra("itemHash");
        if (this.f1757o && this.f1758p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2077a(this.f1759q, (String) hashMap.get("field1"), streamMaxVolume);
            }
        }
    }

    private HashMap<String, String> m2582k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1759q.getProgress()));
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
        setContentView(C0622e.task_sound_level_3);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1761s = (AudioManager) getSystemService("audio");
        this.f1759q = (SeekBar) findViewById(C0621d.seekBarSoundLevel);
        this.f1760r = (TextView) findViewById(C0621d.textViewVolume);
        this.f1759q.setOnSeekBarChangeListener(new C05541(this));
        m2581j();
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
        String valueOf = String.valueOf(this.f1759q.getProgress());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1756n);
        intent.putExtra("itemTask", valueOf);
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1758p);
        intent.putExtra("itemUpdate", this.f1757o);
        intent.putExtra("itemFields", m2582k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
