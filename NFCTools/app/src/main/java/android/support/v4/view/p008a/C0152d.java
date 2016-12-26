package android.support.v4.view.p008a;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.p008a.C0154e.C0145a;
import android.support.v4.view.p008a.C0156f.C0149a;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.view.a.d */
public class C0152d {
    private static final C0144a f395a;
    private final Object f396b;

    /* renamed from: android.support.v4.view.a.d.a */
    interface C0144a {
        Object m712a(C0152d c0152d);
    }

    /* renamed from: android.support.v4.view.a.d.d */
    static class C0147d implements C0144a {
        C0147d() {
        }

        public Object m719a(C0152d c0152d) {
            return null;
        }
    }

    /* renamed from: android.support.v4.view.a.d.b */
    static class C0148b extends C0147d {

        /* renamed from: android.support.v4.view.a.d.b.1 */
        class C01461 implements C0145a {
            final /* synthetic */ C0152d f391a;
            final /* synthetic */ C0148b f392b;

            C01461(C0148b c0148b, C0152d c0152d) {
                this.f392b = c0148b;
                this.f391a = c0152d;
            }

            public Object m716a(int i) {
                C0141a a = this.f391a.m730a(i);
                return a == null ? null : a.m667a();
            }

            public List<Object> m717a(String str, int i) {
                List a = this.f391a.m732a(str, i);
                List<Object> arrayList = new ArrayList();
                int size = a.size();
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(((C0141a) a.get(i2)).m667a());
                }
                return arrayList;
            }

            public boolean m718a(int i, int i2, Bundle bundle) {
                return this.f391a.m733a(i, i2, bundle);
            }
        }

        C0148b() {
        }

        public Object m720a(C0152d c0152d) {
            return C0154e.m735a(new C01461(this, c0152d));
        }
    }

    /* renamed from: android.support.v4.view.a.d.c */
    static class C0151c extends C0147d {

        /* renamed from: android.support.v4.view.a.d.c.1 */
        class C01501 implements C0149a {
            final /* synthetic */ C0152d f393a;
            final /* synthetic */ C0151c f394b;

            C01501(C0151c c0151c, C0152d c0152d) {
                this.f394b = c0151c;
                this.f393a = c0152d;
            }

            public Object m725a(int i) {
                C0141a a = this.f393a.m730a(i);
                return a == null ? null : a.m667a();
            }

            public List<Object> m726a(String str, int i) {
                List a = this.f393a.m732a(str, i);
                List<Object> arrayList = new ArrayList();
                int size = a.size();
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(((C0141a) a.get(i2)).m667a());
                }
                return arrayList;
            }

            public boolean m727a(int i, int i2, Bundle bundle) {
                return this.f393a.m733a(i, i2, bundle);
            }

            public Object m728b(int i) {
                C0141a b = this.f393a.m734b(i);
                return b == null ? null : b.m667a();
            }
        }

        C0151c() {
        }

        public Object m729a(C0152d c0152d) {
            return C0156f.m736a(new C01501(this, c0152d));
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            f395a = new C0151c();
        } else if (VERSION.SDK_INT >= 16) {
            f395a = new C0148b();
        } else {
            f395a = new C0147d();
        }
    }

    public C0152d() {
        this.f396b = f395a.m712a(this);
    }

    public C0152d(Object obj) {
        this.f396b = obj;
    }

    public C0141a m730a(int i) {
        return null;
    }

    public Object m731a() {
        return this.f396b;
    }

    public List<C0141a> m732a(String str, int i) {
        return null;
    }

    public boolean m733a(int i, int i2, Bundle bundle) {
        return false;
    }

    public C0141a m734b(int i) {
        return null;
    }
}
