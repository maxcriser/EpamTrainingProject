package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class RatingCompat implements Parcelable {
    public static final Creator<RatingCompat> CREATOR;
    private final int f306a;
    private final float f307b;

    /* renamed from: android.support.v4.media.RatingCompat.1 */
    static class C01091 implements Creator<RatingCompat> {
        C01091() {
        }

        public RatingCompat m464a(Parcel parcel) {
            return new RatingCompat(parcel.readFloat(), null);
        }

        public RatingCompat[] m465a(int i) {
            return new RatingCompat[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m464a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m465a(i);
        }
    }

    static {
        CREATOR = new C01091();
    }

    private RatingCompat(int i, float f) {
        this.f306a = i;
        this.f307b = f;
    }

    public int describeContents() {
        return this.f306a;
    }

    public String toString() {
        return "Rating:style=" + this.f306a + " rating=" + (this.f307b < 0.0f ? "unrated" : String.valueOf(this.f307b));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f306a);
        parcel.writeFloat(this.f307b);
    }
}
