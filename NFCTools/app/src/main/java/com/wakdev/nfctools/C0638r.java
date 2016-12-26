package com.wakdev.nfctools;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.C0039h;
import android.support.v4.app.C0045j;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.wakdev.libs.core.WDCore;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;

/* renamed from: com.wakdev.nfctools.r */
public class C0638r extends C0045j {
    SparseArray<Fragment> f2412a;

    public C0638r(C0039h c0039h) {
        super(c0039h);
        this.f2412a = new SparseArray();
    }

    public Fragment m3058a(int i) {
        Fragment c0637q;
        Bundle bundle;
        switch (i) {
            case C0627j.View_paddingStart /*1*/:
                c0637q = new C0637q();
                bundle = new Bundle();
                bundle.putInt("section_number", i + 1);
                c0637q.m58b(bundle);
                return c0637q;
            case C0627j.View_paddingEnd /*2*/:
                c0637q = new C0629n();
                bundle = new Bundle();
                bundle.putInt("section_number", i + 1);
                c0637q.m58b(bundle);
                return c0637q;
            case C0627j.Toolbar_subtitle /*3*/:
                c0637q = new C0634p();
                bundle = new Bundle();
                bundle.putInt("section_number", i + 1);
                c0637q.m58b(bundle);
                return c0637q;
            default:
                c0637q = new C0630o();
                bundle = new Bundle();
                bundle.putInt("section_number", i + 1);
                c0637q.m58b(bundle);
                return c0637q;
        }
    }

    public Object m3059a(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.m262a(viewGroup, i);
        this.f2412a.put(i, fragment);
        return fragment;
    }

    public void m3060a(ViewGroup viewGroup, int i, Object obj) {
        this.f2412a.remove(i);
        super.m265a(viewGroup, i, obj);
    }

    public int m3061b() {
        return 4;
    }

    public CharSequence m3062c(int i) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        switch (i) {
            case C0627j.View_android_focusable /*0*/:
                return applicationContext.getString(C0625h.tabtitle0);
            case C0627j.View_paddingStart /*1*/:
                return applicationContext.getString(C0625h.tabtitle1);
            case C0627j.View_paddingEnd /*2*/:
                return applicationContext.getString(C0625h.tabtitle2);
            case C0627j.Toolbar_subtitle /*3*/:
                return applicationContext.getString(C0625h.tabtitle3);
            default:
                return null;
        }
    }

    public Fragment m3063e(int i) {
        return (Fragment) this.f2412a.get(i);
    }
}
