package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentManagerState implements Parcelable {
    public static final Creator<FragmentManagerState> CREATOR;
    FragmentState[] f73a;
    int[] f74b;
    BackStackState[] f75c;

    /* renamed from: android.support.v4.app.FragmentManagerState.1 */
    static class C00171 implements Creator<FragmentManagerState> {
        C00171() {
        }

        public FragmentManagerState m100a(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        public FragmentManagerState[] m101a(int i) {
            return new FragmentManagerState[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m100a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m101a(i);
        }
    }

    static {
        CREATOR = new C00171();
    }

    public FragmentManagerState(Parcel parcel) {
        this.f73a = (FragmentState[]) parcel.createTypedArray(FragmentState.CREATOR);
        this.f74b = parcel.createIntArray();
        this.f75c = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.f73a, i);
        parcel.writeIntArray(this.f74b);
        parcel.writeTypedArray(this.f75c, i);
    }
}
