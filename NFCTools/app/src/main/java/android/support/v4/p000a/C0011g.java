package android.support.v4.p000a;

import android.support.v4.p007f.C0099c;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: android.support.v4.a.g */
public class C0011g<D> {
    int f1a;
    C0010a<D> f2b;
    boolean f3c;
    boolean f4d;
    boolean f5e;
    boolean f6f;
    boolean f7g;

    /* renamed from: android.support.v4.a.g.a */
    public interface C0010a<D> {
    }

    public String m10a(D d) {
        StringBuilder stringBuilder = new StringBuilder(64);
        C0099c.m445a(d, stringBuilder);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public final void m11a() {
        this.f3c = true;
        this.f5e = false;
        this.f4d = false;
        m15b();
    }

    public void m12a(int i, C0010a<D> c0010a) {
        if (this.f2b != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.f2b = c0010a;
        this.f1a = i;
    }

    public void m13a(C0010a<D> c0010a) {
        if (this.f2b == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.f2b != c0010a) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.f2b = null;
        }
    }

    public void m14a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.f1a);
        printWriter.print(" mListener=");
        printWriter.println(this.f2b);
        if (this.f3c || this.f6f || this.f7g) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f3c);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.f6f);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.f7g);
        }
        if (this.f4d || this.f5e) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.f4d);
            printWriter.print(" mReset=");
            printWriter.println(this.f5e);
        }
    }

    protected void m15b() {
    }

    public void m16c() {
        this.f3c = false;
        m17d();
    }

    protected void m17d() {
    }

    public void m18e() {
        m19f();
        this.f5e = true;
        this.f3c = false;
        this.f4d = false;
        this.f6f = false;
        this.f7g = false;
    }

    protected void m19f() {
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        C0099c.m445a(this, stringBuilder);
        stringBuilder.append(" id=");
        stringBuilder.append(this.f1a);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
