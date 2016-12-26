package android.support.v7.internal.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.p003c.p004a.C0079b;
import android.support.v4.view.C0187d;
import android.support.v4.view.C0210l.C0209e;
import android.support.v7.p010b.C0321b;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

@TargetApi(14)
public class MenuItemWrapperICS extends C0354a<C0079b> implements MenuItem {
    static final String LOG_TAG = "MenuItemWrapper";
    private Method mSetExclusiveCheckableMethod;

    /* renamed from: android.support.v7.internal.view.menu.MenuItemWrapperICS.a */
    class C0349a extends C0187d {
        final ActionProvider f760a;
        final /* synthetic */ MenuItemWrapperICS f761b;

        public C0349a(MenuItemWrapperICS menuItemWrapperICS, Context context, ActionProvider actionProvider) {
            this.f761b = menuItemWrapperICS;
            super(context);
            this.f760a = actionProvider;
        }

        public boolean hasSubMenu() {
            return this.f760a.hasSubMenu();
        }

        public View onCreateActionView() {
            return this.f760a.onCreateActionView();
        }

        public boolean onPerformDefaultAction() {
            return this.f760a.onPerformDefaultAction();
        }

        public void onPrepareSubMenu(SubMenu subMenu) {
            this.f760a.onPrepareSubMenu(this.f761b.getSubMenuWrapper(subMenu));
        }
    }

    /* renamed from: android.support.v7.internal.view.menu.MenuItemWrapperICS.b */
    static class C0350b extends FrameLayout implements C0321b {
        final CollapsibleActionView f762a;

        C0350b(View view) {
            super(view.getContext());
            this.f762a = (CollapsibleActionView) view;
            addView(view);
        }

        View m1711a() {
            return (View) this.f762a;
        }

        public void onActionViewCollapsed() {
            this.f762a.onActionViewCollapsed();
        }

        public void onActionViewExpanded() {
            this.f762a.onActionViewExpanded();
        }
    }

    /* renamed from: android.support.v7.internal.view.menu.MenuItemWrapperICS.c */
    private class C0352c extends C0351b<OnActionExpandListener> implements C0209e {
        final /* synthetic */ MenuItemWrapperICS f763a;

        C0352c(MenuItemWrapperICS menuItemWrapperICS, OnActionExpandListener onActionExpandListener) {
            this.f763a = menuItemWrapperICS;
            super(onActionExpandListener);
        }

        public boolean m1712a(MenuItem menuItem) {
            return ((OnActionExpandListener) this.mWrappedObject).onMenuItemActionExpand(this.f763a.getMenuItemWrapper(menuItem));
        }

        public boolean m1713b(MenuItem menuItem) {
            return ((OnActionExpandListener) this.mWrappedObject).onMenuItemActionCollapse(this.f763a.getMenuItemWrapper(menuItem));
        }
    }

    /* renamed from: android.support.v7.internal.view.menu.MenuItemWrapperICS.d */
    private class C0353d extends C0351b<OnMenuItemClickListener> implements OnMenuItemClickListener {
        final /* synthetic */ MenuItemWrapperICS f764a;

        C0353d(MenuItemWrapperICS menuItemWrapperICS, OnMenuItemClickListener onMenuItemClickListener) {
            this.f764a = menuItemWrapperICS;
            super(onMenuItemClickListener);
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            return ((OnMenuItemClickListener) this.mWrappedObject).onMenuItemClick(this.f764a.getMenuItemWrapper(menuItem));
        }
    }

    MenuItemWrapperICS(Context context, C0079b c0079b) {
        super(context, c0079b);
    }

    public boolean collapseActionView() {
        return ((C0079b) this.mWrappedObject).collapseActionView();
    }

    C0349a createActionProviderWrapper(ActionProvider actionProvider) {
        return new C0349a(this, this.mContext, actionProvider);
    }

    public boolean expandActionView() {
        return ((C0079b) this.mWrappedObject).expandActionView();
    }

    public ActionProvider getActionProvider() {
        C0187d supportActionProvider = ((C0079b) this.mWrappedObject).getSupportActionProvider();
        return supportActionProvider instanceof C0349a ? ((C0349a) supportActionProvider).f760a : null;
    }

    public View getActionView() {
        View actionView = ((C0079b) this.mWrappedObject).getActionView();
        return actionView instanceof C0350b ? ((C0350b) actionView).m1711a() : actionView;
    }

    public char getAlphabeticShortcut() {
        return ((C0079b) this.mWrappedObject).getAlphabeticShortcut();
    }

    public int getGroupId() {
        return ((C0079b) this.mWrappedObject).getGroupId();
    }

