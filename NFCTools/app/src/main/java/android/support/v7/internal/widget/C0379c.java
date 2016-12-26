package android.support.v7.internal.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.v4.p001b.p002a.C0074a;

/* renamed from: android.support.v7.internal.widget.c */
class C0379c extends Drawable implements Callback {
    private final Drawable f807a;

    public C0379c(Drawable drawable) {
        this.f807a = drawable;
        this.f807a.setCallback(this);
    }

    public void draw(Canvas canvas) {
        this.f807a.draw(canvas);
    }

    public int getChangingConfigurations() {
        return this.f807a.getChangingConfigurations();
    }

    public Drawable getCurrent() {
        return this.f807a.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.f807a.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.f807a.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.f807a.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.f807a.getMinimumWidth();
    }

    public int getOpacity() {
        return this.f807a.getOpacity();
    }

    public boolean getPadding(Rect rect) {
        return this.f807a.getPadding(rect);
    }

    public int[] getState() {
        return this.f807a.getState();
    }

    public Region getTransparentRegion() {
        return this.f807a.getTransparentRegion();
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return C0074a.m374b(this.f807a);
    }

    public boolean isStateful() {
        return this.f807a.isStateful();
    }

    public void jumpToCurrentState() {
        C0074a.m367a(this.f807a);
    }

    protected boolean onLevelChange(int i) {
        return this.f807a.setLevel(i);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.f807a.setAlpha(i);
    }

    public void setAutoMirrored(boolean z) {
        C0074a.m373a(this.f807a, z);
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.f807a.setBounds(i, i2, i3, i4);
    }

    public void setChangingConfigurations(int i) {
        this.f807a.setChangingConfigurations(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f807a.setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        this.f807a.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.f807a.setFilterBitmap(z);
    }

    public void setHotspot(float f, float f2) {
        C0074a.m368a(this.f807a, f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        C0074a.m370a(this.f807a, i, i2, i3, i4);
    }

    public boolean setState(int[] iArr) {
        return this.f807a.setState(iArr);
    }

    public void setTint(int i) {
        C0074a.m369a(this.f807a, i);
    }

    public void setTintList(ColorStateList colorStateList) {
        C0074a.m371a(this.f807a, colorStateList);
    }

    public void setTintMode(Mode mode) {
        C0074a.m372a(this.f807a, mode);
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f807a.setVisible(z, z2);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
