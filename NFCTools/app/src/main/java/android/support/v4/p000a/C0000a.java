package android.support.v4.p000a;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;

/* renamed from: android.support.v4.a.a */
public class C0000a {
    public static final Drawable m0a(Context context, int i) {
        return VERSION.SDK_INT >= 21 ? C0001b.m2a(context, i) : context.getResources().getDrawable(i);
    }

    public static boolean m1a(Context context, Intent[] intentArr, Bundle bundle) {
        int i = VERSION.SDK_INT;
        if (i >= 16) {
            C0003d.m4a(context, intentArr, bundle);
            return true;
        } else if (i < 11) {
            return false;
        } else {
            C0002c.m3a(context, intentArr);
            return true;
        }
    }
}
