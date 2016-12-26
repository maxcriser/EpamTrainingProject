package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import java.util.ArrayList;

public class FragmentTabHost extends TabHost implements OnTabChangeListener {
    private final ArrayList<C0021a> f92a;
    private Context f93b;
    private C0039h f94c;
    private int f95d;
    private OnTabChangeListener f96e;
    private C0021a f97f;
    private boolean f98g;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        String f87a;

        /* renamed from: android.support.v4.app.FragmentTabHost.SavedState.1 */
        static class C00201 implements Creator<SavedState> {
            C00201() {
            }

            public SavedState m105a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m106a(int i) {
                return new SavedState[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m105a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m106a(i);
            }
        }

        static {
            CREATOR = new C00201();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f87a = parcel.readString();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f87a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f87a);
        }
    }

    /* renamed from: android.support.v4.app.FragmentTabHost.a */
    static final class C0021a {
        private final String f88a;
        private final Class<?> f89b;
        private final Bundle f90c;
        private Fragment f91d;
    }

    private C0032k m112a(String str, C0032k c0032k) {
        C0021a c0021a = null;
        int i = 0;
        while (i < this.f92a.size()) {
            C0021a c0021a2 = (C0021a) this.f92a.get(i);
            if (!c0021a2.f88a.equals(str)) {
                c0021a2 = c0021a;
            }
            i++;
            c0021a = c0021a2;
        }
        if (c0021a == null) {
            throw new IllegalStateException("No tab known for tag " + str);
        }
        if (this.f97f != c0021a) {
            if (c0032k == null) {
                c0032k = this.f94c.m177a();
            }
            if (!(this.f97f == null || this.f97f.f91d == null)) {
                c0032k.m122a(this.f97f.f91d);
            }
            if (c0021a != null) {
                if (c0021a.f91d == null) {
                    c0021a.f91d = Fragment.m30a(this.f93b, c0021a.f89b.getName(), c0021a.f90c);
                    c0032k.m121a(this.f95d, c0021a.f91d, c0021a.f88a);
                } else {
                    c0032k.m124b(c0021a.f91d);
                }
            }
            this.f97f = c0021a;
        }
        return c0032k;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        C0032k c0032k = null;
        for (int i = 0; i < this.f92a.size(); i++) {
            C0021a c0021a = (C0021a) this.f92a.get(i);
            c0021a.f91d = this.f94c.m176a(c0021a.f88a);
            if (!(c0021a.f91d == null || c0021a.f91d.m75f())) {
                if (c0021a.f88a.equals(currentTabTag)) {
                    this.f97f = c0021a;
                } else {
                    if (c0032k == null) {
                        c0032k = this.f94c.m177a();
                    }
                    c0032k.m122a(c0021a.f91d);
                }
            }
        }
        this.f98g = true;
        C0032k a = m112a(currentTabTag, c0032k);
        if (a != null) {
            a.m120a();
            this.f94c.m178b();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f98g = false;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.f87a);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f87a = getCurrentTabTag();
        return savedState;
    }

    public void onTabChanged(String str) {
        if (this.f98g) {
            C0032k a = m112a(str, null);
            if (a != null) {
                a.m120a();
            }
        }
        if (this.f96e != null) {
            this.f96e.onTabChanged(str);
        }
    }

    public void setOnTabChangedListener(OnTabChangeListener onTabChangeListener) {
        this.f96e = onTabChangeListener;
    }

    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }
}
