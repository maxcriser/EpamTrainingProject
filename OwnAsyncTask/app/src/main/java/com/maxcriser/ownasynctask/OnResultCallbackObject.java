package com.maxcriser.ownasynctask;

import com.maxcriser.ownasynctask.WorkerOperation;

public interface OnResultCallbackObject {

    void onSuccess(WorkerOperation.Result result);

    void onError(Exception e);

}