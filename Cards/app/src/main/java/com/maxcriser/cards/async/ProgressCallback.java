package com.maxcriser.cards.async;

public interface ProgressCallback<Progress> {

    void onProgressChanged(Progress progress);

}