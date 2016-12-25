package com.maxcriser.cards.utils;

import android.content.Context;
import android.net.NetworkInfo;

public final class ConnectivityManager {

    private static NetworkInfo getNetworkInfo(final Context context) {
        final android.net.ConnectivityManager cm = (android.net.ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static boolean isConnected(final Context context) {
        final NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

}
