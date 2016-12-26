package com.wakdev.nfctools;

import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.core.WDCore;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;

public class ProEditionActivity extends C0316b {
    public void clickPro(View view) {
        if ("com.wakdev.droidautomation.free".equals(WDCore.m2174a().getPackageName()) || "com.wakdev.droidautomation.pro".equals(WDCore.m2174a().getPackageName())) {
            C0495h.m2085a("com.wakdev.droidautomation.pro", 1);
        } else {
            C0495h.m2085a("com.wakdev.nfctools.pro", 1);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.pro_edition);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        if (C0507a.m2176g() == 2) {
            ((TextView) findViewById(C0621d.download_pro_button)).setText(getString(C0625h.download_app_button_amazon));
        }
        if (C0507a.m2176g() == 3) {
            ((TextView) findViewById(C0621d.download_pro_button)).setText(getString(C0625h.download_app_button_samsung));
        }
        if (C0507a.m2176g() == 5) {
            ((TextView) findViewById(C0621d.download_pro_button)).setText(getString(C0625h.download_app_button_slideme));
        }
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
