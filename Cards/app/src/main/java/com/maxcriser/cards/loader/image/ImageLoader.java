package com.maxcriser.cards.loader.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.http.HttpClient;
import com.maxcriser.cards.model.Priority;
import com.maxcriser.cards.model.Request;
import com.maxcriser.cards.thread.ITask;
import com.maxcriser.cards.thread.PriorityRunnable;
import com.maxcriser.cards.thread.ThreadManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Stack;

public class ImageLoader {

    private static final String FILE = "file://";
    private static final String HTTP = "http";
    private static ImageLoader sImageLoader;
    private final HttpClient mHttpClient;
    private final ThreadManager mThreadManager;
    private final LruCache<String, Bitmap> mLruCache;
    private final Stack<Priority> mDownloadPriorities;
    private final Object mLockObj = new Object();
    private final String TAG = "ImageLoader";

    private ImageLoader() {
        mThreadManager = ThreadManager.getInstance();
        mDownloadPriorities = new Stack<>();
        mHttpClient = new HttpClient();
        long size;
        int defaultCacheSize = 12 * 1024 * 1024;
        if (Runtime.getRuntime().maxMemory() / 4 > defaultCacheSize) {
            size = defaultCacheSize;
        } else {
            size = Runtime.getRuntime().maxMemory() / 4;
        }
        Log.d(TAG, "ImageLoader: MEMORY ALLOCATED FOR LRU CACHE: " + size);
        mLruCache = new LruCache<String, Bitmap>(((int) size)) {

            @Override
            protected int sizeOf(final String pKey, final Bitmap pValue) {
                return pKey.getBytes().length + pValue.getByteCount();
            }

        };
    }

    public static ImageLoader getInstance() {
        if (sImageLoader == null) {
            sImageLoader = new ImageLoader();
        }
        return sImageLoader;
    }

    public void downloadAndDraw(final String pUrl, final ImageView pView,
                                @Nullable final OnResultCallback<Bitmap, Void> pCallback, final int... pArgs) {
        if (findModel(pUrl) != -1) {
            risePriority(pUrl);
            recalculatePriorities();
            Log.d(TAG, "downloadAndDraw: UP PRIORITY FOR " + pUrl);
            return;
        }
        pView.setTag(pUrl);
        Bitmap cachedBitmap;
        synchronized (mLockObj) {
            cachedBitmap = mLruCache.get(pUrl);
        }
        Log.d(TAG, "downloadAndDraw: CACHED: " + (cachedBitmap == null ? "NO" : "YES") + " " + pUrl);
        if (cachedBitmap != null && pView.getTag() == pUrl) {
            Log.d(TAG, "downloadAndDraw: FROM LRU CACHE " + pUrl);
            if (pArgs.length != 0) {
                final Bitmap resizedBitmap = Bitmap.createScaledBitmap(cachedBitmap, pArgs[0], pArgs[1], true);
                pView.setImageBitmap(resizedBitmap);
            } else {
                pView.setImageBitmap(cachedBitmap);
            }
            if (pCallback != null) {
                pCallback.onSuccess(cachedBitmap);
            }
            return;
        }
        final Handler handler = new Handler(Looper.getMainLooper());
        final PriorityRunnable<String, Void, Bitmap> priorityRunnable = new PriorityRunnable<>(handler, new ITask<String, Void, Bitmap>() {
            @Override
            public Bitmap doInBackground(String pUrl, ProgressCallback<Void> pProgressCallback) {
                Bitmap bitmap = null;
                Log.d("startWith file://", pUrl.startsWith(FILE) + "");
                if (pUrl.startsWith(FILE)) {
                    Log.d("pUrl", pUrl);
                    Uri pUri = Uri.parse(pUrl);
                    bitmap = BitmapFactory.decodeFile(pUri.getPath());
                    return bitmap;
                } else if (pUrl.startsWith(HTTP)){
                    HttpURLConnection connection = null;
                    InputStream inputStream = null;
                    try {
                        connection = (HttpURLConnection) mHttpClient.getConnection(new Request.Builder().setUrl(pUrl).setMethod("GET").build());
                        inputStream = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    } catch (final Exception e) {
                        Log.e(TAG, "download: ", e);
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (final IOException e) {
                                Log.e(TAG, "download: inputStream did not closed: ", e);
                            }
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                    return bitmap;
                }
                return null;
            }
        },
                pUrl,
                null,
                new OnResultCallback<Bitmap, Void>()

                {

                    @Override
                    public void onProgressChanged(Void pVoid) {

                    }

                    private void cleanStack() {
                        synchronized (mLockObj) {
                            if (findModel(pUrl) != -1) {
                                mDownloadPriorities.remove(findModel(pUrl));
                            }
                        }
                        recalculatePriorities();
                    }

                    @Override
                    public void onSuccess(final Bitmap pBitmap) {
                        if (pBitmap != null) {
                            Log.d(TAG, "onSuccess: DOWNLOADED " + pUrl);
                            Log.d(TAG, "onSuccess: LAY IN LRU");
                            synchronized (mLockObj) {
                                if (!mLruCache.snapshot().containsKey(pUrl)) {
                                    mLruCache.put(pUrl, pBitmap);
                                }
                            }
                            if (pView.getTag() == pUrl) {
                                if (pArgs.length != 0) {
                                    final Bitmap resizedBitmap = Bitmap.createScaledBitmap(pBitmap, pArgs[0], pArgs[1], true);
                                    pView.setImageBitmap(resizedBitmap);
                                } else {
                                    pView.setImageBitmap(pBitmap);
                                }
                                if (pCallback != null) {
                                    pCallback.onSuccess(pBitmap);
                                }
                            }
                        }
                        cleanStack();
                    }

                    @Override
                    public void onError(final Exception e) {
                        cleanStack();
                        Log.e(TAG, "onError: ", e);
                    }
                });
        final Priority model = new Priority(priorityRunnable);
        model.setUrl(pUrl);
        model.setPriority(Thread.MAX_PRIORITY);
        synchronized (mLockObj) {
            mDownloadPriorities.push(model);
        }
        recalculatePriorities();
        mThreadManager.execute(priorityRunnable);
    }

    private synchronized int findModel(final String pUrl) {
        int index = 0;
        for (final Priority priority : mDownloadPriorities) {
            if (priority.getUrl().equalsIgnoreCase(pUrl)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private synchronized void risePriority(final String pUrl) {
        final Priority modelToRise = mDownloadPriorities.remove(findModel(pUrl));
        modelToRise.setPriority(Thread.MAX_PRIORITY);
        mDownloadPriorities.push(modelToRise);
    }

    private synchronized void recalculatePriorities() {
        for (final Priority priorityModel : mDownloadPriorities) {
            int priority = Math.round((float) mDownloadPriorities.indexOf(priorityModel) * 10 / mDownloadPriorities.size());
            if (priority == 0) {
                priority = Thread.MIN_PRIORITY;
            }
            priorityModel.setPriority(priority);
        }
    }
}