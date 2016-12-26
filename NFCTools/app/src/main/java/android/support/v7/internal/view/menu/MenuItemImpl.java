package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.p003c.p004a.C0079b;
import android.support.v4.view.C0187d;
import android.support.v4.view.C0187d.C0186b;
import android.support.v4.view.C0210l.C0209e;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.support.v7.internal.widget.TintManager;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug.CapturedViewProperty;
import android.widget.LinearLayout;
import com.wakdev.nfctools.C0628m.C0627j;

public final class MenuItemImpl implements C0079b {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int IS_ACTION = 32;
    static final int NO_ICON = 0;
    private static final int SHOW_AS_ACTION_MASK = 3;
    private static final String TAG = "MenuItemImpl";
    private static String sDeleteShortcutLabel;
    private static String sEnterShortcutLabel;
    private static String sPrependShortcutLabel;
    private static String sSpaceShortcutLabel;
    private C0187d mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private OnMenuItemClickListener mClickListener;
    private int mFlags;
    private final int mGroup;
    private Drawable mIconDrawable;
    private int mIconResId;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded;
    private Runnable mItemCallback;
    private MenuBuilder mMenu;
    private ContextMenuInfo mMenuInfo;
    private C0209e mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private int mShowAsAction;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;

    /* renamed from: android.support.v7.internal.view.menu.MenuItemImpl.1 */
    class C03481 implements C0186b {
        final /* synthetic */ MenuItemImpl f759a;

        C03481(MenuItemImpl menuItemImpl) {
            this.f759a = menuItemImpl;
        }

        public void m1710a(boolean z) {
            this.f759a.mMenu.onItemVisibleChanged(this.f759a);
        }
    }

