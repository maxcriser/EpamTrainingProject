package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.internal.p011a.C0317b;
import android.support.v7.internal.view.C0343d;
import android.support.v7.p009a.C0301a.C0300k;
import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/* renamed from: android.support.v7.app.c */
abstract class C0311c {
    final C0316b f599a;
    boolean f600b;
    boolean f601c;
    boolean f602d;
    boolean f603e;
    final C0317b f604f;
    private C0315a f605g;
    private MenuInflater f606h;
    private C0317b f607i;
    private boolean f608j;

    /* renamed from: android.support.v7.app.c.1 */
    class C03181 implements C0317b {
        final /* synthetic */ C0311c f634a;

        C03181(C0311c c0311c) {
            this.f634a = c0311c;
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            return this.f634a.f599a.m1535a(i, menu);
        }

        public View onCreatePanelView(int i) {
            return null;
        }

        public boolean onMenuItemSelected(int i, MenuItem menuItem) {
            return this.f634a.f599a.onMenuItemSelected(i, menuItem);
        }

        public boolean onMenuOpened(int i, Menu menu) {
            return this.f634a.f599a.onMenuOpened(i, menu);
        }

        public void onPanelClosed(int i, Menu menu) {
            this.f634a.f599a.onPanelClosed(i, menu);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            return this.f634a.f599a.m1536a(i, view, menu);
        }

        public C0320a startActionMode(C0308a c0308a) {
            return this.f634a.m1426a(c0308a);
        }
    }

    C0311c(C0316b c0316b) {
        this.f604f = new C03181(this);
        this.f599a = c0316b;
        this.f607i = this.f604f;
    }

    static C0311c m1424a(C0316b c0316b) {
        return VERSION.SDK_INT >= 11 ? new C0319d(c0316b) : new ActionBarActivityDelegateBase(c0316b);
    }

    abstract C0315a m1425a();

    abstract C0320a m1426a(C0308a c0308a);

    abstract View m1427a(String str, Context context, AttributeSet attributeSet);

    abstract void m1428a(int i);

    abstract void m1429a(int i, Menu menu);

    abstract void m1430a(Configuration configuration);

    void m1431a(Bundle bundle) {
        TypedArray obtainStyledAttributes = this.f599a.obtainStyledAttributes(C0300k.Theme);
        if (obtainStyledAttributes.hasValue(C0300k.Theme_windowActionBar)) {
            if (obtainStyledAttributes.getBoolean(C0300k.Theme_windowActionBar, false)) {
                this.f600b = true;
            }
            if (obtainStyledAttributes.getBoolean(C0300k.Theme_windowActionBarOverlay, false)) {
                this.f601c = true;
            }
            if (obtainStyledAttributes.getBoolean(C0300k.Theme_windowActionModeOverlay, false)) {
                this.f602d = true;
            }
            this.f603e = obtainStyledAttributes.getBoolean(C0300k.Theme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            return;
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    protected final void m1432a(C0315a c0315a) {
        this.f605g = c0315a;
    }

    final void m1433a(C0317b c0317b) {
        if (c0317b == null) {
            throw new IllegalArgumentException("callback can not be null");
        }
        this.f607i = c0317b;
    }

    abstract void m1434a(Toolbar toolbar);

    abstract void m1435a(View view);

    abstract void m1436a(View view, LayoutParams layoutParams);

    abstract void m1437a(CharSequence charSequence);

    boolean m1438a(int i, KeyEvent keyEvent) {
        return false;
    }

    abstract boolean m1439a(int i, View view, Menu menu);

    boolean m1440a(View view, Menu menu) {
        return VERSION.SDK_INT < 16 ? this.f599a.onPrepareOptionsMenu(menu) : this.f599a.m1543b(view, menu);
    }

    final C0315a m1441b() {
        if (this.f600b && this.f605g == null) {
            this.f605g = m1425a();
        }
        return this.f605g;
    }

    abstract View m1442b(int i);

    abstract void m1443b(View view, LayoutParams layoutParams);

    abstract boolean m1444b(int i, KeyEvent keyEvent);

    abstract boolean m1445b(int i, Menu menu);

    final C0315a m1446c() {
        return this.f605g;
    }

    abstract boolean m1447c(int i, Menu menu);

    MenuInflater m1448d() {
        if (this.f606h == null) {
            this.f606h = new C0343d(m1454j());
        }
        return this.f606h;
    }

    abstract void m1449e();

    abstract void m1450f();

    abstract void m1451g();

    abstract boolean m1452h();

    abstract void m1453i();

    protected final Context m1454j() {
        Context context = null;
        C0315a b = m1441b();
        if (b != null) {
            context = b.m1522b();
        }
        return context == null ? this.f599a : context;
    }

    final C0317b m1455k() {
        return this.f607i;
    }

    final void m1456l() {
        this.f608j = true;
    }

    final boolean m1457m() {
        return this.f608j;
    }
}
