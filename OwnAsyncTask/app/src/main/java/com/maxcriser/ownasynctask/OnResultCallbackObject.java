package com.maxcriser.ownasynctask;

public interface OnResultCallbackObject {

    void onSuccess(MainActivity.Result result);

    void onError(Exception e);
}
