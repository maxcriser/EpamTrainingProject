package android.support.v7.internal.p011a;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.app.C0037f;
import android.support.v4.view.C0234u;
import android.support.v4.view.af;
import android.support.v4.view.aj;
import android.support.v4.view.ak;
import android.support.v4.view.al;
import android.support.v7.app.C0315a;
import android.support.v7.app.C0315a.C0313b;
import android.support.v7.app.C0316b;
import android.support.v7.internal.view.C0337a;
import android.support.v7.internal.view.C0343d;
import android.support.v7.internal.view.C0345e;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.internal.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0295f;
import android.support.v7.p009a.C0301a.C0300k;
import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: android.support.v7.internal.a.c */
public class C0333c extends C0315a implements ActionBarVisibilityCallback {
    static final /* synthetic */ boolean f663h;
    private static final boolean f664i;
    private int f665A;
    private boolean f666B;
    private boolean f667C;
    private boolean f668D;
    private boolean f669E;
    private boolean f670F;
    private C0345e f671G;
    private boolean f672H;
    C0332a f673a;
    C0320a f674b;
    C0308a f675c;
    boolean f676d;
    final aj f677e;
    final aj f678f;
    final al f679g;
    private Context f680j;
    private Context f681k;
    private C0037f f682l;
    private ActionBarOverlayLayout f683m;
    private ActionBarContainer f684n;
    private DecorToolbar f685o;
    private ActionBarContextView f686p;
    private ActionBarContainer f687q;
    private View f688r;
    private ScrollingTabContainerView f689s;
    private ArrayList<Object> f690t;
    private int f691u;
    private boolean f692v;
    private boolean f693w;
    private ArrayList<C0313b> f694x;
    private int f695y;
    private boolean f696z;

    /* renamed from: android.support.v7.internal.a.c.1 */
    class C03291 extends ak {
        final /* synthetic */ C0333c f656a;

        C03291(C0333c c0333c) {
            this.f656a = c0333c;
        }

        public void onAnimationEnd(View view) {
            if (this.f656a.f666B && this.f656a.f688r != null) {
                C0234u.m1084b(this.f656a.f688r, 0.0f);
                C0234u.m1084b(this.f656a.f684n, 0.0f);
            }
            if (this.f656a.f687q != null && this.f656a.f695y == 1) {
                this.f656a.f687q.setVisibility(8);
            }
            this.f656a.f684n.setVisibility(8);
            this.f656a.f684n.setTransitioning(false);
            this.f656a.f671G = null;
            this.f656a.m1641e();
            if (this.f656a.f683m != null) {
                C0234u.m1099m(this.f656a.f683m);
            }
        }
    }

    /* renamed from: android.support.v7.internal.a.c.2 */
    class C03302 extends ak {
        final /* synthetic */ C0333c f657a;

        C03302(C0333c c0333c) {
            this.f657a = c0333c;
        }

        public void onAnimationEnd(View view) {
            this.f657a.f671G = null;
            this.f657a.f684n.requestLayout();
        }
    }

    /* renamed from: android.support.v7.internal.a.c.3 */
    class C03313 implements al {
        final /* synthetic */ C0333c f658a;

        C03313(C0333c c0333c) {
            this.f658a = c0333c;
        }

        public void m1593a(View view) {
            ((View) this.f658a.f684n.getParent()).invalidate();
        }
    }

    /* renamed from: android.support.v7.internal.a.c.a */
    public class C0332a extends C0320a implements Callback {
        final /* synthetic */ C0333c f659a;
        private C0308a f660b;
        private MenuBuilder f661c;
        private WeakReference<View> f662d;

        public C0332a(C0333c c0333c, C0308a c0308a) {
            this.f659a = c0333c;
            this.f660b = c0308a;
            this.f661c = new MenuBuilder(c0333c.m1636b()).setDefaultShowAsAction(1);
            this.f661c.setCallback(this);
        }

        public MenuInflater m1594a() {
            return new C0343d(this.f659a.m1636b());
        }

        public void m1595a(int i) {
            m1601b(this.f659a.f680j.getResources().getString(i));
        }

        public void m1596a(View view) {
            this.f659a.f686p.setCustomView(view);
            this.f662d = new WeakReference(view);
        }

        public void m1597a(CharSequence charSequence) {
            this.f659a.f686p.setSubtitle(charSequence);
        }

        public void m1598a(boolean z) {
            super.m1555a(z);
            this.f659a.f686p.setTitleOptional(z);
        }

