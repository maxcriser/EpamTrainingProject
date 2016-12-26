package android.support.v4.view.p008a;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import com.wakdev.nfctools.C0628m.C0627j;

/* renamed from: android.support.v4.view.a.a */
public class C0141a {
    private static final C0134c f389a;
    private final Object f390b;

    /* renamed from: android.support.v4.view.a.a.c */
    interface C0134c {
        int m600a(Object obj);

        void m601a(Object obj, int i);

        void m602a(Object obj, Rect rect);

        void m603a(Object obj, View view);

        void m604a(Object obj, CharSequence charSequence);

        void m605a(Object obj, boolean z);

        CharSequence m606b(Object obj);

        void m607b(Object obj, Rect rect);

        CharSequence m608c(Object obj);

        CharSequence m609d(Object obj);

        CharSequence m610e(Object obj);

        boolean m611f(Object obj);

        boolean m612g(Object obj);

        boolean m613h(Object obj);

        boolean m614i(Object obj);

        boolean m615j(Object obj);

        boolean m616k(Object obj);

        boolean m617l(Object obj);

        boolean m618m(Object obj);

        boolean m619n(Object obj);

        boolean m620o(Object obj);

        String m621p(Object obj);
    }

    /* renamed from: android.support.v4.view.a.a.g */
    static class C0135g implements C0134c {
        C0135g() {
        }

        public int m622a(Object obj) {
            return 0;
        }

        public void m623a(Object obj, int i) {
        }

        public void m624a(Object obj, Rect rect) {
        }

        public void m625a(Object obj, View view) {
        }

        public void m626a(Object obj, CharSequence charSequence) {
        }

        public void m627a(Object obj, boolean z) {
        }

        public CharSequence m628b(Object obj) {
            return null;
        }

        public void m629b(Object obj, Rect rect) {
        }

        public CharSequence m630c(Object obj) {
            return null;
        }

        public CharSequence m631d(Object obj) {
            return null;
        }

        public CharSequence m632e(Object obj) {
            return null;
        }

        public boolean m633f(Object obj) {
            return false;
        }

        public boolean m634g(Object obj) {
            return false;
        }

        public boolean m635h(Object obj) {
            return false;
        }

        public boolean m636i(Object obj) {
            return false;
        }

        public boolean m637j(Object obj) {
            return false;
        }

        public boolean m638k(Object obj) {
            return false;
        }

        public boolean m639l(Object obj) {
            return false;
        }

        public boolean m640m(Object obj) {
            return false;
        }

        public boolean m641n(Object obj) {
            return false;
        }

        public boolean m642o(Object obj) {
            return false;
        }

        public String m643p(Object obj) {
            return null;
        }
    }

    /* renamed from: android.support.v4.view.a.a.b */
    static class C0136b extends C0135g {
        C0136b() {
        }

        public int m644a(Object obj) {
            return C0142b.m690a(obj);
        }

        public void m645a(Object obj, int i) {
            C0142b.m691a(obj, i);
        }

        public void m646a(Object obj, Rect rect) {
            C0142b.m692a(obj, rect);
        }

        public void m647a(Object obj, View view) {
            C0142b.m693a(obj, view);
        }

        public void m648a(Object obj, CharSequence charSequence) {
            C0142b.m694a(obj, charSequence);
        }

        public void m649a(Object obj, boolean z) {
            C0142b.m695a(obj, z);
        }

        public CharSequence m650b(Object obj) {
            return C0142b.m696b(obj);
        }

        public void m651b(Object obj, Rect rect) {
            C0142b.m697b(obj, rect);
        }

        public CharSequence m652c(Object obj) {
            return C0142b.m698c(obj);
        }

        public CharSequence m653d(Object obj) {
            return C0142b.m699d(obj);
        }

        public CharSequence m654e(Object obj) {
            return C0142b.m700e(obj);
        }

        public boolean m655f(Object obj) {
            return C0142b.m701f(obj);
        }

        public boolean m656g(Object obj) {
            return C0142b.m702g(obj);
        }

        public boolean m657h(Object obj) {
            return C0142b.m703h(obj);
        }

        public boolean m658i(Object obj) {
            return C0142b.m704i(obj);
        }

        public boolean m659j(Object obj) {
            return C0142b.m705j(obj);
        }

        public boolean m660k(Object obj) {
            return C0142b.m706k(obj);
        }

        public boolean m661l(Object obj) {
            return C0142b.m707l(obj);
        }

        public boolean m662m(Object obj) {
            return C0142b.m708m(obj);
        }

        public boolean m663n(Object obj) {
            return C0142b.m709n(obj);
        }

        public boolean m664o(Object obj) {
            return C0142b.m710o(obj);
        }
    }

    /* renamed from: android.support.v4.view.a.a.d */
    static class C0137d extends C0136b {
        C0137d() {
        }
    }

    /* renamed from: android.support.v4.view.a.a.e */
    static class C0138e extends C0137d {
        C0138e() {
        }

        public String m665p(Object obj) {
            return C0143c.m711a(obj);
        }
    }

