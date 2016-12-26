package android.support.v7.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.p003c.p004a.C0078a;
import android.support.v4.p003c.p004a.C0079b;
import android.support.v4.p007f.C0096g;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

@TargetApi(11)
/* renamed from: android.support.v7.internal.view.c */
public class C0340c extends ActionMode {
    final Context f710a;
    final C0320a f711b;

    /* renamed from: android.support.v7.internal.view.c.a */
    public static class C0339a implements C0308a {
        final Callback f706a;
        final Context f707b;
        final C0096g<C0320a, C0340c> f708c;
        final C0096g<Menu, Menu> f709d;

        public C0339a(Context context, Callback callback) {
            this.f707b = context;
            this.f706a = callback;
            this.f708c = new C0096g();
            this.f709d = new C0096g();
        }

        private Menu m1672a(Menu menu) {
            Menu menu2 = (Menu) this.f709d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            menu2 = MenuWrapperFactory.wrapSupportMenu(this.f707b, (C0078a) menu);
            this.f709d.put(menu, menu2);
            return menu2;
        }

        private ActionMode m1673b(C0320a c0320a) {
            C0340c c0340c = (C0340c) this.f708c.get(c0320a);
            if (c0340c != null) {
                return c0340c;
            }
            ActionMode c0340c2 = new C0340c(this.f707b, c0320a);
            this.f708c.put(c0320a, c0340c2);
            return c0340c2;
        }

        public void m1674a(C0320a c0320a) {
            this.f706a.onDestroyActionMode(m1673b(c0320a));
        }

        public boolean m1675a(C0320a c0320a, Menu menu) {
            return this.f706a.onCreateActionMode(m1673b(c0320a), m1672a(menu));
        }

        public boolean m1676a(C0320a c0320a, MenuItem menuItem) {
            return this.f706a.onActionItemClicked(m1673b(c0320a), MenuWrapperFactory.wrapSupportMenuItem(this.f707b, (C0079b) menuItem));
        }

        public boolean m1677b(C0320a c0320a, Menu menu) {
            return this.f706a.onPrepareActionMode(m1673b(c0320a), m1672a(menu));
        }
    }

    public C0340c(Context context, C0320a c0320a) {
        this.f710a = context;
        this.f711b = c0320a;
    }

    public void finish() {
        this.f711b.m1559c();
    }

    public View getCustomView() {
        return this.f711b.m1564i();
    }

    public Menu getMenu() {
        return MenuWrapperFactory.wrapSupportMenu(this.f710a, (C0078a) this.f711b.m1556b());
    }

    public MenuInflater getMenuInflater() {
        return this.f711b.m1550a();
    }

    public CharSequence getSubtitle() {
        return this.f711b.m1562g();
    }

    public Object getTag() {
        return this.f711b.m1565j();
    }

    public CharSequence getTitle() {
        return this.f711b.m1561f();
    }

    public boolean getTitleOptionalHint() {
        return this.f711b.m1566k();
    }

    public void invalidate() {
        this.f711b.m1560d();
    }

    public boolean isTitleOptional() {
        return this.f711b.m1563h();
    }

    public void setCustomView(View view) {
        this.f711b.m1552a(view);
    }

    public void setSubtitle(int i) {
        this.f711b.m1557b(i);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f711b.m1553a(charSequence);
    }

    public void setTag(Object obj) {
        this.f711b.m1554a(obj);
    }

    public void setTitle(int i) {
        this.f711b.m1551a(i);
    }

    public void setTitle(CharSequence charSequence) {
        this.f711b.m1558b(charSequence);
    }

    public void setTitleOptionalHint(boolean z) {
        this.f711b.m1555a(z);
    }
}
