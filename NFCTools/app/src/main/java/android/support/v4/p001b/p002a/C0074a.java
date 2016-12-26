package android.support.v4.p001b.p002a;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

/* renamed from: android.support.v4.b.a.a */
public class C0074a {
    static final C0069b f253a;

    /* renamed from: android.support.v4.b.a.a.b */
    interface C0069b {
        void m343a(Drawable drawable);

        void m344a(Drawable drawable, float f, float f2);

        void m345a(Drawable drawable, int i);

        void m346a(Drawable drawable, int i, int i2, int i3, int i4);

        void m347a(Drawable drawable, ColorStateList colorStateList);

        void m348a(Drawable drawable, Mode mode);

        void m349a(Drawable drawable, boolean z);

        boolean m350b(Drawable drawable);
    }

    /* renamed from: android.support.v4.b.a.a.a */
    static class C0070a implements C0069b {
        C0070a() {
        }

        public void m351a(Drawable drawable) {
        }

        public void m352a(Drawable drawable, float f, float f2) {
        }

        public void m353a(Drawable drawable, int i) {
        }

        public void m354a(Drawable drawable, int i, int i2, int i3, int i4) {
        }

        public void m355a(Drawable drawable, ColorStateList colorStateList) {
        }

        public void m356a(Drawable drawable, Mode mode) {
        }

        public void m357a(Drawable drawable, boolean z) {
        }

        public boolean m358b(Drawable drawable) {
            return false;
        }
    }

    /* renamed from: android.support.v4.b.a.a.c */
    static class C0071c extends C0070a {
        C0071c() {
        }

        public void m359a(Drawable drawable) {
            C0075b.m375a(drawable);
        }
    }

    /* renamed from: android.support.v4.b.a.a.d */
    static class C0072d extends C0071c {
        C0072d() {
        }

        public void m360a(Drawable drawable, boolean z) {
            C0076c.m376a(drawable, z);
        }

        public boolean m361b(Drawable drawable) {
            return C0076c.m377a(drawable);
        }
    }

    /* renamed from: android.support.v4.b.a.a.e */
    static class C0073e extends C0072d {
        C0073e() {
        }

        public void m362a(Drawable drawable, float f, float f2) {
            C0077d.m378a(drawable, f, f2);
        }

        public void m363a(Drawable drawable, int i) {
            C0077d.m379a(drawable, i);
        }

        public void m364a(Drawable drawable, int i, int i2, int i3, int i4) {
            C0077d.m380a(drawable, i, i2, i3, i4);
        }

        public void m365a(Drawable drawable, ColorStateList colorStateList) {
            C0077d.m381a(drawable, colorStateList);
        }

        public void m366a(Drawable drawable, Mode mode) {
            C0077d.m382a(drawable, mode);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            f253a = new C0073e();
        } else if (i >= 19) {
            f253a = new C0072d();
        } else if (i >= 11) {
            f253a = new C0071c();
        } else {
            f253a = new C0070a();
        }
    }

    public static void m367a(Drawable drawable) {
        f253a.m343a(drawable);
    }

    public static void m368a(Drawable drawable, float f, float f2) {
        f253a.m344a(drawable, f, f2);
    }

    public static void m369a(Drawable drawable, int i) {
        f253a.m345a(drawable, i);
    }

    public static void m370a(Drawable drawable, int i, int i2, int i3, int i4) {
        f253a.m346a(drawable, i, i2, i3, i4);
    }

    public static void m371a(Drawable drawable, ColorStateList colorStateList) {
        f253a.m347a(drawable, colorStateList);
    }

    public static void m372a(Drawable drawable, Mode mode) {
        f253a.m348a(drawable, mode);
    }

    public static void m373a(Drawable drawable, boolean z) {
        f253a.m349a(drawable, z);
    }

    public static boolean m374b(Drawable drawable) {
        return f253a.m350b(drawable);
    }
}
