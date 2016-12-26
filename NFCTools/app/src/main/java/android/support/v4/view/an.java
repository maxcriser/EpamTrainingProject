package android.support.v4.view;

import android.view.WindowInsets;

class an extends am {
    private final WindowInsets f419a;

    an(WindowInsets windowInsets) {
        this.f419a = windowInsets;
    }

    public int m851a() {
        return this.f419a.getSystemWindowInsetLeft();
    }

    public am m852a(int i, int i2, int i3, int i4) {
        return new an(this.f419a.replaceSystemWindowInsets(i, i2, i3, i4));
    }

    public int m853b() {
        return this.f419a.getSystemWindowInsetTop();
    }

    public int m854c() {
        return this.f419a.getSystemWindowInsetRight();
    }

    public int m855d() {
        return this.f419a.getSystemWindowInsetBottom();
    }

    WindowInsets m856e() {
        return this.f419a;
    }
}
