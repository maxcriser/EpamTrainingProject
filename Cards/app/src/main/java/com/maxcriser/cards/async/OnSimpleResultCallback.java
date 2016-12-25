package com.maxcriser.cards.async;

public interface OnSimpleResultCallback<Result, Progress> extends ProgressCallback<Progress> {

    void onSuccess(Result pResult);

}