        public Menu m1599b() {
            return this.f661c;
        }

        public void m1600b(int i) {
            m1597a(this.f659a.f680j.getResources().getString(i));
        }

        public void m1601b(CharSequence charSequence) {
            this.f659a.f686p.setTitle(charSequence);
        }

        public void m1602c() {
            if (this.f659a.f673a == this) {
                if (C0333c.m1615b(this.f659a.f667C, this.f659a.f668D, false)) {
                    this.f660b.m1416a(this);
                } else {
                    this.f659a.f674b = this;
                    this.f659a.f675c = this.f660b;
                }
                this.f660b = null;
                this.f659a.m1647i(false);
                this.f659a.f686p.closeMode();
                this.f659a.f685o.getViewGroup().sendAccessibilityEvent(32);
                this.f659a.f683m.setHideOnContentScrollEnabled(this.f659a.f676d);
                this.f659a.f673a = null;
            }
        }

        public void m1603d() {
            this.f661c.stopDispatchingItemsChanged();
            try {
                this.f660b.m1419b(this, this.f661c);
            } finally {
                this.f661c.startDispatchingItemsChanged();
            }
        }

        public boolean m1604e() {
            this.f661c.stopDispatchingItemsChanged();
            try {
                boolean a = this.f660b.m1417a((C0320a) this, this.f661c);
                return a;
            } finally {
                this.f661c.startDispatchingItemsChanged();
            }
        }

        public CharSequence m1605f() {
            return this.f659a.f686p.getTitle();
        }

        public CharSequence m1606g() {
            return this.f659a.f686p.getSubtitle();
        }

        public boolean m1607h() {
            return this.f659a.f686p.isTitleOptional();
        }

