package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.p000a.C0000a;
import android.support.v4.view.C0119a;
import android.support.v4.view.C0191e;
import android.support.v4.view.C0197g;
import android.support.v4.view.C0234u;
import android.support.v4.view.p008a.C0141a;
import android.support.v4.widget.C0289m.C0248a;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.wakdev.nfctools.C0628m.C0627j;

public class DrawerLayout extends ViewGroup {
    static final C0243b f443a;
    private static final int[] f444b;
    private static final boolean f445c;
    private boolean f446A;
    private final C0242a f447d;
    private int f448e;
    private int f449f;
    private float f450g;
    private Paint f451h;
    private final C0289m f452i;
    private final C0289m f453j;
    private final C0249g f454k;
    private final C0249g f455l;
    private int f456m;
    private boolean f457n;
    private boolean f458o;
    private int f459p;
    private int f460q;
    private boolean f461r;
    private boolean f462s;
    private C0246e f463t;
    private float f464u;
    private float f465v;
    private Drawable f466w;
    private Drawable f467x;
    private Drawable f468y;
    private Object f469z;

    protected static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f432a;
        int f433b;
        int f434c;

        /* renamed from: android.support.v4.widget.DrawerLayout.SavedState.1 */
        static class C02411 implements Creator<SavedState> {
            C02411() {
            }

            public SavedState m1121a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m1122a(int i) {
                return new SavedState[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m1121a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m1122a(i);
            }
        }

