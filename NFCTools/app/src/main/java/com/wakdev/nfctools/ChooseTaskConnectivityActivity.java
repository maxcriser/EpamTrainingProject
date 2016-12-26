package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.commons.C0486a;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.util.ArrayList;

public class ChooseTaskConnectivityActivity extends C0316b implements C0447b {
    private ListView f1213n;
    private C0454c f1214o;
    private ArrayList<C0446a> f1215p;
    private C0507a f1216q;

    public void m2273a(C0446a c0446a) {
        Intent intent = new Intent(this, C0486a.m2048d(C0481m.m2026a(c0446a.m1819h())));
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2274b(C0446a c0446a) {
        m2273a(c0446a);
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
        setContentView(C0622e.choose_task_connectivity);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1216q = C0507a.m2175a();
        boolean b = this.f1216q.m2194b();
        this.f1215p = new ArrayList();
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_WIFI_STATE));
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_HOTSPOT_STATE));
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_BLUETOOTH_STATE));
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_BLUETOOTH_DEVICE_CONNECT));
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_BLUETOOTH_DEVICE_DISCONNECT));
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_BLUETOOTH_DISCOVERABLE));
        if (VERSION.SDK_INT < 21) {
            this.f1215p.add(C0486a.m2047c(C0481m.TASK_MOBILE_DATA_STATE));
        }
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_WIFI_NETWORK));
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_WIFI_FORGOT_NETWORK));
        this.f1215p.add(C0486a.m2047c(C0481m.TASK_BLUETOOTH_DEVICE_UNPAIR));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_NETWORK_WOL, b ? C0620c.item_next : C0620c.item_pro));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_NETWORK_PING, b ? C0620c.item_next : C0620c.item_pro));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_NETWORK_HTTP_AUTH, b ? C0620c.item_next : C0620c.item_pro));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_NETWORK_HTTP_GET, b ? C0620c.item_next : C0620c.item_pro));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_NETWORK_HTTP_GET_TO_VAR, b ? C0620c.item_next : C0620c.item_pro));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_NETWORK_HTTP_POST, b ? C0620c.item_next : C0620c.item_pro));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_NETWORK_HTTP_POST_TO_VAR, b ? C0620c.item_next : C0620c.item_pro));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_DOWNLOAD_FILE, b ? C0620c.item_next : C0620c.item_pro));
        this.f1215p.add(C0486a.m2043a(C0481m.TASK_OPENVPN, b ? C0620c.item_next : C0620c.item_pro));
        this.f1213n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1214o = new C0454c(getApplicationContext(), this.f1215p);
        this.f1214o.m1842a((C0447b) this);
        this.f1213n.setAdapter(this.f1214o);
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
