package android.support.v4.p000a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;

/* renamed from: android.support.v4.a.e */
public class C0008e {
    private static final C0004a f0a;

    /* renamed from: android.support.v4.a.e.a */
    interface C0004a {
        Intent m5a(ComponentName componentName);
    }

    /* renamed from: android.support.v4.a.e.b */
    static class C0005b implements C0004a {
        C0005b() {
        }

        public Intent m6a(ComponentName componentName) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(componentName);
            intent.addCategory("android.intent.category.LAUNCHER");
            return intent;
        }
    }

    /* renamed from: android.support.v4.a.e.c */
    static class C0006c extends C0005b {
        C0006c() {
        }

        public Intent m7a(ComponentName componentName) {
            return C0009f.m9a(componentName);
        }
    }

    /* renamed from: android.support.v4.a.e.d */
    static class C0007d extends C0006c {
        C0007d() {
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 15) {
            f0a = new C0007d();
        } else if (i >= 11) {
            f0a = new C0006c();
        } else {
            f0a = new C0005b();
        }
    }

    public static Intent m8a(ComponentName componentName) {
        return f0a.m5a(componentName);
    }
}
