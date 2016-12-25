package com.maxcriser.cards.loader.image;

import com.maxcriser.cards.thread.PriorityRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

    private static ThreadManager sThreadManager;
    private final ExecutorService mExecutorService;
    private final PriorityBlockingQueue<Runnable> mThreadQueue;

    private ThreadManager() {
        int numberOfThreads = 3;
        if (Runtime.getRuntime().availableProcessors() > 2) {
            numberOfThreads = Runtime.getRuntime().availableProcessors();
        }
        mThreadQueue = new PriorityBlockingQueue<>();
        this.mExecutorService = new ThreadPoolExecutor(numberOfThreads, numberOfThreads,
                Long.MAX_VALUE, TimeUnit.NANOSECONDS, mThreadQueue);
    }

    public static ThreadManager getInstance() {
        if (sThreadManager == null) {
            sThreadManager = new ThreadManager();
        }
        return sThreadManager;
    }

    public <Params, Progress, Result> void execute(final PriorityRunnable<Params, Progress, Result> pRunnable) {
        mThreadQueue.put(pRunnable);
        mExecutorService.execute(pRunnable);
    }

}