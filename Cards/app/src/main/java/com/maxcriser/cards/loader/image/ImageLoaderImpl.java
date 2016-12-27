package com.maxcriser.cards.loader.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.widget.ImageView;

import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.http.HttpClient;
import com.maxcriser.cards.model.PriorityModel;
import com.maxcriser.cards.model.Request;
import com.maxcriser.cards.thread.ITask;
import com.maxcriser.cards.thread.PriorityRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Stack;

import static com.maxcriser.cards.constant.ListConstants.FILE;
import static com.maxcriser.cards.constant.ListConstants.HTTP;

class ImageLoaderImpl implements ImageLoader {

    private final HttpClient mHttpClient;
    private final Object mLockSynchronized = new Object();
    private final ThreadManager mThreadManager;
    private final LruCache<String, Bitmap> mLruCache;
    private final Stack<PriorityModel> mStackPriorities;

    ImageLoaderImpl() {
        mThreadManager = ThreadManager.getInstance();
        mStackPriorities = new Stack<>();
        mHttpClient = new HttpClient();
        final long size;
        final int defaultCacheSize = 12 * 1024 * 1024;
        if (Runtime.getRuntime().maxMemory() / 4 > defaultCacheSize) {
            size = defaultCacheSize;
        } else {
            size = Runtime.getRuntime().maxMemory() / 4;
        }
        mLruCache = new LruCache<String, Bitmap>(((int) size)) {

            @Override
            protected int sizeOf(final String pKey, final Bitmap pValue) {
                return pKey.getBytes().length + pValue.getByteCount();
            }

        };
    }

    public void downloadToView(final String pUrl, final ImageView pView,
                               @Nullable final OnResultCallback<Bitmap, Void> pCallback, final int... pArgs) {
        if (findModel(pUrl) != -1) {
            risePriority(pUrl);
            recalculatePriorities();
            return;
        }
        pView.setTag(pUrl);
        final Bitmap cachedBitmap;
        synchronized (mLockSynchronized) {
            cachedBitmap = mLruCache.get(pUrl);
        }
        if (cachedBitmap != null && pView.getTag().equals(pUrl)) {
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
            public Bitmap doInBackground(final String pUrl, final ProgressCallback<Void> pProgressCallback) throws Exception {
                Bitmap bitmap = null;
                if (pUrl.startsWith(FILE)) {
                    final Uri pUri = Uri.parse(pUrl);
                    final Bitmap decodeFile = BitmapFactory.decodeFile(pUri.getPath());
                    if (pArgs.length != 0) {
                        return Bitmap.createScaledBitmap(decodeFile, pArgs[0], pArgs[1], true);
                    } else {
                        return decodeFile;
                    }
                } else if (pUrl.startsWith(HTTP)) {
                    HttpURLConnection connection = null;
                    InputStream inputStream = null;
                    try {
                        connection = (HttpURLConnection) mHttpClient.getConnection(new Request.Builder().setUrl(pUrl).setMethod("GET").build());
                        inputStream = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    } catch (final Exception e) {
                        throw new Exception(e);
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (final IOException e) {
                                throw new Exception(e);
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
                    public void onProgressChanged(final Void pVoid) {

                    }

                    private void cleanStack() {
                        synchronized (mLockSynchronized) {
                            if (findModel(pUrl) != -1) {
                                mStackPriorities.remove(findModel(pUrl));
                            }
                        }
                        recalculatePriorities();
                    }

                    @Override
                    public void onSuccess(final Bitmap pBitmap) {
                        if (pBitmap != null) {
                            synchronized (mLockSynchronized) {
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
                    }
                });
        final PriorityModel model = new PriorityModel(priorityRunnable);
        model.setUrl(pUrl);
        model.setPriority(Thread.MAX_PRIORITY);
        synchronized (mLockSynchronized) {
            mStackPriorities.push(model);
        }
        recalculatePriorities();
        mThreadManager.execute(priorityRunnable);
    }

    private synchronized int findModel(final String pUrl) {
        int index = 0;
        for (final PriorityModel priorityModel : mStackPriorities) {
            if (priorityModel.getUrl().equalsIgnoreCase(pUrl)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private synchronized void risePriority(final String pUrl) {
        final PriorityModel modelToRise = mStackPriorities.remove(findModel(pUrl));
        modelToRise.setPriority(Thread.MAX_PRIORITY);
        mStackPriorities.push(modelToRise);
    }

    private synchronized void recalculatePriorities() {
        for (final PriorityModel priorityModelModel : mStackPriorities) {
            int priority = Math.round((float) mStackPriorities.indexOf(priorityModelModel) * 10 / mStackPriorities.size());
            if (priority == 0) {
                priority = Thread.MIN_PRIORITY;
            }
            priorityModelModel.setPriority(priority);
        }
    }
}