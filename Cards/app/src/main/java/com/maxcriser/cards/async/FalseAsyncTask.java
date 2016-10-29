package com.maxcriser.cards.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FalseAsyncTask {

    final int COUNT_CORES = 3;
    ExecutorService mExecutorService;

    public FalseAsyncTask(){
        this.mExecutorService = Executors.newFixedThreadPool(COUNT_CORES);
    }

    public FalseAsyncTask(final ExecutorService mExecutorService){
        this.mExecutorService = mExecutorService;
    }

    public<Params, Progress, Result> void execute(
            final Task<Params, Progress, Result> task,
            final Params param,
            final OnResultCallback<Result, Progress> onResultCallback){

        mExecutorService.execute(new Runnable() {
            android.os.Handler mHandler = new android.os.Handler();
            @Override
            public void run() {

                try {
                    final Result result = task.doInBackground(param, new ProgressCallback<Progress>() {
                        @Override
                        public void onProgressChanged(final Progress pProgress) {
                                 mHandler.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         onResultCallback.onProgressChanged(pProgress);
                                     }
                                 });
                        }
                    });
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onResultCallback.onSuccess(result);
                        }
                    });
                } catch (Exception pE) {
                    onResultCallback.onError(pE);
                }
            }
        });
    }

}
