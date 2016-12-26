package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.HashMap;

public class TaskSoundModeActivity extends C0316b {
    private static final int f1790n;
    private boolean f1791o;
    private String f1792p;
    private Spinner f1793q;

    static {
        f1790n = C0481m.TASK_SOUND_MODE.cM;
    }

    public TaskSoundModeActivity() {
        this.f1791o = false;
        this.f1792p = null;
    }

    private void m2595j() {
        Intent intent = getIntent();
        this.f1791o = intent.getBooleanExtra("itemUpdate", false);
        this.f1792p = intent.getStringExtra("itemHash");
        if (this.f1791o && this.f1792p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1793q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2596k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1793q.getSelectedItemPosition()));
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
        setContentView(C0622e.task_sound_mode);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1793q = (Spinner) findViewById(C0621d.state_spinner);
        m2595j();
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
        String valueOf = String.valueOf(this.f1793q.getSelectedItemPosition());
        if (valueOf.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1790n);
        intent.putExtra("itemTask", valueOf);
        valueOf = "";
        switch (this.f1793q.getSelectedItemPosition()) {
            case C0627j.View_android_focusable /*0*/:
                valueOf = getString(C0625h.profile_mute);
                break;
            case C0627j.View_paddingStart /*1*/:
                valueOf = getString(C0625h.profile_vibrate);
                break;
            case C0627j.View_paddingEnd /*2*/:
                valueOf = getString(C0625h.profile_normal);
                break;
            case C0627j.Toolbar_subtitle /*3*/:
                valueOf = getString(C0625h.profile_toggle_normal_mute);
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                valueOf = getString(C0625h.profile_toggle_normal_vibrate);
                break;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                valueOf = getString(C0625h.profile_toggle_vibrate_mute);
                break;
        }
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1792p);
        intent.putExtra("itemUpdate", this.f1791o);
        intent.putExtra("itemFields", m2596k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
