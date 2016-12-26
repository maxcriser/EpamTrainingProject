package android.support.v7.internal.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.ac;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0291b;
import android.support.v7.p009a.C0301a.C0293d;
import android.support.v7.p009a.C0301a.C0296g;
import android.support.v7.p009a.C0301a.C0300k;
import android.view.ViewConfiguration;

/* renamed from: android.support.v7.internal.view.a */
public class C0337a {
    private Context f698a;

    private C0337a(Context context) {
        this.f698a = context;
    }

    public static C0337a m1650a(Context context) {
        return new C0337a(context);
    }

    public int m1651a() {
        return this.f698a.getResources().getInteger(C0296g.abc_max_action_buttons);
    }

    public boolean m1652b() {
        return VERSION.SDK_INT >= 19 || !ac.m782b(ViewConfiguration.get(this.f698a));
    }

    public int m1653c() {
        return this.f698a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean m1654d() {
        return this.f698a.getApplicationInfo().targetSdkVersion >= 16 ? this.f698a.getResources().getBoolean(C0291b.abc_action_bar_embed_tabs) : this.f698a.getResources().getBoolean(C0291b.abc_action_bar_embed_tabs_pre_jb);
    }

    public int m1655e() {
        TypedArray obtainStyledAttributes = this.f698a.obtainStyledAttributes(null, C0300k.ActionBar, C0290a.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(C0300k.ActionBar_height, 0);
        Resources resources = this.f698a.getResources();
        if (!m1654d()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(C0293d.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public boolean m1656f() {
        return this.f698a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int m1657g() {
        return this.f698a.getResources().getDimensionPixelSize(C0293d.abc_action_bar_stacked_tab_max_width);
    }
}
