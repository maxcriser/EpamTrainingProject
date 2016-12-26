package com.wakdev.nfctools;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.core.WDCore;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0623f;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.util.ArrayList;

public class ChooseVarsActivity extends C0316b implements OnQueryTextListener, C0447b {
    private ListView f1250n;
    private C0454c f1251o;
    private ArrayList<C0446a> f1252p;
    private String f1253q;
    private int f1254r;
    private MenuItem f1255s;
    private SearchView f1256t;

    private C0446a m2292a(int i, int i2, String str, String str2) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        c0446a.m1804a(str);
        c0446a.m1808b(str2);
        return c0446a;
    }

    public void m2293a(C0446a c0446a) {
        if (c0446a != null) {
            Intent intent = new Intent();
            intent.putExtra("kTargetField", this.f1253q);
            intent.putExtra("kSelectionField", this.f1254r);
            intent.putExtra("kResultValue", c0446a.m1822k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }

    public void m2294b(C0446a c0446a) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            String stringExtra = intent.getStringExtra("kResultValue");
            if (stringExtra == null || stringExtra.isEmpty()) {
                C0493f.m2081a(this, getString(C0625h.error));
                return;
            }
            Intent intent2 = new Intent();
            intent2.putExtra("kTargetField", this.f1253q);
            intent2.putExtra("kSelectionField", this.f1254r);
            intent2.putExtra("kResultValue", stringExtra);
            setResult(-1, intent2);
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
        setContentView(C0622e.choose_vars);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        Intent intent = getIntent();
        if (intent == null) {
            C0493f.m2081a(this, getString(C0625h.error));
            finish();
            return;
        }
        this.f1253q = intent.getStringExtra("kTargetField");
        this.f1254r = intent.getIntExtra("kSelectionField", -1);
        this.f1252p = new ArrayList();
        String[] stringArray = getResources().getStringArray(C0619b.vars_list_headline_arrays);
        String[] stringArray2 = getResources().getStringArray(C0619b.vars_list_baseline_arrays);
        int i = 0;
        while (i < stringArray.length) {
            if ((!"com.wakdev.droidautomation.free".equals(WDCore.m2174a().getPackageName()) && !"com.wakdev.droidautomation.pro".equals(WDCore.m2174a().getPackageName())) || !"{TAGID}".equals(stringArray2[i])) {
                this.f1252p.add(m2292a(i + 1, C0620c.vars_icon, stringArray[i], stringArray2[i]));
            }
            i++;
        }
        this.f1250n = (ListView) findViewById(C0621d.mylistview_choose);
        this.f1251o = new C0454c(getApplicationContext(), this.f1252p);
        this.f1251o.m1844a(true);
        this.f1251o.getFilter().filter("");
        this.f1250n.setAdapter(this.f1251o);
        this.f1250n.setTextFilterEnabled(true);
        this.f1251o.m1842a((C0447b) this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(C0623f.search, menu);
            SearchManager searchManager = (SearchManager) getSystemService("search");
            this.f1255s = menu.findItem(C0621d.menu_search);
            this.f1256t = (SearchView) this.f1255s.getActionView();
            this.f1256t.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            this.f1256t.setSubmitButtonEnabled(false);
            this.f1256t.setOnQueryTextListener(this);
            this.f1256t.setBackgroundResource(C0620c.my_search_toolbar);
            this.f1256t.setQueryHint(getString(C0625h.search_hint));
        } catch (Exception e) {
        }
        return true;
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

    public boolean onQueryTextChange(String str) {
        if (this.f1250n != null) {
            if (str == null || str.isEmpty()) {
                this.f1250n.clearTextFilter();
            } else {
                this.f1250n.setFilterText(str);
            }
        }
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public void onUserVariableButton(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.SELECT_USER_VARIABLE");
            try {
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_USER_VARIABLE");
            try {
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e2) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
            }
        } else {
            C0493f.m2081a(this, getString(C0625h.need_nfctasks));
        }
    }
}
