package com.wakdev.nfctools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
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

public class TaskTimerSetActivity extends C0316b {
    private static final int f1816n;
    private boolean f1817o;
    private String f1818p;
    private NumberPicker f1819q;
    private NumberPicker f1820r;
    private NumberPicker f1821s;

    /* renamed from: com.wakdev.nfctools.TaskTimerSetActivity.a */
    private class C0560a implements Formatter {
        final /* synthetic */ TaskTimerSetActivity f1815a;

        private C0560a(TaskTimerSetActivity taskTimerSetActivity) {
            this.f1815a = taskTimerSetActivity;
        }

        @SuppressLint({"DefaultLocale"})
        public String format(int i) {
            return String.format("%02d", new Object[]{Integer.valueOf(i)});
        }
    }

    static {
        f1816n = C0481m.TASK_TIMER_SET.cM;
    }

    public TaskTimerSetActivity() {
        this.f1817o = false;
        this.f1818p = null;
    }

    private void m2607j() {
        Intent intent = getIntent();
        this.f1817o = intent.getBooleanExtra("itemUpdate", false);
        this.f1818p = intent.getStringExtra("itemHash");
        if (this.f1817o && this.f1818p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2076a(this.f1819q, (String) hashMap.get("field1"));
                C0490e.m2076a(this.f1820r, (String) hashMap.get("field2"));
                C0490e.m2076a(this.f1821s, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2608k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1819q.getValue()));
        hashMap.put("field2", String.valueOf(this.f1820r.getValue()));
        hashMap.put("field3", String.valueOf(this.f1821s.getValue()));
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
        setContentView(C0622e.task_timer_set);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1819q = (NumberPicker) findViewById(C0621d.numberPickerHours);
        this.f1819q.setFormatter(new C0560a());
        this.f1819q.setMaxValue(23);
        this.f1819q.setMinValue(0);
        this.f1820r = (NumberPicker) findViewById(C0621d.numberPickerMinutes);
        this.f1820r.setFormatter(new C0560a());
        this.f1820r.setMaxValue(59);
        this.f1820r.setMinValue(0);
        this.f1821s = (NumberPicker) findViewById(C0621d.numberPickerSeconds);
        this.f1821s.setFormatter(new C0560a());
        this.f1821s.setMaxValue(59);
        this.f1821s.setMinValue(0);
        m2607j();
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
        int value = this.f1819q.getValue();
        int value2 = this.f1820r.getValue();
        int value3 = this.f1821s.getValue();
        int i = (((value * 60) * 60) + (value2 * 60)) + value3;
        String valueOf = String.valueOf(value);
        String valueOf2 = String.valueOf(value2);
        String valueOf3 = String.valueOf(value3);
        if (valueOf.length() == 1) {
            valueOf = "0" + valueOf;
        }
        if (valueOf2.length() == 1) {
            valueOf2 = "0" + valueOf2;
        }
        if (valueOf3.length() == 1) {
            valueOf3 = "0" + valueOf3;
        }
        valueOf2 = valueOf + ":" + valueOf2 + ":" + valueOf3;
        valueOf3 = String.valueOf(i);
        if (value == 0 && value2 == 0 && value3 == 0 && i <= 86400) {
            Object obj = null;
        } else {
            int i2 = 1;
        }
        if (obj != null) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1816n);
            intent.putExtra("itemTask", valueOf3);
            intent.putExtra("itemDescription", valueOf2);
            intent.putExtra("itemHash", this.f1818p);
            intent.putExtra("itemUpdate", this.f1817o);
            intent.putExtra("itemFields", m2608k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
