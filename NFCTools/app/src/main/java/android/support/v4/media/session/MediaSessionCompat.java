package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MediaSessionCompat {

    public static final class Token implements Parcelable {
        public static final Creator<Token> CREATOR;
        private final Parcelable f308a;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat.Token.1 */
        static class C01101 implements Creator<Token> {
            C01101() {
            }

            public Token m466a(Parcel parcel) {
                return new Token(parcel.readParcelable(null));
            }

            public Token[] m467a(int i) {
                return new Token[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m466a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m467a(i);
            }
        }

        static {
            CREATOR = new C01101();
        }

        Token(Parcelable parcelable) {
            this.f308a = parcelable;
        }

        public int describeContents() {
            return this.f308a.describeContents();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f308a, i);
        }
    }
}
