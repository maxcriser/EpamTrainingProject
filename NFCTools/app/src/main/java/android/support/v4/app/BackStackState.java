package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.C0033e.C0030a;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
    public static final Creator<BackStackState> CREATOR;
    final int[] f8a;
    final int f9b;
    final int f10c;
    final String f11d;
    final int f12e;
    final int f13f;
    final CharSequence f14g;
    final int f15h;
    final CharSequence f16i;
    final ArrayList<String> f17j;
    final ArrayList<String> f18k;

    /* renamed from: android.support.v4.app.BackStackState.1 */
    static class C00121 implements Creator<BackStackState> {
        C00121() {
        }

        public BackStackState m20a(Parcel parcel) {
            return new BackStackState(parcel);
        }

        public BackStackState[] m21a(int i) {
            return new BackStackState[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m20a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m21a(i);
        }
    }

    static {
        CREATOR = new C00121();
    }

    public BackStackState(Parcel parcel) {
        this.f8a = parcel.createIntArray();
        this.f9b = parcel.readInt();
        this.f10c = parcel.readInt();
        this.f11d = parcel.readString();
        this.f12e = parcel.readInt();
        this.f13f = parcel.readInt();
        this.f14g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f15h = parcel.readInt();
        this.f16i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f17j = parcel.createStringArrayList();
        this.f18k = parcel.createStringArrayList();
    }

    public BackStackState(C0043i c0043i, C0033e c0033e) {
        int i = 0;
        for (C0030a c0030a = c0033e.f129b; c0030a != null; c0030a = c0030a.f114a) {
            if (c0030a.f122i != null) {
                i += c0030a.f122i.size();
            }
        }
        this.f8a = new int[(i + (c0033e.f131d * 7))];
        if (c0033e.f138k) {
            i = 0;
            for (C0030a c0030a2 = c0033e.f129b; c0030a2 != null; c0030a2 = c0030a2.f114a) {
                int i2 = i + 1;
                this.f8a[i] = c0030a2.f116c;
                int i3 = i2 + 1;
                this.f8a[i2] = c0030a2.f117d != null ? c0030a2.f117d.f53g : -1;
                int i4 = i3 + 1;
                this.f8a[i3] = c0030a2.f118e;
                i2 = i4 + 1;
                this.f8a[i4] = c0030a2.f119f;
                i4 = i2 + 1;
                this.f8a[i2] = c0030a2.f120g;
                i2 = i4 + 1;
                this.f8a[i4] = c0030a2.f121h;
                if (c0030a2.f122i != null) {
                    int size = c0030a2.f122i.size();
                    i4 = i2 + 1;
                    this.f8a[i2] = size;
                    i2 = 0;
                    while (i2 < size) {
                        i3 = i4 + 1;
                        this.f8a[i4] = ((Fragment) c0030a2.f122i.get(i2)).f53g;
                        i2++;
                        i4 = i3;
                    }
                    i = i4;
                } else {
                    i = i2 + 1;
                    this.f8a[i2] = 0;
                }
            }
            this.f9b = c0033e.f136i;
            this.f10c = c0033e.f137j;
            this.f11d = c0033e.f140m;
            this.f12e = c0033e.f142o;
            this.f13f = c0033e.f143p;
            this.f14g = c0033e.f144q;
            this.f15h = c0033e.f145r;
            this.f16i = c0033e.f146s;
            this.f17j = c0033e.f147t;
            this.f18k = c0033e.f148u;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public C0033e m22a(C0043i c0043i) {
        C0033e c0033e = new C0033e(c0043i);
        int i = 0;
        int i2 = 0;
        while (i2 < this.f8a.length) {
            C0030a c0030a = new C0030a();
            int i3 = i2 + 1;
            c0030a.f116c = this.f8a[i2];
            if (C0043i.f176a) {
                Log.v("FragmentManager", "Instantiate " + c0033e + " op #" + i + " base fragment #" + this.f8a[i3]);
            }
            int i4 = i3 + 1;
            i2 = this.f8a[i3];
            if (i2 >= 0) {
                c0030a.f117d = (Fragment) c0043i.f182f.get(i2);
            } else {
                c0030a.f117d = null;
            }
            i3 = i4 + 1;
            c0030a.f118e = this.f8a[i4];
            i4 = i3 + 1;
            c0030a.f119f = this.f8a[i3];
            i3 = i4 + 1;
            c0030a.f120g = this.f8a[i4];
            int i5 = i3 + 1;
            c0030a.f121h = this.f8a[i3];
            i4 = i5 + 1;
            int i6 = this.f8a[i5];
            if (i6 > 0) {
                c0030a.f122i = new ArrayList(i6);
                i3 = 0;
                while (i3 < i6) {
                    if (C0043i.f176a) {
                        Log.v("FragmentManager", "Instantiate " + c0033e + " set remove fragment #" + this.f8a[i4]);
                    }
                    i5 = i4 + 1;
                    c0030a.f122i.add((Fragment) c0043i.f182f.get(this.f8a[i4]));
                    i3++;
                    i4 = i5;
                }
            }
            c0033e.m158a(c0030a);
            i++;
            i2 = i4;
        }
        c0033e.f136i = this.f9b;
        c0033e.f137j = this.f10c;
        c0033e.f140m = this.f11d;
        c0033e.f142o = this.f12e;
        c0033e.f138k = true;
        c0033e.f143p = this.f13f;
        c0033e.f144q = this.f14g;
        c0033e.f145r = this.f15h;
        c0033e.f146s = this.f16i;
        c0033e.f147t = this.f17j;
        c0033e.f148u = this.f18k;
        c0033e.m157a(1);
        return c0033e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f8a);
        parcel.writeInt(this.f9b);
        parcel.writeInt(this.f10c);
        parcel.writeString(this.f11d);
        parcel.writeInt(this.f12e);
        parcel.writeInt(this.f13f);
        TextUtils.writeToParcel(this.f14g, parcel, 0);
        parcel.writeInt(this.f15h);
        TextUtils.writeToParcel(this.f16i, parcel, 0);
        parcel.writeStringList(this.f17j);
        parcel.writeStringList(this.f18k);
    }
}
