package android.support.v4.p005d;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: android.support.v4.d.c */
public class C0084c {

    /* renamed from: android.support.v4.d.c.a */
    static class C0083a<T> implements Creator<T> {
        final C0085d<T> f254a;

        public C0083a(C0085d<T> c0085d) {
            this.f254a = c0085d;
        }

        public T createFromParcel(Parcel parcel) {
            return this.f254a.m386a(parcel, null);
        }

        public T[] newArray(int i) {
            return this.f254a.m387a(i);
        }
    }

    public static <T> Creator<T> m385a(C0085d<T> c0085d) {
        if (VERSION.SDK_INT >= 13) {
            C0087f.m388a(c0085d);
        }
        return new C0083a(c0085d);
    }
}
