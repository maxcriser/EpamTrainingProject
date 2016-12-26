package android.support.v7.internal.widget;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.C0191e;
import android.support.v4.view.C0234u;
import android.support.v7.p009a.C0301a.C0300k;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import com.wakdev.nfctools.C0628m.C0627j;

class SpinnerCompat extends AbsSpinnerCompat implements OnClickListener {
    int f834j;
    private ForwardingListener f835k;
    private C0388d f836l;
    private C0390b f837m;
    private int f838n;
    private boolean f839o;
    private Rect f840p;
    private final TintManager f841q;

    /* renamed from: android.support.v7.internal.widget.SpinnerCompat.1 */
    class C03851 extends ForwardingListener {
        final /* synthetic */ C0392c f819a;
        final /* synthetic */ SpinnerCompat f820b;

        C03851(SpinnerCompat spinnerCompat, View view, C0392c c0392c) {
            this.f820b = spinnerCompat;
            this.f819a = c0392c;
            super(view);
        }

        public ListPopupWindow getPopup() {
            return this.f819a;
        }

        public boolean onForwardingStarted() {
            if (!this.f820b.f836l.isShowing()) {
                this.f820b.f836l.show();
            }
            return true;
        }
    }

    /* renamed from: android.support.v7.internal.widget.SpinnerCompat.2 */
    class C03862 implements OnGlobalLayoutListener {
        final /* synthetic */ SpinnerCompat f821a;

        C03862(SpinnerCompat spinnerCompat) {
            this.f821a = spinnerCompat;
        }

        public void onGlobalLayout() {
            if (!this.f821a.f836l.isShowing()) {
                this.f821a.f836l.show();
            }
            ViewTreeObserver viewTreeObserver = this.f821a.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
        }
    }

    static class SavedState extends SavedState {
        public static final Creator<SavedState> CREATOR;
        boolean f822c;

        /* renamed from: android.support.v7.internal.widget.SpinnerCompat.SavedState.1 */
        static class C03871 implements Creator<SavedState> {
            C03871() {
            }

            public SavedState m1748a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m1749a(int i) {
                return new SavedState[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m1748a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m1749a(i);
            }
        }

        static {
            CREATOR = new C03871();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f822c = parcel.readByte() != null;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte((byte) (this.f822c ? 1 : 0));
        }
    }

    /* renamed from: android.support.v7.internal.widget.SpinnerCompat.d */
    private interface C0388d {
        void m1750a(CharSequence charSequence);

        void dismiss();

        boolean isShowing();

        void setAdapter(ListAdapter listAdapter);

        void show();
    }

    /* renamed from: android.support.v7.internal.widget.SpinnerCompat.a */
    private class C0389a implements OnClickListener, C0388d {
        final /* synthetic */ SpinnerCompat f823a;
        private AlertDialog f824b;
        private ListAdapter f825c;
        private CharSequence f826d;

        private C0389a(SpinnerCompat spinnerCompat) {
            this.f823a = spinnerCompat;
        }

        public void m1751a(CharSequence charSequence) {
            this.f826d = charSequence;
        }

        public void dismiss() {
            if (this.f824b != null) {
                this.f824b.dismiss();
                this.f824b = null;
            }
        }

        public boolean isShowing() {
            return this.f824b != null ? this.f824b.isShowing() : false;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f823a.setSelection(i);
            if (this.f823a.mOnItemClickListener != null) {
                this.f823a.performItemClick(null, i, this.f825c.getItemId(i));
            }
            dismiss();
        }

        public void setAdapter(ListAdapter listAdapter) {
            this.f825c = listAdapter;
        }

        public void show() {
            if (this.f825c != null) {
                Builder builder = new Builder(this.f823a.getContext());
                if (this.f826d != null) {
                    builder.setTitle(this.f826d);
                }
                this.f824b = builder.setSingleChoiceItems(this.f825c, this.f823a.getSelectedItemPosition(), this).create();
                this.f824b.show();
            }
        }
    }

    /* renamed from: android.support.v7.internal.widget.SpinnerCompat.b */
    private static class C0390b implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter f827a;
        private ListAdapter f828b;

        public C0390b(SpinnerAdapter spinnerAdapter) {
            this.f827a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f828b = (ListAdapter) spinnerAdapter;
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f828b;
            return listAdapter != null ? listAdapter.areAllItemsEnabled() : true;
        }

