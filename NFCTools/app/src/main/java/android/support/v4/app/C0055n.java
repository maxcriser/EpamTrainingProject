package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.C0053m.C0052a;
import android.support.v4.p000a.C0011g;
import android.support.v4.p000a.C0011g.C0010a;
import android.support.v4.p007f.C0099c;
import android.support.v4.p007f.C0107h;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* renamed from: android.support.v4.app.n */
class C0055n extends C0053m {
    static boolean f242a;
    final C0107h<C0054a> f243b;
    final C0107h<C0054a> f244c;
    final String f245d;
    C0037f f246e;
    boolean f247f;
    boolean f248g;

    /* renamed from: android.support.v4.app.n.a */
    final class C0054a implements C0010a<Object> {
        final int f227a;
        final Bundle f228b;
        C0052a<Object> f229c;
        C0011g<Object> f230d;
        boolean f231e;
        boolean f232f;
        Object f233g;
        boolean f234h;
        boolean f235i;
        boolean f236j;
        boolean f237k;
        boolean f238l;
        boolean f239m;
        C0054a f240n;
        final /* synthetic */ C0055n f241o;

        void m291a() {
            if (this.f235i && this.f236j) {
                this.f234h = true;
            } else if (!this.f234h) {
                this.f234h = true;
                if (C0055n.f242a) {
                    Log.v("LoaderManager", "  Starting: " + this);
                }
                if (this.f230d == null && this.f229c != null) {
                    this.f230d = this.f229c.m287a(this.f227a, this.f228b);
                }
                if (this.f230d == null) {
                    return;
                }
                if (!this.f230d.getClass().isMemberClass() || Modifier.isStatic(this.f230d.getClass().getModifiers())) {
                    if (!this.f239m) {
                        this.f230d.m12a(this.f227a, this);
                        this.f239m = true;
                    }
                    this.f230d.m11a();
                    return;
                }
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.f230d);
            }
        }

        void m292a(C0011g<Object> c0011g, Object obj) {
            String str;
            if (this.f229c != null) {
                if (this.f241o.f246e != null) {
                    String str2 = this.f241o.f246e.f157b.f197u;
                    this.f241o.f246e.f157b.f197u = "onLoadFinished";
                    str = str2;
                } else {
                    str = null;
                }
                try {
                    if (C0055n.f242a) {
                        Log.v("LoaderManager", "  onLoadFinished in " + c0011g + ": " + c0011g.m10a(obj));
                    }
                    this.f229c.m289a((C0011g) c0011g, obj);
                    this.f232f = true;
                } finally {
                    if (this.f241o.f246e != null) {
                        this.f241o.f246e.f157b.f197u = str;
                    }
                }
            }
        }

