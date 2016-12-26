package android.support.v7.internal.p011a;

import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v7.internal.a.b */
public interface C0317b {
    boolean onCreatePanelMenu(int i, Menu menu);

    View onCreatePanelView(int i);

    boolean onMenuItemSelected(int i, MenuItem menuItem);

    boolean onMenuOpened(int i, Menu menu);

    void onPanelClosed(int i, Menu menu);

    boolean onPreparePanel(int i, View view, Menu menu);

    C0320a startActionMode(C0308a c0308a);
}
