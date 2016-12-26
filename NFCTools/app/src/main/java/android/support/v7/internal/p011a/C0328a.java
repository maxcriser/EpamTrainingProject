package android.support.v7.internal.p011a;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.C0234u;
import android.support.v7.app.C0315a;
import android.support.v7.app.C0315a.C0313b;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.support.v7.widget.WindowCallbackWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.ArrayList;

/* renamed from: android.support.v7.internal.a.a */
public class C0328a extends C0315a {
    private Toolbar f645a;
    private DecorToolbar f646b;
    private boolean f647c;
    private C0317b f648d;
    private boolean f649e;
    private boolean f650f;
    private ArrayList<C0313b> f651g;
    private Window f652h;
    private ListMenuPresenter f653i;
    private final Runnable f654j;
    private final OnMenuItemClickListener f655k;

    /* renamed from: android.support.v7.internal.a.a.1 */
    class C03221 implements Runnable {
        final /* synthetic */ C0328a f638a;

        C03221(C0328a c0328a) {
            this.f638a = c0328a;
        }

        public void run() {
            this.f638a.m1592f();
        }
    }

    /* renamed from: android.support.v7.internal.a.a.2 */
    class C03232 implements OnMenuItemClickListener {
        final /* synthetic */ C0328a f639a;

        C03232(C0328a c0328a) {
            this.f639a = c0328a;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            return this.f639a.f648d.onMenuItemSelected(0, menuItem);
        }
    }

    /* renamed from: android.support.v7.internal.a.a.a */
    private final class C0324a implements Callback {
        final /* synthetic */ C0328a f640a;
        private boolean f641b;

        private C0324a(C0328a c0328a) {
            this.f640a = c0328a;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (!this.f641b) {
                this.f641b = true;
                this.f640a.f645a.dismissPopupMenus();
                if (this.f640a.f648d != null) {
                    this.f640a.f648d.onPanelClosed(8, menuBuilder);
                }
                this.f641b = false;
            }
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (this.f640a.f648d == null) {
                return false;
            }
            this.f640a.f648d.onMenuOpened(8, menuBuilder);
            return true;
        }
    }

    /* renamed from: android.support.v7.internal.a.a.b */
    private final class C0325b implements MenuBuilder.Callback {
        final /* synthetic */ C0328a f642a;

