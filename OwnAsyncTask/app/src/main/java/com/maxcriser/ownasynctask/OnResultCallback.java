package com.maxcriser.ownasynctask;

public interface OnResultCallback<Result, Progress> extends ProgressCallback<Progress> {

    onSuccess(Result pResult);

    onError(Exception pE);

}
