package android.support.v7.internal.view;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

/* renamed from: android.support.v7.internal.view.b */
public class C0338b extends C0320a implements Callback {
    private Context f699a;
    private ActionBarContextView f700b;
    private C0308a f701c;
    private WeakReference<View> f702d;
    private boolean f703e;
    private boolean f704f;
    private MenuBuilder f705g;

    public C0338b(Context context, ActionBarContextView actionBarContextView, C0308a c0308a, boolean z) {
        this.f699a = context;
        this.f700b = actionBarContextView;
        this.f701c = c0308a;
        this.f705g = new MenuBuilder(context).setDefaultShowAsAction(1);
        this.f705g.setCallback(this);
        this.f704f = z;
    }

    public MenuInflater m1658a() {
        return new MenuInflater(this.f699a);
    }

    public void m1659a(int i) {
        m1665b(this.f699a.getString(i));
    }

    public void m1660a(View view) {
        this.f700b.setCustomView(view);
        this.f702d = view != null ? new WeakReference(view) : null;
    }

    public void m1661a(CharSequence charSequence) {
        this.f700b.setSubtitle(charSequence);
    }

    public void m1662a(boolean z) {
        super.m1555a(z);
        this.f700b.setTitleOptional(z);
    }

    public Menu m1663b() {
        return this.f705g;
    }

    public void m1664b(int i) {
        m1661a(this.f699a.getString(i));
    }

    public void m1665b(CharSequence charSequence) {
        this.f700b.setTitle(charSequence);
    }

    public void m1666c() {
        if (!this.f703e) {
            this.f703e = true;
            this.f700b.sendAccessibilityEvent(32);
            this.f701c.m1416a(this);
        }
    }

    public void m1667d() {
        this.f701c.m1419b(this, this.f705g);
    }

    public CharSequence m1668f() {
        return this.f700b.getTitle();
    }

    public CharSequence m1669g() {
        return this.f700b.getSubtitle();
    }

    public boolean m1670h() {
        return this.f700b.isTitleOptional();
    }

    public View m1671i() {
        return this.f702d != null ? (View) this.f702d.get() : null;
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.f701c.m1418a((C0320a) this, menuItem);
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        m1667d();
        this.f700b.showOverflowMenu();
    }
}
