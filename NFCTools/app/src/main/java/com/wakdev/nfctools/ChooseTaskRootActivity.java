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

public class ChooseTaskRootActivity extends C0316b implements C0447b {
    private ListView f1228n;
    private C0454c f1229o;
    private ArrayList<C0446a> f1230p;

    /* renamed from: com.wakdev.nfctools.ChooseTaskRootActivity.1 */
    static /* synthetic */ class C05181 {
        static final /* synthetic */ int[] f1227a;

        static {
            f1227a = new int[C0481m.values().length];
            try {
                f1227a[C0481m.TASK_REBOOT_DEVICE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1227a[C0481m.TASK_SHUTDOWN_DEVICE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1227a[C0481m.TASK_LOCKSCREEN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1227a[C0481m.TASK_ZEN_MODE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1227a[C0481m.TASK_BATTERY_SAVER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private C0446a m2279a(int i, int i2, int i3, int i4, int i5) {
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

    public void m2280a(C0446a c0446a) {
        C0481m a = C0481m.m2026a(c0446a.m1819h());
        Intent intent = null;
        Serializable hashMap;
        Intent intent2;
        switch (C05181.f1227a[a.ordinal()]) {
            case C0627j.View_paddingStart /*1*/:
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
            case C0627j.View_paddingEnd /*2*/:
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
            case C0627j.Toolbar_subtitle /*3*/:
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
            case C0627j.Toolbar_contentInsetStart /*4*/:
                if (VERSION.SDK_INT >= 21) {
                    intent = new Intent(this, TaskZenModeActivity.class);
                    break;
                }
                break;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                if (VERSION.SDK_INT >= 21) {
                    intent = new Intent(this, TaskBatterySaverActivity.class);
                    break;
                }
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

    public void m2281b(C0446a c0446a) {
        m2280a(c0446a);
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
        this.f1230p = new ArrayList();
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_PLANE_MODE));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_GPS_MODE));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_NFC_MODE));
        if (VERSION.SDK_INT >= 21) {
            this.f1230p.add(C0486a.m2047c(C0481m.TASK_MOBILE_DATA_STATE));
        }
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_MISC_KILL_APP_ROOT));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_EXE_CMD));
        this.f1230p.add(C0486a.m2043a(C0481m.TASK_REBOOT_DEVICE, 0));
        this.f1230p.add(C0486a.m2043a(C0481m.TASK_SHUTDOWN_DEVICE, 0));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_DISABLE_APP));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_ENABLE_APP));
        if (VERSION.SDK_INT >= 21) {
            this.f1230p.add(C0486a.m2047c(C0481m.TASK_ZEN_MODE));
        } else {
            this.f1230p.add(m2279a(C0481m.TASK_ZEN_MODE.cM, C0620c.task_zen_mode, C0625h.task_zen_mode, C0625h.err_not_compatible_with_your_android_version, C0620c.lock_warning_icon));
        }
        if (VERSION.SDK_INT >= 21) {
            this.f1230p.add(C0486a.m2047c(C0481m.TASK_BATTERY_SAVER));
        } else {
            this.f1230p.add(m2279a(C0481m.TASK_BATTERY_SAVER.cM, C0620c.task_battery_saver, C0625h.task_battery_saver, C0625h.err_not_compatible_with_your_android_version, C0620c.lock_warning_icon));
        }
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_SCREENSHOT));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_BUTTON));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_KEYBOARD));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_DPAD));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_NUMPAD));
        this.f1230p.add(C0486a.m2047c(C0481m.TASK_GAMEPAD));
        this.f1230p.add(C0486a.m2043a(C0481m.TASK_LOCKSCREEN, 0));
        this.f1228n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1229o = new C0454c(getApplicationContext(), this.f1230p);
        this.f1229o.m1842a((C0447b) this);
        this.f1228n.setAdapter(this.f1229o);
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
