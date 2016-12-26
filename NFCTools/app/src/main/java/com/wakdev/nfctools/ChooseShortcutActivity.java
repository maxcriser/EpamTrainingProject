package com.wakdev.nfctools;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseShortcutActivity extends C0316b implements OnQueryTextListener, C0447b {
    private ListView f1189n;
    private C0454c f1190o;
    private ArrayList<C0446a> f1191p;
    private ArrayList<ResolveInfo> f1192q;
    private MenuItem f1193r;
    private SearchView f1194s;

    /* renamed from: com.wakdev.nfctools.ChooseShortcutActivity.a */
    private class C0512a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ ChooseShortcutActivity f1186a;
        private ProgressDialog f1187b;
        private C0454c f1188c;

        private C0512a(ChooseShortcutActivity chooseShortcutActivity) {
            this.f1186a = chooseShortcutActivity;
            this.f1187b = null;
            this.f1188c = null;
        }

        private C0446a m2251a(int i, Drawable drawable, String str, String str2) {
            C0446a c0446a = new C0446a();
            c0446a.m1811c(i);
            if (drawable != null) {
                c0446a.m1803a(drawable);
            }
            c0446a.m1804a(str);
            c0446a.m1808b(str2);
            return c0446a;
        }

        protected Void m2252a(Void... voidArr) {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            PackageManager packageManager = this.f1186a.getPackageManager();
            List queryIntentActivities = packageManager.queryIntentActivities(new Intent("android.intent.action.CREATE_SHORTCUT"), 0);
            Collections.sort(queryIntentActivities, new DisplayNameComparator(packageManager));
            this.f1186a.f1191p = new ArrayList();
            this.f1186a.f1192q = new ArrayList();
            for (int i = 0; i < queryIntentActivities.size(); i++) {
                ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivities.get(i);
                this.f1186a.f1191p.add(m2251a(i, resolveInfo.loadIcon(packageManager), resolveInfo.loadLabel(packageManager).toString(), resolveInfo.activityInfo.packageName));
                this.f1186a.f1192q.add(resolveInfo);
            }
            Context applicationContext2 = applicationContext == null ? this.f1186a.getApplicationContext() : applicationContext;
            if (applicationContext2 != null) {
                this.f1188c = new C0454c(applicationContext2, this.f1186a.f1191p);
            }
            return null;
        }

        protected void m2253a(Void voidR) {
            this.f1186a.m2260a(this.f1188c);
            try {
                this.f1187b.dismiss();
            } catch (Exception e) {
            }
            super.onPostExecute(voidR);
        }

        protected void m2254b(Void... voidArr) {
            super.onProgressUpdate(voidArr);
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2252a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m2253a((Void) obj);
        }

        protected void onPreExecute() {
            this.f1187b = ProgressDialog.show(this.f1186a, null, "Loading ...");
            super.onPreExecute();
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            m2254b((Void[]) objArr);
        }
    }

    public void m2259a(C0446a c0446a) {
        if (c0446a != null) {
            try {
                ResolveInfo resolveInfo = (ResolveInfo) this.f1192q.get(c0446a.m1819h());
                Intent intent = new Intent("android.intent.action.CREATE_SHORTCUT");
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                intent.setClassName(activityInfo.packageName, activityInfo.name);
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.select_a_shortcut_error));
            }
        }
    }

    public void m2260a(C0454c c0454c) {
        if (c0454c != null) {
            this.f1189n = (ListView) findViewById(C0621d.mylistview_choose);
            this.f1190o = c0454c;
            this.f1190o.m1844a(true);
            this.f1190o.getFilter().filter("");
            this.f1189n.setAdapter(this.f1190o);
            this.f1189n.setTextFilterEnabled(true);
            this.f1190o.m1842a((C0447b) this);
            return;
        }
        C0493f.m2081a(this, "Error when retrieving the list of shortcut !");
    }

    public void m2261b(C0446a c0446a) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case C0627j.View_paddingStart /*1*/:
                    if (intent != null) {
                        try {
                            Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
                            String stringExtra = intent.getStringExtra("android.intent.extra.shortcut.NAME");
                            String toUri = intent2.toUri(0);
                            Intent intent3 = new Intent();
                            intent3.putExtra("kShortcutName", stringExtra);
                            intent3.putExtra("kShortcutEncodedIntent", toUri);
                            setResult(-1, intent3);
                        } catch (Exception e) {
                            C0493f.m2081a(this, getString(C0625h.select_a_shortcut_error));
                        }
                        finish();
                        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    }
                default:
            }
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
        new C0512a().execute(new Void[0]);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(C0623f.search, menu);
            SearchManager searchManager = (SearchManager) getSystemService("search");
            this.f1193r = menu.findItem(C0621d.menu_search);
            this.f1194s = (SearchView) this.f1193r.getActionView();
            this.f1194s.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            this.f1194s.setSubmitButtonEnabled(false);
            this.f1194s.setOnQueryTextListener(this);
            this.f1194s.setBackgroundResource(C0620c.my_search_toolbar);
            this.f1194s.setQueryHint(getString(C0625h.search_hint));
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
        if (this.f1189n != null) {
            if (str == null || str.isEmpty()) {
                this.f1189n.clearTextFilter();
            } else {
                this.f1189n.setFilterText(str);
            }
        }
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }
}