    /* renamed from: android.support.v4.view.a.a.f */
    static class C0139f extends C0138e {
        C0139f() {
        }
    }

    /* renamed from: android.support.v4.view.a.a.a */
    static class C0140a extends C0139f {
        C0140a() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            f389a = new C0140a();
        } else if (VERSION.SDK_INT >= 19) {
            f389a = new C0139f();
        } else if (VERSION.SDK_INT >= 18) {
            f389a = new C0138e();
        } else if (VERSION.SDK_INT >= 16) {
            f389a = new C0137d();
        } else if (VERSION.SDK_INT >= 14) {
            f389a = new C0136b();
        } else {
            f389a = new C0135g();
        }
    }

    public C0141a(Object obj) {
        this.f390b = obj;
    }

    private static String m666b(int i) {
        switch (i) {
            case C0627j.View_paddingStart /*1*/:
                return "ACTION_FOCUS";
            case C0627j.View_paddingEnd /*2*/:
                return "ACTION_CLEAR_FOCUS";
            case C0627j.Toolbar_contentInsetStart /*4*/:
                return "ACTION_SELECT";
            case C0627j.Toolbar_popupTheme /*8*/:
                return "ACTION_CLEAR_SELECTION";
            case C0627j.Toolbar_maxButtonHeight /*16*/:
                return "ACTION_CLICK";
            case C0627j.Theme_actionModeShareDrawable /*32*/:
                return "ACTION_LONG_CLICK";
            case C0627j.Theme_listPreferredItemHeightLarge /*64*/:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }

    public Object m667a() {
        return this.f390b;
    }

    public void m668a(int i) {
        f389a.m601a(this.f390b, i);
    }

    public void m669a(Rect rect) {
        f389a.m602a(this.f390b, rect);
    }

    public void m670a(View view) {
        f389a.m603a(this.f390b, view);
    }

    public void m671a(CharSequence charSequence) {
        f389a.m604a(this.f390b, charSequence);
    }

    public void m672a(boolean z) {
        f389a.m605a(this.f390b, z);
    }

    public int m673b() {
        return f389a.m600a(this.f390b);
    }

    public void m674b(Rect rect) {
        f389a.m607b(this.f390b, rect);
    }

    public boolean m675c() {
        return f389a.m611f(this.f390b);
    }

    public boolean m676d() {
        return f389a.m612g(this.f390b);
    }

    public boolean m677e() {
        return f389a.m615j(this.f390b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        C0141a c0141a = (C0141a) obj;
        return this.f390b == null ? c0141a.f390b == null : this.f390b.equals(c0141a.f390b);
    }

    public boolean m678f() {
        return f389a.m616k(this.f390b);
    }

    public boolean m679g() {
        return f389a.m620o(this.f390b);
    }

    public boolean m680h() {
        return f389a.m613h(this.f390b);
    }

    public int hashCode() {
        return this.f390b == null ? 0 : this.f390b.hashCode();
    }

    public boolean m681i() {
        return f389a.m617l(this.f390b);
    }

    public boolean m682j() {
        return f389a.m614i(this.f390b);
    }

    public boolean m683k() {
        return f389a.m618m(this.f390b);
    }

    public boolean m684l() {
        return f389a.m619n(this.f390b);
    }

    public CharSequence m685m() {
        return f389a.m609d(this.f390b);
    }

    public CharSequence m686n() {
        return f389a.m606b(this.f390b);
    }

    public CharSequence m687o() {
        return f389a.m610e(this.f390b);
    }

    public CharSequence m688p() {
        return f389a.m608c(this.f390b);
    }

    public String m689q() {
        return f389a.m621p(this.f390b);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        Rect rect = new Rect();
        m669a(rect);
        stringBuilder.append("; boundsInParent: " + rect);
        m674b(rect);
        stringBuilder.append("; boundsInScreen: " + rect);
        stringBuilder.append("; packageName: ").append(m685m());
        stringBuilder.append("; className: ").append(m686n());
        stringBuilder.append("; text: ").append(m687o());
        stringBuilder.append("; contentDescription: ").append(m688p());
        stringBuilder.append("; viewId: ").append(m689q());
        stringBuilder.append("; checkable: ").append(m675c());
        stringBuilder.append("; checked: ").append(m676d());
        stringBuilder.append("; focusable: ").append(m677e());
        stringBuilder.append("; focused: ").append(m678f());
        stringBuilder.append("; selected: ").append(m679g());
        stringBuilder.append("; clickable: ").append(m680h());
        stringBuilder.append("; longClickable: ").append(m681i());
        stringBuilder.append("; enabled: ").append(m682j());
        stringBuilder.append("; password: ").append(m683k());
        stringBuilder.append("; scrollable: " + m684l());
        stringBuilder.append("; [");
        int b = m673b();
        while (b != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(b);
            b &= numberOfTrailingZeros ^ -1;
            stringBuilder.append(C0141a.m666b(numberOfTrailingZeros));
            if (b != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
