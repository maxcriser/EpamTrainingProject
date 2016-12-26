package com.wakdev.nfctools;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import com.wakdev.libs.commons.C0487b;
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

public class TaskCondDateActivity extends C0316b {
    private static final int f1471t;
    public int f1472n;
    public int f1473o;
    public int f1474p;
    public int f1475q;
    public int f1476r;
    public int f1477s;
    private boolean f1478u;
    private String f1479v;
    private Button f1480w;
    private Button f1481x;
    private Spinner f1482y;

    /* renamed from: com.wakdev.nfctools.TaskCondDateActivity.a */
    public static class C0540a extends DialogFragment implements OnDateSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            TaskCondDateActivity taskCondDateActivity = (TaskCondDateActivity) getActivity();
            return new DatePickerDialog(getActivity(), this, taskCondDateActivity.f1475q, taskCondDateActivity.f1476r, taskCondDateActivity.f1477s);
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            TaskCondDateActivity taskCondDateActivity = (TaskCondDateActivity) getActivity();
            taskCondDateActivity.f1475q = i;
            taskCondDateActivity.f1476r = i2;
            taskCondDateActivity.f1477s = i3;
            taskCondDateActivity.m2437j();
        }
    }

    /* renamed from: com.wakdev.nfctools.TaskCondDateActivity.b */
    public static class C0541b extends DialogFragment implements OnDateSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            TaskCondDateActivity taskCondDateActivity = (TaskCondDateActivity) getActivity();
            return new DatePickerDialog(getActivity(), this, taskCondDateActivity.f1472n, taskCondDateActivity.f1473o, taskCondDateActivity.f1474p);
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            TaskCondDateActivity taskCondDateActivity = (TaskCondDateActivity) getActivity();
            taskCondDateActivity.f1472n = i;
            taskCondDateActivity.f1473o = i2;
            taskCondDateActivity.f1474p = i3;
            taskCondDateActivity.m2437j();
        }
    }

    static {
        f1471t = C0481m.TASK_COND_IS_DATE.cM;
    }

    public TaskCondDateActivity() {
        this.f1478u = false;
        this.f1479v = null;
    }

    private void m2431k() {
        Calendar instance = Calendar.getInstance();
        this.f1472n = instance.get(1);
        this.f1473o = instance.get(2);
        this.f1474p = instance.get(5);
        instance.add(10, 24);
        this.f1475q = instance.get(1);
        this.f1476r = instance.get(2);
        this.f1477s = instance.get(5);
    }

    private void m2432l() {
        Intent intent = getIntent();
        this.f1478u = intent.getBooleanExtra("itemUpdate", false);
        this.f1479v = intent.getStringExtra("itemHash");
        if (this.f1478u && this.f1479v != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                Calendar a;
                String str = (String) hashMap.get("field1");
                String str2 = (String) hashMap.get("field2");
                if (str != null) {
                    a = C0487b.m2052a(str, "yyyy-MM-dd");
                    this.f1472n = a.get(1);
                    this.f1473o = a.get(2);
                    this.f1474p = a.get(5);
                }
                if (str2 != null) {
                    a = C0487b.m2052a(str2, "yyyy-MM-dd");
                    this.f1475q = a.get(1);
                    this.f1476r = a.get(2);
                    this.f1477s = a.get(5);
                }
                C0490e.m2078a(this.f1482y, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2433m() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1472n) + "-" + String.valueOf(this.f1473o + 1) + "-" + String.valueOf(this.f1474p));
        hashMap.put("field2", String.valueOf(this.f1475q) + "-" + String.valueOf(this.f1476r + 1) + "-" + String.valueOf(this.f1477s));
        hashMap.put("field3", String.valueOf(this.f1482y.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2434n() {
        String valueOf = String.valueOf(this.f1482y.getSelectedItemPosition());
        String valueOf2 = String.valueOf(this.f1473o + 1);
        String valueOf3 = String.valueOf(this.f1474p);
        String valueOf4 = String.valueOf(this.f1476r + 1);
        String valueOf5 = String.valueOf(this.f1477s);
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
        return ((String.valueOf(this.f1472n) + "-" + valueOf2 + "-" + valueOf3) + "|" + String.valueOf(this.f1475q) + "-" + valueOf4 + "-" + valueOf5) + "|" + valueOf;
    }

    private String m2435o() {
        String string = getString(C0625h.cond_desc_exclude);
        String charSequence = this.f1480w.getText().toString();
        String charSequence2 = this.f1481x.getText().toString();
        if (this.f1482y.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return getString(C0625h.task_cond_date_title) + " " + charSequence + " - " + charSequence2 + "\n" + string;
    }

    private boolean m2436p() {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.set(this.f1472n, this.f1473o, this.f1474p);
        instance2.set(this.f1475q, this.f1476r, this.f1477s);
        return !instance2.before(instance);
    }

    public void m2437j() {
        this.f1480w.setText(C0487b.m2050a(this.f1472n, this.f1473o, this.f1474p));
        this.f1481x.setText(C0487b.m2050a(this.f1475q, this.f1476r, this.f1477s));
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
        new C0540a().show(getFragmentManager(), "datePickerEnd");
    }

    public void onClickPickDateStart(View view) {
        new C0541b().show(getFragmentManager(), "datePickerStart");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.task_cond_date);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1480w = (Button) findViewById(C0621d.pickStartDate);
        this.f1481x = (Button) findViewById(C0621d.pickEndDate);
        this.f1482y = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1482y.setSelection(1);
        m2431k();
        m2432l();
        m2437j();
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
        if (m2436p()) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1471t);
            intent.putExtra("itemTask", m2434n());
            intent.putExtra("itemDescription", m2435o());
            intent.putExtra("itemHash", this.f1479v);
            intent.putExtra("itemUpdate", this.f1478u);
            intent.putExtra("itemFields", m2433m());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
