package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.commons.C0488c;
import com.wakdev.libs.commons.C0500k;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ChooseTaskSettingsActivity extends C0316b implements C0447b {
    private ListView f1241n;
    private C0454c f1242o;
    private C0507a f1243p;
    private ArrayList<C0446a> f1244q;

    private C0446a m2286a(int i, int i2, String str, String str2) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        c0446a.m1804a(str);
        c0446a.m1808b(str2);
        return c0446a;
    }

    private C0446a m2287a(int i, int i2, String str, String str2, int i3) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        if (i3 != 0) {
            c0446a.m1807b(i3);
        }
        c0446a.m1804a(str);
        c0446a.m1808b(str2);
        return c0446a;
    }

    public void m2288a(C0446a c0446a) {
        int h = c0446a.m1819h();
        Intent intent = null;
        this.f1243p = C0507a.m2175a();
        if (this.f1243p.m2194b()) {
            Serializable hashMap = new HashMap();
            hashMap.put("field1", String.valueOf(h));
            String[] stringArray = getResources().getStringArray(C0619b.settings_title_arrays);
            Intent intent2 = new Intent();
            intent2.putExtra("requestMode", 2);
            intent2.putExtra("requestType", C0481m.TASK_CONFIG_OPEN_SETTINGS.cM);
            intent2.putExtra("itemTask", String.valueOf(h));
            intent2.putExtra("itemDescription", stringArray[h]);
            intent2.putExtra("itemHash", C0488c.m2054a());
            intent2.putExtra("itemUpdate", false);
            intent2.putExtra("itemFields", hashMap);
            setResult(-1, intent2);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        } else {
            intent = new Intent(this, ProEditionActivity.class);
        }
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2289b(C0446a c0446a) {
        m2288a(c0446a);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.choose_task);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1243p = C0507a.m2175a();
        this.f1244q = new ArrayList();
        String[] stringArray = getResources().getStringArray(C0619b.settings_title_arrays);
        String[] stringArray2 = getResources().getStringArray(C0619b.settings_description_arrays);
        for (int i = 0; i < stringArray.length; i++) {
            int a = C0500k.m2099a(C0500k.f1138a[i]);
            if (a == -1 || a <= VERSION.SDK_INT) {
                if (this.f1243p.m2194b()) {
                    this.f1244q.add(m2286a(i, C0620c.task_item_settings, stringArray[i], stringArray2[i]));
                } else {
                    this.f1244q.add(m2287a(i, C0620c.task_item_settings, stringArray[i], stringArray2[i], C0620c.item_pro));
                }
            }
        }
        this.f1241n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1242o = new C0454c(getApplicationContext(), this.f1244q);
        this.f1242o.m1842a((C0447b) this);
        this.f1241n.setAdapter(this.f1242o);
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
}
