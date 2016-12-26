package com.wakdev.nfctools;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

/* renamed from: com.wakdev.nfctools.y */
public class C0648y extends C0316b {
    private static final int f2447n;
    private boolean f2448o;
    private String f2449p;
    private EditText f2450q;
    private Spinner f2451r;
    private TextView f2452s;

    /* renamed from: com.wakdev.nfctools.y.1 */
    class C06471 implements OnClickListener {
        final /* synthetic */ C0648y f2446a;

        C06471(C0648y c0648y) {
            this.f2446a = c0648y;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    C0499j.m2097b();
                default:
            }
        }
    }

    static {
        f2447n = C0481m.TASK_COND_IS_APP_RUNNING.cM;
    }

    public C0648y() {
        this.f2448o = false;
        this.f2449p = null;
    }

    private void m3084j() {
        Intent intent = getIntent();
        this.f2448o = intent.getBooleanExtra("itemUpdate", false);
        this.f2449p = intent.getStringExtra("itemHash");
        if (this.f2448o && this.f2449p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2450q, (String) hashMap.get("field1"));
                C0490e.m2078a(this.f2451r, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m3085k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2450q.getText().toString());
        hashMap.put("field2", String.valueOf(this.f2451r.getSelectedItemPosition()));
        return hashMap;
    }

    private String m3086l() {
        String obj = this.f2450q.getText().toString();
        return obj + "|" + String.valueOf(this.f2451r.getSelectedItemPosition());
    }

    private String m3087m() {
        String string = getString(C0625h.cond_desc_exclude);
        String str = getString(C0625h.task_cond_running_app_title) + " : " + this.f2450q.getText().toString();
        if (this.f2451r.getSelectedItemPosition() == 1) {
            string = getString(C0625h.cond_desc_include);
        }
        return str + "\n" + string;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            Object stringExtra = intent.getStringExtra("packageName");
            this.f2450q.setText(stringExtra);
            this.f2450q.setSelection(stringExtra.length());
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
        setContentView(C0622e.task_cond_app_running);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2450q = (EditText) findViewById(C0621d.myPackage);
        this.f2451r = (Spinner) findViewById(C0621d.myIncExcSpinner);
        this.f2451r.setSelection(1);
        this.f2452s = (TextView) findViewById(C0621d.myWarning);
        m3084j();
        if (VERSION.SDK_INT < 23) {
            return;
        }
        if (!C0507a.m2175a().m2214m()) {
            this.f2452s.setVisibility(0);
        } else if (!C0499j.m2095a()) {
            OnClickListener c06471 = new C06471(this);
            new Builder(this).setMessage(getString(C0625h.warning_usage_stats_message)).setPositiveButton(getString(C0625h.warning_usage_stats_go), c06471).setNegativeButton(getString(C0625h.warning_usage_stats_cancel), c06471).setIcon(C0620c.info_icon).setTitle(getString(C0625h.warning_usage_stats_title)).show();
        }
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

    public void onSelectPackageButtonClick(View view) {
        startActivityForResult(new Intent(this, ChoosePackageActivity.class), 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        if (this.f2450q.getText().toString().isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f2447n);
        intent.putExtra("itemTask", m3086l());
        intent.putExtra("itemDescription", m3087m());
        intent.putExtra("itemHash", this.f2449p);
        intent.putExtra("itemUpdate", this.f2448o);
        intent.putExtra("itemFields", m3085k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
