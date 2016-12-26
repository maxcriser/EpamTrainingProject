package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.p006e.C0093c;
import android.support.v4.view.C0216o;
import android.support.v4.view.af;
import android.support.v4.widget.C0275g;
import android.support.v4.widget.C0279h;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.support.v7.internal.widget.ListViewCompat;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0300k;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import com.wakdev.nfctools.C0628m.C0627j;
import java.lang.reflect.Method;

public class ListPopupWindow {
    private static final boolean DEBUG = false;
    private static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    private C0418a mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private boolean mForceIgnoreOutsideTouch;
    private Handler mHandler;
    private final C0419b mHideSelector;
    private OnItemClickListener mItemClickListener;
    private OnItemSelectedListener mItemSelectedListener;
    private int mLayoutDirection;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    private final C0423f mResizePopupRunnable;
    private final C0421d mScrollListener;
    private Runnable mShowDropDownRunnable;
    private Rect mTempRect;
    private final C0422e mTouchInterceptor;

    public static abstract class ForwardingListener implements OnTouchListener {
        private int mActivePointerId;
        private Runnable mDisallowIntercept;
        private boolean mForwarding;
        private final int mLongPressTimeout;
        private final float mScaledTouchSlop;
        private final View mSrc;
        private final int mTapTimeout;
        private final int[] mTmpLocation;
        private Runnable mTriggerLongPress;
        private boolean mWasLongPress;

        /* renamed from: android.support.v7.widget.ListPopupWindow.ForwardingListener.a */
        private class C0416a implements Runnable {
            final /* synthetic */ ForwardingListener f869a;

            private C0416a(ForwardingListener forwardingListener) {
                this.f869a = forwardingListener;
            }

            public void run() {
                this.f869a.mSrc.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }

        /* renamed from: android.support.v7.widget.ListPopupWindow.ForwardingListener.b */
        private class C0417b implements Runnable {
            final /* synthetic */ ForwardingListener f870a;

            private C0417b(ForwardingListener forwardingListener) {
                this.f870a = forwardingListener;
            }

            public void run() {
                this.f870a.onLongPress();
            }
        }

        public ForwardingListener(View view) {
            this.mTmpLocation = new int[ListPopupWindow.INPUT_METHOD_NOT_NEEDED];
            this.mSrc = view;
            this.mScaledTouchSlop = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.mTapTimeout = ViewConfiguration.getTapTimeout();
            this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / ListPopupWindow.INPUT_METHOD_NOT_NEEDED;
        }

        private void clearCallbacks() {
            if (this.mTriggerLongPress != null) {
                this.mSrc.removeCallbacks(this.mTriggerLongPress);
            }
            if (this.mDisallowIntercept != null) {
                this.mSrc.removeCallbacks(this.mDisallowIntercept);
            }
        }

        private void onLongPress() {
            clearCallbacks();
            if (this.mSrc.isEnabled() && onForwardingStarted()) {
                this.mSrc.getParent().requestDisallowInterceptTouchEvent(true);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, ListPopupWindow.POSITION_PROMPT_ABOVE);
                this.mSrc.onTouchEvent(obtain);
                obtain.recycle();
                this.mForwarding = true;
                this.mWasLongPress = true;
            }
        }

        private boolean onTouchForwarded(MotionEvent motionEvent) {
            boolean z = true;
            View view = this.mSrc;
            ListPopupWindow popup = getPopup();
            if (popup == null || !popup.isShowing()) {
                return ListPopupWindow.DEBUG;
            }
            View access$600 = popup.mDropDownList;
            if (access$600 == null || !access$600.isShown()) {
                return ListPopupWindow.DEBUG;
            }
            MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
            toGlobalMotionEvent(view, obtainNoHistory);
            toLocalMotionEvent(access$600, obtainNoHistory);
            boolean a = access$600.m1771a(obtainNoHistory, this.mActivePointerId);
            obtainNoHistory.recycle();
            int a2 = C0216o.m954a(motionEvent);
            boolean z2 = (a2 == ListPopupWindow.POSITION_PROMPT_BELOW || a2 == 3) ? ListPopupWindow.DEBUG : ListPopupWindow.POSITION_PROMPT_BELOW;
            if (!(a && z2)) {
                z = ListPopupWindow.DEBUG;
            }
            return z;
        }

