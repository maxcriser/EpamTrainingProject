package android.support.v4.p006e;

import com.wakdev.nfctools.C0628m.C0627j;
import java.util.Locale;

/* renamed from: android.support.v4.e.c */
public class C0093c {
    public static final Locale f259a;
    private static String f260b;
    private static String f261c;

    static {
        f259a = new Locale("", "");
        f260b = "Arab";
        f261c = "Hebr";
    }

    public static int m399a(Locale locale) {
        if (!(locale == null || locale.equals(f259a))) {
            String a = C0091a.m395a(C0091a.m396b(locale.toString()));
            if (a == null) {
                return C0093c.m400b(locale);
            }
            if (a.equalsIgnoreCase(f260b) || a.equalsIgnoreCase(f261c)) {
                return 1;
            }
        }
        return 0;
    }

    private static int m400b(Locale locale) {
        switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
            case C0627j.View_paddingStart /*1*/:
            case C0627j.View_paddingEnd /*2*/:
                return 1;
            default:
                return 0;
        }
    }
}
