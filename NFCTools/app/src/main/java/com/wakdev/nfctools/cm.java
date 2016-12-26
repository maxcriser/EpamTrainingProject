package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

public class cm extends C0316b {
    private static final int f2287n;
    private String f2288A;
    private Spinner f2289o;
    private EditText f2290p;
    private EditText f2291q;
    private EditText f2292r;
    private EditText f2293s;
    private EditText f2294t;
    private EditText f2295u;
    private EditText f2296v;
    private EditText f2297w;
    private EditText f2298x;
    private EditText f2299y;
    private boolean f2300z;

    static {
        f2287n = C0481m.TASK_MISC_SEND_INTENT.cM;
    }

    public cm() {
        this.f2300z = false;
        this.f2288A = null;
    }

    private void m2898j() {
        Intent intent = getIntent();
        this.f2300z = intent.getBooleanExtra("itemUpdate", false);
        this.f2288A = intent.getStringExtra("itemHash");
        if (this.f2300z && this.f2288A != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2289o, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2290p, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f2291q, (String) hashMap.get("field3"));
                C0490e.m2075a(this.f2292r, (String) hashMap.get("field4"));
                C0490e.m2075a(this.f2293s, (String) hashMap.get("field5"));
                C0490e.m2075a(this.f2294t, (String) hashMap.get("field6"));
                C0490e.m2075a(this.f2295u, (String) hashMap.get("field7"));
                C0490e.m2075a(this.f2296v, (String) hashMap.get("field8"));
                C0490e.m2075a(this.f2298x, (String) hashMap.get("field9"));
                C0490e.m2075a(this.f2297w, (String) hashMap.get("field10"));
                C0490e.m2075a(this.f2299y, (String) hashMap.get("field11"));
            }
        }
    }

    private HashMap<String, String> m2899k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2289o.getSelectedItemPosition()));
        hashMap.put("field2", this.f2290p.getText().toString());
        hashMap.put("field3", this.f2291q.getText().toString());
        hashMap.put("field4", this.f2292r.getText().toString());
        hashMap.put("field5", this.f2293s.getText().toString());
        hashMap.put("field6", this.f2294t.getText().toString());
        hashMap.put("field7", this.f2295u.getText().toString());
        hashMap.put("field8", this.f2296v.getText().toString());
        hashMap.put("field9", this.f2298x.getText().toString());
        hashMap.put("field10", this.f2297w.getText().toString());
        hashMap.put("field11", this.f2299y.getText().toString());
        return hashMap;
    }

    private String m2900l() {
        String[] stringArray = getResources().getStringArray(C0619b.task_send_intent_target_arrays);
        String obj = this.f2290p.getText().toString();
        String obj2 = this.f2291q.getText().toString();
        String obj3 = this.f2292r.getText().toString();
        String obj4 = this.f2293s.getText().toString();
        String obj5 = this.f2294t.getText().toString();
        String obj6 = this.f2295u.getText().toString();
        String obj7 = this.f2296v.getText().toString();
        String obj8 = this.f2298x.getText().toString();
        String obj9 = this.f2297w.getText().toString();
        String obj10 = this.f2299y.getText().toString();
        String str = getString(C0625h.task_send_intent_title_target) + " " + stringArray[this.f2289o.getSelectedItemPosition()] + "\n";
        if (!obj.isEmpty()) {
            str = str + getString(C0625h.task_send_intent_title_action) + " " + obj + "\n";
        }
        if (!obj2.isEmpty()) {
            str = str + getString(C0625h.task_send_intent_title_category) + " " + obj2 + "\n";
        }
        if (!obj3.isEmpty()) {
            str = str + getString(C0625h.task_send_intent_title_flag) + " " + obj3 + "\n";
        }
        if (!obj4.isEmpty()) {
            str = str + getString(C0625h.task_send_intent_title_package) + " " + obj4 + "\n";
        }
        if (!obj5.isEmpty()) {
            str = str + getString(C0625h.task_send_intent_title_class) + " " + obj5 + "\n";
        }
        if (!obj6.isEmpty()) {
            str = str + getString(C0625h.task_send_intent_title_type) + " " + obj6 + "\n";
        }
        if (!obj7.isEmpty()) {
            str = (str + " - " + getString(C0625h.task_send_intent_title_extra1) + " -\n" + getString(C0625h.task_send_intent_title_extrakey) + " " + obj7 + "\n") + getString(C0625h.task_send_intent_title_extravalue) + " " + (obj8.isEmpty() ? "null" : obj8) + "\n";
        }
        if (obj9.isEmpty()) {
            return str;
        }
        return (str + " - " + getString(C0625h.task_send_intent_title_extra2) + " -\n" + getString(C0625h.task_send_intent_title_extrakey) + " " + obj9 + "\n") + getString(C0625h.task_send_intent_title_extravalue) + " " + (obj10.isEmpty() ? "null" : obj10) + "\n";
    }

    private String m2901m() {
        String str = "";
        String str2 = "";
        String valueOf = String.valueOf(this.f2289o.getSelectedItemPosition());
        String obj = this.f2290p.getText().toString();
        String obj2 = this.f2291q.getText().toString();
        String obj3 = this.f2292r.getText().toString();
        String obj4 = this.f2293s.getText().toString();
        String obj5 = this.f2294t.getText().toString();
        String obj6 = this.f2295u.getText().toString();
        String obj7 = this.f2296v.getText().toString();
        String obj8 = this.f2298x.getText().toString();
        String obj9 = this.f2297w.getText().toString();
        String obj10 = this.f2299y.getText().toString();
        if (obj.isEmpty()) {
            obj = "null";
        }
        if (obj2.isEmpty()) {
            obj2 = "null";
        }
        if (obj3.isEmpty()) {
            obj3 = "null";
        }
        if (obj4.isEmpty()) {
            obj4 = "null";
        }
        if (obj5.isEmpty()) {
            obj5 = "null";
        }
        if (obj6.isEmpty()) {
            obj6 = "null";
        }
        if (obj7.isEmpty()) {
            obj7 = "null";
        }
        if (obj8.isEmpty()) {
            obj8 = "null";
        }
        if (obj9.isEmpty()) {
            obj9 = "null";
        }
        if (obj10.isEmpty()) {
            obj10 = "null";
        }
        if (obj9.isEmpty()) {
            obj9 = str2;
            obj10 = str;
        } else {
            obj10 = obj9 + "|" + obj10;
            obj9 = "|";
        }
        if (!obj7.isEmpty()) {
            obj10 = obj7 + "|" + obj8 + obj9 + obj10;
            obj9 = "|";
        }
        if (!obj6.isEmpty()) {
            obj10 = obj6 + obj9 + obj10;
            obj9 = "|";
        }
        if (!obj5.isEmpty()) {
            obj10 = obj5 + obj9 + obj10;
            obj9 = "|";
        }
        if (!obj4.isEmpty()) {
            obj10 = obj4 + obj9 + obj10;
            obj9 = "|";
        }
        if (!obj3.isEmpty()) {
            obj10 = obj3 + obj9 + obj10;
            obj9 = "|";
        }
        if (!obj2.isEmpty()) {
            obj10 = obj2 + obj9 + obj10;
            obj9 = "|";
        }
        return valueOf + "|" + obj + obj9 + obj10;
    }

    public void onActionButtonClick(View view) {
        Intent intent = new Intent(this, C0563a.class);
        intent.putExtra("intentTypeParam", 1);
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra;
            if (intent.hasExtra("intentResultParam") && intent.hasExtra("intentTypeParam")) {
                stringExtra = intent.getStringExtra("intentResultParam");
                int intExtra = intent.getIntExtra("intentTypeParam", -1);
                if (!(stringExtra == null || stringExtra.isEmpty() || intExtra == -1)) {
                    switch (intExtra) {
                        case C0627j.View_paddingStart /*1*/:
                            this.f2290p.setText(stringExtra);
                            this.f2290p.setSelection(stringExtra.length());
                            break;
                        case C0627j.View_paddingEnd /*2*/:
                            this.f2291q.setText(stringExtra);
                            this.f2291q.setSelection(stringExtra.length());
                            break;
                        case C0627j.Toolbar_subtitle /*3*/:
                            this.f2292r.setText(stringExtra);
                            this.f2292r.setSelection(stringExtra.length());
                            break;
                        case C0627j.Toolbar_contentInsetStart /*4*/:
                            this.f2296v.setText(stringExtra);
                            this.f2296v.setSelection(stringExtra.length());
                            break;
                        case C0627j.Toolbar_contentInsetEnd /*5*/:
                            this.f2297w.setText(stringExtra);
                            this.f2297w.setSelection(stringExtra.length());
                            break;
                    }
                }
            }
            if (intent.hasExtra("packageName")) {
                stringExtra = intent.getStringExtra("packageName");
                this.f2293s.setText(stringExtra);
                this.f2293s.setSelection(stringExtra.length());
            }
        }
        if (i2 == -1 && i == 2 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra2 = intent.getStringExtra("kResultValue");
            String stringExtra3 = intent.getStringExtra("kTargetField");
            int intExtra2 = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra2 != null && !stringExtra2.isEmpty() && stringExtra3 != null && !stringExtra3.isEmpty()) {
                if ("field9".equals(stringExtra3)) {
                    if (intExtra2 != -1) {
                        this.f2298x.setText(new StringBuffer(this.f2298x.getText().toString()).insert(intExtra2, stringExtra2).toString());
                        this.f2298x.setSelection(stringExtra2.length() + intExtra2);
                    } else {
                        this.f2298x.setText(this.f2298x.getText().toString() + stringExtra2);
                        this.f2298x.setSelection(this.f2298x.length());
                    }
                }
                if (!"field11".equals(stringExtra3)) {
                    return;
                }
                if (intExtra2 != -1) {
                    this.f2299y.setText(new StringBuffer(this.f2299y.getText().toString()).insert(intExtra2, stringExtra2).toString());
                    this.f2299y.setSelection(stringExtra2.length() + intExtra2);
                    return;
                }
                this.f2299y.setText(this.f2299y.getText().toString() + stringExtra2);
                this.f2299y.setSelection(this.f2299y.length());
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

    public void onCategoryButtonClick(View view) {
        Intent intent = new Intent(this, C0563a.class);
        intent.putExtra("intentTypeParam", 2);
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.task_intent);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2289o = (Spinner) findViewById(C0621d.myTarget);
        this.f2290p = (EditText) findViewById(C0621d.myAction);
        this.f2291q = (EditText) findViewById(C0621d.myCategory);
        this.f2292r = (EditText) findViewById(C0621d.myFlag);
        this.f2293s = (EditText) findViewById(C0621d.myPackage);
        this.f2294t = (EditText) findViewById(C0621d.myClass);
        this.f2295u = (EditText) findViewById(C0621d.myType);
        this.f2296v = (EditText) findViewById(C0621d.myExtraKey1);
        this.f2298x = (EditText) findViewById(C0621d.myExtraValue1);
        this.f2297w = (EditText) findViewById(C0621d.myExtraKey2);
        this.f2299y = (EditText) findViewById(C0621d.myExtraValue2);
        m2898j();
    }

    public void onExtraKey1ButtonClick(View view) {
        Intent intent = new Intent(this, C0563a.class);
        intent.putExtra("intentTypeParam", 4);
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onExtraKey2ButtonClick(View view) {
        Intent intent = new Intent(this, C0563a.class);
        intent.putExtra("intentTypeParam", 5);
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onFlagButtonClick(View view) {
        Intent intent = new Intent(this, C0563a.class);
        intent.putExtra("intentTypeParam", 3);
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
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

    public void onPackageButtonClick(View view) {
        startActivityForResult(new Intent(this, ChoosePackageActivity.class), 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick1(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field9");
        intent.putExtra("kSelectionField", this.f2298x.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field11");
        intent.putExtra("kSelectionField", this.f2299y.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (this.f2289o.getSelectedItemPosition() != -1) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2287n);
            intent.putExtra("itemTask", m2901m());
            intent.putExtra("itemDescription", m2900l());
            intent.putExtra("itemHash", this.f2288A);
            intent.putExtra("itemUpdate", this.f2300z);
            intent.putExtra("itemFields", m2899k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
