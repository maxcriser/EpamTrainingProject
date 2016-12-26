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

public class cd extends C0316b {
    private static final int f2230x;
    private EditText f2231A;
    private EditText f2232B;
    private EditText f2233C;
    private Button f2234D;
    private Button f2235E;
    private Button f2236F;
    private Button f2237G;
    private Spinner f2238H;
    public int f2239n;
    public int f2240o;
    public int f2241p;
    public int f2242q;
    public int f2243r;
    public int f2244s;
    public int f2245t;
    public int f2246u;
    public int f2247v;
    public int f2248w;
    private boolean f2249y;
    private String f2250z;

    /* renamed from: com.wakdev.nfctools.cd.1 */
    class C05851 implements OnItemSelectedListener {
        final /* synthetic */ cd f2229a;

        C05851(cd cdVar) {
            this.f2229a = cdVar;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i == 1) {
                this.f2229a.f2236F.setEnabled(false);
                this.f2229a.f2237G.setEnabled(false);
                return;
            }
            this.f2229a.f2236F.setEnabled(true);
            this.f2229a.f2237G.setEnabled(true);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.wakdev.nfctools.cd.a */
    public static class C0586a extends DialogFragment implements OnDateSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            cd cdVar = (cd) getActivity();
            return new DatePickerDialog(getActivity(), this, cdVar.f2242q, cdVar.f2243r, cdVar.f2244s);
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            cd cdVar = (cd) getActivity();
            cdVar.f2242q = i;
            cdVar.f2243r = i2;
            cdVar.f2244s = i3;
            cdVar.m2877j();
        }
    }

    /* renamed from: com.wakdev.nfctools.cd.b */
    public static class C0587b extends DialogFragment implements OnDateSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            cd cdVar = (cd) getActivity();
            return new DatePickerDialog(getActivity(), this, cdVar.f2239n, cdVar.f2240o, cdVar.f2241p);
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            cd cdVar = (cd) getActivity();
            cdVar.f2239n = i;
            cdVar.f2240o = i2;
            cdVar.f2241p = i3;
            cdVar.m2877j();
        }
    }

    /* renamed from: com.wakdev.nfctools.cd.c */
    public static class C0588c extends DialogFragment implements OnTimeSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            cd cdVar = (cd) getActivity();
            return new TimePickerDialog(getActivity(), this, cdVar.f2247v, cdVar.f2248w, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            cd cdVar = (cd) getActivity();
            cdVar.f2247v = i;
            cdVar.f2248w = i2;
            cdVar.m2877j();
        }
    }

    /* renamed from: com.wakdev.nfctools.cd.d */
    public static class C0589d extends DialogFragment implements OnTimeSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            cd cdVar = (cd) getActivity();
            return new TimePickerDialog(getActivity(), this, cdVar.f2245t, cdVar.f2246u, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            cd cdVar = (cd) getActivity();
            cdVar.f2245t = i;
            cdVar.f2246u = i2;
            cdVar.m2877j();
        }
    }

    static {
        f2230x = C0481m.TASK_MISC_INSERT_EVENT.cM;
    }

    public cd() {
        this.f2249y = false;
        this.f2250z = null;
    }

    private void m2871k() {
        Calendar instance = Calendar.getInstance();
        this.f2239n = instance.get(1);
        this.f2240o = instance.get(2);
        this.f2241p = instance.get(5);
        this.f2245t = instance.get(11);
        this.f2246u = instance.get(12);
        instance.add(11, 1);
        this.f2242q = instance.get(1);
        this.f2243r = instance.get(2);
        this.f2244s = instance.get(5);
        this.f2247v = instance.get(11);
        this.f2248w = instance.get(12);
    }

    private void m2872l() {
        Intent intent = getIntent();
        this.f2249y = intent.getBooleanExtra("itemUpdate", false);
        this.f2250z = intent.getStringExtra("itemHash");
        if (this.f2249y && this.f2250z != null) {
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
                    this.f2231A.setText(str);
                    this.f2231A.setSelection(str.length());
                }
                if (str2 != null) {
                    this.f2232B.setText(str2);
                    this.f2232B.setSelection(str2.length());
                }
                if (str3 != null) {
                    this.f2233C.setText(str3);
                    this.f2233C.setSelection(str3.length());
                }
                if (str4 != null) {
                    a = C0487b.m2052a(str4, "yyyy-MM-dd");
                    this.f2239n = a.get(1);
                    this.f2240o = a.get(2);
                    this.f2241p = a.get(5);
                }
                if (str5 != null) {
                    a = C0487b.m2052a(str5, "yyyy-MM-dd");
                    this.f2242q = a.get(1);
                    this.f2243r = a.get(2);
                    this.f2244s = a.get(5);
                }
                if (str6 != null) {
                    a = C0487b.m2052a(str6, "HH:mm");
                    this.f2245t = a.get(11);
                    this.f2246u = a.get(12);
                }
                if (str7 != null) {
                    a = C0487b.m2052a(str7, "HH:mm");
                    this.f2247v = a.get(11);
                    this.f2248w = a.get(12);
                }
                if (str8 != null) {
                    this.f2238H.setSelection(Integer.parseInt(str8));
                }
            }
        }
    }

    private HashMap<String, String> m2873m() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2231A.getText().toString());
        hashMap.put("field2", this.f2232B.getText().toString());
        hashMap.put("field3", this.f2233C.getText().toString());
        hashMap.put("field4", String.valueOf(this.f2239n) + "-" + String.valueOf(this.f2240o + 1) + "-" + String.valueOf(this.f2241p));
        hashMap.put("field5", String.valueOf(this.f2242q) + "-" + String.valueOf(this.f2243r + 1) + "-" + String.valueOf(this.f2244s));
        hashMap.put("field6", String.valueOf(this.f2245t) + ":" + String.valueOf(this.f2246u));
        hashMap.put("field7", String.valueOf(this.f2247v) + ":" + String.valueOf(this.f2248w));
        hashMap.put("field8", String.valueOf(this.f2238H.getSelectedItemPosition()));
        return hashMap;
    }

    private boolean m2874n() {
        if (this.f2231A.getText().toString().isEmpty()) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.set(this.f2239n, this.f2240o, this.f2241p, this.f2245t, this.f2246u);
        instance2.set(this.f2242q, this.f2243r, this.f2244s, this.f2247v, this.f2248w);
        return !instance2.before(instance);
    }

    private String m2875o() {
        String obj = this.f2231A.getText().toString();
        String obj2 = this.f2232B.getText().toString();
        String obj3 = this.f2233C.getText().toString();
        String valueOf = String.valueOf(this.f2238H.getSelectedItemPosition());
        obj = "[" + obj;
        if (!obj2.isEmpty()) {
            obj = obj3.isEmpty() ? obj + "|" + obj2 + "|#" : obj + "|" + obj2;
        }
        if (!obj3.isEmpty()) {
            obj = obj + "|" + obj3;
        }
        String str = (obj + "]") + "[";
        obj = String.valueOf(this.f2240o + 1);
        obj2 = String.valueOf(this.f2241p);
        obj3 = String.valueOf(this.f2245t);
        String valueOf2 = String.valueOf(this.f2246u);
        String valueOf3 = String.valueOf(this.f2243r + 1);
        String valueOf4 = String.valueOf(this.f2244s);
        String valueOf5 = String.valueOf(this.f2247v);
        String valueOf6 = String.valueOf(this.f2248w);
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
        obj = str + String.valueOf(this.f2239n) + "-" + obj + "-" + obj2;
        obj = ((this.f2238H.getSelectedItemPosition() == 0 ? obj + " " + obj3 + ":" + valueOf2 : obj + " 00:00") + "|") + String.valueOf(this.f2242q) + "-" + valueOf3 + "-" + valueOf4;
        return ((((this.f2238H.getSelectedItemPosition() == 0 ? obj + " " + valueOf5 + ":" + valueOf6 : obj + " 00:00") + "]") + "[") + valueOf) + "]";
    }

    private String m2876p() {
        String str = "";
        String obj = this.f2231A.getText().toString();
        String obj2 = this.f2232B.getText().toString();
        String obj3 = this.f2233C.getText().toString();
        String charSequence = this.f2234D.getText().toString();
        String charSequence2 = this.f2235E.getText().toString();
        String charSequence3 = this.f2236F.getText().toString();
        String charSequence4 = this.f2237G.getText().toString();
        String string = getString(C0625h.no);
        if (this.f2238H.getSelectedItemPosition() == 1) {
            string = getString(C0625h.yes);
        }
        str = str + charSequence + " - " + charSequence2 + "\n";
        if (this.f2238H.getSelectedItemPosition() == 0) {
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

    public void m2877j() {
        this.f2234D.setText(C0487b.m2050a(this.f2239n, this.f2240o, this.f2241p));
        this.f2235E.setText(C0487b.m2050a(this.f2242q, this.f2243r, this.f2244s));
        this.f2236F.setText(C0487b.m2049a(this.f2245t, this.f2246u));
        this.f2237G.setText(C0487b.m2049a(this.f2247v, this.f2248w));
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
                        this.f2231A.setText(new StringBuffer(this.f2231A.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2231A.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2231A.setText(this.f2231A.getText().toString() + stringExtra);
                        this.f2231A.setSelection(this.f2231A.length());
                    }
                }
                if ("field2".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f2232B.setText(new StringBuffer(this.f2232B.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2232B.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2232B.setText(this.f2232B.getText().toString() + stringExtra);
                        this.f2232B.setSelection(this.f2232B.length());
                    }
                }
                if (!"field3".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f2233C.setText(new StringBuffer(this.f2233C.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f2233C.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f2233C.setText(this.f2233C.getText().toString() + stringExtra);
                this.f2233C.setSelection(this.f2233C.length());
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
        new C0586a().show(getFragmentManager(), "datePickerEnd");
    }

    public void onClickPickDateStart(View view) {
        new C0587b().show(getFragmentManager(), "datePickerStart");
    }

    public void onClickPickTimeEnd(View view) {
        new C0588c().show(getFragmentManager(), "TimePickerEnd");
    }

    public void onClickPickTimeStart(View view) {
        new C0589d().show(getFragmentManager(), "TimePickerStart");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.task_insert_event);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2234D = (Button) findViewById(C0621d.pickStartDate);
        this.f2235E = (Button) findViewById(C0621d.pickEndDate);
        this.f2236F = (Button) findViewById(C0621d.pickStartTime);
        this.f2237G = (Button) findViewById(C0621d.pickEndTime);
        this.f2231A = (EditText) findViewById(C0621d.myTitle);
        this.f2232B = (EditText) findViewById(C0621d.myLocation);
        this.f2233C = (EditText) findViewById(C0621d.myDescription);
        this.f2238H = (Spinner) findViewById(C0621d.myAllDay);
        this.f2238H.setOnItemSelectedListener(new C05851(this));
        m2871k();
        m2872l();
        m2877j();
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
        intent.putExtra("kSelectionField", this.f2231A.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f2232B.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick3(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field3");
        intent.putExtra("kSelectionField", this.f2233C.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (m2874n()) {
            String o = m2875o();
            String p = m2876p();
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2230x);
            intent.putExtra("itemTask", o);
            intent.putExtra("itemDescription", p);
            intent.putExtra("itemHash", this.f2250z);
            intent.putExtra("itemUpdate", this.f2249y);
            intent.putExtra("itemFields", m2873m());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
