package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0293d;
import android.support.v7.p009a.C0301a.C0297h;
import android.support.v7.widget.ListPopupWindow;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import java.util.ArrayList;

public class MenuPopupHelper implements MenuPresenter, OnKeyListener, OnGlobalLayoutListener, OnItemClickListener, OnDismissListener {
    static final int ITEM_LAYOUT;
    private static final String TAG = "MenuPopupHelper";
    private final C0355a mAdapter;
    private View mAnchorView;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity;
    boolean mForceShowIcon;
    private boolean mHasContentWidth;
    private final LayoutInflater mInflater;
    private ViewGroup mMeasureParent;
    private final MenuBuilder mMenu;
    private final boolean mOverflowOnly;
    private ListPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;
    private ViewTreeObserver mTreeObserver;

    /* renamed from: android.support.v7.internal.view.menu.MenuPopupHelper.a */
    private class C0355a extends BaseAdapter {
        final /* synthetic */ MenuPopupHelper f765a;
        private MenuBuilder f766b;
        private int f767c;

        public C0355a(MenuPopupHelper menuPopupHelper, MenuBuilder menuBuilder) {
            this.f765a = menuPopupHelper;
            this.f767c = -1;
            this.f766b = menuBuilder;
            m1716a();
        }

        public MenuItemImpl m1715a(int i) {
            ArrayList nonActionItems = this.f765a.mOverflowOnly ? this.f766b.getNonActionItems() : this.f766b.getVisibleItems();
            if (this.f767c >= 0 && i >= this.f767c) {
                i++;
            }
            return (MenuItemImpl) nonActionItems.get(i);
        }

        void m1716a() {
            MenuItemImpl expandedItem = this.f765a.mMenu.getExpandedItem();
            if (expandedItem != null) {
                ArrayList nonActionItems = this.f765a.mMenu.getNonActionItems();
                int size = nonActionItems.size();
                for (int i = MenuPopupHelper.ITEM_LAYOUT; i < size; i++) {
                    if (((MenuItemImpl) nonActionItems.get(i)) == expandedItem) {
                        this.f767c = i;
                        return;
                    }
                }
            }
            this.f767c = -1;
        }

        public int getCount() {
            ArrayList nonActionItems = this.f765a.mOverflowOnly ? this.f766b.getNonActionItems() : this.f766b.getVisibleItems();
            return this.f767c < 0 ? nonActionItems.size() : nonActionItems.size() - 1;
        }

        public /* synthetic */ Object getItem(int i) {
            return m1715a(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = view == null ? this.f765a.mInflater.inflate(MenuPopupHelper.ITEM_LAYOUT, viewGroup, false) : view;
            ItemView itemView = (ItemView) inflate;
            if (this.f765a.mForceShowIcon) {
                ((ListMenuItemView) inflate).setForceShowIcon(true);
            }
            itemView.initialize(m1715a(i), MenuPopupHelper.ITEM_LAYOUT);
            return inflate;
        }

        public void notifyDataSetChanged() {
            m1716a();
            super.notifyDataSetChanged();
        }
    }

