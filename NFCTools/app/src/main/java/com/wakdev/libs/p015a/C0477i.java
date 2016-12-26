package com.wakdev.libs.p015a;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

/* renamed from: com.wakdev.libs.a.i */
public class C0477i {
    public static int m1924a(byte[] bArr, byte[] bArr2) {
        try {
            return C0477i.m1927a("04494E1A386D3D3CFE3DC10E5DE68A499B1C202DB5B132393E89ED19FE5BE8BC61", bArr2, bArr) ? 1 : 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public static ECParameterSpec m1925a() {
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(new BigInteger("fffffffdffffffffffffffffffffffff", 16)), new BigInteger("fffffffdfffffffffffffffffffffffc", 16), new BigInteger("e87579c11079f43dd824993c2cee5ed3", 16)), new ECPoint(new BigInteger("161ff7528b899b2d0c28607ca52c5b86", 16), new BigInteger("cf5ac8395bafeb13c02da292dded7a83", 16)), new BigInteger("fffffffe0000000075a30d1b9038a115", 16), 1);
    }

    public static ECPublicKeySpec m1926a(String str, ECParameterSpec eCParameterSpec) {
        if (str == null || str.length() != 66 || !str.startsWith("04")) {
            return null;
        }
        return new ECPublicKeySpec(new ECPoint(new BigInteger(str.substring(2, 34), 16), new BigInteger(str.substring(34, 66), 16)), eCParameterSpec);
    }

    public static boolean m1927a(String str, byte[] bArr, byte[] bArr2) {
        return C0477i.m1928a(C0477i.m1926a(str, C0477i.m1925a()), bArr, bArr2);
    }

    public static boolean m1928a(ECPublicKeySpec eCPublicKeySpec, byte[] bArr, byte[] bArr2) {
        KeyFactory instance;
        try {
            instance = KeyFactory.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            instance = KeyFactory.getInstance("ECDSA");
        }
        if (instance != null) {
            try {
                PublicKey generatePublic = instance.generatePublic(eCPublicKeySpec);
                Signature instance2 = Signature.getInstance("NONEwithECDSA");
                instance2.initVerify(generatePublic);
                instance2.update(bArr2);
                return instance2.verify(C0477i.m1929a(bArr));
            } catch (Exception e2) {
            }
        }
        return false;
    }

    public static byte[] m1929a(byte[] bArr) {
        Object copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
        Object copyOfRange2 = Arrays.copyOfRange(bArr, 16, 32);
        int length = copyOfRange.length;
        int length2 = copyOfRange.length;
        if ((copyOfRange[0] & 128) != 0) {
            length++;
        }
        if ((copyOfRange2[0] & 128) != 0) {
            length2++;
        }
        Object obj = new byte[((length + length2) + 6)];
        obj[0] = (byte) 48;
        obj[1] = (byte) ((length + 4) + length2);
        obj[2] = 2;
        obj[3] = (byte) length;
        obj[length + 4] = 2;
        obj[(length + 4) + 1] = (byte) length2;
        obj[4] = null;
        obj[(length + 4) + 2] = null;
        System.arraycopy(copyOfRange, 0, obj, (length + 4) - copyOfRange.length, copyOfRange.length);
        System.arraycopy(copyOfRange2, 0, obj, (((length + 4) + 2) + length2) - copyOfRange2.length, copyOfRange2.length);
        return obj;
    }
}
