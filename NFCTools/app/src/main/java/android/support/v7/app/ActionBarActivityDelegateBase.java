package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.C0059o;
import android.support.v4.view.C0218q;
import android.support.v4.view.C0234u;
import android.support.v4.view.ac;
import android.support.v4.view.am;
import android.support.v7.internal.p011a.C0317b;
import android.support.v7.internal.p011a.C0328a;
import android.support.v7.internal.p011a.C0333c;
import android.support.v7.internal.view.C0338b;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.DecorContentParent;
import android.support.v7.internal.widget.FitWindowsViewGroup;
import android.support.v7.internal.widget.FitWindowsViewGroup.OnFitSystemWindowsListener;
import android.support.v7.internal.widget.TintCheckBox;
import android.support.v7.internal.widget.TintCheckedTextView;
import android.support.v7.internal.widget.TintEditText;
import android.support.v7.internal.widget.TintRadioButton;
import android.support.v7.internal.widget.TintSpinner;
import android.support.v7.internal.widget.ViewStubCompat;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0292c;
import android.support.v7.p009a.C0301a.C0295f;
import android.support.v7.p009a.C0301a.C0297h;
import android.support.v7.p009a.C0301a.C0299j;
import android.support.v7.p009a.C0301a.C0300k;
import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.wakdev.nfctools.C0628m.C0627j;

class ActionBarActivityDelegateBase extends C0311c implements Callback {
    private boolean f609A;
    private ListMenuPresenter f610B;
    private Rect f611C;
    private Rect f612D;
    C0320a f613g;
    ActionBarContextView f614h;
    PopupWindow f615i;
    Runnable f616j;
    private DecorContentParent f617k;
    private C0307a f618l;
    private C0310c f619m;
    private boolean f620n;
    private ViewGroup f621o;
    private ViewGroup f622p;
    private View f623q;
    private CharSequence f624r;
    private boolean f625s;
    private boolean f626t;
    private boolean f627u;
    private PanelFeatureState[] f628v;
    private PanelFeatureState f629w;
    private boolean f630x;
    private int f631y;
    private final Runnable f632z;

    /* renamed from: android.support.v7.app.ActionBarActivityDelegateBase.1 */
    class C03021 implements Runnable {
        final /* synthetic */ ActionBarActivityDelegateBase f575a;

        C03021(ActionBarActivityDelegateBase actionBarActivityDelegateBase) {
            this.f575a = actionBarActivityDelegateBase;
        }

        public void run() {
            if ((this.f575a.f631y & 1) != 0) {
                this.f575a.m1479d(0);
            }
            if ((this.f575a.f631y & 256) != 0) {
                this.f575a.m1479d(8);
            }
            this.f575a.f630x = false;
            this.f575a.f631y = 0;
        }
    }

    /* renamed from: android.support.v7.app.ActionBarActivityDelegateBase.2 */
    class C03032 implements C0218q {
        final /* synthetic */ ActionBarActivityDelegateBase f576a;

        C03032(ActionBarActivityDelegateBase actionBarActivityDelegateBase) {
            this.f576a = actionBarActivityDelegateBase;
        }

        public am m1407a(View view, am amVar) {
            int b = amVar.m848b();
            int c = this.f576a.m1480e(b);
            return b != c ? amVar.m847a(amVar.m846a(), c, amVar.m849c(), amVar.m850d()) : amVar;
        }
    }

    /* renamed from: android.support.v7.app.ActionBarActivityDelegateBase.3 */
    class C03043 implements OnFitSystemWindowsListener {
        final /* synthetic */ ActionBarActivityDelegateBase f577a;

        C03043(ActionBarActivityDelegateBase actionBarActivityDelegateBase) {
            this.f577a = actionBarActivityDelegateBase;
        }

        public void onFitSystemWindows(Rect rect) {
            rect.top = this.f577a.m1480e(rect.top);
        }
    }

    /* renamed from: android.support.v7.app.ActionBarActivityDelegateBase.4 */
    class C03054 implements Runnable {
        final /* synthetic */ ActionBarActivityDelegateBase f578a;

        C03054(ActionBarActivityDelegateBase actionBarActivityDelegateBase) {
            this.f578a = actionBarActivityDelegateBase;
        }

        public void run() {
            this.f578a.f615i.showAtLocation(this.f578a.f614h, 55, 0, 0);
        }
    }

    private static final class PanelFeatureState {
        int f582a;
        ViewGroup f583b;
        View f584c;
        MenuBuilder f585d;
        ListMenuPresenter f586e;
        Context f587f;
        boolean f588g;
        boolean f589h;
        boolean f590i;
        public boolean f591j;
        boolean f592k;
        boolean f593l;
        Bundle f594m;

