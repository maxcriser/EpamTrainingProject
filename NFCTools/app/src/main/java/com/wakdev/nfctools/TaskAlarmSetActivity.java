package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import java.util.Calendar;
import java.util.HashMap;

public class TaskAlarmSetActivity extends C0316b {
    private static final int f1409n;
    private boolean f1410o;
    private String f1411p;
    private EditText f1412q;
    private TimePicker f1413r;

    static {
        f1409n = C0481m.TASK_ALARM_SET.cM;
    }

    public TaskAlarmSetActivity() {
        this.f1410o = false;
        this.f1411p = null;
    }

    private void m2401j() {
        Intent intent = getIntent();
        this.f1410o = intent.getBooleanExtra("itemUpdate", false);
        this.f1411p = intent.getStringExtra("itemHash");
        if (this.f1410o && this.f1411p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1412q, (String) hashMap.get("field1"));
                C0490e.m2079a(this.f1413r, (String) hashMap.get("field2"), (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2402k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1412q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1413r.getCurrentHour()));
        hashMap.put("field3", String.valueOf(this.f1413r.getCurrentMinute()));
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field1".equals(stringExtra2)) {
                if (intExtra != -1) {
                    this.f1412q.setText(new StringBuffer(this.f1412q.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1412q.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1412q.setText(this.f1412q.getText().toString() + stringExtra);
                this.f1412q.setSelection(this.f1412q.length());
            }
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
        setContentView(C0622e.task_alarm_set);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        Calendar instance = Calendar.getInstance();
        this.f1412q = (EditText) findViewById(C0621d.myAlarmTitle);
        this.f1413r = (TimePicker) findViewById(C0621d.myTimePicker);
        this.f1413r.setIs24HourView(Boolean.valueOf(true));
        this.f1413r.setCurrentHour(Integer.valueOf(instance.get(11)));
        this.f1413r.setCurrentMinute(Integer.valueOf(instance.get(12)));
        m2401j();
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

    public void onSelectVarsButtonClick1(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field1");
        intent.putExtra("kSelectionField", this.f1412q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1412q.getText().toString();
        String valueOf = String.valueOf(this.f1413r.getCurrentHour());
        String valueOf2 = String.valueOf(this.f1413r.getCurrentMinute());
        String str = valueOf + ":" + valueOf2;
        if (valueOf.length() == 1) {
            valueOf = "0" + valueOf;
        }
        if (valueOf2.length() == 1) {
            valueOf2 = "0" + valueOf2;
        }
        valueOf = valueOf + ":" + valueOf2;
        if (obj.isEmpty()) {
            valueOf2 = str;
        } else {
            valueOf2 = str + ";" + obj;
            valueOf = obj + " - " + valueOf;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1409n);
        intent.putExtra("itemTask", valueOf2);
        intent.putExtra("itemDescription", valueOf);
        intent.putExtra("itemHash", this.f1411p);
        intent.putExtra("itemUpdate", this.f1410o);
        intent.putExtra("itemFields", m2402k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
