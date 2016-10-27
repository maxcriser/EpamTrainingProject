package com.maxcriser.ownasynctask;

public interface ProgressCallback<Progress> {

    void onProgressChanged(Progress progress);

}