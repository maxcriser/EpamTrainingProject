package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import java.util.WeakHashMap;

/* renamed from: android.support.v4.view.u */
public class C0234u {
    static final C0224j f430a;

    /* renamed from: android.support.v4.view.u.j */
    interface C0224j {
        int m977a(int i, int i2, int i3);

        void m978a(View view);

        void m979a(View view, float f);

        void m980a(View view, int i, int i2, int i3, int i4);

        void m981a(View view, int i, Paint paint);

        void m982a(View view, Paint paint);

        void m983a(View view, C0119a c0119a);

        void m984a(View view, C0218q c0218q);

        void m985a(View view, Runnable runnable);

        void m986a(View view, Runnable runnable, long j);

        boolean m987a(View view, int i);

        int m988b(View view);

        void m989b(View view, float f);

        void m990b(View view, int i);

        void m991c(View view);

        void m992c(View view, float f);

        int m993d(View view);

        void m994d(View view, float f);

        int m995e(View view);

        void m996e(View view, float f);

        int m997f(View view);

        boolean m998g(View view);

        int m999h(View view);

        int m1000i(View view);

        float m1001j(View view);

        int m1002k(View view);

        af m1003l(View view);

        int m1004m(View view);

        boolean m1005n(View view);

        void m1006o(View view);
    }

    /* renamed from: android.support.v4.view.u.b */
    static class C0225b implements C0224j {
        WeakHashMap<View, af> f428a;

        C0225b() {
            this.f428a = null;
        }

        public int m1007a(int i, int i2, int i3) {
            return View.resolveSize(i, i2);
        }

        long m1008a() {
            return 10;
        }

        public void m1009a(View view) {
        }

        public void m1010a(View view, float f) {
        }

        public void m1011a(View view, int i, int i2, int i3, int i4) {
            view.invalidate(i, i2, i3, i4);
        }

        public void m1012a(View view, int i, Paint paint) {
        }

        public void m1013a(View view, Paint paint) {
        }

        public void m1014a(View view, C0119a c0119a) {
        }

        public void m1015a(View view, C0218q c0218q) {
        }

        public void m1016a(View view, Runnable runnable) {
            view.postDelayed(runnable, m1008a());
        }

        public void m1017a(View view, Runnable runnable, long j) {
            view.postDelayed(runnable, m1008a() + j);
        }

        public boolean m1018a(View view, int i) {
            return false;
        }

        public int m1019b(View view) {
            return 2;
        }

        public void m1020b(View view, float f) {
        }

        public void m1021b(View view, int i) {
        }

        public void m1022c(View view) {
            view.invalidate();
        }

        public void m1023c(View view, float f) {
        }

        public int m1024d(View view) {
            return 0;
        }

        public void m1025d(View view, float f) {
        }

        public int m1026e(View view) {
            return 0;
        }

        public void m1027e(View view, float f) {
        }

        public int m1028f(View view) {
            return 0;
        }

        public boolean m1029g(View view) {
            Drawable background = view.getBackground();
            return background != null && background.getOpacity() == -1;
        }

        public int m1030h(View view) {
            return view.getMeasuredWidth();
        }

        public int m1031i(View view) {
            return 0;
        }

        public float m1032j(View view) {
            return 0.0f;
        }

        public int m1033k(View view) {
            return 0;
        }

        public af m1034l(View view) {
            return new af(view);
        }

        public int m1035m(View view) {
            return 0;
        }

        public boolean m1036n(View view) {
            return false;
        }

        public void m1037o(View view) {
        }
    }

    /* renamed from: android.support.v4.view.u.c */
    static class C0226c extends C0225b {
        C0226c() {
        }

        public boolean m1038g(View view) {
            return C0237w.m1105a(view);
        }
    }

    /* renamed from: android.support.v4.view.u.d */
    static class C0227d extends C0226c {
        C0227d() {
        }

        public int m1039b(View view) {
            return C0238x.m1106a(view);
        }
    }

    /* renamed from: android.support.v4.view.u.e */
    static class C0228e extends C0227d {
        C0228e() {
        }

        public int m1040a(int i, int i2, int i3) {
            return C0239y.m1107a(i, i2, i3);
        }

        long m1041a() {
            return C0239y.m1109a();
        }

        public void m1042a(View view, int i, Paint paint) {
            C0239y.m1111a(view, i, paint);
        }

        public void m1043a(View view, Paint paint) {
            m1042a(view, m1047e(view), paint);
            view.invalidate();
        }

        public void m1044b(View view, float f) {
            C0239y.m1110a(view, f);
        }

        public void m1045c(View view, float f) {
            C0239y.m1113b(view, f);
        }

        public void m1046d(View view, float f) {
            C0239y.m1115c(view, f);
        }

        public int m1047e(View view) {
            return C0239y.m1108a(view);
        }

        public void m1048e(View view, float f) {
            C0239y.m1117d(view, f);
        }

        public int m1049h(View view) {
            return C0239y.m1112b(view);
        }

        public int m1050i(View view) {
            return C0239y.m1114c(view);
        }

        public float m1051j(View view) {
            return C0239y.m1116d(view);
        }

        public void m1052o(View view) {
            C0239y.m1118e(view);
        }
    }

    /* renamed from: android.support.v4.view.u.f */
    static class C0229f extends C0228e {
        static boolean f429b;

        static {
            f429b = false;
        }

        C0229f() {
        }

        public void m1053a(View view, C0119a c0119a) {
            C0240z.m1119a(view, c0119a.m476a());
        }

