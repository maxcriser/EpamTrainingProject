package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.v4.view.C0234u;
import android.support.v4.view.af;
import android.support.v4.view.aj;
import android.support.v4.view.ak;
import android.support.v4.widget.C0286k;
import android.support.v7.internal.C0334a;
import android.support.v7.internal.p011a.C0317b;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0295f;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.wakdev.nfctools.C0628m.C0627j;

public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent {
    static final int[] ATTRS;
    private static final String TAG = "ActionBarOverlayLayout";
    private final int ACTION_BAR_ANIMATE_DELAY;
    private ActionBarContainer mActionBarBottom;
    private int mActionBarHeight;
    private ActionBarContainer mActionBarTop;
    private ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    private boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private final Rect mBaseInnerInsets;
    private final aj mBottomAnimatorListener;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    private af mCurrentActionBarBottomAnimator;
    private af mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private C0286k mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private boolean mIgnoreWindowContentOverlay;
    private final Rect mInnerInsets;
    private final Rect mLastBaseContentInsets;
    private final Rect mLastInnerInsets;
    private int mLastSystemUiVisibility;
    private boolean mOverlayMode;
    private final Runnable mRemoveActionBarHideOffset;
    private final aj mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility;

    public interface ActionBarVisibilityCallback {
        void enableContentAnimations(boolean z);

        void hideForSystem();

        void onContentScrollStarted();

        void onContentScrollStopped();

        void onWindowVisibilityChanged(int i);

        void showForSystem();
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarOverlayLayout.1 */
    class C03641 extends ak {
        final /* synthetic */ ActionBarOverlayLayout f787a;

        C03641(ActionBarOverlayLayout actionBarOverlayLayout) {
            this.f787a = actionBarOverlayLayout;
        }

        public void onAnimationCancel(View view) {
            this.f787a.mCurrentActionBarTopAnimator = null;
            this.f787a.mAnimatingForFling = false;
        }

        public void onAnimationEnd(View view) {
            this.f787a.mCurrentActionBarTopAnimator = null;
            this.f787a.mAnimatingForFling = false;
        }
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarOverlayLayout.2 */
    class C03652 extends ak {
        final /* synthetic */ ActionBarOverlayLayout f788a;

        C03652(ActionBarOverlayLayout actionBarOverlayLayout) {
            this.f788a = actionBarOverlayLayout;
        }

        public void onAnimationCancel(View view) {
            this.f788a.mCurrentActionBarBottomAnimator = null;
            this.f788a.mAnimatingForFling = false;
        }

        public void onAnimationEnd(View view) {
            this.f788a.mCurrentActionBarBottomAnimator = null;
            this.f788a.mAnimatingForFling = false;
        }
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarOverlayLayout.3 */
    class C03663 implements Runnable {
        final /* synthetic */ ActionBarOverlayLayout f789a;

        C03663(ActionBarOverlayLayout actionBarOverlayLayout) {
            this.f789a = actionBarOverlayLayout;
        }

        public void run() {
            this.f789a.haltActionBarHideOffsetAnimations();
            this.f789a.mCurrentActionBarTopAnimator = C0234u.m1097k(this.f789a.mActionBarTop).m832c(0.0f).m826a(this.f789a.mTopAnimatorListener);
            if (this.f789a.mActionBarBottom != null && this.f789a.mActionBarBottom.getVisibility() != 8) {
                this.f789a.mCurrentActionBarBottomAnimator = C0234u.m1097k(this.f789a.mActionBarBottom).m832c(0.0f).m826a(this.f789a.mBottomAnimatorListener);
            }
        }
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarOverlayLayout.4 */
    class C03674 implements Runnable {
        final /* synthetic */ ActionBarOverlayLayout f790a;

        C03674(ActionBarOverlayLayout actionBarOverlayLayout) {
            this.f790a = actionBarOverlayLayout;
        }

        public void run() {
            this.f790a.haltActionBarHideOffsetAnimations();
            this.f790a.mCurrentActionBarTopAnimator = C0234u.m1097k(this.f790a.mActionBarTop).m832c((float) (-this.f790a.mActionBarTop.getHeight())).m826a(this.f790a.mTopAnimatorListener);
            if (this.f790a.mActionBarBottom != null && this.f790a.mActionBarBottom.getVisibility() != 8) {
                this.f790a.mCurrentActionBarBottomAnimator = C0234u.m1097k(this.f790a.mActionBarBottom).m832c((float) this.f790a.mActionBarBottom.getHeight()).m826a(this.f790a.mBottomAnimatorListener);
            }
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    static {
        ATTRS = new int[]{C0290a.actionBarSize, 16842841};
    }

    public ActionBarOverlayLayout(Context context) {
        super(context);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mBaseInnerInsets = new Rect();
        this.mInnerInsets = new Rect();
        this.mLastInnerInsets = new Rect();
        this.ACTION_BAR_ANIMATE_DELAY = 600;
        this.mTopAnimatorListener = new C03641(this);
        this.mBottomAnimatorListener = new C03652(this);
        this.mRemoveActionBarHideOffset = new C03663(this);
        this.mAddActionBarHideOffset = new C03674(this);
        init(context);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mBaseInnerInsets = new Rect();
        this.mInnerInsets = new Rect();
        this.mLastInnerInsets = new Rect();
        this.ACTION_BAR_ANIMATE_DELAY = 600;
        this.mTopAnimatorListener = new C03641(this);
        this.mBottomAnimatorListener = new C03652(this);
        this.mRemoveActionBarHideOffset = new C03663(this);
        this.mAddActionBarHideOffset = new C03674(this);
        init(context);
    }

    private void addActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }

    private boolean applyInsets(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5 = false;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (z && layoutParams.leftMargin != rect.left) {
            layoutParams.leftMargin = rect.left;
            z5 = true;
        }
        if (z2 && layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z5 = true;
        }
        if (z4 && layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z5 = true;
        }
        if (!z3 || layoutParams.bottomMargin == rect.bottom) {
            return z5;
        }
        layoutParams.bottomMargin = rect.bottom;
        return true;
    }

    private DecorToolbar getDecorToolbar(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void haltActionBarHideOffsetAnimations() {
        removeCallbacks(this.mRemoveActionBarHideOffset);
        removeCallbacks(this.mAddActionBarHideOffset);
        if (this.mCurrentActionBarTopAnimator != null) {
            this.mCurrentActionBarTopAnimator.m829a();
        }
        if (this.mCurrentActionBarBottomAnimator != null) {
            this.mCurrentActionBarBottomAnimator.m829a();
        }
    }

    private void init(Context context) {
        boolean z = true;
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(ATTRS);
        this.mActionBarHeight = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mWindowContentOverlay = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.mWindowContentOverlay == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion >= 19) {
            z = false;
        }
        this.mIgnoreWindowContentOverlay = z;
        this.mFlingEstimator = C0286k.m1349a(context);
    }

    private void postAddActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mAddActionBarHideOffset, 600);
    }

    private void postRemoveActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mRemoveActionBarHideOffset, 600);
    }

    private void removeActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }

    private boolean shouldHideActionBarOnFling(float f, float f2) {
        this.mFlingEstimator.m1353a(0, 0, 0, (int) f2, 0, 0, RtlSpacingHelper.UNDEFINED, Integer.MAX_VALUE);
        return this.mFlingEstimator.m1356d() > this.mActionBarTop.getHeight();
    }

    public boolean canShowOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void dismissPopups() {
        pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mWindowContentOverlay != null && !this.mIgnoreWindowContentOverlay) {
            int bottom = this.mActionBarTop.getVisibility() == 0 ? (int) ((((float) this.mActionBarTop.getBottom()) + C0234u.m1095i(this.mActionBarTop)) + 0.5f) : 0;
            this.mWindowContentOverlay.setBounds(0, bottom, getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + bottom);
            this.mWindowContentOverlay.draw(canvas);
        }
    }

    protected boolean fitSystemWindows(Rect rect) {
        pullChildren();
        boolean applyInsets = (C0234u.m1098l(this) & 256) != 0 ? applyInsets(this.mActionBarTop, rect, true, true, false, true) : applyInsets(this.mActionBarTop, rect, true, true, false, true);
        if (this.mActionBarBottom != null) {
            applyInsets |= applyInsets(this.mActionBarBottom, rect, true, false, true, true);
        }
        this.mBaseInnerInsets.set(rect);
        ViewUtils.computeFitSystemWindows(this, this.mBaseInnerInsets, this.mBaseContentInsets);
        if (!this.mLastBaseContentInsets.equals(this.mBaseContentInsets)) {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
            applyInsets = true;
        }
        if (applyInsets) {
            requestLayout();
        }
        return true;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public int getActionBarHideOffset() {
        return this.mActionBarTop != null ? -((int) C0234u.m1095i(this.mActionBarTop)) : 0;
    }

    public CharSequence getTitle() {
        pullChildren();
        return this.mDecorToolbar.getTitle();
    }

    public boolean hasIcon() {
        pullChildren();
        return this.mDecorToolbar.hasIcon();
    }

    public boolean hasLogo() {
        pullChildren();
        return this.mDecorToolbar.hasLogo();
    }

    public boolean hideOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }

    public void initFeature(int i) {
        pullChildren();
        switch (i) {
            case C0627j.View_paddingEnd /*2*/:
                this.mDecorToolbar.initProgress();
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                this.mDecorToolbar.initIndeterminateProgress();
            case C0627j.Toolbar_titleTextAppearance /*9*/:
                setOverlayMode(true);
            default:
        }
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.mHideOnContentScroll;
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    public boolean isOverflowMenuShowPending() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        init(getContext());
        C0234u.m1099m(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        haltActionBarHideOffsetAnimations();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i3 - i) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = layoutParams.leftMargin + paddingLeft;
                paddingRight = childAt == this.mActionBarBottom ? (paddingBottom - measuredHeight) - layoutParams.bottomMargin : layoutParams.topMargin + paddingTop;
                childAt.layout(i6, paddingRight, measuredWidth + i6, measuredHeight + paddingRight);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int max;
        int max2;
        int i3;
        int i4;
        pullChildren();
        measureChildWithMargins(this.mActionBarTop, i, 0, i2, 0);
        LayoutParams layoutParams = (LayoutParams) this.mActionBarTop.getLayoutParams();
        int max3 = Math.max(0, (this.mActionBarTop.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
        int max4 = Math.max(0, layoutParams.bottomMargin + (this.mActionBarTop.getMeasuredHeight() + layoutParams.topMargin));
        int combineMeasuredStates = ViewUtils.combineMeasuredStates(0, C0234u.m1094h(this.mActionBarTop));
        if (this.mActionBarBottom != null) {
            measureChildWithMargins(this.mActionBarBottom, i, 0, i2, 0);
            layoutParams = (LayoutParams) this.mActionBarBottom.getLayoutParams();
            max = Math.max(max3, (this.mActionBarBottom.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
            max2 = Math.max(max4, layoutParams.bottomMargin + (this.mActionBarBottom.getMeasuredHeight() + layoutParams.topMargin));
            max3 = ViewUtils.combineMeasuredStates(combineMeasuredStates, C0234u.m1094h(this.mActionBarBottom));
            max4 = max;
            i3 = max2;
        } else {
            i3 = max4;
            max4 = max3;
            max3 = combineMeasuredStates;
        }
        Object obj = (C0234u.m1098l(this) & 256) != 0 ? 1 : null;
        if (obj != null) {
            i4 = this.mActionBarHeight;
            if (this.mHasNonEmbeddedTabs && this.mActionBarTop.getTabContainer() != null) {
                i4 += this.mActionBarHeight;
            }
        } else {
            i4 = this.mActionBarTop.getVisibility() != 8 ? this.mActionBarTop.getMeasuredHeight() : 0;
        }
        max = (!this.mDecorToolbar.isSplit() || this.mActionBarBottom == null) ? 0 : obj != null ? this.mActionBarHeight : this.mActionBarBottom.getMeasuredHeight();
        this.mContentInsets.set(this.mBaseContentInsets);
        this.mInnerInsets.set(this.mBaseInnerInsets);
        Rect rect;
        Rect rect2;
        if (this.mOverlayMode || obj != null) {
            rect = this.mInnerInsets;
            rect.top = i4 + rect.top;
            rect2 = this.mInnerInsets;
            rect2.bottom = max + rect2.bottom;
        } else {
            rect = this.mContentInsets;
            rect.top = i4 + rect.top;
            rect2 = this.mContentInsets;
            rect2.bottom = max + rect2.bottom;
        }
        applyInsets(this.mContent, this.mContentInsets, true, true, true, true);
        if (!this.mLastInnerInsets.equals(this.mInnerInsets)) {
            this.mLastInnerInsets.set(this.mInnerInsets);
            this.mContent.dispatchFitSystemWindows(this.mInnerInsets);
        }
        measureChildWithMargins(this.mContent, i, 0, i2, 0);
        layoutParams = (LayoutParams) this.mContent.getLayoutParams();
        max = Math.max(max4, (this.mContent.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
        i4 = Math.max(i3, layoutParams.bottomMargin + (this.mContent.getMeasuredHeight() + layoutParams.topMargin));
        max2 = ViewUtils.combineMeasuredStates(max3, C0234u.m1094h(this.mContent));
        setMeasuredDimension(C0234u.m1072a(Math.max(max + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, max2), C0234u.m1072a(Math.max(i4 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i2, max2 << 16));
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.mHideOnContentScroll || !z) {
            return false;
        }
        if (shouldHideActionBarOnFling(f, f2)) {
            addActionBarHideOffset();
        } else {
            removeActionBarHideOffset();
        }
        this.mAnimatingForFling = true;
        return true;
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.mHideOnContentScrollReference += i2;
        setActionBarHideOffset(this.mHideOnContentScrollReference);
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        super.onNestedScrollAccepted(view, view2, i);
        this.mHideOnContentScrollReference = getActionBarHideOffset();
        haltActionBarHideOffsetAnimations();
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStarted();
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return ((i & 2) == 0 || this.mActionBarTop.getVisibility() != 0) ? false : this.mHideOnContentScroll;
    }

    public void onStopNestedScroll(View view) {
        super.onStopNestedScroll(view);
        if (this.mHideOnContentScroll && !this.mAnimatingForFling) {
            if (this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
                postRemoveActionBarHideOffset();
            } else {
                postAddActionBarHideOffset();
            }
        }
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStopped();
        }
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        boolean z = true;
        if (VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        pullChildren();
        int i2 = this.mLastSystemUiVisibility ^ i;
        this.mLastSystemUiVisibility = i;
        boolean z2 = (i & 4) == 0;
        boolean z3 = (i & 256) != 0;
        if (this.mActionBarVisibilityCallback != null) {
            ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
            if (z3) {
                z = false;
            }
            actionBarVisibilityCallback.enableContentAnimations(z);
            if (z2 || !z3) {
                this.mActionBarVisibilityCallback.showForSystem();
            } else {
                this.mActionBarVisibilityCallback.hideForSystem();
            }
        }
        if ((i2 & 256) != 0 && this.mActionBarVisibilityCallback != null) {
            C0234u.m1099m(this);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.mWindowVisibility = i;
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(i);
        }
    }

    void pullChildren() {
        if (this.mContent == null) {
            this.mContent = (ContentFrameLayout) findViewById(C0295f.action_bar_activity_content);
            this.mActionBarTop = (ActionBarContainer) findViewById(C0295f.action_bar_container);
            this.mDecorToolbar = getDecorToolbar(findViewById(C0295f.action_bar));
            this.mActionBarBottom = (ActionBarContainer) findViewById(C0295f.split_action_bar);
        }
    }

    public void restoreToolbarHierarchyState(SparseArray<Parcelable> sparseArray) {
        pullChildren();
        this.mDecorToolbar.restoreHierarchyState(sparseArray);
    }

    public void saveToolbarHierarchyState(SparseArray<Parcelable> sparseArray) {
        pullChildren();
        this.mDecorToolbar.saveHierarchyState(sparseArray);
    }

    public void setActionBarHideOffset(int i) {
        haltActionBarHideOffsetAnimations();
        int height = this.mActionBarTop.getHeight();
        int max = Math.max(0, Math.min(i, height));
        C0234u.m1084b(this.mActionBarTop, (float) (-max));
        if (this.mActionBarBottom != null && this.mActionBarBottom.getVisibility() != 8) {
            C0234u.m1084b(this.mActionBarBottom, (float) ((int) ((((float) max) / ((float) height)) * ((float) this.mActionBarBottom.getHeight()))));
        }
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback actionBarVisibilityCallback) {
        this.mActionBarVisibilityCallback = actionBarVisibilityCallback;
        if (getWindowToken() != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
            if (this.mLastSystemUiVisibility != 0) {
                onWindowSystemUiVisibilityChanged(this.mLastSystemUiVisibility);
                C0234u.m1099m(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.mHasNonEmbeddedTabs = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.mHideOnContentScroll) {
            this.mHideOnContentScroll = z;
            if (!z) {
                if (C0334a.m1648a()) {
                    stopNestedScroll();
                }
                haltActionBarHideOffsetAnimations();
                setActionBarHideOffset(0);
            }
        }
    }

    public void setIcon(int i) {
        pullChildren();
        this.mDecorToolbar.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        pullChildren();
        this.mDecorToolbar.setIcon(drawable);
    }

    public void setLogo(int i) {
        pullChildren();
        this.mDecorToolbar.setLogo(i);
    }

    public void setMenu(Menu menu, Callback callback) {
        pullChildren();
        this.mDecorToolbar.setMenu(menu, callback);
    }

    public void setMenuPrepared() {
        pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }

    public void setOverlayMode(boolean z) {
        this.mOverlayMode = z;
        boolean z2 = z && getContext().getApplicationInfo().targetSdkVersion < 19;
        this.mIgnoreWindowContentOverlay = z2;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    public void setWindowCallback(C0317b c0317b) {
        pullChildren();
        this.mDecorToolbar.setWindowCallback(c0317b);
    }

    public void setWindowTitle(CharSequence charSequence) {
        pullChildren();
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean showOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }
}
