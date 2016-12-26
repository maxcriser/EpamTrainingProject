package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.v4.p003c.p004a.C0079b;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v4.view.l */
public class C0210l {
    static final C0205d f425a;

    /* renamed from: android.support.v4.view.l.d */
    interface C0205d {
        MenuItem m904a(MenuItem menuItem, View view);

        View m905a(MenuItem menuItem);

        void m906a(MenuItem menuItem, int i);

        MenuItem m907b(MenuItem menuItem, int i);

        boolean m908b(MenuItem menuItem);

        boolean m909c(MenuItem menuItem);
    }

    /* renamed from: android.support.v4.view.l.a */
    static class C0206a implements C0205d {
        C0206a() {
        }

        public MenuItem m910a(MenuItem menuItem, View view) {
            return menuItem;
        }

        public View m911a(MenuItem menuItem) {
            return null;
        }

        public void m912a(MenuItem menuItem, int i) {
        }

        public MenuItem m913b(MenuItem menuItem, int i) {
            return menuItem;
        }

        public boolean m914b(MenuItem menuItem) {
            return false;
        }

        public boolean m915c(MenuItem menuItem) {
            return false;
        }
    }

    /* renamed from: android.support.v4.view.l.b */
    static class C0207b implements C0205d {
        C0207b() {
        }

        public MenuItem m916a(MenuItem menuItem, View view) {
            return C0211m.m933a(menuItem, view);
        }

        public View m917a(MenuItem menuItem) {
            return C0211m.m934a(menuItem);
        }

        public void m918a(MenuItem menuItem, int i) {
            C0211m.m935a(menuItem, i);
        }

        public MenuItem m919b(MenuItem menuItem, int i) {
            return C0211m.m936b(menuItem, i);
        }

        public boolean m920b(MenuItem menuItem) {
            return false;
        }

        public boolean m921c(MenuItem menuItem) {
            return false;
        }
    }

    /* renamed from: android.support.v4.view.l.c */
    static class C0208c extends C0207b {
        C0208c() {
        }

        public boolean m922b(MenuItem menuItem) {
            return C0212n.m937a(menuItem);
        }

        public boolean m923c(MenuItem menuItem) {
            return C0212n.m938b(menuItem);
        }
    }

    /* renamed from: android.support.v4.view.l.e */
    public interface C0209e {
        boolean m924a(MenuItem menuItem);

        boolean m925b(MenuItem menuItem);
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 14) {
            f425a = new C0208c();
        } else if (i >= 11) {
            f425a = new C0207b();
        } else {
            f425a = new C0206a();
        }
    }

    public static MenuItem m926a(MenuItem menuItem, C0187d c0187d) {
        if (menuItem instanceof C0079b) {
            return ((C0079b) menuItem).setSupportActionProvider(c0187d);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static MenuItem m927a(MenuItem menuItem, View view) {
        return menuItem instanceof C0079b ? ((C0079b) menuItem).setActionView(view) : f425a.m904a(menuItem, view);
    }

    public static View m928a(MenuItem menuItem) {
        return menuItem instanceof C0079b ? ((C0079b) menuItem).getActionView() : f425a.m905a(menuItem);
    }

    public static void m929a(MenuItem menuItem, int i) {
        if (menuItem instanceof C0079b) {
            ((C0079b) menuItem).setShowAsAction(i);
        } else {
            f425a.m906a(menuItem, i);
        }
    }

    public static MenuItem m930b(MenuItem menuItem, int i) {
        return menuItem instanceof C0079b ? ((C0079b) menuItem).setActionView(i) : f425a.m907b(menuItem, i);
    }

    public static boolean m931b(MenuItem menuItem) {
        return menuItem instanceof C0079b ? ((C0079b) menuItem).expandActionView() : f425a.m908b(menuItem);
    }

    public static boolean m932c(MenuItem menuItem) {
        return menuItem instanceof C0079b ? ((C0079b) menuItem).isActionViewExpanded() : f425a.m909c(menuItem);
    }
}
