package com.wakdev.wdtabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.C0122f;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.Locale;

public class SlidingTabStrip extends HorizontalScrollView {
    private static final int[] f2595b;
    private int f2596A;
    private int f2597B;
    private int f2598C;
    private Locale f2599D;
    public C0122f f2600a;
    private LayoutParams f2601c;
    private LayoutParams f2602d;
    private final C0675b f2603e;
    private LinearLayout f2604f;
    private ViewPager f2605g;
    private int f2606h;
    private int f2607i;
    private float f2608j;
    private Paint f2609k;
    private Paint f2610l;
    private int f2611m;
    private int f2612n;
    private int f2613o;
    private boolean f2614p;
    private boolean f2615q;
    private int f2616r;
    private int f2617s;
    private int f2618t;
    private int f2619u;
    private int f2620v;
    private int f2621w;
    private int f2622x;
    private int f2623y;
    private Typeface f2624z;

    /* renamed from: com.wakdev.wdtabs.SlidingTabStrip.1 */
    class C06711 implements OnGlobalLayoutListener {
        final /* synthetic */ SlidingTabStrip f2590a;

        C06711(SlidingTabStrip slidingTabStrip) {
            this.f2590a = slidingTabStrip;
        }

        @SuppressLint({"NewApi"})
        public void onGlobalLayout() {
            if (VERSION.SDK_INT < 16) {
                this.f2590a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            } else {
                this.f2590a.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            this.f2590a.f2607i = this.f2590a.f2605g.getCurrentItem();
            this.f2590a.m3236b(this.f2590a.f2607i, 0);
        }
    }

    /* renamed from: com.wakdev.wdtabs.SlidingTabStrip.2 */
    class C06722 implements OnClickListener {
        final /* synthetic */ int f2591a;
        final /* synthetic */ SlidingTabStrip f2592b;

        C06722(SlidingTabStrip slidingTabStrip, int i) {
            this.f2592b = slidingTabStrip;
            this.f2591a = i;
        }

        public void onClick(View view) {
            this.f2592b.f2605g.setCurrentItem(this.f2591a);
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f2593a;

        /* renamed from: com.wakdev.wdtabs.SlidingTabStrip.SavedState.1 */
        static class C06731 implements Creator<SavedState> {
            C06731() {
            }

            public SavedState m3221a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m3222a(int i) {
                return new SavedState[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m3221a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m3222a(i);
            }
        }

        static {
            CREATOR = new C06731();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f2593a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f2593a);
        }
    }

    /* renamed from: com.wakdev.wdtabs.SlidingTabStrip.a */
    public interface C0674a {
        int m3223a(int i);
    }

    /* renamed from: com.wakdev.wdtabs.SlidingTabStrip.b */
    private class C0675b implements C0122f {
        final /* synthetic */ SlidingTabStrip f2594a;

        private C0675b(SlidingTabStrip slidingTabStrip) {
            this.f2594a = slidingTabStrip;
        }

        public void m3224a(int i) {
            if (this.f2594a.f2600a != null) {
                this.f2594a.f2600a.m490a(i);
            }
        }

        public void m3225a(int i, float f, int i2) {
            this.f2594a.f2607i = i;
            this.f2594a.f2608j = f;
            this.f2594a.m3236b(i, (int) (((float) this.f2594a.f2604f.getChildAt(i).getWidth()) * f));
            this.f2594a.invalidate();
            if (this.f2594a.f2600a != null) {
                this.f2594a.f2600a.m491a(i, f, i2);
            }
        }

        public void m3226b(int i) {
            if (i == 0) {
                this.f2594a.m3236b(this.f2594a.f2605g.getCurrentItem(), 0);
            }
            if (this.f2594a.f2600a != null) {
                this.f2594a.f2600a.m492b(i);
            }
        }
    }

    static {
        f2595b = new int[]{16842901, 16842904};
    }

    public SlidingTabStrip(Context context) {
        this(context, null);
    }

