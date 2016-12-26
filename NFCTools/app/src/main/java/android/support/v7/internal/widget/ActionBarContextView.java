package android.support.v7.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.C0234u;
import android.support.v4.view.af;
import android.support.v4.view.aj;
import android.support.v7.internal.view.C0345e;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0295f;
import android.support.v7.p009a.C0301a.C0297h;
import android.support.v7.p009a.C0301a.C0300k;
import android.support.v7.p010b.C0320a;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBarContextView extends AbsActionBarView implements aj {
    private static final int ANIMATE_IDLE = 0;
    private static final int ANIMATE_IN = 1;
    private static final int ANIMATE_OUT = 2;
    private static final String TAG = "ActionBarContextView";
    private boolean mAnimateInOnLayout;
    private int mAnimationMode;
    private View mClose;
    private int mCloseItemLayout;
    private C0345e mCurrentAnimation;
    private View mCustomView;
    private Drawable mSplitBackground;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    /* renamed from: android.support.v7.internal.widget.ActionBarContextView.1 */
    class C03631 implements OnClickListener {
        final /* synthetic */ C0320a f785a;
        final /* synthetic */ ActionBarContextView f786b;

        C03631(ActionBarContextView actionBarContextView, C0320a c0320a) {
            this.f786b = actionBarContextView;
            this.f785a = c0320a;
        }

        public void onClick(View view) {
            this.f785a.m1559c();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0290a.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0300k.ActionMode, i, ANIMATE_IDLE);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(C0300k.ActionMode_background));
        this.mTitleStyleRes = obtainStyledAttributes.getResourceId(C0300k.ActionMode_titleTextStyle, ANIMATE_IDLE);
        this.mSubtitleStyleRes = obtainStyledAttributes.getResourceId(C0300k.ActionMode_subtitleTextStyle, ANIMATE_IDLE);
        this.mContentHeight = obtainStyledAttributes.getLayoutDimension(C0300k.ActionMode_height, (int) ANIMATE_IDLE);
        this.mSplitBackground = obtainStyledAttributes.getDrawable(C0300k.ActionMode_backgroundSplit);
        this.mCloseItemLayout = obtainStyledAttributes.getResourceId(C0300k.ActionMode_closeItemLayout, C0297h.abc_action_mode_close_item_material);
        obtainStyledAttributes.recycle();
    }

    private void finishAnimation() {
        C0345e c0345e = this.mCurrentAnimation;
        if (c0345e != null) {
            this.mCurrentAnimation = null;
            c0345e.m1707b();
        }
    }

    private void initTitle() {
        int i = 8;
        Object obj = ANIMATE_IN;
        if (this.mTitleLayout == null) {
            LayoutInflater.from(getContext()).inflate(C0297h.abc_action_bar_title_item, this);
            this.mTitleLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleView = (TextView) this.mTitleLayout.findViewById(C0295f.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(C0295f.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        Object obj2 = !TextUtils.isEmpty(this.mTitle) ? ANIMATE_IN : ANIMATE_IDLE;
        if (TextUtils.isEmpty(this.mSubtitle)) {
            obj = ANIMATE_IDLE;
        }
        this.mSubtitleView.setVisibility(obj != null ? ANIMATE_IDLE : 8);
        LinearLayout linearLayout = this.mTitleLayout;
        if (!(obj2 == null && obj == null)) {
            i = ANIMATE_IDLE;
        }
        linearLayout.setVisibility(i);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    private C0345e makeInAnimation() {
        C0234u.m1074a(this.mClose, (float) ((-this.mClose.getWidth()) - ((MarginLayoutParams) this.mClose.getLayoutParams()).leftMargin));
        af b = C0234u.m1097k(this.mClose).m830b(0.0f);
        b.m825a(200);
        b.m826a((aj) this);
        b.m828a(new DecelerateInterpolator());
        C0345e c0345e = new C0345e();
        c0345e.m1703a(b);
        if (this.mMenuView != null) {
            int childCount = this.mMenuView.getChildCount();
            if (childCount > 0) {
                int i = childCount - 1;
                childCount = ANIMATE_IDLE;
                while (i >= 0) {
                    View childAt = this.mMenuView.getChildAt(i);
                    C0234u.m1089d(childAt, 0.0f);
                    af d = C0234u.m1097k(childAt).m833d(1.0f);
                    d.m825a(300);
                    c0345e.m1703a(d);
                    i--;
                    childCount += ANIMATE_IN;
                }
            }
        }
        return c0345e;
    }

    private C0345e makeOutAnimation() {
        af b = C0234u.m1097k(this.mClose).m830b((float) ((-this.mClose.getWidth()) - ((MarginLayoutParams) this.mClose.getLayoutParams()).leftMargin));
        b.m825a(200);
        b.m826a((aj) this);
        b.m828a(new DecelerateInterpolator());
        C0345e c0345e = new C0345e();
        c0345e.m1703a(b);
        return (this.mMenuView == null || this.mMenuView.getChildCount() > 0) ? c0345e : c0345e;
    }

    public /* bridge */ /* synthetic */ void animateToVisibility(int i) {
        super.animateToVisibility(i);
    }

    public /* bridge */ /* synthetic */ boolean canShowOverflowMenu() {
        return super.canShowOverflowMenu();
    }

    public void closeMode() {
        if (this.mAnimationMode != ANIMATE_OUT) {
            if (this.mClose == null) {
                killMode();
                return;
            }
            finishAnimation();
            this.mAnimationMode = ANIMATE_OUT;
            this.mCurrentAnimation = makeOutAnimation();
            this.mCurrentAnimation.m1706a();
        }
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.hideOverflowMenu() : false;
    }

    public void initForMode(C0320a c0320a) {
        if (this.mClose == null) {
            this.mClose = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, this, false);
            addView(this.mClose);
        } else if (this.mClose.getParent() == null) {
            addView(this.mClose);
        }
        this.mClose.findViewById(C0295f.action_mode_close_button).setOnClickListener(new C03631(this, c0320a));
        MenuBuilder menuBuilder = (MenuBuilder) c0320a.m1556b();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        this.mActionMenuPresenter = new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter.setReserveOverflow(true);
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        if (this.mSplitActionBar) {
            this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
            this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
            layoutParams.width = -1;
            layoutParams.height = this.mContentHeight;
            menuBuilder.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
            this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
            this.mMenuView.setBackgroundDrawable(this.mSplitBackground);
            this.mSplitView.addView(this.mMenuView, layoutParams);
        } else {
            menuBuilder.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
            this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
            this.mMenuView.setBackgroundDrawable(null);
            addView(this.mMenuView, layoutParams);
        }
        this.mAnimateInOnLayout = true;
    }

    public /* bridge */ /* synthetic */ boolean isOverflowMenuShowPending() {
        return super.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.isOverflowMenuShowing() : false;
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }

    public void killMode() {
        finishAnimation();
        removeAllViews();
        if (this.mSplitView != null) {
            this.mSplitView.removeView(this.mMenuView);
        }
        this.mCustomView = null;
        this.mMenuView = null;
        this.mAnimateInOnLayout = false;
    }

    public void onAnimationCancel(View view) {
    }

    public void onAnimationEnd(View view) {
        if (this.mAnimationMode == ANIMATE_OUT) {
            killMode();
        }
        this.mAnimationMode = ANIMATE_IDLE;
    }

    public void onAnimationStart(View view) {
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (VERSION.SDK_INT < 14) {
            return;
        }
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.mTitle);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingRight = isLayoutRtl ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        if (!(this.mClose == null || this.mClose.getVisibility() == 8)) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mClose.getLayoutParams();
            i5 = isLayoutRtl ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = isLayoutRtl ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            i5 = AbsActionBarView.next(paddingRight, i5, isLayoutRtl);
            paddingRight = AbsActionBarView.next(positionChild(this.mClose, i5, paddingTop, paddingTop2, isLayoutRtl) + i5, i6, isLayoutRtl);
            if (this.mAnimateInOnLayout) {
                this.mAnimationMode = ANIMATE_IN;
                this.mCurrentAnimation = makeInAnimation();
                this.mCurrentAnimation.m1706a();
                this.mAnimateInOnLayout = false;
            }
        }
        i5 = paddingRight;
        if (!(this.mTitleLayout == null || this.mCustomView != null || this.mTitleLayout.getVisibility() == 8)) {
            i5 += positionChild(this.mTitleLayout, i5, paddingTop, paddingTop2, isLayoutRtl);
        }
        if (this.mCustomView != null) {
            int positionChild = positionChild(this.mCustomView, i5, paddingTop, paddingTop2, isLayoutRtl) + i5;
        }
        i5 = isLayoutRtl ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        if (this.mMenuView != null) {
            positionChild = positionChild(this.mMenuView, i5, paddingTop, paddingTop2, !isLayoutRtl) + i5;
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 1073741824;
        int i4 = ANIMATE_IDLE;
        if (MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        } else {
            int measureChildView;
            int size = MeasureSpec.getSize(i);
            int size2 = this.mContentHeight > 0 ? this.mContentHeight : MeasureSpec.getSize(i2);
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i5 = size2 - paddingTop;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i5, RtlSpacingHelper.UNDEFINED);
            if (this.mClose != null) {
                measureChildView = measureChildView(this.mClose, paddingLeft, makeMeasureSpec, ANIMATE_IDLE);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mClose.getLayoutParams();
                paddingLeft = measureChildView - (marginLayoutParams.rightMargin + marginLayoutParams.leftMargin);
            }
            if (this.mMenuView != null && this.mMenuView.getParent() == this) {
                paddingLeft = measureChildView(this.mMenuView, paddingLeft, makeMeasureSpec, ANIMATE_IDLE);
            }
            if (this.mTitleLayout != null && this.mCustomView == null) {
                if (this.mTitleOptional) {
                    this.mTitleLayout.measure(MeasureSpec.makeMeasureSpec(ANIMATE_IDLE, ANIMATE_IDLE), makeMeasureSpec);
                    measureChildView = this.mTitleLayout.getMeasuredWidth();
                    makeMeasureSpec = measureChildView <= paddingLeft ? ANIMATE_IN : ANIMATE_IDLE;
                    if (makeMeasureSpec != 0) {
                        paddingLeft -= measureChildView;
                    }
                    this.mTitleLayout.setVisibility(makeMeasureSpec != 0 ? ANIMATE_IDLE : 8);
                } else {
                    paddingLeft = measureChildView(this.mTitleLayout, paddingLeft, makeMeasureSpec, ANIMATE_IDLE);
                }
            }
            if (this.mCustomView != null) {
                LayoutParams layoutParams = this.mCustomView.getLayoutParams();
                makeMeasureSpec = layoutParams.width != -2 ? 1073741824 : RtlSpacingHelper.UNDEFINED;
                if (layoutParams.width >= 0) {
                    paddingLeft = Math.min(layoutParams.width, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i3 = RtlSpacingHelper.UNDEFINED;
                }
                this.mCustomView.measure(MeasureSpec.makeMeasureSpec(paddingLeft, makeMeasureSpec), MeasureSpec.makeMeasureSpec(layoutParams.height >= 0 ? Math.min(layoutParams.height, i5) : i5, i3));
            }
            if (this.mContentHeight <= 0) {
                makeMeasureSpec = getChildCount();
                size2 = ANIMATE_IDLE;
                while (i4 < makeMeasureSpec) {
                    paddingLeft = getChildAt(i4).getMeasuredHeight() + paddingTop;
                    if (paddingLeft <= size2) {
                        paddingLeft = size2;
                    }
                    i4 += ANIMATE_IN;
                    size2 = paddingLeft;
                }
                setMeasuredDimension(size, size2);
                return;
            }
            setMeasuredDimension(size, size2);
        }
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public void setContentHeight(int i) {
        this.mContentHeight = i;
    }

    public void setCustomView(View view) {
        if (this.mCustomView != null) {
            removeView(this.mCustomView);
        }
        this.mCustomView = view;
        if (this.mTitleLayout != null) {
            removeView(this.mTitleLayout);
            this.mTitleLayout = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSplitToolbar(boolean z) {
        if (this.mSplitActionBar != z) {
            if (this.mActionMenuPresenter != null) {
                LayoutParams layoutParams = new LayoutParams(-2, -1);
                ViewGroup viewGroup;
                if (z) {
                    this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
                    layoutParams.width = -1;
                    layoutParams.height = this.mContentHeight;
                    this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
                    this.mMenuView.setBackgroundDrawable(this.mSplitBackground);
                    viewGroup = (ViewGroup) this.mMenuView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(this.mMenuView);
                    }
                    this.mSplitView.addView(this.mMenuView, layoutParams);
                } else {
                    this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
                    this.mMenuView.setBackgroundDrawable(null);
                    viewGroup = (ViewGroup) this.mMenuView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(this.mMenuView);
                    }
                    addView(this.mMenuView, layoutParams);
                }
            }
            super.setSplitToolbar(z);
        }
    }

    public /* bridge */ /* synthetic */ void setSplitView(ViewGroup viewGroup) {
        super.setSplitView(viewGroup);
    }

    public /* bridge */ /* synthetic */ void setSplitWhenNarrow(boolean z) {
        super.setSplitWhenNarrow(z);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        initTitle();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        initTitle();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = z;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.showOverflowMenu() : false;
    }
}