    MenuItemImpl(MenuBuilder menuBuilder, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.mIconResId = NO_ICON;
        this.mFlags = ENABLED;
        this.mShowAsAction = NO_ICON;
        this.mIsActionViewExpanded = false;
        this.mMenu = menuBuilder;
        this.mId = i2;
        this.mGroup = i;
        this.mCategoryOrder = i3;
        this.mOrdering = i4;
        this.mTitle = charSequence;
        this.mShowAsAction = i5;
    }

    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }

    public boolean collapseActionView() {
        return (this.mShowAsAction & HIDDEN) == 0 ? false : this.mActionView == null ? true : (this.mOnActionExpandListener == null || this.mOnActionExpandListener.m925b(this)) ? this.mMenu.collapseItemActionView(this) : false;
    }

    public boolean expandActionView() {
        return !hasCollapsibleActionView() ? false : (this.mOnActionExpandListener == null || this.mOnActionExpandListener.m924a(this)) ? this.mMenu.expandItemActionView(this) : false;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public View getActionView() {
        if (this.mActionView != null) {
            return this.mActionView;
        }
        if (this.mActionProvider == null) {
            return null;
        }
        this.mActionView = this.mActionProvider.onCreateActionView(this);
        return this.mActionView;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    Runnable getCallback() {
        return this.mItemCallback;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        if (this.mIconDrawable != null) {
            return this.mIconDrawable;
        }
        if (this.mIconResId == 0) {
            return null;
        }
        Drawable drawable = TintManager.getDrawable(this.mMenu.getContext(), this.mIconResId);
        this.mIconResId = NO_ICON;
        this.mIconDrawable = drawable;
        return drawable;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    @CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }

    public ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mCategoryOrder;
    }

    public int getOrdering() {
        return this.mOrdering;
    }

    char getShortcut() {
        return this.mMenu.isQwertyMode() ? this.mShortcutAlphabeticChar : this.mShortcutNumericChar;
    }

    String getShortcutLabel() {
        char shortcut = getShortcut();
        if (shortcut == '\u0000') {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(sPrependShortcutLabel);
        switch (shortcut) {
            case HIDDEN /*8*/:
                stringBuilder.append(sDeleteShortcutLabel);
                break;
            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                stringBuilder.append(sEnterShortcutLabel);
                break;
            case IS_ACTION /*32*/:
                stringBuilder.append(sSpaceShortcutLabel);
                break;
            default:
                stringBuilder.append(shortcut);
                break;
        }
        return stringBuilder.toString();
    }

    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    public C0187d getSupportActionProvider() {
        return this.mActionProvider;
    }

    @CapturedViewProperty
    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.mTitleCondensed != null ? this.mTitleCondensed : this.mTitle;
        return (VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) ? charSequence : charSequence.toString();
    }

    CharSequence getTitleForItemView(ItemView itemView) {
        return (itemView == null || !itemView.prefersCondensedTitle()) ? getTitle() : getTitleCondensed();
    }

    public boolean hasCollapsibleActionView() {
        if ((this.mShowAsAction & HIDDEN) == 0) {
            return false;
        }
        if (this.mActionView == null && this.mActionProvider != null) {
            this.mActionView = this.mActionProvider.onCreateActionView(this);
        }
        return this.mActionView != null;
    }

    public boolean hasSubMenu() {
        return this.mSubMenu != null;
    }

    public boolean invoke() {
        if ((this.mClickListener != null && this.mClickListener.onMenuItemClick(this)) || this.mMenu.dispatchMenuItemSelected(this.mMenu.getRootMenu(), this)) {
            return true;
        }
        if (this.mItemCallback != null) {
            this.mItemCallback.run();
            return true;
        }
        if (this.mIntent != null) {
            try {
                this.mMenu.getContext().startActivity(this.mIntent);
                return true;
            } catch (Throwable e) {
                Log.e(TAG, "Can't find activity to handle intent; ignoring", e);
            }
        }
        return this.mActionProvider != null && this.mActionProvider.onPerformDefaultAction();
    }

    public boolean isActionButton() {
        return (this.mFlags & IS_ACTION) == IS_ACTION;
    }

    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    public boolean isCheckable() {
        return (this.mFlags & CHECKABLE) == CHECKABLE;
    }

    public boolean isChecked() {
        return (this.mFlags & CHECKED) == CHECKED;
    }

    public boolean isEnabled() {
        return (this.mFlags & ENABLED) != 0;
    }

    public boolean isExclusiveCheckable() {
        return (this.mFlags & EXCLUSIVE) != 0;
    }

    public boolean isVisible() {
        return (this.mActionProvider == null || !this.mActionProvider.overridesItemVisibility()) ? (this.mFlags & HIDDEN) == 0 : (this.mFlags & HIDDEN) == 0 && this.mActionProvider.isVisible();
    }

    public boolean requestsActionButton() {
        return (this.mShowAsAction & CHECKABLE) == CHECKABLE;
    }

    public boolean requiresActionButton() {
        return (this.mShowAsAction & CHECKED) == CHECKED;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public C0079b setActionView(int i) {
        Context context = this.mMenu.getContext();
        setActionView(LayoutInflater.from(context).inflate(i, new LinearLayout(context), false));
        return this;
    }

    public C0079b setActionView(View view) {
        this.mActionView = view;
        this.mActionProvider = null;
        if (view != null && view.getId() == -1 && this.mId > 0) {
            view.setId(this.mId);
        }
        this.mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public void setActionViewExpanded(boolean z) {
        this.mIsActionViewExpanded = z;
        this.mMenu.onItemsChanged(false);
    }

    public MenuItem setAlphabeticShortcut(char c) {
        if (this.mShortcutAlphabeticChar != c) {
            this.mShortcutAlphabeticChar = Character.toLowerCase(c);
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    public MenuItem setCallback(Runnable runnable) {
        this.mItemCallback = runnable;
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        int i = this.mFlags;
        this.mFlags = (z ? CHECKABLE : NO_ICON) | (this.mFlags & -2);
        if (i != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    public MenuItem setChecked(boolean z) {
        if ((this.mFlags & EXCLUSIVE) != 0) {
            this.mMenu.setExclusiveItemChecked(this);
        } else {
            setCheckedInt(z);
        }
        return this;
    }

    void setCheckedInt(boolean z) {
        int i = this.mFlags;
        this.mFlags = (z ? CHECKED : NO_ICON) | (this.mFlags & -3);
        if (i != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
    }

    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.mFlags |= ENABLED;
        } else {
            this.mFlags &= -17;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setExclusiveCheckable(boolean z) {
        this.mFlags = (z ? EXCLUSIVE : NO_ICON) | (this.mFlags & -5);
    }

    public MenuItem setIcon(int i) {
        this.mIconDrawable = null;
        this.mIconResId = i;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.mIconResId = NO_ICON;
        this.mIconDrawable = drawable;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean z) {
        if (z) {
            this.mFlags |= IS_ACTION;
        } else {
            this.mFlags &= -33;
        }
    }

    void setMenuInfo(ContextMenuInfo contextMenuInfo) {
        this.mMenuInfo = contextMenuInfo;
    }

    public MenuItem setNumericShortcut(char c) {
        if (this.mShortcutNumericChar != c) {
            this.mShortcutNumericChar = c;
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.mShortcutNumericChar = c;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int i) {
        switch (i & SHOW_AS_ACTION_MASK) {
            case NO_ICON /*0*/:
            case CHECKABLE /*1*/:
            case CHECKED /*2*/:
                this.mShowAsAction = i;
                this.mMenu.onItemActionRequestChanged(this);
            default:
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
    }

    public C0079b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    void setSubMenu(SubMenuBuilder subMenuBuilder) {
        this.mSubMenu = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(getTitle());
    }

    public C0079b setSupportActionProvider(C0187d c0187d) {
        if (this.mActionProvider != null) {
            this.mActionProvider.setVisibilityListener(null);
        }
        this.mActionView = null;
        this.mActionProvider = c0187d;
        this.mMenu.onItemsChanged(true);
        if (this.mActionProvider != null) {
            this.mActionProvider.setVisibilityListener(new C03481(this));
        }
        return this;
    }

    public C0079b setSupportOnActionExpandListener(C0209e c0209e) {
        this.mOnActionExpandListener = c0209e;
        return this;
    }

    public MenuItem setTitle(int i) {
        return setTitle(this.mMenu.getContext().getString(i));
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        if (this.mSubMenu != null) {
            this.mSubMenu.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        if (charSequence == null) {
            CharSequence charSequence2 = this.mTitle;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setVisible(boolean z) {
        if (setVisibleInt(z)) {
            this.mMenu.onItemVisibleChanged(this);
        }
        return this;
    }

    boolean setVisibleInt(boolean z) {
        int i = this.mFlags;
        this.mFlags = (z ? NO_ICON : HIDDEN) | (this.mFlags & -9);
        return i != this.mFlags;
    }

    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }

    boolean shouldShowShortcut() {
        return this.mMenu.isShortcutsVisible() && getShortcut() != '\u0000';
    }

    public boolean showsTextAsAction() {
        return (this.mShowAsAction & EXCLUSIVE) == EXCLUSIVE;
    }

    public String toString() {
        return this.mTitle.toString();
    }
}
