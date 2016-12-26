package android.support.v4.view;

import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v4.view.m */
class C0211m {
    public static MenuItem m933a(MenuItem menuItem, View view) {
        return menuItem.setActionView(view);
    }

    public static View m934a(MenuItem menuItem) {
        return menuItem.getActionView();
    }

    public static void m935a(MenuItem menuItem, int i) {
        menuItem.setShowAsAction(i);
    }

    public static MenuItem m936b(MenuItem menuItem, int i) {
        return menuItem.setActionView(i);
    }
}
