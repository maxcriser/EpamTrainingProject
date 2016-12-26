package android.support.v7.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.p003c.p004a.C0080c;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v7.internal.view.menu.e */
class C0359e extends C0358d implements SubMenu {
    C0359e(Context context, C0080c c0080c) {
        super(context, c0080c);
    }

    public C0080c m1717a() {
        return (C0080c) this.mWrappedObject;
    }

    public void clearHeader() {
        m1717a().clearHeader();
    }

    public MenuItem getItem() {
        return getMenuItemWrapper(m1717a().getItem());
    }

    public /* synthetic */ Object getWrappedObject() {
        return m1717a();
    }

    public SubMenu setHeaderIcon(int i) {
        m1717a().setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        m1717a().setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        m1717a().setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        m1717a().setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        m1717a().setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int i) {
        m1717a().setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        m1717a().setIcon(drawable);
        return this;
    }
}
