package com.wakdev.nfctools;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.wakdev.libs.commons.C0496i;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.core.C0508b;
import com.wakdev.libs.core.WDCore;
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
import java.util.Iterator;

public class ChoosePackageActivity extends C0316b implements OnQueryTextListener, C0447b {
    private ListView f1170n;
    private C0454c f1171o;
    private ArrayList<C0446a> f1172p;
    private ArrayList<C0496i> f1173q;
    private MenuItem f1174r;
    private SearchView f1175s;
    private boolean f1176t;

    /* renamed from: com.wakdev.nfctools.ChoosePackageActivity.a */
    private class C0510a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ ChoosePackageActivity f1165a;
        private final String f1166b;
        private final String f1167c;
        private ProgressDialog f1168d;
        private C0454c f1169e;

        private C0510a(ChoosePackageActivity choosePackageActivity) {
            this.f1165a = choosePackageActivity;
            this.f1166b = "CachePackagesListViewAdapterForRegularApps";
            this.f1167c = "CachePackagesListViewAdapterForSystemApps";
            this.f1168d = null;
            this.f1169e = null;
        }

        private C0446a m2229a(int i, Drawable drawable, String str, String str2) {
            C0446a c0446a = new C0446a();
            c0446a.m1811c(i);
            if (drawable != null) {
                c0446a.m1803a(drawable);
            }
            c0446a.m1804a(str);
            c0446a.m1808b(str2);
            return c0446a;
        }

        private C0446a m2230a(Context context) {
            C0446a c0446a = new C0446a();
            c0446a.m1811c(-1);
            c0446a.m1802a(C0620c.wrench_icon);
            c0446a.m1807b(C0620c.item_next);
            c0446a.m1804a(context.getString(C0625h.system_apps));
            c0446a.m1808b(context.getString(C0625h.system_apps_description));
            return c0446a;
        }

        protected Void m2231a(Void... voidArr) {
            C0508b a = C0508b.m2226a();
            if (this.f1165a.f1176t) {
                this.f1169e = a.m2227a("CachePackagesListViewAdapterForSystemApps");
            } else {
                this.f1169e = a.m2227a("CachePackagesListViewAdapterForRegularApps");
            }
            if (this.f1169e == null) {
                Context applicationContext = WDCore.m2174a().getApplicationContext();
                if (this.f1165a.f1176t) {
                    this.f1165a.f1173q = C0499j.m2093a(true);
                } else {
                    this.f1165a.f1173q = C0499j.m2094a(false, true);
                }
                this.f1165a.f1172p = new ArrayList();
                Iterator it = this.f1165a.f1173q.iterator();
                while (it.hasNext()) {
                    C0496i c0496i = (C0496i) it.next();
                    this.f1165a.f1172p.add(m2229a(0, c0496i.f1137e, c0496i.f1133a, c0496i.f1134b));
                }
                Context applicationContext2 = applicationContext == null ? this.f1165a.getApplicationContext() : applicationContext;
                if (applicationContext2 != null) {
                    if (!this.f1165a.f1176t) {
                        this.f1165a.f1172p.add(m2230a(applicationContext2));
                    }
                    this.f1169e = new C0454c(applicationContext2, this.f1165a.f1172p);
                    if (this.f1165a.f1176t) {
                        a.m2228a("CachePackagesListViewAdapterForSystemApps", this.f1169e);
                    } else {
                        a.m2228a("CachePackagesListViewAdapterForRegularApps", this.f1169e);
                    }
                }
            }
            return null;
        }

        protected void m2232a(Void voidR) {
            this.f1165a.m2240a(this.f1169e);
            try {
                this.f1168d.dismiss();
            } catch (Exception e) {
            }
            super.onPostExecute(voidR);
        }

        protected void m2233b(Void... voidArr) {
            super.onProgressUpdate(voidArr);
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2231a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m2232a((Void) obj);
        }

        protected void onPreExecute() {
            this.f1168d = ProgressDialog.show(this.f1165a, null, "Loading ...");
            super.onPreExecute();
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            m2233b((Void[]) objArr);
        }
    }

    public ChoosePackageActivity() {
        this.f1176t = false;
    }

    public void m2239a(C0446a c0446a) {
        if (c0446a == null) {
            return;
        }
        if (c0446a.m1819h() != -1) {
            String k = c0446a.m1822k();
            Intent intent = new Intent();
            intent.putExtra("packageName", k);
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        Intent intent2 = new Intent(this, ChoosePackageActivity.class);
        intent2.putExtra("loadSystemApp", true);
        startActivityForResult(intent2, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void m2240a(C0454c c0454c) {
        if (c0454c != null) {
            this.f1170n = (ListView) findViewById(C0621d.mylistview_choose);
            this.f1171o = c0454c;
            this.f1171o.m1844a(true);
            this.f1171o.getFilter().filter("");
            this.f1170n.setAdapter(this.f1171o);
            this.f1170n.setTextFilterEnabled(true);
            this.f1171o.m1842a((C0447b) this);
            return;
        }
        C0493f.m2081a(this, "Error when retrieving the list of applications !");
    }

    public void m2241b(C0446a c0446a) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.getStringExtra("packageName") != null) {
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
        setContentView(C0622e.choose_layout);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        Intent intent = getIntent();
        if (intent != null) {
            this.f1176t = intent.getBooleanExtra("loadSystemApp", false);
            if (this.f1176t) {
                setTitle(C0625h.system_apps);
            }
        }
        new C0510a().execute(new Void[0]);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(C0623f.search, menu);
            SearchManager searchManager = (SearchManager) getSystemService("search");
            this.f1174r = menu.findItem(C0621d.menu_search);
            this.f1175s = (SearchView) this.f1174r.getActionView();
            this.f1175s.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            this.f1175s.setSubmitButtonEnabled(false);
            this.f1175s.setOnQueryTextListener(this);
            this.f1175s.setBackgroundResource(C0620c.my_search_toolbar);
            this.f1175s.setQueryHint(getString(C0625h.search_hint));
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
        if (this.f1170n != null) {
            if (str == null || str.isEmpty()) {
                this.f1170n.clearTextFilter();
            } else {
                this.f1170n.setFilterText(str);
            }
        }
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }
}
