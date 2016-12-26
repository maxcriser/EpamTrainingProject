package android.support.v4.p007f;

/* renamed from: android.support.v4.f.b */
class C0098b {
    static final int[] f274a;
    static final long[] f275b;
    static final Object[] f276c;

    static {
        f274a = new int[0];
        f275b = new long[0];
        f276c = new Object[0];
    }

    public static int m441a(int i) {
        return C0098b.m444b(i * 4) / 4;
    }

    static int m442a(int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = i - 1;
        while (i3 <= i4) {
            int i5 = (i3 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i3 = i5 + 1;
            } else if (i6 <= i2) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return i3 ^ -1;
    }

    public static boolean m443a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int m444b(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }
}
