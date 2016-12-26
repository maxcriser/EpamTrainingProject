package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.C0234u;
import android.support.v4.view.af;
import android.support.v4.view.aj;
import android.support.v7.internal.view.C0345e;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0300k;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.ActionMenuView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

abstract class AbsActionBarView extends ViewGroup {
    private static final int FADE_DURATION = 200;
    private static final Interpolator sAlphaInterpolator;
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected boolean mSplitActionBar;
    protected ViewGroup mSplitView;
    protected boolean mSplitWhenNarrow;
    protected final VisibilityAnimListener mVisAnimListener;
    protected af mVisibilityAnim;

    /* renamed from: android.support.v7.internal.widget.AbsActionBarView.1 */
    class C03601 implements Runnable {
        final /* synthetic */ AbsActionBarView f770a;

        C03601(AbsActionBarView absActionBarView) {
            this.f770a = absActionBarView;
        }

        public void run() {
            this.f770a.showOverflowMenu();
        }
    }

    protected class VisibilityAnimListener implements aj {
        private boolean mCanceled;
        int mFinalVisibility;

        protected VisibilityAnimListener() {
            this.mCanceled = false;
        }

        public void onAnimationCancel(View view) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(View view) {
            if (!this.mCanceled) {
                AbsActionBarView.this.mVisibilityAnim = null;
                AbsActionBarView.this.setVisibility(this.mFinalVisibility);
                if (AbsActionBarView.this.mSplitView != null && AbsActionBarView.this.mMenuView != null) {
                    AbsActionBarView.this.mMenuView.setVisibility(this.mFinalVisibility);
                }
            }
        }

        public void onAnimationStart(View view) {
            AbsActionBarView.this.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(af afVar, int i) {
            AbsActionBarView.this.mVisibilityAnim = afVar;
            this.mFinalVisibility = i;
            return this;
        }
    }

    static {
        sAlphaInterpolator = new DecelerateInterpolator();
    }

    AbsActionBarView(Context context) {
        this(context, null);
    }

    AbsActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    AbsActionBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVisAnimListener = new VisibilityAnimListener();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(C0290a.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.mPopupContext = context;
        } else {
            this.mPopupContext = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    protected static int next(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    public void animateToVisibility(int i) {
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.m829a();
        }
        af a;
        if (i == 0) {
            if (getVisibility() != 0) {
                C0234u.m1087c(this, 0.0f);
                if (!(this.mSplitView == null || this.mMenuView == null)) {
                    C0234u.m1087c(this.mMenuView, 0.0f);
                }
            }
            a = C0234u.m1097k(this).m824a(1.0f);
            a.m825a(200);
            a.m828a(sAlphaInterpolator);
            if (this.mSplitView == null || this.mMenuView == null) {
                a.m826a(this.mVisAnimListener.withFinalVisibility(a, i));
                a.m831b();
                return;
            }
            C0345e c0345e = new C0345e();
            af a2 = C0234u.m1097k(this.mMenuView).m824a(1.0f);
            a2.m825a(200);
            c0345e.m1704a(this.mVisAnimListener.withFinalVisibility(a, i));
            c0345e.m1703a(a).m1703a(a2);
            c0345e.m1706a();
            return;
        }
        a = C0234u.m1097k(this).m824a(0.0f);
        a.m825a(200);
        a.m828a(sAlphaInterpolator);
        if (this.mSplitView == null || this.mMenuView == null) {
            a.m826a(this.mVisAnimListener.withFinalVisibility(a, i));
            a.m831b();
            return;
        }
        c0345e = new C0345e();
        a2 = C0234u.m1097k(this.mMenuView).m824a(0.0f);
        a2.m825a(200);
        c0345e.m1704a(this.mVisAnimListener.withFinalVisibility(a, i));
        c0345e.m1703a(a).m1703a(a2);
        c0345e.m1706a();
    }

    public boolean canShowOverflowMenu() {
        return isOverflowReserved() && getVisibility() == 0;
    }

    public void dismissPopupMenus() {
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
    }

    public int getAnimatedVisibility() {
        return this.mVisibilityAnim != null ? this.mVisAnimListener.mFinalVisibility : getVisibility();
    }

    public int getContentHeight() {
        return this.mContentHeight;
    }

    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.hideOverflowMenu() : false;
    }

    public boolean isOverflowMenuShowPending() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.isOverflowMenuShowPending() : false;
    }

    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.isOverflowMenuShowing() : false;
    }

    public boolean isOverflowReserved() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowReserved();
    }

    protected int measureChildView(View view, int i, int i2, int i3) {
        view.measure(MeasureSpec.makeMeasureSpec(i, RtlSpacingHelper.UNDEFINED), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, C0300k.ActionBar, C0290a.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(C0300k.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.onConfigurationChanged(configuration);
        }
    }

    protected int positionChild(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public void postShowOverflowMenu() {
        post(new C03601(this));
    }

    public void setContentHeight(int i) {
        this.mContentHeight = i;
        requestLayout();
    }

    public void setSplitToolbar(boolean z) {
        this.mSplitActionBar = z;
    }

    public void setSplitView(ViewGroup viewGroup) {
        this.mSplitView = viewGroup;
    }

    public void setSplitWhenNarrow(boolean z) {
        this.mSplitWhenNarrow = z;
    }

    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.showOverflowMenu() : false;
    }
}