        public boolean m1054a(View view, int i) {
            return C0240z.m1120a(view, i);
        }

        public af m1055l(View view) {
            if (this.a == null) {
                this.a = new WeakHashMap();
            }
            af afVar = (af) this.a.get(view);
            if (afVar != null) {
                return afVar;
            }
            afVar = new af(view);
            this.a.put(view, afVar);
            return afVar;
        }
    }

    /* renamed from: android.support.v4.view.u.g */
    static class C0230g extends C0229f {
        C0230g() {
        }

        public void m1056a(View view) {
            aa.m769d(view);
        }

        public void m1057a(View view, int i, int i2, int i3, int i4) {
            aa.m764a(view, i, i2, i3, i4);
        }

        public void m1058a(View view, Runnable runnable) {
            aa.m765a(view, runnable);
        }

        public void m1059a(View view, Runnable runnable, long j) {
            aa.m766a(view, runnable, j);
        }

        public void m1060b(View view, int i) {
            if (i == 4) {
                i = 2;
            }
            aa.m763a(view, i);
        }

        public void m1061c(View view) {
            aa.m762a(view);
        }

        public int m1062d(View view) {
            return aa.m767b(view);
        }

        public int m1063k(View view) {
            return aa.m768c(view);
        }

        public boolean m1064n(View view) {
            return aa.m770e(view);
        }
    }

    /* renamed from: android.support.v4.view.u.h */
    static class C0231h extends C0230g {
        C0231h() {
        }

        public void m1065a(View view, Paint paint) {
            ab.m772a(view, paint);
        }

        public int m1066f(View view) {
            return ab.m771a(view);
        }

        public int m1067m(View view) {
            return ab.m773b(view);
        }
    }

    /* renamed from: android.support.v4.view.u.i */
    static class C0232i extends C0231h {
        C0232i() {
        }

        public void m1068b(View view, int i) {
            aa.m763a(view, i);
        }
    }

    /* renamed from: android.support.v4.view.u.a */
    static class C0233a extends C0232i {
        C0233a() {
        }

        public void m1069a(View view) {
            C0236v.m1102a(view);
        }

        public void m1070a(View view, float f) {
            C0236v.m1103a(view, f);
        }

        public void m1071a(View view, C0218q c0218q) {
            C0236v.m1104a(view, c0218q);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            f430a = new C0233a();
        } else if (i >= 19) {
            f430a = new C0232i();
        } else if (i >= 17) {
            f430a = new C0231h();
        } else if (i >= 16) {
            f430a = new C0230g();
        } else if (i >= 14) {
            f430a = new C0229f();
        } else if (i >= 11) {
            f430a = new C0228e();
        } else if (i >= 9) {
            f430a = new C0227d();
        } else if (i >= 7) {
            f430a = new C0226c();
        } else {
            f430a = new C0225b();
        }
    }

    public static int m1072a(int i, int i2, int i3) {
        return f430a.m977a(i, i2, i3);
    }

    public static int m1073a(View view) {
        return f430a.m988b(view);
    }

    public static void m1074a(View view, float f) {
        f430a.m989b(view, f);
    }

    public static void m1075a(View view, int i, int i2, int i3, int i4) {
        f430a.m980a(view, i, i2, i3, i4);
    }

    public static void m1076a(View view, int i, Paint paint) {
        f430a.m981a(view, i, paint);
    }

    public static void m1077a(View view, Paint paint) {
        f430a.m982a(view, paint);
    }

    public static void m1078a(View view, C0119a c0119a) {
        f430a.m983a(view, c0119a);
    }

    public static void m1079a(View view, C0218q c0218q) {
        f430a.m984a(view, c0218q);
    }

    public static void m1080a(View view, Runnable runnable) {
        f430a.m985a(view, runnable);
    }

    public static void m1081a(View view, Runnable runnable, long j) {
        f430a.m986a(view, runnable, j);
    }

    public static boolean m1082a(View view, int i) {
        return f430a.m987a(view, i);
    }

    public static void m1083b(View view) {
        f430a.m991c(view);
    }

    public static void m1084b(View view, float f) {
        f430a.m992c(view, f);
    }

    public static void m1085b(View view, int i) {
        f430a.m990b(view, i);
    }

    public static int m1086c(View view) {
        return f430a.m993d(view);
    }

    public static void m1087c(View view, float f) {
        f430a.m994d(view, f);
    }

    public static int m1088d(View view) {
        return f430a.m995e(view);
    }

    public static void m1089d(View view, float f) {
        f430a.m996e(view, f);
    }

    public static int m1090e(View view) {
        return f430a.m997f(view);
    }

    public static void m1091e(View view, float f) {
        f430a.m979a(view, f);
    }

    public static boolean m1092f(View view) {
        return f430a.m998g(view);
    }

    public static int m1093g(View view) {
        return f430a.m999h(view);
    }

    public static int m1094h(View view) {
        return f430a.m1000i(view);
    }

    public static float m1095i(View view) {
        return f430a.m1001j(view);
    }

    public static int m1096j(View view) {
        return f430a.m1002k(view);
    }

    public static af m1097k(View view) {
        return f430a.m1003l(view);
    }

    public static int m1098l(View view) {
        return f430a.m1004m(view);
    }

    public static void m1099m(View view) {
        f430a.m978a(view);
    }

    public static boolean m1100n(View view) {
        return f430a.m1005n(view);
    }

    public static void m1101o(View view) {
        f430a.m1006o(view);
    }
}