    public Drawable getIcon() {
        return ((C0079b) this.mWrappedObject).getIcon();
    }

    public Intent getIntent() {
        return ((C0079b) this.mWrappedObject).getIntent();
    }

    public int getItemId() {
        return ((C0079b) this.mWrappedObject).getItemId();
    }

    public ContextMenuInfo getMenuInfo() {
        return ((C0079b) this.mWrappedObject).getMenuInfo();
    }

    public char getNumericShortcut() {
        return ((C0079b) this.mWrappedObject).getNumericShortcut();
    }

    public int getOrder() {
        return ((C0079b) this.mWrappedObject).getOrder();
    }

    public SubMenu getSubMenu() {
        return getSubMenuWrapper(((C0079b) this.mWrappedObject).getSubMenu());
    }

    public CharSequence getTitle() {
        return ((C0079b) this.mWrappedObject).getTitle();
    }

    public CharSequence getTitleCondensed() {
        return ((C0079b) this.mWrappedObject).getTitleCondensed();
    }

    public boolean hasSubMenu() {
        return ((C0079b) this.mWrappedObject).hasSubMenu();
    }

    public boolean isActionViewExpanded() {
        return ((C0079b) this.mWrappedObject).isActionViewExpanded();
    }

    public boolean isCheckable() {
        return ((C0079b) this.mWrappedObject).isCheckable();
    }

    public boolean isChecked() {
        return ((C0079b) this.mWrappedObject).isChecked();
    }

    public boolean isEnabled() {
        return ((C0079b) this.mWrappedObject).isEnabled();
    }

    public boolean isVisible() {
        return ((C0079b) this.mWrappedObject).isVisible();
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        ((C0079b) this.mWrappedObject).setSupportActionProvider(actionProvider != null ? createActionProviderWrapper(actionProvider) : null);
        return this;
    }

    public MenuItem setActionView(int i) {
        ((C0079b) this.mWrappedObject).setActionView(i);
        View actionView = ((C0079b) this.mWrappedObject).getActionView();
        if (actionView instanceof CollapsibleActionView) {
            ((C0079b) this.mWrappedObject).setActionView(new C0350b(actionView));
        }
        return this;
    }

    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new C0350b(view);
        }
        ((C0079b) this.mWrappedObject).setActionView(view);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c) {
        ((C0079b) this.mWrappedObject).setAlphabeticShortcut(c);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        ((C0079b) this.mWrappedObject).setCheckable(z);
        return this;
    }

    public MenuItem setChecked(boolean z) {
        ((C0079b) this.mWrappedObject).setChecked(z);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        ((C0079b) this.mWrappedObject).setEnabled(z);
        return this;
    }

    public void setExclusiveCheckable(boolean z) {
        try {
            if (this.mSetExclusiveCheckableMethod == null) {
                this.mSetExclusiveCheckableMethod = ((C0079b) this.mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
            }
            this.mSetExclusiveCheckableMethod.invoke(this.mWrappedObject, new Object[]{Boolean.valueOf(z)});
        } catch (Throwable e) {
            Log.w(LOG_TAG, "Error while calling setExclusiveCheckable", e);
        }
    }

    public MenuItem setIcon(int i) {
        ((C0079b) this.mWrappedObject).setIcon(i);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        ((C0079b) this.mWrappedObject).setIcon(drawable);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        ((C0079b) this.mWrappedObject).setIntent(intent);
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        ((C0079b) this.mWrappedObject).setNumericShortcut(c);
        return this;
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        ((C0079b) this.mWrappedObject).setSupportOnActionExpandListener(onActionExpandListener != null ? new C0352c(this, onActionExpandListener) : null);
        return this;
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        ((C0079b) this.mWrappedObject).setOnMenuItemClickListener(onMenuItemClickListener != null ? new C0353d(this, onMenuItemClickListener) : null);
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        ((C0079b) this.mWrappedObject).setShortcut(c, c2);
        return this;
    }

    public void setShowAsAction(int i) {
        ((C0079b) this.mWrappedObject).setShowAsAction(i);
    }

    public MenuItem setShowAsActionFlags(int i) {
        ((C0079b) this.mWrappedObject).setShowAsActionFlags(i);
        return this;
    }

    public MenuItem setTitle(int i) {
        ((C0079b) this.mWrappedObject).setTitle(i);
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        ((C0079b) this.mWrappedObject).setTitle(charSequence);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((C0079b) this.mWrappedObject).setTitleCondensed(charSequence);
        return this;
    }

    public MenuItem setVisible(boolean z) {
        return ((C0079b) this.mWrappedObject).setVisible(z);
    }
}
