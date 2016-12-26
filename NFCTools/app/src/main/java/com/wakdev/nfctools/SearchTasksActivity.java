package com.wakdev.nfctools;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.commons.C0486a;
import com.wakdev.libs.commons.C0488c;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.core.WDCore;
import com.wakdev.libs.p015a.C0481m;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SearchTasksActivity extends C0316b implements OnQueryTextListener, C0447b {
    private ListView f1383n;
    private C0454c f1384o;
    private ArrayList<C0446a> f1385p;
    private MenuItem f1386q;
    private SearchView f1387r;

    /* renamed from: com.wakdev.nfctools.SearchTasksActivity.1 */
    static /* synthetic */ class C05351 {
        static final /* synthetic */ int[] f1382a;

        static {
            f1382a = new int[C0481m.values().length];
            try {
                f1382a[C0481m.TASK_COND_END.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1382a[C0481m.TASK_COND_ELSE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1382a[C0481m.TASK_COND_IS_ZEN_MODE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1382a[C0481m.TASK_SOUND_STOP_MEDIA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1382a[C0481m.TASK_TIMER_SET.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1382a[C0481m.TASK_CONFIG_INPUT_METHOD.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1382a[C0481m.TASK_REBOOT_DEVICE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f1382a[C0481m.TASK_SHUTDOWN_DEVICE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f1382a[C0481m.TASK_LOCKSCREEN.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f1382a[C0481m.TASK_ZEN_MODE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f1382a[C0481m.TASK_CONFIG_SVOICE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f1382a[C0481m.TASK_CONFIG_SPLANNER.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f1382a[C0481m.TASK_MISC_GO_HOME.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f1382a[C0481m.TASK_DEV_EXIT.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f1382a[C0481m.TASK_MISC_OK_GOOGLE.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f1382a[C0481m.TASK_END_CALL.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f1382a[C0481m.TASK_ALARM_DISMISS_ALL.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f1382a[C0481m.TASK_SCREEN_START_SCREENSAVER.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
        }
    }

    public void m2390a(C0446a c0446a) {
        if (c0446a != null) {
            C0481m a = C0481m.m2026a(c0446a.m1819h());
            Intent intent = null;
            C0507a a2 = C0507a.m2175a();
            Serializable hashMap;
            Intent intent2;
            switch (C05351.f1382a[a.ordinal()]) {
                case C0627j.View_paddingStart /*1*/:
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_COND_END.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_cond_end_description));
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
                    intent2.putExtra("requestType", C0481m.TASK_COND_ELSE.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_cond_else_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                case C0627j.Toolbar_subtitle /*3*/:
                    if (a2.m2194b()) {
                        if (VERSION.SDK_INT >= 21) {
                            intent = new Intent(this, bs.class);
                            break;
                        }
                    }
                    intent = new Intent(this, ProEditionActivity.class);
                    break;
                    break;
                case C0627j.Toolbar_contentInsetStart /*4*/:
                    if (!a2.m2194b()) {
                        intent = new Intent(this, ProEditionActivity.class);
                        break;
                    }
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_SOUND_STOP_MEDIA.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_stop_sound_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                case C0627j.Toolbar_contentInsetEnd /*5*/:
                    if (VERSION.SDK_INT >= 19) {
                        intent = new Intent(this, TaskTimerSetActivity.class);
                        break;
                    }
                    break;
                case C0627j.Toolbar_contentInsetLeft /*6*/:
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_CONFIG_INPUT_METHOD.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_input_method_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                case C0627j.Toolbar_contentInsetRight /*7*/:
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_REBOOT_DEVICE.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_reboot_device_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                case C0627j.Toolbar_popupTheme /*8*/:
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_SHUTDOWN_DEVICE.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_shutdown_device_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                case C0627j.Toolbar_titleTextAppearance /*9*/:
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_LOCKSCREEN.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_lockscreen_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                    if (VERSION.SDK_INT >= 21) {
                        intent = new Intent(this, TaskZenModeActivity.class);
                        break;
                    }
                    break;
                case C0627j.Toolbar_titleMargins /*11*/:
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
                case C0627j.Toolbar_titleMarginStart /*12*/:
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
                case C0627j.Toolbar_titleMarginEnd /*13*/:
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
                case C0627j.Toolbar_titleMarginTop /*14*/:
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_DEV_EXIT.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_exit_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                case C0627j.Toolbar_titleMarginBottom /*15*/:
                    if (!a2.m2194b()) {
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
                case C0627j.Toolbar_maxButtonHeight /*16*/:
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_END_CALL.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_end_call_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                case C0627j.Toolbar_theme /*17*/:
                    if (VERSION.SDK_INT >= 23) {
                        hashMap = new HashMap();
                        hashMap.put("field1", "1");
                        intent2 = new Intent();
                        intent2.putExtra("requestMode", 2);
                        intent2.putExtra("requestType", C0481m.TASK_ALARM_DISMISS_ALL.cM);
                        intent2.putExtra("itemTask", "1");
                        intent2.putExtra("itemDescription", getString(C0625h.task_dismiss_alarms_description));
                        intent2.putExtra("itemHash", C0488c.m2054a());
                        intent2.putExtra("itemUpdate", false);
                        intent2.putExtra("itemFields", hashMap);
                        setResult(-1, intent2);
                        finish();
                        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                        break;
                    }
                    break;
                case C0627j.Toolbar_collapseIcon /*18*/:
                    hashMap = new HashMap();
                    hashMap.put("field1", "1");
                    intent2 = new Intent();
                    intent2.putExtra("requestMode", 2);
                    intent2.putExtra("requestType", C0481m.TASK_SCREEN_START_SCREENSAVER.cM);
                    intent2.putExtra("itemTask", "1");
                    intent2.putExtra("itemDescription", getString(C0625h.task_screensaver_description));
                    intent2.putExtra("itemHash", C0488c.m2054a());
                    intent2.putExtra("itemUpdate", false);
                    intent2.putExtra("itemFields", hashMap);
                    setResult(-1, intent2);
                    finish();
                    overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                    break;
                default:
                    Class d = C0486a.m2048d(a);
                    if (d != null) {
                        intent = new Intent(this, d);
                        break;
                    }
                    break;
            }
            if (intent != null) {
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            }
        }
    }

    public void m2391b(C0446a c0446a) {
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
        setContentView(C0622e.choose_layout);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        boolean b = C0507a.m2175a().m2194b();
        String packageName = WDCore.m2174a().getPackageName();
        boolean z = false;
        C0446a c0446a = null;
        this.f1385p = C0486a.m2045a();
        Iterator it = this.f1385p.iterator();
        while (it.hasNext()) {
            boolean z2;
            C0446a c0446a2 = (C0446a) it.next();
            int h = c0446a2.m1819h();
            if (h == C0481m.TASK_COND_IS_ZEN_MODE.cM) {
                if (VERSION.SDK_INT < 21) {
                    c0446a2.m1808b(getString(C0625h.err_not_compatible_with_your_android_version));
                    c0446a2.m1807b(b ? C0620c.lock_warning_icon : C0620c.item_pro);
                    c0446a2 = c0446a;
                    z2 = z;
                }
                c0446a2 = c0446a;
                z2 = z;
            } else if (h == C0481m.TASK_TIMER_SET.cM) {
                if (VERSION.SDK_INT < 19) {
                    c0446a2.m1808b(getString(C0625h.err_not_compatible_with_your_android_version));
                    c0446a2.m1807b(b ? C0620c.lock_warning_icon : C0620c.item_pro);
                    c0446a2 = c0446a;
                    z2 = z;
                }
                c0446a2 = c0446a;
                z2 = z;
            } else if (h == C0481m.TASK_ZEN_MODE.cM) {
                if (VERSION.SDK_INT < 21) {
                    c0446a2.m1808b(getString(C0625h.err_not_compatible_with_your_android_version));
                    c0446a2.m1807b(b ? C0620c.lock_warning_icon : C0620c.item_pro);
                    c0446a2 = c0446a;
                    z2 = z;
                }
                c0446a2 = c0446a;
                z2 = z;
            } else if (h == C0481m.TASK_ALARM_DISMISS_ALL.cM) {
                if (VERSION.SDK_INT < 23) {
                    c0446a2.m1808b(getString(C0625h.err_not_compatible_with_your_android_version));
                    c0446a2.m1807b(b ? C0620c.lock_warning_icon : C0620c.item_pro);
                    c0446a2 = c0446a;
                    z2 = z;
                }
                c0446a2 = c0446a;
                z2 = z;
            } else {
                if (h == C0481m.TASK_COND_IS_SCAN_NUMBER.cM && ("com.wakdev.droidautomation.free".equals(packageName) || "com.wakdev.droidautomation.pro".equals(packageName))) {
                    z2 = true;
                }
                c0446a2 = c0446a;
                z2 = z;
            }
            z = z2;
            c0446a = c0446a2;
        }
        if (z) {
            this.f1385p.remove(c0446a);
        }
        this.f1383n = (ListView) findViewById(C0621d.mylistview_choose);
        this.f1384o = new C0454c(getApplicationContext(), this.f1385p);
        this.f1384o.m1844a(true);
        this.f1384o.getFilter().filter("");
        this.f1383n.setAdapter(this.f1384o);
        this.f1383n.setTextFilterEnabled(true);
        this.f1384o.m1842a((C0447b) this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(C0623f.search, menu);
            SearchManager searchManager = (SearchManager) getSystemService("search");
            this.f1386q = menu.findItem(C0621d.menu_search);
            this.f1387r = (SearchView) this.f1386q.getActionView();
            this.f1387r.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            this.f1387r.setSubmitButtonEnabled(false);
            this.f1387r.setOnQueryTextListener(this);
            this.f1387r.setBackgroundResource(C0620c.my_search_toolbar);
            this.f1387r.setQueryHint(getString(C0625h.search_hint));
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
        if (this.f1383n != null) {
            if (str == null || str.isEmpty()) {
                this.f1383n.clearTextFilter();
            } else {
                this.f1383n.setFilterText(str);
            }
        }
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }
}
