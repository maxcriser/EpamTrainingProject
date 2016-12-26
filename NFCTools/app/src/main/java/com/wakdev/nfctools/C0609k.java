package com.wakdev.nfctools;

import android.content.Context;
import android.support.v4.app.C0039h;
import android.support.v4.app.C0045j;
import android.support.v4.app.Fragment;
import com.wakdev.nfctools.C0628m.C0627j;

/* renamed from: com.wakdev.nfctools.k */
public class C0609k extends C0045j {
    private Context f2396a;

    public C0609k(Context context, C0039h c0039h) {
        super(c0039h);
        this.f2396a = context;
    }

    public Fragment m3027a(int i) {
        Fragment fragment = new Fragment();
        switch (i) {
            case C0627j.View_android_focusable /*0*/:
                return C0601f.m2939a(this.f2396a);
            case C0627j.View_paddingStart /*1*/:
                return C0605g.m3019a(this.f2396a);
            case C0627j.View_paddingEnd /*2*/:
                return C0606h.m3021a(this.f2396a);
            case C0627j.Toolbar_subtitle /*3*/:
                return C0607i.m3023a(this.f2396a);
            case C0627j.Toolbar_contentInsetStart /*4*/:
                return C0608j.m3025a(this.f2396a);
            default:
                return fragment;
        }
    }

    public int m3028b() {
        return 5;
    }
}
