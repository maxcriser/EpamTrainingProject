package com.wakdev.libs.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* renamed from: com.wakdev.libs.commons.c */
public class C0488c {
    public static String m2054a() {
        return UUID.randomUUID().toString();
    }

    public static byte[] m2055a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
