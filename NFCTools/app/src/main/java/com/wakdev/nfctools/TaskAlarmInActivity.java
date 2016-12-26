package com.wakdev.nfctools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.HashMap;

public class TaskAlarmInActivity extends C0316b {
    private static final int f1403n;
    private boolean f1404o;
    private String f1405p;
    private EditText f1406q;
    private NumberPicker f1407r;
    private NumberPicker f1408s;

    /* renamed from: com.wakdev.nfctools.TaskAlarmInActivity.a */
    private class C0538a implements Formatter {
        final /* synthetic */ TaskAlarmInActivity f1402a;

        private C0538a(TaskAlarmInActivity taskAlarmInActivity) {
            this.f1402a = taskAlarmInActivity;
        }

        @SuppressLint({"DefaultLocale"})
        public String format(int i) {
            return String.format("%02d", new Object[]{Integer.valueOf(i)});
        }
    }

    static {
        f1403n = C0481m.TASK_ALARM_IN.cM;
    }

    public TaskAlarmInActivity() {
        this.f1404o = false;
        this.f1405p = null;
    }

    private void m2399j() {
        Intent intent = getIntent();
        this.f1404o = intent.getBooleanExtra("itemUpdate", false);
        this.f1405p = intent.getStringExtra("itemHash");
        if (this.f1404o && this.f1405p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1406q, (String) hashMap.get("field1"));
                C0490e.m2076a(this.f1407r, (String) hashMap.get("field2"));
                C0490e.m2076a(this.f1408s, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2400k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1406q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1407r.getValue()));
        hashMap.put("field3", String.valueOf(this.f1408s.getValue()));
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
                    this.f1406q.setText(new StringBuffer(this.f1406q.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1406q.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1406q.setText(this.f1406q.getText().toString() + stringExtra);
                this.f1406q.setSelection(this.f1406q.length());
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
        setContentView(C0622e.task_alarm_in);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        Calendar.getInstance();
        this.f1406q = (EditText) findViewById(C0621d.myAlarmTitle);
        this.f1407r = (NumberPicker) findViewById(C0621d.numberPickerHours);
        this.f1407r.setFormatter(new C0538a());
        this.f1407r.setMaxValue(23);
        this.f1407r.setMinValue(0);
        this.f1408s = (NumberPicker) findViewById(C0621d.numberPickerMinutes);
        this.f1408s.setFormatter(new C0538a());
        this.f1408s.setMaxValue(59);
        this.f1408s.setMinValue(0);
        m2399j();
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
        intent.putExtra("kSelectionField", this.f1406q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1406q.getText().toString();
        String valueOf = String.valueOf(this.f1407r.getValue());
        String valueOf2 = String.valueOf(this.f1408s.getValue());
        if (this.f1407r.getValue() == 0 && this.f1408s.getValue() == 0) {
            Object obj2 = null;
        } else {
            int i = 1;
        }
        String str = valueOf + ":" + valueOf2;
        if (valueOf.length() == 1) {
            valueOf = "0" + valueOf;
        }
        String str2 = valueOf + ":" + (valueOf2.length() == 1 ? "0" + valueOf2 : valueOf2);
        if (obj.isEmpty()) {
            valueOf = str;
        } else {
            valueOf = str + ";" + obj;
            str2 = obj + " - " + str2;
        }
        if (obj2 != null) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1403n);
            intent.putExtra("itemTask", valueOf);
            intent.putExtra("itemDescription", str2);
            intent.putExtra("itemHash", this.f1405p);
            intent.putExtra("itemUpdate", this.f1404o);
            intent.putExtra("itemFields", m2400k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