        private static class SavedState implements Parcelable {
            public static final Creator<SavedState> CREATOR;
            int f579a;
            boolean f580b;
            Bundle f581c;

            /* renamed from: android.support.v7.app.ActionBarActivityDelegateBase.PanelFeatureState.SavedState.1 */
            static class C03061 implements Creator<SavedState> {
                C03061() {
                }

                public SavedState m1408a(Parcel parcel) {
                    return SavedState.m1411b(parcel);
                }

                public SavedState[] m1409a(int i) {
                    return new SavedState[i];
                }

                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return m1408a(parcel);
                }

                public /* synthetic */ Object[] newArray(int i) {
                    return m1409a(i);
                }
            }

            static {
                CREATOR = new C03061();
            }

            private SavedState() {
            }

            private static SavedState m1411b(Parcel parcel) {
                boolean z = true;
                SavedState savedState = new SavedState();
                savedState.f579a = parcel.readInt();
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.f580b = z;
                if (savedState.f580b) {
                    savedState.f581c = parcel.readBundle();
                }
                return savedState;
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f579a);
                parcel.writeInt(this.f580b ? 1 : 0);
                if (this.f580b) {
                    parcel.writeBundle(this.f581c);
                }
            }
        }

        PanelFeatureState(int i) {
            this.f582a = i;
            this.f592k = false;
        }

        MenuView m1412a(MenuPresenter.Callback callback) {
            if (this.f585d == null) {
                return null;
            }
            if (this.f586e == null) {
                this.f586e = new ListMenuPresenter(this.f587f, C0297h.abc_list_menu_item_layout);
                this.f586e.setCallback(callback);
                this.f585d.addMenuPresenter(this.f586e);
            }
            return this.f586e.getMenuView(this.f583b);
        }

        void m1413a(Context context) {
            TypedValue typedValue = new TypedValue();
            Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(C0290a.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(C0290a.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            } else {
                newTheme.applyStyle(C0299j.Theme_AppCompat_CompactMenu, true);
            }
            Context contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.f587f = contextThemeWrapper;
        }

        void m1414a(MenuBuilder menuBuilder) {
            if (menuBuilder != this.f585d) {
                if (this.f585d != null) {
                    this.f585d.removeMenuPresenter(this.f586e);
                }
                this.f585d = menuBuilder;
                if (menuBuilder != null && this.f586e != null) {
                    menuBuilder.addMenuPresenter(this.f586e);
                }
            }
        }

        public boolean m1415a() {
            return this.f584c != null && this.f586e.getAdapter().getCount() > 0;
        }
    }

    /* renamed from: android.support.v7.app.ActionBarActivityDelegateBase.a */
    private final class C0307a implements MenuPresenter.Callback {
        final /* synthetic */ ActionBarActivityDelegateBase f595a;

        private C0307a(ActionBarActivityDelegateBase actionBarActivityDelegateBase) {
            this.f595a = actionBarActivityDelegateBase;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            this.f595a.m1470a(menuBuilder);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            C0317b k = this.f595a.m1455k();
            if (k != null) {
                k.onMenuOpened(8, menuBuilder);
            }
            return true;
        }
    }

    /* renamed from: android.support.v7.app.ActionBarActivityDelegateBase.b */
    private class C0309b implements C0308a {
        final /* synthetic */ ActionBarActivityDelegateBase f596a;
        private C0308a f597b;

        public C0309b(ActionBarActivityDelegateBase actionBarActivityDelegateBase, C0308a c0308a) {
            this.f596a = actionBarActivityDelegateBase;
            this.f597b = c0308a;
        }

        public void m1420a(C0320a c0320a) {
            this.f597b.m1416a(c0320a);
            if (this.f596a.f615i != null) {
                this.f596a.a.getWindow().getDecorView().removeCallbacks(this.f596a.f616j);
                this.f596a.f615i.dismiss();
            } else if (this.f596a.f614h != null) {
                this.f596a.f614h.setVisibility(8);
                if (this.f596a.f614h.getParent() != null) {
                    C0234u.m1099m((View) this.f596a.f614h.getParent());
                }
            }
            if (this.f596a.f614h != null) {
                this.f596a.f614h.removeAllViews();
            }
            if (this.f596a.a != null) {
                try {
                    this.f596a.a.m1542b(this.f596a.f613g);
                } catch (AbstractMethodError e) {
                }
            }
            this.f596a.f613g = null;
        }

        public boolean m1421a(C0320a c0320a, Menu menu) {
            return this.f597b.m1417a(c0320a, menu);
        }

        public boolean m1422a(C0320a c0320a, MenuItem menuItem) {
            return this.f597b.m1418a(c0320a, menuItem);
        }

        public boolean m1423b(C0320a c0320a, Menu menu) {
            return this.f597b.m1419b(c0320a, menu);
        }
    }

    /* renamed from: android.support.v7.app.ActionBarActivityDelegateBase.c */
    private final class C0310c implements MenuPresenter.Callback {
        final /* synthetic */ ActionBarActivityDelegateBase f598a;

        private C0310c(ActionBarActivityDelegateBase actionBarActivityDelegateBase) {
            this.f598a = actionBarActivityDelegateBase;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            Menu menu;
            Menu rootMenu = menuBuilder.getRootMenu();
            boolean z2 = rootMenu != menuBuilder;
            ActionBarActivityDelegateBase actionBarActivityDelegateBase = this.f598a;
            if (z2) {
                menu = rootMenu;
            }
            PanelFeatureState a = actionBarActivityDelegateBase.m1461a(menu);
            if (a == null) {
                return;
            }
            if (z2) {
                this.f598a.m1462a(a.f582a, a, rootMenu);
                this.f598a.m1465a(a, true);
                return;
            }
            this.f598a.a.closeOptionsMenu();
            this.f598a.m1465a(a, z);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null && this.f598a.b) {
                C0317b k = this.f598a.m1455k();
                if (!(k == null || this.f598a.m1457m())) {
                    k.onMenuOpened(8, menuBuilder);
                }
            }
            return true;
        }
    }

    ActionBarActivityDelegateBase(C0316b c0316b) {
        super(c0316b);
        this.f632z = new C03021(this);
    }

    private PanelFeatureState m1459a(int i, boolean z) {
        Object obj = this.f628v;
        if (obj == null || obj.length <= i) {
            Object obj2 = new PanelFeatureState[(i + 1)];
            if (obj != null) {
                System.arraycopy(obj, 0, obj2, 0, obj.length);
            }
            this.f628v = obj2;
            obj = obj2;
        }
        PanelFeatureState panelFeatureState = obj[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        panelFeatureState = new PanelFeatureState(i);
        obj[i] = panelFeatureState;
        return panelFeatureState;
    }

    private PanelFeatureState m1461a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.f628v;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i = 0; i < length; i++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.f585d == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    private void m1462a(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0 && i < this.f628v.length) {
                panelFeatureState = this.f628v[i];
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.f585d;
            }
        }
        if (panelFeatureState == null || panelFeatureState.f590i) {
            m1455k().onPanelClosed(i, menu);
        }
    }

    private void m1463a(PanelFeatureState panelFeatureState) {
        panelFeatureState.f583b = this.f621o;
        panelFeatureState.m1413a(m1454j());
    }

    private void m1464a(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (!panelFeatureState.f590i && !m1457m()) {
            if (panelFeatureState.f582a == 0) {
                Context context = this.a;
                boolean z = (context.getResources().getConfiguration().screenLayout & 15) == 4;
                boolean z2 = context.getApplicationInfo().targetSdkVersion >= 11;
                if (z && z2) {
                    return;
                }
            }
            C0317b k = m1455k();
            if (k != null && !k.onMenuOpened(panelFeatureState.f582a, panelFeatureState.f585d)) {
                m1465a(panelFeatureState, true);
            } else if (m1475b(panelFeatureState, keyEvent)) {
                if (panelFeatureState.f583b == null || panelFeatureState.f592k) {
                    m1463a(panelFeatureState);
                }
                if (m1478c(panelFeatureState) && panelFeatureState.m1415a()) {
                    panelFeatureState.f589h = false;
                    panelFeatureState.f590i = true;
                }
            }
        }
    }

    private void m1465a(PanelFeatureState panelFeatureState, boolean z) {
        if (z && panelFeatureState.f582a == 0 && this.f617k != null && this.f617k.isOverflowMenuShowing()) {
            m1470a(panelFeatureState.f585d);
            return;
        }
        if (panelFeatureState.f590i && z) {
            m1462a(panelFeatureState.f582a, panelFeatureState, null);
        }
        panelFeatureState.f588g = false;
        panelFeatureState.f589h = false;
        panelFeatureState.f590i = false;
        panelFeatureState.f584c = null;
        panelFeatureState.f592k = true;
        if (this.f629w == panelFeatureState) {
            this.f629w = null;
        }
    }

    private void m1470a(MenuBuilder menuBuilder) {
        if (!this.f627u) {
            this.f627u = true;
            this.f617k.dismissPopups();
            C0317b k = m1455k();
            if (!(k == null || m1457m())) {
                k.onPanelClosed(8, menuBuilder);
            }
            this.f627u = false;
        }
    }

    private void m1471a(MenuBuilder menuBuilder, boolean z) {
        if (this.f617k == null || !this.f617k.canShowOverflowMenu() || (ac.m782b(ViewConfiguration.get(this.a)) && !this.f617k.isOverflowMenuShowPending())) {
            PanelFeatureState a = m1459a(0, true);
            a.f592k = true;
            m1465a(a, false);
            m1464a(a, null);
            return;
        }
        C0317b k = m1455k();
        if (this.f617k.isOverflowMenuShowing() && z) {
            this.f617k.hideOverflowMenu();
            if (!m1457m()) {
                this.a.onPanelClosed(8, m1459a(0, true).f585d);
            }
        } else if (k != null && !m1457m()) {
            if (this.f630x && (this.f631y & 1) != 0) {
                this.f621o.removeCallbacks(this.f632z);
                this.f632z.run();
            }
            PanelFeatureState a2 = m1459a(0, true);
            if (a2.f585d != null && !a2.f593l && k.onPreparePanel(0, null, a2.f585d)) {
                k.onMenuOpened(8, a2.f585d);
                this.f617k.showOverflowMenu();
            }
        }
    }

    private boolean m1474b(PanelFeatureState panelFeatureState) {
        Context contextThemeWrapper;
        MenuBuilder menuBuilder;
        Context context = this.a;
        if ((panelFeatureState.f582a == 0 || panelFeatureState.f582a == 8) && this.f617k != null) {
            TypedValue typedValue = new TypedValue();
            Theme theme = context.getTheme();
            theme.resolveAttribute(C0290a.actionBarTheme, typedValue, true);
            Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(C0290a.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(C0290a.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            Theme theme3 = theme2;
            if (theme3 != null) {
                contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme3);
                menuBuilder = new MenuBuilder(contextThemeWrapper);
                menuBuilder.setCallback(this);
                panelFeatureState.m1414a(menuBuilder);
                return true;
            }
        }
        contextThemeWrapper = context;
        menuBuilder = new MenuBuilder(contextThemeWrapper);
        menuBuilder.setCallback(this);
        panelFeatureState.m1414a(menuBuilder);
        return true;
    }

    private boolean m1475b(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (m1457m()) {
            return false;
        }
        if (panelFeatureState.f588g) {
            return true;
        }
        if (!(this.f629w == null || this.f629w == panelFeatureState)) {
            m1465a(this.f629w, false);
        }
        boolean z = panelFeatureState.f582a == 0 || panelFeatureState.f582a == 8;
        if (z && this.f617k != null) {
            this.f617k.setMenuPrepared();
        }
        if (panelFeatureState.f585d == null || panelFeatureState.f593l) {
            if (panelFeatureState.f585d == null && (!m1474b(panelFeatureState) || panelFeatureState.f585d == null)) {
                return false;
            }
            if (z && this.f617k != null) {
                if (this.f618l == null) {
                    this.f618l = new C0307a();
                }
                this.f617k.setMenu(panelFeatureState.f585d, this.f618l);
            }
            panelFeatureState.f585d.stopDispatchingItemsChanged();
            if (m1455k().onCreatePanelMenu(panelFeatureState.f582a, panelFeatureState.f585d)) {
                panelFeatureState.f593l = false;
            } else {
                panelFeatureState.m1414a(null);
                if (!z || this.f617k == null) {
                    return false;
                }
                this.f617k.setMenu(null, this.f618l);
                return false;
            }
        }
        panelFeatureState.f585d.stopDispatchingItemsChanged();
        if (panelFeatureState.f594m != null) {
            panelFeatureState.f585d.restoreActionViewStates(panelFeatureState.f594m);
            panelFeatureState.f594m = null;
        }
        if (m1455k().onPreparePanel(0, null, panelFeatureState.f585d)) {
            panelFeatureState.f591j = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.f585d.setQwertyMode(panelFeatureState.f591j);
            panelFeatureState.f585d.startDispatchingItemsChanged();
            panelFeatureState.f588g = true;
            panelFeatureState.f589h = false;
            this.f629w = panelFeatureState;
            return true;
        }
        if (z && this.f617k != null) {
            this.f617k.setMenu(null, this.f618l);
        }
        panelFeatureState.f585d.startDispatchingItemsChanged();
        return false;
    }

    private void m1477c(int i) {
        this.f631y |= 1 << i;
        if (!this.f630x && this.f621o != null) {
            C0234u.m1080a(this.f621o, this.f632z);
            this.f630x = true;
        }
    }

    private boolean m1478c(PanelFeatureState panelFeatureState) {
        if (panelFeatureState.f585d == null) {
            return false;
        }
        if (this.f619m == null) {
            this.f619m = new C0310c();
        }
        panelFeatureState.f584c = (View) panelFeatureState.m1412a(this.f619m);
        return panelFeatureState.f584c != null;
    }

    private void m1479d(int i) {
        PanelFeatureState a = m1459a(i, true);
        if (a.f585d != null) {
            Bundle bundle = new Bundle();
            a.f585d.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                a.f594m = bundle;
            }
            a.f585d.stopDispatchingItemsChanged();
            a.f585d.clear();
        }
        a.f593l = true;
        a.f592k = true;
        if ((i == 8 || i == 0) && this.f617k != null) {
            a = m1459a(0, false);
            if (a != null) {
                a.f588g = false;
                m1475b(a, null);
            }
        }
    }

    private int m1480e(int i) {
        int i2;
        int i3 = 1;
        int i4 = 0;
        if (this.f614h == null || !(this.f614h.getLayoutParams() instanceof MarginLayoutParams)) {
            i2 = 0;
        } else {
            int i5;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f614h.getLayoutParams();
            if (this.f614h.isShown()) {
                if (this.f611C == null) {
                    this.f611C = new Rect();
                    this.f612D = new Rect();
                }
                Rect rect = this.f611C;
                Rect rect2 = this.f612D;
                rect.set(0, i, 0, 0);
                ViewUtils.computeFitSystemWindows(this.f622p, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    if (this.f623q == null) {
                        this.f623q = new View(this.a);
                        this.f623q.setBackgroundColor(this.a.getResources().getColor(C0292c.abc_input_method_navigation_guard));
                        this.f622p.addView(this.f623q, -1, new LayoutParams(-1, i));
                        i5 = 1;
                    } else {
                        LayoutParams layoutParams = this.f623q.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.f623q.setLayoutParams(layoutParams);
                        }
                        i5 = 1;
                    }
                } else {
                    i5 = 0;
                }
                if (this.f623q == null) {
                    i3 = 0;
                }
                if (!(this.d || i3 == 0)) {
                    i = 0;
                }
                int i6 = i5;
                i5 = i3;
                i3 = i6;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                i5 = 0;
            } else {
                i3 = 0;
                i5 = 0;
            }
            if (i3 != 0) {
                this.f614h.setLayoutParams(marginLayoutParams);
            }
            i2 = i5;
        }
        if (this.f623q != null) {
            View view = this.f623q;
            if (i2 == 0) {
                i4 = 8;
            }
            view.setVisibility(i4);
        }
        return i;
    }

    private void m1481p() {
        TypedValue typedValue;
        TypedValue typedValue2;
        TypedValue typedValue3;
        int dimension;
        int dimension2;
        TypedValue typedValue4 = null;
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(C0300k.Theme);
        if (obtainStyledAttributes.hasValue(C0300k.Theme_windowFixedWidthMajor)) {
            typedValue = null == null ? new TypedValue() : null;
            obtainStyledAttributes.getValue(C0300k.Theme_windowFixedWidthMajor, typedValue);
        } else {
            typedValue = null;
        }
        if (obtainStyledAttributes.hasValue(C0300k.Theme_windowFixedWidthMinor)) {
            typedValue2 = null == null ? new TypedValue() : null;
            obtainStyledAttributes.getValue(C0300k.Theme_windowFixedWidthMinor, typedValue2);
        } else {
            typedValue2 = null;
        }
        if (obtainStyledAttributes.hasValue(C0300k.Theme_windowFixedHeightMajor)) {
            typedValue3 = null == null ? new TypedValue() : null;
            obtainStyledAttributes.getValue(C0300k.Theme_windowFixedHeightMajor, typedValue3);
        } else {
            typedValue3 = null;
        }
        if (obtainStyledAttributes.hasValue(C0300k.Theme_windowFixedHeightMinor)) {
            if (null == null) {
                typedValue4 = new TypedValue();
            }
            obtainStyledAttributes.getValue(C0300k.Theme_windowFixedHeightMinor, typedValue4);
        }
        DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
        Object obj = displayMetrics.widthPixels < displayMetrics.heightPixels ? 1 : null;
        if (obj == null) {
            typedValue2 = typedValue;
        }
        if (!(typedValue2 == null || typedValue2.type == 0)) {
            if (typedValue2.type == 5) {
                dimension = (int) typedValue2.getDimension(displayMetrics);
            } else if (typedValue2.type == 6) {
                dimension = (int) typedValue2.getFraction((float) displayMetrics.widthPixels, (float) displayMetrics.widthPixels);
            }
            if (obj == null) {
                typedValue3 = typedValue4;
            }
            if (!(typedValue3 == null || typedValue3.type == 0)) {
                if (typedValue3.type != 5) {
                    dimension2 = (int) typedValue3.getDimension(displayMetrics);
                } else if (typedValue3.type == 6) {
                    dimension2 = (int) typedValue3.getFraction((float) displayMetrics.heightPixels, (float) displayMetrics.heightPixels);
                }
                if (!(dimension == -1 && dimension2 == -1)) {
                    this.a.getWindow().setLayout(dimension, dimension2);
                }
                obtainStyledAttributes.recycle();
            }
            dimension2 = -1;
            this.a.getWindow().setLayout(dimension, dimension2);
            obtainStyledAttributes.recycle();
        }
        dimension = -1;
        if (obj == null) {
            typedValue3 = typedValue4;
        }
        if (typedValue3.type != 5) {
            if (typedValue3.type == 6) {
                dimension2 = (int) typedValue3.getFraction((float) displayMetrics.heightPixels, (float) displayMetrics.heightPixels);
            }
            dimension2 = -1;
        } else {
            dimension2 = (int) typedValue3.getDimension(displayMetrics);
        }
        this.a.getWindow().setLayout(dimension, dimension2);
        obtainStyledAttributes.recycle();
    }

    private void m1482q() {
        if (this.f610B == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(C0290a.panelMenuListTheme, typedValue, true);
            this.f610B = new ListMenuPresenter(new ContextThemeWrapper(this.a, typedValue.resourceId != 0 ? typedValue.resourceId : C0299j.Theme_AppCompat_CompactMenu), C0297h.abc_list_menu_item_layout);
        }
    }

    public C0315a m1483a() {
        m1508n();
        C0315a c0333c = new C0333c(this.a, this.c);
        c0333c.m1524c(this.f609A);
        return c0333c;
    }

    C0320a m1484a(C0308a c0308a) {
        if (this.f613g != null) {
            this.f613g.m1559c();
        }
        C0308a c0309b = new C0309b(this, c0308a);
        Context j = m1454j();
        if (this.f614h == null) {
            if (this.e) {
                this.f614h = new ActionBarContextView(j);
                this.f615i = new PopupWindow(j, null, C0290a.actionModePopupWindowStyle);
                this.f615i.setContentView(this.f614h);
                this.f615i.setWidth(-1);
                TypedValue typedValue = new TypedValue();
                this.a.getTheme().resolveAttribute(C0290a.actionBarSize, typedValue, true);
                this.f614h.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, this.a.getResources().getDisplayMetrics()));
                this.f615i.setHeight(-2);
                this.f616j = new C03054(this);
            } else {
                ViewStubCompat viewStubCompat = (ViewStubCompat) this.a.findViewById(C0295f.action_mode_bar_stub);
                if (viewStubCompat != null) {
                    viewStubCompat.setLayoutInflater(LayoutInflater.from(j));
                    this.f614h = (ActionBarContextView) viewStubCompat.inflate();
                }
            }
        }
        if (this.f614h != null) {
            this.f614h.killMode();
            C0320a c0338b = new C0338b(j, this.f614h, c0309b, this.f615i == null);
            if (c0308a.m1417a(c0338b, c0338b.m1556b())) {
                c0338b.m1560d();
                this.f614h.initForMode(c0338b);
                this.f614h.setVisibility(0);
                this.f613g = c0338b;
                if (this.f615i != null) {
                    this.a.getWindow().getDecorView().post(this.f616j);
                }
                this.f614h.sendAccessibilityEvent(32);
                if (this.f614h.getParent() != null) {
                    C0234u.m1099m((View) this.f614h.getParent());
                }
            } else {
                this.f613g = null;
            }
        }
        if (!(this.f613g == null || this.a == null)) {
            this.a.m1532a(this.f613g);
        }
        return this.f613g;
    }

    View m1485a(String str, Context context, AttributeSet attributeSet) {
        if (VERSION.SDK_INT < 21) {
            Object obj = -1;
            switch (str.hashCode()) {
                case -1455429095:
                    if (str.equals("CheckedTextView")) {
                        obj = 4;
                        break;
                    }
                    break;
                case -339785223:
                    if (str.equals("Spinner")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 776382189:
                    if (str.equals("RadioButton")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 1601505219:
                    if (str.equals("CheckBox")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1666676343:
                    if (str.equals("EditText")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case C0627j.View_android_focusable /*0*/:
                    return new TintEditText(context, attributeSet);
                case C0627j.View_paddingStart /*1*/:
                    return new TintSpinner(context, attributeSet);
                case C0627j.View_paddingEnd /*2*/:
                    return new TintCheckBox(context, attributeSet);
                case C0627j.Toolbar_subtitle /*3*/:
                    return new TintRadioButton(context, attributeSet);
                case C0627j.Toolbar_contentInsetStart /*4*/:
                    return new TintCheckedTextView(context, attributeSet);
            }
        }
        return null;
    }

    public void m1486a(int i) {
        m1508n();
        ViewGroup viewGroup = (ViewGroup) this.a.findViewById(16908290);
        viewGroup.removeAllViews();
        this.a.getLayoutInflater().inflate(i, viewGroup);
        this.a.m1547i();
    }

    public void m1487a(int i, Menu menu) {
        PanelFeatureState a = m1459a(i, false);
        if (a != null) {
            m1465a(a, false);
        }
        if (i == 8) {
            C0315a b = m1441b();
            if (b != null) {
                b.m1528e(false);
            }
        } else if (!m1457m()) {
            this.a.m1539b(i, menu);
        }
    }

    public void m1488a(Configuration configuration) {
        if (this.b && this.f620n) {
            C0315a b = m1441b();
            if (b != null) {
                b.m1519a(configuration);
            }
        }
    }

    void m1489a(Bundle bundle) {
        super.m1431a(bundle);
        this.f621o = (ViewGroup) this.a.getWindow().getDecorView();
        if (C0059o.m325b(this.a) != null) {
            C0315a c = m1446c();
            if (c == null) {
                this.f609A = true;
            } else {
                c.m1524c(true);
            }
        }
    }

    void m1490a(Toolbar toolbar) {
        C0315a b = m1441b();
        if (b instanceof C0333c) {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
        if (b instanceof C0328a) {
            ((C0328a) b).m1582a(null);
        }
        b = new C0328a(toolbar, this.a.getTitle(), this.a.getWindow(), this.f);
        m1482q();
        b.m1582a(this.f610B);
        m1432a(b);
        m1433a(b.m1590e());
        b.m1587c();
    }

    public void m1491a(View view) {
        m1508n();
        ViewGroup viewGroup = (ViewGroup) this.a.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.a.m1547i();
    }

    public void m1492a(View view, LayoutParams layoutParams) {
        m1508n();
        ViewGroup viewGroup = (ViewGroup) this.a.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.a.m1547i();
    }

    public void m1493a(CharSequence charSequence) {
        if (this.f617k != null) {
            this.f617k.setWindowTitle(charSequence);
        } else if (m1441b() != null) {
            m1441b().m1520a(charSequence);
        } else {
            this.f624r = charSequence;
        }
    }

    boolean m1494a(int i, KeyEvent keyEvent) {
        return m1500b(i, keyEvent);
    }

    public boolean m1495a(int i, View view, Menu menu) {
        return i != 0 ? m1455k().onPreparePanel(i, view, menu) : false;
    }

    final boolean m1496a(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        boolean z = false;
        if (!keyEvent.isSystem()) {
            if ((panelFeatureState.f588g || m1475b(panelFeatureState, keyEvent)) && panelFeatureState.f585d != null) {
                z = panelFeatureState.f585d.performShortcut(i, keyEvent, i2);
            }
            if (z && (i2 & 1) == 0 && this.f617k == null) {
                m1465a(panelFeatureState, true);
            }
        }
        return z;
    }

    public C0320a m1497b(C0308a c0308a) {
        if (c0308a == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.f613g != null) {
            this.f613g.m1559c();
        }
        C0308a c0309b = new C0309b(this, c0308a);
        C0315a b = m1441b();
        if (b != null) {
            this.f613g = b.m1517a(c0309b);
            if (this.f613g != null) {
                this.a.m1532a(this.f613g);
            }
        }
        if (this.f613g == null) {
            this.f613g = m1484a(c0309b);
        }
        return this.f613g;
    }

    public View m1498b(int i) {
        if (this.f613g != null) {
            return null;
        }
        C0317b k = m1455k();
        View onCreatePanelView = k != null ? k.onCreatePanelView(i) : null;
        if (onCreatePanelView != null || this.f610B != null) {
            return onCreatePanelView;
        }
        PanelFeatureState a = m1459a(i, true);
        m1464a(a, null);
        return a.f590i ? a.f584c : onCreatePanelView;
    }

    public void m1499b(View view, LayoutParams layoutParams) {
        m1508n();
        ((ViewGroup) this.a.findViewById(16908290)).addView(view, layoutParams);
        this.a.m1547i();
    }

    boolean m1500b(int i, KeyEvent keyEvent) {
        if (this.f629w == null || !m1496a(this.f629w, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.f629w == null) {
                PanelFeatureState a = m1459a(0, true);
                m1475b(a, keyEvent);
                boolean a2 = m1496a(a, keyEvent.getKeyCode(), keyEvent, 1);
                a.f588g = false;
                if (a2) {
                    return true;
                }
            }
            return false;
        } else if (this.f629w == null) {
            return true;
        } else {
            this.f629w.f589h = true;
            return true;
        }
    }

    boolean m1501b(int i, Menu menu) {
        if (i != 8) {
            return this.a.m1544c(i, menu);
        }
        C0315a b = m1441b();
        if (b == null) {
            return true;
        }
        b.m1528e(true);
        return true;
    }

    public boolean m1502c(int i, Menu menu) {
        return i != 0 ? m1455k().onCreatePanelMenu(i, menu) : false;
    }

    public void m1503e() {
        C0315a b = m1441b();
        if (b != null) {
            b.m1526d(false);
        }
    }

    public void m1504f() {
        C0315a b = m1441b();
        if (b != null) {
            b.m1526d(true);
        }
    }

    public void m1505g() {
        C0315a b = m1441b();
        if (b == null || !b.m1525c()) {
            m1477c(0);
        }
    }

    public boolean m1506h() {
        if (this.f613g != null) {
            this.f613g.m1559c();
            return true;
        }
        C0315a b = m1441b();
        return b != null && b.m1527d();
    }

    public void m1507i() {
    }

    final void m1508n() {
        if (!this.f620n) {
            if (this.b) {
                TypedValue typedValue = new TypedValue();
                this.a.getTheme().resolveAttribute(C0290a.actionBarTheme, typedValue, true);
                this.f622p = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new ContextThemeWrapper(this.a, typedValue.resourceId) : this.a).inflate(C0297h.abc_screen_toolbar, null);
                this.f617k = (DecorContentParent) this.f622p.findViewById(C0295f.decor_content_parent);
                this.f617k.setWindowCallback(m1455k());
                if (this.c) {
                    this.f617k.initFeature(9);
                }
                if (this.f625s) {
                    this.f617k.initFeature(2);
                }
                if (this.f626t) {
                    this.f617k.initFeature(5);
                }
            } else {
                if (this.d) {
                    this.f622p = (ViewGroup) LayoutInflater.from(this.a).inflate(C0297h.abc_screen_simple_overlay_action_mode, null);
                } else {
                    this.f622p = (ViewGroup) LayoutInflater.from(this.a).inflate(C0297h.abc_screen_simple, null);
                }
                if (VERSION.SDK_INT >= 21) {
                    C0234u.m1079a(this.f622p, new C03032(this));
                } else {
                    ((FitWindowsViewGroup) this.f622p).setOnFitSystemWindowsListener(new C03043(this));
                }
            }
            ViewUtils.makeOptionalFitsSystemWindows(this.f622p);
            this.a.m1534a(this.f622p);
            View findViewById = this.a.findViewById(16908290);
            findViewById.setId(-1);
            this.a.findViewById(C0295f.action_bar_activity_content).setId(16908290);
            if (findViewById instanceof FrameLayout) {
                ((FrameLayout) findViewById).setForeground(null);
            }
            if (!(this.f624r == null || this.f617k == null)) {
                this.f617k.setWindowTitle(this.f624r);
                this.f624r = null;
            }
            m1481p();
            m1509o();
            this.f620n = true;
            PanelFeatureState a = m1459a(0, false);
            if (!m1457m()) {
                if (a == null || a.f585d == null) {
                    m1477c(8);
                }
            }
        }
    }

    void m1509o() {
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        C0317b k = m1455k();
        if (!(k == null || m1457m())) {
            PanelFeatureState a = m1461a(menuBuilder.getRootMenu());
            if (a != null) {
                return k.onMenuItemSelected(a.f582a, menuItem);
            }
        }
        return false;
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        m1471a(menuBuilder, true);
    }
}
