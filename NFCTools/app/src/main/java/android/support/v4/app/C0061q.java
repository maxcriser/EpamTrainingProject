package android.support.v4.app;

import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

/* renamed from: android.support.v4.app.q */
class C0061q extends FrameLayout {
    public C0061q(Context context) {
        super(context);
    }

    static ViewGroup m332a(View view) {
        ViewGroup c0061q = new C0061q(view.getContext());
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            c0061q.setLayoutParams(layoutParams);
        }
        view.setLayoutParams(new LayoutParams(-1, -1));
        c0061q.addView(view);
        return c0061q;
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }
}
