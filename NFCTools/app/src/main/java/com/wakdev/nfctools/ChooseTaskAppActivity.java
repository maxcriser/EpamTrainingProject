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

public class ChooseTaskAppActivity extends C0316b implements C0447b {
    private ListView f1199n;
    private C0454c f1200o;
    private C0507a f1201p;
    private ArrayList<C0446a> f1202q;

    /* renamed from: com.wakdev.nfctools.ChooseTaskAppActivity.1 */
    static /* synthetic */ class C05131 {
        static final /* synthetic */ int[] f1198a;

        static {
            f1198a = new int[C0481m.values().length];
            try {
                f1198a[C0481m.TASK_MISC_GO_HOME.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1198a[C0481m.TASK_MISC_OK_GOOGLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void m2265a(C0446a c0446a) {
        C0481m a = C0481m.m2026a(c0446a.m1819h());
        Intent intent = null;
        this.f1201p = C0507a.m2175a();
        Serializable hashMap;
        Intent intent2;
        switch (C05131.f1198a[a.ordinal()]) {
            case C0627j.View_paddingStart /*1*/:
                hashMap = new HashMap();
                hashMap.put("field1", "1");
                intent2 = new Intent();
                intent2.putExtra("requestMode", 2);
                intent2.putExtra("requestType", C0481m.TASK_MISC_GO_HOME.cM);
                intent2.putExtra("itemTask", "1");
                intent2.putExtra("itemDescription", getString(C0625h.task_go_home_description));
                intent2.putExtra("itemHash", C0488c.m2054a());
                intent2.putExtra("itemUpdate", false);
                intent2.putExtra("itemFields", hashMap);
                setResult(-1, intent2);
                finish();
                overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                break;
            case C0627j.View_paddingEnd /*2*/:
                if (!this.f1201p.m2194b()) {
                    intent = new Intent(this, ProEditionActivity.class);
                    break;
                }
                hashMap = new HashMap();
                hashMap.put("field1", "1");
                intent2 = new Intent();
                intent2.putExtra("requestMode", 2);
                intent2.putExtra("requestType", C0481m.TASK_MISC_OK_GOOGLE.cM);
                intent2.putExtra("itemTask", "1");
                intent2.putExtra("itemDescription", getString(C0625h.task_ok_google_description));
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

    public void m2266b(C0446a c0446a) {
        m2265a(c0446a);
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
        this.f1201p = C0507a.m2175a();
        boolean b = this.f1201p.m2194b();
        this.f1202q = new ArrayList();
        this.f1202q.add(C0486a.m2047c(C0481m.TASK_LAUNCH_APP));
        this.f1202q.add(C0486a.m2047c(C0481m.TASK_LAUNCH_URL));
        this.f1202q.add(C0486a.m2047c(C0481m.TASK_MISC_SEARCH));
        this.f1202q.add(C0486a.m2047c(C0481m.TASK_MISC_RUN_SHORTCUT));
        if (C0507a.m2176g() != 5) {
            this.f1202q.add(C0486a.m2047c(C0481m.TASK_RUN_TOOL));
        }
        this.f1202q.add(C0486a.m2047c(C0481m.TASK_MISC_UNINSTALL_APP));
        this.f1202q.add(C0486a.m2047c(C0481m.TASK_MISC_KILL_APP));
        this.f1202q.add(C0486a.m2047c(C0481m.TASK_MISC_SHOW_APP_DETAILS));
        this.f1202q.add(C0486a.m2043a(C0481m.TASK_MISC_GO_HOME, 0));
        this.f1202q.add(C0486a.m2043a(C0481m.TASK_MISC_OK_GOOGLE, b ? 0 : C0620c.item_pro));
        this.f1199n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1200o = new C0454c(getApplicationContext(), this.f1202q);
        this.f1200o.m1842a((C0447b) this);
        this.f1199n.setAdapter(this.f1200o);
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
