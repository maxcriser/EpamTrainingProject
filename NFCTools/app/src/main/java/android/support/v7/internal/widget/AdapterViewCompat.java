package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.CapturedViewProperty;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;

public abstract class AdapterViewCompat<T extends Adapter> extends ViewGroup {
    public static final int INVALID_POSITION = -1;
    public static final long INVALID_ROW_ID = Long.MIN_VALUE;
    static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;
    static final int ITEM_VIEW_TYPE_IGNORE = -1;
    static final int SYNC_FIRST_POSITION = 1;
    static final int SYNC_MAX_DURATION_MILLIS = 100;
    static final int SYNC_SELECTED_POSITION = 0;
    boolean mBlockLayoutRequests;
    boolean mDataChanged;
    private boolean mDesiredFocusableInTouchModeState;
    private boolean mDesiredFocusableState;
    private View mEmptyView;
    @ExportedProperty(category = "scrolling")
    int mFirstPosition;
    boolean mInLayout;
    @ExportedProperty(category = "list")
    int mItemCount;
    private int mLayoutHeight;
    boolean mNeedSync;
    @ExportedProperty(category = "list")
    int mNextSelectedPosition;
    long mNextSelectedRowId;
    int mOldItemCount;
    int mOldSelectedPosition;
    long mOldSelectedRowId;
    OnItemClickListener mOnItemClickListener;
    OnItemLongClickListener mOnItemLongClickListener;
    OnItemSelectedListener mOnItemSelectedListener;
    @ExportedProperty(category = "list")
    int mSelectedPosition;
    long mSelectedRowId;
    private C0378b mSelectionNotifier;
    int mSpecificTop;
    long mSyncHeight;
    int mSyncMode;
    int mSyncPosition;
    long mSyncRowId;

    public static class AdapterContextMenuInfo implements ContextMenuInfo {
        public long id;
        public int position;
        public View targetView;

        public AdapterContextMenuInfo(View view, int i, long j) {
            this.targetView = view;
            this.position = i;
            this.id = j;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(AdapterViewCompat<?> adapterViewCompat, View view, int i, long j);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(AdapterViewCompat<?> adapterViewCompat, View view, int i, long j);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(AdapterViewCompat<?> adapterViewCompat, View view, int i, long j);

        void onNothingSelected(AdapterViewCompat<?> adapterViewCompat);
    }

    /* renamed from: android.support.v7.internal.widget.AdapterViewCompat.a */
    class C0377a extends DataSetObserver {
        final /* synthetic */ AdapterViewCompat f804a;
        private Parcelable f805b;

        C0377a(AdapterViewCompat adapterViewCompat) {
            this.f804a = adapterViewCompat;
            this.f805b = null;
        }

        public void onChanged() {
            this.f804a.mDataChanged = true;
            this.f804a.mOldItemCount = this.f804a.mItemCount;
            this.f804a.mItemCount = this.f804a.getAdapter().getCount();
            if (!this.f804a.getAdapter().hasStableIds() || this.f805b == null || this.f804a.mOldItemCount != 0 || this.f804a.mItemCount <= 0) {
                this.f804a.rememberSyncState();
            } else {
                this.f804a.onRestoreInstanceState(this.f805b);
                this.f805b = null;
            }
            this.f804a.checkFocus();
            this.f804a.requestLayout();
        }

        public void onInvalidated() {
            this.f804a.mDataChanged = true;
            if (this.f804a.getAdapter().hasStableIds()) {
                this.f805b = this.f804a.onSaveInstanceState();
            }
            this.f804a.mOldItemCount = this.f804a.mItemCount;
            this.f804a.mItemCount = 0;
            this.f804a.mSelectedPosition = AdapterViewCompat.ITEM_VIEW_TYPE_IGNORE;
            this.f804a.mSelectedRowId = AdapterViewCompat.INVALID_ROW_ID;
            this.f804a.mNextSelectedPosition = AdapterViewCompat.ITEM_VIEW_TYPE_IGNORE;
            this.f804a.mNextSelectedRowId = AdapterViewCompat.INVALID_ROW_ID;
            this.f804a.mNeedSync = false;
            this.f804a.checkFocus();
            this.f804a.requestLayout();
        }
    }

    /* renamed from: android.support.v7.internal.widget.AdapterViewCompat.b */
    private class C0378b implements Runnable {
        final /* synthetic */ AdapterViewCompat f806a;

        private C0378b(AdapterViewCompat adapterViewCompat) {
            this.f806a = adapterViewCompat;
        }

