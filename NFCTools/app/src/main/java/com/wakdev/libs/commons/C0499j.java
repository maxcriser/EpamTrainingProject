package com.wakdev.libs.commons;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.wakdev.libs.core.WDCore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: com.wakdev.libs.commons.j */
public class C0499j {

    /* renamed from: com.wakdev.libs.commons.j.1 */
    static class C04971 implements Comparator<C0496i> {
        C04971() {
        }

        public int m2091a(C0496i c0496i, C0496i c0496i2) {
            return c0496i.f1133a.compareTo(c0496i2.f1133a);
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2091a((C0496i) obj, (C0496i) obj2);
        }
    }

    /* renamed from: com.wakdev.libs.commons.j.2 */
    static class C04982 implements Comparator<C0496i> {
        C04982() {
        }

        public int m2092a(C0496i c0496i, C0496i c0496i2) {
            return c0496i.f1133a.compareTo(c0496i2.f1133a);
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2092a((C0496i) obj, (C0496i) obj2);
        }
    }

    public static ArrayList<C0496i> m2093a(boolean z) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        List arrayList = new ArrayList();
        try {
            List installedApplications = applicationContext.getPackageManager().getInstalledApplications(0);
            for (int i = 0; i < installedApplications.size(); i++) {
                ApplicationInfo applicationInfo = (ApplicationInfo) installedApplications.get(i);
                if ((applicationInfo.flags & 129) == 1) {
                    C0496i c0496i = new C0496i();
                    c0496i.f1133a = applicationInfo.loadLabel(applicationContext.getPackageManager()).toString();
                    c0496i.f1134b = applicationInfo.packageName;
                    try {
                        c0496i.f1137e = applicationInfo.loadIcon(applicationContext.getPackageManager());
                    } catch (OutOfMemoryError e) {
                    }
                    arrayList.add(c0496i);
                }
            }
            if (z) {
                Collections.sort(arrayList, new C04982());
            }
            return arrayList;
        } catch (Exception e2) {
            return arrayList;
        }
    }

    public static ArrayList<C0496i> m2094a(boolean z, boolean z2) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        List arrayList = new ArrayList();
        try {
            List installedApplications = applicationContext.getPackageManager().getInstalledApplications(0);
            for (int i = 0; i < installedApplications.size(); i++) {
                ApplicationInfo applicationInfo = (ApplicationInfo) installedApplications.get(i);
                if ((applicationInfo.flags & 128) == 128 || (applicationInfo.flags & 1) != 1 || z) {
                    C0496i c0496i = new C0496i();
                    c0496i.f1133a = applicationInfo.loadLabel(applicationContext.getPackageManager()).toString();
                    c0496i.f1134b = applicationInfo.packageName;
                    try {
                        c0496i.f1137e = applicationInfo.loadIcon(applicationContext.getPackageManager());
                    } catch (OutOfMemoryError e) {
                    }
                    arrayList.add(c0496i);
                }
            }
            if (z2) {
                Collections.sort(arrayList, new C04971());
            }
            return arrayList;
        } catch (Exception e2) {
            return arrayList;
        }
    }

    @TargetApi(21)
    public static boolean m2095a() {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        try {
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 0);
            if (((AppOpsManager) applicationContext.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) == 0) {
                return true;
            }
        } catch (Exception e) {
            try {
                UsageStatsManager usageStatsManager = (UsageStatsManager) applicationContext.getSystemService("usagestats");
                long currentTimeMillis = System.currentTimeMillis();
                List queryUsageStats = usageStatsManager.queryUsageStats(0, currentTimeMillis - 10000, currentTimeMillis);
                if (!(queryUsageStats == null || queryUsageStats.isEmpty())) {
                    return true;
                }
            } catch (Exception e2) {
            }
        }
        return false;
    }

    public static boolean m2096a(String str) {
        try {
            WDCore.m2174a().getApplicationContext().getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @TargetApi(21)
    public static void m2097b() {
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$UsageAccessSettingsActivity"));
            intent.setFlags(268435456);
            applicationContext.startActivity(intent);
        } catch (Exception e) {
            C0493f.m2082a("Error: Usage access settings not found");
        }
    }
}
