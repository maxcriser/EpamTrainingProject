package com.wakdev.libs.commons;

import android.util.Patterns;
import java.util.regex.Pattern;

/* renamed from: com.wakdev.libs.commons.o */
public class C0506o {
    public static final boolean m2169a(CharSequence charSequence) {
        return charSequence == null ? false : Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    public static boolean m2170a(String str) {
        return Pattern.compile("^([0-9A-F]{2}[:]){5}([0-9A-F]{2})$").matcher(str).matches();
    }

    public static boolean m2171b(String str) {
        return Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$").matcher(str).matches();
    }

    public static boolean m2172c(String str) {
        return Pattern.compile("(-?(90|(\\d|[1-8]\\d)(\\.\\d{1,30}){0,1}))").matcher(str).matches();
    }

    public static boolean m2173d(String str) {
        return Pattern.compile("(-?(180|(\\d|\\d\\d|1[0-7]\\d)(\\.\\d{1,30}){0,1}))").matcher(str).matches();
    }
}
