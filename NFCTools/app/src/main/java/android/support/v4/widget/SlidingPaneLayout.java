package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.C0234u;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.wakdev.nfctools.C0628m.C0627j;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    static final C0255d f480a;
    private int f481b;
    private int f482c;
    private Drawable f483d;
    private Drawable f484e;
    private final int f485f;
    private boolean f486g;
    private View f487h;
    private float f488i;
    private float f489j;
    private int f490k;
    private boolean f491l;
    private int f492m;
    private float f493n;
    private float f494o;
    private C0254c f495p;
    private final C0289m f496q;
    private boolean f497r;
    private boolean f498s;
    private final Rect f499t;
    private final ArrayList<C0252a> f500u;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        boolean f470a;

        /* renamed from: android.support.v4.widget.SlidingPaneLayout.SavedState.1 */
        static class C02511 implements Creator<SavedState> {
            C02511() {
            }

            public SavedState m1191a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m1192a(int i) {
                return new SavedState[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m1191a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m1192a(i);
            }
        }

        static {
            CREATOR = new C02511();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f470a = parcel.readInt() != 0;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f470a ? 1 : 0);
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.a */
    private class C0252a implements Runnable {
        final View f471a;
        final /* synthetic */ SlidingPaneLayout f472b;

        C0252a(SlidingPaneLayout slidingPaneLayout, View view) {
            this.f472b = slidingPaneLayout;
            this.f471a = view;
        }

        public void run() {
            if (this.f471a.getParent() == this.f472b) {
                C0234u.m1076a(this.f471a, 0, null);
                this.f472b.m1204d(this.f471a);
            }
            this.f472b.f500u.remove(this);
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.b */
    public static class C0253b extends MarginLayoutParams {
        private static final int[] f473e;
        public float f474a;
        boolean f475b;
        boolean f476c;
        Paint f477d;

        static {
            f473e = new int[]{16843137};
        }

        public C0253b() {
            super(-1, -1);
            this.f474a = 0.0f;
        }

        public C0253b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f474a = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f473e);
            this.f474a = obtainStyledAttributes.getFloat(0, 0.0f);
            obtainStyledAttributes.recycle();
        }

        public C0253b(LayoutParams layoutParams) {
            super(layoutParams);
            this.f474a = 0.0f;
        }

        public C0253b(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f474a = 0.0f;
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.c */
    public interface C0254c {
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.d */
    interface C0255d {
        void m1193a(SlidingPaneLayout slidingPaneLayout, View view);
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.e */
    static class C0256e implements C0255d {
        C0256e() {
        }

        public void m1194a(SlidingPaneLayout slidingPaneLayout, View view) {
            C0234u.m1075a(slidingPaneLayout, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.f */
    static class C0257f extends C0256e {
        private Method f478a;
        private Field f479b;

        C0257f() {
            try {
                this.f478a = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
            } catch (Throwable e) {
                Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", e);
            }
            try {
                this.f479b = View.class.getDeclaredField("mRecreateDisplayList");
                this.f479b.setAccessible(true);
            } catch (Throwable e2) {
                Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e2);
            }
        }

        public void m1195a(SlidingPaneLayout slidingPaneLayout, View view) {
            if (this.f478a == null || this.f479b == null) {
                view.invalidate();
                return;
            }
            try {
                this.f479b.setBoolean(view, true);
                this.f478a.invoke(view, (Object[]) null);
            } catch (Throwable e) {
                Log.e("SlidingPaneLayout", "Error refreshing display list state", e);
            }
            super.m1194a(slidingPaneLayout, view);
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.g */
    static class C0258g extends C0256e {
        C0258g() {
        }

        public void m1196a(SlidingPaneLayout slidingPaneLayout, View view) {
            C0234u.m1077a(view, ((C0253b) view.getLayoutParams()).f477d);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 17) {
            f480a = new C0258g();
        } else if (i >= 16) {
            f480a = new C0257f();
        } else {
            f480a = new C0256e();
        }
    }

    private void m1198a(float f) {
        Object obj;
        int childCount;
        int i;
        View childAt;
        int i2;
        boolean f2 = m1205f();
        C0253b c0253b = (C0253b) this.f487h.getLayoutParams();
        if (c0253b.f476c) {
            if ((f2 ? c0253b.rightMargin : c0253b.leftMargin) <= 0) {
                obj = 1;
                childCount = getChildCount();
                for (i = 0; i < childCount; i++) {
                    childAt = getChildAt(i);
                    if (childAt == this.f487h) {
                        i2 = (int) ((1.0f - this.f489j) * ((float) this.f492m));
                        this.f489j = f;
                        i2 -= (int) ((1.0f - f) * ((float) this.f492m));
                        if (f2) {
                            i2 = -i2;
                        }
                        childAt.offsetLeftAndRight(i2);
                        if (obj == null) {
                            m1200a(childAt, f2 ? this.f489j - 1.0f : 1.0f - this.f489j, this.f482c);
                        }
                    }
                }
            }
        }
        obj = null;
        childCount = getChildCount();
        for (i = 0; i < childCount; i++) {
            childAt = getChildAt(i);
            if (childAt == this.f487h) {
                i2 = (int) ((1.0f - this.f489j) * ((float) this.f492m));
                this.f489j = f;
                i2 -= (int) ((1.0f - f) * ((float) this.f492m));
                if (f2) {
                    i2 = -i2;
                }
                childAt.offsetLeftAndRight(i2);
                if (obj == null) {
                    if (f2) {
                    }
                    m1200a(childAt, f2 ? this.f489j - 1.0f : 1.0f - this.f489j, this.f482c);
                }
            }
        }
    }

    private void m1200a(View view, float f, int i) {
        C0253b c0253b = (C0253b) view.getLayoutParams();
        if (f > 0.0f && i != 0) {
            int i2 = (((int) (((float) ((-16777216 & i) >>> 24)) * f)) << 24) | (16777215 & i);
            if (c0253b.f477d == null) {
                c0253b.f477d = new Paint();
            }
            c0253b.f477d.setColorFilter(new PorterDuffColorFilter(i2, Mode.SRC_OVER));
            if (C0234u.m1088d(view) != 2) {
                C0234u.m1076a(view, 2, c0253b.f477d);
            }
            m1204d(view);
        } else if (C0234u.m1088d(view) != 0) {
            if (c0253b.f477d != null) {
                c0253b.f477d.setColorFilter(null);
            }
            Runnable c0252a = new C0252a(this, view);
            this.f500u.add(c0252a);
            C0234u.m1080a((View) this, c0252a);
        }
    }

    private boolean m1201a(View view, int i) {
        if (!this.f498s && !m1208a(0.0f, i)) {
            return false;
        }
        this.f497r = false;
        return true;
    }

    private boolean m1202b(View view, int i) {
        if (!this.f498s && !m1208a(1.0f, i)) {
            return false;
        }
        this.f497r = true;
        return true;
    }

    private static boolean m1203c(View view) {
        if (C0234u.m1092f(view)) {
            return true;
        }
        if (VERSION.SDK_INT >= 18) {
            return false;
        }
        Drawable background = view.getBackground();
        return background != null ? background.getOpacity() == -1 : false;
    }

    private void m1204d(View view) {
        f480a.m1193a(this, view);
    }

    private boolean m1205f() {
        return C0234u.m1090e(this) == 1;
    }

    void m1206a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    void m1207a(View view) {
        int i;
        int i2;
        int i3;
        int i4;
        boolean f = m1205f();
        int width = f ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = f ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view == null || !m1203c(view)) {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        } else {
            i4 = view.getLeft();
            i3 = view.getRight();
            i2 = view.getTop();
            i = view.getBottom();
        }
        int childCount = getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt != view) {
                int i6 = (Math.max(f ? paddingLeft : width, childAt.getLeft()) < i4 || Math.max(paddingTop, childAt.getTop()) < i2 || Math.min(f ? width : paddingLeft, childAt.getRight()) > i3 || Math.min(height, childAt.getBottom()) > i) ? 0 : 4;
                childAt.setVisibility(i6);
                i5++;
            } else {
                return;
            }
        }
    }

    boolean m1208a(float f, int i) {
        if (!this.f486g) {
            return false;
        }
        int width;
        C0253b c0253b = (C0253b) this.f487h.getLayoutParams();
        if (m1205f()) {
            width = (int) (((float) getWidth()) - ((((float) (c0253b.rightMargin + getPaddingRight())) + (((float) this.f490k) * f)) + ((float) this.f487h.getWidth())));
        } else {
            width = (int) (((float) (c0253b.leftMargin + getPaddingLeft())) + (((float) this.f490k) * f));
        }
        if (!this.f496q.m1391a(this.f487h, width, this.f487h.getTop())) {
            return false;
        }
        m1206a();
        C0234u.m1083b(this);
        return true;
    }

    public boolean m1209b() {
        return m1202b(this.f487h, 0);
    }

    boolean m1210b(View view) {
        if (view == null) {
            return false;
        }
        boolean z = this.f486g && ((C0253b) view.getLayoutParams()).f476c && this.f488i > 0.0f;
        return z;
    }

    public boolean m1211c() {
        return m1201a(this.f487h, 0);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof C0253b) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        if (!this.f496q.m1392a(true)) {
            return;
        }
        if (this.f486g) {
            C0234u.m1083b(this);
        } else {
            this.f496q.m1406f();
        }
    }

    public boolean m1212d() {
        return !this.f486g || this.f488i == 1.0f;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = m1205f() ? this.f484e : this.f483d;
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt != null && drawable != null) {
            int right;
            int i;
            int top = childAt.getTop();
            int bottom = childAt.getBottom();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (m1205f()) {
                right = childAt.getRight();
                i = right + intrinsicWidth;
            } else {
                i = childAt.getLeft();
                right = i - intrinsicWidth;
            }
            drawable.setBounds(right, top, i, bottom);
            drawable.draw(canvas);
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild;
        C0253b c0253b = (C0253b) view.getLayoutParams();
        int save = canvas.save(2);
        if (!(!this.f486g || c0253b.f475b || this.f487h == null)) {
            canvas.getClipBounds(this.f499t);
            if (m1205f()) {
                this.f499t.left = Math.max(this.f499t.left, this.f487h.getRight());
            } else {
                this.f499t.right = Math.min(this.f499t.right, this.f487h.getLeft());
            }
            canvas.clipRect(this.f499t);
        }
        if (VERSION.SDK_INT >= 11) {
            drawChild = super.drawChild(canvas, view, j);
        } else if (!c0253b.f476c || this.f488i <= 0.0f) {
            if (view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(false);
            }
            drawChild = super.drawChild(canvas, view, j);
        } else {
            if (!view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(true);
            }
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                canvas.drawBitmap(drawingCache, (float) view.getLeft(), (float) view.getTop(), c0253b.f477d);
                drawChild = false;
            } else {
                Log.e("SlidingPaneLayout", "drawChild: child view " + view + " returned null drawing cache");
                drawChild = super.drawChild(canvas, view, j);
            }
        }
        canvas.restoreToCount(save);
        return drawChild;
    }

    public boolean m1213e() {
        return this.f486g;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new C0253b();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0253b(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new C0253b((MarginLayoutParams) layoutParams) : new C0253b(layoutParams);
    }

    public int getCoveredFadeColor() {
        return this.f482c;
    }

    public int getParallaxDistance() {
        return this.f492m;
    }

    public int getSliderFadeColor() {
        return this.f481b;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f498s = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f498s = true;
        int size = this.f500u.size();
        for (int i = 0; i < size; i++) {
            ((C0252a) this.f500u.get(i)).run();
        }
        this.f500u.clear();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(MotionEvent r8) {
        /*
        r7 = this;
        r2 = 0;
        r1 = 1;
        r3 = android.support.v4.view.C0216o.m954a(r8);
        r0 = r7.f486g;
        if (r0 != 0) goto L_0x002d;
    L_0x000a:
        if (r3 != 0) goto L_0x002d;
    L_0x000c:
        r0 = r7.getChildCount();
        if (r0 <= r1) goto L_0x002d;
    L_0x0012:
        r0 = r7.getChildAt(r1);
        if (r0 == 0) goto L_0x002d;
    L_0x0018:
        r4 = r7.f496q;
        r5 = r8.getX();
        r5 = (int) r5;
        r6 = r8.getY();
        r6 = (int) r6;
        r0 = r4.m1398b(r0, r5, r6);
        if (r0 != 0) goto L_0x0041;
    L_0x002a:
        r0 = r1;
    L_0x002b:
        r7.f497r = r0;
    L_0x002d:
        r0 = r7.f486g;
        if (r0 == 0) goto L_0x0037;
    L_0x0031:
        r0 = r7.f491l;
        if (r0 == 0) goto L_0x0043;
    L_0x0035:
        if (r3 == 0) goto L_0x0043;
    L_0x0037:
        r0 = r7.f496q;
        r0.m1405e();
        r2 = super.onInterceptTouchEvent(r8);
    L_0x0040:
        return r2;
    L_0x0041:
        r0 = r2;
        goto L_0x002b;
    L_0x0043:
        r0 = 3;
        if (r3 == r0) goto L_0x0048;
    L_0x0046:
        if (r3 != r1) goto L_0x004e;
    L_0x0048:
        r0 = r7.f496q;
        r0.m1405e();
        goto L_0x0040;
    L_0x004e:
        switch(r3) {
            case 0: goto L_0x005e;
            case 1: goto L_0x0051;
            case 2: goto L_0x0082;
            default: goto L_0x0051;
        };
    L_0x0051:
        r0 = r2;
    L_0x0052:
        r3 = r7.f496q;
        r3 = r3.m1390a(r8);
        if (r3 != 0) goto L_0x005c;
    L_0x005a:
        if (r0 == 0) goto L_0x0040;
    L_0x005c:
        r2 = r1;
        goto L_0x0040;
    L_0x005e:
        r7.f491l = r2;
        r0 = r8.getX();
        r3 = r8.getY();
        r7.f493n = r0;
        r7.f494o = r3;
        r4 = r7.f496q;
        r5 = r7.f487h;
        r0 = (int) r0;
        r3 = (int) r3;
        r0 = r4.m1398b(r5, r0, r3);
        if (r0 == 0) goto L_0x0051;
    L_0x0078:
        r0 = r7.f487h;
        r0 = r7.m1210b(r0);
        if (r0 == 0) goto L_0x0051;
    L_0x0080:
        r0 = r1;
        goto L_0x0052;
    L_0x0082:
        r0 = r8.getX();
        r3 = r8.getY();
        r4 = r7.f493n;
        r0 = r0 - r4;
        r0 = java.lang.Math.abs(r0);
        r4 = r7.f494o;
        r3 = r3 - r4;
        r3 = java.lang.Math.abs(r3);
        r4 = r7.f496q;
        r4 = r4.m1402d();
        r4 = (float) r4;
        r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x0051;
    L_0x00a3:
        r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
        if (r0 <= 0) goto L_0x0051;
    L_0x00a7:
        r0 = r7.f496q;
        r0.m1405e();
        r7.f491l = r1;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        boolean f = m1205f();
        if (f) {
            this.f496q.m1387a(2);
        } else {
            this.f496q.m1387a(1);
        }
        int i6 = i3 - i;
        int paddingRight = f ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = f ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.f498s) {
            float f2 = (this.f486g && this.f497r) ? 1.0f : 0.0f;
            this.f488i = f2;
        }
        int i7 = 0;
        int i8 = paddingRight;
        while (i7 < childCount) {
            int i9;
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() == 8) {
                i5 = paddingRight;
                i9 = i8;
            } else {
                int i10;
                C0253b c0253b = (C0253b) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (c0253b.f475b) {
                    int min = (Math.min(paddingRight, (i6 - paddingLeft) - this.f485f) - i8) - (c0253b.leftMargin + c0253b.rightMargin);
                    this.f490k = min;
                    i9 = f ? c0253b.rightMargin : c0253b.leftMargin;
                    c0253b.f476c = ((i8 + i9) + min) + (measuredWidth / 2) > i6 - paddingLeft;
                    i5 = (int) (((float) min) * this.f488i);
                    i10 = i8 + (i9 + i5);
                    this.f488i = ((float) i5) / ((float) this.f490k);
                    i5 = 0;
                } else if (!this.f486g || this.f492m == 0) {
                    i5 = 0;
                    i10 = paddingRight;
                } else {
                    i5 = (int) ((1.0f - this.f488i) * ((float) this.f492m));
                    i10 = paddingRight;
                }
                if (f) {
                    i9 = (i6 - i10) + i5;
                    i5 = i9 - measuredWidth;
                } else {
                    i5 = i10 - i5;
                    i9 = i5 + measuredWidth;
                }
                childAt.layout(i5, paddingTop, i9, childAt.getMeasuredHeight() + paddingTop);
                i5 = childAt.getWidth() + paddingRight;
                i9 = i10;
            }
            i7++;
            paddingRight = i5;
            i8 = i9;
        }
        if (this.f498s) {
            if (this.f486g) {
                if (this.f492m != 0) {
                    m1198a(this.f488i);
                }
                if (((C0253b) this.f487h.getLayoutParams()).f476c) {
                    m1200a(this.f487h, this.f488i, this.f481b);
                }
            } else {
                for (i5 = 0; i5 < childCount; i5++) {
                    m1200a(getChildAt(i5), 0.0f, this.f481b);
                }
            }
            m1207a(this.f487h);
        }
        this.f498s = false;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            if (mode2 == 0) {
                if (!isInEditMode()) {
                    throw new IllegalStateException("Height must not be UNSPECIFIED");
                } else if (mode2 == 0) {
                    i3 = Integer.MIN_VALUE;
                    i4 = size;
                    size = 300;
                }
            }
            i3 = mode2;
            i4 = size;
            size = size2;
        } else if (!isInEditMode()) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        } else if (mode == RtlSpacingHelper.UNDEFINED) {
            i3 = mode2;
            i4 = size;
            size = size2;
        } else {
            if (mode == 0) {
                i3 = mode2;
                i4 = 300;
                size = size2;
            }
            i3 = mode2;
            i4 = size;
            size = size2;
        }
        switch (i3) {
            case RtlSpacingHelper.UNDEFINED /*-2147483648*/:
                size2 = 0;
                mode2 = (size - getPaddingTop()) - getPaddingBottom();
                break;
            case 1073741824:
                size2 = (size - getPaddingTop()) - getPaddingBottom();
                mode2 = size2;
                break;
            default:
                size2 = 0;
                mode2 = -1;
                break;
        }
        boolean z = false;
        int paddingLeft = (i4 - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.f487h = null;
        int i5 = 0;
        int i6 = paddingLeft;
        int i7 = size2;
        float f = 0.0f;
        while (i5 < childCount) {
            float f2;
            int i8;
            boolean z2;
            View childAt = getChildAt(i5);
            C0253b c0253b = (C0253b) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                c0253b.f476c = false;
                size2 = i6;
                f2 = f;
                i8 = i7;
                z2 = z;
            } else {
                if (c0253b.f474a > 0.0f) {
                    f += c0253b.f474a;
                    if (c0253b.width == 0) {
                        size2 = i6;
                        f2 = f;
                        i8 = i7;
                        z2 = z;
                    }
                }
                mode = c0253b.leftMargin + c0253b.rightMargin;
                mode = c0253b.width == -2 ? MeasureSpec.makeMeasureSpec(paddingLeft - mode, RtlSpacingHelper.UNDEFINED) : c0253b.width == -1 ? MeasureSpec.makeMeasureSpec(paddingLeft - mode, 1073741824) : MeasureSpec.makeMeasureSpec(c0253b.width, 1073741824);
                i8 = c0253b.height == -2 ? MeasureSpec.makeMeasureSpec(mode2, RtlSpacingHelper.UNDEFINED) : c0253b.height == -1 ? MeasureSpec.makeMeasureSpec(mode2, 1073741824) : MeasureSpec.makeMeasureSpec(c0253b.height, 1073741824);
                childAt.measure(mode, i8);
                mode = childAt.getMeasuredWidth();
                i8 = childAt.getMeasuredHeight();
                if (i3 == Integer.MIN_VALUE && i8 > i7) {
                    i7 = Math.min(i8, mode2);
                }
                i8 = i6 - mode;
                boolean z3 = i8 < 0;
                c0253b.f475b = z3;
                z3 |= z;
                if (c0253b.f475b) {
                    this.f487h = childAt;
                }
                size2 = i8;
                i8 = i7;
                float f3 = f;
                z2 = z3;
                f2 = f3;
            }
            i5++;
            z = z2;
            i7 = i8;
            f = f2;
            i6 = size2;
        }
        if (z || f > 0.0f) {
            int i9 = paddingLeft - this.f485f;
            for (i3 = 0; i3 < childCount; i3++) {
                View childAt2 = getChildAt(i3);
                if (childAt2.getVisibility() != 8) {
                    c0253b = (C0253b) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != 8) {
                        Object obj = (c0253b.width != 0 || c0253b.f474a <= 0.0f) ? null : 1;
                        i8 = obj != null ? 0 : childAt2.getMeasuredWidth();
                        if (!z || childAt2 == this.f487h) {
                            if (c0253b.f474a > 0.0f) {
                                mode = c0253b.width == 0 ? c0253b.height == -2 ? MeasureSpec.makeMeasureSpec(mode2, RtlSpacingHelper.UNDEFINED) : c0253b.height == -1 ? MeasureSpec.makeMeasureSpec(mode2, 1073741824) : MeasureSpec.makeMeasureSpec(c0253b.height, 1073741824) : MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                if (z) {
                                    size2 = paddingLeft - (c0253b.rightMargin + c0253b.leftMargin);
                                    i5 = MeasureSpec.makeMeasureSpec(size2, 1073741824);
                                    if (i8 != size2) {
                                        childAt2.measure(i5, mode);
                                    }
                                } else {
                                    childAt2.measure(MeasureSpec.makeMeasureSpec(((int) ((c0253b.f474a * ((float) Math.max(0, i6))) / f)) + i8, 1073741824), mode);
                                }
                            }
                        } else if (c0253b.width < 0 && (i8 > i9 || c0253b.f474a > 0.0f)) {
                            size2 = obj != null ? c0253b.height == -2 ? MeasureSpec.makeMeasureSpec(mode2, RtlSpacingHelper.UNDEFINED) : c0253b.height == -1 ? MeasureSpec.makeMeasureSpec(mode2, 1073741824) : MeasureSpec.makeMeasureSpec(c0253b.height, 1073741824) : MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                            childAt2.measure(MeasureSpec.makeMeasureSpec(i9, 1073741824), size2);
                        }
                    }
                }
            }
        }
        setMeasuredDimension(i4, (getPaddingTop() + i7) + getPaddingBottom());
        this.f486g = z;
        if (this.f496q.m1386a() != 0 && !z) {
            this.f496q.m1406f();
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f470a) {
            m1209b();
        } else {
            m1211c();
        }
        this.f497r = savedState.f470a;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f470a = m1213e() ? m1212d() : this.f497r;
        return savedState;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.f498s = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f486g) {
            return super.onTouchEvent(motionEvent);
        }
        this.f496q.m1394b(motionEvent);
        float x;
        float y;
        switch (motionEvent.getAction() & 255) {
            case C0627j.View_android_focusable /*0*/:
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.f493n = x;
                this.f494o = y;
                return true;
            case C0627j.View_paddingStart /*1*/:
                if (!m1210b(this.f487h)) {
                    return true;
                }
                x = motionEvent.getX();
                y = motionEvent.getY();
                float f = x - this.f493n;
                float f2 = y - this.f494o;
                int d = this.f496q.m1402d();
                if ((f * f) + (f2 * f2) >= ((float) (d * d)) || !this.f496q.m1398b(this.f487h, (int) x, (int) y)) {
                    return true;
                }
                m1201a(this.f487h, 0);
                return true;
            default:
                return true;
        }
    }

    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (!isInTouchMode() && !this.f486g) {
            this.f497r = view == this.f487h;
        }
    }

    public void setCoveredFadeColor(int i) {
        this.f482c = i;
    }

    public void setPanelSlideListener(C0254c c0254c) {
        this.f495p = c0254c;
    }

    public void setParallaxDistance(int i) {
        this.f492m = i;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable) {
        this.f483d = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable) {
        this.f484e = drawable;
    }

    @Deprecated
    public void setShadowResource(int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i) {
        setShadowDrawableLeft(getResources().getDrawable(i));
    }

    public void setShadowResourceRight(int i) {
        setShadowDrawableRight(getResources().getDrawable(i));
    }

    public void setSliderFadeColor(int i) {
        this.f481b = i;
    }
}