        private boolean onTouchObserved(MotionEvent motionEvent) {
            View view = this.mSrc;
            if (!view.isEnabled()) {
                return ListPopupWindow.DEBUG;
            }
            switch (C0216o.m954a(motionEvent)) {
                case ListPopupWindow.POSITION_PROMPT_ABOVE /*0*/:
                    this.mActivePointerId = motionEvent.getPointerId(ListPopupWindow.POSITION_PROMPT_ABOVE);
                    this.mWasLongPress = ListPopupWindow.DEBUG;
                    if (this.mDisallowIntercept == null) {
                        this.mDisallowIntercept = new C0416a();
                    }
                    view.postDelayed(this.mDisallowIntercept, (long) this.mTapTimeout);
                    if (this.mTriggerLongPress == null) {
                        this.mTriggerLongPress = new C0417b();
                    }
                    view.postDelayed(this.mTriggerLongPress, (long) this.mLongPressTimeout);
                    return ListPopupWindow.DEBUG;
                case ListPopupWindow.POSITION_PROMPT_BELOW /*1*/:
                case C0627j.Toolbar_subtitle /*3*/:
                    clearCallbacks();
                    return ListPopupWindow.DEBUG;
                case ListPopupWindow.INPUT_METHOD_NOT_NEEDED /*2*/:
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex < 0 || pointInView(view, motionEvent.getX(findPointerIndex), motionEvent.getY(findPointerIndex), this.mScaledTouchSlop)) {
                        return ListPopupWindow.DEBUG;
                    }
                    clearCallbacks();
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                default:
                    return ListPopupWindow.DEBUG;
            }
        }

        private static boolean pointInView(View view, float f, float f2, float f3) {
            return (f < (-f3) || f2 < (-f3) || f >= ((float) (view.getRight() - view.getLeft())) + f3 || f2 >= ((float) (view.getBottom() - view.getTop())) + f3) ? ListPopupWindow.DEBUG : true;
        }

        private boolean toGlobalMotionEvent(View view, MotionEvent motionEvent) {
            int[] iArr = this.mTmpLocation;
            view.getLocationOnScreen(iArr);
            motionEvent.offsetLocation((float) iArr[ListPopupWindow.POSITION_PROMPT_ABOVE], (float) iArr[ListPopupWindow.POSITION_PROMPT_BELOW]);
            return true;
        }

        private boolean toLocalMotionEvent(View view, MotionEvent motionEvent) {
            int[] iArr = this.mTmpLocation;
            view.getLocationOnScreen(iArr);
            motionEvent.offsetLocation((float) (-iArr[ListPopupWindow.POSITION_PROMPT_ABOVE]), (float) (-iArr[ListPopupWindow.POSITION_PROMPT_BELOW]));
            return true;
        }

        public abstract ListPopupWindow getPopup();

        protected boolean onForwardingStarted() {
            ListPopupWindow popup = getPopup();
            if (!(popup == null || popup.isShowing())) {
                popup.show();
            }
            return true;
        }

