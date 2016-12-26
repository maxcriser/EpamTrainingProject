package com.wakdev.wdsortablelist;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.wakdev.nfctools.C0628m.C0627j;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DragSortListView extends ListView {
    private View[] f2510A;
    private C0656d f2511B;
    private float f2512C;
    private float f2513D;
    private int f2514E;
    private int f2515F;
    private float f2516G;
    private float f2517H;
    private float f2518I;
    private float f2519J;
    private float f2520K;
    private C0650c f2521L;
    private int f2522M;
    private int f2523N;
    private int f2524O;
    private int f2525P;
    private int f2526Q;
    private int f2527R;
    private boolean f2528S;
    private boolean f2529T;
    private C0662i f2530U;
    private MotionEvent f2531V;
    private int f2532W;
    private View f2533a;
    private float aa;
    private float ab;
    private C0654a ac;
    private boolean ad;
    private C0659f ae;
    private boolean af;
    private boolean ag;
    private C0663j ah;
    private C0665l ai;
    private C0664k aj;
    private C0661g ak;
    private boolean al;
    private float am;
    private boolean an;
    private boolean ao;
    private Point f2534b;
    private Point f2535c;
    private int f2536d;
    private boolean f2537e;
    private DataSetObserver f2538f;
    private float f2539g;
    private float f2540h;
    private int f2541i;
    private int f2542j;
    private int f2543k;
    private boolean f2544l;
    private int f2545m;
    private int f2546n;
    private int f2547o;
    private int f2548p;
    private int f2549q;
    private C0655b f2550r;
    private C0631h f2551s;
    private C0657m f2552t;
    private boolean f2553u;
    private int f2554v;
    private int f2555w;
    private int f2556x;
    private int f2557y;
    private int f2558z;

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.h */
    public interface C0631h {
        void a_(int i, int i2);
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.c */
    public interface C0650c {
        float m3092a(float f, long j);
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.1 */
    class C06511 implements C0650c {
        final /* synthetic */ DragSortListView f2458a;

        C06511(DragSortListView dragSortListView) {
            this.f2458a = dragSortListView;
        }

        public float m3093a(float f, long j) {
            return this.f2458a.f2520K * f;
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.2 */
    class C06522 extends DataSetObserver {
        final /* synthetic */ DragSortListView f2459a;

        C06522(DragSortListView dragSortListView) {
            this.f2459a = dragSortListView;
        }

        private void m3094a() {
            if (this.f2459a.f2554v == 4) {
                this.f2459a.m3188a();
            }
        }

        public void onChanged() {
            m3094a();
        }

        public void onInvalidated() {
            m3094a();
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.a */
    private class C0654a extends BaseAdapter {
        final /* synthetic */ DragSortListView f2462a;
        private ListAdapter f2463b;

        /* renamed from: com.wakdev.wdsortablelist.DragSortListView.a.1 */
        class C06531 extends DataSetObserver {
            final /* synthetic */ DragSortListView f2460a;
            final /* synthetic */ C0654a f2461b;

            C06531(C0654a c0654a, DragSortListView dragSortListView) {
                this.f2461b = c0654a;
                this.f2460a = dragSortListView;
            }

            public void onChanged() {
                this.f2461b.notifyDataSetChanged();
            }

            public void onInvalidated() {
                this.f2461b.notifyDataSetInvalidated();
            }
        }

        public C0654a(DragSortListView dragSortListView, ListAdapter listAdapter) {
            this.f2462a = dragSortListView;
            this.f2463b = listAdapter;
            this.f2463b.registerDataSetObserver(new C06531(this, dragSortListView));
        }

        public ListAdapter m3095a() {
            return this.f2463b;
        }

        public boolean areAllItemsEnabled() {
            return this.f2463b.areAllItemsEnabled();
        }

        public int getCount() {
            return this.f2463b.getCount();
        }

        public Object getItem(int i) {
            return this.f2463b.getItem(i);
        }

        public long getItemId(int i) {
            return this.f2463b.getItemId(i);
        }

        public int getItemViewType(int i) {
            return this.f2463b.getItemViewType(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View childAt;
            View view2;
            if (view != null) {
                view = (C0669b) view;
                childAt = view.getChildAt(0);
                view2 = this.f2463b.getView(i, childAt, this.f2462a);
                if (view2 != childAt) {
                    if (childAt != null) {
                        view.removeViewAt(0);
                    }
                    view.addView(view2);
                }
            } else {
                view2 = this.f2463b.getView(i, null, this.f2462a);
                childAt = view2 instanceof Checkable ? new C0670c(this.f2462a.getContext()) : new C0669b(this.f2462a.getContext());
                childAt.setLayoutParams(new LayoutParams(-1, -2));
                childAt.addView(view2);
                view = childAt;
            }
            this.f2462a.m3134a(this.f2462a.getHeaderViewsCount() + i, view, true);
            return view;
        }

        public int getViewTypeCount() {
            return this.f2463b.getViewTypeCount();
        }

        public boolean hasStableIds() {
            return this.f2463b.hasStableIds();
        }

        public boolean isEmpty() {
            return this.f2463b.isEmpty();
        }

        public boolean isEnabled(int i) {
            return this.f2463b.isEnabled(i);
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.b */
    public interface C0655b {
        void m3096a(int i, int i2);
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.d */
    private class C0656d implements Runnable {
        final /* synthetic */ DragSortListView f2464a;
        private boolean f2465b;
        private long f2466c;
        private long f2467d;
        private int f2468e;
        private float f2469f;
        private long f2470g;
        private int f2471h;
        private float f2472i;
        private boolean f2473j;

        public C0656d(DragSortListView dragSortListView) {
            this.f2464a = dragSortListView;
            this.f2473j = false;
        }

        public void m3097a(int i) {
            if (!this.f2473j) {
                this.f2465b = false;
                this.f2473j = true;
                this.f2470g = SystemClock.uptimeMillis();
                this.f2466c = this.f2470g;
                this.f2471h = i;
                this.f2464a.post(this);
            }
        }

        public void m3098a(boolean z) {
            if (z) {
                this.f2464a.removeCallbacks(this);
                this.f2473j = false;
                return;
            }
            this.f2465b = true;
        }

        public boolean m3099a() {
            return this.f2473j;
        }

        public int m3100b() {
            return this.f2473j ? this.f2471h : -1;
        }

        public void run() {
            if (this.f2465b) {
                this.f2473j = false;
                return;
            }
            View childAt;
            int firstVisiblePosition = this.f2464a.getFirstVisiblePosition();
            int lastVisiblePosition = this.f2464a.getLastVisiblePosition();
            int count = this.f2464a.getCount();
            int paddingTop = this.f2464a.getPaddingTop();
            int height = (this.f2464a.getHeight() - paddingTop) - this.f2464a.getPaddingBottom();
            int min = Math.min(this.f2464a.f2523N, this.f2464a.f2536d + this.f2464a.f2557y);
            int max = Math.max(this.f2464a.f2523N, this.f2464a.f2536d - this.f2464a.f2557y);
            if (this.f2471h == 0) {
                childAt = this.f2464a.getChildAt(0);
                if (childAt == null) {
                    this.f2473j = false;
                    return;
                } else if (firstVisiblePosition == 0 && childAt.getTop() == paddingTop) {
                    this.f2473j = false;
                    return;
                } else {
                    this.f2472i = this.f2464a.f2521L.m3092a((this.f2464a.f2517H - ((float) max)) / this.f2464a.f2518I, this.f2466c);
                }
            } else {
                View childAt2 = this.f2464a.getChildAt(lastVisiblePosition - firstVisiblePosition);
                if (childAt2 == null) {
                    this.f2473j = false;
                    return;
                } else if (lastVisiblePosition != count - 1 || childAt2.getBottom() > height + paddingTop) {
                    this.f2472i = -this.f2464a.f2521L.m3092a((((float) min) - this.f2464a.f2516G) / this.f2464a.f2519J, this.f2466c);
                } else {
                    this.f2473j = false;
                    return;
                }
            }
            this.f2467d = SystemClock.uptimeMillis();
            this.f2469f = (float) (this.f2467d - this.f2466c);
            this.f2468e = Math.round(this.f2472i * this.f2469f);
            if (this.f2468e >= 0) {
                this.f2468e = Math.min(height, this.f2468e);
                lastVisiblePosition = firstVisiblePosition;
            } else {
                this.f2468e = Math.max(-height, this.f2468e);
            }
            childAt = this.f2464a.getChildAt(lastVisiblePosition - firstVisiblePosition);
            firstVisiblePosition = childAt.getTop() + this.f2468e;
            if (lastVisiblePosition == 0 && firstVisiblePosition > paddingTop) {
                firstVisiblePosition = paddingTop;
            }
            this.f2464a.af = true;
            this.f2464a.setSelectionFromTop(lastVisiblePosition, firstVisiblePosition - paddingTop);
            this.f2464a.layoutChildren();
            this.f2464a.invalidate();
            this.f2464a.af = false;
            this.f2464a.m3156d(lastVisiblePosition, childAt, false);
            this.f2466c = this.f2467d;
            this.f2464a.post(this);
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.m */
    public interface C0657m {
        void m3101a(int i);
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.e */
    public interface C0658e extends C0655b, C0631h, C0657m {
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.f */
    private class C0659f {
        StringBuilder f2474a;
        File f2475b;
        final /* synthetic */ DragSortListView f2476c;
        private int f2477d;
        private int f2478e;
        private boolean f2479f;

        public C0659f(DragSortListView dragSortListView) {
            this.f2476c = dragSortListView;
            this.f2474a = new StringBuilder();
            this.f2477d = 0;
            this.f2478e = 0;
            this.f2479f = false;
            this.f2475b = new File(Environment.getExternalStorageDirectory(), "dslv_state.txt");
            if (!this.f2475b.exists()) {
                try {
                    this.f2475b.createNewFile();
                    Log.d("mobeta", "file created");
                } catch (IOException e) {
                    Log.w("mobeta", "Could not create dslv_state.txt");
                    Log.d("mobeta", e.getMessage());
                }
            }
        }

        public void m3102a() {
            this.f2474a.append("<DSLVStates>\n");
            this.f2478e = 0;
            this.f2479f = true;
        }

        public void m3103b() {
            if (this.f2479f) {
                int i;
                this.f2474a.append("<DSLVState>\n");
                int childCount = this.f2476c.getChildCount();
                int firstVisiblePosition = this.f2476c.getFirstVisiblePosition();
                this.f2474a.append("    <Positions>");
                for (i = 0; i < childCount; i++) {
                    this.f2474a.append(firstVisiblePosition + i).append(",");
                }
                this.f2474a.append("</Positions>\n");
                this.f2474a.append("    <Tops>");
                for (i = 0; i < childCount; i++) {
                    this.f2474a.append(this.f2476c.getChildAt(i).getTop()).append(",");
                }
                this.f2474a.append("</Tops>\n");
                this.f2474a.append("    <Bottoms>");
                for (i = 0; i < childCount; i++) {
                    this.f2474a.append(this.f2476c.getChildAt(i).getBottom()).append(",");
                }
                this.f2474a.append("</Bottoms>\n");
                this.f2474a.append("    <FirstExpPos>").append(this.f2476c.f2542j).append("</FirstExpPos>\n");
                this.f2474a.append("    <FirstExpBlankHeight>").append(this.f2476c.m3138b(this.f2476c.f2542j) - this.f2476c.m3153d(this.f2476c.f2542j)).append("</FirstExpBlankHeight>\n");
                this.f2474a.append("    <SecondExpPos>").append(this.f2476c.f2543k).append("</SecondExpPos>\n");
                this.f2474a.append("    <SecondExpBlankHeight>").append(this.f2476c.m3138b(this.f2476c.f2543k) - this.f2476c.m3153d(this.f2476c.f2543k)).append("</SecondExpBlankHeight>\n");
                this.f2474a.append("    <SrcPos>").append(this.f2476c.f2545m).append("</SrcPos>\n");
                this.f2474a.append("    <SrcHeight>").append(this.f2476c.f2556x + this.f2476c.getDividerHeight()).append("</SrcHeight>\n");
                this.f2474a.append("    <ViewHeight>").append(this.f2476c.getHeight()).append("</ViewHeight>\n");
                this.f2474a.append("    <LastY>").append(this.f2476c.f2525P).append("</LastY>\n");
                this.f2474a.append("    <FloatY>").append(this.f2476c.f2536d).append("</FloatY>\n");
                this.f2474a.append("    <ShuffleEdges>");
                for (i = 0; i < childCount; i++) {
                    this.f2474a.append(this.f2476c.m3129a(firstVisiblePosition + i, this.f2476c.getChildAt(i).getTop())).append(",");
                }
                this.f2474a.append("</ShuffleEdges>\n");
                this.f2474a.append("</DSLVState>\n");
                this.f2477d++;
                if (this.f2477d > 1000) {
                    m3104c();
                    this.f2477d = 0;
                }
            }
        }

        public void m3104c() {
            boolean z = false;
            if (this.f2479f) {
                try {
                    if (this.f2478e != 0) {
                        z = true;
                    }
                    FileWriter fileWriter = new FileWriter(this.f2475b, z);
                    fileWriter.write(this.f2474a.toString());
                    this.f2474a.delete(0, this.f2474a.length());
                    fileWriter.flush();
                    fileWriter.close();
                    this.f2478e++;
                } catch (IOException e) {
                }
            }
        }

        public void m3105d() {
            if (this.f2479f) {
                this.f2474a.append("</DSLVStates>\n");
                m3104c();
                this.f2479f = false;
            }
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.n */
    private class C0660n implements Runnable {
        private float f2480a;
        protected long f2481b;
        final /* synthetic */ DragSortListView f2482c;
        private float f2483d;
        private float f2484e;
        private float f2485f;
        private float f2486g;
        private float f2487h;
        private boolean f2488i;

        public C0660n(DragSortListView dragSortListView, float f, int i) {
            this.f2482c = dragSortListView;
            this.f2483d = f;
            this.f2480a = (float) i;
            float f2 = 1.0f / ((this.f2483d * 2.0f) * (1.0f - this.f2483d));
            this.f2487h = f2;
            this.f2484e = f2;
            this.f2485f = this.f2483d / ((this.f2483d - 1.0f) * 2.0f);
            this.f2486g = 1.0f / (1.0f - this.f2483d);
        }

        public float m3106a(float f) {
            return f < this.f2483d ? (this.f2484e * f) * f : f < 1.0f - this.f2483d ? this.f2485f + (this.f2486g * f) : 1.0f - ((this.f2487h * (f - 1.0f)) * (f - 1.0f));
        }

        public void m3107a() {
        }

        public void m3108a(float f, float f2) {
        }

        public void m3109b() {
        }

        public void m3110c() {
            this.f2481b = SystemClock.uptimeMillis();
            this.f2488i = false;
            m3107a();
            this.f2482c.post(this);
        }

        public void m3111d() {
            this.f2488i = true;
        }

        public void run() {
            if (!this.f2488i) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.f2481b)) / this.f2480a;
                if (uptimeMillis >= 1.0f) {
                    m3108a(1.0f, 1.0f);
                    m3109b();
                    return;
                }
                m3108a(uptimeMillis, m3106a(uptimeMillis));
                this.f2482c.post(this);
            }
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.g */
    private class C0661g extends C0660n {
        final /* synthetic */ DragSortListView f2489a;
        private int f2490d;
        private int f2491e;
        private float f2492f;
        private float f2493g;

        public C0661g(DragSortListView dragSortListView, float f, int i) {
            this.f2489a = dragSortListView;
            super(dragSortListView, f, i);
        }

        private int m3112e() {
            int i = (this.f2489a.f2555w + this.f2489a.getDividerHeight()) / 2;
            View childAt = this.f2489a.getChildAt(this.f2490d - this.f2489a.getFirstVisiblePosition());
            if (childAt != null) {
                return this.f2490d == this.f2491e ? childAt.getTop() : this.f2490d < this.f2491e ? childAt.getTop() - i : (childAt.getBottom() + i) - this.f2489a.f2556x;
            } else {
                m3111d();
                return -1;
            }
        }

        public void m3113a() {
            this.f2490d = this.f2489a.f2541i;
            this.f2491e = this.f2489a.f2545m;
            this.f2489a.f2554v = 2;
            this.f2492f = (float) (this.f2489a.f2534b.y - m3112e());
            this.f2493g = (float) (this.f2489a.f2534b.x - this.f2489a.getPaddingLeft());
        }

        public void m3114a(float f, float f2) {
            int e = m3112e();
            float paddingLeft = (float) (this.f2489a.f2534b.x - this.f2489a.getPaddingLeft());
            float f3 = 1.0f - f2;
            if (f3 < Math.abs(((float) (this.f2489a.f2534b.y - e)) / this.f2492f) || f3 < Math.abs(paddingLeft / this.f2493g)) {
                this.f2489a.f2534b.y = e + ((int) (this.f2492f * f3));
                this.f2489a.f2534b.x = this.f2489a.getPaddingLeft() + ((int) (this.f2493g * f3));
                this.f2489a.m3145b(true);
            }
        }

        public void m3115b() {
            this.f2489a.m3161f();
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.i */
    public interface C0662i {
        void m3116a(View view);

        void m3117a(View view, Point point, Point point2);

        View m3118c(int i);
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.j */
    private class C0663j {
        final /* synthetic */ DragSortListView f2494a;
        private SparseIntArray f2495b;
        private ArrayList<Integer> f2496c;
        private int f2497d;

        public C0663j(DragSortListView dragSortListView, int i) {
            this.f2494a = dragSortListView;
            this.f2495b = new SparseIntArray(i);
            this.f2496c = new ArrayList(i);
            this.f2497d = i;
        }

        public int m3119a(int i) {
            return this.f2495b.get(i, -1);
        }

        public void m3120a() {
            this.f2495b.clear();
            this.f2496c.clear();
        }

        public void m3121a(int i, int i2) {
            int i3 = this.f2495b.get(i, -1);
            if (i3 != i2) {
                if (i3 != -1) {
                    this.f2496c.remove(Integer.valueOf(i));
                } else if (this.f2495b.size() == this.f2497d) {
                    this.f2495b.delete(((Integer) this.f2496c.remove(0)).intValue());
                }
                this.f2495b.put(i, i2);
                this.f2496c.add(Integer.valueOf(i));
            }
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.k */
    private class C0664k extends C0660n {
        final /* synthetic */ DragSortListView f2498a;
        private float f2499d;
        private float f2500e;

        public void m3122a() {
            this.f2499d = (float) this.f2498a.f2547o;
            this.f2500e = (float) this.f2498a.f2557y;
        }

        public void m3123a(float f, float f2) {
            if (this.f2498a.f2554v != 4) {
                m3111d();
                return;
            }
            this.f2498a.f2547o = (int) ((this.f2500e * f2) + ((1.0f - f2) * this.f2499d));
            this.f2498a.f2534b.y = this.f2498a.f2523N - this.f2498a.f2547o;
            this.f2498a.m3145b(true);
        }
    }

    /* renamed from: com.wakdev.wdsortablelist.DragSortListView.l */
    private class C0665l extends C0660n {
        final /* synthetic */ DragSortListView f2501a;
        private float f2502d;
        private float f2503e;
        private float f2504f;
        private int f2505g;
        private int f2506h;
        private int f2507i;
        private int f2508j;
        private int f2509k;

        public C0665l(DragSortListView dragSortListView, float f, int i) {
            this.f2501a = dragSortListView;
            super(dragSortListView, f, i);
            this.f2505g = -1;
            this.f2506h = -1;
        }

        public void m3124a() {
            int i = -1;
            this.f2505g = -1;
            this.f2506h = -1;
            this.f2507i = this.f2501a.f2542j;
            this.f2508j = this.f2501a.f2543k;
            this.f2509k = this.f2501a.f2545m;
            this.f2501a.f2554v = 1;
            this.f2502d = (float) this.f2501a.f2534b.x;
            if (this.f2501a.al) {
                float width = ((float) this.f2501a.getWidth()) * 2.0f;
                if (this.f2501a.am == 0.0f) {
                    DragSortListView dragSortListView = this.f2501a;
                    if (this.f2502d >= 0.0f) {
                        i = 1;
                    }
                    dragSortListView.am = ((float) i) * width;
                    return;
                }
                float f = width * 2.0f;
                if (this.f2501a.am < 0.0f && this.f2501a.am > (-f)) {
                    this.f2501a.am = -f;
                    return;
                } else if (this.f2501a.am > 0.0f && this.f2501a.am < f) {
                    this.f2501a.am = f;
                    return;
                } else {
                    return;
                }
            }
            this.f2501a.m3176n();
        }

        public void m3125a(float f, float f2) {
            float f3 = 1.0f - f2;
            int firstVisiblePosition = this.f2501a.getFirstVisiblePosition();
            View childAt = this.f2501a.getChildAt(this.f2507i - firstVisiblePosition);
            if (this.f2501a.al) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.b)) / 1000.0f;
                if (uptimeMillis != 0.0f) {
                    float o = this.f2501a.am * uptimeMillis;
                    int width = this.f2501a.getWidth();
                    this.f2501a.am = ((((float) (this.f2501a.am > 0.0f ? 1 : -1)) * uptimeMillis) * ((float) width)) + this.f2501a.am;
                    this.f2502d += o;
                    this.f2501a.f2534b.x = (int) this.f2502d;
                    if (this.f2502d < ((float) width) && this.f2502d > ((float) (-width))) {
                        this.b = SystemClock.uptimeMillis();
                        this.f2501a.m3145b(true);
                        return;
                    }
                }
                return;
            }
            if (childAt != null) {
                if (this.f2505g == -1) {
                    this.f2505g = this.f2501a.m3139b(this.f2507i, childAt, false);
                    this.f2503e = (float) (childAt.getHeight() - this.f2505g);
                }
                int max = Math.max((int) (this.f2503e * f3), 1);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                layoutParams.height = max + this.f2505g;
                childAt.setLayoutParams(layoutParams);
            }
            if (this.f2508j != this.f2507i) {
                View childAt2 = this.f2501a.getChildAt(this.f2508j - firstVisiblePosition);
                if (childAt2 != null) {
                    if (this.f2506h == -1) {
                        this.f2506h = this.f2501a.m3139b(this.f2508j, childAt2, false);
                        this.f2504f = (float) (childAt2.getHeight() - this.f2506h);
                    }
                    int max2 = Math.max((int) (this.f2504f * f3), 1);
                    ViewGroup.LayoutParams layoutParams2 = childAt2.getLayoutParams();
                    layoutParams2.height = max2 + this.f2506h;
                    childAt2.setLayoutParams(layoutParams2);
                }
            }
        }

        public void m3126b() {
            this.f2501a.m3163g();
        }
    }

    public DragSortListView(Context context, AttributeSet attributeSet) {
        int i;
        super(context, attributeSet);
        this.f2534b = new Point();
        this.f2535c = new Point();
        this.f2537e = false;
        this.f2539g = 1.0f;
        this.f2540h = 1.0f;
        this.f2544l = false;
        this.f2553u = true;
        this.f2554v = 0;
        this.f2555w = 1;
        this.f2558z = 0;
        this.f2510A = new View[1];
        this.f2512C = 0.33333334f;
        this.f2513D = 0.33333334f;
        this.f2520K = 0.5f;
        this.f2521L = new C06511(this);
        this.f2527R = 0;
        this.f2528S = false;
        this.f2529T = false;
        this.f2530U = null;
        this.f2532W = 0;
        this.aa = 0.25f;
        this.ab = 0.0f;
        this.ad = false;
        this.af = false;
        this.ag = false;
        this.ah = new C0663j(this, 3);
        this.am = 0.0f;
        this.an = false;
        this.ao = false;
        int i2 = 150;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0627j.DragSortListView, 0, 0);
            this.f2555w = Math.max(1, obtainStyledAttributes.getDimensionPixelSize(C0627j.DragSortListView_collapsed_height, 1));
            this.ad = obtainStyledAttributes.getBoolean(C0627j.DragSortListView_track_drag_sort, false);
            if (this.ad) {
                this.ae = new C0659f(this);
            }
            this.f2539g = obtainStyledAttributes.getFloat(C0627j.DragSortListView_float_alpha, this.f2539g);
            this.f2540h = this.f2539g;
            this.f2553u = obtainStyledAttributes.getBoolean(C0627j.DragSortListView_drag_enabled, this.f2553u);
            this.aa = Math.max(0.0f, Math.min(1.0f, 1.0f - obtainStyledAttributes.getFloat(C0627j.DragSortListView_slide_shuffle_speed, 0.75f)));
            this.f2544l = this.aa > 0.0f;
            setDragScrollStart(obtainStyledAttributes.getFloat(C0627j.DragSortListView_drag_scroll_start, this.f2512C));
            this.f2520K = obtainStyledAttributes.getFloat(C0627j.DragSortListView_max_drag_scroll_speed, this.f2520K);
            int i3 = obtainStyledAttributes.getInt(C0627j.DragSortListView_remove_animation_duration, 150);
            int i4 = obtainStyledAttributes.getInt(C0627j.DragSortListView_drop_animation_duration, 150);
            if (obtainStyledAttributes.getBoolean(C0627j.DragSortListView_use_default_controller, true)) {
                boolean z = obtainStyledAttributes.getBoolean(C0627j.DragSortListView_remove_enabled, false);
                int i5 = obtainStyledAttributes.getInt(C0627j.DragSortListView_remove_mode, 1);
                boolean z2 = obtainStyledAttributes.getBoolean(C0627j.DragSortListView_sort_enabled, true);
                int i6 = obtainStyledAttributes.getInt(C0627j.DragSortListView_drag_start_mode, 0);
                int resourceId = obtainStyledAttributes.getResourceId(C0627j.DragSortListView_drag_handle_id, 0);
                int resourceId2 = obtainStyledAttributes.getResourceId(C0627j.DragSortListView_fling_handle_id, 0);
                int resourceId3 = obtainStyledAttributes.getResourceId(C0627j.DragSortListView_click_remove_id, 0);
                int color = obtainStyledAttributes.getColor(C0627j.DragSortListView_float_background_color, -16777216);
                Object c0668a = new C0668a(this, resourceId, i6, i5, resourceId3, resourceId2);
                c0668a.m3218b(z);
                c0668a.m3214a(z2);
                c0668a.m3203d(color);
                this.f2530U = c0668a;
                setOnTouchListener(c0668a);
            }
            obtainStyledAttributes.recycle();
            i = i4;
            i2 = i3;
        } else {
            i = 150;
        }
        this.f2511B = new C0656d(this);
        if (i2 > 0) {
            this.ai = new C0665l(this, 0.5f, i2);
        }
        if (i > 0) {
            this.ak = new C0661g(this, 0.5f, i);
        }
        this.f2531V = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        this.f2538f = new C06522(this);
    }

    private int m3129a(int i, int i2) {
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        if (i <= headerViewsCount || i >= getCount() - footerViewsCount) {
            return i2;
        }
        headerViewsCount = getDividerHeight();
        footerViewsCount = this.f2556x - this.f2555w;
        int d = m3153d(i);
        int b = m3138b(i);
        if (this.f2543k <= this.f2545m) {
            if (i == this.f2543k && this.f2542j != this.f2543k) {
                i2 = i == this.f2545m ? (i2 + b) - this.f2556x : ((b - d) + i2) - footerViewsCount;
            } else if (i > this.f2543k && i <= this.f2545m) {
                i2 -= footerViewsCount;
            }
        } else if (i > this.f2545m && i <= this.f2542j) {
            i2 += footerViewsCount;
        } else if (i == this.f2543k && this.f2542j != this.f2543k) {
            i2 += b - d;
        }
        return i <= this.f2545m ? (((this.f2556x - headerViewsCount) - m3153d(i - 1)) / 2) + i2 : (((d - headerViewsCount) - this.f2556x) / 2) + i2;
    }

    private int m3130a(int i, View view, int i2, int i3) {
        int i4;
        int d = m3153d(i);
        int height = view.getHeight();
        int c = m3147c(i, d);
        if (i != this.f2545m) {
            i4 = height - d;
            d = c - d;
        } else {
            d = c;
            i4 = height;
        }
        int i5 = this.f2556x;
        if (!(this.f2545m == this.f2542j || this.f2545m == this.f2543k)) {
            i5 -= this.f2555w;
        }
        if (i <= i2) {
            if (i > this.f2542j) {
                return (i5 - d) + 0;
            }
        } else if (i == i3) {
            return i <= this.f2542j ? (i4 - i5) + 0 : i == this.f2543k ? (height - c) + 0 : 0 + i4;
        } else {
            if (i <= this.f2542j) {
                return 0 - i5;
            }
            if (i == this.f2543k) {
                return 0 - d;
            }
        }
        return 0;
    }

    private void m3133a(int i, Canvas canvas) {
        Drawable divider = getDivider();
        int dividerHeight = getDividerHeight();
        if (divider != null && dividerHeight != 0) {
            ViewGroup viewGroup = (ViewGroup) getChildAt(i - getFirstVisiblePosition());
            if (viewGroup != null) {
                int i2;
                int paddingLeft = getPaddingLeft();
                int width = getWidth() - getPaddingRight();
                int height = viewGroup.getChildAt(0).getHeight();
                if (i > this.f2545m) {
                    height += viewGroup.getTop();
                    i2 = height + dividerHeight;
                } else {
                    i2 = viewGroup.getBottom() - height;
                    height = i2 - dividerHeight;
                }
                canvas.save();
                canvas.clipRect(paddingLeft, height, width, i2);
                divider.setBounds(paddingLeft, height, width, i2);
                divider.draw(canvas);
                canvas.restore();
            }
        }
    }

    private void m3134a(int i, View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int c = (i == this.f2545m || i == this.f2542j || i == this.f2543k) ? m3148c(i, view, z) : -2;
        if (c != layoutParams.height) {
            layoutParams.height = c;
            view.setLayoutParams(layoutParams);
        }
        if (i == this.f2542j || i == this.f2543k) {
            if (i < this.f2545m) {
                ((C0669b) view).setGravity(80);
            } else if (i > this.f2545m) {
                ((C0669b) view).setGravity(48);
            }
        }
        int visibility = view.getVisibility();
        c = 0;
        if (i == this.f2545m && this.f2533a != null) {
            c = 4;
        }
        if (c != visibility) {
            view.setVisibility(c);
        }
    }

    private void m3135a(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
            view.setLayoutParams(layoutParams);
        }
        view.measure(ViewGroup.getChildMeasureSpec(this.f2558z, getListPaddingLeft() + getListPaddingRight(), layoutParams.width), layoutParams.height > 0 ? MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824) : MeasureSpec.makeMeasureSpec(0, 0));
    }

    private int m3138b(int i) {
        View childAt = getChildAt(i - getFirstVisiblePosition());
        return childAt != null ? childAt.getHeight() : m3147c(i, m3153d(i));
    }

    private int m3139b(int i, View view, boolean z) {
        if (i == this.f2545m) {
            return 0;
        }
        if (i >= getHeaderViewsCount() && i < getCount() - getFooterViewsCount()) {
            view = ((ViewGroup) view).getChildAt(0);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            return layoutParams.height;
        }
        int height = view.getHeight();
        if (height != 0 && !z) {
            return height;
        }
        m3135a(view);
        return view.getMeasuredHeight();
    }

    private void m3143b(int i, int i2) {
        this.f2534b.x = i - this.f2546n;
        this.f2534b.y = i2 - this.f2547o;
        m3145b(true);
        int min = Math.min(i2, this.f2536d + this.f2557y);
        int max = Math.max(i2, this.f2536d - this.f2557y);
        int b = this.f2511B.m3100b();
        if (min > this.f2525P && min > this.f2515F && b != 1) {
            if (b != -1) {
                this.f2511B.m3098a(true);
            }
            this.f2511B.m3097a(1);
        } else if (max < this.f2525P && max < this.f2514E && b != 0) {
            if (b != -1) {
                this.f2511B.m3098a(true);
            }
            this.f2511B.m3097a(0);
        } else if (max >= this.f2514E && min <= this.f2515F && this.f2511B.m3099a()) {
            this.f2511B.m3098a(true);
        }
    }

    private void m3144b(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            this.f2524O = this.f2522M;
            this.f2525P = this.f2523N;
        }
        this.f2522M = (int) motionEvent.getX();
        this.f2523N = (int) motionEvent.getY();
        if (action == 0) {
            this.f2524O = this.f2522M;
            this.f2525P = this.f2523N;
        }
        this.f2548p = ((int) motionEvent.getRawX()) - this.f2522M;
        this.f2549q = ((int) motionEvent.getRawY()) - this.f2523N;
    }

    private void m3145b(boolean z) {
        int firstVisiblePosition = getFirstVisiblePosition() + (getChildCount() / 2);
        View childAt = getChildAt(getChildCount() / 2);
        if (childAt != null) {
            m3156d(firstVisiblePosition, childAt, z);
        }
    }

    private int m3147c(int i, int i2) {
        getDividerHeight();
        Object obj = (!this.f2544l || this.f2542j == this.f2543k) ? null : 1;
        int i3 = this.f2556x - this.f2555w;
        int i4 = (int) (this.ab * ((float) i3));
        return i == this.f2545m ? this.f2545m == this.f2542j ? obj != null ? i4 + this.f2555w : this.f2556x : this.f2545m == this.f2543k ? this.f2556x - i4 : this.f2555w : i == this.f2542j ? obj != null ? i2 + i4 : i2 + i3 : i == this.f2543k ? (i2 + i3) - i4 : i2;
    }

    private int m3148c(int i, View view, boolean z) {
        return m3147c(i, m3139b(i, view, z));
    }

    private void m3151c(int i) {
        this.f2554v = 1;
        if (this.f2552t != null) {
            this.f2552t.m3101a(i);
        }
        m3176n();
        m3165h();
        m3159e();
        if (this.f2529T) {
            this.f2554v = 3;
        } else {
            this.f2554v = 0;
        }
    }

    private int m3153d(int i) {
        if (i == this.f2545m) {
            return 0;
        }
        View childAt = getChildAt(i - getFirstVisiblePosition());
        if (childAt != null) {
            return m3139b(i, childAt, false);
        }
        int a = this.ah.m3119a(i);
        if (a != -1) {
            return a;
        }
        View view;
        ListAdapter adapter = getAdapter();
        int itemViewType = adapter.getItemViewType(i);
        int viewTypeCount = adapter.getViewTypeCount();
        if (viewTypeCount != this.f2510A.length) {
            this.f2510A = new View[viewTypeCount];
        }
        if (itemViewType < 0) {
            view = adapter.getView(i, null, this);
        } else if (this.f2510A[itemViewType] == null) {
            view = adapter.getView(i, null, this);
            this.f2510A[itemViewType] = view;
        } else {
            view = adapter.getView(i, this.f2510A[itemViewType], this);
        }
        a = m3139b(i, view, true);
        this.ah.m3121a(i, a);
        return a;
    }

    private void m3156d(int i, View view, boolean z) {
        this.af = true;
        m3175m();
        int i2 = this.f2542j;
        int i3 = this.f2543k;
        boolean d = m3157d();
        if (d) {
            m3170k();
            setSelectionFromTop(i, (m3130a(i, view, i2, i3) + view.getTop()) - getPaddingTop());
            layoutChildren();
        }
        if (d || z) {
            invalidate();
        }
        this.af = false;
    }

    private boolean m3157d() {
        int count;
        int firstVisiblePosition = getFirstVisiblePosition();
        int i = this.f2542j;
        View childAt = getChildAt(i - firstVisiblePosition);
        if (childAt == null) {
            i = firstVisiblePosition + (getChildCount() / 2);
            childAt = getChildAt(i - firstVisiblePosition);
        }
        firstVisiblePosition = childAt.getTop();
        int height = childAt.getHeight();
        int a = m3129a(i, firstVisiblePosition);
        int dividerHeight = getDividerHeight();
        if (this.f2536d >= a) {
            count = getCount();
            int i2 = height;
            height = firstVisiblePosition;
            firstVisiblePosition = a;
            int i3 = a;
            a = i;
            i = i3;
            while (a < count) {
                if (a != count - 1) {
                    height += dividerHeight + i2;
                    i2 = m3138b(a + 1);
                    firstVisiblePosition = m3129a(a + 1, height);
                    if (this.f2536d < firstVisiblePosition) {
                        break;
                    }
                    a++;
                    i = firstVisiblePosition;
                } else {
                    firstVisiblePosition = (height + dividerHeight) + i2;
                    break;
                }
            }
        }
        height = firstVisiblePosition;
        firstVisiblePosition = a;
        i3 = a;
        a = i;
        i = i3;
        while (a >= 0) {
            a--;
            firstVisiblePosition = m3138b(a);
            if (a != 0) {
                height -= firstVisiblePosition + dividerHeight;
                firstVisiblePosition = m3129a(a, height);
                if (this.f2536d >= firstVisiblePosition) {
                    break;
                }
                i = firstVisiblePosition;
            } else {
                firstVisiblePosition = (height - dividerHeight) - firstVisiblePosition;
                break;
            }
        }
        height = getHeaderViewsCount();
        dividerHeight = getFooterViewsCount();
        count = this.f2542j;
        int i4 = this.f2543k;
        float f = this.ab;
        if (this.f2544l) {
            int abs = Math.abs(firstVisiblePosition - i);
            if (this.f2536d >= firstVisiblePosition) {
                i3 = i;
                i = firstVisiblePosition;
                firstVisiblePosition = i3;
            }
            abs = (int) (((float) abs) * (this.aa * 0.5f));
            float f2 = (float) abs;
            i += abs;
            abs = firstVisiblePosition - abs;
            if (this.f2536d < i) {
                this.f2542j = a - 1;
                this.f2543k = a;
                this.ab = (((float) (i - this.f2536d)) * 0.5f) / f2;
            } else if (this.f2536d < abs) {
                this.f2542j = a;
                this.f2543k = a;
            } else {
                this.f2542j = a;
                this.f2543k = a + 1;
                this.ab = (1.0f + (((float) (firstVisiblePosition - this.f2536d)) / f2)) * 0.5f;
            }
        } else {
            this.f2542j = a;
            this.f2543k = a;
        }
        if (this.f2542j < height) {
            this.f2542j = height;
            this.f2543k = height;
            a = height;
        } else if (this.f2543k >= getCount() - dividerHeight) {
            a = (getCount() - dividerHeight) - 1;
            this.f2542j = a;
            this.f2543k = a;
        }
        boolean z = (this.f2542j == count && this.f2543k == i4 && this.ab == f) ? false : true;
        if (a == this.f2541i) {
            return z;
        }
        if (this.f2550r != null) {
            this.f2550r.m3096a(this.f2541i - height, a - height);
        }
        this.f2541i = a;
        return true;
    }

    private void m3159e() {
        this.f2545m = -1;
        this.f2542j = -1;
        this.f2543k = -1;
        this.f2541i = -1;
    }

    private void m3161f() {
        this.f2554v = 2;
        if (this.f2551s != null && this.f2541i >= 0 && this.f2541i < getCount()) {
            int headerViewsCount = getHeaderViewsCount();
            this.f2551s.a_(this.f2545m - headerViewsCount, this.f2541i - headerViewsCount);
        }
        m3176n();
        m3165h();
        m3159e();
        m3170k();
        if (this.f2529T) {
            this.f2554v = 3;
        } else {
            this.f2554v = 0;
        }
    }

    private void m3163g() {
        m3151c(this.f2545m - getHeaderViewsCount());
    }

    private void m3165h() {
        int i = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        if (this.f2545m < firstVisiblePosition) {
            View childAt = getChildAt(0);
            if (childAt != null) {
                i = childAt.getTop();
            }
            setSelectionFromTop(firstVisiblePosition - 1, i - getPaddingTop());
        }
    }

    private void m3167i() {
        this.f2532W = 0;
        this.f2529T = false;
        if (this.f2554v == 3) {
            this.f2554v = 0;
        }
        this.f2540h = this.f2539g;
        this.an = false;
        this.ah.m3120a();
    }

    private void m3169j() {
        int paddingTop = getPaddingTop();
        int height = (getHeight() - paddingTop) - getPaddingBottom();
        float f = (float) height;
        this.f2517H = ((float) paddingTop) + (this.f2512C * f);
        this.f2516G = (f * (1.0f - this.f2513D)) + ((float) paddingTop);
        this.f2514E = (int) this.f2517H;
        this.f2515F = (int) this.f2516G;
        this.f2518I = this.f2517H - ((float) paddingTop);
        this.f2519J = ((float) (paddingTop + height)) - this.f2516G;
    }

    private void m3170k() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        lastVisiblePosition = Math.min(lastVisiblePosition - firstVisiblePosition, ((getCount() - 1) - getFooterViewsCount()) - firstVisiblePosition);
        for (int max = Math.max(0, getHeaderViewsCount() - firstVisiblePosition); max <= lastVisiblePosition; max++) {
            View childAt = getChildAt(max);
            if (childAt != null) {
                m3134a(firstVisiblePosition + max, childAt, false);
            }
        }
    }

    private void m3173l() {
        if (this.f2533a != null) {
            m3135a(this.f2533a);
            this.f2556x = this.f2533a.getMeasuredHeight();
            this.f2557y = this.f2556x / 2;
        }
    }

    private void m3175m() {
        if (this.f2530U != null) {
            this.f2535c.set(this.f2522M, this.f2523N);
            this.f2530U.m3117a(this.f2533a, this.f2534b, this.f2535c);
        }
        int i = this.f2534b.x;
        int i2 = this.f2534b.y;
        int paddingLeft = getPaddingLeft();
        if ((this.f2527R & 1) == 0 && i > paddingLeft) {
            this.f2534b.x = paddingLeft;
        } else if ((this.f2527R & 2) == 0 && i < paddingLeft) {
            this.f2534b.x = paddingLeft;
        }
        paddingLeft = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        i = getPaddingTop();
        if (firstVisiblePosition < paddingLeft) {
            i = getChildAt((paddingLeft - firstVisiblePosition) - 1).getBottom();
        }
        if ((this.f2527R & 8) == 0 && firstVisiblePosition <= this.f2545m) {
            i = Math.max(getChildAt(this.f2545m - firstVisiblePosition).getTop(), i);
        }
        paddingLeft = getHeight() - getPaddingBottom();
        if (lastVisiblePosition >= (getCount() - footerViewsCount) - 1) {
            paddingLeft = getChildAt(((getCount() - footerViewsCount) - 1) - firstVisiblePosition).getBottom();
        }
        if ((this.f2527R & 4) == 0 && lastVisiblePosition >= this.f2545m) {
            paddingLeft = Math.min(getChildAt(this.f2545m - firstVisiblePosition).getBottom(), paddingLeft);
        }
        if (i2 < i) {
            this.f2534b.y = i;
        } else if (this.f2556x + i2 > paddingLeft) {
            this.f2534b.y = paddingLeft - this.f2556x;
        }
        this.f2536d = this.f2534b.y + this.f2557y;
    }

    private void m3176n() {
        if (this.f2533a != null) {
            this.f2533a.setVisibility(8);
            if (this.f2530U != null) {
                this.f2530U.m3116a(this.f2533a);
            }
            this.f2533a = null;
            invalidate();
        }
    }

    public void m3188a() {
        if (this.f2554v == 4) {
            this.f2511B.m3098a(true);
            m3176n();
            m3159e();
            m3170k();
            if (this.f2529T) {
                this.f2554v = 3;
            } else {
                this.f2554v = 0;
            }
        }
    }

    public void m3189a(float f, float f2) {
        if (f2 > 0.5f) {
            this.f2513D = 0.5f;
        } else {
            this.f2513D = f2;
        }
        if (f > 0.5f) {
            this.f2512C = 0.5f;
        } else {
            this.f2512C = f;
        }
        if (getHeight() != 0) {
            m3169j();
        }
    }

    public void m3190a(int i) {
        this.al = false;
        m3191a(i, 0.0f);
    }

    public void m3191a(int i, float f) {
        if (this.f2554v == 0 || this.f2554v == 4) {
            if (this.f2554v == 0) {
                this.f2545m = getHeaderViewsCount() + i;
                this.f2542j = this.f2545m;
                this.f2543k = this.f2545m;
                this.f2541i = this.f2545m;
                View childAt = getChildAt(this.f2545m - getFirstVisiblePosition());
                if (childAt != null) {
                    childAt.setVisibility(4);
                }
            }
            this.f2554v = 1;
            this.am = f;
            if (this.f2529T) {
                switch (this.f2532W) {
                    case C0627j.View_paddingStart /*1*/:
                        super.onTouchEvent(this.f2531V);
                        break;
                    case C0627j.View_paddingEnd /*2*/:
                        super.onInterceptTouchEvent(this.f2531V);
                        break;
                }
            }
            if (this.ai != null) {
                this.ai.m3110c();
            } else {
                m3151c(i);
            }
        }
    }

    public boolean m3192a(int i, int i2, int i3, int i4) {
        if (!this.f2529T || this.f2530U == null) {
            return false;
        }
        View c = this.f2530U.m3118c(i);
        return c != null ? m3193a(i, c, i2, i3, i4) : false;
    }

    public boolean m3193a(int i, View view, int i2, int i3, int i4) {
        if (this.f2554v != 0 || !this.f2529T || this.f2533a != null || view == null || !this.f2553u) {
            return false;
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        int headerViewsCount = getHeaderViewsCount() + i;
        this.f2542j = headerViewsCount;
        this.f2543k = headerViewsCount;
        this.f2545m = headerViewsCount;
        this.f2541i = headerViewsCount;
        this.f2554v = 4;
        this.f2527R = 0;
        this.f2527R |= i2;
        this.f2533a = view;
        m3173l();
        this.f2546n = i3;
        this.f2547o = i4;
        this.f2526Q = this.f2523N;
        this.f2534b.x = this.f2522M - this.f2546n;
        this.f2534b.y = this.f2523N - this.f2547o;
        View childAt = getChildAt(this.f2545m - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setVisibility(4);
        }
        if (this.ad) {
            this.ae.m3102a();
        }
        switch (this.f2532W) {
            case C0627j.View_paddingStart /*1*/:
                super.onTouchEvent(this.f2531V);
                break;
            case C0627j.View_paddingEnd /*2*/:
                super.onInterceptTouchEvent(this.f2531V);
                break;
        }
        requestLayout();
        if (this.aj == null) {
            return true;
        }
        this.aj.m3110c();
        return true;
    }

    protected boolean m3194a(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        switch (motionEvent.getAction() & 255) {
            case C0627j.View_paddingStart /*1*/:
                if (this.f2554v == 4) {
                    m3195a(false);
                }
                m3167i();
                break;
            case C0627j.View_paddingEnd /*2*/:
                m3143b((int) motionEvent.getX(), (int) motionEvent.getY());
                break;
            case C0627j.Toolbar_subtitle /*3*/:
                if (this.f2554v == 4) {
                    m3188a();
                }
                m3167i();
                break;
        }
        return true;
    }

    public boolean m3195a(boolean z) {
        this.al = false;
        return m3198b(z, 0.0f);
    }

    public boolean m3196a(boolean z, float f) {
        this.al = true;
        return m3198b(z, f);
    }

    public boolean m3197b() {
        return this.an;
    }

    public boolean m3198b(boolean z, float f) {
        if (this.f2533a == null) {
            return false;
        }
        this.f2511B.m3098a(true);
        if (z) {
            m3191a(this.f2545m - getHeaderViewsCount(), f);
        } else if (this.ak != null) {
            this.ak.m3110c();
        } else {
            m3161f();
        }
        if (!this.ad) {
            return true;
        }
        this.ae.m3105d();
        return true;
    }

    public boolean m3199c() {
        return this.f2553u;
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f2554v != 0) {
            if (this.f2542j != this.f2545m) {
                m3133a(this.f2542j, canvas);
            }
            if (!(this.f2543k == this.f2542j || this.f2543k == this.f2545m)) {
                m3133a(this.f2543k, canvas);
            }
        }
        if (this.f2533a != null) {
            float f;
            int width = this.f2533a.getWidth();
            int height = this.f2533a.getHeight();
            int i = this.f2534b.x;
            int width2 = getWidth();
            if (i < 0) {
                i = -i;
            }
            if (i < width2) {
                f = ((float) (width2 - i)) / ((float) width2);
                f *= f;
            } else {
                f = 0.0f;
            }
            int i2 = (int) (f * (255.0f * this.f2540h));
            canvas.save();
            canvas.translate((float) this.f2534b.x, (float) this.f2534b.y);
            canvas.clipRect(0, 0, width, height);
            canvas.saveLayerAlpha(0.0f, 0.0f, (float) width, (float) height, i2, 31);
            this.f2533a.draw(canvas);
            canvas.restore();
            canvas.restore();
        }
    }

    public float getFloatAlpha() {
        return this.f2540h;
    }

    public ListAdapter getInputAdapter() {
        return this.ac == null ? null : this.ac.m3095a();
    }

    protected void layoutChildren() {
        super.layoutChildren();
        if (this.f2533a != null) {
            if (this.f2533a.isLayoutRequested() && !this.f2537e) {
                m3173l();
            }
            this.f2533a.layout(0, 0, this.f2533a.getMeasuredWidth(), this.f2533a.getMeasuredHeight());
            this.f2537e = false;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.ad) {
            this.ae.m3103b();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f2553u) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        boolean z;
        m3144b(motionEvent);
        this.f2528S = true;
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (this.f2554v != 0) {
                this.ag = true;
                return true;
            }
            this.f2529T = true;
        }
        if (this.f2533a == null) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                this.an = true;
                z = true;
            } else {
                z = false;
            }
            switch (action) {
                case C0627j.View_paddingStart /*1*/:
                case C0627j.Toolbar_subtitle /*3*/:
                    m3167i();
                    break;
                default:
                    if (!z) {
                        this.f2532W = 2;
                        break;
                    }
                    this.f2532W = 1;
                    break;
            }
        }
        z = true;
        if (action == 1 || action == 3) {
            this.f2529T = false;
        }
        return z;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f2533a != null) {
            if (this.f2533a.isLayoutRequested()) {
                m3173l();
            }
            this.f2537e = true;
        }
        this.f2558z = i;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m3169j();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.ag) {
            this.ag = false;
            return false;
        } else if (!this.f2553u) {
            return super.onTouchEvent(motionEvent);
        } else {
            boolean z2 = this.f2528S;
            this.f2528S = false;
            if (!z2) {
                m3144b(motionEvent);
            }
            if (this.f2554v == 4) {
                m3194a(motionEvent);
                return true;
            }
            if (this.f2554v == 0 && super.onTouchEvent(motionEvent)) {
                z = true;
            }
            switch (motionEvent.getAction() & 255) {
                case C0627j.View_paddingStart /*1*/:
                case C0627j.Toolbar_subtitle /*3*/:
                    m3167i();
                    return z;
                default:
                    if (!z) {
                        return z;
                    }
                    this.f2532W = 1;
                    return z;
            }
        }
    }

    public void requestLayout() {
        if (!this.af) {
            super.requestLayout();
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            this.ac = new C0654a(this, listAdapter);
            listAdapter.registerDataSetObserver(this.f2538f);
            if (listAdapter instanceof C0631h) {
                setDropListener((C0631h) listAdapter);
            }
            if (listAdapter instanceof C0655b) {
                setDragListener((C0655b) listAdapter);
            }
            if (listAdapter instanceof C0657m) {
                setRemoveListener((C0657m) listAdapter);
            }
        } else {
            this.ac = null;
        }
        super.setAdapter(this.ac);
    }

    public void setDragEnabled(boolean z) {
        this.f2553u = z;
    }

    public void setDragListener(C0655b c0655b) {
        this.f2550r = c0655b;
    }

    public void setDragScrollProfile(C0650c c0650c) {
        if (c0650c != null) {
            this.f2521L = c0650c;
        }
    }

    public void setDragScrollStart(float f) {
        m3189a(f, f);
    }

    public void setDragSortListener(C0658e c0658e) {
        setDropListener(c0658e);
        setDragListener(c0658e);
        setRemoveListener(c0658e);
    }

    public void setDropListener(C0631h c0631h) {
        this.f2551s = c0631h;
    }

    public void setFloatAlpha(float f) {
        this.f2540h = f;
    }

    public void setFloatViewManager(C0662i c0662i) {
        this.f2530U = c0662i;
    }

    public void setMaxScrollSpeed(float f) {
        this.f2520K = f;
    }

    public void setRemoveListener(C0657m c0657m) {
        this.f2552t = c0657m;
    }
}
