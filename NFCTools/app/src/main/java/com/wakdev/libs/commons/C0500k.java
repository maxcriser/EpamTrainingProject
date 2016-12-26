package com.wakdev.libs.commons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import com.wakdev.libs.core.WDCore;

/* renamed from: com.wakdev.libs.commons.k */
public class C0500k {
    @SuppressLint({"InlinedApi"})
    public static final String[] f1138a;

    static {
        f1138a = new String[]{"android.settings.ACCESSIBILITY_SETTINGS", "android.settings.ADD_ACCOUNT_SETTINGS", "android.settings.AIRPLANE_MODE_SETTINGS", "android.settings.APN_SETTINGS", "android.settings.APPLICATION_DEVELOPMENT_SETTINGS", "android.settings.APPLICATION_SETTINGS", "android.settings.BLUETOOTH_SETTINGS", "android.settings.CAPTIONING_SETTINGS", "android.settings.DATA_ROAMING_SETTINGS", "android.settings.DATE_SETTINGS", "android.settings.DEVICE_INFO_SETTINGS", "android.settings.DISPLAY_SETTINGS", "android.settings.DREAM_SETTINGS", "android.settings.INPUT_METHOD_SETTINGS", "android.settings.INPUT_METHOD_SUBTYPE_SETTINGS", "android.settings.INTERNAL_STORAGE_SETTINGS", "android.settings.LOCALE_SETTINGS", "android.settings.LOCATION_SOURCE_SETTINGS", "android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS", "android.settings.MANAGE_APPLICATIONS_SETTINGS", "android.settings.MEMORY_CARD_SETTINGS", "android.settings.NETWORK_OPERATOR_SETTINGS", "android.settings.NFCSHARING_SETTINGS", "android.settings.NFC_PAYMENT_SETTINGS", "android.settings.NFC_SETTINGS", "android.settings.ACTION_PRINT_SETTINGS", "android.settings.PRIVACY_SETTINGS", "android.settings.QUICK_LAUNCH_SETTINGS", "android.search.action.SEARCH_SETTINGS", "android.settings.SECURITY_SETTINGS", "android.settings.SETTINGS", "android.settings.SOUND_SETTINGS", "android.settings.SYNC_SETTINGS", "android.settings.USER_DICTIONARY_SETTINGS", "android.settings.WIFI_IP_SETTINGS", "android.settings.WIFI_SETTINGS", "android.settings.WIRELESS_SETTINGS"};
    }

    public static float m2098a() {
        Intent registerReceiver = WDCore.m2174a().getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return -1.0f;
        }
        return ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
    }

    public static int m2099a(String str) {
        int i = -1;
        if ("android.settings.CAPTIONING_SETTINGS".equals(str) || "android.settings.NFC_PAYMENT_SETTINGS".equals(str) || "android.settings.ACTION_PRINT_SETTINGS".equals(str)) {
            i = 19;
        }
        if ("android.settings.DREAM_SETTINGS".equals(str)) {
            i = 18;
        }
        return "android.settings.NFC_SETTINGS".equals(str) ? 16 : i;
    }

    public static boolean m2100b() {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        return VERSION.SDK_INT < 17 ? System.getInt(applicationContext.getContentResolver(), "always_finish_activities", 0) == 1 : Global.getInt(applicationContext.getContentResolver(), "always_finish_activities", 0) == 1;
    }

    public static float m2101c() {
        Intent registerReceiver = WDCore.m2174a().getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return registerReceiver != null ? ((float) registerReceiver.getIntExtra("temperature", 0)) / 10.0f : 0.0f;
    }

    public static boolean m2102d() {
        boolean z = false;
        if (VERSION.SDK_INT >= 19) {
            try {
                z = WDCore.m2174a().getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.nfc.hce");
            } catch (Exception e) {
            }
        }
        return z;
    }

    public static void m2103e() {
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            Intent intent = new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS");
            intent.setFlags(268435456);
            applicationContext.startActivity(intent);
        } catch (Exception e) {
        }
    }
}
