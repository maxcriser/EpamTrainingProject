package com.maxcriser.ownasynctask;

public interface OnResultCallback<Result, Progress> extends ProgressCallback<Progress> {

    void onSuccess(Result pResult);

    void onError(Exception pE);

}