        private C0325b(C0328a c0328a) {
            this.f642a = c0328a;
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.f642a.f648d == null) {
                return;
            }
            if (this.f642a.f645a.isOverflowMenuShowing()) {
                this.f642a.f648d.onPanelClosed(8, menuBuilder);
            } else if (this.f642a.f648d.onPreparePanel(0, null, menuBuilder)) {
                this.f642a.f648d.onMenuOpened(8, menuBuilder);
            }
        }
    }

    /* renamed from: android.support.v7.internal.a.a.c */
    private final class C0326c implements Callback {
        final /* synthetic */ C0328a f643a;

        private C0326c(C0328a c0328a) {
            this.f643a = c0328a;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (this.f643a.f648d != null) {
                this.f643a.f648d.onPanelClosed(0, menuBuilder);
            }
            this.f643a.f652h.closePanel(0);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null && this.f643a.f648d != null) {
                this.f643a.f648d.onMenuOpened(0, menuBuilder);
            }
            return true;
        }
    }

    /* renamed from: android.support.v7.internal.a.a.d */
    private class C0327d extends WindowCallbackWrapper {
        final /* synthetic */ C0328a f644a;

        public C0327d(C0328a c0328a, C0317b c0317b) {
            this.f644a = c0328a;
            super(c0317b);
        }

        public View onCreatePanelView(int i) {
            switch (i) {
                case C0627j.View_android_focusable /*0*/:
                    if (!this.f644a.f647c) {
                        this.f644a.m1592f();
                        this.f644a.f645a.removeCallbacks(this.f644a.f654j);
                    }
                    if (this.f644a.f647c && this.f644a.f648d != null) {
                        Menu f = this.f644a.m1576g();
                        if (this.f644a.f648d.onPreparePanel(i, null, f) && this.f644a.f648d.onMenuOpened(i, f)) {
                            return this.f644a.m1569a(f);
                        }
                    }
            }
            return super.onCreatePanelView(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (onPreparePanel && !this.f644a.f647c) {
                this.f644a.f646b.setMenuPrepared();
                this.f644a.f647c = true;
            }
            return onPreparePanel;
        }
    }

    public C0328a(Toolbar toolbar, CharSequence charSequence, Window window, C0317b c0317b) {
        this.f651g = new ArrayList();
        this.f654j = new C03221(this);
        this.f655k = new C03232(this);
        this.f645a = toolbar;
        this.f646b = new ToolbarWidgetWrapper(toolbar, false);
        this.f648d = new C0327d(this, c0317b);
        this.f646b.setWindowCallback(this.f648d);
        toolbar.setOnMenuItemClickListener(this.f655k);
        this.f646b.setWindowTitle(charSequence);
        this.f652h = window;
    }

    private View m1569a(Menu menu) {
        return (menu == null || this.f653i == null || this.f653i.getAdapter().getCount() <= 0) ? null : (View) this.f653i.getMenuView(this.f645a);
    }

    private Menu m1576g() {
        if (!this.f649e) {
            this.f645a.setMenuCallbacks(new C0324a(), new C0325b());
            this.f649e = true;
        }
        return this.f645a.getMenu();
    }

    public int m1578a() {
        return this.f646b.getDisplayOptions();
    }

    public C0320a m1579a(C0308a c0308a) {
        return this.f648d.startActionMode(c0308a);
    }

    public void m1580a(float f) {
        C0234u.m1091e(this.f645a, f);
    }

    public void m1581a(Configuration configuration) {
        super.m1519a(configuration);
    }

    public void m1582a(ListMenuPresenter listMenuPresenter) {
        Menu g = m1576g();
        if (g instanceof MenuBuilder) {
            MenuBuilder menuBuilder = (MenuBuilder) g;
            if (this.f653i != null) {
                this.f653i.setCallback(null);
                menuBuilder.removeMenuPresenter(this.f653i);
            }
            this.f653i = listMenuPresenter;
            if (listMenuPresenter != null) {
                listMenuPresenter.setCallback(new C0326c());
                menuBuilder.addMenuPresenter(listMenuPresenter);
            }
        }
    }

    public void m1583a(CharSequence charSequence) {
        this.f646b.setWindowTitle(charSequence);
    }

    public void m1584a(boolean z) {
    }

    public Context m1585b() {
        return this.f645a.getContext();
    }

    public void m1586c(boolean z) {
    }

    public boolean m1587c() {
        this.f645a.removeCallbacks(this.f654j);
        C0234u.m1080a(this.f645a, this.f654j);
        return true;
    }

    public void m1588d(boolean z) {
    }

    public boolean m1589d() {
        if (!this.f645a.hasExpandedActionView()) {
            return false;
        }
        this.f645a.collapseActionView();
        return true;
    }

    public C0317b m1590e() {
        return this.f648d;
    }

    public void m1591e(boolean z) {
        if (z != this.f650f) {
            this.f650f = z;
            int size = this.f651g.size();
            for (int i = 0; i < size; i++) {
                ((C0313b) this.f651g.get(i)).m1510a(z);
            }
        }
    }

    void m1592f() {
        Menu g = m1576g();
        MenuBuilder menuBuilder = g instanceof MenuBuilder ? (MenuBuilder) g : null;
        if (menuBuilder != null) {
            menuBuilder.stopDispatchingItemsChanged();
        }
        try {
            g.clear();
            if (!(this.f648d.onCreatePanelMenu(0, g) && this.f648d.onPreparePanel(0, null, g))) {
                g.clear();
            }
            if (menuBuilder != null) {
                menuBuilder.startDispatchingItemsChanged();
            }
        } catch (Throwable th) {
            if (menuBuilder != null) {
                menuBuilder.startDispatchingItemsChanged();
            }
        }
    }
}
