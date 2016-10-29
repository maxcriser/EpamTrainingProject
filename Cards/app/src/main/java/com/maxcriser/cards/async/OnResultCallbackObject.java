package com.maxcriser.cards.async;

public interface OnResultCallbackObject {

    void onSuccess(String result);

    void onError(Exception e);

}