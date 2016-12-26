package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.v4.p000a.C0008e;
import android.util.Log;

/* renamed from: android.support.v4.app.o */
public class C0059o {
    private static final C0056a f249a;

    /* renamed from: android.support.v4.app.o.a */
    interface C0056a {
        Intent m309a(Activity activity);

        String m310a(Context context, ActivityInfo activityInfo);

        boolean m311a(Activity activity, Intent intent);

        void m312b(Activity activity, Intent intent);
    }

    /* renamed from: android.support.v4.app.o.b */
    static class C0057b implements C0056a {
        C0057b() {
        }

        public Intent m313a(Activity activity) {
            String b = C0059o.m325b(activity);
            if (b == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, b);
            try {
                return C0059o.m326b((Context) activity, componentName) == null ? C0008e.m8a(componentName) : new Intent().setComponent(componentName);
            } catch (NameNotFoundException e) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + b + "' in manifest");
                return null;
            }
        }

        public String m314a(Context context, ActivityInfo activityInfo) {
            if (activityInfo.metaData == null) {
                return null;
            }
            String string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
            return string == null ? null : string.charAt(0) == '.' ? context.getPackageName() + string : string;
        }

        public boolean m315a(Activity activity, Intent intent) {
            String action = activity.getIntent().getAction();
            return (action == null || action.equals("android.intent.action.MAIN")) ? false : true;
        }

        public void m316b(Activity activity, Intent intent) {
            intent.addFlags(67108864);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    /* renamed from: android.support.v4.app.o.c */
    static class C0058c extends C0057b {
        C0058c() {
        }

        public Intent m317a(Activity activity) {
            Intent a = C0060p.m328a(activity);
            return a == null ? m320b(activity) : a;
        }

        public String m318a(Context context, ActivityInfo activityInfo) {
            String a = C0060p.m329a(activityInfo);
            return a == null ? super.m314a(context, activityInfo) : a;
        }

        public boolean m319a(Activity activity, Intent intent) {
            return C0060p.m330a(activity, intent);
        }

        Intent m320b(Activity activity) {
            return super.m313a(activity);
        }

        public void m321b(Activity activity, Intent intent) {
            C0060p.m331b(activity, intent);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f249a = new C0058c();
        } else {
            f249a = new C0057b();
        }
    }

    public static Intent m322a(Activity activity) {
        return f249a.m309a(activity);
    }

    public static Intent m323a(Context context, ComponentName componentName) {
        String b = C0059o.m326b(context, componentName);
        if (b == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), b);
        return C0059o.m326b(context, componentName2) == null ? C0008e.m8a(componentName2) : new Intent().setComponent(componentName2);
    }

    public static boolean m324a(Activity activity, Intent intent) {
        return f249a.m311a(activity, intent);
    }

    public static String m325b(Activity activity) {
        try {
            return C0059o.m326b((Context) activity, activity.getComponentName());
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String m326b(Context context, ComponentName componentName) {
        return f249a.m310a(context, context.getPackageManager().getActivityInfo(componentName, 128));
    }

    public static void m327b(Activity activity, Intent intent) {
        f249a.m312b(activity, intent);
    }
}
