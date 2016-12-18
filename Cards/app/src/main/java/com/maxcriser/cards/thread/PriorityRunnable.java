package com.maxcriser.cards.thread;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.ProgressCallback;

public class PriorityRunnable<Params, Progress, Result> implements Runnable, Comparable<PriorityRunnable> {

    private final ITask<Params, Progress, Result> mTask;
    private final Params mParams;
    private final ProgressCallback<Progress> mProgressCallback;
    private final OnResultCallback<Result, Progress> mResultCallback;
    private final Handler mHandler;
    private int mPriority;

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(final int pPriority) {
        mPriority = pPriority;
    }

    public PriorityRunnable(final Handler pHandler, final ITask<Params, Progress, Result> pTask, final Params pParams, final ProgressCallback<Progress> pProgressCallback, final OnResultCallback<Result, Progress> pResultCallback) {
        mHandler = pHandler;
        mTask = pTask;
        mParams = pParams;
        mProgressCallback = pProgressCallback;
        mResultCallback = pResultCallback;
        mPriority = Thread.MAX_PRIORITY;
    }

    @Override
    public void run() {
        try {
            final Result result = mTask.doInBackground(mParams, new ProgressCallback<Progress>() {
                @Override
                public void onProgressChanged(final Progress pProgress) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressCallback.onProgressChanged(pProgress);
                        }
                    });
                }
            });
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mResultCallback.onSuccess(result);
                }
            });
        } catch (final Exception e) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mResultCallback.onError(e);
                }
            });
        }
    }

    @Override
    public int compareTo(@NonNull final PriorityRunnable pRunnable) {
        final int otherRunnablePriority = pRunnable.getPriority();
        if (mPriority > otherRunnablePriority) {
            return -1;
        } else if (mPriority == otherRunnablePriority) {
            return 0;
        } else {
            return 1;
        }
    }
}