    public SlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2603e = new C0675b();
        this.f2607i = 0;
        this.f2608j = 0.0f;
        this.f2611m = -10066330;
        this.f2612n = 436207616;
        this.f2613o = 436207616;
        this.f2614p = true;
        this.f2615q = true;
        this.f2616r = 52;
        this.f2617s = 8;
        this.f2618t = 2;
        this.f2619u = 12;
        this.f2620v = 24;
        this.f2621w = 1;
        this.f2622x = 12;
        this.f2623y = -1;
        this.f2624z = null;
        this.f2596A = 1;
        this.f2597B = 0;
        this.f2598C = C0620c.my_background_tab;
        setFillViewport(true);
        setWillNotDraw(false);
        this.f2604f = new LinearLayout(context);
        this.f2604f.setOrientation(0);
        this.f2604f.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.f2604f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f2616r = (int) TypedValue.applyDimension(1, (float) this.f2616r, displayMetrics);
        this.f2617s = (int) TypedValue.applyDimension(1, (float) this.f2617s, displayMetrics);
        this.f2618t = (int) TypedValue.applyDimension(1, (float) this.f2618t, displayMetrics);
        this.f2619u = (int) TypedValue.applyDimension(1, (float) this.f2619u, displayMetrics);
        this.f2620v = (int) TypedValue.applyDimension(1, (float) this.f2620v, displayMetrics);
        this.f2621w = (int) TypedValue.applyDimension(1, (float) this.f2621w, displayMetrics);
        this.f2622x = (int) TypedValue.applyDimension(2, (float) this.f2622x, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f2595b);
        this.f2622x = obtainStyledAttributes.getDimensionPixelSize(0, this.f2622x);
        this.f2623y = obtainStyledAttributes.getColor(1, this.f2623y);
        obtainStyledAttributes.recycle();
        obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0627j.SlidingTabStrip);
        this.f2611m = obtainStyledAttributes.getColor(C0627j.SlidingTabStrip_pstsIndicatorColor, this.f2611m);
        this.f2612n = obtainStyledAttributes.getColor(C0627j.SlidingTabStrip_pstsUnderlineColor, this.f2612n);
        this.f2613o = obtainStyledAttributes.getColor(C0627j.SlidingTabStrip_pstsDividerColor, this.f2613o);
        this.f2617s = obtainStyledAttributes.getDimensionPixelSize(C0627j.SlidingTabStrip_pstsIndicatorHeight, this.f2617s);
        this.f2618t = obtainStyledAttributes.getDimensionPixelSize(C0627j.SlidingTabStrip_pstsUnderlineHeight, this.f2618t);
        this.f2619u = obtainStyledAttributes.getDimensionPixelSize(C0627j.SlidingTabStrip_pstsDividerPadding, this.f2619u);
        this.f2620v = obtainStyledAttributes.getDimensionPixelSize(C0627j.SlidingTabStrip_pstsTabPaddingLeftRight, this.f2620v);
        this.f2598C = obtainStyledAttributes.getResourceId(C0627j.SlidingTabStrip_pstsTabBackground, this.f2598C);
        this.f2614p = obtainStyledAttributes.getBoolean(C0627j.SlidingTabStrip_pstsShouldExpand, this.f2614p);
        this.f2616r = obtainStyledAttributes.getDimensionPixelSize(C0627j.SlidingTabStrip_pstsScrollOffset, this.f2616r);
        this.f2615q = obtainStyledAttributes.getBoolean(C0627j.SlidingTabStrip_pstsTextAllCaps, this.f2615q);
        obtainStyledAttributes.recycle();
        this.f2609k = new Paint();
        this.f2609k.setAntiAlias(true);
        this.f2609k.setStyle(Style.FILL);
        this.f2610l = new Paint();
        this.f2610l.setAntiAlias(true);
        this.f2610l.setStrokeWidth((float) this.f2621w);
        this.f2601c = new LayoutParams(-2, -1);
        this.f2602d = new LayoutParams(0, -1, 1.0f);
        if (this.f2599D == null) {
            this.f2599D = getResources().getConfiguration().locale;
        }
    }

    private void m3230a(int i, int i2) {
        View imageButton = new ImageButton(getContext());
        imageButton.setImageResource(i2);
        m3231a(i, imageButton);
    }

