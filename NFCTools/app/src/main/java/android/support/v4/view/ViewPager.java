package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.p005d.C0084c;
import android.support.v4.p005d.C0085d;
import android.support.v4.view.p008a.C0141a;
import android.support.v4.view.p008a.C0162g;
import android.support.v4.widget.C0273e;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.wakdev.nfctools.C0628m.C0627j;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewPager extends ViewGroup {
    private static final int[] f336a;
    private static final C0125i ag;
    private static final Comparator<C0117b> f337c;
    private static final Interpolator f338d;
    private boolean f339A;
    private boolean f340B;
    private int f341C;
    private int f342D;
    private int f343E;
    private float f344F;
    private float f345G;
    private float f346H;
    private float f347I;
    private int f348J;
    private VelocityTracker f349K;
    private int f350L;
    private int f351M;
    private int f352N;
    private int f353O;
    private boolean f354P;
    private C0273e f355Q;
    private C0273e f356R;
    private boolean f357S;
    private boolean f358T;
    private boolean f359U;
    private int f360V;
    private C0122f f361W;
    private C0122f aa;
    private C0121e ab;
    private C0123g ac;
    private Method ad;
    private int ae;
    private ArrayList<View> af;
    private final Runnable ah;
    private int ai;
    private int f362b;
    private final ArrayList<C0117b> f363e;
    private final C0117b f364f;
    private final Rect f365g;
    private C0044r f366h;
    private int f367i;
    private int f368j;
    private Parcelable f369k;
    private ClassLoader f370l;
    private Scroller f371m;
    private C0124h f372n;
    private int f373o;
    private Drawable f374p;
    private int f375q;
    private int f376r;
    private float f377s;
    private float f378t;
    private int f379u;
    private int f380v;
    private boolean f381w;
    private boolean f382x;
    private boolean f383y;
    private int f384z;

    /* renamed from: android.support.v4.view.ViewPager.1 */
    static class C01121 implements Comparator<C0117b> {
        C01121() {
        }

        public int m470a(C0117b c0117b, C0117b c0117b2) {
            return c0117b.f321b - c0117b2.f321b;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m470a((C0117b) obj, (C0117b) obj2);
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.2 */
    static class C01132 implements Interpolator {
        C01132() {
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.3 */
    class C01143 implements Runnable {
        final /* synthetic */ ViewPager f316a;

        C01143(ViewPager viewPager) {
            this.f316a = viewPager;
        }

        public void run() {
            this.f316a.setScrollState(0);
            this.f316a.m530c();
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f317a;
        Parcelable f318b;
        ClassLoader f319c;

        /* renamed from: android.support.v4.view.ViewPager.SavedState.1 */
        static class C01151 implements C0085d<SavedState> {
            C01151() {
            }

            public /* synthetic */ Object m471a(Parcel parcel, ClassLoader classLoader) {
                return m473b(parcel, classLoader);
            }

            public /* synthetic */ Object[] m472a(int i) {
                return m474b(i);
            }

            public SavedState m473b(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState[] m474b(int i) {
                return new SavedState[i];
            }
        }

        static {
            CREATOR = C0084c.m385a(new C01151());
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            if (classLoader == null) {
                classLoader = getClass().getClassLoader();
            }
            this.f317a = parcel.readInt();
            this.f318b = parcel.readParcelable(classLoader);
            this.f319c = classLoader;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f317a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f317a);
            parcel.writeParcelable(this.f318b, i);
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.a */
    interface C0116a {
    }

    /* renamed from: android.support.v4.view.ViewPager.b */
    static class C0117b {
        Object f320a;
        int f321b;
        boolean f322c;
        float f323d;
        float f324e;

        C0117b() {
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.c */
    public static class C0118c extends LayoutParams {
        public boolean f325a;
        public int f326b;
        float f327c;
        boolean f328d;
        int f329e;
        int f330f;

        public C0118c() {
            super(-1, -1);
            this.f327c = 0.0f;
        }

        public C0118c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f327c = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f336a);
            this.f326b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.d */
    class C0120d extends C0119a {
        final /* synthetic */ ViewPager f334b;

        C0120d(ViewPager viewPager) {
            this.f334b = viewPager;
        }

        private boolean m485b() {
            return this.f334b.f366h != null && this.f334b.f366h.m251b() > 1;
        }

        public void m486a(View view, C0141a c0141a) {
            super.m478a(view, c0141a);
            c0141a.m671a(ViewPager.class.getName());
            c0141a.m672a(m485b());
            if (this.f334b.canScrollHorizontally(1)) {
                c0141a.m668a(4096);
            }
            if (this.f334b.canScrollHorizontally(-1)) {
                c0141a.m668a(8192);
            }
        }

        public boolean m487a(View view, int i, Bundle bundle) {
            if (super.m480a(view, i, bundle)) {
                return true;
            }
            switch (i) {
                case 4096:
                    if (!this.f334b.canScrollHorizontally(1)) {
                        return false;
                    }
                    this.f334b.setCurrentItem(this.f334b.f367i + 1);
                    return true;
                case 8192:
                    if (!this.f334b.canScrollHorizontally(-1)) {
                        return false;
                    }
                    this.f334b.setCurrentItem(this.f334b.f367i - 1);
                    return true;
                default:
                    return false;
            }
        }

        public void m488d(View view, AccessibilityEvent accessibilityEvent) {
            super.m484d(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            C0162g a = C0162g.m752a();
            a.m754a(m485b());
            if (accessibilityEvent.getEventType() == 4096 && this.f334b.f366h != null) {
                a.m753a(this.f334b.f366h.m251b());
                a.m755b(this.f334b.f367i);
                a.m756c(this.f334b.f367i);
            }
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.e */
    interface C0121e {
        void m489a(C0044r c0044r, C0044r c0044r2);
    }

    /* renamed from: android.support.v4.view.ViewPager.f */
    public interface C0122f {
        void m490a(int i);

        void m491a(int i, float f, int i2);

        void m492b(int i);
    }

    /* renamed from: android.support.v4.view.ViewPager.g */
    public interface C0123g {
        void m493a(View view, float f);
    }

    /* renamed from: android.support.v4.view.ViewPager.h */
    private class C0124h extends DataSetObserver {
        final /* synthetic */ ViewPager f335a;

        private C0124h(ViewPager viewPager) {
            this.f335a = viewPager;
        }

        public void onChanged() {
            this.f335a.m529b();
        }

        public void onInvalidated() {
            this.f335a.m529b();
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.i */
    static class C0125i implements Comparator<View> {
        C0125i() {
        }

        public int m494a(View view, View view2) {
            C0118c c0118c = (C0118c) view.getLayoutParams();
            C0118c c0118c2 = (C0118c) view2.getLayoutParams();
            return c0118c.f325a != c0118c2.f325a ? c0118c.f325a ? 1 : -1 : c0118c.f329e - c0118c2.f329e;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m494a((View) obj, (View) obj2);
        }
    }

    static {
        f336a = new int[]{16842931};
        f337c = new C01121();
        f338d = new C01132();
        ag = new C0125i();
    }

    public ViewPager(Context context) {
        super(context);
        this.f363e = new ArrayList();
        this.f364f = new C0117b();
        this.f365g = new Rect();
        this.f368j = -1;
        this.f369k = null;
        this.f370l = null;
        this.f377s = -3.4028235E38f;
        this.f378t = Float.MAX_VALUE;
        this.f384z = 1;
        this.f348J = -1;
        this.f357S = true;
        this.f358T = false;
        this.ah = new C01143(this);
        this.ai = 0;
        m518a();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f363e = new ArrayList();
        this.f364f = new C0117b();
        this.f365g = new Rect();
        this.f368j = -1;
        this.f369k = null;
        this.f370l = null;
        this.f377s = -3.4028235E38f;
        this.f378t = Float.MAX_VALUE;
        this.f384z = 1;
        this.f348J = -1;
        this.f357S = true;
        this.f358T = false;
        this.ah = new C01143(this);
        this.ai = 0;
        m518a();
    }

    private int m495a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.f352N || Math.abs(i2) <= this.f350L) {
            i = (int) ((i >= this.f367i ? 0.4f : 0.6f) + (((float) i) + f));
        } else if (i2 <= 0) {
            i++;
        }
        if (this.f363e.size() <= 0) {
            return i;
        }
        return Math.max(((C0117b) this.f363e.get(0)).f321b, Math.min(i, ((C0117b) this.f363e.get(this.f363e.size() - 1)).f321b));
    }

    private Rect m496a(Rect rect, View view) {
        Rect rect2 = rect == null ? new Rect() : rect;
        if (view == null) {
            rect2.set(0, 0, 0, 0);
            return rect2;
        }
        rect2.left = view.getLeft();
        rect2.right = view.getRight();
        rect2.top = view.getTop();
        rect2.bottom = view.getBottom();
        ViewPager parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = parent;
            rect2.left += viewGroup.getLeft();
            rect2.right += viewGroup.getRight();
            rect2.top += viewGroup.getTop();
            rect2.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect2;
    }

    private void m498a(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.f363e.isEmpty()) {
            C0117b b = m527b(this.f367i);
            int min = (int) ((b != null ? Math.min(b.f324e, this.f378t) : 0.0f) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                m503a(false);
                scrollTo(min, getScrollY());
                return;
            }
            return;
        }
        int paddingLeft = (int) (((float) (((i - getPaddingLeft()) - getPaddingRight()) + i3)) * (((float) getScrollX()) / ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))));
        scrollTo(paddingLeft, getScrollY());
        if (!this.f371m.isFinished()) {
            this.f371m.startScroll(paddingLeft, 0, (int) (m527b(this.f367i).f324e * ((float) i)), 0, this.f371m.getDuration() - this.f371m.timePassed());
        }
    }

    private void m499a(int i, boolean z, int i2, boolean z2) {
        int max;
        C0117b b = m527b(i);
        if (b != null) {
            max = (int) (Math.max(this.f377s, Math.min(b.f324e, this.f378t)) * ((float) getClientWidth()));
        } else {
            max = 0;
        }
        if (z) {
            m521a(max, 0, i2);
            if (z2 && this.f361W != null) {
                this.f361W.m490a(i);
            }
            if (z2 && this.aa != null) {
                this.aa.m490a(i);
                return;
            }
            return;
        }
        if (z2 && this.f361W != null) {
            this.f361W.m490a(i);
        }
        if (z2 && this.aa != null) {
            this.aa.m490a(i);
        }
        m503a(false);
        scrollTo(max, 0);
        m509d(max);
    }

    private void m500a(C0117b c0117b, int i, C0117b c0117b2) {
        float f;
        int i2;
        C0117b c0117b3;
        int i3;
        int b = this.f366h.m251b();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? ((float) this.f373o) / ((float) clientWidth) : 0.0f;
        if (c0117b2 != null) {
            clientWidth = c0117b2.f321b;
            int i4;
            if (clientWidth < c0117b.f321b) {
                f = (c0117b2.f324e + c0117b2.f323d) + f2;
                i4 = clientWidth + 1;
                i2 = 0;
                while (i4 <= c0117b.f321b && i2 < this.f363e.size()) {
                    c0117b3 = (C0117b) this.f363e.get(i2);
                    while (i4 > c0117b3.f321b && i2 < this.f363e.size() - 1) {
                        i2++;
                        c0117b3 = (C0117b) this.f363e.get(i2);
                    }
                    while (i4 < c0117b3.f321b) {
                        f += this.f366h.m258d(i4) + f2;
                        i4++;
                    }
                    c0117b3.f324e = f;
                    f += c0117b3.f323d + f2;
                    i4++;
                }
            } else if (clientWidth > c0117b.f321b) {
                i2 = this.f363e.size() - 1;
                f = c0117b2.f324e;
                i4 = clientWidth - 1;
                while (i4 >= c0117b.f321b && i2 >= 0) {
                    c0117b3 = (C0117b) this.f363e.get(i2);
                    while (i4 < c0117b3.f321b && i2 > 0) {
                        i2--;
                        c0117b3 = (C0117b) this.f363e.get(i2);
                    }
                    while (i4 > c0117b3.f321b) {
                        f -= this.f366h.m258d(i4) + f2;
                        i4--;
                    }
                    f -= c0117b3.f323d + f2;
                    c0117b3.f324e = f;
                    i4--;
                }
            }
        }
        int size = this.f363e.size();
        float f3 = c0117b.f324e;
        i2 = c0117b.f321b - 1;
        this.f377s = c0117b.f321b == 0 ? c0117b.f324e : -3.4028235E38f;
        this.f378t = c0117b.f321b == b + -1 ? (c0117b.f324e + c0117b.f323d) - 1.0f : Float.MAX_VALUE;
        for (i3 = i - 1; i3 >= 0; i3--) {
            c0117b3 = (C0117b) this.f363e.get(i3);
            f = f3;
            while (i2 > c0117b3.f321b) {
                f -= this.f366h.m258d(i2) + f2;
                i2--;
            }
            f3 = f - (c0117b3.f323d + f2);
            c0117b3.f324e = f3;
            if (c0117b3.f321b == 0) {
                this.f377s = f3;
            }
            i2--;
        }
        f3 = (c0117b.f324e + c0117b.f323d) + f2;
        i2 = c0117b.f321b + 1;
        for (i3 = i + 1; i3 < size; i3++) {
            c0117b3 = (C0117b) this.f363e.get(i3);
            f = f3;
            while (i2 < c0117b3.f321b) {
                f = (this.f366h.m258d(i2) + f2) + f;
                i2++;
            }
            if (c0117b3.f321b == b - 1) {
                this.f378t = (c0117b3.f323d + f) - 1.0f;
            }
            c0117b3.f324e = f;
            f3 = f + (c0117b3.f323d + f2);
            i2++;
        }
        this.f358T = false;
    }

    private void m502a(MotionEvent motionEvent) {
        int b = C0216o.m956b(motionEvent);
        if (C0216o.m957b(motionEvent, b) == this.f348J) {
            b = b == 0 ? 1 : 0;
            this.f344F = C0216o.m958c(motionEvent, b);
            this.f348J = C0216o.m957b(motionEvent, b);
            if (this.f349K != null) {
                this.f349K.clear();
            }
        }
    }

    private void m503a(boolean z) {
        int scrollX;
        boolean z2 = this.ai == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.f371m.abortAnimation();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.f371m.getCurrX();
            int currY = this.f371m.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
        }
        this.f383y = false;
        boolean z3 = z2;
        for (scrollX = 0; scrollX < this.f363e.size(); scrollX++) {
            C0117b c0117b = (C0117b) this.f363e.get(scrollX);
            if (c0117b.f322c) {
                c0117b.f322c = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            C0234u.m1080a((View) this, this.ah);
        } else {
            this.ah.run();
        }
    }

    private boolean m504a(float f, float f2) {
        return (f < ((float) this.f342D) && f2 > 0.0f) || (f > ((float) (getWidth() - this.f342D)) && f2 < 0.0f);
    }

    private void m506b(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            C0234u.m1076a(getChildAt(i), z ? 2 : 0, null);
        }
    }

    private boolean m507b(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        boolean z3 = false;
        float f3 = this.f344F - f;
        this.f344F = f;
        float scrollX = ((float) getScrollX()) + f3;
        int clientWidth = getClientWidth();
        float f4 = ((float) clientWidth) * this.f377s;
        float f5 = ((float) clientWidth) * this.f378t;
        C0117b c0117b = (C0117b) this.f363e.get(0);
        C0117b c0117b2 = (C0117b) this.f363e.get(this.f363e.size() - 1);
        if (c0117b.f321b != 0) {
            f4 = c0117b.f324e * ((float) clientWidth);
            z = false;
        } else {
            z = true;
        }
        if (c0117b2.f321b != this.f366h.m251b() - 1) {
            f2 = c0117b2.f324e * ((float) clientWidth);
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollX < f4) {
            if (z) {
                z3 = this.f355Q.m1301a(Math.abs(f4 - scrollX) / ((float) clientWidth));
            }
        } else if (scrollX > f2) {
            if (z2) {
                z3 = this.f356R.m1301a(Math.abs(scrollX - f2) / ((float) clientWidth));
            }
            f4 = f2;
        } else {
            f4 = scrollX;
        }
        this.f344F += f4 - ((float) ((int) f4));
        scrollTo((int) f4, getScrollY());
        m509d((int) f4);
        return z3;
    }

    private void m508c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean m509d(int i) {
        if (this.f363e.size() == 0) {
            this.f359U = false;
            m520a(0, 0.0f, 0);
            if (this.f359U) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        C0117b i2 = m513i();
        int clientWidth = getClientWidth();
        int i3 = this.f373o + clientWidth;
        float f = ((float) this.f373o) / ((float) clientWidth);
        int i4 = i2.f321b;
        float f2 = ((((float) i) / ((float) clientWidth)) - i2.f324e) / (i2.f323d + f);
        clientWidth = (int) (((float) i3) * f2);
        this.f359U = false;
        m520a(i4, f2, clientWidth);
        if (this.f359U) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private void m511g() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((C0118c) getChildAt(i).getLayoutParams()).f325a) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void m512h() {
        if (this.ae != 0) {
            if (this.af == null) {
                this.af = new ArrayList();
            } else {
                this.af.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.af.add(getChildAt(i));
            }
            Collections.sort(this.af, ag);
        }
    }

    private C0117b m513i() {
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? ((float) getScrollX()) / ((float) clientWidth) : 0.0f;
        float f = clientWidth > 0 ? ((float) this.f373o) / ((float) clientWidth) : 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = -1;
        int i2 = 0;
        Object obj = 1;
        C0117b c0117b = null;
        while (i2 < this.f363e.size()) {
            int i3;
            C0117b c0117b2;
            C0117b c0117b3 = (C0117b) this.f363e.get(i2);
            C0117b c0117b4;
            if (obj != null || c0117b3.f321b == i + 1) {
                c0117b4 = c0117b3;
                i3 = i2;
                c0117b2 = c0117b4;
            } else {
                c0117b3 = this.f364f;
                c0117b3.f324e = (f2 + f3) + f;
                c0117b3.f321b = i + 1;
                c0117b3.f323d = this.f366h.m258d(c0117b3.f321b);
                c0117b4 = c0117b3;
                i3 = i2 - 1;
                c0117b2 = c0117b4;
            }
            f2 = c0117b2.f324e;
            f3 = (c0117b2.f323d + f2) + f;
            if (obj == null && scrollX < f2) {
                return c0117b;
            }
            if (scrollX < f3 || i3 == this.f363e.size() - 1) {
                return c0117b2;
            }
            f3 = f2;
            i = c0117b2.f321b;
            obj = null;
            f2 = c0117b2.f323d;
            c0117b = c0117b2;
            i2 = i3 + 1;
        }
        return c0117b;
    }

    private void m514j() {
        this.f339A = false;
        this.f340B = false;
        if (this.f349K != null) {
            this.f349K.recycle();
            this.f349K = null;
        }
    }

    private void setScrollState(int i) {
        if (this.ai != i) {
            this.ai = i;
            if (this.ac != null) {
                m506b(i != 0);
            }
            if (this.f361W != null) {
                this.f361W.m492b(i);
            }
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.f382x != z) {
            this.f382x = z;
        }
    }

    float m515a(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    C0117b m516a(int i, int i2) {
        C0117b c0117b = new C0117b();
        c0117b.f321b = i;
        c0117b.f320a = this.f366h.m243a((ViewGroup) this, i);
        c0117b.f323d = this.f366h.m258d(i);
        if (i2 < 0 || i2 >= this.f363e.size()) {
            this.f363e.add(c0117b);
        } else {
            this.f363e.add(i2, c0117b);
        }
        return c0117b;
    }

    C0117b m517a(View view) {
        for (int i = 0; i < this.f363e.size(); i++) {
            C0117b c0117b = (C0117b) this.f363e.get(i);
            if (this.f366h.m250a(view, c0117b.f320a)) {
                return c0117b;
            }
        }
        return null;
    }

    void m518a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f371m = new Scroller(context, f338d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.f343E = ac.m781a(viewConfiguration);
        this.f350L = (int) (400.0f * f);
        this.f351M = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f355Q = new C0273e(context);
        this.f356R = new C0273e(context);
        this.f352N = (int) (25.0f * f);
        this.f353O = (int) (2.0f * f);
        this.f341C = (int) (16.0f * f);
        C0234u.m1078a((View) this, new C0120d(this));
        if (C0234u.m1086c(this) == 0) {
            C0234u.m1085b((View) this, 1);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m519a(int r19) {
        /*
        r18 = this;
        r3 = 0;
        r2 = 2;
        r0 = r18;
        r4 = r0.f367i;
        r0 = r19;
        if (r4 == r0) goto L_0x033f;
    L_0x000a:
        r0 = r18;
        r2 = r0.f367i;
        r0 = r19;
        if (r2 >= r0) goto L_0x0030;
    L_0x0012:
        r2 = 66;
    L_0x0014:
        r0 = r18;
        r3 = r0.f367i;
        r0 = r18;
        r3 = r0.m527b(r3);
        r0 = r19;
        r1 = r18;
        r1.f367i = r0;
        r4 = r3;
        r3 = r2;
    L_0x0026:
        r0 = r18;
        r2 = r0.f366h;
        if (r2 != 0) goto L_0x0033;
    L_0x002c:
        r18.m512h();
    L_0x002f:
        return;
    L_0x0030:
        r2 = 17;
        goto L_0x0014;
    L_0x0033:
        r0 = r18;
        r2 = r0.f383y;
        if (r2 == 0) goto L_0x003d;
    L_0x0039:
        r18.m512h();
        goto L_0x002f;
    L_0x003d:
        r2 = r18.getWindowToken();
        if (r2 == 0) goto L_0x002f;
    L_0x0043:
        r0 = r18;
        r2 = r0.f366h;
        r0 = r18;
        r2.m248a(r0);
        r0 = r18;
        r2 = r0.f384z;
        r5 = 0;
        r0 = r18;
        r6 = r0.f367i;
        r6 = r6 - r2;
        r11 = java.lang.Math.max(r5, r6);
        r0 = r18;
        r5 = r0.f366h;
        r12 = r5.m251b();
        r5 = r12 + -1;
        r0 = r18;
        r6 = r0.f367i;
        r2 = r2 + r6;
        r13 = java.lang.Math.min(r5, r2);
        r0 = r18;
        r2 = r0.f362b;
        if (r12 == r2) goto L_0x00da;
    L_0x0073:
        r2 = r18.getResources();	 Catch:{ NotFoundException -> 0x00d0 }
        r3 = r18.getId();	 Catch:{ NotFoundException -> 0x00d0 }
        r2 = r2.getResourceName(r3);	 Catch:{ NotFoundException -> 0x00d0 }
    L_0x007f:
        r3 = new java.lang.IllegalStateException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ";
        r4 = r4.append(r5);
        r0 = r18;
        r5 = r0.f362b;
        r4 = r4.append(r5);
        r5 = ", found: ";
        r4 = r4.append(r5);
        r4 = r4.append(r12);
        r5 = " Pager id: ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = " Pager class: ";
        r2 = r2.append(r4);
        r4 = r18.getClass();
        r2 = r2.append(r4);
        r4 = " Problematic adapter: ";
        r2 = r2.append(r4);
        r0 = r18;
        r4 = r0.f366h;
        r4 = r4.getClass();
        r2 = r2.append(r4);
        r2 = r2.toString();
        r3.<init>(r2);
        throw r3;
    L_0x00d0:
        r2 = move-exception;
        r2 = r18.getId();
        r2 = java.lang.Integer.toHexString(r2);
        goto L_0x007f;
    L_0x00da:
        r6 = 0;
        r2 = 0;
        r5 = r2;
    L_0x00dd:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.size();
        if (r5 >= r2) goto L_0x033c;
    L_0x00e7:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
        r7 = r2.f321b;
        r0 = r18;
        r8 = r0.f367i;
        if (r7 < r8) goto L_0x01cf;
    L_0x00f9:
        r7 = r2.f321b;
        r0 = r18;
        r8 = r0.f367i;
        if (r7 != r8) goto L_0x033c;
    L_0x0101:
        if (r2 != 0) goto L_0x0339;
    L_0x0103:
        if (r12 <= 0) goto L_0x0339;
    L_0x0105:
        r0 = r18;
        r2 = r0.f367i;
        r0 = r18;
        r2 = r0.m516a(r2, r5);
        r10 = r2;
    L_0x0110:
        if (r10 == 0) goto L_0x0180;
    L_0x0112:
        r9 = 0;
        r8 = r5 + -1;
        if (r8 < 0) goto L_0x01d4;
    L_0x0117:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r8);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
    L_0x0121:
        r14 = r18.getClientWidth();
        if (r14 > 0) goto L_0x01d7;
    L_0x0127:
        r6 = 0;
    L_0x0128:
        r0 = r18;
        r7 = r0.f367i;
        r7 = r7 + -1;
        r16 = r7;
        r7 = r9;
        r9 = r16;
        r17 = r8;
        r8 = r5;
        r5 = r17;
    L_0x0138:
        if (r9 < 0) goto L_0x0142;
    L_0x013a:
        r15 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1));
        if (r15 < 0) goto L_0x0216;
    L_0x013e:
        if (r9 >= r11) goto L_0x0216;
    L_0x0140:
        if (r2 != 0) goto L_0x01e6;
    L_0x0142:
        r6 = r10.f323d;
        r9 = r8 + 1;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x017b;
    L_0x014c:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.size();
        if (r9 >= r2) goto L_0x024c;
    L_0x0156:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r9);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
        r7 = r2;
    L_0x0161:
        if (r14 > 0) goto L_0x024f;
    L_0x0163:
        r2 = 0;
        r5 = r2;
    L_0x0165:
        r0 = r18;
        r2 = r0.f367i;
        r2 = r2 + 1;
        r16 = r2;
        r2 = r7;
        r7 = r9;
        r9 = r16;
    L_0x0171:
        if (r9 >= r12) goto L_0x017b;
    L_0x0173:
        r11 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1));
        if (r11 < 0) goto L_0x029a;
    L_0x0177:
        if (r9 <= r13) goto L_0x029a;
    L_0x0179:
        if (r2 != 0) goto L_0x025c;
    L_0x017b:
        r0 = r18;
        r0.m500a(r10, r8, r4);
    L_0x0180:
        r0 = r18;
        r4 = r0.f366h;
        r0 = r18;
        r5 = r0.f367i;
        if (r10 == 0) goto L_0x02e8;
    L_0x018a:
        r2 = r10.f320a;
    L_0x018c:
        r0 = r18;
        r4.m256b(r0, r5, r2);
        r0 = r18;
        r2 = r0.f366h;
        r0 = r18;
        r2.m255b(r0);
        r5 = r18.getChildCount();
        r2 = 0;
        r4 = r2;
    L_0x01a0:
        if (r4 >= r5) goto L_0x02eb;
    L_0x01a2:
        r0 = r18;
        r6 = r0.getChildAt(r4);
        r2 = r6.getLayoutParams();
        r2 = (android.support.v4.view.ViewPager.C0118c) r2;
        r2.f330f = r4;
        r7 = r2.f325a;
        if (r7 != 0) goto L_0x01cb;
    L_0x01b4:
        r7 = r2.f327c;
        r8 = 0;
        r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1));
        if (r7 != 0) goto L_0x01cb;
    L_0x01bb:
        r0 = r18;
        r6 = r0.m517a(r6);
        if (r6 == 0) goto L_0x01cb;
    L_0x01c3:
        r7 = r6.f323d;
        r2.f327c = r7;
        r6 = r6.f321b;
        r2.f329e = r6;
    L_0x01cb:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x01a0;
    L_0x01cf:
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x00dd;
    L_0x01d4:
        r2 = 0;
        goto L_0x0121;
    L_0x01d7:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r10.f323d;
        r6 = r6 - r7;
        r7 = r18.getPaddingLeft();
        r7 = (float) r7;
        r15 = (float) r14;
        r7 = r7 / r15;
        r6 = r6 + r7;
        goto L_0x0128;
    L_0x01e6:
        r15 = r2.f321b;
        if (r9 != r15) goto L_0x0210;
    L_0x01ea:
        r15 = r2.f322c;
        if (r15 != 0) goto L_0x0210;
    L_0x01ee:
        r0 = r18;
        r15 = r0.f363e;
        r15.remove(r5);
        r0 = r18;
        r15 = r0.f366h;
        r2 = r2.f320a;
        r0 = r18;
        r15.m249a(r0, r9, r2);
        r5 = r5 + -1;
        r8 = r8 + -1;
        if (r5 < 0) goto L_0x0214;
    L_0x0206:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
    L_0x0210:
        r9 = r9 + -1;
        goto L_0x0138;
    L_0x0214:
        r2 = 0;
        goto L_0x0210;
    L_0x0216:
        if (r2 == 0) goto L_0x0230;
    L_0x0218:
        r15 = r2.f321b;
        if (r9 != r15) goto L_0x0230;
    L_0x021c:
        r2 = r2.f323d;
        r7 = r7 + r2;
        r5 = r5 + -1;
        if (r5 < 0) goto L_0x022e;
    L_0x0223:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
        goto L_0x0210;
    L_0x022e:
        r2 = 0;
        goto L_0x0210;
    L_0x0230:
        r2 = r5 + 1;
        r0 = r18;
        r2 = r0.m516a(r9, r2);
        r2 = r2.f323d;
        r7 = r7 + r2;
        r8 = r8 + 1;
        if (r5 < 0) goto L_0x024a;
    L_0x023f:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
        goto L_0x0210;
    L_0x024a:
        r2 = 0;
        goto L_0x0210;
    L_0x024c:
        r7 = 0;
        goto L_0x0161;
    L_0x024f:
        r2 = r18.getPaddingRight();
        r2 = (float) r2;
        r5 = (float) r14;
        r2 = r2 / r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 + r5;
        r5 = r2;
        goto L_0x0165;
    L_0x025c:
        r11 = r2.f321b;
        if (r9 != r11) goto L_0x0332;
    L_0x0260:
        r11 = r2.f322c;
        if (r11 != 0) goto L_0x0332;
    L_0x0264:
        r0 = r18;
        r11 = r0.f363e;
        r11.remove(r7);
        r0 = r18;
        r11 = r0.f366h;
        r2 = r2.f320a;
        r0 = r18;
        r11.m249a(r0, r9, r2);
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x0298;
    L_0x0280:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
    L_0x028a:
        r16 = r6;
        r6 = r2;
        r2 = r16;
    L_0x028f:
        r9 = r9 + 1;
        r16 = r2;
        r2 = r6;
        r6 = r16;
        goto L_0x0171;
    L_0x0298:
        r2 = 0;
        goto L_0x028a;
    L_0x029a:
        if (r2 == 0) goto L_0x02c1;
    L_0x029c:
        r11 = r2.f321b;
        if (r9 != r11) goto L_0x02c1;
    L_0x02a0:
        r2 = r2.f323d;
        r6 = r6 + r2;
        r7 = r7 + 1;
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02bf;
    L_0x02af:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
    L_0x02b9:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02bf:
        r2 = 0;
        goto L_0x02b9;
    L_0x02c1:
        r0 = r18;
        r2 = r0.m516a(r9, r7);
        r7 = r7 + 1;
        r2 = r2.f323d;
        r6 = r6 + r2;
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02e6;
    L_0x02d6:
        r0 = r18;
        r2 = r0.f363e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.C0117b) r2;
    L_0x02e0:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02e6:
        r2 = 0;
        goto L_0x02e0;
    L_0x02e8:
        r2 = 0;
        goto L_0x018c;
    L_0x02eb:
        r18.m512h();
        r2 = r18.hasFocus();
        if (r2 == 0) goto L_0x002f;
    L_0x02f4:
        r2 = r18.findFocus();
        if (r2 == 0) goto L_0x0330;
    L_0x02fa:
        r0 = r18;
        r2 = r0.m528b(r2);
    L_0x0300:
        if (r2 == 0) goto L_0x030a;
    L_0x0302:
        r2 = r2.f321b;
        r0 = r18;
        r4 = r0.f367i;
        if (r2 == r4) goto L_0x002f;
    L_0x030a:
        r2 = 0;
    L_0x030b:
        r4 = r18.getChildCount();
        if (r2 >= r4) goto L_0x002f;
    L_0x0311:
        r0 = r18;
        r4 = r0.getChildAt(r2);
        r0 = r18;
        r5 = r0.m517a(r4);
        if (r5 == 0) goto L_0x032d;
    L_0x031f:
        r5 = r5.f321b;
        r0 = r18;
        r6 = r0.f367i;
        if (r5 != r6) goto L_0x032d;
    L_0x0327:
        r4 = r4.requestFocus(r3);
        if (r4 != 0) goto L_0x002f;
    L_0x032d:
        r2 = r2 + 1;
        goto L_0x030b;
    L_0x0330:
        r2 = 0;
        goto L_0x0300;
    L_0x0332:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x0339:
        r10 = r2;
        goto L_0x0110;
    L_0x033c:
        r2 = r6;
        goto L_0x0101;
    L_0x033f:
        r4 = r3;
        r3 = r2;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.a(int):void");
    }

    protected void m520a(int i, float f, int i2) {
        int paddingLeft;
        int paddingRight;
        int i3;
        if (this.f360V > 0) {
            int scrollX = getScrollX();
            paddingLeft = getPaddingLeft();
            paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            i3 = 0;
            while (i3 < childCount) {
                int i4;
                View childAt = getChildAt(i3);
                C0118c c0118c = (C0118c) childAt.getLayoutParams();
                if (c0118c.f325a) {
                    int max;
                    switch (c0118c.f326b & 7) {
                        case C0627j.View_paddingStart /*1*/:
                            max = Math.max((width - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        case C0627j.Toolbar_subtitle /*3*/:
                            max = childAt.getWidth() + paddingLeft;
                            i4 = paddingLeft;
                            paddingLeft = paddingRight;
                            paddingRight = max;
                            max = i4;
                            break;
                        case C0627j.Toolbar_contentInsetEnd /*5*/:
                            max = (width - paddingRight) - childAt.getMeasuredWidth();
                            i4 = paddingRight + childAt.getMeasuredWidth();
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        default:
                            max = paddingLeft;
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                    }
                    max = (max + scrollX) - childAt.getLeft();
                    if (max != 0) {
                        childAt.offsetLeftAndRight(max);
                    }
                } else {
                    i4 = paddingRight;
                    paddingRight = paddingLeft;
                    paddingLeft = i4;
                }
                i3++;
                i4 = paddingLeft;
                paddingLeft = paddingRight;
                paddingRight = i4;
            }
        }
        if (this.f361W != null) {
            this.f361W.m491a(i, f, i2);
        }
        if (this.aa != null) {
            this.aa.m491a(i, f, i2);
        }
        if (this.ac != null) {
            paddingRight = getScrollX();
            i3 = getChildCount();
            for (paddingLeft = 0; paddingLeft < i3; paddingLeft++) {
                View childAt2 = getChildAt(paddingLeft);
                if (!((C0118c) childAt2.getLayoutParams()).f325a) {
                    this.ac.m493a(childAt2, ((float) (childAt2.getLeft() - paddingRight)) / ((float) getClientWidth()));
                }
            }
        }
        this.f359U = true;
    }

    void m521a(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            m503a(false);
            m530c();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i6 = clientWidth / 2;
        float a = (((float) i6) * m515a(Math.min(1.0f, (((float) Math.abs(i4)) * 1.0f) / ((float) clientWidth)))) + ((float) i6);
        int abs = Math.abs(i3);
        if (abs > 0) {
            clientWidth = Math.round(1000.0f * Math.abs(a / ((float) abs))) * 4;
        } else {
            clientWidth = (int) (((((float) Math.abs(i4)) / ((((float) clientWidth) * this.f366h.m258d(this.f367i)) + ((float) this.f373o))) + 1.0f) * 100.0f);
        }
        this.f371m.startScroll(scrollX, scrollY, i4, i5, Math.min(clientWidth, 600));
        C0234u.m1083b(this);
    }

    public void m522a(int i, boolean z) {
        this.f383y = false;
        m523a(i, z, false);
    }

    void m523a(int i, boolean z, boolean z2) {
        m524a(i, z, z2, 0);
    }

    void m524a(int i, boolean z, boolean z2, int i2) {
        boolean z3 = false;
        if (this.f366h == null || this.f366h.m251b() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.f367i != i || this.f363e.size() == 0) {
            if (i < 0) {
                i = 0;
            } else if (i >= this.f366h.m251b()) {
                i = this.f366h.m251b() - 1;
            }
            int i3 = this.f384z;
            if (i > this.f367i + i3 || i < this.f367i - i3) {
                for (int i4 = 0; i4 < this.f363e.size(); i4++) {
                    ((C0117b) this.f363e.get(i4)).f322c = true;
                }
            }
            if (this.f367i != i) {
                z3 = true;
            }
            if (this.f357S) {
                this.f367i = i;
                if (z3 && this.f361W != null) {
                    this.f361W.m490a(i);
                }
                if (z3 && this.aa != null) {
                    this.aa.m490a(i);
                }
                requestLayout();
                return;
            }
            m519a(i);
            m499a(i, z, i2, z3);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    public boolean m525a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case C0627j.Toolbar_navigationContentDescription /*21*/:
                return m531c(17);
            case C0627j.Theme_actionMenuTextColor /*22*/:
                return m531c(66);
            case C0627j.Theme_searchViewStyle /*61*/:
                return VERSION.SDK_INT >= 11 ? C0197g.m887a(keyEvent) ? m531c(2) : C0197g.m888a(keyEvent, 1) ? m531c(1) : false : false;
            default:
                return false;
        }
    }

    protected boolean m526a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom()) {
                    if (m526a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        return z && C0234u.m1082a(view, -i);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    C0117b a = m517a(childAt);
                    if (a != null && a.f321b == this.f367i) {
                        childAt.addFocusables(arrayList, i, i2);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                C0117b a = m517a(childAt);
                if (a != null && a.f321b == this.f367i) {
                    childAt.addTouchables(arrayList);
                }
            }
        }
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        LayoutParams generateLayoutParams = !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : layoutParams;
        C0118c c0118c = (C0118c) generateLayoutParams;
        c0118c.f325a |= view instanceof C0116a;
        if (!this.f381w) {
            super.addView(view, i, generateLayoutParams);
        } else if (c0118c == null || !c0118c.f325a) {
            c0118c.f328d = true;
            addViewInLayout(view, i, generateLayoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    C0117b m527b(int i) {
        for (int i2 = 0; i2 < this.f363e.size(); i2++) {
            C0117b c0117b = (C0117b) this.f363e.get(i2);
            if (c0117b.f321b == i) {
                return c0117b;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    C0117b m528b(View r3) {
        /*
        r2 = this;
    L_0x0000:
        r0 = r3.getParent();
        if (r0 == r2) goto L_0x0012;
    L_0x0006:
        if (r0 == 0) goto L_0x000c;
    L_0x0008:
        r1 = r0 instanceof android.view.View;
        if (r1 != 0) goto L_0x000e;
    L_0x000c:
        r0 = 0;
    L_0x000d:
        return r0;
    L_0x000e:
        r0 = (android.view.View) r0;
        r3 = r0;
        goto L_0x0000;
    L_0x0012:
        r0 = r2.m517a(r3);
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.b(android.view.View):android.support.v4.view.ViewPager$b");
    }

    void m529b() {
        int i;
        int b = this.f366h.m251b();
        this.f362b = b;
        boolean z = this.f363e.size() < (this.f384z * 2) + 1 && this.f363e.size() < b;
        boolean z2 = false;
        int i2 = this.f367i;
        boolean z3 = z;
        int i3 = 0;
        while (i3 < this.f363e.size()) {
            int i4;
            boolean z4;
            boolean z5;
            C0117b c0117b = (C0117b) this.f363e.get(i3);
            int a = this.f366h.m240a(c0117b.f320a);
            if (a == -1) {
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = z3;
            } else if (a == -2) {
                this.f363e.remove(i3);
                i3--;
                if (!z2) {
                    this.f366h.m248a((ViewGroup) this);
                    z2 = true;
                }
                this.f366h.m249a((ViewGroup) this, c0117b.f321b, c0117b.f320a);
                if (this.f367i == c0117b.f321b) {
                    i4 = i3;
                    z4 = z2;
                    i = Math.max(0, Math.min(this.f367i, b - 1));
                    z5 = true;
                } else {
                    i4 = i3;
                    z4 = z2;
                    i = i2;
                    z5 = true;
                }
            } else if (c0117b.f321b != a) {
                if (c0117b.f321b == this.f367i) {
                    i2 = a;
                }
                c0117b.f321b = a;
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = true;
            } else {
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = z3;
            }
            z3 = z5;
            i2 = i;
            z2 = z4;
            i3 = i4 + 1;
        }
        if (z2) {
            this.f366h.m255b((ViewGroup) this);
        }
        Collections.sort(this.f363e, f337c);
        if (z3) {
            i = getChildCount();
            for (i3 = 0; i3 < i; i3++) {
                C0118c c0118c = (C0118c) getChildAt(i3).getLayoutParams();
                if (!c0118c.f325a) {
                    c0118c.f327c = 0.0f;
                }
            }
            m523a(i2, false, true);
            requestLayout();
        }
    }

    void m530c() {
        m519a(this.f367i);
    }

    public boolean m531c(int i) {
        View view;
        boolean d;
        View findFocus = findFocus();
        if (findFocus == this) {
            view = null;
        } else {
            if (findFocus != null) {
                Object obj;
                for (ViewPager parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                    if (parent == this) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        stringBuilder.append(" => ").append(parent2.getClass().getSimpleName());
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + stringBuilder.toString());
                    view = null;
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (findNextFocus == null || findNextFocus == view) {
            if (i == 17 || i == 1) {
                d = m532d();
            } else {
                if (i == 66 || i == 2) {
                    d = m533e();
                }
                d = false;
            }
        } else if (i == 17) {
            d = (view == null || m496a(this.f365g, findNextFocus).left < m496a(this.f365g, view).left) ? findNextFocus.requestFocus() : m532d();
        } else {
            if (i == 66) {
                d = (view == null || m496a(this.f365g, findNextFocus).left > m496a(this.f365g, view).left) ? findNextFocus.requestFocus() : m533e();
            }
            d = false;
        }
        if (d) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return d;
    }

    public boolean canScrollHorizontally(int i) {
        boolean z = true;
        if (this.f366h == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX <= ((int) (((float) clientWidth) * this.f377s))) {
                z = false;
            }
            return z;
        } else if (i <= 0) {
            return false;
        } else {
            if (scrollX >= ((int) (((float) clientWidth) * this.f378t))) {
                z = false;
            }
            return z;
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof C0118c) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        if (this.f371m.isFinished() || !this.f371m.computeScrollOffset()) {
            m503a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f371m.getCurrX();
        int currY = this.f371m.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!m509d(currX)) {
                this.f371m.abortAnimation();
                scrollTo(0, currY);
            }
        }
        C0234u.m1083b(this);
    }

    boolean m532d() {
        if (this.f367i <= 0) {
            return false;
        }
        m522a(this.f367i - 1, true);
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m525a(keyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                C0117b a = m517a(childAt);
                if (a != null && a.f321b == this.f367i && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int i = 0;
        int a = C0234u.m1073a(this);
        if (a == 0 || (a == 1 && this.f366h != null && this.f366h.m251b() > 1)) {
            int height;
            int width;
            if (!this.f355Q.m1300a()) {
                a = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.f377s * ((float) width));
                this.f355Q.m1299a(height, width);
                i = 0 | this.f355Q.m1302a(canvas);
                canvas.restoreToCount(a);
            }
            if (!this.f356R.m1300a()) {
                a = canvas.save();
                height = getWidth();
                width = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.f378t + 1.0f)) * ((float) height));
                this.f356R.m1299a(width, height);
                i |= this.f356R.m1302a(canvas);
                canvas.restoreToCount(a);
            }
        } else {
            this.f355Q.m1303b();
            this.f356R.m1303b();
        }
        if (i != 0) {
            C0234u.m1083b(this);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f374p;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    boolean m533e() {
        if (this.f366h == null || this.f367i >= this.f366h.m251b() - 1) {
            return false;
        }
        m522a(this.f367i + 1, true);
        return true;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new C0118c();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0118c(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public C0044r getAdapter() {
        return this.f366h;
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.ae == 2) {
            i2 = (i - 1) - i2;
        }
        return ((C0118c) ((View) this.af.get(i2)).getLayoutParams()).f330f;
    }

    public int getCurrentItem() {
        return this.f367i;
    }

    public int getOffscreenPageLimit() {
        return this.f384z;
    }

    public int getPageMargin() {
        return this.f373o;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f357S = true;
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.ah);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f373o > 0 && this.f374p != null && this.f363e.size() > 0 && this.f366h != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f = ((float) this.f373o) / ((float) width);
            C0117b c0117b = (C0117b) this.f363e.get(0);
            float f2 = c0117b.f324e;
            int size = this.f363e.size();
            int i = c0117b.f321b;
            int i2 = ((C0117b) this.f363e.get(size - 1)).f321b;
            int i3 = 0;
            int i4 = i;
            while (i4 < i2) {
                float f3;
                while (i4 > c0117b.f321b && i3 < size) {
                    i3++;
                    c0117b = (C0117b) this.f363e.get(i3);
                }
                if (i4 == c0117b.f321b) {
                    f3 = (c0117b.f324e + c0117b.f323d) * ((float) width);
                    f2 = (c0117b.f324e + c0117b.f323d) + f;
                } else {
                    float d = this.f366h.m258d(i4);
                    f3 = (f2 + d) * ((float) width);
                    f2 += d + f;
                }
                if (((float) this.f373o) + f3 > ((float) scrollX)) {
                    this.f374p.setBounds((int) f3, this.f375q, (int) ((((float) this.f373o) + f3) + 0.5f), this.f376r);
                    this.f374p.draw(canvas);
                }
                if (f3 <= ((float) (scrollX + width))) {
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.f339A = false;
            this.f340B = false;
            this.f348J = -1;
            if (this.f349K == null) {
                return false;
            }
            this.f349K.recycle();
            this.f349K = null;
            return false;
        }
        if (action != 0) {
            if (this.f339A) {
                return true;
            }
            if (this.f340B) {
                return false;
            }
        }
        switch (action) {
            case C0627j.View_android_focusable /*0*/:
                float x = motionEvent.getX();
                this.f346H = x;
                this.f344F = x;
                x = motionEvent.getY();
                this.f347I = x;
                this.f345G = x;
                this.f348J = C0216o.m957b(motionEvent, 0);
                this.f340B = false;
                this.f371m.computeScrollOffset();
                if (this.ai == 2 && Math.abs(this.f371m.getFinalX() - this.f371m.getCurrX()) > this.f353O) {
                    this.f371m.abortAnimation();
                    this.f383y = false;
                    m530c();
                    this.f339A = true;
                    m508c(true);
                    setScrollState(1);
                    break;
                }
                m503a(false);
                this.f339A = false;
                break;
                break;
            case C0627j.View_paddingEnd /*2*/:
                action = this.f348J;
                if (action != -1) {
                    action = C0216o.m955a(motionEvent, action);
                    float c = C0216o.m958c(motionEvent, action);
                    float f = c - this.f344F;
                    float abs = Math.abs(f);
                    float d = C0216o.m960d(motionEvent, action);
                    float abs2 = Math.abs(d - this.f347I);
                    if (f == 0.0f || m504a(this.f344F, f) || !m526a(this, false, (int) f, (int) c, (int) d)) {
                        if (abs > ((float) this.f343E) && 0.5f * abs > abs2) {
                            this.f339A = true;
                            m508c(true);
                            setScrollState(1);
                            this.f344F = f > 0.0f ? this.f346H + ((float) this.f343E) : this.f346H - ((float) this.f343E);
                            this.f345G = d;
                            setScrollingCacheEnabled(true);
                        } else if (abs2 > ((float) this.f343E)) {
                            this.f340B = true;
                        }
                        if (this.f339A && m507b(c)) {
                            C0234u.m1083b(this);
                            break;
                        }
                    }
                    this.f344F = c;
                    this.f345G = d;
                    this.f340B = true;
                    return false;
                }
                break;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                m502a(motionEvent);
                break;
        }
        if (this.f349K == null) {
            this.f349K = VelocityTracker.obtain();
        }
        this.f349K.addMovement(motionEvent);
        return this.f339A;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        C0118c c0118c;
        int max;
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i7 = 0;
        int i8 = 0;
        while (i8 < childCount) {
            int measuredWidth;
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                c0118c = (C0118c) childAt.getLayoutParams();
                if (c0118c.f325a) {
                    int i9 = c0118c.f326b & 112;
                    switch (c0118c.f326b & 7) {
                        case C0627j.View_paddingStart /*1*/:
                            max = Math.max((i5 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case C0627j.Toolbar_subtitle /*3*/:
                            max = paddingLeft;
                            paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                            break;
                        case C0627j.Toolbar_contentInsetEnd /*5*/:
                            measuredWidth = (i5 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                            max = measuredWidth;
                            break;
                        default:
                            max = paddingLeft;
                            break;
                    }
                    int i10;
                    switch (i9) {
                        case C0627j.Toolbar_maxButtonHeight /*16*/:
                            measuredWidth = Math.max((i6 - childAt.getMeasuredHeight()) / 2, paddingTop);
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        case C0627j.Theme_dividerVertical /*48*/:
                            measuredWidth = childAt.getMeasuredHeight() + paddingTop;
                            i10 = paddingTop;
                            paddingTop = paddingBottom;
                            paddingBottom = measuredWidth;
                            measuredWidth = i10;
                            break;
                        case C0627j.Theme_colorControlHighlight /*80*/:
                            measuredWidth = (i6 - paddingBottom) - childAt.getMeasuredHeight();
                            i10 = paddingBottom + childAt.getMeasuredHeight();
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        default:
                            measuredWidth = paddingTop;
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                    }
                    max += scrollX;
                    childAt.layout(max, measuredWidth, childAt.getMeasuredWidth() + max, childAt.getMeasuredHeight() + measuredWidth);
                    measuredWidth = i7 + 1;
                    i7 = paddingBottom;
                    paddingBottom = paddingTop;
                    paddingTop = paddingRight;
                    paddingRight = paddingLeft;
                    i8++;
                    paddingLeft = paddingRight;
                    paddingRight = paddingTop;
                    paddingTop = i7;
                    i7 = measuredWidth;
                }
            }
            measuredWidth = i7;
            i7 = paddingTop;
            paddingTop = paddingRight;
            paddingRight = paddingLeft;
            i8++;
            paddingLeft = paddingRight;
            paddingRight = paddingTop;
            paddingTop = i7;
            i7 = measuredWidth;
        }
        max = (i5 - paddingLeft) - paddingRight;
        for (paddingRight = 0; paddingRight < childCount; paddingRight++) {
            View childAt2 = getChildAt(paddingRight);
            if (childAt2.getVisibility() != 8) {
                c0118c = (C0118c) childAt2.getLayoutParams();
                if (!c0118c.f325a) {
                    C0117b a = m517a(childAt2);
                    if (a != null) {
                        i5 = ((int) (a.f324e * ((float) max))) + paddingLeft;
                        if (c0118c.f328d) {
                            c0118c.f328d = false;
                            childAt2.measure(MeasureSpec.makeMeasureSpec((int) (c0118c.f327c * ((float) max)), 1073741824), MeasureSpec.makeMeasureSpec((i6 - paddingTop) - paddingBottom, 1073741824));
                        }
                        childAt2.layout(i5, paddingTop, childAt2.getMeasuredWidth() + i5, childAt2.getMeasuredHeight() + paddingTop);
                    }
                }
            }
        }
        this.f375q = paddingTop;
        this.f376r = i6 - paddingBottom;
        this.f360V = i7;
        if (this.f357S) {
            m499a(this.f367i, false, 0, false);
        }
        this.f357S = false;
    }

    protected void onMeasure(int i, int i2) {
        C0118c c0118c;
        int i3;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.f342D = Math.min(measuredWidth / 10, this.f341C);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            int i5;
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                c0118c = (C0118c) childAt.getLayoutParams();
                if (c0118c != null && c0118c.f325a) {
                    int i6 = c0118c.f326b & 7;
                    int i7 = c0118c.f326b & 112;
                    i3 = RtlSpacingHelper.UNDEFINED;
                    i5 = RtlSpacingHelper.UNDEFINED;
                    Object obj = (i7 == 48 || i7 == 80) ? 1 : null;
                    Object obj2 = (i6 == 3 || i6 == 5) ? 1 : null;
                    if (obj != null) {
                        i3 = 1073741824;
                    } else if (obj2 != null) {
                        i5 = 1073741824;
                    }
                    if (c0118c.width != -2) {
                        i7 = 1073741824;
                        i3 = c0118c.width != -1 ? c0118c.width : paddingLeft;
                    } else {
                        i7 = i3;
                        i3 = paddingLeft;
                    }
                    if (c0118c.height != -2) {
                        i5 = 1073741824;
                        if (c0118c.height != -1) {
                            measuredWidth = c0118c.height;
                            childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i5));
                            if (obj != null) {
                                measuredHeight -= childAt.getMeasuredHeight();
                            } else if (obj2 != null) {
                                paddingLeft -= childAt.getMeasuredWidth();
                            }
                        }
                    }
                    measuredWidth = measuredHeight;
                    childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i5));
                    if (obj != null) {
                        measuredHeight -= childAt.getMeasuredHeight();
                    } else if (obj2 != null) {
                        paddingLeft -= childAt.getMeasuredWidth();
                    }
                }
            }
        }
        this.f379u = MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.f380v = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.f381w = true;
        m530c();
        this.f381w = false;
        i3 = getChildCount();
        for (i5 = 0; i5 < i3; i5++) {
            View childAt2 = getChildAt(i5);
            if (childAt2.getVisibility() != 8) {
                c0118c = (C0118c) childAt2.getLayoutParams();
                if (c0118c == null || !c0118c.f325a) {
                    childAt2.measure(MeasureSpec.makeMeasureSpec((int) (c0118c.f327c * ((float) paddingLeft)), 1073741824), this.f380v);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                C0117b a = m517a(childAt);
                if (a != null && a.f321b == this.f367i && childAt.requestFocus(i, rect)) {
                    return true;
                }
            }
            i2 += i3;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.f366h != null) {
                this.f366h.m245a(savedState.f318b, savedState.f319c);
                m523a(savedState.f317a, false, true);
                return;
            }
            this.f368j = savedState.f317a;
            this.f369k = savedState.f318b;
            this.f370l = savedState.f319c;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f317a = this.f367i;
        if (this.f366h != null) {
            savedState.f318b = this.f366h.m241a();
        }
        return savedState;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            m498a(i, i3, this.f373o, this.f373o);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.f354P) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.f366h == null || this.f366h.m251b() == 0) {
            return false;
        }
        if (this.f349K == null) {
            this.f349K = VelocityTracker.obtain();
        }
        this.f349K.addMovement(motionEvent);
        float x;
        int a;
        switch (motionEvent.getAction() & 255) {
            case C0627j.View_android_focusable /*0*/:
                this.f371m.abortAnimation();
                this.f383y = false;
                m530c();
                x = motionEvent.getX();
                this.f346H = x;
                this.f344F = x;
                x = motionEvent.getY();
                this.f347I = x;
                this.f345G = x;
                this.f348J = C0216o.m957b(motionEvent, 0);
                break;
            case C0627j.View_paddingStart /*1*/:
                if (this.f339A) {
                    VelocityTracker velocityTracker = this.f349K;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.f351M);
                    a = (int) C0222s.m973a(velocityTracker, this.f348J);
                    this.f383y = true;
                    int clientWidth = getClientWidth();
                    int scrollX = getScrollX();
                    C0117b i = m513i();
                    m524a(m495a(i.f321b, ((((float) scrollX) / ((float) clientWidth)) - i.f324e) / i.f323d, a, (int) (C0216o.m958c(motionEvent, C0216o.m955a(motionEvent, this.f348J)) - this.f346H)), true, true, a);
                    this.f348J = -1;
                    m514j();
                    z = this.f356R.m1304c() | this.f355Q.m1304c();
                    break;
                }
                break;
            case C0627j.View_paddingEnd /*2*/:
                if (!this.f339A) {
                    a = C0216o.m955a(motionEvent, this.f348J);
                    float c = C0216o.m958c(motionEvent, a);
                    float abs = Math.abs(c - this.f344F);
                    float d = C0216o.m960d(motionEvent, a);
                    x = Math.abs(d - this.f345G);
                    if (abs > ((float) this.f343E) && abs > x) {
                        this.f339A = true;
                        m508c(true);
                        this.f344F = c - this.f346H > 0.0f ? this.f346H + ((float) this.f343E) : this.f346H - ((float) this.f343E);
                        this.f345G = d;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.f339A) {
                    z = false | m507b(C0216o.m958c(motionEvent, C0216o.m955a(motionEvent, this.f348J)));
                    break;
                }
                break;
            case C0627j.Toolbar_subtitle /*3*/:
                if (this.f339A) {
                    m499a(this.f367i, true, 0, false);
                    this.f348J = -1;
                    m514j();
                    z = this.f356R.m1304c() | this.f355Q.m1304c();
                    break;
                }
                break;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                a = C0216o.m956b(motionEvent);
                this.f344F = C0216o.m958c(motionEvent, a);
                this.f348J = C0216o.m957b(motionEvent, a);
                break;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                m502a(motionEvent);
                this.f344F = C0216o.m958c(motionEvent, C0216o.m955a(motionEvent, this.f348J));
                break;
        }
        if (z) {
            C0234u.m1083b(this);
        }
        return true;
    }

    public void removeView(View view) {
        if (this.f381w) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(C0044r c0044r) {
        if (this.f366h != null) {
            this.f366h.m252b(this.f372n);
            this.f366h.m248a((ViewGroup) this);
            for (int i = 0; i < this.f363e.size(); i++) {
                C0117b c0117b = (C0117b) this.f363e.get(i);
                this.f366h.m249a((ViewGroup) this, c0117b.f321b, c0117b.f320a);
            }
            this.f366h.m255b((ViewGroup) this);
            this.f363e.clear();
            m511g();
            this.f367i = 0;
            scrollTo(0, 0);
        }
        C0044r c0044r2 = this.f366h;
        this.f366h = c0044r;
        this.f362b = 0;
        if (this.f366h != null) {
            if (this.f372n == null) {
                this.f372n = new C0124h();
            }
            this.f366h.m244a(this.f372n);
            this.f383y = false;
            boolean z = this.f357S;
            this.f357S = true;
            this.f362b = this.f366h.m251b();
            if (this.f368j >= 0) {
                this.f366h.m245a(this.f369k, this.f370l);
                m523a(this.f368j, false, true);
                this.f368j = -1;
                this.f369k = null;
                this.f370l = null;
            } else if (z) {
                requestLayout();
            } else {
                m530c();
            }
        }
        if (this.ab != null && c0044r2 != c0044r) {
            this.ab.m489a(c0044r2, c0044r);
        }
    }

    void setChildrenDrawingOrderEnabledCompat(boolean z) {
        if (VERSION.SDK_INT >= 7) {
            if (this.ad == null) {
                try {
                    this.ad = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (Throwable e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.ad.invoke(this, new Object[]{Boolean.valueOf(z)});
            } catch (Throwable e2) {
                Log.e("ViewPager", "Error changing children drawing order", e2);
            }
        }
    }

    public void setCurrentItem(int i) {
        this.f383y = false;
        m523a(i, !this.f357S, false);
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to " + 1);
            i = 1;
        }
        if (i != this.f384z) {
            this.f384z = i;
            m530c();
        }
    }

    void setOnAdapterChangeListener(C0121e c0121e) {
        this.ab = c0121e;
    }

    public void setOnPageChangeListener(C0122f c0122f) {
        this.f361W = c0122f;
    }

    public void setPageMargin(int i) {
        int i2 = this.f373o;
        this.f373o = i;
        int width = getWidth();
        m498a(width, width, i, i2);
        requestLayout();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f374p = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f374p;
    }
}
