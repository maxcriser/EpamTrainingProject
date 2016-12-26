package com.wakdev.nfctools;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0581c.C0551a;
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

public class TaskRunToolActivity extends C0316b implements C0447b, C0551a {
    private static final int f1707n;
    private ListView f1708o;
    private C0454c f1709p;
    private ArrayList<C0446a> f1710q;
    private C0581c f1711r;

    static {
        f1707n = C0481m.TASK_RUN_TOOL.cM;
    }

    private C0446a m2551a(int i, int i2, int i3, int i4) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        if (i == 1 && C0499j.m2096a("com.wakdev.infinitelight")) {
            c0446a.m1807b(C0620c.valid_icon);
        }
        if (i == 2 && C0499j.m2096a("com.wakdev.heartmonitor")) {
            c0446a.m1807b(C0620c.valid_icon);
        }
        if (i == 3 && C0499j.m2096a("com.wakdev.infinitecompass")) {
            c0446a.m1807b(C0620c.valid_icon);
        }
        if (i == 4 && C0499j.m2096a("com.wakdev.infiniteweather")) {
            c0446a.m1807b(C0620c.valid_icon);
        }
        c0446a.m1804a(getString(i3));
        c0446a.m1808b(getString(i4));
        return c0446a;
    }

    public void m2552a(int i, HashMap<String, String> hashMap) {
        if (this.f1711r != null) {
            this.f1711r.dismiss();
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("appDialog");
        if (findFragmentByTag != null) {
            beginTransaction.remove(findFragmentByTag);
        }
        if (hashMap.get("dialog_title") == null) {
            hashMap.put("dialog_title", getString(C0625h.task_run_tool));
        }
        if (i == 0) {
            i = C0622e.dialog_app;
        }
        this.f1711r = null;
        this.f1711r = C0581c.m2855a(i, hashMap);
        this.f1711r.m2857a((C0551a) this);
        this.f1711r.show(beginTransaction, "appDialog");
    }

    public void m2553a(C0446a c0446a) {
        m2556b(c0446a);
    }

    public void m2554a(HashMap<String, String> hashMap) {
        try {
            int parseInt = Integer.parseInt((String) hashMap.get("dialog_id"));
            if (parseInt != 0) {
                switch (parseInt) {
                    case C0627j.View_paddingStart /*1*/:
                        C0495h.m2085a("com.wakdev.infinitelight", 1);
                        break;
                    case C0627j.View_paddingEnd /*2*/:
                        C0495h.m2085a("com.wakdev.heartmonitor", 1);
                        break;
                    case C0627j.Toolbar_subtitle /*3*/:
                        C0495h.m2085a("com.wakdev.infinitecompass", 1);
                        break;
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        C0495h.m2085a("com.wakdev.infiniteweather", 1);
                        break;
                }
                this.f1711r.dismiss();
            }
        } catch (Exception e) {
        }
    }

    public void m2555b() {
        this.f1711r.dismiss();
    }

    public void m2556b(C0446a c0446a) {
        HashMap hashMap = new HashMap();
        int i = C0622e.dialog_app;
        switch (c0446a.m1819h()) {
            case C0627j.View_paddingStart /*1*/:
                hashMap.put("dialog_id", String.valueOf(1));
                hashMap.put("dialog_title", getString(C0625h.task_run_tool_1));
                hashMap.put("dialog_description", c0446a.m1822k());
                hashMap.put("dialog_image", String.valueOf(c0446a.m1801a()));
                if (C0499j.m2096a("com.wakdev.infinitelight")) {
                    hashMap.put("dialog_show_button_select", String.valueOf(true));
                } else {
                    hashMap.put("dialog_show_button_download", String.valueOf(true));
                }
                m2552a(i, hashMap);
            case C0627j.View_paddingEnd /*2*/:
                hashMap.put("dialog_id", String.valueOf(2));
                hashMap.put("dialog_title", getString(C0625h.task_run_tool_2));
                hashMap.put("dialog_description", c0446a.m1822k());
                hashMap.put("dialog_image", String.valueOf(c0446a.m1801a()));
                if (C0499j.m2096a("com.wakdev.heartmonitor")) {
                    hashMap.put("dialog_show_button_select", String.valueOf(true));
                } else {
                    hashMap.put("dialog_show_button_download", String.valueOf(true));
                }
                m2552a(i, hashMap);
            case C0627j.Toolbar_subtitle /*3*/:
                hashMap.put("dialog_id", String.valueOf(3));
                hashMap.put("dialog_title", getString(C0625h.task_run_tool_3));
                hashMap.put("dialog_description", c0446a.m1822k());
                hashMap.put("dialog_image", String.valueOf(c0446a.m1801a()));
                if (C0499j.m2096a("com.wakdev.infinitecompass")) {
                    hashMap.put("dialog_show_button_select", String.valueOf(true));
                } else {
                    hashMap.put("dialog_show_button_download", String.valueOf(true));
                }
                m2552a(i, hashMap);
            case C0627j.Toolbar_contentInsetStart /*4*/:
                hashMap.put("dialog_id", String.valueOf(4));
                hashMap.put("dialog_title", getString(C0625h.task_run_tool_4));
                hashMap.put("dialog_description", c0446a.m1822k());
                hashMap.put("dialog_image", String.valueOf(c0446a.m1801a()));
                if (C0499j.m2096a("com.wakdev.infiniteweather")) {
                    hashMap.put("dialog_show_button_select", String.valueOf(true));
                } else {
                    hashMap.put("dialog_show_button_download", String.valueOf(true));
                }
                m2552a(i, hashMap);
            default:
        }
    }

    public void m2557b(HashMap<String, String> hashMap) {
        String str = null;
        int i = 1;
        try {
            String string;
            switch (Integer.parseInt((String) hashMap.get("dialog_id"))) {
                case C0627j.View_paddingStart /*1*/:
                    str = String.valueOf(1);
                    string = getString(C0625h.task_run_tool_1);
                    break;
                case C0627j.View_paddingEnd /*2*/:
                    str = String.valueOf(2);
                    string = getString(C0625h.task_run_tool_2);
                    break;
                case C0627j.Toolbar_subtitle /*3*/:
                    str = String.valueOf(3);
                    string = getString(C0625h.task_run_tool_3);
                    break;
                case C0627j.Toolbar_contentInsetStart /*4*/:
                    str = String.valueOf(4);
                    string = getString(C0625h.task_run_tool_4);
                    break;
                default:
                    string = null;
                    i = 0;
                    break;
            }
            if (i != 0) {
                Intent intent = new Intent();
                intent.putExtra("requestMode", 2);
                intent.putExtra("requestType", f1707n);
                intent.putExtra("itemTask", str);
                intent.putExtra("itemDescription", string);
                Serializable hashMap2 = new HashMap();
                hashMap2.put("field1", str);
                intent.putExtra("itemFields", hashMap2);
                setResult(-1, intent);
                finish();
                overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            }
        } catch (Exception e) {
        }
    }

    public void i_() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.task_run_tool);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1710q = new ArrayList();
        this.f1710q.add(m2551a(0, C0620c.info_icon, C0625h.task_run_tool_0, C0625h.task_run_tool_0_description));
        this.f1710q.add(m2551a(1, C0620c.infinitelight, C0625h.task_run_tool_1, C0625h.task_run_tool_1_description));
        this.f1710q.add(m2551a(3, C0620c.infinitecompass, C0625h.task_run_tool_3, C0625h.task_run_tool_3_description));
        this.f1710q.add(m2551a(4, C0620c.infiniteweather, C0625h.task_run_tool_4, C0625h.task_run_tool_4_description));
        this.f1710q.add(m2551a(2, C0620c.heartmonitor, C0625h.task_run_tool_2, C0625h.task_run_tool_2_description));
        this.f1708o = (ListView) findViewById(C0621d.mylistview_choose_tool);
        this.f1709p = new C0454c(getApplicationContext(), this.f1710q);
        this.f1709p.m1842a((C0447b) this);
        this.f1708o.setAdapter(this.f1709p);
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
