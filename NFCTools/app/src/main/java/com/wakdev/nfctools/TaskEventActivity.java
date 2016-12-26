package com.wakdev.nfctools;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import com.wakdev.libs.commons.C0487b;
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

public class TaskEventActivity extends C0316b {
    private static final int f1569x;
    private EditText f1570A;
    private EditText f1571B;
    private EditText f1572C;
    private Button f1573D;
    private Button f1574E;
    private Button f1575F;
    private Button f1576G;
    private Spinner f1577H;
    public int f1578n;
    public int f1579o;
    public int f1580p;
    public int f1581q;
    public int f1582r;
    public int f1583s;
    public int f1584t;
    public int f1585u;
    public int f1586v;
    public int f1587w;
    private boolean f1588y;
    private String f1589z;

    /* renamed from: com.wakdev.nfctools.TaskEventActivity.1 */
    class C05461 implements OnItemSelectedListener {
        final /* synthetic */ TaskEventActivity f1568a;

        C05461(TaskEventActivity taskEventActivity) {
            this.f1568a = taskEventActivity;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i == 1) {
                this.f1568a.f1575F.setEnabled(false);
                this.f1568a.f1576G.setEnabled(false);
                return;
            }
            this.f1568a.f1575F.setEnabled(true);
            this.f1568a.f1576G.setEnabled(true);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.wakdev.nfctools.TaskEventActivity.a */
    public static class C0547a extends DialogFragment implements OnDateSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            TaskEventActivity taskEventActivity = (TaskEventActivity) getActivity();
            return new DatePickerDialog(getActivity(), this, taskEventActivity.f1581q, taskEventActivity.f1582r, taskEventActivity.f1583s);
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            TaskEventActivity taskEventActivity = (TaskEventActivity) getActivity();
            taskEventActivity.f1581q = i;
            taskEventActivity.f1582r = i2;
            taskEventActivity.f1583s = i3;
            taskEventActivity.m2493j();
        }
    }

    /* renamed from: com.wakdev.nfctools.TaskEventActivity.b */
    public static class C0548b extends DialogFragment implements OnDateSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            TaskEventActivity taskEventActivity = (TaskEventActivity) getActivity();
            return new DatePickerDialog(getActivity(), this, taskEventActivity.f1578n, taskEventActivity.f1579o, taskEventActivity.f1580p);
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            TaskEventActivity taskEventActivity = (TaskEventActivity) getActivity();
            taskEventActivity.f1578n = i;
            taskEventActivity.f1579o = i2;
            taskEventActivity.f1580p = i3;
            taskEventActivity.m2493j();
        }
    }

    /* renamed from: com.wakdev.nfctools.TaskEventActivity.c */
    public static class C0549c extends DialogFragment implements OnTimeSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            TaskEventActivity taskEventActivity = (TaskEventActivity) getActivity();
            return new TimePickerDialog(getActivity(), this, taskEventActivity.f1586v, taskEventActivity.f1587w, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            TaskEventActivity taskEventActivity = (TaskEventActivity) getActivity();
            taskEventActivity.f1586v = i;
            taskEventActivity.f1587w = i2;
            taskEventActivity.m2493j();
        }
    }

    /* renamed from: com.wakdev.nfctools.TaskEventActivity.d */
    public static class C0550d extends DialogFragment implements OnTimeSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            TaskEventActivity taskEventActivity = (TaskEventActivity) getActivity();
            return new TimePickerDialog(getActivity(), this, taskEventActivity.f1584t, taskEventActivity.f1585u, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            TaskEventActivity taskEventActivity = (TaskEventActivity) getActivity();
            taskEventActivity.f1584t = i;
            taskEventActivity.f1585u = i2;
            taskEventActivity.m2493j();
        }
    }

    static {
        f1569x = C0481m.TASK_MISC_EVENT.cM;
    }

    public TaskEventActivity() {
        this.f1588y = false;
        this.f1589z = null;
    }

    private void m2487k() {
        Calendar instance = Calendar.getInstance();
        this.f1578n = instance.get(1);
        this.f1579o = instance.get(2);
        this.f1580p = instance.get(5);
        this.f1584t = instance.get(11);
        this.f1585u = instance.get(12);
        instance.add(11, 1);
        this.f1581q = instance.get(1);
        this.f1582r = instance.get(2);
        this.f1583s = instance.get(5);
        this.f1586v = instance.get(11);
        this.f1587w = instance.get(12);
    }

    private void m2488l() {
        Intent intent = getIntent();
        this.f1588y = intent.getBooleanExtra("itemUpdate", false);
        this.f1589z = intent.getStringExtra("itemHash");
        if (this.f1588y && this.f1589z != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                Calendar a;
                String str = (String) hashMap.get("field1");
                String str2 = (String) hashMap.get("field2");
                String str3 = (String) hashMap.get("field3");
                String str4 = (String) hashMap.get("field4");
                String str5 = (String) hashMap.get("field5");
                String str6 = (String) hashMap.get("field6");
                String str7 = (String) hashMap.get("field7");
                String str8 = (String) hashMap.get("field8");
                if (str != null) {
                    this.f1570A.setText(str);
                    this.f1570A.setSelection(str.length());
                }
                if (str2 != null) {
                    this.f1571B.setText(str2);
                    this.f1571B.setSelection(str2.length());
                }
                if (str3 != null) {
                    this.f1572C.setText(str3);
                    this.f1572C.setSelection(str3.length());
                }
                if (str4 != null) {
                    a = C0487b.m2052a(str4, "yyyy-MM-dd");
                    this.f1578n = a.get(1);
                    this.f1579o = a.get(2);
                    this.f1580p = a.get(5);
                }
                if (str5 != null) {
                    a = C0487b.m2052a(str5, "yyyy-MM-dd");
                    this.f1581q = a.get(1);
                    this.f1582r = a.get(2);
                    this.f1583s = a.get(5);
                }
                if (str6 != null) {
                    a = C0487b.m2052a(str6, "HH:mm");
                    this.f1584t = a.get(11);
                    this.f1585u = a.get(12);
                }
                if (str7 != null) {
                    a = C0487b.m2052a(str7, "HH:mm");
                    this.f1586v = a.get(11);
                    this.f1587w = a.get(12);
                }
                if (str8 != null) {
                    this.f1577H.setSelection(Integer.parseInt(str8));
                }
            }
        }
    }

    private HashMap<String, String> m2489m() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1570A.getText().toString());
        hashMap.put("field2", this.f1571B.getText().toString());
        hashMap.put("field3", this.f1572C.getText().toString());
        hashMap.put("field4", String.valueOf(this.f1578n) + "-" + String.valueOf(this.f1579o + 1) + "-" + String.valueOf(this.f1580p));
        hashMap.put("field5", String.valueOf(this.f1581q) + "-" + String.valueOf(this.f1582r + 1) + "-" + String.valueOf(this.f1583s));
        hashMap.put("field6", String.valueOf(this.f1584t) + ":" + String.valueOf(this.f1585u));
        hashMap.put("field7", String.valueOf(this.f1586v) + ":" + String.valueOf(this.f1587w));
        hashMap.put("field8", String.valueOf(this.f1577H.getSelectedItemPosition()));
        return hashMap;
    }

    private boolean m2490n() {
        if (this.f1570A.getText().toString().isEmpty()) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.set(this.f1578n, this.f1579o, this.f1580p, this.f1584t, this.f1585u);
        instance2.set(this.f1581q, this.f1582r, this.f1583s, this.f1586v, this.f1587w);
        return !instance2.before(instance);
    }

    private String m2491o() {
        String obj = this.f1570A.getText().toString();
        String obj2 = this.f1571B.getText().toString();
        String obj3 = this.f1572C.getText().toString();
        String valueOf = String.valueOf(this.f1577H.getSelectedItemPosition());
        obj = "[" + obj;
        if (!obj2.isEmpty()) {
            obj = obj3.isEmpty() ? obj + "|" + obj2 + "|#" : obj + "|" + obj2;
        }
        if (!obj3.isEmpty()) {
            obj = obj + "|" + obj3;
        }
        String str = (obj + "]") + "[";
        obj = String.valueOf(this.f1579o + 1);
        obj2 = String.valueOf(this.f1580p);
        obj3 = String.valueOf(this.f1584t);
        String valueOf2 = String.valueOf(this.f1585u);
        String valueOf3 = String.valueOf(this.f1582r + 1);
        String valueOf4 = String.valueOf(this.f1583s);
        String valueOf5 = String.valueOf(this.f1586v);
        String valueOf6 = String.valueOf(this.f1587w);
        if (obj.length() == 1) {
            obj = "0" + obj;
        }
        if (obj2.length() == 1) {
            obj2 = "0" + obj2;
        }
        if (obj3.length() == 1) {
            obj3 = "0" + obj3;
        }
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
        if (valueOf6.length() == 1) {
            valueOf6 = "0" + valueOf6;
        }
        obj = str + String.valueOf(this.f1578n) + "-" + obj + "-" + obj2;
        obj = ((this.f1577H.getSelectedItemPosition() == 0 ? obj + " " + obj3 + ":" + valueOf2 : obj + " 00:00") + "|") + String.valueOf(this.f1581q) + "-" + valueOf3 + "-" + valueOf4;
        return ((((this.f1577H.getSelectedItemPosition() == 0 ? obj + " " + valueOf5 + ":" + valueOf6 : obj + " 00:00") + "]") + "[") + valueOf) + "]";
    }

    private String m2492p() {
        String str = "";
        String obj = this.f1570A.getText().toString();
        String obj2 = this.f1571B.getText().toString();
        String obj3 = this.f1572C.getText().toString();
        String charSequence = this.f1573D.getText().toString();
        String charSequence2 = this.f1574E.getText().toString();
        String charSequence3 = this.f1575F.getText().toString();
        String charSequence4 = this.f1576G.getText().toString();
        String string = getString(C0625h.no);
        if (this.f1577H.getSelectedItemPosition() == 1) {
            string = getString(C0625h.yes);
        }
        str = str + charSequence + " - " + charSequence2 + "\n";
        if (this.f1577H.getSelectedItemPosition() == 0) {
            str = str + charSequence3 + " - " + charSequence4 + "\n";
        }
        str = str + obj + "\n";
        if (!obj2.isEmpty()) {
            str = str + obj2 + "\n";
        }
        if (!obj3.isEmpty()) {
            str = str + obj3 + "\n";
        }
        return str + getString(C0625h.task_event_all_day) + string + "\n";
    }

    public void m2493j() {
        this.f1573D.setText(C0487b.m2050a(this.f1578n, this.f1579o, this.f1580p));
        this.f1574E.setText(C0487b.m2050a(this.f1581q, this.f1582r, this.f1583s));
        this.f1575F.setText(C0487b.m2049a(this.f1584t, this.f1585u));
        this.f1576G.setText(C0487b.m2049a(this.f1586v, this.f1587w));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty()) {
                if ("field1".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f1570A.setText(new StringBuffer(this.f1570A.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f1570A.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f1570A.setText(this.f1570A.getText().toString() + stringExtra);
                        this.f1570A.setSelection(this.f1570A.length());
                    }
                }
                if ("field2".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f1571B.setText(new StringBuffer(this.f1571B.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f1571B.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f1571B.setText(this.f1571B.getText().toString() + stringExtra);
                        this.f1571B.setSelection(this.f1571B.length());
                    }
                }
                if (!"field3".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f1572C.setText(new StringBuffer(this.f1572C.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1572C.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1572C.setText(this.f1572C.getText().toString() + stringExtra);
                this.f1572C.setSelection(this.f1572C.length());
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

    public void onClickPickDateEnd(View view) {
        new C0547a().show(getFragmentManager(), "datePickerEnd");
    }

    public void onClickPickDateStart(View view) {
        new C0548b().show(getFragmentManager(), "datePickerStart");
    }

    public void onClickPickTimeEnd(View view) {
        new C0549c().show(getFragmentManager(), "TimePickerEnd");
    }

    public void onClickPickTimeStart(View view) {
        new C0550d().show(getFragmentManager(), "TimePickerStart");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.task_event);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1573D = (Button) findViewById(C0621d.pickStartDate);
        this.f1574E = (Button) findViewById(C0621d.pickEndDate);
        this.f1575F = (Button) findViewById(C0621d.pickStartTime);
        this.f1576G = (Button) findViewById(C0621d.pickEndTime);
        this.f1570A = (EditText) findViewById(C0621d.myTitle);
        this.f1571B = (EditText) findViewById(C0621d.myLocation);
        this.f1572C = (EditText) findViewById(C0621d.myDescription);
        this.f1577H = (Spinner) findViewById(C0621d.myAllDay);
        this.f1577H.setOnItemSelectedListener(new C05461(this));
        m2487k();
        m2488l();
        m2493j();
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
        intent.putExtra("kSelectionField", this.f1570A.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f1571B.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick3(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field3");
        intent.putExtra("kSelectionField", this.f1572C.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (m2490n()) {
            String o = m2491o();
            String p = m2492p();
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1569x);
            intent.putExtra("itemTask", o);
            intent.putExtra("itemDescription", p);
            intent.putExtra("itemHash", this.f1589z);
            intent.putExtra("itemUpdate", this.f1588y);
            intent.putExtra("itemFields", m2489m());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
