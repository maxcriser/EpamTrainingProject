package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
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
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ChooseTaskCondActivity extends C0316b implements C0447b {
    private ListView f1204n;
    private C0454c f1205o;
    private C0507a f1206p;
    private ArrayList<C0446a> f1207q;

    /* renamed from: com.wakdev.nfctools.ChooseTaskCondActivity.1 */
    static /* synthetic */ class C05141 {
        static final /* synthetic */ int[] f1203a;

        static {
            f1203a = new int[C0481m.values().length];
            try {
                f1203a[C0481m.TASK_COND_END.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1203a[C0481m.TASK_COND_ELSE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1203a[C0481m.TASK_COND_IS_ZEN_MODE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1203a[C0481m.TASK_COND_IS_BATTERY_SAVER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private C0446a m2267a(int i, int i2, int i3, int i4, int i5) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        if (i5 != 0) {
            c0446a.m1807b(i5);
        }
        c0446a.m1804a(getString(i3));
        c0446a.m1808b(getString(i4));
        return c0446a;
    }

    public void m2268a(C0446a c0446a) {
        C0481m a = C0481m.m2026a(c0446a.m1819h());
        Intent intent = null;
        this.f1206p = C0507a.m2175a();
        Serializable hashMap;
        Intent intent2;
        switch (C05141.f1203a[a.ordinal()]) {
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
                if (this.f1206p.m2194b()) {
                    if (VERSION.SDK_INT >= 21) {
                        intent = new Intent(this, bs.class);
                        break;
                    }
                }
                intent = new Intent(this, ProEditionActivity.class);
                break;
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                if (this.f1206p.m2194b()) {
                    if (VERSION.SDK_INT >= 21) {
                        intent = new Intent(this, ab.class);
                        break;
                    }
                }
                intent = new Intent(this, ProEditionActivity.class);
                break;
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

    public void m2269b(C0446a c0446a) {
        m2268a(c0446a);
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
        this.f1206p = C0507a.m2175a();
        boolean b = this.f1206p.m2194b();
        String packageName = WDCore.m2174a().getPackageName();
        this.f1207q = new ArrayList();
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_END, 0));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_ELSE, 0));
        this.f1207q.add(C0486a.m2047c(C0481m.TASK_COND_TIME));
        this.f1207q.add(C0486a.m2047c(C0481m.TASK_COND_DAY));
        this.f1207q.add(C0486a.m2047c(C0481m.TASK_COND_IS_DAYOFMONTH));
        this.f1207q.add(C0486a.m2047c(C0481m.TASK_COND_IS_MONTH));
        this.f1207q.add(C0486a.m2047c(C0481m.TASK_COND_IS_YEAR));
        this.f1207q.add(C0486a.m2047c(C0481m.TASK_COND_IS_DATE));
        if (!("com.wakdev.droidautomation.free".equals(packageName) || "com.wakdev.droidautomation.pro".equals(packageName))) {
            this.f1207q.add(C0486a.m2047c(C0481m.TASK_COND_IS_SCAN_NUMBER));
        }
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_WIFI, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_WIFI_NETWORK, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_HOTSPOT_WIFI, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_BLUETOOTH, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_DEVICE_PAIRED, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_BLUETOOTH_DEVICE_CONNECTED, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_YES_NO_DIALOG, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_CLIPBOARD, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_HTTP_GET, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_INTERNET_AVAILABILITY, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_WEBSITE_REACHABLE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_HTTP_STATUS_CODE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IMEI, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_PLUGGED_IN, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_BATTERY_LEVEL, b ? C0620c.item_next : C0620c.item_pro));
        if (VERSION.SDK_INT >= 21) {
            this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_BATTERY_SAVER, b ? C0620c.item_next : C0620c.item_pro));
        } else {
            this.f1207q.add(m2267a(C0481m.TASK_COND_IS_BATTERY_SAVER.cM, C0620c.task_if_battery_saver, C0625h.task_cond_if_battery_saver, C0625h.err_not_compatible_with_your_android_version, b ? C0620c.lock_warning_icon : C0620c.item_pro));
        }
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_BATTERY_TEMP, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_APP_INSTALLED, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_APP_RUNNING, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_AIRPLANE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_GPS, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_CAR_MODE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_MOBILE_DATA, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_NFC, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_NFC_BEAM, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SYNC, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_BRIGHTNESS_MODE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_BRIGHTNESS_LEVEL, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_NOTIFICATION_LIGHT, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_HAPTIC_FEEDBACK, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_AUTO_ROTATE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_WIRED_HEADSET, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_ROOT, b ? C0620c.item_next : C0620c.item_pro));
        if (VERSION.SDK_INT >= 21) {
            this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_ZEN_MODE, b ? C0620c.item_next : C0620c.item_pro));
        } else {
            this.f1207q.add(m2267a(C0481m.TASK_COND_IS_ZEN_MODE.cM, C0620c.task_if_zen_mode, C0625h.task_cond_zen_mode, C0625h.err_not_compatible_with_your_android_version, b ? C0620c.lock_warning_icon : C0620c.item_pro));
        }
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_MUSIC, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SOUND_PROFILE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SOUND_LEVEL_1, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SOUND_LEVEL_2, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SOUND_LEVEL_3, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SOUND_LEVEL_4, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SOUND_LEVEL_5, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SOUND_LEVEL_6, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_SOUND_LEVEL_7, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_DIRECTORY_EXIST, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_FILE_EXIST, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_FILE_CONTENT, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_VAR_EXIST, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_VAR_EQUAL, b ? C0620c.item_next : C0620c.item_pro));
        this.f1207q.add(C0486a.m2043a(C0481m.TASK_COND_IS_VAR_RANGE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1204n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1205o = new C0454c(getApplicationContext(), this.f1207q);
        this.f1205o.m1842a((C0447b) this);
        this.f1204n.setAdapter(this.f1205o);
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
