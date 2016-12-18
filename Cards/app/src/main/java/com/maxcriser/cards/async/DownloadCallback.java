package com.maxcriser.cards.async;


public interface DownloadCallback<T> {

    void onSuccess(final T result);
}