    static {
        ITEM_LAYOUT = C0297h.abc_popup_menu_item_layout;
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder) {
        this(context, menuBuilder, null, false, C0290a.popupMenuStyle);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view) {
        this(context, menuBuilder, view, false, C0290a.popupMenuStyle);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i) {
        this(context, menuBuilder, view, z, i, ITEM_LAYOUT);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i, int i2) {
        this.mDropDownGravity = ITEM_LAYOUT;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMenu = menuBuilder;
        this.mAdapter = new C0355a(this, this.mMenu);
        this.mOverflowOnly = z;
        this.mPopupStyleAttr = i;
        this.mPopupStyleRes = i2;
        Resources resources = context.getResources();
        this.mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0293d.abc_config_prefDialogWidth));
        this.mAnchorView = view;
        menuBuilder.addMenuPresenter(this, context);
    }

    private int measureContentWidth() {
        ListAdapter listAdapter = this.mAdapter;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(ITEM_LAYOUT, ITEM_LAYOUT);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(ITEM_LAYOUT, ITEM_LAYOUT);
        int count = listAdapter.getCount();
        int i = ITEM_LAYOUT;
        int i2 = ITEM_LAYOUT;
        View view = null;
        int i3 = ITEM_LAYOUT;
        while (i < count) {
            View view2;
            int itemViewType = listAdapter.getItemViewType(i);
            if (itemViewType != i2) {
                i2 = itemViewType;
                view2 = null;
            } else {
                view2 = view;
            }
            if (this.mMeasureParent == null) {
                this.mMeasureParent = new FrameLayout(this.mContext);
            }
            view = listAdapter.getView(i, view2, this.mMeasureParent);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            itemViewType = view.getMeasuredWidth();
            if (itemViewType >= this.mPopupMaxWidth) {
                return this.mPopupMaxWidth;
            }
            if (itemViewType <= i3) {
                itemViewType = i3;
            }
            i++;
            i3 = itemViewType;
        }
        return i3;
    }

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean flagActionItems() {
        return false;
    }

    public int getId() {
        return ITEM_LAYOUT;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }

    public ListPopupWindow getPopup() {
        return this.mPopup;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
    }

    public boolean isShowing() {
        return this.mPopup != null && this.mPopup.isShowing();
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.mMenu) {
            dismiss();
            if (this.mPresenterCallback != null) {
                this.mPresenterCallback.onCloseMenu(menuBuilder, z);
            }
        }
    }

    public void onDismiss() {
        this.mPopup = null;
        this.mMenu.close();
        if (this.mTreeObserver != null) {
            if (!this.mTreeObserver.isAlive()) {
                this.mTreeObserver = this.mAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this);
            this.mTreeObserver = null;
        }
    }

    public void onGlobalLayout() {
        if (isShowing()) {
            View view = this.mAnchorView;
            if (view == null || !view.isShown()) {
                dismiss();
            } else if (isShowing()) {
                this.mPopup.show();
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C0355a c0355a = this.mAdapter;
        c0355a.f766b.performItemAction(c0355a.m1715a(i), ITEM_LAYOUT);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            boolean z;
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.mContext, subMenuBuilder, this.mAnchorView);
            menuPopupHelper.setCallback(this.mPresenterCallback);
            int size = subMenuBuilder.size();
            for (int i = ITEM_LAYOUT; i < size; i++) {
                MenuItem item = subMenuBuilder.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
            }
            z = false;
            menuPopupHelper.setForceShowIcon(z);
            if (menuPopupHelper.tryShow()) {
                if (this.mPresenterCallback == null) {
                    return true;
                }
                this.mPresenterCallback.onOpenSubMenu(subMenuBuilder);
                return true;
            }
        }
        return false;
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    public void setCallback(Callback callback) {
        this.mPresenterCallback = callback;
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    public void setGravity(int i) {
        this.mDropDownGravity = i;
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean tryShow() {
        boolean z = false;
        this.mPopup = new ListPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
        this.mPopup.setOnDismissListener(this);
        this.mPopup.setOnItemClickListener(this);
        this.mPopup.setAdapter(this.mAdapter);
        this.mPopup.setModal(true);
        View view = this.mAnchorView;
        if (view == null) {
            return false;
        }
        if (this.mTreeObserver == null) {
            z = true;
        }
        this.mTreeObserver = view.getViewTreeObserver();
        if (z) {
            this.mTreeObserver.addOnGlobalLayoutListener(this);
        }
        this.mPopup.setAnchorView(view);
        this.mPopup.setDropDownGravity(this.mDropDownGravity);
        if (!this.mHasContentWidth) {
            this.mContentWidth = measureContentWidth();
            this.mHasContentWidth = true;
        }
        this.mPopup.setContentWidth(this.mContentWidth);
        this.mPopup.setInputMethodMode(2);
        this.mPopup.show();
        this.mPopup.getListView().setOnKeyListener(this);
        return true;
    }

    public void updateMenuView(boolean z) {
        this.mHasContentWidth = false;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
