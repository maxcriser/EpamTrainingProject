package com.wakdev.nfctools;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import com.wakdev.libs.commons.C0487b;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.Calendar;
import java.util.HashMap;

public class TaskCondTimeActivity extends C0316b {
    private static final int f1510r;
    public int f1511n;
    public int f1512o;
    public int f1513p;
    public int f1514q;
    private boolean f1515s;
    private String f1516t;
    private Button f1517u;
    private Button f1518v;
    private Spinner f1519w;

    /* renamed from: com.wakdev.nfctools.TaskCondTimeActivity.a */
    public static class C0542a extends DialogFragment implements OnTimeSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            TaskCondTimeActivity taskCondTimeActivity = (TaskCondTimeActivity) getActivity();
            return new TimePickerDialog(getActivity(), this, taskCondTimeActivity.f1513p, taskCondTimeActivity.f1514q, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            TaskCondTimeActivity taskCondTimeActivity = (TaskCondTimeActivity) getActivity();
            taskCondTimeActivity.f1513p = i;
            taskCondTimeActivity.f1514q = i2;
            taskCondTimeActivity.m2459j();
        }
    }

    /* renamed from: com.wakdev.nfctools.TaskCondTimeActivity.b */
    public static class C0543b extends DialogFragment implements OnTimeSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            TaskCondTimeActivity taskCondTimeActivity = (TaskCondTimeActivity) getActivity();
            return new TimePickerDialog(getActivity(), this, taskCondTimeActivity.f1511n, taskCondTimeActivity.f1512o, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            TaskCondTimeActivity taskCondTimeActivity = (TaskCondTimeActivity) getActivity();
            taskCondTimeActivity.f1511n = i;
            taskCondTimeActivity.f1512o = i2;
            taskCondTimeActivity.m2459j();
        }
    }

    static {
        f1510r = C0481m.TASK_COND_TIME.cM;
    }

    public TaskCondTimeActivity() {
        this.f1515s = false;
        this.f1516t = null;
    }

    private void m2454k() {
        Calendar instance = Calendar.getInstance();
        this.f1511n = instance.get(11);
        this.f1512o = instance.get(12);
        instance.add(11, 1);
        this.f1513p = instance.get(11);
        this.f1514q = instance.get(12);
    }

    private void m2455l() {
        Intent intent = getIntent();
        this.f1515s = intent.getBooleanExtra("itemUpdate", false);
        this.f1516t = intent.getStringExtra("itemHash");
        if (this.f1515s && this.f1516t != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                Calendar a;
                String str = (String) hashMap.get("field1");
                String str2 = (String) hashMap.get("field2");
                String str3 = (String) hashMap.get("field3");
                if (str != null) {
                    a = C0487b.m2052a(str, "HH:mm");
                    this.f1511n = a.get(11);
                    this.f1512o = a.get(12);
                }
                if (str2 != null) {
                    a = C0487b.m2052a(str2, "HH:mm");
                    this.f1513p = a.get(11);
                    this.f1514q = a.get(12);
                }
                if (str3 != null) {
                    this.f1519w.setSelection(Integer.parseInt(str3));
                }
            }
        }
    }

    private HashMap<String, String> m2456m() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1511n) + ":" + String.valueOf(this.f1512o));
        hashMap.put("field2", String.valueOf(this.f1513p) + ":" + String.valueOf(this.f1514q));
        hashMap.put("field3", String.valueOf(this.f1519w.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2457n() {
        String valueOf = String.valueOf(this.f1519w.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f1511n);
        String valueOf3 = String.valueOf(this.f1512o);
        String valueOf4 = String.valueOf(this.f1513p);
        String valueOf5 = String.valueOf(this.f1514q);
        if (valueOf2.length() == 1) {
            valueOf2 = "0" + valueOf2;
        }
        if (valueOf3.length() == 1) {
            valueOf3 = "0" + valueOf3;
        }
        if (valueOf4.length() == 1) {
            valueOf4 = "0" + valueOf4;
        }
        if (valueOf5.length() == 1) {
            valueOf5 = "0" + valueOf5;
        }
        return ((((valueOf2 + ":" + valueOf3) + "|") + valueOf4 + ":" + valueOf5) + "|") + valueOf;
    }

    private String m2458o() {
        String charSequence = this.f1517u.getText().toString();
        String charSequence2 = this.f1518v.getText().toString();
        String string = getString(C0625h.cond_desc_exclude);
        if (this.f1519w.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return charSequence + " - " + charSequence2 + "\n" + string + "\n";
    }

    public void m2459j() {
        this.f1517u.setText(C0487b.m2049a(this.f1511n, this.f1512o));
        this.f1518v.setText(C0487b.m2049a(this.f1513p, this.f1514q));
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

    public void onClickPickTimeEnd(View view) {
        new C0542a().show(getFragmentManager(), "TimePickerEnd");
    }

    public void onClickPickTimeStart(View view) {
        new C0543b().show(getFragmentManager(), "TimePickerStart");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.task_cond_time);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1517u = (Button) findViewById(C0621d.pickStartTime);
        this.f1518v = (Button) findViewById(C0621d.pickEndTime);
        this.f1519w = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1519w.setSelection(1);
        m2454k();
        m2455l();
        m2459j();
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
        String n = m2457n();
        String o = m2458o();
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1510r);
        intent.putExtra("itemTask", n);
        intent.putExtra("itemDescription", o);
        intent.putExtra("itemHash", this.f1516t);
        intent.putExtra("itemUpdate", this.f1515s);
        intent.putExtra("itemFields", m2456m());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
