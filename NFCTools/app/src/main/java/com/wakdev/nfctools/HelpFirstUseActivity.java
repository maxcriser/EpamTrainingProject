package com.wakdev.nfctools;

import android.os.Bundle;
import android.support.v4.app.C0037f;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.C0122f;
import android.view.View;
import android.widget.Button;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0627j;

public class HelpFirstUseActivity extends C0037f {
    private ViewPager f1272n;
    private C0609k f1273o;
    private Button f1274p;
    private Button f1275q;
    private Button f1276r;
    private Button f1277s;
    private Button f1278t;

    /* renamed from: com.wakdev.nfctools.HelpFirstUseActivity.1 */
    class C05261 implements C0122f {
        final /* synthetic */ HelpFirstUseActivity f1271a;

        C05261(HelpFirstUseActivity helpFirstUseActivity) {
            this.f1271a = helpFirstUseActivity;
        }

        public void m2318a(int i) {
            this.f1271a.m2321a(i);
        }

        public void m2319a(int i, float f, int i2) {
        }

        public void m2320b(int i) {
        }
    }

    private void m2321a(int i) {
        switch (i) {
            case C0627j.View_android_focusable /*0*/:
                m2322a(this.f1274p);
            case C0627j.View_paddingStart /*1*/:
                m2322a(this.f1275q);
            case C0627j.View_paddingEnd /*2*/:
                m2322a(this.f1276r);
            case C0627j.Toolbar_subtitle /*3*/:
                m2322a(this.f1277s);
            case C0627j.Toolbar_contentInsetStart /*4*/:
                m2322a(this.f1278t);
            default:
        }
    }

    private void m2322a(Button button) {
        m2323a(this.f1274p, 20, 20);
        m2323a(this.f1275q, 20, 20);
        m2323a(this.f1276r, 20, 20);
        m2323a(this.f1277s, 20, 20);
        m2323a(this.f1278t, 20, 20);
        m2323a(button, 40, 40);
    }

    private void m2323a(Button button, int i, int i2) {
        button.setWidth(i2);
        button.setHeight(i);
    }

    private void m2325g() {
        this.f1272n = (ViewPager) findViewById(C0621d.viewPager);
        this.f1273o = new C0609k(getApplicationContext(), m174f());
        this.f1272n.setAdapter(this.f1273o);
        this.f1272n.setCurrentItem(0);
        m2327i();
    }

    private void m2326h() {
        this.f1272n.setOnPageChangeListener(new C05261(this));
    }

    private void m2327i() {
        this.f1274p = (Button) findViewById(C0621d.btn1);
        this.f1275q = (Button) findViewById(C0621d.btn2);
        this.f1276r = (Button) findViewById(C0621d.btn3);
        this.f1277s = (Button) findViewById(C0621d.btn4);
        this.f1278t = (Button) findViewById(C0621d.btn5);
        m2322a(this.f1274p);
    }

    public void onBackButtonClick(View view) {
        if (this.f1272n.getCurrentItem() > 0) {
            this.f1272n.setCurrentItem(this.f1272n.getCurrentItem() - 1);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.help_first_use_main);
        m2325g();
        m2326h();
    }

    public void onNextButtonClick(View view) {
        if (this.f1272n.getCurrentItem() < 4) {
            this.f1272n.setCurrentItem(this.f1272n.getCurrentItem() + 1);
        }
    }

    public void onSkipButtonClick(View view) {
        finish();
    }

    public void partnerLinkClick(View view) {
        C0495h.m2084a("http://whiztags.wakdev.com/nfctools/1/");
    }
}
