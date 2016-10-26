package com.maxcriser.cards.util;

import android.content.Context;

public final class ContextObject {

    private static ContextObject sContextObject;

    private Context mContext;

    private ContextObject() {

    }

    public static ContextObject getInstance() {
        if (sContextObject == null) {
            sContextObject = new ContextObject();
        }

        return sContextObject;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(final Context pContext) {
        mContext = pContext;
    }
}