        public void m293a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f227a);
            printWriter.print(" mArgs=");
            printWriter.println(this.f228b);
            printWriter.print(str);
            printWriter.print("mCallbacks=");
            printWriter.println(this.f229c);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f230d);
            if (this.f230d != null) {
                this.f230d.m14a(str + "  ", fileDescriptor, printWriter, strArr);
            }
            if (this.f231e || this.f232f) {
                printWriter.print(str);
                printWriter.print("mHaveData=");
                printWriter.print(this.f231e);
                printWriter.print("  mDeliveredData=");
                printWriter.println(this.f232f);
                printWriter.print(str);
                printWriter.print("mData=");
                printWriter.println(this.f233g);
            }
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f234h);
            printWriter.print(" mReportNextStart=");
            printWriter.print(this.f237k);
            printWriter.print(" mDestroyed=");
            printWriter.println(this.f238l);
            printWriter.print(str);
            printWriter.print("mRetaining=");
            printWriter.print(this.f235i);
            printWriter.print(" mRetainingStarted=");
            printWriter.print(this.f236j);
            printWriter.print(" mListenerRegistered=");
            printWriter.println(this.f239m);
            if (this.f240n != null) {
                printWriter.print(str);
                printWriter.println("Pending Loader ");
                printWriter.print(this.f240n);
                printWriter.println(":");
                this.f240n.m293a(str + "  ", fileDescriptor, printWriter, strArr);
            }
        }

        void m294b() {
            if (C0055n.f242a) {
                Log.v("LoaderManager", "  Retaining: " + this);
            }
            this.f235i = true;
            this.f236j = this.f234h;
            this.f234h = false;
            this.f229c = null;
        }

        void m295c() {
            if (this.f235i) {
                if (C0055n.f242a) {
                    Log.v("LoaderManager", "  Finished Retaining: " + this);
                }
                this.f235i = false;
                if (!(this.f234h == this.f236j || this.f234h)) {
                    m297e();
                }
            }
            if (this.f234h && this.f231e && !this.f237k) {
                m292a(this.f230d, this.f233g);
            }
        }

        void m296d() {
            if (this.f234h && this.f237k) {
                this.f237k = false;
                if (this.f231e) {
                    m292a(this.f230d, this.f233g);
                }
            }
        }

        void m297e() {
            if (C0055n.f242a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.f234h = false;
            if (!this.f235i && this.f230d != null && this.f239m) {
                this.f239m = false;
                this.f230d.m13a((C0010a) this);
                this.f230d.m16c();
            }
        }

        void m298f() {
            String str;
            C0052a c0052a = null;
            if (C0055n.f242a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.f238l = true;
            boolean z = this.f232f;
            this.f232f = false;
            if (this.f229c != null && this.f230d != null && this.f231e && z) {
                if (C0055n.f242a) {
                    Log.v("LoaderManager", "  Reseting: " + this);
                }
                if (this.f241o.f246e != null) {
                    String str2 = this.f241o.f246e.f157b.f197u;
                    this.f241o.f246e.f157b.f197u = "onLoaderReset";
                    str = str2;
                } else {
                    str = null;
                }
                try {
                    this.f229c.m288a(this.f230d);
                } finally {
                    c0052a = this.f241o.f246e;
                    if (c0052a != null) {
                        c0052a = this.f241o.f246e.f157b;
                        c0052a.f197u = str;
                    }
                }
            }
            this.f229c = c0052a;
            this.f233g = c0052a;
            this.f231e = false;
            if (this.f230d != null) {
                if (this.f239m) {
                    this.f239m = false;
                    this.f230d.m13a((C0010a) this);
                }
                this.f230d.m18e();
            }
            if (this.f240n != null) {
                this.f240n.m298f();
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append("LoaderInfo{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" #");
            stringBuilder.append(this.f227a);
            stringBuilder.append(" : ");
            C0099c.m445a(this.f230d, stringBuilder);
            stringBuilder.append("}}");
            return stringBuilder.toString();
        }
    }

    static {
        f242a = false;
    }

    C0055n(String str, C0037f c0037f, boolean z) {
        this.f243b = new C0107h();
        this.f244c = new C0107h();
        this.f245d = str;
        this.f246e = c0037f;
        this.f247f = z;
    }

    void m299a(C0037f c0037f) {
        this.f246e = c0037f;
    }

    public void m300a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        if (this.f243b.m459b() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            String str2 = str + "    ";
            for (int i2 = 0; i2 < this.f243b.m459b(); i2++) {
                C0054a c0054a = (C0054a) this.f243b.m460b(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f243b.m457a(i2));
                printWriter.print(": ");
                printWriter.println(c0054a.toString());
                c0054a.m293a(str2, fileDescriptor, printWriter, strArr);
            }
        }
        if (this.f244c.m459b() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            String str3 = str + "    ";
            while (i < this.f244c.m459b()) {
                c0054a = (C0054a) this.f244c.m460b(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f244c.m457a(i));
                printWriter.print(": ");
                printWriter.println(c0054a.toString());
                c0054a.m293a(str3, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    public boolean m301a() {
        int b = this.f243b.m459b();
        boolean z = false;
        for (int i = 0; i < b; i++) {
            C0054a c0054a = (C0054a) this.f243b.m460b(i);
            int i2 = (!c0054a.f234h || c0054a.f232f) ? 0 : 1;
            z |= i2;
        }
        return z;
    }

    void m302b() {
        if (f242a) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.f247f) {
            Throwable runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
            return;
        }
        this.f247f = true;
        for (int b = this.f243b.m459b() - 1; b >= 0; b--) {
            ((C0054a) this.f243b.m460b(b)).m291a();
        }
    }

    void m303c() {
        if (f242a) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (this.f247f) {
            for (int b = this.f243b.m459b() - 1; b >= 0; b--) {
                ((C0054a) this.f243b.m460b(b)).m297e();
            }
            this.f247f = false;
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
    }

    void m304d() {
        if (f242a) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (this.f247f) {
            this.f248g = true;
            this.f247f = false;
            for (int b = this.f243b.m459b() - 1; b >= 0; b--) {
                ((C0054a) this.f243b.m460b(b)).m294b();
            }
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
    }

    void m305e() {
        if (this.f248g) {
            if (f242a) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.f248g = false;
            for (int b = this.f243b.m459b() - 1; b >= 0; b--) {
                ((C0054a) this.f243b.m460b(b)).m295c();
            }
        }
    }

    void m306f() {
        for (int b = this.f243b.m459b() - 1; b >= 0; b--) {
            ((C0054a) this.f243b.m460b(b)).f237k = true;
        }
    }

    void m307g() {
        for (int b = this.f243b.m459b() - 1; b >= 0; b--) {
            ((C0054a) this.f243b.m460b(b)).m296d();
        }
    }

    void m308h() {
        int b;
        if (!this.f248g) {
            if (f242a) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (b = this.f243b.m459b() - 1; b >= 0; b--) {
                ((C0054a) this.f243b.m460b(b)).m298f();
            }
            this.f243b.m461c();
        }
        if (f242a) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (b = this.f244c.m459b() - 1; b >= 0; b--) {
            ((C0054a) this.f244c.m460b(b)).m298f();
        }
        this.f244c.m461c();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("LoaderManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        C0099c.m445a(this.f246e, stringBuilder);
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }
}
