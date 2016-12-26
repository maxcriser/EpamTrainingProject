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

public class TaskDelVarActivity extends C0316b {
    private static final int f1530n;
    private boolean f1531o;
    private String f1532p;
    private EditText f1533q;

    /* renamed from: com.wakdev.nfctools.TaskDelVarActivity.1 */
    class C05441 implements InputFilter {
        final /* synthetic */ TaskDelVarActivity f1529a;

        C05441(TaskDelVarActivity taskDelVarActivity) {
            this.f1529a = taskDelVarActivity;
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
        f1530n = C0481m.TASK_MISC_DELVAR.cM;
    }

    public TaskDelVarActivity() {
        this.f1531o = false;
        this.f1532p = null;
    }

    private void m2466j() {
        Intent intent = getIntent();
        this.f1531o = intent.getBooleanExtra("itemUpdate", false);
        this.f1532p = intent.getStringExtra("itemHash");
        if (this.f1531o && this.f1532p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1533q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2467k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1533q.getText().toString());
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
                    Object replace = stringExtra.replace("{VAR_", "").replace("}", "");
                    this.f1533q.setText(replace);
                    this.f1533q.setSelection(replace.length());
                    return;
                }
                this.f1533q.setText(this.f1533q.getText().toString() + stringExtra);
                this.f1533q.setSelection(this.f1533q.length());
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
        setContentView(C0622e.task_delvar);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1533q = (EditText) findViewById(C0621d.myText);
        C05441 c05441 = new C05441(this);
        this.f1533q.setFilters(new InputFilter[]{c05441, new AllCaps()});
        m2466j();
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
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.SELECT_USER_VARIABLE");
            try {
                intent.putExtra("kTargetField", "field1");
                intent.putExtra("kSelectionField", this.f1533q.getSelectionStart());
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_USER_VARIABLE");
            try {
                intent.putExtra("kTargetField", "field1");
                intent.putExtra("kSelectionField", this.f1533q.getSelectionStart());
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
        String obj = this.f1533q.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_text_is_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1530n);
        intent.putExtra("itemTask", "{VAR_" + obj + "}");
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1532p);
        intent.putExtra("itemUpdate", this.f1531o);
        intent.putExtra("itemFields", m2467k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
