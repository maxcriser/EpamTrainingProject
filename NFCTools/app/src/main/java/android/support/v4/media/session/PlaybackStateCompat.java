package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

public final class PlaybackStateCompat implements Parcelable {
    public static final Creator<PlaybackStateCompat> CREATOR;
    private final int f309a;
    private final long f310b;
    private final long f311c;
    private final float f312d;
    private final long f313e;
    private final CharSequence f314f;
    private final long f315g;

    /* renamed from: android.support.v4.media.session.PlaybackStateCompat.1 */
    static class C01111 implements Creator<PlaybackStateCompat> {
        C01111() {
        }

        public PlaybackStateCompat m468a(Parcel parcel) {
            return new PlaybackStateCompat(null);
        }

        public PlaybackStateCompat[] m469a(int i) {
            return new PlaybackStateCompat[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m468a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m469a(i);
        }
    }

    static {
        CREATOR = new C01111();
    }

    private PlaybackStateCompat(Parcel parcel) {
        this.f309a = parcel.readInt();
        this.f310b = parcel.readLong();
        this.f312d = parcel.readFloat();
        this.f315g = parcel.readLong();
        this.f311c = parcel.readLong();
        this.f313e = parcel.readLong();
        this.f314f = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PlaybackState {");
        stringBuilder.append("state=").append(this.f309a);
        stringBuilder.append(", position=").append(this.f310b);
        stringBuilder.append(", buffered position=").append(this.f311c);
        stringBuilder.append(", speed=").append(this.f312d);
        stringBuilder.append(", updated=").append(this.f315g);
        stringBuilder.append(", actions=").append(this.f313e);
        stringBuilder.append(", error=").append(this.f314f);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f309a);
        parcel.writeLong(this.f310b);
        parcel.writeFloat(this.f312d);
        parcel.writeLong(this.f315g);
        parcel.writeLong(this.f311c);
        parcel.writeLong(this.f313e);
        TextUtils.writeToParcel(this.f314f, parcel, i);
    }
}
