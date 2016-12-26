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

public class ca extends C0316b {
    private static final int f2208n;
    private boolean f2209o;
    private String f2210p;
    private EditText f2211q;
    private EditText f2212r;

    /* renamed from: com.wakdev.nfctools.ca.1 */
    class C05821 implements InputFilter {
        final /* synthetic */ ca f2207a;

        C05821(ca caVar) {
            this.f2207a = caVar;
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
        f2208n = C0481m.TASK_NETWORK_HTTP_GET_TO_VAR.cM;
    }

    public ca() {
        this.f2209o = false;
        this.f2210p = null;
    }

    private void m2859j() {
        Intent intent = getIntent();
        this.f2209o = intent.getBooleanExtra("itemUpdate", false);
        this.f2210p = intent.getStringExtra("itemHash");
        if (this.f2209o && this.f2210p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2211q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2212r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2860k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2211q.getText().toString());
        hashMap.put("field2", this.f2212r.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            CharSequence stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty()) {
                if ("field1".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f2211q.setText(new StringBuffer(this.f2211q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2211q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2211q.setText(this.f2211q.getText().toString() + stringExtra);
                        this.f2211q.setSelection(this.f2211q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    Object replace = stringExtra.replace("{VAR_", "").replace("}", "");
                    this.f2212r.setText(replace);
                    this.f2212r.setSelection(replace.length());
                    return;
                }
                this.f2212r.setText(stringExtra);
                this.f2212r.setSelection(this.f2212r.length());
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
        setContentView(C0622e.task_httpget2var);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2211q = (EditText) findViewById(C0621d.myRequest);
        this.f2212r = (EditText) findViewById(C0621d.myVar);
        C05821 c05821 = new C05821(this);
        this.f2212r.setFilters(new InputFilter[]{c05821, new AllCaps()});
        m2859j();
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
        intent.putExtra("kSelectionField", this.f2211q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.SELECT_USER_VARIABLE");
            try {
                intent.putExtra("kTargetField", "field2");
                intent.putExtra("kSelectionField", this.f2212r.getSelectionStart());
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_USER_VARIABLE");
            try {
                intent.putExtra("kTargetField", "field2");
                intent.putExtra("kSelectionField", this.f2212r.getSelectionStart());
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
        String obj = this.f2211q.getText().toString();
        String obj2 = this.f2212r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        String str = (getString(C0625h.task_http_get_to_variable_request) + " " + obj + "\n") + getString(C0625h.task_http_get_to_variable_varname) + " " + obj2;
        obj = "[{VAR_" + obj2 + "}]" + obj;
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2208n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f2210p);
        intent.putExtra("itemUpdate", this.f2209o);
        intent.putExtra("itemFields", m2860k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
