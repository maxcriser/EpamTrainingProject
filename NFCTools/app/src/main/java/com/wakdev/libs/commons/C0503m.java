package com.wakdev.libs.commons;

import android.content.ClipData;
import android.content.ClipboardManager;
import com.wakdev.libs.core.WDCore;
import java.io.File;

/* renamed from: com.wakdev.libs.commons.m */
public class C0503m {
    public static void m2107a(String str) {
        ((ClipboardManager) WDCore.m2174a().getApplicationContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str));
    }

    public static boolean m2108b(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        File file = new File(str);
        return file != null && file.exists();
    }

    public static String m2109c(String str) {
        File file = new File(str);
        return file != null ? file.getParent() : null;
    }

    public static String m2110d(String str) {
        File file = new File(str);
        return file != null ? file.getName() : null;
    }
}
