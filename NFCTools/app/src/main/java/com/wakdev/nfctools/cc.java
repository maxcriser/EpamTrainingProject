package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputFilter.AllCaps;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class cc extends C0316b {
    private static final int f2223n;
    private boolean f2224o;
    private String f2225p;
    private EditText f2226q;
    private EditText f2227r;
    private EditText f2228s;

    /* renamed from: com.wakdev.nfctools.cc.1 */
    class C05841 implements InputFilter {
        final /* synthetic */ cc f2222a;

        C05841(cc ccVar) {
            this.f2222a = ccVar;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            while (i < i2) {
                if (!Character.isLetterOrDigit(charSequence.charAt(i))) {
                    return "";
                }
                i++;
            }
            return null;
        }
    }

    static {
        f2223n = C0481m.TASK_MISC_INPUT_FIELD.cM;
    }

    public cc() {
        this.f2224o = false;
        this.f2225p = null;
    }

    private void m2867j() {
        Intent intent = getIntent();
        this.f2224o = intent.getBooleanExtra("itemUpdate", false);
        this.f2225p = intent.getStringExtra("itemHash");
        if (this.f2224o && this.f2225p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2226q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2227r, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f2228s, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2868k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2226q.getText().toString());
        hashMap.put("field2", this.f2227r.getText().toString());
        hashMap.put("field3", this.f2228s.getText().toString());
        return hashMap;
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
                        this.f2226q.setText(new StringBuffer(this.f2226q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2226q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2226q.setText(this.f2226q.getText().toString() + stringExtra);
                        this.f2226q.setSelection(this.f2226q.length());
                    }
                }
                if ("field2".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f2227r.setText(new StringBuffer(this.f2227r.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2227r.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2227r.setText(this.f2227r.getText().toString() + stringExtra);
                        this.f2227r.setSelection(this.f2227r.length());
                    }
                }
                if (!"field3".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    Object replace = stringExtra.replace("{VAR_", "").replace("}", "");
                    this.f2228s.setText(replace);
                    this.f2228s.setSelection(replace.length());
                    return;
                }
                this.f2228s.setText(this.f2228s.getText().toString() + stringExtra);
                this.f2228s.setSelection(this.f2228s.length());
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
        setContentView(C0622e.task_input_field);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2226q = (EditText) findViewById(C0621d.myTitle);
        this.f2227r = (EditText) findViewById(C0621d.myMessage);
        this.f2228s = (EditText) findViewById(C0621d.myVar);
        C05841 c05841 = new C05841(this);
        this.f2228s.setFilters(new InputFilter[]{c05841, new AllCaps()});
        m2867j();
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
        intent.putExtra("kSelectionField", this.f2226q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f2227r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick3(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.SELECT_USER_VARIABLE");
            try {
                intent.putExtra("kTargetField", "field3");
                intent.putExtra("kSelectionField", this.f2228s.getSelectionStart());
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_USER_VARIABLE");
            try {
                intent.putExtra("kTargetField", "field3");
                intent.putExtra("kSelectionField", this.f2228s.getSelectionStart());
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e2) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
            }
        } else {
            C0493f.m2081a(this, getString(C0625h.need_nfctasks));
        }
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f2226q.getText().toString();
        String obj2 = this.f2227r.getText().toString();
        String obj3 = this.f2228s.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty() || obj3.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        obj = obj.replace("|", "");
        obj2 = obj2.replace("|", "");
        String str = ((getString(C0625h.task_input_field_title) + " " + obj + "\n") + getString(C0625h.task_input_field_message) + " " + obj2 + "\n") + getString(C0625h.task_input_field_variable) + " " + obj3;
        obj = obj + "|" + obj2 + "|" + obj3;
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2223n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f2225p);
        intent.putExtra("itemUpdate", this.f2224o);
        intent.putExtra("itemFields", m2868k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
