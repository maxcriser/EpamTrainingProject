package com.maxcriser.cards.async;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OwnAsyncTask {

    private ExecutorService mExecutorService;

    public OwnAsyncTask() {
        int COUNT_CORES = 3;
        this.mExecutorService = Executors.newFixedThreadPool(COUNT_CORES);
    }

    public OwnAsyncTask(final ExecutorService mExecutorService) {
        this.mExecutorService = mExecutorService;
    }

    public <Params, Progress, Result> void execute(
            final Task<Params, Progress, Result> task,
            final Params param,
            final OnResultCallback<Result, Progress> onResultCallback) {

        mExecutorService.execute(new Runnable() {
            android.os.Handler mHandler = new Handler(Looper.getMainLooper());

            @Override
            public void run() {
                try {
                    final Result result = task.doInBackground(param, new ProgressCallback<Progress>() {
                        @Override
                        public void onProgressChanged(final Progress pProgress) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (onResultCallback != null)
                                        onResultCallback.onProgressChanged(pProgress);
                                }
                            });
                        }
                    });
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (onResultCallback != null)
                                onResultCallback.onSuccess(result);
                        }
                    });
                } catch (Exception pE) {
                    if (onResultCallback != null)
                        onResultCallback.onError(pE);
                }
            }
        });
    }
}
