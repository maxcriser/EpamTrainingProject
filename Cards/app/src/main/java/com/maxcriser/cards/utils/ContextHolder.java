package com.maxcriser.cards.utils;

import android.content.Context;

//TODO move to root package
public class ContextHolder {

    private static ContextHolder sContextHolder;
    private Context mContext;

    private ContextHolder() {
    }

    public static ContextHolder getInstance() {
        if (sContextHolder == null) {
            sContextHolder = new ContextHolder();
        }
        return sContextHolder;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context pContext) {
        mContext = pContext;
    }

}