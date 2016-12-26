package com.wakdev.libs.commons;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.wakdev.libs.core.WDCore;
import com.wakdev.nfctools.C0628m.C0627j;

/* renamed from: com.wakdev.libs.commons.h */
public class C0495h {
    public static void m2084a(String str) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        if (str != null) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.setFlags(268435456);
                applicationContext.startActivity(intent);
            } catch (Exception e) {
            }
        }
    }

    public static void m2085a(String str, int i) {
        switch (i) {
            case C0627j.View_paddingStart /*1*/:
                C0495h.m2087a(str, "market://details?id=", "http://play.google.com/store/apps/details?id=");
            case C0627j.View_paddingEnd /*2*/:
                C0495h.m2087a(str, "http://www.amazon.com/gp/mas/dl/android?p=", "http://www.amazon.com/gp/mas/dl/android?p=");
            case C0627j.Toolbar_subtitle /*3*/:
                C0495h.m2087a(str, "samsungapps://ProductDetail/", "http://www.samsungapps.com/appquery/appDetail.as?appId=");
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                C0495h.m2087a(str, "sam://details?id=", "http://slideme.org/app/");
            default:
                C0495h.m2087a(str, "market://details?id=", "http://play.google.com/store/apps/details?id=");
        }
    }

    public static void m2086a(String str, String str2) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str2 + str);
        intent.setType("text/plain");
        applicationContext.startActivity(intent);
    }

    public static void m2087a(String str, String str2, String str3) {
        Intent intent;
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        try {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(str2 + str));
            intent.setFlags(268435456);
            applicationContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(str3 + str));
            intent.setFlags(268435456);
            applicationContext.startActivity(intent);
        }
    }

    public static void m2088b(String str) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        try {
            Intent launchIntentForPackage = applicationContext.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                throw new NameNotFoundException();
            }
            launchIntentForPackage.setFlags(268435456);
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            applicationContext.startActivity(launchIntentForPackage);
        } catch (NameNotFoundException e) {
            C0495h.m2090c(str);
        }
    }

    public static void m2089b(String str, int i) {
        switch (i) {
            case C0627j.View_paddingStart /*1*/:
                C0495h.m2086a(str, "http://play.google.com/store/apps/details?id=");
            case C0627j.View_paddingEnd /*2*/:
                C0495h.m2086a(str, "http://www.amazon.com/gp/mas/dl/android?p=");
            case C0627j.Toolbar_subtitle /*3*/:
                C0495h.m2086a(str, "http://www.samsungapps.com/appquery/appDetail.as?appId=");
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                C0495h.m2086a(str, "http://slideme.org/app/");
            default:
                C0495h.m2086a(str, "http://play.google.com/store/apps/details?id=");
        }
    }

    public static void m2090c(String str) {
        C0495h.m2087a(str, "market://details?id=", "http://play.google.com/store/apps/details?id=");
    }
}
