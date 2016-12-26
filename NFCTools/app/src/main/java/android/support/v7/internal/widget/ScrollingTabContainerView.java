package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.C0234u;
import android.support.v4.view.af;
import android.support.v4.view.aj;
import android.support.v7.app.C0315a.C0314c;
import android.support.v7.internal.view.C0337a;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingTabContainerView extends HorizontalScrollView implements OnItemClickListener {
    private static final int FADE_DURATION = 200;
    private static final String TAG = "ScrollingTabContainerView";
    private static final Interpolator sAlphaInterpolator;
    private boolean mAllowCollapse;
    private int mContentHeight;
    int mMaxTabWidth;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private C0383b mTabClickListener;
    private LinearLayoutCompat mTabLayout;
    Runnable mTabSelector;
    private SpinnerCompat mTabSpinner;
    protected final VisibilityAnimListener mVisAnimListener;
    protected af mVisibilityAnim;

    /* renamed from: android.support.v7.internal.widget.ScrollingTabContainerView.1 */
    class C03811 implements Runnable {
        final /* synthetic */ View f809a;
        final /* synthetic */ ScrollingTabContainerView f810b;

        C03811(ScrollingTabContainerView scrollingTabContainerView, View view) {
            this.f810b = scrollingTabContainerView;
            this.f809a = view;
        }

        public void run() {
            this.f810b.smoothScrollTo(this.f809a.getLeft() - ((this.f810b.getWidth() - this.f809a.getWidth()) / 2), 0);
            this.f810b.mTabSelector = null;
        }
    }

    protected class VisibilityAnimListener implements aj {
        private boolean mCanceled;
        private int mFinalVisibility;

        protected VisibilityAnimListener() {
            this.mCanceled = false;
        }

        public void onAnimationCancel(View view) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(View view) {
            if (!this.mCanceled) {
                ScrollingTabContainerView.this.mVisibilityAnim = null;
                ScrollingTabContainerView.this.setVisibility(this.mFinalVisibility);
            }
        }

        public void onAnimationStart(View view) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(af afVar, int i) {
            this.mFinalVisibility = i;
            ScrollingTabContainerView.this.mVisibilityAnim = afVar;
            return this;
        }
    }

    /* renamed from: android.support.v7.internal.widget.ScrollingTabContainerView.a */
    private class C0382a extends BaseAdapter {
        final /* synthetic */ ScrollingTabContainerView f811a;

        private C0382a(ScrollingTabContainerView scrollingTabContainerView) {
            this.f811a = scrollingTabContainerView;
        }

        public int getCount() {
            return this.f811a.mTabLayout.getChildCount();
        }

        public Object getItem(int i) {
            return ((C0384c) this.f811a.mTabLayout.getChildAt(i)).m1747b();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return this.f811a.createTabView((C0314c) getItem(i), true);
            }
            ((C0384c) view).m1746a((C0314c) getItem(i));
            return view;
        }
    }

    /* renamed from: android.support.v7.internal.widget.ScrollingTabContainerView.b */
    private class C0383b implements OnClickListener {
        final /* synthetic */ ScrollingTabContainerView f812a;

        private C0383b(ScrollingTabContainerView scrollingTabContainerView) {
            this.f812a = scrollingTabContainerView;
        }

        public void onClick(View view) {
            ((C0384c) view).m1747b().m1514d();
            int childCount = this.f812a.mTabLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f812a.mTabLayout.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }

    /* renamed from: android.support.v7.internal.widget.ScrollingTabContainerView.c */
    private class C0384c extends LinearLayoutCompat implements OnLongClickListener {
        final /* synthetic */ ScrollingTabContainerView f813a;
        private final int[] f814b;
        private C0314c f815c;
        private TextView f816d;
        private ImageView f817e;
        private View f818f;

        public C0384c(ScrollingTabContainerView scrollingTabContainerView, Context context, C0314c c0314c, boolean z) {
            this.f813a = scrollingTabContainerView;
            super(context, null, C0290a.actionBarTabStyle);
            this.f814b = new int[]{16842964};
            this.f815c = c0314c;
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, null, this.f814b, C0290a.actionBarTabStyle, 0);
            if (obtainStyledAttributes.hasValue(0)) {
                setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
            }
            obtainStyledAttributes.recycle();
            if (z) {
                setGravity(8388627);
            }
            m1745a();
        }

        public void m1745a() {
            C0314c c0314c = this.f815c;
            View c = c0314c.m1513c();
            if (c != null) {
                C0384c parent = c.getParent();
                if (parent != this) {
                    if (parent != null) {
                        parent.removeView(c);
                    }
                    addView(c);
                }
                this.f818f = c;
                if (this.f816d != null) {
                    this.f816d.setVisibility(8);
                }
                if (this.f817e != null) {
                    this.f817e.setVisibility(8);
                    this.f817e.setImageDrawable(null);
                    return;
                }
                return;
            }
            if (this.f818f != null) {
                removeView(this.f818f);
                this.f818f = null;
            }
            Drawable a = c0314c.m1511a();
            CharSequence b = c0314c.m1512b();
            if (a != null) {
                if (this.f817e == null) {
                    View imageView = new ImageView(getContext());
                    LayoutParams layoutParams = new LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView, 0);
                    this.f817e = imageView;
                }
                this.f817e.setImageDrawable(a);
                this.f817e.setVisibility(0);
            } else if (this.f817e != null) {
                this.f817e.setVisibility(8);
                this.f817e.setImageDrawable(null);
            }
            boolean z = !TextUtils.isEmpty(b);
            if (z) {
                if (this.f816d == null) {
                    imageView = new CompatTextView(getContext(), null, C0290a.actionBarTabTextStyle);
                    imageView.setEllipsize(TruncateAt.END);
                    layoutParams = new LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView);
                    this.f816d = imageView;
                }
                this.f816d.setText(b);
                this.f816d.setVisibility(0);
            } else if (this.f816d != null) {
                this.f816d.setVisibility(8);
                this.f816d.setText(null);
            }
            if (this.f817e != null) {
                this.f817e.setContentDescription(c0314c.m1515e());
            }
            if (z || TextUtils.isEmpty(c0314c.m1515e())) {
                setOnLongClickListener(null);
                setLongClickable(false);
                return;
            }
            setOnLongClickListener(this);
        }

        public void m1746a(C0314c c0314c) {
            this.f815c = c0314c;
            m1745a();
        }

        public C0314c m1747b() {
            return this.f815c;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(C0314c.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (VERSION.SDK_INT >= 14) {
                accessibilityNodeInfo.setClassName(C0314c.class.getName());
            }
        }

        public boolean onLongClick(View view) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = context.getResources().getDisplayMetrics().widthPixels;
            Toast makeText = Toast.makeText(context, this.f815c.m1515e(), 0);
            makeText.setGravity(49, (iArr[0] + (width / 2)) - (i / 2), height);
            makeText.show();
            return true;
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.f813a.mMaxTabWidth > 0 && getMeasuredWidth() > this.f813a.mMaxTabWidth) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(this.f813a.mMaxTabWidth, 1073741824), i2);
            }
        }

        public void setSelected(boolean z) {
            Object obj = isSelected() != z ? 1 : null;
            super.setSelected(z);
            if (obj != null && z) {
                sendAccessibilityEvent(4);
            }
        }
    }

    static {
        sAlphaInterpolator = new DecelerateInterpolator();
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.mVisAnimListener = new VisibilityAnimListener();
        setHorizontalScrollBarEnabled(false);
        C0337a a = C0337a.m1650a(context);
        setContentHeight(a.m1655e());
        this.mStackedTabMaxWidth = a.m1657g();
        this.mTabLayout = createTabLayout();
        addView(this.mTabLayout, new LayoutParams(-2, -1));
    }

    private SpinnerCompat createSpinner() {
        SpinnerCompat spinnerCompat = new SpinnerCompat(getContext(), null, C0290a.actionDropDownStyle);
        spinnerCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        spinnerCompat.m1759a((OnItemClickListener) this);
        return spinnerCompat;
    }

    private LinearLayoutCompat createTabLayout() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, C0290a.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        return linearLayoutCompat;
    }

    private C0384c createTabView(C0314c c0314c, boolean z) {
        C0384c c0384c = new C0384c(this, getContext(), c0314c, z);
        if (z) {
            c0384c.setBackgroundDrawable(null);
            c0384c.setLayoutParams(new AbsListView.LayoutParams(-1, this.mContentHeight));
        } else {
            c0384c.setFocusable(true);
            if (this.mTabClickListener == null) {
                this.mTabClickListener = new C0383b();
            }
            c0384c.setOnClickListener(this.mTabClickListener);
        }
        return c0384c;
    }

    private boolean isCollapsed() {
        return this.mTabSpinner != null && this.mTabSpinner.getParent() == this;
    }

    private void performCollapse() {
        if (!isCollapsed()) {
            if (this.mTabSpinner == null) {
                this.mTabSpinner = createSpinner();
            }
            removeView(this.mTabLayout);
            addView(this.mTabSpinner, new LayoutParams(-2, -1));
            if (this.mTabSpinner.m1731c() == null) {
                this.mTabSpinner.m1760a(new C0382a());
            }
            if (this.mTabSelector != null) {
                removeCallbacks(this.mTabSelector);
                this.mTabSelector = null;
            }
            this.mTabSpinner.setSelection(this.mSelectedTabIndex);
        }
    }

    private boolean performExpand() {
        if (isCollapsed()) {
            removeView(this.mTabSpinner);
            addView(this.mTabLayout, new LayoutParams(-2, -1));
            setTabSelected(this.mTabSpinner.getSelectedItemPosition());
        }
        return false;
    }

    public void addTab(C0314c c0314c, int i, boolean z) {
        View createTabView = createTabView(c0314c, false);
        this.mTabLayout.addView(createTabView, i, new LinearLayoutCompat.LayoutParams(0, -1, 1.0f));
        if (this.mTabSpinner != null) {
            ((C0382a) this.mTabSpinner.m1731c()).notifyDataSetChanged();
        }
        if (z) {
            createTabView.setSelected(true);
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void addTab(C0314c c0314c, boolean z) {
        View createTabView = createTabView(c0314c, false);
        this.mTabLayout.addView(createTabView, new LinearLayoutCompat.LayoutParams(0, -1, 1.0f));
        if (this.mTabSpinner != null) {
            ((C0382a) this.mTabSpinner.m1731c()).notifyDataSetChanged();
        }
        if (z) {
            createTabView.setSelected(true);
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void animateToTab(int i) {
        View childAt = this.mTabLayout.getChildAt(i);
        if (this.mTabSelector != null) {
            removeCallbacks(this.mTabSelector);
        }
        this.mTabSelector = new C03811(this, childAt);
        post(this.mTabSelector);
    }

    public void animateToVisibility(int i) {
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.m829a();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                C0234u.m1087c(this, 0.0f);
            }
            af a = C0234u.m1097k(this).m824a(1.0f);
            a.m825a(200);
            a.m828a(sAlphaInterpolator);
            a.m826a(this.mVisAnimListener.withFinalVisibility(a, i));
            a.m831b();
            return;
        }
        a = C0234u.m1097k(this).m824a(0.0f);
        a.m825a(200);
        a.m828a(sAlphaInterpolator);
        a.m826a(this.mVisAnimListener.withFinalVisibility(a, i));
        a.m831b();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mTabSelector != null) {
            post(this.mTabSelector);
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        C0337a a = C0337a.m1650a(getContext());
        setContentHeight(a.m1655e());
        this.mStackedTabMaxWidth = a.m1657g();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mTabSelector != null) {
            removeCallbacks(this.mTabSelector);
        }
    }

    public void onItemClick(AdapterViewCompat<?> adapterViewCompat, View view, int i, long j) {
        ((C0384c) view).m1747b().m1514d();
    }

    public void onMeasure(int i, int i2) {
        int i3 = 1;
        int mode = MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.mTabLayout.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == RtlSpacingHelper.UNDEFINED)) {
            this.mMaxTabWidth = -1;
        } else {
            if (childCount > 2) {
                this.mMaxTabWidth = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
            } else {
                this.mMaxTabWidth = MeasureSpec.getSize(i) / 2;
            }
            this.mMaxTabWidth = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
        }
        mode = MeasureSpec.makeMeasureSpec(this.mContentHeight, 1073741824);
        if (z || !this.mAllowCollapse) {
            i3 = 0;
        }
        if (i3 != 0) {
            this.mTabLayout.measure(0, mode);
            if (this.mTabLayout.getMeasuredWidth() > MeasureSpec.getSize(i)) {
                performCollapse();
            } else {
                performExpand();
            }
        } else {
            performExpand();
        }
        i3 = getMeasuredWidth();
        super.onMeasure(i, mode);
        int measuredWidth = getMeasuredWidth();
        if (z && i3 != measuredWidth) {
            setTabSelected(this.mSelectedTabIndex);
        }
    }

    public void removeAllTabs() {
        this.mTabLayout.removeAllViews();
        if (this.mTabSpinner != null) {
            ((C0382a) this.mTabSpinner.m1731c()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void removeTabAt(int i) {
        this.mTabLayout.removeViewAt(i);
        if (this.mTabSpinner != null) {
            ((C0382a) this.mTabSpinner.m1731c()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void setAllowCollapse(boolean z) {
        this.mAllowCollapse = z;
    }

    public void setContentHeight(int i) {
        this.mContentHeight = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.mSelectedTabIndex = i;
        int childCount = this.mTabLayout.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.mTabLayout.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                animateToTab(i);
            }
            i2++;
        }
        if (this.mTabSpinner != null && i >= 0) {
            this.mTabSpinner.setSelection(i);
        }
    }

    public void updateTab(int i) {
        ((C0384c) this.mTabLayout.getChildAt(i)).m1745a();
        if (this.mTabSpinner != null) {
            ((C0382a) this.mTabSpinner.m1731c()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }
}