        public int getCount() {
            return this.f827a == null ? 0 : this.f827a.getCount();
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return this.f827a == null ? null : this.f827a.getDropDownView(i, view, viewGroup);
        }

        public Object getItem(int i) {
            return this.f827a == null ? null : this.f827a.getItem(i);
        }

        public long getItemId(int i) {
            return this.f827a == null ? -1 : this.f827a.getItemId(i);
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            return this.f827a != null && this.f827a.hasStableIds();
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f828b;
            return listAdapter != null ? listAdapter.isEnabled(i) : true;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.f827a != null) {
                this.f827a.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.f827a != null) {
                this.f827a.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* renamed from: android.support.v7.internal.widget.SpinnerCompat.c */
    private class C0392c extends ListPopupWindow implements C0388d {
        final /* synthetic */ SpinnerCompat f831a;
        private CharSequence f832b;
        private ListAdapter f833c;

        /* renamed from: android.support.v7.internal.widget.SpinnerCompat.c.1 */
        class C03911 implements OnItemClickListener {
            final /* synthetic */ SpinnerCompat f829a;
            final /* synthetic */ C0392c f830b;

            C03911(C0392c c0392c, SpinnerCompat spinnerCompat) {
                this.f830b = c0392c;
                this.f829a = spinnerCompat;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.f830b.f831a.setSelection(i);
                if (this.f830b.f831a.mOnItemClickListener != null) {
                    this.f830b.f831a.performItemClick(view, i, this.f830b.f833c.getItemId(i));
                }
                this.f830b.dismiss();
            }
        }

        public C0392c(SpinnerCompat spinnerCompat, Context context, AttributeSet attributeSet, int i) {
            this.f831a = spinnerCompat;
            super(context, attributeSet, i);
            setAnchorView(spinnerCompat);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new C03911(this, spinnerCompat));
        }

        public void m1753a(CharSequence charSequence) {
            this.f832b = charSequence;
        }

        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.f833c = listAdapter;
        }
    }

