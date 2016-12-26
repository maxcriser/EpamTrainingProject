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

public class TaskEditVarActivity extends C0316b {
    private static final int f1559n;
    private boolean f1560o;
    private String f1561p;
    private EditText f1562q;
    private EditText f1563r;

    /* renamed from: com.wakdev.nfctools.TaskEditVarActivity.1 */
    class C05451 implements InputFilter {
        final /* synthetic */ TaskEditVarActivity f1558a;

        C05451(TaskEditVarActivity taskEditVarActivity) {
            this.f1558a = taskEditVarActivity;
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
        f1559n = C0481m.TASK_MISC_EDITVAR.cM;
    }

    public TaskEditVarActivity() {
        this.f1560o = false;
        this.f1561p = null;
    }

    private void m2481j() {
        Intent intent = getIntent();
        this.f1560o = intent.getBooleanExtra("itemUpdate", false);
        this.f1561p = intent.getStringExtra("itemHash");
        if (this.f1560o && this.f1561p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1562q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1563r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2482k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1562q.getText().toString());
        hashMap.put("field2", this.f1563r.getText().toString());
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
                        stringExtra = stringExtra.replace("{VAR_", "").replace("}", "");
                        this.f1562q.setText(stringExtra);
                        this.f1562q.setSelection(stringExtra.length());
                    } else {
                        this.f1562q.setText(this.f1562q.getText().toString() + stringExtra);
                        this.f1562q.setSelection(this.f1562q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    this.f1563r.setText(new StringBuffer(this.f1563r.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1563r.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1563r.setText(this.f1563r.getText().toString() + stringExtra);
                this.f1563r.setSelection(this.f1563r.length());
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
        setContentView(C0622e.task_editvar);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1562q = (EditText) findViewById(C0621d.myTitle);
        this.f1563r = (EditText) findViewById(C0621d.myMessage);
        C05451 c05451 = new C05451(this);
        this.f1562q.setFilters(new InputFilter[]{c05451, new AllCaps()});
        m2481j();
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
                intent.putExtra("kSelectionField", this.f1562q.getSelectionStart());
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
                intent.putExtra("kSelectionField", this.f1562q.getSelectionStart());
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e2) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
            }
        } else {
            C0493f.m2081a(this, getString(C0625h.need_nfctasks));
        }
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f1563r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1562q.getText().toString();
        String obj2 = this.f1563r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        String str = obj + "\n" + obj2;
        obj = "[{VAR_" + obj + "}]" + obj2;
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1559n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f1561p);
        intent.putExtra("itemUpdate", this.f1560o);
        intent.putExtra("itemFields", m2482k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
