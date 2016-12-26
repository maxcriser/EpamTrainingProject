package android.support.v7.p010b;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v7.b.a */
public abstract class C0320a {
    private Object f636a;
    private boolean f637b;

    /* renamed from: android.support.v7.b.a.a */
    public interface C0308a {
        void m1416a(C0320a c0320a);

        boolean m1417a(C0320a c0320a, Menu menu);

        boolean m1418a(C0320a c0320a, MenuItem menuItem);

        boolean m1419b(C0320a c0320a, Menu menu);
    }

    public abstract MenuInflater m1550a();

    public abstract void m1551a(int i);

    public abstract void m1552a(View view);

    public abstract void m1553a(CharSequence charSequence);

    public void m1554a(Object obj) {
        this.f636a = obj;
    }

    public void m1555a(boolean z) {
        this.f637b = z;
    }

    public abstract Menu m1556b();

    public abstract void m1557b(int i);

    public abstract void m1558b(CharSequence charSequence);

    public abstract void m1559c();

    public abstract void m1560d();

    public abstract CharSequence m1561f();

    public abstract CharSequence m1562g();

    public boolean m1563h() {
        return false;
    }

    public abstract View m1564i();

    public Object m1565j() {
        return this.f636a;
    }

    public boolean m1566k() {
        return this.f637b;
    }
}
