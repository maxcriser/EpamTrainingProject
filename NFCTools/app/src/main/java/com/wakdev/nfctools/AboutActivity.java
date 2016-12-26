package com.wakdev.nfctools;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AboutActivity extends C0316b {
    public void clickApi(View view) {
        if (getResources().getConfiguration().locale.getLanguage().equals("fr")) {
            C0495h.m2084a("http://fr.api.nfc.systems/");
        } else {
            C0495h.m2084a("http://en.api.nfc.systems/");
        }
    }

    public void clickBug(View view) {
        C0495h.m2084a("mailto:nfctools@wakdev.com?subject=NFC Tools");
    }

    public void clickBuyCoffee(View view) {
        C0495h.m2085a("com.wakdev.donate", 1);
    }

    public void clickBuyPro(View view) {
        C0495h.m2085a("com.wakdev.nfctools.pro", 1);
    }

    public void clickDeviceCheck(View view) {
        try {
            C0495h.m2084a("http://devices.nfc.help/?search=" + URLEncoder.encode(Build.MODEL, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void clickFAQ(View view) {
        if (getResources().getConfiguration().locale.getLanguage().equals("fr")) {
            C0495h.m2084a("http://fr.faq.nfc.systems/");
        } else {
            C0495h.m2084a("http://en.faq.nfc.systems/");
        }
    }

    public void clickNFCTasks(View view) {
        C0495h.m2085a("com.wakdev.nfctasks", 1);
    }

    public void clickRate(View view) {
        if (C0507a.m2175a().m2194b()) {
            C0495h.m2085a("com.wakdev.nfctools.pro", 1);
        } else {
            C0495h.m2085a("com.wakdev.wdnfc", 1);
        }
    }

    public void clickShare(View view) {
        if (C0507a.m2175a().m2194b()) {
            C0495h.m2089b("com.wakdev.nfctools.pro", 1);
        } else {
            C0495h.m2089b("com.wakdev.wdnfc", 1);
        }
    }

    public void clickTerms(View view) {
        if (getResources().getConfiguration().locale.getLanguage().equals("fr")) {
            C0495h.m2084a("http://fr.terms.wakdev.com/");
        } else {
            C0495h.m2084a("http://en.terms.wakdev.com/");
        }
    }

    public void clickVersion(View view) {
        if (C0507a.m2175a().m2194b()) {
            C0495h.m2085a("com.wakdev.nfctools.pro", 1);
        } else {
            C0495h.m2085a("com.wakdev.wdnfc", 1);
        }
    }

    public void clickWakdev(View view) {
        C0495h.m2084a("http://www.wakdev.com");
    }

    public void clickWhiztags(View view) {
        C0495h.m2084a("http://whiztags.wakdev.com/nfctools/3/");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.about);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        if (C0507a.m2176g() == 5 && C0507a.m2175a().m2194b()) {
            ((Button) findViewById(C0621d.donate_button)).setVisibility(8);
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
