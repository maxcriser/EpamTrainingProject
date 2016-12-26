package android.support.v7.app;

import android.annotation.TargetApi;
import android.support.v7.internal.view.C0340c;
import android.support.v7.internal.view.C0340c.C0339a;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;
import android.support.v7.internal.widget.NativeActionModeAwareLayout.OnActionModeForChildListener;
import android.support.v7.p010b.C0320a;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.View;

@TargetApi(11)
/* renamed from: android.support.v7.app.d */
class C0319d extends ActionBarActivityDelegateBase implements OnActionModeForChildListener {
    private NativeActionModeAwareLayout f635k;

    C0319d(C0316b c0316b) {
        super(c0316b);
    }

    boolean m1548a(int i, KeyEvent keyEvent) {
        return false;
    }

    void m1549o() {
        this.f635k = (NativeActionModeAwareLayout) this.a.findViewById(16908290);
        if (this.f635k != null) {
            this.f635k.setActionModeForChildListener(this);
        }
    }

    public ActionMode startActionModeForChild(View view, Callback callback) {
        C0320a b = m1497b(new C0339a(view.getContext(), callback));
        return b != null ? new C0340c(this.a, b) : null;
    }
}
