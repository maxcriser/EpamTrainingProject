package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.C0234u;
import android.support.v7.internal.widget.AdapterViewCompat.C0377a;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.SpinnerAdapter;

abstract class AbsSpinnerCompat extends AdapterViewCompat<SpinnerAdapter> {
    SpinnerAdapter f775a;
    int f776b;
    int f777c;
    int f778d;
    int f779e;
    int f780f;
    int f781g;
    final Rect f782h;
    final C0362a f783i;
    private DataSetObserver f784j;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        long f771a;
        int f772b;

        /* renamed from: android.support.v7.internal.widget.AbsSpinnerCompat.SavedState.1 */
        static class C03611 implements Creator<SavedState> {
            C03611() {
            }

            public SavedState m1718a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m1719a(int i) {
                return new SavedState[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m1718a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m1719a(i);
            }
        }

        static {
            CREATOR = new C03611();
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f771a = parcel.readLong();
            this.f772b = parcel.readInt();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.f771a + " position=" + this.f772b + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.f771a);
            parcel.writeInt(this.f772b);
        }
    }

    /* renamed from: android.support.v7.internal.widget.AbsSpinnerCompat.a */
    class C0362a {
        final /* synthetic */ AbsSpinnerCompat f773a;
        private final SparseArray<View> f774b;

        C0362a(AbsSpinnerCompat absSpinnerCompat) {
            this.f773a = absSpinnerCompat;
            this.f774b = new SparseArray();
        }

        View m1720a(int i) {
            View view = (View) this.f774b.get(i);
            if (view != null) {
                this.f774b.delete(i);
            }
            return view;
        }

        void m1721a() {
            SparseArray sparseArray = this.f774b;
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                View view = (View) sparseArray.valueAt(i);
                if (view != null) {
                    this.f773a.removeDetachedView(view, true);
                }
            }
            sparseArray.clear();
        }

        public void m1722a(int i, View view) {
            this.f774b.put(i, view);
        }
    }

    AbsSpinnerCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f778d = 0;
        this.f779e = 0;
        this.f780f = 0;
        this.f781g = 0;
        this.f782h = new Rect();
        this.f783i = new C0362a(this);
        m1724d();
    }

    private void m1724d() {
        setFocusable(true);
        setWillNotDraw(false);
    }

    int m1725a(View view) {
        return view.getMeasuredHeight();
    }

    void m1726a() {
        this.mDataChanged = false;
        this.mNeedSync = false;
        removeAllViewsInLayout();
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        setSelectedPositionInt(-1);
        setNextSelectedPositionInt(-1);
        invalidate();
    }

    abstract void m1727a(int i, boolean z);

    public void m1728a(SpinnerAdapter spinnerAdapter) {
        int i = -1;
        if (this.f775a != null) {
            this.f775a.unregisterDataSetObserver(this.f784j);
            m1726a();
        }
        this.f775a = spinnerAdapter;
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        if (this.f775a != null) {
            this.mOldItemCount = this.mItemCount;
            this.mItemCount = this.f775a.getCount();
            checkFocus();
            this.f784j = new C0377a(this);
            this.f775a.registerDataSetObserver(this.f784j);
            if (this.mItemCount > 0) {
                i = 0;
            }
            setSelectedPositionInt(i);
            setNextSelectedPositionInt(i);
            if (this.mItemCount == 0) {
                checkSelectionChanged();
            }
        } else {
            checkFocus();
            m1726a();
            checkSelectionChanged();
        }
        requestLayout();
    }

    int m1729b(View view) {
        return view.getMeasuredWidth();
    }

    void m1730b() {
        int childCount = getChildCount();
        C0362a c0362a = this.f783i;
        int i = this.mFirstPosition;
        for (int i2 = 0; i2 < childCount; i2++) {
            c0362a.m1722a(i + i2, getChildAt(i2));
        }
    }

    public SpinnerAdapter m1731c() {
        return this.f775a;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    public /* synthetic */ Adapter getAdapter() {
        return m1731c();
    }

    public int getCount() {
        return this.mItemCount;
    }

    public View getSelectedView() {
        return (this.mItemCount <= 0 || this.mSelectedPosition < 0) ? null : getChildAt(this.mSelectedPosition - this.mFirstPosition);
    }

    protected void onMeasure(int i, int i2) {
        boolean z;
        int mode = MeasureSpec.getMode(i);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        Rect rect = this.f782h;
        if (paddingLeft <= this.f778d) {
            paddingLeft = this.f778d;
        }
        rect.left = paddingLeft;
        this.f782h.top = paddingTop > this.f779e ? paddingTop : this.f779e;
        this.f782h.right = paddingRight > this.f780f ? paddingRight : this.f780f;
        this.f782h.bottom = paddingBottom > this.f781g ? paddingBottom : this.f781g;
        if (this.mDataChanged) {
            handleDataChanged();
        }
        paddingTop = getSelectedItemPosition();
        if (paddingTop >= 0 && this.f775a != null && paddingTop < this.f775a.getCount()) {
            View a = this.f783i.m1720a(paddingTop);
            if (a == null) {
                a = this.f775a.getView(paddingTop, null, this);
            }
            if (a != null) {
                this.f783i.m1722a(paddingTop, a);
                if (a.getLayoutParams() == null) {
                    this.mBlockLayoutRequests = true;
                    a.setLayoutParams(generateDefaultLayoutParams());
                    this.mBlockLayoutRequests = false;
                }
                measureChild(a, i, i2);
                paddingTop = (m1725a(a) + this.f782h.top) + this.f782h.bottom;
                paddingLeft = (m1729b(a) + this.f782h.left) + this.f782h.right;
                z = false;
                if (z) {
                    paddingTop = this.f782h.top + this.f782h.bottom;
                    if (mode == 0) {
                        paddingLeft = this.f782h.left + this.f782h.right;
                    }
                }
                setMeasuredDimension(C0234u.m1072a(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, 0), C0234u.m1072a(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, 0));
                this.f776b = i2;
                this.f777c = i;
            }
        }
        z = true;
        paddingLeft = 0;
        paddingTop = 0;
        if (z) {
            paddingTop = this.f782h.top + this.f782h.bottom;
            if (mode == 0) {
                paddingLeft = this.f782h.left + this.f782h.right;
            }
        }
        setMeasuredDimension(C0234u.m1072a(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, 0), C0234u.m1072a(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, 0));
        this.f776b = i2;
        this.f777c = i;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f771a >= 0) {
            this.mDataChanged = true;
            this.mNeedSync = true;
            this.mSyncRowId = savedState.f771a;
            this.mSyncPosition = savedState.f772b;
            this.mSyncMode = 0;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        Object savedState = new SavedState(super.onSaveInstanceState());
        savedState.f771a = getSelectedItemId();
        if (savedState.f771a >= 0) {
            savedState.f772b = getSelectedItemPosition();
        } else {
            savedState.f772b = -1;
        }
        return savedState;
    }

    public void requestLayout() {
        if (!this.mBlockLayoutRequests) {
            super.requestLayout();
        }
    }

    public /* synthetic */ void setAdapter(Adapter adapter) {
        m1728a((SpinnerAdapter) adapter);
    }

    public void setSelection(int i) {
        setNextSelectedPositionInt(i);
        requestLayout();
        invalidate();
    }
}
