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

public class TaskKeyboardActivity extends C0316b {
    private static final int f1620n;
    private boolean f1621o;
    private String f1622p;
    private Spinner f1623q;

    static {
        f1620n = C0481m.TASK_KEYBOARD.cM;
    }

    public TaskKeyboardActivity() {
        this.f1621o = false;
        this.f1622p = null;
    }

    private void m2508j() {
        Intent intent = getIntent();
        this.f1621o = intent.getBooleanExtra("itemUpdate", false);
        this.f1622p = intent.getStringExtra("itemHash");
        if (this.f1621o && this.f1622p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f1623q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2509k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f1623q.getSelectedItemPosition()));
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
        setContentView(C0622e.task_keyboard);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1623q = (Spinner) findViewById(C0621d.state_spinner);
        m2508j();
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
        String[] stringArray = getResources().getStringArray(C0619b.keycodes_keyboard_names);
        String[] stringArray2 = getResources().getStringArray(C0619b.keycodes_keyboard_values);
        String str2 = "";
        try {
            str2 = stringArray[this.f1623q.getSelectedItemPosition()];
            str = str2;
            keyCodeFromString = KeyEvent.keyCodeFromString(stringArray2[this.f1623q.getSelectedItemPosition()]);
        } catch (Exception e) {
            str = str2;
            keyCodeFromString = -1;
        }
        if (keyCodeFromString != -1) {
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f1620n);
            intent.putExtra("itemTask", String.valueOf(keyCodeFromString));
            intent.putExtra("itemDescription", str);
            intent.putExtra("itemHash", this.f1622p);
            intent.putExtra("itemUpdate", this.f1621o);
            intent.putExtra("itemFields", m2509k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
    }
}
