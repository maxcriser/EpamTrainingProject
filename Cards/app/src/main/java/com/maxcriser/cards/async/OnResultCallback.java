package com.maxcriser.cards.async;

public interface OnResultCallback<Result, Progress> extends ProgressCallback<Progress> {

    void onSuccess(Result pResult);

    void onError(Exception pE);

}
