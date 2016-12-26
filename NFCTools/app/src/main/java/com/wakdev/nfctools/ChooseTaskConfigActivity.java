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

public class ChooseTaskConfigActivity extends C0316b implements C0447b {
    private ListView f1209n;
    private C0454c f1210o;
    private C0507a f1211p;
    private ArrayList<C0446a> f1212q;

    /* renamed from: com.wakdev.nfctools.ChooseTaskConfigActivity.1 */
    static /* synthetic */ class C05151 {
        static final /* synthetic */ int[] f1208a;

        static {
            f1208a = new int[C0481m.values().length];
            try {
                f1208a[C0481m.TASK_TIMER_SET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1208a[C0481m.TASK_CONFIG_INPUT_METHOD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1208a[C0481m.TASK_ALARM_DISMISS_ALL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1208a[C0481m.TASK_SOUND_DO_NOT_DISTURB_PLUS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private C0446a m2270a(int i, int i2, int i3, int i4, int i5) {
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

    public void m2271a(C0446a c0446a) {
        C0481m a = C0481m.m2026a(c0446a.m1819h());
        Intent intent = null;
        Serializable hashMap;
        Intent intent2;
        switch (C05151.f1208a[a.ordinal()]) {
            case C0627j.View_paddingStart /*1*/:
                if (VERSION.SDK_INT >= 19) {
                    intent = new Intent(this, TaskTimerSetActivity.class);
                    break;
                }
                break;
            case C0627j.View_paddingEnd /*2*/:
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
            case C0627j.Toolbar_subtitle /*3*/:
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
            case C0627j.Toolbar_contentInsetStart /*4*/:
                if (VERSION.SDK_INT >= 23) {
                    intent = new Intent(this, TaskDoNotDisturbPlusActivity.class);
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

    public void m2272b(C0446a c0446a) {
        m2271a(c0446a);
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
        this.f1211p = C0507a.m2175a();
        boolean b = this.f1211p.m2194b();
        this.f1212q = new ArrayList();
        this.f1212q.add(C0486a.m2047c(C0481m.TASK_SOUND_MODE));
        this.f1212q.add(C0486a.m2047c(C0481m.TASK_SOUND_DO_NOT_DISTURB));
        if (VERSION.SDK_INT >= 23) {
            this.f1212q.add(C0486a.m2047c(C0481m.TASK_SOUND_DO_NOT_DISTURB_PLUS));
        } else {
            this.f1212q.add(m2270a(C0481m.TASK_SOUND_DO_NOT_DISTURB_PLUS.cM, C0620c.task_donotdisturb_plus, C0625h.task_donotdisturb_plus, C0625h.err_not_compatible_with_your_android_version, C0620c.lock_warning_icon));
        }
        this.f1212q.add(C0486a.m2047c(C0481m.TASK_ALARM_SET));
        this.f1212q.add(C0486a.m2047c(C0481m.TASK_ALARM_IN));
        if (VERSION.SDK_INT >= 23) {
            this.f1212q.add(C0486a.m2043a(C0481m.TASK_ALARM_DISMISS_ALL, 0));
        } else {
            this.f1212q.add(m2270a(C0481m.TASK_ALARM_DISMISS_ALL.cM, C0620c.task_dismiss_alarms, C0625h.task_dismiss_alarms, C0625h.err_not_compatible_with_your_android_version, C0620c.lock_warning_icon));
        }
        this.f1212q.add(C0486a.m2047c(C0481m.TASK_CONFIG_CAR_MODE));
        if (VERSION.SDK_INT >= 19) {
            this.f1212q.add(C0486a.m2047c(C0481m.TASK_TIMER_SET));
        } else {
            this.f1212q.add(m2270a(C0481m.TASK_TIMER_SET.cM, C0620c.task_timer, C0625h.task_timer_set, C0625h.err_not_compatible_with_your_android_version, C0620c.lock_warning_icon));
        }
        this.f1212q.add(C0486a.m2043a(C0481m.TASK_CONFIG_INPUT_METHOD, 0));
        this.f1212q.add(C0486a.m2047c(C0481m.TASK_CONFIG_SAMSUNG));
        this.f1212q.add(C0486a.m2043a(C0481m.TASK_CONFIG_TIMEZONE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1212q.add(C0486a.m2043a(C0481m.TASK_CONFIG_SYNC_STATE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1212q.add(C0486a.m2043a(C0481m.TASK_CONFIG_HAPTIC_FEEDBACK, b ? C0620c.item_next : C0620c.item_pro));
        this.f1212q.add(C0486a.m2047c(C0481m.TASK_CONFIG_OPEN_SETTINGS));
        this.f1209n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1210o = new C0454c(getApplicationContext(), this.f1212q);
        this.f1210o.m1842a((C0447b) this);
        this.f1209n.setAdapter(this.f1210o);
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
