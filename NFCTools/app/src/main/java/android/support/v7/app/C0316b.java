package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.C0022a;
import android.support.v4.app.C0037f;
import android.support.v4.app.C0059o;
import android.support.v4.app.C0068t;
import android.support.v4.app.C0068t.C0064a;
import android.support.v7.p010b.C0320a;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/* renamed from: android.support.v7.app.b */
public class C0316b extends C0037f implements C0064a {
    private C0311c f633n;

    private C0311c m1529j() {
        if (this.f633n == null) {
            this.f633n = C0311c.m1424a(this);
        }
        return this.f633n;
    }

    public Intent m1530a() {
        return C0059o.m322a(this);
    }

    public void m1531a(C0068t c0068t) {
        c0068t.m338a((Activity) this);
    }

    public void m1532a(C0320a c0320a) {
    }

    public void m1533a(Toolbar toolbar) {
        m1529j().m1434a(toolbar);
    }

    void m1534a(View view) {
        super.setContentView(view);
    }

    boolean m1535a(int i, Menu menu) {
        return super.onCreatePanelMenu(i, menu);
    }

    boolean m1536a(int i, View view, Menu menu) {
        return super.onPreparePanel(i, view, menu);
    }

    public boolean m1537a(Intent intent) {
        return C0059o.m324a((Activity) this, intent);
    }

    protected boolean m1538a(View view, Menu menu) {
        return m1529j().m1440a(view, menu);
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        m1529j().m1443b(view, layoutParams);
    }

    void m1539b(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    public void m1540b(Intent intent) {
        C0059o.m327b((Activity) this, intent);
    }

    public void m1541b(C0068t c0068t) {
    }

    public void m1542b(C0320a c0320a) {
    }

    boolean m1543b(View view, Menu menu) {
        return super.m173a(view, menu);
    }

    public void b_() {
        m1529j().m1451g();
    }

    boolean m1544c(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    public C0315a m1545g() {
        return m1529j().m1441b();
    }

    public MenuInflater getMenuInflater() {
        return m1529j().m1448d();
    }

    public boolean m1546h() {
        Intent a = m1530a();
        if (a == null) {
            return false;
        }
        if (m1537a(a)) {
            C0068t a2 = C0068t.m337a((Context) this);
            m1531a(a2);
            m1541b(a2);
            a2.m341a();
            try {
                C0022a.m113a(this);
            } catch (IllegalStateException e) {
                finish();
            }
        } else {
            m1540b(a);
        }
        return true;
    }

    public void m1547i() {
    }

    public void invalidateOptionsMenu() {
        m1529j().m1451g();
    }

    public void onBackPressed() {
        if (!m1529j().m1452h()) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m1529j().m1430a(configuration);
    }

    public final void onContentChanged() {
        m1529j().m1453i();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m1529j().m1431a(bundle);
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return m1529j().m1447c(i, menu);
    }

    public View onCreatePanelView(int i) {
        return i == 0 ? m1529j().m1442b(i) : super.onCreatePanelView(i);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View onCreateView = super.onCreateView(str, context, attributeSet);
        return onCreateView != null ? onCreateView : m1529j().m1427a(str, context, attributeSet);
    }

    protected void onDestroy() {
        super.onDestroy();
        m1529j().m1456l();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent) ? true : m1529j().m1438a(i, keyEvent);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        return m1529j().m1444b(i, keyEvent);
    }

    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        C0315a g = m1545g();
        return (menuItem.getItemId() != 16908332 || g == null || (g.m1516a() & 4) == 0) ? false : m1546h();
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return m1529j().m1445b(i, menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        m1529j().m1429a(i, menu);
    }

    protected void onPostResume() {
        super.onPostResume();
        m1529j().m1450f();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        return m1529j().m1439a(i, view, menu);
    }

    protected void onStop() {
        super.onStop();
        m1529j().m1449e();
    }

    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        m1529j().m1437a(charSequence);
    }

    public void setContentView(int i) {
        m1529j().m1428a(i);
    }

    public void setContentView(View view) {
        m1529j().m1435a(view);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        m1529j().m1436a(view, layoutParams);
    }
}
