package com.wakdev.libs.commons;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.wakdev.libs.core.WDCore;

/* renamed from: com.wakdev.libs.commons.f */
public class C0493f {
    private static int f1132a;

    /* renamed from: com.wakdev.libs.commons.f.1 */
    static class C04911 implements Runnable {
        final /* synthetic */ Activity f1127a;
        final /* synthetic */ String f1128b;

        C04911(Activity activity, String str) {
            this.f1127a = activity;
            this.f1128b = str;
        }

        public void run() {
            Toast.makeText(this.f1127a, this.f1128b, 0).show();
        }
    }

    /* renamed from: com.wakdev.libs.commons.f.2 */
    static class C04922 implements Runnable {
        final /* synthetic */ Context f1129a;
        final /* synthetic */ String f1130b;
        final /* synthetic */ Handler f1131c;

        C04922(Context context, String str, Handler handler) {
            this.f1129a = context;
            this.f1130b = str;
            this.f1131c = handler;
        }

        public void run() {
            Toast.makeText(this.f1129a, this.f1130b, 0).show();
            this.f1131c.removeCallbacks(this);
        }
    }

    static {
        f1132a = 1337;
    }

    public static void m2081a(Activity activity, String str) {
        activity.runOnUiThread(new C04911(activity, str));
    }

    public static void m2082a(String str) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        Handler handler = new Handler();
        handler.post(new C04922(applicationContext, str, handler));
    }
}
