package com.maxcriser.hw_unitstest;

import android.os.Build;

public class Version implements AndroidVersion {

    public String androidVersion;

    @Override
    public String versionAndroid() {
        String release = Build.VERSION.RELEASE;
        int version = Build.VERSION.SDK_INT;
        androidVersion = (release + " (" + version + ")");
        return androidVersion;
    }

    @Override
    public boolean isUpdate() {
        return (currentVersion() < Build.VERSION_CODES.N);
    }

    @Override
    public int currentVersion() {
        return Build.VERSION.SDK_INT;
    }
}
