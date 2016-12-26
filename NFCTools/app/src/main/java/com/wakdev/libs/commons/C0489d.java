package com.wakdev.libs.commons;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* renamed from: com.wakdev.libs.commons.d */
public class C0489d {
    protected static final char[] f1126a;

    static {
        f1126a = "0123456789ABCDEF".toCharArray();
    }

    public static float m2056a(float f) {
        return ((float) (1.8d * ((double) f))) + 32.0f;
    }

    public static int m2057a(byte b) {
        return (b & 255) | 0;
    }

    public static int m2058a(byte[] bArr, byte b) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] == b) {
                return i;
            }
        }
        return -1;
    }

    public static String m2059a(String str) {
        return Integer.toHexString(Integer.parseInt(str, 2));
    }

    public static String m2060a(String str, int i) {
        String toBinaryString = Integer.toBinaryString(Integer.parseInt(str, 16));
        while (toBinaryString.length() < i) {
            toBinaryString = "0" + toBinaryString;
        }
        return toBinaryString;
    }

    public static String m2061a(String str, String str2) {
        try {
            ByteBuffer allocate = ByteBuffer.allocate(str.length() / 2);
            for (int i = 0; i < str.length(); i += 2) {
                allocate.put((byte) Integer.parseInt(str.substring(i, i + 2), 16));
            }
            allocate.rewind();
            return Charset.forName("US-ASCII").decode(allocate).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String m2062a(String str, String str2, int i, boolean z) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i2 = 0; i2 < str.length() / i; i2++) {
            stringBuilder.insert(((i2 + 1) * i) + i2, str2);
        }
        return !z ? stringBuilder.substring(0, stringBuilder.length() - 1) : stringBuilder.toString();
    }

    public static String m2063a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = f1126a[i2 >>> 4];
            cArr[(i * 2) + 1] = f1126a[i2 & 15];
        }
        return new String(cArr);
    }

    public static byte[] m2064a(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return obj;
    }

    public static byte[] m2065a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public static <T> T[] m2066a(T[] tArr, T[] tArr2) {
        Object copyOf = Arrays.copyOf(tArr, tArr.length + tArr2.length);
        System.arraycopy(tArr2, 0, copyOf, tArr.length, tArr2.length);
        return copyOf;
    }

    public static float m2067b(float f) {
        return ((float) Math.round(f * 10.0f)) / 10.0f;
    }

    public static String m2068b(String str) {
        return Integer.toBinaryString(Integer.parseInt(str, 16));
    }

    public static String m2069b(String str, int i) {
        try {
            return String.format("%0" + String.valueOf(i) + "d", new Object[]{Integer.valueOf(Integer.parseInt(str))});
        } catch (Exception e) {
            return null;
        }
    }

    public static String m2070c(String str) {
        return String.valueOf(Integer.parseInt(str, 2));
    }

    public static String m2071d(String str) {
        return C0489d.m2061a(str, "UTF-8");
    }

    public static String m2072e(String str) {
        return C0489d.m2061a(str, "US-ASCII");
    }

    public static int m2073f(String str) {
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            i2 |= Character.digit(str.charAt(i), 16);
            if (i < str.length() - 1) {
                i2 <<= 4;
            }
            i++;
        }
        return i2;
    }
}
