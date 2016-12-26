package android.support.v4.p005d;

import android.os.AsyncTask;

/* renamed from: android.support.v4.d.b */
class C0082b {
    static <Params, Progress, Result> void m384a(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramsArr);
    }
}
