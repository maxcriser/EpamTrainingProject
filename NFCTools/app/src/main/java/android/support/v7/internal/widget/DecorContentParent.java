package android.support.v7.internal.widget;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.internal.p011a.C0317b;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.util.SparseArray;
import android.view.Menu;

public interface DecorContentParent {
    boolean canShowOverflowMenu();

    void dismissPopups();

    CharSequence getTitle();

    boolean hasIcon();

    boolean hasLogo();

    boolean hideOverflowMenu();

    void initFeature(int i);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void restoreToolbarHierarchyState(SparseArray<Parcelable> sparseArray);

    void saveToolbarHierarchyState(SparseArray<Parcelable> sparseArray);

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setLogo(int i);

    void setMenu(Menu menu, Callback callback);

    void setMenuPrepared();

    void setUiOptions(int i);

    void setWindowCallback(C0317b c0317b);

    void setWindowTitle(CharSequence charSequence);

    boolean showOverflowMenu();
}
