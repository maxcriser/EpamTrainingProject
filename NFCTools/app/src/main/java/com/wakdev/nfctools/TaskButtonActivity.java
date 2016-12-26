package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
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

public class TaskButtonActivity extends C0316b {
    private static final int f1463n;
    private boolean f1464o;
    private String f1465p;
    private Spinner f1466q;

    static {
        f1463n = C0481m.TASK_BUTTON.cM;
    }

    public TaskButtonActivity() {
        this.f1464o = false;
        this.f1465p = null;
    }

    private void m2427j() {
        Intent intent = getIntent();
        this.f1464o = intent.getBooleanExtra("itemUpdate", false);
        this.f1465p = intent.getStringExtra("itemHash");
        if (this.f1464o && this.f1465p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1466q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2428k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1466q.getSelectedItemPosition()));
        return hashMap;
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
        setContentView(C0622e.task_button);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1466q = (Spinner) findViewById(C0621d.state_spinner);
        m2427j();
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
        String str;
        int keyCodeFromString;
        String[] stringArray = getResources().getStringArray(C0619b.keycodes_button_names);
        String[] stringArray2 = getResources().getStringArray(C0619b.keycodes_button_values);
        String str2 = "";
        try {
            str2 = stringArray[this.f1466q.getSelectedItemPosition()];
            str = str2;
            keyCodeFromString = KeyEvent.keyCodeFromString(stringArray2[this.f1466q.getSelectedItemPosition()]);
        } catch (Exception e) {
            str = str2;
            keyCodeFromString = -1;
        }
        if (keyCodeFromString != -1) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1463n);
            intent.putExtra("itemTask", String.valueOf(keyCodeFromString));
            intent.putExtra("itemDescription", str);
            intent.putExtra("itemHash", this.f1465p);
            intent.putExtra("itemUpdate", this.f1464o);
            intent.putExtra("itemFields", m2428k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
