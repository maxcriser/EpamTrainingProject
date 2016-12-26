package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
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

public class cj extends C0316b {
    private static final int f2274n;
    private boolean f2275o;
    private String f2276p;
    private Spinner f2277q;
    private Spinner f2278r;

    static {
        f2274n = C0481m.TASK_ROLL_DICE.cM;
    }

    public cj() {
        this.f2275o = false;
        this.f2276p = null;
    }

    private void m2890j() {
        Intent intent = getIntent();
        this.f2275o = intent.getBooleanExtra("itemUpdate", false);
        this.f2276p = intent.getStringExtra("itemHash");
        if (this.f2275o && this.f2276p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2078a(this.f2277q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2278r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2891k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", String.valueOf(this.f2277q.getSelectedItemPosition()));
        hashMap.put("field2", String.valueOf(this.f2278r.getSelectedItemPosition()));
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
        setContentView(C0622e.task_roll_dice);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2277q = (Spinner) findViewById(C0621d.state_spinner1);
        this.f2278r = (Spinner) findViewById(C0621d.state_spinner2);
        this.f2278r.setSelection(2);
        m2890j();
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
        int selectedItemPosition = this.f2277q.getSelectedItemPosition();
        int selectedItemPosition2 = this.f2278r.getSelectedItemPosition();
        if (selectedItemPosition == -1 || selectedItemPosition2 == -1) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        String str = getResources().getStringArray(C0619b.state_dice_roll)[selectedItemPosition] + "d" + getResources().getStringArray(C0619b.state_dice_sides)[selectedItemPosition2];
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2274n);
        intent.putExtra("itemTask", str);
        intent.putExtra("itemDescription", str);
        intent.putExtra("itemHash", this.f2276p);
        intent.putExtra("itemUpdate", this.f2275o);
        intent.putExtra("itemFields", m2891k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
