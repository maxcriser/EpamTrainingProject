package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.p001b.p002a.C0074a;
import android.support.v4.view.C0187d;
import android.support.v4.view.C0187d.C0185a;
import android.support.v7.internal.p013c.C0336a;
import android.support.v7.internal.view.C0337a;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.internal.view.menu.ActionMenuItemView.PopupCallback;
import android.support.v7.internal.view.menu.BaseMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.TintImageView;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0296g;
import android.support.v7.p009a.C0301a.C0297h;
import android.support.v7.widget.ActionMenuView.ActionMenuChildView;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

public class ActionMenuPresenter extends BaseMenuPresenter implements C0185a {
    private static final String TAG = "ActionMenuPresenter";
    private final SparseBooleanArray mActionButtonGroups;
    private C0403a mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    private View mOverflowButton;
    private C0408e mOverflowPopup;
    private C0404b mPopupCallback;
    final C0409f mPopupPresenterCallback;
    private C0405c mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        public int f852a;

        /* renamed from: android.support.v7.widget.ActionMenuPresenter.SavedState.1 */
        static class C04021 implements Creator<SavedState> {
            C04021() {
            }

            public SavedState m1765a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m1766a(int i) {
                return new SavedState[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m1765a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m1766a(i);
            }
        }

        static {
            CREATOR = new C04021();
        }

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.f852a = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f852a);
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter.a */
    private class C0403a extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter f853a;
        private SubMenuBuilder f854b;