        public void run() {
            if (!this.f806a.mDataChanged) {
                this.f806a.fireOnSelected();
            } else if (this.f806a.getAdapter() != null) {
                this.f806a.post(this);
            }
        }
    }

    AdapterViewCompat(Context context) {
        super(context);
        this.mFirstPosition = 0;
        this.mSyncRowId = INVALID_ROW_ID;
        this.mNeedSync = false;
        this.mInLayout = false;
        this.mNextSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mNextSelectedRowId = INVALID_ROW_ID;
        this.mSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mSelectedRowId = INVALID_ROW_ID;
        this.mOldSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mOldSelectedRowId = INVALID_ROW_ID;
        this.mBlockLayoutRequests = false;
    }

    AdapterViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFirstPosition = 0;
        this.mSyncRowId = INVALID_ROW_ID;
        this.mNeedSync = false;
        this.mInLayout = false;
        this.mNextSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mNextSelectedRowId = INVALID_ROW_ID;
        this.mSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mSelectedRowId = INVALID_ROW_ID;
        this.mOldSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mOldSelectedRowId = INVALID_ROW_ID;
        this.mBlockLayoutRequests = false;
    }

    AdapterViewCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFirstPosition = 0;
        this.mSyncRowId = INVALID_ROW_ID;
        this.mNeedSync = false;
        this.mInLayout = false;
        this.mNextSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mNextSelectedRowId = INVALID_ROW_ID;
        this.mSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mSelectedRowId = INVALID_ROW_ID;
        this.mOldSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
        this.mOldSelectedRowId = INVALID_ROW_ID;
        this.mBlockLayoutRequests = false;
    }

    private void fireOnSelected() {
        if (this.mOnItemSelectedListener != null) {
            int selectedItemPosition = getSelectedItemPosition();
            if (selectedItemPosition >= 0) {
                View selectedView = getSelectedView();
                this.mOnItemSelectedListener.onItemSelected(this, selectedView, selectedItemPosition, getAdapter().getItemId(selectedItemPosition));
                return;
            }
            this.mOnItemSelectedListener.onNothingSelected(this);
        }
    }

    private void updateEmptyStatus(boolean z) {
        if (isInFilterMode()) {
            z = false;
        }
        if (z) {
            if (this.mEmptyView != null) {
                this.mEmptyView.setVisibility(0);
                setVisibility(8);
            } else {
                setVisibility(0);
            }
            if (this.mDataChanged) {
                onLayout(false, getLeft(), getTop(), getRight(), getBottom());
                return;
            }
            return;
        }
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        setVisibility(0);
    }

    public void addView(View view) {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    public void addView(View view, int i) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }

    public void addView(View view, LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
    }

    protected boolean canAnimate() {
        return super.canAnimate() && this.mItemCount > 0;
    }

    void checkFocus() {
        boolean z = false;
        Adapter adapter = getAdapter();
        boolean z2 = (adapter == null || adapter.getCount() == 0) ? SYNC_FIRST_POSITION : false;
        boolean z3 = (!z2 || isInFilterMode()) ? SYNC_FIRST_POSITION : false;
        z2 = z3 && this.mDesiredFocusableInTouchModeState;
        super.setFocusableInTouchMode(z2);
        z2 = z3 && this.mDesiredFocusableState;
        super.setFocusable(z2);
        if (this.mEmptyView != null) {
            if (adapter == null || adapter.isEmpty()) {
                z = true;
            }
            updateEmptyStatus(z);
        }
    }

    void checkSelectionChanged() {
        if (this.mSelectedPosition != this.mOldSelectedPosition || this.mSelectedRowId != this.mOldSelectedRowId) {
            selectionChanged();
            this.mOldSelectedPosition = this.mSelectedPosition;
            this.mOldSelectedRowId = this.mSelectedRowId;
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        View selectedView = getSelectedView();
        return selectedView != null && selectedView.getVisibility() == 0 && selectedView.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    int findSyncPosition() {
        int i = this.mItemCount;
        if (i == 0) {
            return ITEM_VIEW_TYPE_IGNORE;
        }
        long j = this.mSyncRowId;
        int i2 = this.mSyncPosition;
        if (j == INVALID_ROW_ID) {
            return ITEM_VIEW_TYPE_IGNORE;
        }
        int min = Math.min(i + ITEM_VIEW_TYPE_IGNORE, Math.max(0, i2));
        long uptimeMillis = SystemClock.uptimeMillis() + 100;
        Object obj = null;
        Adapter adapter = getAdapter();
        if (adapter == null) {
            return ITEM_VIEW_TYPE_IGNORE;
        }
        int i3 = min;
        int i4 = min;
        while (SystemClock.uptimeMillis() <= uptimeMillis) {
            if (adapter.getItemId(i4) != j) {
                Object obj2 = min == i + ITEM_VIEW_TYPE_IGNORE ? SYNC_FIRST_POSITION : null;
                Object obj3 = i3 == 0 ? SYNC_FIRST_POSITION : null;
                if (obj2 != null && obj3 != null) {
                    break;
                } else if (obj3 != null || (r0 != null && obj2 == null)) {
                    min += SYNC_FIRST_POSITION;
                    obj = null;
                    i4 = min;
                } else if (obj2 != null || (r0 == null && obj3 == null)) {
                    i3 += ITEM_VIEW_TYPE_IGNORE;
                    obj = SYNC_FIRST_POSITION;
                    i4 = i3;
                }
            } else {
                return i4;
            }
        }
        return ITEM_VIEW_TYPE_IGNORE;
    }

    public abstract T getAdapter();

    @CapturedViewProperty
    public int getCount() {
        return this.mItemCount;
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    public int getFirstVisiblePosition() {
        return this.mFirstPosition;
    }

    public Object getItemAtPosition(int i) {
        Adapter adapter = getAdapter();
        return (adapter == null || i < 0) ? null : adapter.getItem(i);
    }

    public long getItemIdAtPosition(int i) {
        Adapter adapter = getAdapter();
        return (adapter == null || i < 0) ? INVALID_ROW_ID : adapter.getItemId(i);
    }

    public int getLastVisiblePosition() {
        return (this.mFirstPosition + getChildCount()) + ITEM_VIEW_TYPE_IGNORE;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    public final OnItemSelectedListener getOnItemSelectedListener() {
        return this.mOnItemSelectedListener;
    }

    public int getPositionForView(View view) {
        while (true) {
            try {
                View view2 = (View) view.getParent();
                if (view2.equals(this)) {
                    break;
                }
                view = view2;
            } catch (ClassCastException e) {
                return ITEM_VIEW_TYPE_IGNORE;
            }
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i += SYNC_FIRST_POSITION) {
            if (getChildAt(i).equals(view)) {
                return i + this.mFirstPosition;
            }
        }
        return ITEM_VIEW_TYPE_IGNORE;
    }

    public Object getSelectedItem() {
        Adapter adapter = getAdapter();
        int selectedItemPosition = getSelectedItemPosition();
        return (adapter == null || adapter.getCount() <= 0 || selectedItemPosition < 0) ? null : adapter.getItem(selectedItemPosition);
    }

    @CapturedViewProperty
    public long getSelectedItemId() {
        return this.mNextSelectedRowId;
    }

    @CapturedViewProperty
    public int getSelectedItemPosition() {
        return this.mNextSelectedPosition;
    }

    public abstract View getSelectedView();

    void handleDataChanged() {
        boolean z;
        int i = this.mItemCount;
        if (i > 0) {
            int findSyncPosition;
            boolean z2;
            if (this.mNeedSync) {
                this.mNeedSync = false;
                findSyncPosition = findSyncPosition();
                if (findSyncPosition >= 0 && lookForSelectablePosition(findSyncPosition, true) == findSyncPosition) {
                    setNextSelectedPositionInt(findSyncPosition);
                    z2 = true;
                    if (!z2) {
                        findSyncPosition = getSelectedItemPosition();
                        if (findSyncPosition >= i) {
                            findSyncPosition = i + ITEM_VIEW_TYPE_IGNORE;
                        }
                        if (findSyncPosition < 0) {
                            findSyncPosition = 0;
                        }
                        i = lookForSelectablePosition(findSyncPosition, true);
                        findSyncPosition = i >= 0 ? lookForSelectablePosition(findSyncPosition, false) : i;
                        if (findSyncPosition >= 0) {
                            setNextSelectedPositionInt(findSyncPosition);
                            checkSelectionChanged();
                            z = true;
                        }
                    }
                    z = z2;
                }
            }
            z2 = false;
            if (z2) {
                findSyncPosition = getSelectedItemPosition();
                if (findSyncPosition >= i) {
                    findSyncPosition = i + ITEM_VIEW_TYPE_IGNORE;
                }
                if (findSyncPosition < 0) {
                    findSyncPosition = 0;
                }
                i = lookForSelectablePosition(findSyncPosition, true);
                if (i >= 0) {
                }
                if (findSyncPosition >= 0) {
                    setNextSelectedPositionInt(findSyncPosition);
                    checkSelectionChanged();
                    z = true;
                }
            }
            z = z2;
        } else {
            z = false;
        }
        if (!z) {
            this.mSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
            this.mSelectedRowId = INVALID_ROW_ID;
            this.mNextSelectedPosition = ITEM_VIEW_TYPE_IGNORE;
            this.mNextSelectedRowId = INVALID_ROW_ID;
            this.mNeedSync = false;
            checkSelectionChanged();
        }
    }

    boolean isInFilterMode() {
        return false;
    }

    int lookForSelectablePosition(int i, boolean z) {
        return i;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mSelectionNotifier);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mLayoutHeight = getHeight();
    }

    public boolean performItemClick(View view, int i, long j) {
        if (this.mOnItemClickListener == null) {
            return false;
        }
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(SYNC_FIRST_POSITION);
        }
        this.mOnItemClickListener.onItemClick(this, view, i, j);
        return true;
    }

    void rememberSyncState() {
        if (getChildCount() > 0) {
            this.mNeedSync = true;
            this.mSyncHeight = (long) this.mLayoutHeight;
            View childAt;
            if (this.mSelectedPosition >= 0) {
                childAt = getChildAt(this.mSelectedPosition - this.mFirstPosition);
                this.mSyncRowId = this.mNextSelectedRowId;
                this.mSyncPosition = this.mNextSelectedPosition;
                if (childAt != null) {
                    this.mSpecificTop = childAt.getTop();
                }
                this.mSyncMode = 0;
                return;
            }
            childAt = getChildAt(0);
            Adapter adapter = getAdapter();
            if (this.mFirstPosition < 0 || this.mFirstPosition >= adapter.getCount()) {
                this.mSyncRowId = -1;
            } else {
                this.mSyncRowId = adapter.getItemId(this.mFirstPosition);
            }
            this.mSyncPosition = this.mFirstPosition;
            if (childAt != null) {
                this.mSpecificTop = childAt.getTop();
            }
            this.mSyncMode = SYNC_FIRST_POSITION;
        }
    }

    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    public void removeView(View view) {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    public void removeViewAt(int i) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    void selectionChanged() {
        if (this.mOnItemSelectedListener != null) {
            if (this.mInLayout || this.mBlockLayoutRequests) {
                if (this.mSelectionNotifier == null) {
                    this.mSelectionNotifier = new C0378b();
                }
                post(this.mSelectionNotifier);
            } else {
                fireOnSelected();
            }
        }
        if (this.mSelectedPosition != ITEM_VIEW_TYPE_IGNORE && isShown() && !isInTouchMode()) {
            sendAccessibilityEvent(4);
        }
    }

    public abstract void setAdapter(T t);

    public void setEmptyView(View view) {
        this.mEmptyView = view;
        Adapter adapter = getAdapter();
        boolean z = adapter == null || adapter.isEmpty();
        updateEmptyStatus(z);
    }

    public void setFocusable(boolean z) {
        boolean z2 = true;
        Adapter adapter = getAdapter();
        boolean z3 = adapter == null || adapter.getCount() == 0;
        this.mDesiredFocusableState = z;
        if (!z) {
            this.mDesiredFocusableInTouchModeState = false;
        }
        if (!z || (z3 && !isInFilterMode())) {
            z2 = false;
        }
        super.setFocusable(z2);
    }

    public void setFocusableInTouchMode(boolean z) {
        boolean z2 = true;
        Adapter adapter = getAdapter();
        boolean z3 = adapter == null || adapter.getCount() == 0;
        this.mDesiredFocusableInTouchModeState = z;
        if (z) {
            this.mDesiredFocusableState = true;
        }
        if (!z || (z3 && !isInFilterMode())) {
            z2 = false;
        }
        super.setFocusableInTouchMode(z2);
    }

    void setNextSelectedPositionInt(int i) {
        this.mNextSelectedPosition = i;
        this.mNextSelectedRowId = getItemIdAtPosition(i);
        if (this.mNeedSync && this.mSyncMode == 0 && i >= 0) {
            this.mSyncPosition = i;
            this.mSyncRowId = this.mNextSelectedRowId;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    void setSelectedPositionInt(int i) {
        this.mSelectedPosition = i;
        this.mSelectedRowId = getItemIdAtPosition(i);
    }

    public abstract void setSelection(int i);
}
