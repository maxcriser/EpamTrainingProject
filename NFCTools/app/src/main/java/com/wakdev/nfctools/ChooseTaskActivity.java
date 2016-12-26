package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0623f;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.util.ArrayList;

public class ChooseTaskActivity extends C0316b implements C0447b {
    private ListView f1195n;
    private C0454c f1196o;
    private ArrayList<C0446a> f1197p;

    private C0446a m2262a(int i, int i2, int i3, int i4) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        c0446a.m1807b(C0620c.item_next);
        c0446a.m1804a(getString(i3));
        c0446a.m1808b(getString(i4));
        return c0446a;
    }

    public void m2263a(C0446a c0446a) {
        Intent intent = null;
        switch (c0446a.m1819h()) {
            case -9:
                intent = new Intent(this, ChooseTaskPhoneActivity.class);
                break;
            case -8:
                intent = new Intent(this, ChooseTaskAppActivity.class);
                break;
            case -7:
                intent = new Intent(this, ChooseTaskCondActivity.class);
                break;
            case -6:
                intent = new Intent(this, ChooseTaskRootActivity.class);
                break;
            case -5:
                intent = new Intent(this, ChooseTaskVariousActivity.class);
                break;
            case -4:
                intent = new Intent(this, ChooseTaskConfigActivity.class);
                break;
            case -3:
                intent = new Intent(this, ChooseTaskScreenActivity.class);
                break;
            case ListPopupWindow.WRAP_CONTENT /*-2*/:
                intent = new Intent(this, ChooseTaskMediaActivity.class);
                break;
            case ListPopupWindow.MATCH_PARENT /*-1*/:
                intent = new Intent(this, ChooseTaskConnectivityActivity.class);
                break;
        }
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2264b(C0446a c0446a) {
        m2263a(c0446a);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.choose_task);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1197p = new ArrayList();
        this.f1197p.add(m2262a(-1, C0620c.task_list_connectivity, C0625h.task_category_connectivity, C0625h.task_category_connectivity_description));
        this.f1197p.add(m2262a(-2, C0620c.task_list_media, C0625h.task_category_sound_media, C0625h.task_category_sound_media_description));
        this.f1197p.add(m2262a(-3, C0620c.task_list_screen, C0625h.task_category_screen, C0625h.task_category_screen_description));
        this.f1197p.add(m2262a(-4, C0620c.task_list_config, C0625h.task_category_config, C0625h.task_category_config_description));
        this.f1197p.add(m2262a(-8, C0620c.task_list_app, C0625h.task_category_app, C0625h.task_category_app_description));
        this.f1197p.add(m2262a(-9, C0620c.task_list_phone, C0625h.task_category_phone, C0625h.task_category_phone_description));
        this.f1197p.add(m2262a(-5, C0620c.task_list_various, C0625h.task_category_various, C0625h.task_category_various_description));
        this.f1197p.add(m2262a(-6, C0620c.task_list_root, C0625h.task_category_root, C0625h.task_category_root_description));
        this.f1197p.add(m2262a(-7, C0620c.task_list_cond, C0625h.task_cond_blocks, C0625h.task_cond_blocks_description));
        this.f1195n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1196o = new C0454c(getApplicationContext(), this.f1197p);
        this.f1196o.m1842a((C0447b) this);
        this.f1195n.setAdapter(this.f1196o);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            if (!C0507a.m2177n()) {
                getMenuInflater().inflate(C0623f.search_tasks_menu, menu);
            }
        } catch (Exception e) {
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == C0621d.action_search) {
            startActivityForResult(new Intent(this, SearchTasksActivity.class), 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            return true;
        } else if (itemId != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            onBackPressed();
            return true;
        }
    }
}
