package android.support.v7.internal.view.menu;

import android.content.Context;
import android.support.v4.p003c.p004a.C0079b;
import android.support.v4.p003c.p004a.C0080c;
import android.support.v4.p007f.C0097a;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

/* renamed from: android.support.v7.internal.view.menu.a */
abstract class C0354a<T> extends C0351b<T> {
    final Context mContext;
    private Map<C0079b, MenuItem> mMenuItems;
    private Map<C0080c, SubMenu> mSubMenus;

    C0354a(Context context, T t) {
        super(t);
        this.mContext = context;
    }

    final MenuItem getMenuItemWrapper(MenuItem menuItem) {
        if (!(menuItem instanceof C0079b)) {
            return menuItem;
        }
        C0079b c0079b = (C0079b) menuItem;
        if (this.mMenuItems == null) {
            this.mMenuItems = new C0097a();
        }
        MenuItem menuItem2 = (MenuItem) this.mMenuItems.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        menuItem2 = MenuWrapperFactory.wrapSupportMenuItem(this.mContext, c0079b);
        this.mMenuItems.put(c0079b, menuItem2);
        return menuItem2;
    }

    final SubMenu getSubMenuWrapper(SubMenu subMenu) {
        if (!(subMenu instanceof C0080c)) {
            return subMenu;
        }
        C0080c c0080c = (C0080c) subMenu;
        if (this.mSubMenus == null) {
            this.mSubMenus = new C0097a();
        }
        SubMenu subMenu2 = (SubMenu) this.mSubMenus.get(c0080c);
        if (subMenu2 != null) {
            return subMenu2;
        }
        subMenu2 = MenuWrapperFactory.wrapSupportSubMenu(this.mContext, c0080c);
        this.mSubMenus.put(c0080c, subMenu2);
        return subMenu2;
    }

    final void internalClear() {
        if (this.mMenuItems != null) {
            this.mMenuItems.clear();
        }
        if (this.mSubMenus != null) {
            this.mSubMenus.clear();
        }
    }

    final void internalRemoveGroup(int i) {
        if (this.mMenuItems != null) {
            Iterator it = this.mMenuItems.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getGroupId()) {
                    it.remove();
                }
            }
        }
    }

    final void internalRemoveItem(int i) {
        if (this.mMenuItems != null) {
            Iterator it = this.mMenuItems.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getItemId()) {
                    it.remove();
                    return;
                }
            }
        }
    }
}