    SpinnerCompat(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    SpinnerCompat(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.f840p = new Rect();
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0300k.Spinner, i, 0);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(C0300k.Spinner_android_background));
        if (i2 == -1) {
            i2 = obtainStyledAttributes.getInt(C0300k.Spinner_spinnerMode, 0);
        }
        switch (i2) {
            case C0627j.View_android_focusable /*0*/:
                this.f836l = new C0389a();
                break;
            case C0627j.View_paddingStart /*1*/:
                Object c0392c = new C0392c(this, context, attributeSet, i);
                this.f834j = obtainStyledAttributes.getLayoutDimension(C0300k.Spinner_android_dropDownWidth, -2);
                c0392c.setBackgroundDrawable(obtainStyledAttributes.getDrawable(C0300k.Spinner_android_popupBackground));
                this.f836l = c0392c;
                this.f835k = new C03851(this, this, c0392c);
                break;
        }
        this.f838n = obtainStyledAttributes.getInt(C0300k.Spinner_android_gravity, 17);
        this.f836l.m1750a(obtainStyledAttributes.getString(C0300k.Spinner_prompt));
        this.f839o = obtainStyledAttributes.getBoolean(C0300k.Spinner_disableChildrenWhenDisabled, false);
        obtainStyledAttributes.recycle();
        if (this.f837m != null) {
            this.f836l.setAdapter(this.f837m);
            this.f837m = null;
        }
        this.f841q = obtainStyledAttributes.getTintManager();
    }

    private void m1755a(View view, boolean z) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        if (z) {
            addViewInLayout(view, 0, layoutParams);
        }
        view.setSelected(hasFocus());
        if (this.f839o) {
            view.setEnabled(isEnabled());
        }
        view.measure(ViewGroup.getChildMeasureSpec(this.c, this.h.left + this.h.right, layoutParams.width), ViewGroup.getChildMeasureSpec(this.b, this.h.top + this.h.bottom, layoutParams.height));
        int measuredHeight = this.h.top + ((((getMeasuredHeight() - this.h.bottom) - this.h.top) - view.getMeasuredHeight()) / 2);
        view.layout(0, measuredHeight, view.getMeasuredWidth() + 0, view.getMeasuredHeight() + measuredHeight);
    }

    private View m1756b(int i, boolean z) {
        View a;
        if (!this.mDataChanged) {
            a = this.i.m1720a(i);
            if (a != null) {
                m1755a(a, z);
                return a;
            }
        }
        a = this.a.getView(i, null, this);
        m1755a(a, z);
        return a;
    }

    int m1757a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int max2 = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int i = 0;
        max = 0;
        while (max2 < min) {
            View view2;
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != max) {
                view2 = null;
            } else {
                itemViewType = max;
                view2 = view;
            }
            view = spinnerAdapter.getView(max2, view2, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i = Math.max(i, view.getMeasuredWidth());
            max2++;
            max = itemViewType;
        }
        if (drawable == null) {
            return i;
        }
        drawable.getPadding(this.f840p);
        return (this.f840p.left + this.f840p.right) + i;
    }

    void m1758a(int i, boolean z) {
        int i2 = this.h.left;
        int right = ((getRight() - getLeft()) - this.h.left) - this.h.right;
        if (this.mDataChanged) {
            handleDataChanged();
        }
        if (this.mItemCount == 0) {
            m1726a();
            return;
        }
        if (this.mNextSelectedPosition >= 0) {
            setSelectedPositionInt(this.mNextSelectedPosition);
        }
        m1730b();
        removeAllViewsInLayout();
        this.mFirstPosition = this.mSelectedPosition;
        if (this.a != null) {
            View b = m1756b(this.mSelectedPosition, true);
            int measuredWidth = b.getMeasuredWidth();
            switch (C0191e.m873a(this.f838n, C0234u.m1090e(this)) & 7) {
                case C0627j.View_paddingStart /*1*/:
                    i2 = (i2 + (right / 2)) - (measuredWidth / 2);
                    break;
                case C0627j.Toolbar_contentInsetEnd /*5*/:
                    i2 = (i2 + right) - measuredWidth;
                    break;
            }
            b.offsetLeftAndRight(i2);
        }
        this.i.m1721a();
        invalidate();
        checkSelectionChanged();
        this.mDataChanged = false;
        this.mNeedSync = false;
        setNextSelectedPositionInt(this.mSelectedPosition);
    }

    void m1759a(AdapterViewCompat.OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    public void m1760a(SpinnerAdapter spinnerAdapter) {
        super.m1728a(spinnerAdapter);
        this.i.m1721a();
        if (getContext().getApplicationInfo().targetSdkVersion >= 21 && spinnerAdapter != null && spinnerAdapter.getViewTypeCount() != 1) {
            throw new IllegalArgumentException("Spinner adapter view type count must be 1");
        } else if (this.f836l != null) {
            this.f836l.setAdapter(new C0390b(spinnerAdapter));
        } else {
            this.f837m = new C0390b(spinnerAdapter);
        }
    }

    public int getBaseline() {
        View view = null;
        if (getChildCount() > 0) {
            view = getChildAt(0);
        } else if (this.a != null && this.a.getCount() > 0) {
            view = m1756b(0, false);
            this.i.m1722a(0, view);
        }
        if (view == null) {
            return -1;
        }
        int baseline = view.getBaseline();
        return baseline >= 0 ? view.getTop() + baseline : -1;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        setSelection(i);
        dialogInterface.dismiss();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f836l != null && this.f836l.isShowing()) {
            this.f836l.dismiss();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mInLayout = true;
        m1758a(0, false);
        this.mInLayout = false;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f836l != null && MeasureSpec.getMode(i) == RtlSpacingHelper.UNDEFINED) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), m1757a(m1731c(), getBackground())), MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f822c) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnGlobalLayoutListener(new C03862(this));
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        boolean z = this.f836l != null && this.f836l.isShowing();
        savedState.f822c = z;
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return (this.f835k == null || !this.f835k.onTouch(this, motionEvent)) ? super.onTouchEvent(motionEvent) : true;
    }

    public boolean performClick() {
        boolean performClick = super.performClick();
        if (!performClick) {
            performClick = true;
            if (!this.f836l.isShowing()) {
                this.f836l.show();
            }
        }
        return performClick;
    }

    public /* synthetic */ void setAdapter(Adapter adapter) {
        m1760a((SpinnerAdapter) adapter);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.f839o) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).setEnabled(z);
            }
        }
    }

    public void setOnItemClickListener(AdapterViewCompat.OnItemClickListener onItemClickListener) {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }
}
