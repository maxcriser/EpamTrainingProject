package com.wakdev.libs.core;

import android.app.Application;

public class WDCore extends Application {
    private static WDCore f1144a;

    public WDCore() {
        f1144a = this;
    }

    public static synchronized WDCore m2174a() {
        WDCore wDCore;
        synchronized (WDCore.class) {
            if (f1144a == null) {
                f1144a = new WDCore();
            }
            wDCore = f1144a;
        }
        return wDCore;
    }
}
