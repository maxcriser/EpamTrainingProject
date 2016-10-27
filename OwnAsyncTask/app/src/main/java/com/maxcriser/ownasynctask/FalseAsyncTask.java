package com.maxcriser.ownasynctask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class FalseAsyncTask {

    final int COUNT_CORES = 3;
    ExecutorService mExecutorService;

    public FalseAsyncTask(){
        Executors.newFixedThreadPool(COUNT_CORES);
    }

    public FalseAsyncTask(final ExecutorService mExecutorService){
        this.mExecutorService = mExecutorService;
    }

    public<Params, Progress, Result> void execute(
            final Task<Params, Progress, Result> task,
            Params param,
            OnResultCallback<Result, Progress> onResultCallback){

        mExecutorService.execute(new Runnable() {
            android.os.Handler mHandler = new android.os.Handler();
            @Override
            public void run() {


            }
        });
    }

}
