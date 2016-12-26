package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.KeyEvent;

/* renamed from: android.support.v4.view.g */
public class C0197g {
    static final C0193d f423a;

    /* renamed from: android.support.v4.view.g.d */
    interface C0193d {
        void m875a(KeyEvent keyEvent);

        boolean m876a(int i, int i2);

        boolean m877b(int i);
    }

    /* renamed from: android.support.v4.view.g.a */
    static class C0194a implements C0193d {
        C0194a() {
        }

        private static int m878a(int i, int i2, int i3, int i4, int i5) {
            Object obj = 1;
            Object obj2 = (i2 & i3) != 0 ? 1 : null;
            int i6 = i4 | i5;
            if ((i2 & i6) == 0) {
                obj = null;
            }
            if (obj2 == null) {
                return obj != null ? i & (i3 ^ -1) : i;
            } else {
                if (obj == null) {
                    return i & (i6 ^ -1);
                }
                throw new IllegalArgumentException("bad arguments");
            }
        }

        public int m879a(int i) {
            int i2 = (i & 192) != 0 ? i | 1 : i;
            if ((i2 & 48) != 0) {
                i2 |= 2;
            }
            return i2 & 247;
        }

        public void m880a(KeyEvent keyEvent) {
        }

        public boolean m881a(int i, int i2) {
            return C0194a.m878a(C0194a.m878a(m879a(i) & 247, i2, 1, 64, 128), i2, 2, 16, 32) == i2;
        }

        public boolean m882b(int i) {
            return (m879a(i) & 247) == 0;
        }
    }

    /* renamed from: android.support.v4.view.g.b */
    static class C0195b extends C0194a {
        C0195b() {
        }

        public void m883a(KeyEvent keyEvent) {
            C0198h.m890a(keyEvent);
        }
    }

    /* renamed from: android.support.v4.view.g.c */
    static class C0196c extends C0195b {
        C0196c() {
        }

        public int m884a(int i) {
            return C0199i.m891a(i);
        }

        public boolean m885a(int i, int i2) {
            return C0199i.m892a(i, i2);
        }

        public boolean m886b(int i) {
            return C0199i.m893b(i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f423a = new C0196c();
        } else {
            f423a = new C0194a();
        }
    }

    public static boolean m887a(KeyEvent keyEvent) {
        return f423a.m877b(keyEvent.getMetaState());
    }

    public static boolean m888a(KeyEvent keyEvent, int i) {
        return f423a.m876a(keyEvent.getMetaState(), i);
    }

    public static void m889b(KeyEvent keyEvent) {
        f423a.m875a(keyEvent);
    }
}
