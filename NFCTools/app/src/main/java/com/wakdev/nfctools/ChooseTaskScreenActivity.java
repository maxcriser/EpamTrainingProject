package com.wakdev.nfctools;

import android.content.Intent;
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

public class ChooseTaskScreenActivity extends C0316b implements C0447b {
    private ListView f1237n;
    private C0454c f1238o;
    private ArrayList<C0446a> f1239p;
    private C0507a f1240q;

    /* renamed from: com.wakdev.nfctools.ChooseTaskScreenActivity.1 */
    static /* synthetic */ class C05201 {
        static final /* synthetic */ int[] f1236a;

        static {
            f1236a = new int[C0481m.values().length];
            try {
                f1236a[C0481m.TASK_SCREEN_START_SCREENSAVER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public void m2284a(C0446a c0446a) {
        C0481m a = C0481m.m2026a(c0446a.m1819h());
        Intent intent = null;
        this.f1240q = C0507a.m2175a();
        switch (C05201.f1236a[a.ordinal()]) {
            case C0627j.View_paddingStart /*1*/:
                Serializable hashMap = new HashMap();
                hashMap.put("field1", "1");
                Intent intent2 = new Intent();
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
                intent = new Intent(this, C0486a.m2048d(a));
                break;
        }
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2285b(C0446a c0446a) {
        m2284a(c0446a);
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
        this.f1240q = C0507a.m2175a();
        boolean b = this.f1240q.m2194b();
        this.f1239p = new ArrayList();
        this.f1239p.add(C0486a.m2047c(C0481m.TASK_SCREEN_BRIGHTNESS_MODE));
        this.f1239p.add(C0486a.m2047c(C0481m.TASK_SCREEN_BRIGHTNESS));
        this.f1239p.add(C0486a.m2047c(C0481m.TASK_SCREEN_ADAPTIVE_BRIGHTNESS));
        this.f1239p.add(C0486a.m2047c(C0481m.TASK_SCREEN_DISPLAY_SLEEP));
        this.f1239p.add(C0486a.m2047c(C0481m.TASK_SCREEN_AUTO_ROTATE));
        this.f1239p.add(C0486a.m2047c(C0481m.TASK_SCREEN_NOTIFICATION_LIGHT));
        this.f1239p.add(C0486a.m2043a(C0481m.TASK_SCREEN_START_SCREENSAVER, 0));
        this.f1239p.add(C0486a.m2043a(C0481m.TASK_SCREEN_CHANGE_WALLPAPER, b ? C0620c.item_next : C0620c.item_pro));
        this.f1239p.add(C0486a.m2043a(C0481m.TASK_SCREEN_SHOW_IMAGE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1237n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1238o = new C0454c(getApplicationContext(), this.f1239p);
        this.f1238o.m1842a((C0447b) this);
        this.f1237n.setAdapter(this.f1238o);
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
