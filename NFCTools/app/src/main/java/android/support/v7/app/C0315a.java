package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.p009a.C0301a.C0300k;
import android.support.v7.p010b.C0320a;
import android.support.v7.p010b.C0320a.C0308a;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

/* renamed from: android.support.v7.app.a */
public abstract class C0315a {

    /* renamed from: android.support.v7.app.a.a */
    public static class C0312a extends MarginLayoutParams {
        public int gravity;

        public C0312a(int i) {
            this(-2, -1, i);
        }

        public C0312a(int i, int i2) {
            super(i, i2);
            this.gravity = 0;
            this.gravity = 8388627;
        }

        public C0312a(int i, int i2, int i3) {
            super(i, i2);
            this.gravity = 0;
            this.gravity = i3;
        }

        public C0312a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0300k.ActionBarLayout);
            this.gravity = obtainStyledAttributes.getInt(C0300k.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public C0312a(C0312a c0312a) {
            super(c0312a);
            this.gravity = 0;
            this.gravity = c0312a.gravity;
        }

        public C0312a(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
        }
    }

    /* renamed from: android.support.v7.app.a.b */
    public interface C0313b {
        void m1510a(boolean z);
    }

    /* renamed from: android.support.v7.app.a.c */
    public static abstract class C0314c {
        public abstract Drawable m1511a();

        public abstract CharSequence m1512b();

        public abstract View m1513c();

        public abstract void m1514d();

        public abstract CharSequence m1515e();
    }

    public abstract int m1516a();

    public C0320a m1517a(C0308a c0308a) {
        return null;
    }

    public void m1518a(float f) {
        if (f != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    public void m1519a(Configuration configuration) {
    }

    public void m1520a(CharSequence charSequence) {
    }

    public void m1521a(boolean z) {
    }

    public Context m1522b() {
        return null;
    }

    public void m1523b(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    public void m1524c(boolean z) {
    }

    public boolean m1525c() {
        return false;
    }

    public void m1526d(boolean z) {
    }

    public boolean m1527d() {
        return false;
    }

    public void m1528e(boolean z) {
    }
}
