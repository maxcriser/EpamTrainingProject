package android.support.v4.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.v4.p000a.C0000a;

/* renamed from: android.support.v4.app.a */
public class C0022a extends C0000a {
    public static void m113a(Activity activity) {
        if (VERSION.SDK_INT >= 16) {
            C0025d.m117a(activity);
        } else {
            activity.finish();
        }
    }

    public static void m114b(Activity activity) {
        if (VERSION.SDK_INT >= 21) {
            C0023b.m115a(activity);
        } else {
            activity.finish();
        }
    }
}
