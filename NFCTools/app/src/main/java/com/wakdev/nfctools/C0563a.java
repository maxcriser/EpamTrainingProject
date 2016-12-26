package com.wakdev.nfctools;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.core.WDCore;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0623f;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* renamed from: com.wakdev.nfctools.a */
public class C0563a extends C0316b implements OnQueryTextListener, C0447b {
    private ListView f1855n;
    private C0454c f1856o;
    private ArrayList<C0446a> f1857p;
    private int f1858q;
    private String f1859r;
    private MenuItem f1860s;
    private SearchView f1861t;

    /* renamed from: com.wakdev.nfctools.a.a */
    private class C0562a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ C0563a f1852a;
        private ProgressDialog f1853b;
        private C0454c f1854c;

        private C0562a(C0563a c0563a) {
            this.f1852a = c0563a;
            this.f1853b = null;
            this.f1854c = null;
        }

        private C0446a m2623a(int i, int i2, String str, String str2) {
            C0446a c0446a = new C0446a();
            c0446a.m1811c(i);
            c0446a.m1802a(i2);
            c0446a.m1804a(str);
            c0446a.m1808b(str2);
            return c0446a;
        }

        protected Void m2624a(Void... voidArr) {
            int i = 0;
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            this.f1852a.f1857p = new ArrayList();
            for (Field field : new Intent().getClass().getDeclaredFields()) {
                try {
                    Object obj = field.get(new Intent());
                    String name = field.getName();
                    String obj2 = obj.toString();
                    if (!(name == null || obj2 == null || !name.startsWith(this.f1852a.f1859r))) {
                        this.f1852a.f1857p.add(m2623a(i, C0620c.task_intent, name, obj2));
                        i++;
                    }
                } catch (Exception e) {
                }
            }
            Context applicationContext2 = applicationContext == null ? this.f1852a.getApplicationContext() : applicationContext;
            if (applicationContext2 != null) {
                this.f1854c = new C0454c(applicationContext2, this.f1852a.f1857p);
            }
            return null;
        }

        protected void m2625a(Void voidR) {
            this.f1852a.m2631a(this.f1854c);
            try {
                this.f1853b.dismiss();
            } catch (Exception e) {
            }
            super.onPostExecute(voidR);
        }

        protected void m2626b(Void... voidArr) {
            super.onProgressUpdate(voidArr);
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2624a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m2625a((Void) obj);
        }

        protected void onPreExecute() {
            this.f1853b = ProgressDialog.show(this.f1852a, null, "Loading ...");
            super.onPreExecute();
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            m2626b((Void[]) objArr);
        }
    }

    public C0563a() {
        this.f1858q = -1;
        this.f1859r = "";
    }

    public void m2630a(C0446a c0446a) {
        if (c0446a != null) {
            Intent intent = new Intent();
            intent.putExtra("intentTypeParam", this.f1858q);
            intent.putExtra("intentResultParam", c0446a.m1822k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }

    public void m2631a(C0454c c0454c) {
        if (c0454c != null) {
            this.f1855n = (ListView) findViewById(C0621d.mylistview_choose);
            this.f1856o = c0454c;
            this.f1856o.m1844a(true);
            this.f1856o.getFilter().filter("");
            this.f1855n.setAdapter(this.f1856o);
            this.f1855n.setTextFilterEnabled(true);
            this.f1856o.m1842a((C0447b) this);
            return;
        }
        C0493f.m2081a(this, "Error when retrieving the list !");
    }

    public void m2632b(C0446a c0446a) {
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.choose_layout);
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
        this.f1858q = intent.getIntExtra("intentTypeParam", -1);
        switch (this.f1858q) {
            case C0627j.View_paddingStart /*1*/:
                this.f1859r = "ACTION_";
                setTitle(getString(C0625h.task_send_intent_select_action));
                break;
            case C0627j.View_paddingEnd /*2*/:
                this.f1859r = "CATEGORY_";
                setTitle(getString(C0625h.task_send_intent_select_category));
                break;
            case C0627j.Toolbar_subtitle /*3*/:
                this.f1859r = "FLAG_";
                setTitle(getString(C0625h.task_send_intent_select_flag));
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                this.f1859r = "EXTRA_";
                setTitle(getString(C0625h.task_send_intent_select_extrakey));
                break;
            default:
                C0493f.m2081a(this, getString(C0625h.error));
                finish();
                return;
        }
        new C0562a().execute(new Void[0]);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(C0623f.search, menu);
            SearchManager searchManager = (SearchManager) getSystemService("search");
            this.f1860s = menu.findItem(C0621d.menu_search);
            this.f1861t = (SearchView) this.f1860s.getActionView();
            this.f1861t.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            this.f1861t.setSubmitButtonEnabled(false);
            this.f1861t.setOnQueryTextListener(this);
            this.f1861t.setBackgroundResource(C0620c.my_search_toolbar);
            this.f1861t.setQueryHint(getString(C0625h.search_hint));
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
        if (this.f1855n != null) {
            if (str == null || str.isEmpty()) {
                this.f1855n.clearTextFilter();
            } else {
                this.f1855n.setFilterText(str);
            }
        }
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }
}
