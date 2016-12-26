package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

/* renamed from: android.support.v4.app.p */
class C0060p {
    public static Intent m328a(Activity activity) {
        return activity.getParentActivityIntent();
    }

    public static String m329a(ActivityInfo activityInfo) {
        return activityInfo.parentActivityName;
    }

    public static boolean m330a(Activity activity, Intent intent) {
        return activity.shouldUpRecreateTask(intent);
    }

    public static void m331b(Activity activity, Intent intent) {
        activity.navigateUpTo(intent);
    }
}
