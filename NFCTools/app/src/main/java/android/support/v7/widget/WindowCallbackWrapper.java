package android.support.v7.widget;

import android.support.v7.internal.p011a.C0317b;
import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class WindowCallbackWrapper implements C0317b {
    private C0317b mWrapped;

    public WindowCallbackWrapper(C0317b c0317b) {
        if (c0317b == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.mWrapped = c0317b;
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return this.mWrapped.onCreatePanelMenu(i, menu);
    }

    public View onCreatePanelView(int i) {
        return this.mWrapped.onCreatePanelView(i);
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.mWrapped.onMenuItemSelected(i, menuItem);
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return this.mWrapped.onMenuOpened(i, menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        this.mWrapped.onPanelClosed(i, menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        return this.mWrapped.onPreparePanel(i, view, menu);
    }

    public C0320a startActionMode(C0308a c0308a) {
        return this.mWrapped.startActionMode(c0308a);
    }
}
