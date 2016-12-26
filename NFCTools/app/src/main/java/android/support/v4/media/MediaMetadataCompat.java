package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.p007f.C0097a;

public final class MediaMetadataCompat implements Parcelable {
    public static final Creator<MediaMetadataCompat> CREATOR;
    private static final C0097a<String, Integer> f304a;
    private final Bundle f305b;

    /* renamed from: android.support.v4.media.MediaMetadataCompat.1 */
    static class C01081 implements Creator<MediaMetadataCompat> {
        C01081() {
        }

        public MediaMetadataCompat m462a(Parcel parcel) {
            return new MediaMetadataCompat(null);
        }

        public MediaMetadataCompat[] m463a(int i) {
            return new MediaMetadataCompat[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m462a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m463a(i);
        }
    }

    static {
        f304a = new C0097a();
        f304a.put("android.media.metadata.TITLE", Integer.valueOf(1));
        f304a.put("android.media.metadata.ARTIST", Integer.valueOf(1));
        f304a.put("android.media.metadata.DURATION", Integer.valueOf(0));
        f304a.put("android.media.metadata.ALBUM", Integer.valueOf(1));
        f304a.put("android.media.metadata.AUTHOR", Integer.valueOf(1));
        f304a.put("android.media.metadata.WRITER", Integer.valueOf(1));
        f304a.put("android.media.metadata.COMPOSER", Integer.valueOf(1));
        f304a.put("android.media.metadata.COMPILATION", Integer.valueOf(1));
        f304a.put("android.media.metadata.DATE", Integer.valueOf(1));
        f304a.put("android.media.metadata.YEAR", Integer.valueOf(0));
        f304a.put("android.media.metadata.GENRE", Integer.valueOf(1));
        f304a.put("android.media.metadata.TRACK_NUMBER", Integer.valueOf(0));
        f304a.put("android.media.metadata.NUM_TRACKS", Integer.valueOf(0));
        f304a.put("android.media.metadata.DISC_NUMBER", Integer.valueOf(0));
        f304a.put("android.media.metadata.ALBUM_ARTIST", Integer.valueOf(1));
        f304a.put("android.media.metadata.ART", Integer.valueOf(2));
        f304a.put("android.media.metadata.ART_URI", Integer.valueOf(1));
        f304a.put("android.media.metadata.ALBUM_ART", Integer.valueOf(2));
        f304a.put("android.media.metadata.ALBUM_ART_URI", Integer.valueOf(1));
        f304a.put("android.media.metadata.USER_RATING", Integer.valueOf(3));
        f304a.put("android.media.metadata.RATING", Integer.valueOf(3));
        f304a.put("android.media.metadata.DISPLAY_TITLE", Integer.valueOf(1));
        f304a.put("android.media.metadata.DISPLAY_SUBTITLE", Integer.valueOf(1));
        f304a.put("android.media.metadata.DISPLAY_DESCRIPTION", Integer.valueOf(1));
        f304a.put("android.media.metadata.DISPLAY_ICON", Integer.valueOf(2));
        f304a.put("android.media.metadata.DISPLAY_ICON_URI", Integer.valueOf(1));
        CREATOR = new C01081();
    }

    private MediaMetadataCompat(Parcel parcel) {
        this.f305b = parcel.readBundle();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f305b);
    }
}