        public C0403a(ActionMenuPresenter actionMenuPresenter, Context context, SubMenuBuilder subMenuBuilder) {
            boolean z = false;
            this.f853a = actionMenuPresenter;
            super(context, subMenuBuilder, null, false, C0290a.actionOverflowMenuStyle);
            this.f854b = subMenuBuilder;
            if (!((MenuItemImpl) subMenuBuilder.getItem()).isActionButton()) {
                setAnchorView(actionMenuPresenter.mOverflowButton == null ? (View) actionMenuPresenter.mMenuView : actionMenuPresenter.mOverflowButton);
            }
            setCallback(actionMenuPresenter.mPopupPresenterCallback);
            int size = subMenuBuilder.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = subMenuBuilder.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
            }
            setForceShowIcon(z);
        }

        public void onDismiss() {
            super.onDismiss();
            this.f853a.mActionButtonPopup = null;
            this.f853a.mOpenSubMenuId = 0;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter.b */
    private class C0404b extends PopupCallback {
        final /* synthetic */ ActionMenuPresenter f855a;

        private C0404b(ActionMenuPresenter actionMenuPresenter) {
            this.f855a = actionMenuPresenter;
        }

        public ListPopupWindow getPopup() {
            return this.f855a.mActionButtonPopup != null ? this.f855a.mActionButtonPopup.getPopup() : null;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter.c */
    private class C0405c implements Runnable {
        final /* synthetic */ ActionMenuPresenter f856a;
        private C0408e f857b;

        public C0405c(ActionMenuPresenter actionMenuPresenter, C0408e c0408e) {
            this.f856a = actionMenuPresenter;
            this.f857b = c0408e;
        }

        public void run() {
            this.f856a.mMenu.changeMenuMode();
            View view = (View) this.f856a.mMenuView;
            if (!(view == null || view.getWindowToken() == null || !this.f857b.tryShow())) {
                this.f856a.mOverflowPopup = this.f857b;
            }
            this.f856a.mPostedOpenRunnable = null;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter.d */
    private class C0407d extends TintImageView implements ActionMenuChildView {
        final /* synthetic */ ActionMenuPresenter f860a;
        private final float[] f861b;

        /* renamed from: android.support.v7.widget.ActionMenuPresenter.d.1 */
        class C04061 extends ForwardingListener {
            final /* synthetic */ ActionMenuPresenter f858a;
            final /* synthetic */ C0407d f859b;

            C04061(C0407d c0407d, View view, ActionMenuPresenter actionMenuPresenter) {
                this.f859b = c0407d;
                this.f858a = actionMenuPresenter;
                super(view);
            }

            public ListPopupWindow getPopup() {
                return this.f859b.f860a.mOverflowPopup == null ? null : this.f859b.f860a.mOverflowPopup.getPopup();
            }

            public boolean onForwardingStarted() {
                this.f859b.f860a.showOverflowMenu();
                return true;
            }

            public boolean onForwardingStopped() {
                if (this.f859b.f860a.mPostedOpenRunnable != null) {
                    return false;
                }
                this.f859b.f860a.hideOverflowMenu();
                return true;
            }
        }

        public C0407d(ActionMenuPresenter actionMenuPresenter, Context context) {
            this.f860a = actionMenuPresenter;
            super(context, null, C0290a.actionOverflowButtonStyle);
            this.f861b = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            setOnTouchListener(new C04061(this, this, actionMenuPresenter));
        }

        public boolean needsDividerAfter() {
            return false;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public boolean performClick() {
            if (!super.performClick()) {
                playSoundEffect(0);
                this.f860a.showOverflowMenu();
            }
            return true;
        }

        protected boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                float[] fArr = this.f861b;
                fArr[0] = (float) drawable.getBounds().centerX();
                getImageMatrix().mapPoints(fArr);
                int width = ((int) fArr[0]) - (getWidth() / 2);
                C0074a.m370a(background, width, 0, getWidth() + width, getHeight());
            }
            return frame;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter.e */
    private class C0408e extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter f862a;

        public C0408e(ActionMenuPresenter actionMenuPresenter, Context context, MenuBuilder menuBuilder, View view, boolean z) {
            this.f862a = actionMenuPresenter;
            super(context, menuBuilder, view, z, C0290a.actionOverflowMenuStyle);
            setGravity(8388613);
            setCallback(actionMenuPresenter.mPopupPresenterCallback);
        }

        public void onDismiss() {
            super.onDismiss();
            this.f862a.mMenu.close();
            this.f862a.mOverflowPopup = null;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter.f */
    private class C0409f implements Callback {
        final /* synthetic */ ActionMenuPresenter f863a;

        private C0409f(ActionMenuPresenter actionMenuPresenter) {
            this.f863a = actionMenuPresenter;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                ((SubMenuBuilder) menuBuilder).getRootMenu().close(false);
            }
            Callback callback = this.f863a.getCallback();
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z);
            }
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            this.f863a.mOpenSubMenuId = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            Callback callback = this.f863a.getCallback();
            return callback != null ? callback.onOpenSubMenu(menuBuilder) : false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, C0297h.abc_action_menu_layout, C0297h.abc_action_menu_item_layout);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new C0409f();
    }

    private View findViewForItem(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof ItemView) && ((ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public void bindItemView(MenuItemImpl menuItemImpl, ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new C0404b();
        }
        actionMenuItemView.setPopupCallback(this.mPopupCallback);
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        return viewGroup.getChildAt(i) == this.mOverflowButton ? false : super.filterLeftoverView(viewGroup, i);
    }

    public boolean flagActionItems() {
        int i;
        ArrayList visibleItems = this.mMenu.getVisibleItems();
        int size = visibleItems.size();
        int i2 = this.mMaxItems;
        int i3 = this.mActionItemWidthLimit;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        int i4 = 0;
        int i5 = 0;
        Object obj = null;
        int i6 = 0;
        while (i6 < size) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) visibleItems.get(i6);
            if (menuItemImpl.requiresActionButton()) {
                i4++;
            } else if (menuItemImpl.requestsActionButton()) {
                i5++;
            } else {
                obj = 1;
            }
            i = (this.mExpandedActionViewsExclusive && menuItemImpl.isActionViewExpanded()) ? 0 : i2;
            i6++;
            i2 = i;
        }
        if (this.mReserveOverflow && (r4 != null || i4 + i5 > i2)) {
            i2--;
        }
        i6 = i2 - i4;
        SparseBooleanArray sparseBooleanArray = this.mActionButtonGroups;
        sparseBooleanArray.clear();
        i = 0;
        if (this.mStrictWidthLimit) {
            i = i3 / this.mMinCellSize;
            i5 = ((i3 % this.mMinCellSize) / i) + this.mMinCellSize;
        } else {
            i5 = 0;
        }
        int i7 = 0;
        i2 = 0;
        int i8 = i;
        while (i7 < size) {
            menuItemImpl = (MenuItemImpl) visibleItems.get(i7);
            int i9;
            if (menuItemImpl.requiresActionButton()) {
                View itemView = getItemView(menuItemImpl, this.mScrapActionButtonView, viewGroup);
                if (this.mScrapActionButtonView == null) {
                    this.mScrapActionButtonView = itemView;
                }
                if (this.mStrictWidthLimit) {
                    i8 -= ActionMenuView.measureChildForCells(itemView, i5, i8, makeMeasureSpec, 0);
                } else {
                    itemView.measure(makeMeasureSpec, makeMeasureSpec);
                }
                i4 = itemView.getMeasuredWidth();
                i9 = i3 - i4;
                if (i2 != 0) {
                    i4 = i2;
                }
                i2 = menuItemImpl.getGroupId();
                if (i2 != 0) {
                    sparseBooleanArray.put(i2, true);
                }
                menuItemImpl.setIsActionButton(true);
                i = i9;
                i2 = i6;
            } else if (menuItemImpl.requestsActionButton()) {
                boolean z;
                int groupId = menuItemImpl.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId);
                boolean z3 = (i6 > 0 || z2) && i3 > 0 && (!this.mStrictWidthLimit || i8 > 0);
                if (z3) {
                    View itemView2 = getItemView(menuItemImpl, this.mScrapActionButtonView, viewGroup);
                    if (this.mScrapActionButtonView == null) {
                        this.mScrapActionButtonView = itemView2;
                    }
                    boolean z4;
                    if (this.mStrictWidthLimit) {
                        int measureChildForCells = ActionMenuView.measureChildForCells(itemView2, i5, i8, makeMeasureSpec, 0);
                        i9 = i8 - measureChildForCells;
                        if (measureChildForCells == 0) {
                            i8 = 0;
                        } else {
                            z4 = z3;
                        }
                        i4 = i9;
                    } else {
                        itemView2.measure(makeMeasureSpec, makeMeasureSpec);
                        boolean z5 = z3;
                        i4 = i8;
                        z4 = z5;
                    }
                    i9 = itemView2.getMeasuredWidth();
                    i3 -= i9;
                    if (i2 == 0) {
                        i2 = i9;
                    }
                    if (this.mStrictWidthLimit) {
                        z = i8 & (i3 >= 0 ? 1 : 0);
                        i9 = i2;
                        i2 = i4;
                    } else {
                        z = i8 & (i3 + i2 > 0 ? 1 : 0);
                        i9 = i2;
                        i2 = i4;
                    }
                } else {
                    z = z3;
                    i9 = i2;
                    i2 = i8;
                }
                if (z && groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                    i8 = i6;
                } else if (z2) {
                    sparseBooleanArray.put(groupId, false);
                    i4 = i6;
                    for (i6 = 0; i6 < i7; i6++) {
                        MenuItemImpl menuItemImpl2 = (MenuItemImpl) visibleItems.get(i6);
                        if (menuItemImpl2.getGroupId() == groupId) {
                            if (menuItemImpl2.isActionButton()) {
                                i4++;
                            }
                            menuItemImpl2.setIsActionButton(false);
                        }
                    }
                    i8 = i4;
                } else {
                    i8 = i6;
                }
                if (z) {
                    i8--;
                }
                menuItemImpl.setIsActionButton(z);
                i4 = i9;
                i = i3;
                int i10 = i2;
                i2 = i8;
                i8 = i10;
            } else {
                menuItemImpl.setIsActionButton(false);
                i4 = i2;
                i = i3;
                i2 = i6;
            }
            i7++;
            i3 = i;
            i6 = i2;
            i2 = i4;
        }
        return true;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            actionView = super.getItemView(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuView menuView = super.getMenuView(viewGroup);
        ((ActionMenuView) menuView).setPresenter(this);
        return menuView;
    }

    public boolean hideOverflowMenu() {
        if (this.mPostedOpenRunnable == null || this.mMenuView == null) {
            MenuPopupHelper menuPopupHelper = this.mOverflowPopup;
            if (menuPopupHelper == null) {
                return false;
            }
            menuPopupHelper.dismiss();
            return true;
        }
        ((View) this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
        this.mPostedOpenRunnable = null;
        return true;
    }

    public boolean hideSubMenus() {
        if (this.mActionButtonPopup == null) {
            return false;
        }
        this.mActionButtonPopup.dismiss();
        return true;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        Resources resources = context.getResources();
        C0337a a = C0337a.m1650a(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = a.m1652b();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = a.m1653c();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = a.m1651a();
        }
        int i = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new C0407d(this, this.mSystemContext);
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = i;
        this.mMinCellSize = (int) (56.0f * resources.getDisplayMetrics().density);
        this.mScrapActionButtonView = null;
    }

    public boolean isOverflowMenuShowPending() {
        return this.mPostedOpenRunnable != null || isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowing() {
        return this.mOverflowPopup != null && this.mOverflowPopup.isShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        dismissPopupMenus();
        super.onCloseMenu(menuBuilder, z);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mMaxItemsSet) {
            this.mMaxItems = this.mContext.getResources().getInteger(C0296g.abc_max_action_buttons);
        }
        if (this.mMenu != null) {
            this.mMenu.onItemsChanged(true);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        if (savedState.f852a > 0) {
            MenuItem findItem = this.mMenu.findItem(savedState.f852a);
            if (findItem != null) {
                onSubMenuSelected((SubMenuBuilder) findItem.getSubMenu());
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState();
        savedState.f852a = this.mOpenSubMenuId;
        return savedState;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.getParentMenu() != this.mMenu) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.getParentMenu();
        }
        View findViewForItem = findViewForItem(subMenuBuilder2.getItem());
        if (findViewForItem == null) {
            if (this.mOverflowButton == null) {
                return false;
            }
            findViewForItem = this.mOverflowButton;
        }
        this.mOpenSubMenuId = subMenuBuilder.getItem().getItemId();
        this.mActionButtonPopup = new C0403a(this, this.mContext, subMenuBuilder);
        this.mActionButtonPopup.setAnchorView(findViewForItem);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(subMenuBuilder);
        return true;
    }

    public void onSubUiVisibilityChanged(boolean z) {
        if (z) {
            super.onSubMenuSelected(null);
        } else {
            this.mMenu.close(false);
        }
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mExpandedActionViewsExclusive = z;
    }

    public void setItemLimit(int i) {
        this.mMaxItems = i;
        this.mMaxItemsSet = true;
    }

    public void setMenuView(ActionMenuView actionMenuView) {
        this.mMenuView = actionMenuView;
        actionMenuView.initialize(this.mMenu);
    }

    public void setReserveOverflow(boolean z) {
        this.mReserveOverflow = z;
        this.mReserveOverflowSet = true;
    }

    public void setWidthLimit(int i, boolean z) {
        this.mWidthLimit = i;
        this.mStrictWidthLimit = z;
        this.mWidthLimitSet = true;
    }

    public boolean shouldIncludeItem(int i, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public boolean showOverflowMenu() {
        if (!this.mReserveOverflow || isOverflowMenuShowing() || this.mMenu == null || this.mMenuView == null || this.mPostedOpenRunnable != null || this.mMenu.getNonActionItems().isEmpty()) {
            return false;
        }
        this.mPostedOpenRunnable = new C0405c(this, new C0408e(this, this.mContext, this.mMenu, this.mOverflowButton, true));
        ((View) this.mMenuView).post(this.mPostedOpenRunnable);
        super.onSubMenuSelected(null);
        return true;
    }

    public void updateMenuView(boolean z) {
        int i;
        int i2 = 1;
        int i3 = 0;
        ViewGroup viewGroup = (ViewGroup) ((View) this.mMenuView).getParent();
        if (viewGroup != null) {
            C0336a.m1649a(viewGroup);
        }
        super.updateMenuView(z);
        ((View) this.mMenuView).requestLayout();
        if (this.mMenu != null) {
            ArrayList actionItems = this.mMenu.getActionItems();
            int size = actionItems.size();
            for (i = 0; i < size; i++) {
                C0187d supportActionProvider = ((MenuItemImpl) actionItems.get(i)).getSupportActionProvider();
                if (supportActionProvider != null) {
                    supportActionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList nonActionItems = this.mMenu != null ? this.mMenu.getNonActionItems() : null;
        if (this.mReserveOverflow && nonActionItems != null) {
            i = nonActionItems.size();
            if (i == 1) {
                i3 = !((MenuItemImpl) nonActionItems.get(0)).isActionViewExpanded() ? 1 : 0;
            } else {
                if (i <= 0) {
                    i2 = 0;
                }
                i3 = i2;
            }
        }
        if (i3 != 0) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new C0407d(this, this.mSystemContext);
            }
            viewGroup = (ViewGroup) this.mOverflowButton.getParent();
            if (viewGroup != this.mMenuView) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.mOverflowButton);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.mMenuView;
                actionMenuView.addView(this.mOverflowButton, actionMenuView.generateOverflowButtonLayoutParams());
            }
        } else if (this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
            ((ViewGroup) this.mMenuView).removeView(this.mOverflowButton);
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }
}
