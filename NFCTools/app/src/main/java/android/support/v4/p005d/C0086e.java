package android.support.v4.p005d;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

/* renamed from: android.support.v4.d.e */
class C0086e<T> implements ClassLoaderCreator<T> {
    private final C0085d<T> f255a;

    public C0086e(C0085d<T> c0085d) {
        this.f255a = c0085d;
    }

    public T createFromParcel(Parcel parcel) {
        return this.f255a.m386a(parcel, null);
    }

    public T createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return this.f255a.m386a(parcel, classLoader);
    }

    public T[] newArray(int i) {
        return this.f255a.m387a(i);
    }
}