        protected boolean onForwardingStopped() {
            ListPopupWindow popup = getPopup();
            if (popup != null && popup.isShowing()) {
                popup.dismiss();
            }
            return true;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean onTouchForwarded;
            boolean z = this.mForwarding;
            if (z) {
                onTouchForwarded = this.mWasLongPress ? onTouchForwarded(motionEvent) : (onTouchForwarded(motionEvent) || !onForwardingStopped()) ? true : ListPopupWindow.DEBUG;
            } else {
                boolean z2 = (onTouchObserved(motionEvent) && onForwardingStarted()) ? ListPopupWindow.POSITION_PROMPT_BELOW : ListPopupWindow.DEBUG;
                if (z2) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, ListPopupWindow.POSITION_PROMPT_ABOVE);
                    this.mSrc.onTouchEvent(obtain);
                    obtain.recycle();
                }
                onTouchForwarded = z2;
            }
            this.mForwarding = onTouchForwarded;
            return (onTouchForwarded || z) ? true : ListPopupWindow.DEBUG;
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.1 */
    class C04131 extends ForwardingListener {
        final /* synthetic */ ListPopupWindow f866a;

        C04131(ListPopupWindow listPopupWindow, View view) {
            this.f866a = listPopupWindow;
            super(view);
        }

        public ListPopupWindow getPopup() {
            return this.f866a;
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.2 */
    class C04142 implements Runnable {
        final /* synthetic */ ListPopupWindow f867a;

        C04142(ListPopupWindow listPopupWindow) {
            this.f867a = listPopupWindow;
        }

        public void run() {
            View anchorView = this.f867a.getAnchorView();
            if (anchorView != null && anchorView.getWindowToken() != null) {
                this.f867a.show();
            }
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.3 */
    class C04153 implements OnItemSelectedListener {
        final /* synthetic */ ListPopupWindow f868a;

        C04153(ListPopupWindow listPopupWindow) {
            this.f868a = listPopupWindow;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != ListPopupWindow.MATCH_PARENT) {
                C0418a access$600 = this.f868a.mDropDownList;
                if (access$600 != null) {
                    access$600.f871a = ListPopupWindow.DEBUG;
                }
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.a */
    private static class C0418a extends ListViewCompat {
        private boolean f871a;
        private boolean f872b;
        private boolean f873c;
        private af f874d;
        private C0275g f875e;

        public C0418a(Context context, boolean z) {
            super(context, null, C0290a.dropDownListViewStyle);
            this.f872b = z;
            setCacheColorHint(ListPopupWindow.POSITION_PROMPT_ABOVE);
        }

        private void m1767a() {
            this.f873c = ListPopupWindow.DEBUG;
            setPressed(ListPopupWindow.DEBUG);
            drawableStateChanged();
            if (this.f874d != null) {
                this.f874d.m829a();
                this.f874d = null;
            }
        }

        private void m1768a(View view, int i) {
            performItemClick(view, i, getItemIdAtPosition(i));
        }

        private void m1769a(View view, int i, float f, float f2) {
            this.f873c = true;
            setPressed(true);
            layoutChildren();
            setSelection(i);
            positionSelectorLikeTouchCompat(i, view, f, f2);
            setSelectorEnabled(ListPopupWindow.DEBUG);
            refreshDrawableState();
        }

        public boolean m1771a(MotionEvent motionEvent, int i) {
            boolean z;
            boolean z2;
            int a = C0216o.m954a(motionEvent);
            switch (a) {
                case ListPopupWindow.POSITION_PROMPT_BELOW /*1*/:
                    z = ListPopupWindow.DEBUG;
                    break;
                case ListPopupWindow.INPUT_METHOD_NOT_NEEDED /*2*/:
                    z = true;
                    break;
                case C0627j.Toolbar_subtitle /*3*/:
                    z = ListPopupWindow.DEBUG;
                    z2 = ListPopupWindow.DEBUG;
                    break;
                default:
                    z = ListPopupWindow.DEBUG;
                    z2 = true;
                    break;
            }
            int findPointerIndex = motionEvent.findPointerIndex(i);
            if (findPointerIndex < 0) {
                z = ListPopupWindow.DEBUG;
                z2 = ListPopupWindow.DEBUG;
            } else {
                int x = (int) motionEvent.getX(findPointerIndex);
                findPointerIndex = (int) motionEvent.getY(findPointerIndex);
                int pointToPosition = pointToPosition(x, findPointerIndex);
                if (pointToPosition == ListPopupWindow.MATCH_PARENT) {
                    z2 = z;
                    z = true;
                } else {
                    View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                    m1769a(childAt, pointToPosition, (float) x, (float) findPointerIndex);
                    if (a == ListPopupWindow.POSITION_PROMPT_BELOW) {
                        m1768a(childAt, pointToPosition);
                    }
                    z = ListPopupWindow.DEBUG;
                    z2 = true;
                }
            }
            if (!z2 || r0) {
                m1767a();
            }
            if (z2) {
                if (this.f875e == null) {
                    this.f875e = new C0275g(this);
                }
                this.f875e.m1250a(true);
                this.f875e.onTouch(this, motionEvent);
            } else if (this.f875e != null) {
                this.f875e.m1250a((boolean) ListPopupWindow.DEBUG);
            }
            return z2;
        }

        public boolean hasFocus() {
            return (this.f872b || super.hasFocus()) ? true : ListPopupWindow.DEBUG;
        }

        public boolean hasWindowFocus() {
            return (this.f872b || super.hasWindowFocus()) ? true : ListPopupWindow.DEBUG;
        }

        public boolean isFocused() {
            return (this.f872b || super.isFocused()) ? true : ListPopupWindow.DEBUG;
        }

        public boolean isInTouchMode() {
            return ((this.f872b && this.f871a) || super.isInTouchMode()) ? true : ListPopupWindow.DEBUG;
        }

        protected boolean touchModeDrawsInPressedStateCompat() {
            return (this.f873c || super.touchModeDrawsInPressedStateCompat()) ? true : ListPopupWindow.DEBUG;
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.b */
    private class C0419b implements Runnable {
        final /* synthetic */ ListPopupWindow f876a;

        private C0419b(ListPopupWindow listPopupWindow) {
            this.f876a = listPopupWindow;
        }

        public void run() {
            this.f876a.clearListSelection();
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.c */
    private class C0420c extends DataSetObserver {
        final /* synthetic */ ListPopupWindow f877a;

        private C0420c(ListPopupWindow listPopupWindow) {
            this.f877a = listPopupWindow;
        }

        public void onChanged() {
            if (this.f877a.isShowing()) {
                this.f877a.show();
            }
        }

        public void onInvalidated() {
            this.f877a.dismiss();
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.d */
    private class C0421d implements OnScrollListener {
        final /* synthetic */ ListPopupWindow f878a;

        private C0421d(ListPopupWindow listPopupWindow) {
            this.f878a = listPopupWindow;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == ListPopupWindow.POSITION_PROMPT_BELOW && !this.f878a.isInputMethodNotNeeded() && this.f878a.mPopup.getContentView() != null) {
                this.f878a.mHandler.removeCallbacks(this.f878a.mResizePopupRunnable);
                this.f878a.mResizePopupRunnable.run();
            }
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.e */
    private class C0422e implements OnTouchListener {
        final /* synthetic */ ListPopupWindow f879a;

        private C0422e(ListPopupWindow listPopupWindow) {
            this.f879a = listPopupWindow;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && this.f879a.mPopup != null && this.f879a.mPopup.isShowing() && x >= 0 && x < this.f879a.mPopup.getWidth() && y >= 0 && y < this.f879a.mPopup.getHeight()) {
                this.f879a.mHandler.postDelayed(this.f879a.mResizePopupRunnable, 250);
            } else if (action == ListPopupWindow.POSITION_PROMPT_BELOW) {
                this.f879a.mHandler.removeCallbacks(this.f879a.mResizePopupRunnable);
            }
            return ListPopupWindow.DEBUG;
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.f */
    private class C0423f implements Runnable {
        final /* synthetic */ ListPopupWindow f880a;

        private C0423f(ListPopupWindow listPopupWindow) {
            this.f880a = listPopupWindow;
        }

        public void run() {
            if (this.f880a.mDropDownList != null && this.f880a.mDropDownList.getCount() > this.f880a.mDropDownList.getChildCount() && this.f880a.mDropDownList.getChildCount() <= this.f880a.mListItemExpandMaximum) {
                this.f880a.mPopup.setInputMethodMode(ListPopupWindow.INPUT_METHOD_NOT_NEEDED);
                this.f880a.show();
            }
        }
    }

    static {
        try {
            Class[] clsArr = new Class[POSITION_PROMPT_BELOW];
            clsArr[POSITION_PROMPT_ABOVE] = Boolean.TYPE;
            sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", clsArr);
        } catch (NoSuchMethodException e) {
            Log.i(TAG, "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, C0290a.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0290a.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, POSITION_PROMPT_ABOVE);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mDropDownHeight = WRAP_CONTENT;
        this.mDropDownWidth = WRAP_CONTENT;
        this.mDropDownGravity = POSITION_PROMPT_ABOVE;
        this.mDropDownAlwaysVisible = DEBUG;
        this.mForceIgnoreOutsideTouch = DEBUG;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = POSITION_PROMPT_ABOVE;
        this.mResizePopupRunnable = new C0423f();
        this.mTouchInterceptor = new C0422e();
        this.mScrollListener = new C0421d();
        this.mHideSelector = new C0419b();
        this.mHandler = new Handler();
        this.mTempRect = new Rect();
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0300k.ListPopupWindow, i, i2);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(C0300k.ListPopupWindow_android_dropDownHorizontalOffset, POSITION_PROMPT_ABOVE);
        this.mDropDownVerticalOffset = obtainStyledAttributes.getDimensionPixelOffset(C0300k.ListPopupWindow_android_dropDownVerticalOffset, POSITION_PROMPT_ABOVE);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        this.mPopup = new AppCompatPopupWindow(context, attributeSet, i);
        this.mPopup.setInputMethodMode(POSITION_PROMPT_BELOW);
        this.mLayoutDirection = C0093c.m399a(this.mContext.getResources().getConfiguration().locale);
    }

    private int buildDropDown() {
        int measuredHeight;
        int i;
        int i2;
        int maxAvailableHeight;
        LayoutParams layoutParams;
        View view;
        if (this.mDropDownList == null) {
            Context context = this.mContext;
            this.mShowDropDownRunnable = new C04142(this);
            this.mDropDownList = new C0418a(context, !this.mModal ? true : DEBUG);
            if (this.mDropDownListHighlight != null) {
                this.mDropDownList.setSelector(this.mDropDownListHighlight);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new C04153(this));
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            if (this.mItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
            }
            View view2 = this.mDropDownList;
            View view3 = this.mPromptView;
            if (view3 != null) {
                View linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(POSITION_PROMPT_BELOW);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(MATCH_PARENT, POSITION_PROMPT_ABOVE, 1.0f);
                switch (this.mPromptPosition) {
                    case POSITION_PROMPT_ABOVE /*0*/:
                        linearLayout.addView(view3);
                        linearLayout.addView(view2, layoutParams2);
                        break;
                    case POSITION_PROMPT_BELOW /*1*/:
                        linearLayout.addView(view2, layoutParams2);
                        linearLayout.addView(view3);
                        break;
                    default:
                        Log.e(TAG, "Invalid hint position " + this.mPromptPosition);
                        break;
                }
                view3.measure(MeasureSpec.makeMeasureSpec(this.mDropDownWidth, RtlSpacingHelper.UNDEFINED), POSITION_PROMPT_ABOVE);
                layoutParams = (LayoutParams) view3.getLayoutParams();
                measuredHeight = layoutParams.bottomMargin + (view3.getMeasuredHeight() + layoutParams.topMargin);
                view = linearLayout;
            } else {
                view = view2;
                measuredHeight = POSITION_PROMPT_ABOVE;
            }
            this.mPopup.setContentView(view);
            i = measuredHeight;
        } else {
            ViewGroup viewGroup = (ViewGroup) this.mPopup.getContentView();
            view = this.mPromptView;
            if (view != null) {
                layoutParams = (LayoutParams) view.getLayoutParams();
                i = layoutParams.bottomMargin + (view.getMeasuredHeight() + layoutParams.topMargin);
            } else {
                i = POSITION_PROMPT_ABOVE;
            }
        }
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            measuredHeight = this.mTempRect.top + this.mTempRect.bottom;
            if (this.mDropDownVerticalOffsetSet) {
                i2 = measuredHeight;
            } else {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
                i2 = measuredHeight;
            }
        } else {
            this.mTempRect.setEmpty();
            i2 = POSITION_PROMPT_ABOVE;
        }
        if (this.mPopup.getInputMethodMode() == INPUT_METHOD_NOT_NEEDED) {
            maxAvailableHeight = this.mPopup.getMaxAvailableHeight(getAnchorView(), this.mDropDownVerticalOffset);
        } else {
            maxAvailableHeight = this.mPopup.getMaxAvailableHeight(getAnchorView(), this.mDropDownVerticalOffset);
        }
        if (this.mDropDownAlwaysVisible || this.mDropDownHeight == MATCH_PARENT) {
            return maxAvailableHeight + i2;
        }
        int makeMeasureSpec;
        switch (this.mDropDownWidth) {
            case WRAP_CONTENT /*-2*/:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), RtlSpacingHelper.UNDEFINED);
                break;
            case MATCH_PARENT /*-1*/:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), 1073741824);
                break;
            default:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.mDropDownWidth, 1073741824);
                break;
        }
        measuredHeight = this.mDropDownList.measureHeightOfChildrenCompat(makeMeasureSpec, POSITION_PROMPT_ABOVE, MATCH_PARENT, maxAvailableHeight - i, MATCH_PARENT);
        if (measuredHeight > 0) {
            i += i2;
        }
        return measuredHeight + i;
    }

    private static boolean isConfirmKey(int i) {
        return (i == 66 || i == 23) ? true : DEBUG;
    }

    private void removePromptView() {
        if (this.mPromptView != null) {
            ViewParent parent = this.mPromptView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
    }

    private void setPopupClipToScreenEnabled(boolean z) {
        if (sClipToWindowEnabledMethod != null) {
            try {
                Method method = sClipToWindowEnabledMethod;
                PopupWindow popupWindow = this.mPopup;
                Object[] objArr = new Object[POSITION_PROMPT_BELOW];
                objArr[POSITION_PROMPT_ABOVE] = Boolean.valueOf(z);
                method.invoke(popupWindow, objArr);
            } catch (Exception e) {
                Log.i(TAG, "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void clearListSelection() {
        C0418a c0418a = this.mDropDownList;
        if (c0418a != null) {
            c0418a.f871a = true;
            c0418a.requestLayout();
        }
    }

    public OnTouchListener createDragToOpenListener(View view) {
        return new C04131(this, view);
    }

    public void dismiss() {
        this.mPopup.dismiss();
        removePromptView();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    public ListView getListView() {
        return this.mDropDownList;
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    public Object getSelectedItem() {
        return !isShowing() ? null : this.mDropDownList.getSelectedItem();
    }

    public long getSelectedItemId() {
        return !isShowing() ? Long.MIN_VALUE : this.mDropDownList.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        return !isShowing() ? MATCH_PARENT : this.mDropDownList.getSelectedItemPosition();
    }

    public View getSelectedView() {
        return !isShowing() ? null : this.mDropDownList.getSelectedView();
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public int getVerticalOffset() {
        return !this.mDropDownVerticalOffsetSet ? POSITION_PROMPT_ABOVE : this.mDropDownVerticalOffset;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == INPUT_METHOD_NOT_NEEDED ? true : DEBUG;
    }

    public boolean isModal() {
        return this.mModal;
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (isShowing() && i != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !isConfirmKey(i))) {
            int selectedItemPosition = this.mDropDownList.getSelectedItemPosition();
            boolean z = !this.mPopup.isAboveAnchor() ? true : DEBUG;
            ListAdapter listAdapter = this.mAdapter;
            int i2 = Integer.MAX_VALUE;
            int i3 = RtlSpacingHelper.UNDEFINED;
            if (listAdapter != null) {
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                i2 = areAllItemsEnabled ? POSITION_PROMPT_ABOVE : this.mDropDownList.lookForSelectablePosition(POSITION_PROMPT_ABOVE, true);
                i3 = areAllItemsEnabled ? listAdapter.getCount() + MATCH_PARENT : this.mDropDownList.lookForSelectablePosition(listAdapter.getCount() + MATCH_PARENT, DEBUG);
            }
            if (!(z && i == 19 && selectedItemPosition <= r4) && (z || i != 20 || selectedItemPosition < i3)) {
                this.mDropDownList.f871a = DEBUG;
                if (this.mDropDownList.onKeyDown(i, keyEvent)) {
                    this.mPopup.setInputMethodMode(INPUT_METHOD_NOT_NEEDED);
                    this.mDropDownList.requestFocusFromTouch();
                    show();
                    switch (i) {
                        case C0627j.Toolbar_collapseContentDescription /*19*/:
                        case C0627j.Toolbar_navigationIcon /*20*/:
                        case C0627j.Theme_actionModeStyle /*23*/:
                        case C0627j.Theme_listPreferredItemPaddingRight /*66*/:
                            return true;
                    }
                } else if (z && i == 20) {
                    if (selectedItemPosition == i3) {
                        return true;
                    }
                } else if (!z && i == 19 && selectedItemPosition == r4) {
                    return true;
                }
            }
            clearListSelection();
            this.mPopup.setInputMethodMode(POSITION_PROMPT_BELOW);
            show();
            return true;
        }
        return DEBUG;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4 && isShowing()) {
            View view = this.mDropDownAnchorView;
            DispatcherState keyDispatcherState;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                keyDispatcherState = view.getKeyDispatcherState();
                if (keyDispatcherState == null) {
                    return true;
                }
                keyDispatcherState.startTracking(keyEvent, this);
                return true;
            } else if (keyEvent.getAction() == POSITION_PROMPT_BELOW) {
                keyDispatcherState = view.getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    dismiss();
                    return true;
                }
            }
        }
        return DEBUG;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (!isShowing() || this.mDropDownList.getSelectedItemPosition() < 0) {
            return DEBUG;
        }
        boolean onKeyUp = this.mDropDownList.onKeyUp(i, keyEvent);
        if (!onKeyUp || !isConfirmKey(i)) {
            return onKeyUp;
        }
        dismiss();
        return onKeyUp;
    }

    public boolean performItemClick(int i) {
        if (!isShowing()) {
            return DEBUG;
        }
        if (this.mItemClickListener != null) {
            AdapterView adapterView = this.mDropDownList;
            View childAt = adapterView.getChildAt(i - adapterView.getFirstVisiblePosition());
            ListAdapter adapter = adapterView.getAdapter();
            this.mItemClickListener.onItemClick(adapterView, childAt, i, adapter.getItemId(i));
        }
        return true;
    }

    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.mObserver == null) {
            this.mObserver = new C0420c();
        } else if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = listAdapter;
        if (this.mAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        if (this.mDropDownList != null) {
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(View view) {
        this.mDropDownAnchorView = view;
    }

    public void setAnimationStyle(int i) {
        this.mPopup.setAnimationStyle(i);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i) {
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            this.mDropDownWidth = (this.mTempRect.left + this.mTempRect.right) + i;
            return;
        }
        setWidth(i);
    }

    public void setDropDownAlwaysVisible(boolean z) {
        this.mDropDownAlwaysVisible = z;
    }

    public void setDropDownGravity(int i) {
        this.mDropDownGravity = i;
    }

    public void setForceIgnoreOutsideTouch(boolean z) {
        this.mForceIgnoreOutsideTouch = z;
    }

    public void setHeight(int i) {
        this.mDropDownHeight = i;
    }

    public void setHorizontalOffset(int i) {
        this.mDropDownHorizontalOffset = i;
    }

    public void setInputMethodMode(int i) {
        this.mPopup.setInputMethodMode(i);
    }

    void setListItemExpandMax(int i) {
        this.mListItemExpandMaximum = i;
    }

    public void setListSelector(Drawable drawable) {
        this.mDropDownListHighlight = drawable;
    }

    public void setModal(boolean z) {
        this.mModal = z;
        this.mPopup.setFocusable(z);
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    public void setPromptPosition(int i) {
        this.mPromptPosition = i;
    }

    public void setPromptView(View view) {
        boolean isShowing = isShowing();
        if (isShowing) {
            removePromptView();
        }
        this.mPromptView = view;
        if (isShowing) {
            show();
        }
    }

    public void setSelection(int i) {
        C0418a c0418a = this.mDropDownList;
        if (isShowing() && c0418a != null) {
            c0418a.f871a = DEBUG;
            c0418a.setSelection(i);
            if (VERSION.SDK_INT >= 11 && c0418a.getChoiceMode() != 0) {
                c0418a.setItemChecked(i, true);
            }
        }
    }

    public void setSoftInputMode(int i) {
        this.mPopup.setSoftInputMode(i);
    }

    public void setVerticalOffset(int i) {
        this.mDropDownVerticalOffset = i;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int i) {
        this.mDropDownWidth = i;
    }

    public void show() {
        boolean z = true;
        boolean z2 = DEBUG;
        int i = MATCH_PARENT;
        int buildDropDown = buildDropDown();
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        if (this.mPopup.isShowing()) {
            int width = this.mDropDownWidth == MATCH_PARENT ? MATCH_PARENT : this.mDropDownWidth == WRAP_CONTENT ? getAnchorView().getWidth() : this.mDropDownWidth;
            if (this.mDropDownHeight == MATCH_PARENT) {
                if (!isInputMethodNotNeeded) {
                    buildDropDown = MATCH_PARENT;
                }
                if (isInputMethodNotNeeded) {
                    PopupWindow popupWindow = this.mPopup;
                    if (this.mDropDownWidth != MATCH_PARENT) {
                        i = POSITION_PROMPT_ABOVE;
                    }
                    popupWindow.setWindowLayoutMode(i, POSITION_PROMPT_ABOVE);
                } else {
                    this.mPopup.setWindowLayoutMode(this.mDropDownWidth == MATCH_PARENT ? MATCH_PARENT : POSITION_PROMPT_ABOVE, MATCH_PARENT);
                }
            } else if (this.mDropDownHeight != WRAP_CONTENT) {
                buildDropDown = this.mDropDownHeight;
            }
            PopupWindow popupWindow2 = this.mPopup;
            if (!(this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible)) {
                z2 = true;
            }
            popupWindow2.setOutsideTouchable(z2);
            this.mPopup.update(getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, width, buildDropDown);
            return;
        }
        int i2;
        if (this.mDropDownWidth == MATCH_PARENT) {
            i2 = MATCH_PARENT;
        } else if (this.mDropDownWidth == WRAP_CONTENT) {
            this.mPopup.setWidth(getAnchorView().getWidth());
            i2 = POSITION_PROMPT_ABOVE;
        } else {
            this.mPopup.setWidth(this.mDropDownWidth);
            i2 = POSITION_PROMPT_ABOVE;
        }
        if (this.mDropDownHeight == MATCH_PARENT) {
            width = MATCH_PARENT;
        } else if (this.mDropDownHeight == WRAP_CONTENT) {
            this.mPopup.setHeight(buildDropDown);
            width = POSITION_PROMPT_ABOVE;
        } else {
            this.mPopup.setHeight(this.mDropDownHeight);
            width = POSITION_PROMPT_ABOVE;
        }
        this.mPopup.setWindowLayoutMode(i2, width);
        setPopupClipToScreenEnabled(true);
        popupWindow = this.mPopup;
        if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
            z = DEBUG;
        }
        popupWindow.setOutsideTouchable(z);
        this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
        C0279h.m1318a(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
        this.mDropDownList.setSelection(MATCH_PARENT);
        if (!this.mModal || this.mDropDownList.isInTouchMode()) {
            clearListSelection();
        }
        if (!this.mModal) {
            this.mHandler.post(this.mHideSelector);
        }
    }
}
