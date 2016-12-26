package android.support.v4.p006e;

import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: android.support.v4.e.b */
class C0092b {
    private static Method f257a;
    private static Method f258b;

    static {
        try {
            Class cls = Class.forName("libcore.icu.ICU");
            if (cls != null) {
                f257a = cls.getMethod("getScript", new Class[]{String.class});
                f258b = cls.getMethod("addLikelySubtags", new Class[]{String.class});
            }
        } catch (Throwable e) {
            Log.w("ICUCompatIcs", e);
        }
    }

    public static String m397a(String str) {
        try {
            if (f257a != null) {
                return (String) f257a.invoke(null, new Object[]{str});
            }
        } catch (Throwable e) {
            Log.w("ICUCompatIcs", e);
        } catch (Throwable e2) {
            Log.w("ICUCompatIcs", e2);
        }
        return null;
    }

    public static String m398b(String str) {
        try {
            if (f258b != null) {
                return (String) f258b.invoke(null, new Object[]{str});
            }
        } catch (Throwable e) {
            Log.w("ICUCompatIcs", e);
        } catch (Throwable e2) {
            Log.w("ICUCompatIcs", e2);
        }
        return str;
    }
}