        static {
            CREATOR = new C02411();
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f432a = 0;
            this.f433b = 0;
            this.f434c = 0;
            this.f432a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f432a = 0;
            this.f433b = 0;
            this.f434c = 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f432a);
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.a */
    final class C0242a extends C0119a {
        public void m1123a(View view, C0141a c0141a) {
            super.m478a(view, c0141a);
            if (!DrawerLayout.m1171m(view)) {
                c0141a.m670a(null);
            }
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.b */
    interface C0243b {
        int m1124a(Object obj);

        void m1125a(View view, Object obj, int i);

        void m1126a(MarginLayoutParams marginLayoutParams, Object obj, int i);
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.c */
    static class C0244c implements C0243b {
        C0244c() {
        }

        public int m1127a(Object obj) {
            return C0269d.m1275a(obj);
        }

        public void m1128a(View view, Object obj, int i) {
            C0269d.m1276a(view, obj, i);
        }

        public void m1129a(MarginLayoutParams marginLayoutParams, Object obj, int i) {
            C0269d.m1277a(marginLayoutParams, obj, i);
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.d */
    static class C0245d implements C0243b {
        C0245d() {
        }

        public int m1130a(Object obj) {
            return 0;
        }

        public void m1131a(View view, Object obj, int i) {
        }

        public void m1132a(MarginLayoutParams marginLayoutParams, Object obj, int i) {
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.e */
    public interface C0246e {
        void m1133a(int i);

        void m1134a(View view);

        void m1135a(View view, float f);

        void m1136b(View view);
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.f */
    public static class C0247f extends MarginLayoutParams {
        public int f435a;
        float f436b;
        boolean f437c;
        boolean f438d;

        public C0247f(int i, int i2) {
            super(i, i2);
            this.f435a = 0;
        }

        public C0247f(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f435a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f444b);
            this.f435a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public C0247f(C0247f c0247f) {
            super(c0247f);
            this.f435a = 0;
            this.f435a = c0247f.f435a;
        }

        public C0247f(LayoutParams layoutParams) {
            super(layoutParams);
            this.f435a = 0;
        }

        public C0247f(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f435a = 0;
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.g */
    private class C0249g extends C0248a {
        final /* synthetic */ DrawerLayout f439a;
        private final int f440b;
        private C0289m f441c;
        private final Runnable f442d;

        private void m1150b() {
            int i = 3;
            if (this.f440b == 3) {
                i = 5;
            }
            View a = this.f439a.m1174a(i);
            if (a != null) {
                this.f439a.m1189i(a);
            }
        }

        public int m1151a(View view) {
            return this.f439a.m1187g(view) ? view.getWidth() : 0;
        }

        public int m1152a(View view, int i, int i2) {
            if (this.f439a.m1179a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = this.f439a.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        public void m1153a() {
            this.f439a.removeCallbacks(this.f442d);
        }

        public void m1154a(int i) {
            this.f439a.m1176a(this.f440b, i, this.f441c.m1399c());
        }

        public void m1155a(int i, int i2) {
            this.f439a.postDelayed(this.f442d, 160);
        }

        public void m1156a(View view, float f, float f2) {
            int i;
            float d = this.f439a.m1184d(view);
            int width = view.getWidth();
            if (this.f439a.m1179a(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && d > 0.5f)) ? 0 : -width;
            } else {
                i = this.f439a.getWidth();
                if (f < 0.0f || (f == 0.0f && d > 0.5f)) {
                    i -= width;
                }
            }
            this.f441c.m1389a(i, view.getTop());
            this.f439a.invalidate();
        }

        public void m1157a(View view, int i, int i2, int i3, int i4) {
            int width = view.getWidth();
            float width2 = this.f439a.m1179a(view, 3) ? ((float) (width + i)) / ((float) width) : ((float) (this.f439a.getWidth() - i)) / ((float) width);
            this.f439a.m1182b(view, width2);
            view.setVisibility(width2 == 0.0f ? 4 : 0);
            this.f439a.invalidate();
        }

        public boolean m1158a(View view, int i) {
            return this.f439a.m1187g(view) && this.f439a.m1179a(view, this.f440b) && this.f439a.m1172a(view) == 0;
        }

        public int m1159b(View view, int i, int i2) {
            return view.getTop();
        }

        public void m1160b(int i, int i2) {
            View a = (i & 1) == 1 ? this.f439a.m1174a(3) : this.f439a.m1174a(5);
            if (a != null && this.f439a.m1172a(a) == 0) {
                this.f441c.m1388a(a, i2);
            }
        }

        public void m1161b(View view, int i) {
            ((C0247f) view.getLayoutParams()).f437c = false;
            m1150b();
        }

        public boolean m1162b(int i) {
            return false;
        }
    }

    static {
        boolean z = true;
        f444b = new int[]{16842931};
        if (VERSION.SDK_INT < 19) {
            z = false;
        }
        f445c = z;
        if (VERSION.SDK_INT >= 21) {
            f443a = new C0244c();
        } else {
            f443a = new C0245d();
        }
    }

    private void m1163a(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || m1187g(childAt)) && !(z && childAt == view)) {
                C0234u.m1085b(childAt, 4);
            } else {
                C0234u.m1085b(childAt, 1);
            }
        }
    }

    static String m1164b(int i) {
        return (i & 3) == 3 ? "LEFT" : (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }

    private boolean m1166d() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((C0247f) getChildAt(i).getLayoutParams()).f437c) {
                return true;
            }
        }
        return false;
    }

    private boolean m1167e() {
        return m1168f() != null;
    }

    private View m1168f() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (m1187g(childAt) && m1190j(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    private static boolean m1170l(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    private static boolean m1171m(View view) {
        return (C0234u.m1086c(view) == 4 || C0234u.m1086c(view) == 2) ? false : true;
    }

    public int m1172a(View view) {
        int e = m1185e(view);
        return e == 3 ? this.f459p : e == 5 ? this.f460q : 0;
    }

    View m1173a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (((C0247f) childAt.getLayoutParams()).f438d) {
                return childAt;
            }
        }
        return null;
    }

    View m1174a(int i) {
        int a = C0191e.m873a(i, C0234u.m1090e(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((m1185e(childAt) & 7) == a) {
                return childAt;
            }
        }
        return null;
    }

    public void m1175a(int i, int i2) {
        int a = C0191e.m873a(i2, C0234u.m1090e(this));
        if (a == 3) {
            this.f459p = i;
        } else if (a == 5) {
            this.f460q = i;
        }
        if (i != 0) {
            (a == 3 ? this.f452i : this.f453j).m1405e();
        }
        View a2;
        switch (i) {
            case C0627j.View_paddingStart /*1*/:
                a2 = m1174a(a);
                if (a2 != null) {
                    m1189i(a2);
                }
            case C0627j.View_paddingEnd /*2*/:
                a2 = m1174a(a);
                if (a2 != null) {
                    m1188h(a2);
                }
            default:
        }
    }

    void m1176a(int i, int i2, View view) {
        int i3 = 1;
        int a = this.f452i.m1386a();
        int a2 = this.f453j.m1386a();
        if (!(a == 1 || a2 == 1)) {
            i3 = (a == 2 || a2 == 2) ? 2 : 0;
        }
        if (view != null && i2 == 0) {
            C0247f c0247f = (C0247f) view.getLayoutParams();
            if (c0247f.f436b == 0.0f) {
                m1181b(view);
            } else if (c0247f.f436b == 1.0f) {
                m1183c(view);
            }
        }
        if (i3 != this.f456m) {
            this.f456m = i3;
            if (this.f463t != null) {
                this.f463t.m1133a(i3);
            }
        }
    }

    void m1177a(View view, float f) {
        if (this.f463t != null) {
            this.f463t.m1135a(view, f);
        }
    }

    void m1178a(boolean z) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            C0247f c0247f = (C0247f) childAt.getLayoutParams();
            if (m1187g(childAt) && (!z || c0247f.f437c)) {
                i = m1179a(childAt, 3) ? i | this.f452i.m1391a(childAt, -childAt.getWidth(), childAt.getTop()) : i | this.f453j.m1391a(childAt, getWidth(), childAt.getTop());
                c0247f.f437c = false;
            }
        }
        this.f454k.m1153a();
        this.f455l.m1153a();
        if (i != 0) {
            invalidate();
        }
    }

    boolean m1179a(View view, int i) {
        return (m1185e(view) & i) == i;
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (m1173a() != null || m1187g(view)) {
            C0234u.m1085b(view, 4);
        } else {
            C0234u.m1085b(view, 1);
        }
        if (!f445c) {
            C0234u.m1078a(view, this.f447d);
        }
    }

    public void m1180b() {
        m1178a(false);
    }

    void m1181b(View view) {
        C0247f c0247f = (C0247f) view.getLayoutParams();
        if (c0247f.f438d) {
            c0247f.f438d = false;
            if (this.f463t != null) {
                this.f463t.m1136b(view);
            }
            m1163a(view, false);
            if (hasWindowFocus()) {
                View rootView = getRootView();
                if (rootView != null) {
                    rootView.sendAccessibilityEvent(32);
                }
            }
        }
    }

    void m1182b(View view, float f) {
        C0247f c0247f = (C0247f) view.getLayoutParams();
        if (f != c0247f.f436b) {
            c0247f.f436b = f;
            m1177a(view, f);
        }
    }

    void m1183c(View view) {
        C0247f c0247f = (C0247f) view.getLayoutParams();
        if (!c0247f.f438d) {
            c0247f.f438d = true;
            if (this.f463t != null) {
                this.f463t.m1134a(view);
            }
            m1163a(view, true);
            view.requestFocus();
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof C0247f) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((C0247f) getChildAt(i).getLayoutParams()).f436b);
        }
        this.f450g = f;
        if ((this.f452i.m1392a(true) | this.f453j.m1392a(true)) != 0) {
            C0234u.m1083b(this);
        }
    }

    float m1184d(View view) {
        return ((C0247f) view.getLayoutParams()).f436b;
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        int height = getHeight();
        boolean f = m1186f(view);
        int i2 = 0;
        int width = getWidth();
        int save = canvas.save();
        if (f) {
            int childCount = getChildCount();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                if (childAt != view && childAt.getVisibility() == 0 && m1170l(childAt) && m1187g(childAt)) {
                    if (childAt.getHeight() < height) {
                        i = width;
                    } else if (m1179a(childAt, 3)) {
                        i = childAt.getRight();
                        if (i <= i2) {
                            i = i2;
                        }
                        i2 = i;
                        i = width;
                    } else {
                        i = childAt.getLeft();
                        if (i < width) {
                        }
                    }
                    i3++;
                    width = i;
                }
                i = width;
                i3++;
                width = i;
            }
            canvas.clipRect(i2, 0, width, getHeight());
        }
        i = width;
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        if (this.f450g > 0.0f && f) {
            this.f451h.setColor((((int) (((float) ((this.f449f & -16777216) >>> 24)) * this.f450g)) << 24) | (this.f449f & 16777215));
            canvas.drawRect((float) i2, 0.0f, (float) i, (float) getHeight(), this.f451h);
        } else if (this.f466w != null && m1179a(view, 3)) {
            i = this.f466w.getIntrinsicWidth();
            i2 = view.getRight();
            r2 = Math.max(0.0f, Math.min(((float) i2) / ((float) this.f452i.m1393b()), 1.0f));
            this.f466w.setBounds(i2, view.getTop(), i + i2, view.getBottom());
            this.f466w.setAlpha((int) (255.0f * r2));
            this.f466w.draw(canvas);
        } else if (this.f467x != null && m1179a(view, 5)) {
            i = this.f467x.getIntrinsicWidth();
            i2 = view.getLeft();
            r2 = Math.max(0.0f, Math.min(((float) (getWidth() - i2)) / ((float) this.f453j.m1393b()), 1.0f));
            this.f467x.setBounds(i2 - i, view.getTop(), i2, view.getBottom());
            this.f467x.setAlpha((int) (255.0f * r2));
            this.f467x.draw(canvas);
        }
        return drawChild;
    }

    int m1185e(View view) {
        return C0191e.m873a(((C0247f) view.getLayoutParams()).f435a, C0234u.m1090e(this));
    }

    boolean m1186f(View view) {
        return ((C0247f) view.getLayoutParams()).f435a == 0;
    }

    boolean m1187g(View view) {
        return (C0191e.m873a(((C0247f) view.getLayoutParams()).f435a, C0234u.m1090e(view)) & 7) != 0;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new C0247f(-1, -1);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0247f(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof C0247f ? new C0247f((C0247f) layoutParams) : layoutParams instanceof MarginLayoutParams ? new C0247f((MarginLayoutParams) layoutParams) : new C0247f(layoutParams);
    }

    public void m1188h(View view) {
        if (m1187g(view)) {
            if (this.f458o) {
                C0247f c0247f = (C0247f) view.getLayoutParams();
                c0247f.f436b = 1.0f;
                c0247f.f438d = true;
                m1163a(view, true);
            } else if (m1179a(view, 3)) {
                this.f452i.m1391a(view, 0, view.getTop());
            } else {
                this.f453j.m1391a(view, getWidth() - view.getWidth(), view.getTop());
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void m1189i(View view) {
        if (m1187g(view)) {
            if (this.f458o) {
                C0247f c0247f = (C0247f) view.getLayoutParams();
                c0247f.f436b = 0.0f;
                c0247f.f438d = false;
            } else if (m1179a(view, 3)) {
                this.f452i.m1391a(view, -view.getWidth(), view.getTop());
            } else {
                this.f453j.m1391a(view, getWidth(), view.getTop());
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public boolean m1190j(View view) {
        if (m1187g(view)) {
            return ((C0247f) view.getLayoutParams()).f436b > 0.0f;
        } else {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f458o = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f458o = true;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f446A && this.f468y != null) {
            int a = f443a.m1124a(this.f469z);
            if (a > 0) {
                this.f468y.setBounds(0, 0, getWidth(), a);
                this.f468y.draw(canvas);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(MotionEvent r8) {
        /*
        r7 = this;
        r1 = 1;
        r2 = 0;
        r0 = android.support.v4.view.C0216o.m954a(r8);
        r3 = r7.f452i;
        r3 = r3.m1390a(r8);
        r4 = r7.f453j;
        r4 = r4.m1390a(r8);
        r3 = r3 | r4;
        switch(r0) {
            case 0: goto L_0x0027;
            case 1: goto L_0x0065;
            case 2: goto L_0x0050;
            case 3: goto L_0x0065;
            default: goto L_0x0016;
        };
    L_0x0016:
        r0 = r2;
    L_0x0017:
        if (r3 != 0) goto L_0x0025;
    L_0x0019:
        if (r0 != 0) goto L_0x0025;
    L_0x001b:
        r0 = r7.m1166d();
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r0 = r7.f462s;
        if (r0 == 0) goto L_0x0026;
    L_0x0025:
        r2 = r1;
    L_0x0026:
        return r2;
    L_0x0027:
        r0 = r8.getX();
        r4 = r8.getY();
        r7.f464u = r0;
        r7.f465v = r4;
        r5 = r7.f450g;
        r6 = 0;
        r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x006d;
    L_0x003a:
        r5 = r7.f452i;
        r0 = (int) r0;
        r4 = (int) r4;
        r0 = r5.m1403d(r0, r4);
        if (r0 == 0) goto L_0x006d;
    L_0x0044:
        r0 = r7.m1186f(r0);
        if (r0 == 0) goto L_0x006d;
    L_0x004a:
        r0 = r1;
    L_0x004b:
        r7.f461r = r2;
        r7.f462s = r2;
        goto L_0x0017;
    L_0x0050:
        r0 = r7.f452i;
        r4 = 3;
        r0 = r0.m1404d(r4);
        if (r0 == 0) goto L_0x0016;
    L_0x0059:
        r0 = r7.f454k;
        r0.m1153a();
        r0 = r7.f455l;
        r0.m1153a();
        r0 = r2;
        goto L_0x0017;
    L_0x0065:
        r7.m1178a(r1);
        r7.f461r = r2;
        r7.f462s = r2;
        goto L_0x0016;
    L_0x006d:
        r0 = r2;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !m1167e()) {
            return super.onKeyDown(i, keyEvent);
        }
        C0197g.m889b(keyEvent);
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View f = m1168f();
        if (f != null && m1172a(f) == 0) {
            m1180b();
        }
        return f != null;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f457n = true;
        int i5 = i3 - i;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                C0247f c0247f = (C0247f) childAt.getLayoutParams();
                if (m1186f(childAt)) {
                    childAt.layout(c0247f.leftMargin, c0247f.topMargin, c0247f.leftMargin + childAt.getMeasuredWidth(), c0247f.topMargin + childAt.getMeasuredHeight());
                } else {
                    int i7;
                    float f;
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (m1179a(childAt, 3)) {
                        i7 = ((int) (((float) measuredWidth) * c0247f.f436b)) + (-measuredWidth);
                        f = ((float) (measuredWidth + i7)) / ((float) measuredWidth);
                    } else {
                        i7 = i5 - ((int) (((float) measuredWidth) * c0247f.f436b));
                        f = ((float) (i5 - i7)) / ((float) measuredWidth);
                    }
                    Object obj = f != c0247f.f436b ? 1 : null;
                    int i8;
                    switch (c0247f.f435a & 112) {
                        case C0627j.Toolbar_maxButtonHeight /*16*/:
                            int i9 = i4 - i2;
                            i8 = (i9 - measuredHeight) / 2;
                            if (i8 < c0247f.topMargin) {
                                i8 = c0247f.topMargin;
                            } else if (i8 + measuredHeight > i9 - c0247f.bottomMargin) {
                                i8 = (i9 - c0247f.bottomMargin) - measuredHeight;
                            }
                            childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
                            break;
                        case C0627j.Theme_colorControlHighlight /*80*/:
                            i8 = i4 - i2;
                            childAt.layout(i7, (i8 - c0247f.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i7, i8 - c0247f.bottomMargin);
                            break;
                        default:
                            childAt.layout(i7, c0247f.topMargin, measuredWidth + i7, measuredHeight + c0247f.topMargin);
                            break;
                    }
                    if (obj != null) {
                        m1182b(childAt, f);
                    }
                    int i10 = c0247f.f436b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i10) {
                        childAt.setVisibility(i10);
                    }
                }
            }
        }
        this.f457n = false;
        this.f458o = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r14, int r15) {
        /*
        r13 = this;
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r4 = 0;
        r12 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = android.view.View.MeasureSpec.getMode(r14);
        r5 = android.view.View.MeasureSpec.getMode(r15);
        r2 = android.view.View.MeasureSpec.getSize(r14);
        r0 = android.view.View.MeasureSpec.getSize(r15);
        if (r3 != r12) goto L_0x001b;
    L_0x0019:
        if (r5 == r12) goto L_0x0056;
    L_0x001b:
        r6 = r13.isInEditMode();
        if (r6 == 0) goto L_0x0058;
    L_0x0021:
        if (r3 != r7) goto L_0x0050;
    L_0x0023:
        if (r5 != r7) goto L_0x0054;
    L_0x0025:
        r1 = r0;
    L_0x0026:
        r13.setMeasuredDimension(r2, r1);
        r0 = r13.f469z;
        if (r0 == 0) goto L_0x0060;
    L_0x002d:
        r0 = android.support.v4.view.C0234u.m1100n(r13);
        if (r0 == 0) goto L_0x0060;
    L_0x0033:
        r0 = 1;
        r3 = r0;
    L_0x0035:
        r6 = android.support.v4.view.C0234u.m1090e(r13);
        r7 = r13.getChildCount();
        r5 = r4;
    L_0x003e:
        if (r5 >= r7) goto L_0x0138;
    L_0x0040:
        r8 = r13.getChildAt(r5);
        r0 = r8.getVisibility();
        r9 = 8;
        if (r0 != r9) goto L_0x0062;
    L_0x004c:
        r0 = r5 + 1;
        r5 = r0;
        goto L_0x003e;
    L_0x0050:
        if (r3 != 0) goto L_0x0023;
    L_0x0052:
        r2 = r1;
        goto L_0x0023;
    L_0x0054:
        if (r5 == 0) goto L_0x0026;
    L_0x0056:
        r1 = r0;
        goto L_0x0026;
    L_0x0058:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "DrawerLayout must be measured with MeasureSpec.EXACTLY.";
        r0.<init>(r1);
        throw r0;
    L_0x0060:
        r3 = r4;
        goto L_0x0035;
    L_0x0062:
        r0 = r8.getLayoutParams();
        r0 = (android.support.v4.widget.DrawerLayout.C0247f) r0;
        if (r3 == 0) goto L_0x007d;
    L_0x006a:
        r9 = r0.f435a;
        r9 = android.support.v4.view.C0191e.m873a(r9, r6);
        r10 = android.support.v4.view.C0234u.m1100n(r8);
        if (r10 == 0) goto L_0x009e;
    L_0x0076:
        r10 = f443a;
        r11 = r13.f469z;
        r10.m1125a(r8, r11, r9);
    L_0x007d:
        r9 = r13.m1186f(r8);
        if (r9 == 0) goto L_0x00a6;
    L_0x0083:
        r9 = r0.leftMargin;
        r9 = r2 - r9;
        r10 = r0.rightMargin;
        r9 = r9 - r10;
        r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r12);
        r10 = r0.topMargin;
        r10 = r1 - r10;
        r0 = r0.bottomMargin;
        r0 = r10 - r0;
        r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r12);
        r8.measure(r9, r0);
        goto L_0x004c;
    L_0x009e:
        r10 = f443a;
        r11 = r13.f469z;
        r10.m1126a(r0, r11, r9);
        goto L_0x007d;
    L_0x00a6:
        r9 = r13.m1187g(r8);
        if (r9 == 0) goto L_0x0109;
    L_0x00ac:
        r9 = r13.m1185e(r8);
        r9 = r9 & 7;
        r10 = r4 & r9;
        if (r10 == 0) goto L_0x00eb;
    L_0x00b6:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Child drawer has absolute gravity ";
        r1 = r1.append(r2);
        r2 = m1164b(r9);
        r1 = r1.append(r2);
        r2 = " but this ";
        r1 = r1.append(r2);
        r2 = "DrawerLayout";
        r1 = r1.append(r2);
        r2 = " already has a ";
        r1 = r1.append(r2);
        r2 = "drawer view along that edge";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00eb:
        r9 = r13.f448e;
        r10 = r0.leftMargin;
        r9 = r9 + r10;
        r10 = r0.rightMargin;
        r9 = r9 + r10;
        r10 = r0.width;
        r9 = getChildMeasureSpec(r14, r9, r10);
        r10 = r0.topMargin;
        r11 = r0.bottomMargin;
        r10 = r10 + r11;
        r0 = r0.height;
        r0 = getChildMeasureSpec(r15, r10, r0);
        r8.measure(r9, r0);
        goto L_0x004c;
    L_0x0109:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Child ";
        r1 = r1.append(r2);
        r1 = r1.append(r8);
        r2 = " at index ";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r2 = " does not have a valid layout_gravity - must be Gravity.LEFT, ";
        r1 = r1.append(r2);
        r2 = "Gravity.RIGHT or Gravity.NO_GRAVITY";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0138:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.DrawerLayout.onMeasure(int, int):void");
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f432a != 0) {
            View a = m1174a(savedState.f432a);
            if (a != null) {
                m1188h(a);
            }
        }
        m1175a(savedState.f433b, 3);
        m1175a(savedState.f434c, 5);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        View a = m1173a();
        if (a != null) {
            savedState.f432a = ((C0247f) a.getLayoutParams()).f435a;
        }
        savedState.f433b = this.f459p;
        savedState.f434c = this.f460q;
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f452i.m1394b(motionEvent);
        this.f453j.m1394b(motionEvent);
        float x;
        float y;
        switch (motionEvent.getAction() & 255) {
            case C0627j.View_android_focusable /*0*/:
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.f464u = x;
                this.f465v = y;
                this.f461r = false;
                this.f462s = false;
                break;
            case C0627j.View_paddingStart /*1*/:
                boolean z;
                x = motionEvent.getX();
                y = motionEvent.getY();
                View d = this.f452i.m1403d((int) x, (int) y);
                if (d != null && m1186f(d)) {
                    x -= this.f464u;
                    y -= this.f465v;
                    int d2 = this.f452i.m1402d();
                    if ((x * x) + (y * y) < ((float) (d2 * d2))) {
                        View a = m1173a();
                        if (a != null) {
                            z = m1172a(a) == 2;
                            m1178a(z);
                            this.f461r = false;
                            break;
                        }
                    }
                }
                z = true;
                m1178a(z);
                this.f461r = false;
            case C0627j.Toolbar_subtitle /*3*/:
                m1178a(true);
                this.f461r = false;
                this.f462s = false;
                break;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.f461r = z;
        if (z) {
            m1178a(true);
        }
    }

    public void requestLayout() {
        if (!this.f457n) {
            super.requestLayout();
        }
    }

    public void setDrawerListener(C0246e c0246e) {
        this.f463t = c0246e;
    }

    public void setDrawerLockMode(int i) {
        m1175a(i, 3);
        m1175a(i, 5);
    }

    public void setScrimColor(int i) {
        this.f449f = i;
        invalidate();
    }

    public void setStatusBarBackground(int i) {
        this.f468y = i != 0 ? C0000a.m0a(getContext(), i) : null;
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.f468y = drawable;
    }

    public void setStatusBarBackgroundColor(int i) {
        this.f468y = new ColorDrawable(i);
    }
}
