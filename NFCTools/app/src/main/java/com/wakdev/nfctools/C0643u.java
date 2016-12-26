package com.wakdev.nfctools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

/* renamed from: com.wakdev.nfctools.u */
public class C0643u extends C0316b {
    private static final int f2427n;
    private boolean f2428o;
    private String f2429p;
    private EditText f2430q;
    private Button f2431r;

    static {
        f2427n = C0481m.TASK_SCREEN_CHANGE_WALLPAPER.cM;
    }

    public C0643u() {
        this.f2428o = false;
        this.f2429p = null;
    }

    private void m3072j() {
        Intent intent = getIntent();
        this.f2428o = intent.getBooleanExtra("itemUpdate", false);
        this.f2429p = intent.getStringExtra("itemHash");
        if (this.f2428o && this.f2429p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2430q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m3073k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2430q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            Object stringExtra = intent.getStringExtra("kIntentKeySelectedPath");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                this.f2430q.setText(stringExtra);
                this.f2430q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_change_wallpaper);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2430q = (EditText) findViewById(C0621d.myFilePath);
        this.f2431r = (Button) findViewById(C0621d.mySearchButton);
        m3072j();
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

    public void onSelectFileClick(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.FILE_MANAGER");
            intent.putExtra("kIntentKeySelectionType", 3);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_change_wallpaper_select));
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.FILE_MANAGER");
            intent.putExtra("kIntentKeySelectionType", 3);
            intent.putExtra("kIntentKeyFileManagerTitle", getString(C0625h.task_change_wallpaper_select));
            try {
                startActivityForResult(intent, 1);
            } catch (Exception e2) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
            }
        } else {
            C0493f.m2081a(this, getString(C0625h.need_nfctasks));
        }
    }

    @SuppressLint({"DefaultLocale"})
    public void onValidateButtonClick(View view) {
        String obj = this.f2430q.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2427n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f2429p);
        intent.putExtra("itemUpdate", this.f2428o);
        intent.putExtra("itemFields", m3073k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
