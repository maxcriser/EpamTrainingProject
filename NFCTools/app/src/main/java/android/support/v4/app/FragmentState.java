package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR;
    final String f76a;
    final int f77b;
    final boolean f78c;
    final int f79d;
    final int f80e;
    final String f81f;
    final boolean f82g;
    final boolean f83h;
    final Bundle f84i;
    Bundle f85j;
    Fragment f86k;

    /* renamed from: android.support.v4.app.FragmentState.1 */
    static class C00181 implements Creator<FragmentState> {
        C00181() {
        }

        public FragmentState m102a(Parcel parcel) {
            return new FragmentState(parcel);
        }

        public FragmentState[] m103a(int i) {
            return new FragmentState[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m102a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m103a(i);
        }
    }

    static {
        CREATOR = new C00181();
    }

    public FragmentState(Parcel parcel) {
        boolean z = true;
        this.f76a = parcel.readString();
        this.f77b = parcel.readInt();
        this.f78c = parcel.readInt() != 0;
        this.f79d = parcel.readInt();
        this.f80e = parcel.readInt();
        this.f81f = parcel.readString();
        this.f82g = parcel.readInt() != 0;
        if (parcel.readInt() == 0) {
            z = false;
        }
        this.f83h = z;
        this.f84i = parcel.readBundle();
        this.f85j = parcel.readBundle();
    }

    public FragmentState(Fragment fragment) {
        this.f76a = fragment.getClass().getName();
        this.f77b = fragment.f53g;
        this.f78c = fragment.f62p;
        this.f79d = fragment.f70x;
        this.f80e = fragment.f71y;
        this.f81f = fragment.f72z;
        this.f82g = fragment.f24C;
        this.f83h = fragment.f23B;
        this.f84i = fragment.f55i;
    }

    public Fragment m104a(C0037f c0037f, Fragment fragment) {
        if (this.f86k != null) {
            return this.f86k;
        }
        if (this.f84i != null) {
            this.f84i.setClassLoader(c0037f.getClassLoader());
        }
        this.f86k = Fragment.m30a((Context) c0037f, this.f76a, this.f84i);
        if (this.f85j != null) {
            this.f85j.setClassLoader(c0037f.getClassLoader());
            this.f86k.f51e = this.f85j;
        }
        this.f86k.m44a(this.f77b, fragment);
        this.f86k.f62p = this.f78c;
        this.f86k.f64r = true;
        this.f86k.f70x = this.f79d;
        this.f86k.f71y = this.f80e;
        this.f86k.f72z = this.f81f;
        this.f86k.f24C = this.f82g;
        this.f86k.f23B = this.f83h;
        this.f86k.f66t = c0037f.f157b;
        if (C0043i.f176a) {
            Log.v("FragmentManager", "Instantiated fragment " + this.f86k);
        }
        return this.f86k;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(this.f76a);
        parcel.writeInt(this.f77b);
        parcel.writeInt(this.f78c ? 1 : 0);
        parcel.writeInt(this.f79d);
        parcel.writeInt(this.f80e);
        parcel.writeString(this.f81f);
        parcel.writeInt(this.f82g ? 1 : 0);
        if (!this.f83h) {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeBundle(this.f84i);
        parcel.writeBundle(this.f85j);
    }
}
