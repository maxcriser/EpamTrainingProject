package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.commons.C0486a;
import com.wakdev.libs.commons.C0488c;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ChooseTaskSamsungActivity extends C0316b implements C0447b {
    private ListView f1232n;
    private C0454c f1233o;
    private C0507a f1234p;
    private ArrayList<C0446a> f1235q;

    /* renamed from: com.wakdev.nfctools.ChooseTaskSamsungActivity.1 */
    static /* synthetic */ class C05191 {
        static final /* synthetic */ int[] f1231a;

        static {
            f1231a = new int[C0481m.values().length];
            try {
                f1231a[C0481m.TASK_CONFIG_SVOICE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1231a[C0481m.TASK_CONFIG_SPLANNER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void m2282a(C0446a c0446a) {
        C0481m a = C0481m.m2026a(c0446a.m1819h());
        Intent intent = null;
        Serializable hashMap;
        Intent intent2;
        switch (C05191.f1231a[a.ordinal()]) {
            case C0627j.View_paddingStart /*1*/:
                hashMap = new HashMap();
                hashMap.put("field1", "1");
                intent2 = new Intent();
                intent2.putExtra("requestMode", 2);
                intent2.putExtra("requestType", C0481m.TASK_CONFIG_SVOICE.cM);
                intent2.putExtra("itemTask", "1");
                intent2.putExtra("itemDescription", getString(C0625h.task_svoice_description));
                intent2.putExtra("itemHash", C0488c.m2054a());
                intent2.putExtra("itemUpdate", false);
                intent2.putExtra("itemFields", hashMap);
                setResult(-1, intent2);
                finish();
                overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                break;
            case C0627j.View_paddingEnd /*2*/:
                hashMap = new HashMap();
                hashMap.put("field1", "1");
                intent2 = new Intent();
                intent2.putExtra("requestMode", 2);
                intent2.putExtra("requestType", C0481m.TASK_CONFIG_SPLANNER.cM);
                intent2.putExtra("itemTask", "1");
                intent2.putExtra("itemDescription", getString(C0625h.task_splanner_description));
                intent2.putExtra("itemHash", C0488c.m2054a());
                intent2.putExtra("itemUpdate", false);
                intent2.putExtra("itemFields", hashMap);
                setResult(-1, intent2);
                finish();
                overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                break;
            default:
                intent = new Intent(this, C0486a.m2048d(a));
                break;
        }
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2283b(C0446a c0446a) {
        m2282a(c0446a);
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
        this.f1234p = C0507a.m2175a();
        this.f1235q = new ArrayList();
        this.f1235q.add(C0486a.m2047c(C0481m.TASK_CONFIG_DRIVING_MODE));
        this.f1235q.add(C0486a.m2047c(C0481m.TASK_CONFIG_POWER_SAVING_MODE));
        this.f1235q.add(C0486a.m2047c(C0481m.TASK_CONFIG_BLOCKING_MODE));
        this.f1235q.add(C0486a.m2047c(C0481m.TASK_CONFIG_MULTI_WINDOW));
        this.f1235q.add(C0486a.m2047c(C0481m.TASK_CONFIG_TOOLBOX));
        this.f1235q.add(C0486a.m2047c(C0481m.TASK_CONFIG_AIR_VIEW));
        this.f1235q.add(C0486a.m2043a(C0481m.TASK_CONFIG_SVOICE, 0));
        this.f1235q.add(C0486a.m2043a(C0481m.TASK_CONFIG_SPLANNER, 0));
        this.f1232n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1233o = new C0454c(getApplicationContext(), this.f1235q);
        this.f1233o.m1842a((C0447b) this);
        this.f1232n.setAdapter(this.f1233o);
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
