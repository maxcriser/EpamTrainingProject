package com.wakdev.libs.commons;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import com.wakdev.libs.core.WDCore;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* renamed from: com.wakdev.libs.commons.b */
public class C0487b {
    public static String m2049a(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        return C0487b.m2053b(instance);
    }

    public static String m2050a(int i, int i2, int i3) {
        return DateFormat.getDateFormat(WDCore.m2174a().getApplicationContext()).format(new GregorianCalendar(i, i2, i3).getTime());
    }

    public static String m2051a(Calendar calendar) {
        return C0487b.m2050a(calendar.get(1), calendar.get(2), calendar.get(5));
    }

    @SuppressLint({"SimpleDateFormat"})
    public static Calendar m2052a(String str, String str2) {
        try {
            Date parse = new SimpleDateFormat(str2).parse(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            return instance;
        } catch (Exception e) {
            return null;
        }
    }

    public static String m2053b(Calendar calendar) {
        return DateUtils.formatDateTime(WDCore.m2174a().getApplicationContext(), calendar.getTimeInMillis(), 131073);
    }
}