    private void m3231a(int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new C06722(this, i));
        view.setPadding(this.f2620v, 0, this.f2620v, 0);
        this.f2604f.addView(view, i, this.f2614p ? this.f2602d : this.f2601c);
    }

    private void m3232a(int i, String str) {
        View textView = new TextView(getContext());
        textView.setText(str);
        textView.setGravity(17);
        textView.setSingleLine();
        m3231a(i, textView);
    }

    private void m3235b() {
        for (int i = 0; i < this.f2606h; i++) {
            View childAt = this.f2604f.getChildAt(i);
            childAt.setBackgroundResource(this.f2598C);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.f2622x);
                textView.setTypeface(this.f2624z, this.f2596A);
                textView.setTextColor(this.f2623y);
                if (this.f2615q) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.f2599D));
                    }
                }
            }
        }
    }

    private void m3236b(int i, int i2) {
        if (this.f2606h != 0) {
            int left = this.f2604f.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.f2616r;
            }
            if (left != this.f2597B) {
                this.f2597B = left;
                scrollTo(left, 0);
            }
        }
    }

    public void m3238a() {
        this.f2604f.removeAllViews();
        this.f2606h = this.f2605g.getAdapter().m251b();
        for (int i = 0; i < this.f2606h; i++) {
            if (this.f2605g.getAdapter() instanceof C0674a) {
                m3230a(i, ((C0674a) this.f2605g.getAdapter()).m3223a(i));
            } else {
                m3232a(i, this.f2605g.getAdapter().m257c(i).toString());
            }
        }
        m3235b();
        getViewTreeObserver().addOnGlobalLayoutListener(new C06711(this));
    }

    public int getDividerColor() {
        return this.f2613o;
    }

    public int getDividerPadding() {
        return this.f2619u;
    }

    public int getIndicatorColor() {
        return this.f2611m;
    }

    public int getIndicatorHeight() {
        return this.f2617s;
    }

    public int getScrollOffset() {
        return this.f2616r;
    }

    public boolean getShouldExpand() {
        return this.f2614p;
    }

    public int getTabBackground() {
        return this.f2598C;
    }

    public int getTabPaddingLeftRight() {
        return this.f2620v;
    }

    public int getTextColor() {
        return this.f2623y;
    }

    public int getTextSize() {
        return this.f2622x;
    }

    public int getUnderlineColor() {
        return this.f2612n;
    }

    public int getUnderlineHeight() {
        return this.f2618t;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.f2606h != 0) {
            int height = getHeight();
            this.f2609k.setColor(this.f2611m);
            View childAt = this.f2604f.getChildAt(this.f2607i);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.f2608j > 0.0f && this.f2607i < this.f2606h - 1) {
                childAt = this.f2604f.getChildAt(this.f2607i + 1);
                float left2 = (float) childAt.getLeft();
                left = (left * (1.0f - this.f2608j)) + (left2 * this.f2608j);
                right = (((float) childAt.getRight()) * this.f2608j) + ((1.0f - this.f2608j) * right);
            }
            canvas.drawRect(left, (float) (height - this.f2617s), right, (float) height, this.f2609k);
            this.f2609k.setColor(this.f2612n);
            canvas.drawRect(0.0f, (float) (height - this.f2618t), (float) this.f2604f.getWidth(), (float) height, this.f2609k);
            this.f2610l.setColor(this.f2613o);
            for (int i = 0; i < this.f2606h - 1; i++) {
                childAt = this.f2604f.getChildAt(i);
                canvas.drawLine((float) childAt.getRight(), (float) this.f2619u, (float) childAt.getRight(), (float) (height - this.f2619u), this.f2610l);
            }
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f2607i = savedState.f2593a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2593a = this.f2607i;
        return savedState;
    }

    public void setAllCaps(boolean z) {
        this.f2615q = z;
    }

    public void setDividerColor(int i) {
        this.f2613o = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.f2613o = getResources().getColor(i);
        invalidate();
    }

    public void setDividerPadding(int i) {
        this.f2619u = i;
        invalidate();
    }

    public void setIndicatorColor(int i) {
        this.f2611m = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.f2611m = getResources().getColor(i);
        invalidate();
    }

    public void setIndicatorHeight(int i) {
        this.f2617s = i;
        invalidate();
    }

    public void setOnPageChangeListener(C0122f c0122f) {
        this.f2600a = c0122f;
    }

    public void setScrollOffset(int i) {
        this.f2616r = i;
        invalidate();
    }

    public void setShouldExpand(boolean z) {
        this.f2614p = z;
        requestLayout();
    }

    public void setTabBackground(int i) {
        this.f2598C = i;
    }

    public void setTabPaddingLeftRight(int i) {
        this.f2620v = i;
        m3235b();
    }

    public void setTextColor(int i) {
        this.f2623y = i;
        m3235b();
    }

    public void setTextColorResource(int i) {
        this.f2623y = getResources().getColor(i);
        m3235b();
    }

    public void setTextSize(int i) {
        this.f2622x = i;
        m3235b();
    }

    public void setUnderlineColor(int i) {
        this.f2612n = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.f2612n = getResources().getColor(i);
        invalidate();
    }

    public void setUnderlineHeight(int i) {
        this.f2618t = i;
        invalidate();
    }

    public void setViewPager(ViewPager viewPager) {
        this.f2605g = viewPager;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.f2603e);
        m3238a();
    }
}
