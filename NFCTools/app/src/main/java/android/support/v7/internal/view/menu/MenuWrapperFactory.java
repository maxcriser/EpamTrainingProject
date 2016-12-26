package android.support.v7.internal.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.p003c.p004a.C0078a;
import android.support.v4.p003c.p004a.C0079b;
import android.support.v4.p003c.p004a.C0080c;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public final class MenuWrapperFactory {
    private MenuWrapperFactory() {
    }

    public static Menu wrapSupportMenu(Context context, C0078a c0078a) {
        if (VERSION.SDK_INT >= 14) {
            return new C0358d(context, c0078a);
        }
        throw new UnsupportedOperationException();
    }

    public static MenuItem wrapSupportMenuItem(Context context, C0079b c0079b) {
        if (VERSION.SDK_INT >= 16) {
            return new C0357c(context, c0079b);
        }
        if (VERSION.SDK_INT >= 14) {
            return new MenuItemWrapperICS(context, c0079b);
        }
        throw new UnsupportedOperationException();
    }

    public static SubMenu wrapSupportSubMenu(Context context, C0080c c0080c) {
        if (VERSION.SDK_INT >= 14) {
            return new C0359e(context, c0080c);
        }
        throw new UnsupportedOperationException();
    }
}