        public View m1608i() {
            return this.f662d != null ? (View) this.f662d.get() : null;
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return this.f660b != null ? this.f660b.m1418a((C0320a) this, menuItem) : false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.f660b != null) {
                m1603d();
                this.f659a.f686p.showOverflowMenu();
            }
        }
    }

    static {
        boolean z = true;
        f663h = !C0333c.class.desiredAssertionStatus();
        if (VERSION.SDK_INT < 14) {
            z = false;
        }
        f664i = z;
    }

    public C0333c(C0316b c0316b, boolean z) {
        this.f690t = new ArrayList();
        this.f691u = -1;
        this.f694x = new ArrayList();
        this.f665A = 0;
        this.f666B = true;
        this.f670F = true;
        this.f677e = new C03291(this);
        this.f678f = new C03302(this);
        this.f679g = new C03313(this);
        this.f682l = c0316b;
        View decorView = c0316b.getWindow().getDecorView();
        m1610a(decorView);
        if (!z) {
            this.f688r = decorView.findViewById(16908290);
        }
    }

    private void m1610a(View view) {
        this.f683m = (ActionBarOverlayLayout) view.findViewById(C0295f.decor_content_parent);
        if (this.f683m != null) {
            this.f683m.setActionBarVisibilityCallback(this);
        }
        this.f685o = m1613b(view.findViewById(C0295f.action_bar));
        this.f686p = (ActionBarContextView) view.findViewById(C0295f.action_context_bar);
        this.f684n = (ActionBarContainer) view.findViewById(C0295f.action_bar_container);
        this.f687q = (ActionBarContainer) view.findViewById(C0295f.split_action_bar);
        if (this.f685o == null || this.f686p == null || this.f684n == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.f680j = this.f685o.getContext();
        this.f695y = this.f685o.isSplit() ? 1 : 0;
        boolean z = (this.f685o.getDisplayOptions() & 4) != 0;
        if (z) {
            this.f692v = true;
        }
        C0337a a = C0337a.m1650a(this.f680j);
        z = a.m1656f() || z;
        m1635a(z);
        m1626j(a.m1654d());
        TypedArray obtainStyledAttributes = this.f680j.obtainStyledAttributes(null, C0300k.ActionBar, C0290a.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(C0300k.ActionBar_hideOnContentScroll, false)) {
            m1637b(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C0300k.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            m1631a((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private DecorToolbar m1613b(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private static boolean m1615b(boolean z, boolean z2, boolean z3) {
        return z3 ? true : (z || z2) ? false : true;
    }

    private void m1620g() {
        if (!this.f669E) {
            this.f669E = true;
            if (this.f683m != null) {
                this.f683m.setShowingForActionMode(true);
            }
            m1628k(false);
        }
    }

    private void m1622h() {
        if (this.f669E) {
            this.f669E = false;
            if (this.f683m != null) {
                this.f683m.setShowingForActionMode(false);
            }
            m1628k(false);
        }
    }

    private void m1626j(boolean z) {
        boolean z2 = true;
        this.f696z = z;
        if (this.f696z) {
            this.f684n.setTabContainer(null);
            this.f685o.setEmbeddedTabView(this.f689s);
        } else {
            this.f685o.setEmbeddedTabView(null);
            this.f684n.setTabContainer(this.f689s);
        }
        boolean z3 = m1643f() == 2;
        if (this.f689s != null) {
            if (z3) {
                this.f689s.setVisibility(0);
                if (this.f683m != null) {
                    C0234u.m1099m(this.f683m);
                }
            } else {
                this.f689s.setVisibility(8);
            }
        }
        DecorToolbar decorToolbar = this.f685o;
        boolean z4 = !this.f696z && z3;
        decorToolbar.setCollapsible(z4);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f683m;
        if (this.f696z || !z3) {
            z2 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z2);
    }

    private void m1628k(boolean z) {
        if (C0333c.m1615b(this.f667C, this.f668D, this.f669E)) {
            if (!this.f670F) {
                this.f670F = true;
                m1645g(z);
            }
        } else if (this.f670F) {
            this.f670F = false;
            m1646h(z);
        }
    }

    public int m1629a() {
        return this.f685o.getDisplayOptions();
    }

    public C0320a m1630a(C0308a c0308a) {
        if (this.f673a != null) {
            this.f673a.m1602c();
        }
        this.f683m.setHideOnContentScrollEnabled(false);
        this.f686p.killMode();
        C0320a c0332a = new C0332a(this, c0308a);
        if (!c0332a.m1604e()) {
            return null;
        }
        c0332a.m1603d();
        this.f686p.initForMode(c0332a);
        m1647i(true);
        if (!(this.f687q == null || this.f695y != 1 || this.f687q.getVisibility() == 0)) {
            this.f687q.setVisibility(0);
            if (this.f683m != null) {
                C0234u.m1099m(this.f683m);
            }
        }
        this.f686p.sendAccessibilityEvent(32);
        this.f673a = c0332a;
        return c0332a;
    }

    public void m1631a(float f) {
        C0234u.m1091e(this.f684n, f);
        if (this.f687q != null) {
            C0234u.m1091e(this.f687q, f);
        }
    }

    public void m1632a(int i, int i2) {
        int displayOptions = this.f685o.getDisplayOptions();
        if ((i2 & 4) != 0) {
            this.f692v = true;
        }
        this.f685o.setDisplayOptions((displayOptions & (i2 ^ -1)) | (i & i2));
    }

    public void m1633a(Configuration configuration) {
        m1626j(C0337a.m1650a(this.f680j).m1654d());
    }

    public void m1634a(CharSequence charSequence) {
        this.f685o.setWindowTitle(charSequence);
    }

    public void m1635a(boolean z) {
        this.f685o.setHomeButtonEnabled(z);
    }

    public Context m1636b() {
        if (this.f681k == null) {
            TypedValue typedValue = new TypedValue();
            this.f680j.getTheme().resolveAttribute(C0290a.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f681k = new ContextThemeWrapper(this.f680j, i);
            } else {
                this.f681k = this.f680j;
            }
        }
        return this.f681k;
    }

    public void m1637b(boolean z) {
        if (!z || this.f683m.isInOverlayMode()) {
            this.f676d = z;
            this.f683m.setHideOnContentScrollEnabled(z);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    public void m1638c(boolean z) {
        if (!this.f692v) {
            m1644f(z);
        }
    }

    public void m1639d(boolean z) {
        this.f672H = z;
        if (!z && this.f671G != null) {
            this.f671G.m1707b();
        }
    }

    public boolean m1640d() {
        if (this.f685o == null || !this.f685o.hasExpandedActionView()) {
            return false;
        }
        this.f685o.collapseActionView();
        return true;
    }

    void m1641e() {
        if (this.f675c != null) {
            this.f675c.m1416a(this.f674b);
            this.f674b = null;
            this.f675c = null;
        }
    }

    public void m1642e(boolean z) {
        if (z != this.f693w) {
            this.f693w = z;
            int size = this.f694x.size();
            for (int i = 0; i < size; i++) {
                ((C0313b) this.f694x.get(i)).m1510a(z);
            }
        }
    }

    public void enableContentAnimations(boolean z) {
        this.f666B = z;
    }

    public int m1643f() {
        return this.f685o.getNavigationMode();
    }

    public void m1644f(boolean z) {
        m1632a(z ? 4 : 0, 4);
    }

    public void m1645g(boolean z) {
        if (this.f671G != null) {
            this.f671G.m1707b();
        }
        this.f684n.setVisibility(0);
        if (this.f665A == 0 && f664i && (this.f672H || z)) {
            C0234u.m1084b(this.f684n, 0.0f);
            float f = (float) (-this.f684n.getHeight());
            if (z) {
                int[] iArr = new int[]{0, 0};
                this.f684n.getLocationInWindow(iArr);
                f -= (float) iArr[1];
            }
            C0234u.m1084b(this.f684n, f);
            C0345e c0345e = new C0345e();
            af c = C0234u.m1097k(this.f684n).m832c(0.0f);
            c.m827a(this.f679g);
            c0345e.m1703a(c);
            if (this.f666B && this.f688r != null) {
                C0234u.m1084b(this.f688r, f);
                c0345e.m1703a(C0234u.m1097k(this.f688r).m832c(0.0f));
            }
            if (this.f687q != null && this.f695y == 1) {
                C0234u.m1084b(this.f687q, (float) this.f687q.getHeight());
                this.f687q.setVisibility(0);
                c0345e.m1703a(C0234u.m1097k(this.f687q).m832c(0.0f));
            }
            c0345e.m1705a(AnimationUtils.loadInterpolator(this.f680j, 17432582));
            c0345e.m1702a(250);
            c0345e.m1704a(this.f678f);
            this.f671G = c0345e;
            c0345e.m1706a();
        } else {
            C0234u.m1087c(this.f684n, 1.0f);
            C0234u.m1084b(this.f684n, 0.0f);
            if (this.f666B && this.f688r != null) {
                C0234u.m1084b(this.f688r, 0.0f);
            }
            if (this.f687q != null && this.f695y == 1) {
                C0234u.m1087c(this.f687q, 1.0f);
                C0234u.m1084b(this.f687q, 0.0f);
                this.f687q.setVisibility(0);
            }
            this.f678f.onAnimationEnd(null);
        }
        if (this.f683m != null) {
            C0234u.m1099m(this.f683m);
        }
    }

    public void m1646h(boolean z) {
        if (this.f671G != null) {
            this.f671G.m1707b();
        }
        if (this.f665A == 0 && f664i && (this.f672H || z)) {
            C0234u.m1087c(this.f684n, 1.0f);
            this.f684n.setTransitioning(true);
            C0345e c0345e = new C0345e();
            float f = (float) (-this.f684n.getHeight());
            if (z) {
                int[] iArr = new int[]{0, 0};
                this.f684n.getLocationInWindow(iArr);
                f -= (float) iArr[1];
            }
            af c = C0234u.m1097k(this.f684n).m832c(f);
            c.m827a(this.f679g);
            c0345e.m1703a(c);
            if (this.f666B && this.f688r != null) {
                c0345e.m1703a(C0234u.m1097k(this.f688r).m832c(f));
            }
            if (this.f687q != null && this.f687q.getVisibility() == 0) {
                C0234u.m1087c(this.f687q, 1.0f);
                c0345e.m1703a(C0234u.m1097k(this.f687q).m832c((float) this.f687q.getHeight()));
            }
            c0345e.m1705a(AnimationUtils.loadInterpolator(this.f680j, 17432581));
            c0345e.m1702a(250);
            c0345e.m1704a(this.f677e);
            this.f671G = c0345e;
            c0345e.m1706a();
            return;
        }
        this.f677e.onAnimationEnd(null);
    }

    public void hideForSystem() {
        if (!this.f668D) {
            this.f668D = true;
            m1628k(true);
        }
    }

    public void m1647i(boolean z) {
        int i = 0;
        if (z) {
            m1620g();
        } else {
            m1622h();
        }
        this.f685o.animateToVisibility(z ? 8 : 0);
        ActionBarContextView actionBarContextView = this.f686p;
        if (!z) {
            i = 8;
        }
        actionBarContextView.animateToVisibility(i);
    }

    public void onContentScrollStarted() {
        if (this.f671G != null) {
            this.f671G.m1707b();
            this.f671G = null;
        }
    }

    public void onContentScrollStopped() {
    }

    public void onWindowVisibilityChanged(int i) {
        this.f665A = i;
    }

    public void showForSystem() {
        if (this.f668D) {
            this.f668D = false;
            m1628k(true);
        }
    }
}
