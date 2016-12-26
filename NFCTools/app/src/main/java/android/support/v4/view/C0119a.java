package android.support.v4.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.C0182b.C0126a;
import android.support.v4.view.C0184c.C0131a;
import android.support.v4.view.p008a.C0141a;
import android.support.v4.view.p008a.C0152d;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: android.support.v4.view.a */
public class C0119a {
    private static final C0128b f331b;
    private static final Object f332c;
    final Object f333a;

    /* renamed from: android.support.v4.view.a.b */
    interface C0128b {
        C0152d m548a(Object obj, View view);

        Object m549a();

        Object m550a(C0119a c0119a);

        void m551a(Object obj, View view, int i);

        void m552a(Object obj, View view, C0141a c0141a);

        boolean m553a(Object obj, View view, int i, Bundle bundle);

        boolean m554a(Object obj, View view, AccessibilityEvent accessibilityEvent);

        boolean m555a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void m556b(Object obj, View view, AccessibilityEvent accessibilityEvent);

        void m557c(Object obj, View view, AccessibilityEvent accessibilityEvent);

        void m558d(Object obj, View view, AccessibilityEvent accessibilityEvent);
    }

    /* renamed from: android.support.v4.view.a.d */
    static class C0129d implements C0128b {
        C0129d() {
        }

        public C0152d m559a(Object obj, View view) {
            return null;
        }

        public Object m560a() {
            return null;
        }

        public Object m561a(C0119a c0119a) {
            return null;
        }

        public void m562a(Object obj, View view, int i) {
        }

        public void m563a(Object obj, View view, C0141a c0141a) {
        }

        public boolean m564a(Object obj, View view, int i, Bundle bundle) {
            return false;
        }

        public boolean m565a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            return false;
        }

        public boolean m566a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return true;
        }

        public void m567b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        public void m568c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        public void m569d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }
    }

    /* renamed from: android.support.v4.view.a.a */
    static class C0130a extends C0129d {

        /* renamed from: android.support.v4.view.a.a.1 */
        class C01271 implements C0126a {
            final /* synthetic */ C0119a f385a;
            final /* synthetic */ C0130a f386b;

            C01271(C0130a c0130a, C0119a c0119a) {
                this.f386b = c0130a;
                this.f385a = c0119a;
            }

            public void m541a(View view, int i) {
                this.f385a.m477a(view, i);
            }

            public void m542a(View view, Object obj) {
                this.f385a.m478a(view, new C0141a(obj));
            }

            public boolean m543a(View view, AccessibilityEvent accessibilityEvent) {
                return this.f385a.m482b(view, accessibilityEvent);
            }

            public boolean m544a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return this.f385a.m481a(viewGroup, view, accessibilityEvent);
            }

            public void m545b(View view, AccessibilityEvent accessibilityEvent) {
                this.f385a.m484d(view, accessibilityEvent);
            }

            public void m546c(View view, AccessibilityEvent accessibilityEvent) {
                this.f385a.m483c(view, accessibilityEvent);
            }

            public void m547d(View view, AccessibilityEvent accessibilityEvent) {
                this.f385a.m479a(view, accessibilityEvent);
            }
        }

        C0130a() {
        }

        public Object m570a() {
            return C0182b.m857a();
        }

        public Object m571a(C0119a c0119a) {
            return C0182b.m858a(new C01271(this, c0119a));
        }

        public void m572a(Object obj, View view, int i) {
            C0182b.m859a(obj, view, i);
        }

        public void m573a(Object obj, View view, C0141a c0141a) {
            C0182b.m860a(obj, view, c0141a.m667a());
        }

        public boolean m574a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            return C0182b.m861a(obj, view, accessibilityEvent);
        }

        public boolean m575a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return C0182b.m862a(obj, viewGroup, view, accessibilityEvent);
        }

        public void m576b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            C0182b.m863b(obj, view, accessibilityEvent);
        }

        public void m577c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            C0182b.m864c(obj, view, accessibilityEvent);
        }

        public void m578d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            C0182b.m865d(obj, view, accessibilityEvent);
        }
    }

    /* renamed from: android.support.v4.view.a.c */
    static class C0133c extends C0130a {

        /* renamed from: android.support.v4.view.a.c.1 */
        class C01321 implements C0131a {
            final /* synthetic */ C0119a f387a;
            final /* synthetic */ C0133c f388b;

            C01321(C0133c c0133c, C0119a c0119a) {
                this.f388b = c0133c;
                this.f387a = c0119a;
            }

            public Object m588a(View view) {
                C0152d a = this.f387a.m475a(view);
                return a != null ? a.m731a() : null;
            }

            public void m589a(View view, int i) {
                this.f387a.m477a(view, i);
            }

            public void m590a(View view, Object obj) {
                this.f387a.m478a(view, new C0141a(obj));
            }

            public boolean m591a(View view, int i, Bundle bundle) {
                return this.f387a.m480a(view, i, bundle);
            }

            public boolean m592a(View view, AccessibilityEvent accessibilityEvent) {
                return this.f387a.m482b(view, accessibilityEvent);
            }

            public boolean m593a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return this.f387a.m481a(viewGroup, view, accessibilityEvent);
            }

            public void m594b(View view, AccessibilityEvent accessibilityEvent) {
                this.f387a.m484d(view, accessibilityEvent);
            }

            public void m595c(View view, AccessibilityEvent accessibilityEvent) {
                this.f387a.m483c(view, accessibilityEvent);
            }

            public void m596d(View view, AccessibilityEvent accessibilityEvent) {
                this.f387a.m479a(view, accessibilityEvent);
            }
        }

        C0133c() {
        }

        public C0152d m597a(Object obj, View view) {
            Object a = C0184c.m867a(obj, view);
            return a != null ? new C0152d(a) : null;
        }

        public Object m598a(C0119a c0119a) {
            return C0184c.m866a(new C01321(this, c0119a));
        }

        public boolean m599a(Object obj, View view, int i, Bundle bundle) {
            return C0184c.m868a(obj, view, i, bundle);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f331b = new C0133c();
        } else if (VERSION.SDK_INT >= 14) {
            f331b = new C0130a();
        } else {
            f331b = new C0129d();
        }
        f332c = f331b.m549a();
    }

    public C0119a() {
        this.f333a = f331b.m550a(this);
    }

    public C0152d m475a(View view) {
        return f331b.m548a(f332c, view);
    }

    Object m476a() {
        return this.f333a;
    }

    public void m477a(View view, int i) {
        f331b.m551a(f332c, view, i);
    }

    public void m478a(View view, C0141a c0141a) {
        f331b.m552a(f332c, view, c0141a);
    }

    public void m479a(View view, AccessibilityEvent accessibilityEvent) {
        f331b.m558d(f332c, view, accessibilityEvent);
    }

    public boolean m480a(View view, int i, Bundle bundle) {
        return f331b.m553a(f332c, view, i, bundle);
    }

    public boolean m481a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return f331b.m555a(f332c, viewGroup, view, accessibilityEvent);
    }

    public boolean m482b(View view, AccessibilityEvent accessibilityEvent) {
        return f331b.m554a(f332c, view, accessibilityEvent);
    }

    public void m483c(View view, AccessibilityEvent accessibilityEvent) {
        f331b.m557c(f332c, view, accessibilityEvent);
    }

    public void m484d(View view, AccessibilityEvent accessibilityEvent) {
        f331b.m556b(f332c, view, accessibilityEvent);
    }
}
