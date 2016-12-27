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
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.HashMap;

public class TaskSleepTimerActivity extends C0316b {
    private static final int f1737n;
    private boolean f1738o;
    private String f1739p;
    private Spinner f1740q;

    static {
        f1737n = C0481m.TASK_MISC_SLEEP_TIMER.cM;
    }

    public TaskSleepTimerActivity() {
        this.f1738o = false;
        this.f1739p = null;
    }

    private void m2571j() {
        Intent intent = getIntent();
        this.f1738o = intent.getBooleanExtra("itemUpdate", false);
        this.f1739p = intent.getStringExtra("itemHash");
        if (this.f1738o && this.f1739p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1740q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2572k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1740q.getSelectedItemPosition()));
        return hashMap;
    }

    private int m2573l() {
        switch (this.f1740q.getSelectedItemPosition()) {
            case C0627j.View_android_focusable /*0*/:
                return 1;
            case C0627j.View_paddingStart /*1*/:
                return 2;
            case C0627j.View_paddingEnd /*2*/:
                return 3;
            case C0627j.Toolbar_subtitle /*3*/:
                return 4;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                return 5;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                return 10;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                return 30;
            case C0627j.Toolbar_contentInsetRight /*7*/:
                return 60;
            case C0627j.Toolbar_popupTheme /*8*/:
                return 120;
            case C0627j.Toolbar_titleTextAppearance /*9*/:
                return 300;
            default:
                return -1;
        }
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
        setContentView(C0622e.task_sleep_timer);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1740q = (Spinner) findViewById(C0621d.state_spinner);
        m2571j();
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
        int l = m2573l();
        if (l != -1) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1737n);
            intent.putExtra("itemTask", String.valueOf(l));
            intent.putExtra("itemDescription", getResources().getStringArray(C0619b.task_sleep_timer_arrays)[this.f1740q.getSelectedItemPosition()]);
            intent.putExtra("itemHash", this.f1739p);
            intent.putExtra("itemUpdate", this.f1738o);
            intent.putExtra("itemFields", m2572k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}