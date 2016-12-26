package com.wakdev.wdsortablelist;

import android.graphics.Point;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import com.wakdev.nfctools.C0628m.C0627j;

/* renamed from: com.wakdev.wdsortablelist.a */
public class C0668a extends C0667d implements OnGestureListener, OnTouchListener {
    private int f2564a;
    private boolean f2565b;
    private int f2566c;
    private boolean f2567d;
    private boolean f2568e;
    private GestureDetector f2569f;
    private GestureDetector f2570g;
    private int f2571h;
    private int f2572i;
    private int f2573j;
    private int f2574k;
    private int[] f2575l;
    private int f2576m;
    private int f2577n;
    private int f2578o;
    private int f2579p;
    private boolean f2580q;
    private float f2581r;
    private int f2582s;
    private int f2583t;
    private int f2584u;
    private boolean f2585v;
    private DragSortListView f2586w;
    private int f2587x;
    private OnGestureListener f2588y;

    /* renamed from: com.wakdev.wdsortablelist.a.1 */
    class C06661 extends SimpleOnGestureListener {
        final /* synthetic */ C0668a f2559a;

        C06661(C0668a c0668a) {
            this.f2559a = c0668a;
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.f2559a.f2567d && this.f2559a.f2568e) {
                int width = this.f2559a.f2586w.getWidth() / 5;
                if (f > this.f2559a.f2581r) {
                    if (this.f2559a.f2587x > (-width)) {
                        this.f2559a.f2586w.m3196a(true, f);
                    }
                } else if (f < (-this.f2559a.f2581r) && this.f2559a.f2587x < width) {
                    this.f2559a.f2586w.m3196a(true, f);
                }
                this.f2559a.f2568e = false;
            }
            return false;
        }
    }

    public C0668a(DragSortListView dragSortListView, int i, int i2, int i3, int i4, int i5) {
        super(dragSortListView);
        this.f2564a = 0;
        this.f2565b = true;
        this.f2567d = false;
        this.f2568e = false;
        this.f2572i = -1;
        this.f2573j = -1;
        this.f2574k = -1;
        this.f2575l = new int[2];
        this.f2580q = false;
        this.f2581r = 500.0f;
        this.f2588y = new C06661(this);
        this.f2586w = dragSortListView;
        this.f2569f = new GestureDetector(dragSortListView.getContext(), this);
        this.f2570g = new GestureDetector(dragSortListView.getContext(), this.f2588y);
        this.f2570g.setIsLongpressEnabled(false);
        this.f2571h = ViewConfiguration.get(dragSortListView.getContext()).getScaledTouchSlop();
        this.f2582s = i;
        this.f2583t = i4;
        this.f2584u = i5;
        m3217b(i3);
        m3212a(i2);
    }

    public int m3210a(MotionEvent motionEvent) {
        return m3219c(motionEvent);
    }

    public int m3211a(MotionEvent motionEvent, int i) {
        int pointToPosition = this.f2586w.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        int headerViewsCount = this.f2586w.getHeaderViewsCount();
        int footerViewsCount = this.f2586w.getFooterViewsCount();
        int count = this.f2586w.getCount();
        if (pointToPosition != -1 && pointToPosition >= headerViewsCount && pointToPosition < count - footerViewsCount) {
            View childAt = this.f2586w.getChildAt(pointToPosition - this.f2586w.getFirstVisiblePosition());
            count = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            View findViewById = i == 0 ? childAt : childAt.findViewById(i);
            if (findViewById != null) {
                findViewById.getLocationOnScreen(this.f2575l);
                if (count > this.f2575l[0] && rawY > this.f2575l[1] && count < this.f2575l[0] + findViewById.getWidth()) {
                    if (rawY < findViewById.getHeight() + this.f2575l[1]) {
                        this.f2576m = childAt.getLeft();
                        this.f2577n = childAt.getTop();
                        return pointToPosition;
                    }
                }
            }
        }
        return -1;
    }

    public void m3212a(int i) {
        this.f2564a = i;
    }

    public void m3213a(View view, Point point, Point point2) {
        if (this.f2567d && this.f2568e) {
            this.f2587x = point.x;
        }
    }

    public void m3214a(boolean z) {
        this.f2565b = z;
    }

    public boolean m3215a(int i, int i2, int i3) {
        int i4 = 0;
        if (this.f2565b && !this.f2568e) {
            i4 = 12;
        }
        if (this.f2567d && this.f2568e) {
            i4 = (i4 | 1) | 2;
        }
        this.f2580q = this.f2586w.m3192a(i - this.f2586w.getHeaderViewsCount(), i4, i2, i3);
        return this.f2580q;
    }

    public int m3216b(MotionEvent motionEvent) {
        return this.f2566c == 1 ? m3220d(motionEvent) : -1;
    }

    public void m3217b(int i) {
        this.f2566c = i;
    }

    public void m3218b(boolean z) {
        this.f2567d = z;
    }

    public int m3219c(MotionEvent motionEvent) {
        return m3211a(motionEvent, this.f2582s);
    }

    public int m3220d(MotionEvent motionEvent) {
        return m3211a(motionEvent, this.f2584u);
    }

    public boolean onDown(MotionEvent motionEvent) {
        if (this.f2567d && this.f2566c == 0) {
            this.f2574k = m3211a(motionEvent, this.f2583t);
        }
        this.f2572i = m3210a(motionEvent);
        if (this.f2572i != -1 && this.f2564a == 0) {
            m3215a(this.f2572i, ((int) motionEvent.getX()) - this.f2576m, ((int) motionEvent.getY()) - this.f2577n);
        }
        this.f2568e = false;
        this.f2585v = true;
        this.f2587x = 0;
        this.f2573j = m3216b(motionEvent);
        return true;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.f2572i != -1 && this.f2564a == 2) {
            this.f2586w.performHapticFeedback(0);
            m3215a(this.f2572i, this.f2578o - this.f2576m, this.f2579p - this.f2577n);
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int x2 = (int) motionEvent2.getX();
        int y2 = (int) motionEvent2.getY();
        int i = x2 - this.f2576m;
        int i2 = y2 - this.f2577n;
        if (!(!this.f2585v || this.f2580q || (this.f2572i == -1 && this.f2573j == -1))) {
            if (this.f2572i != -1) {
                if (this.f2564a == 1 && Math.abs(y2 - y) > this.f2571h && this.f2565b) {
                    m3215a(this.f2572i, i, i2);
                } else if (this.f2564a != 0 && Math.abs(x2 - x) > this.f2571h && this.f2567d) {
                    this.f2568e = true;
                    m3215a(this.f2573j, i, i2);
                }
            } else if (this.f2573j != -1) {
                if (Math.abs(x2 - x) > this.f2571h && this.f2567d) {
                    this.f2568e = true;
                    m3215a(this.f2573j, i, i2);
                } else if (Math.abs(y2 - y) > this.f2571h) {
                    this.f2585v = false;
                }
            }
        }
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.f2567d && this.f2566c == 0 && this.f2574k != -1) {
            this.f2586w.m3190a(this.f2574k - this.f2586w.getHeaderViewsCount());
        }
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.f2586w.m3199c() && !this.f2586w.m3197b()) {
            this.f2569f.onTouchEvent(motionEvent);
            if (this.f2567d && this.f2580q && this.f2566c == 1) {
                this.f2570g.onTouchEvent(motionEvent);
            }
            switch (motionEvent.getAction() & 255) {
                case C0627j.View_android_focusable /*0*/:
                    this.f2578o = (int) motionEvent.getX();
                    this.f2579p = (int) motionEvent.getY();
                    break;
                case C0627j.View_paddingStart /*1*/:
                    if (this.f2567d && this.f2568e) {
                        if ((this.f2587x >= 0 ? this.f2587x : -this.f2587x) > this.f2586w.getWidth() / 2) {
                            this.f2586w.m3196a(true, 0.0f);
                            break;
                        }
                    }
                    break;
                case C0627j.Toolbar_subtitle /*3*/:
                    break;
                default:
                    break;
            }
            this.f2568e = false;
            this.f2580q = false;
        }
        return false;
    }
}
