package android.support.v4.p003c.p004a;

import android.support.v4.view.C0187d;
import android.support.v4.view.C0210l.C0209e;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v4.c.a.b */
public interface C0079b extends MenuItem {
    boolean collapseActionView();

    boolean expandActionView();

    View getActionView();

    C0187d getSupportActionProvider();

    boolean isActionViewExpanded();

    MenuItem setActionView(int i);

    MenuItem setActionView(View view);

    void setShowAsAction(int i);

    MenuItem setShowAsActionFlags(int i);

    C0079b setSupportActionProvider(C0187d c0187d);

    C0079b setSupportOnActionExpandListener(C0209e c0209e);
}
