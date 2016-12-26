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
import java.util.HashMap;

public class au extends C0316b {
    public static final Integer[] f1979n;
    private static final int f1980o;
    private boolean f1981p;
    private String f1982q;
    private EditText f1983r;
    private Spinner f1984s;
    private Spinner f1985t;

    static {
        f1980o = C0481m.TASK_COND_IS_HTTP_STATUS_CODE.cM;
        f1979n = new Integer[]{Integer.valueOf(200), Integer.valueOf(404), Integer.valueOf(400), Integer.valueOf(401), Integer.valueOf(403), Integer.valueOf(301), Integer.valueOf(302), Integer.valueOf(500), Integer.valueOf(502), Integer.valueOf(503), Integer.valueOf(504), Integer.valueOf(202), Integer.valueOf(405), Integer.valueOf(408), Integer.valueOf(409), Integer.valueOf(201), Integer.valueOf(413), Integer.valueOf(410), Integer.valueOf(411), Integer.valueOf(300), Integer.valueOf(406), Integer.valueOf(203), Integer.valueOf(501), Integer.valueOf(304), Integer.valueOf(204), Integer.valueOf(206), Integer.valueOf(402), Integer.valueOf(412), Integer.valueOf(407), Integer.valueOf(414), Integer.valueOf(205), Integer.valueOf(303), Integer.valueOf(415), Integer.valueOf(305), Integer.valueOf(505)};
    }

    public au() {
        this.f1981p = false;
        this.f1982q = null;
    }

    private void m2717j() {
        Intent intent = getIntent();
        this.f1981p = intent.getBooleanExtra("itemUpdate", false);
        this.f1982q = intent.getStringExtra("itemHash");
        if (this.f1981p && this.f1982q != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1983r, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f1984s, (String) hashMap.get("field2"));
                C0490e.m2078a(this.f1985t, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2718k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1983r.getText().toString());
        hashMap.put("field2", String.valueOf(this.f1984s.getSelectedItemPosition()));
        hashMap.put("field3", String.valueOf(this.f1985t.getSelectedItemPosition()));
        return hashMap;
    }

    private String m2719l() {
        String obj = this.f1983r.getText().toString();
        String valueOf = String.valueOf(f1979n[this.f1984s.getSelectedItemPosition()]);
        return obj + "|" + valueOf + "|" + String.valueOf(this.f1985t.getSelectedItemPosition());
    }

    private String m2720m() {
        String string = getString(C0625h.cond_desc_exclude);
        String obj = this.f1983r.getText().toString();
        String[] stringArray = getResources().getStringArray(C0619b.task_cond_is_http_status_code_array);
        if (this.f1985t.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return obj + "\n" + getString(C0625h.task_cond_is_http_status_code_http_code_title) + " " + stringArray[this.f1984s.getSelectedItemPosition()] + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field1".equals(stringExtra2)) {
                if (intExtra != -1) {
                    this.f1983r.setText(new StringBuffer(this.f1983r.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1983r.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1983r.setText(this.f1983r.getText().toString() + stringExtra);
                this.f1983r.setSelection(this.f1983r.length());
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
        setContentView(C0622e.task_cond_is_http_response_code);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1983r = (EditText) findViewById(C0621d.myUrl);
        this.f1984s = (Spinner) findViewById(C0621d.state_spinner);
        this.f1985t = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f1985t.setSelection(1);
        m2717j();
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
        intent.putExtra("kSelectionField", this.f1983r.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (this.f1983r.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1980o);
        intent.putExtra("itemTask", m2719l());
        intent.putExtra("itemDescription", m2720m());
        intent.putExtra("itemHash", this.f1982q);
        intent.putExtra("itemUpdate", this.f1981p);
        intent.putExtra("itemFields", m2718k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
