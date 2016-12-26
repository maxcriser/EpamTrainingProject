package android.support.v4.p005d;

import android.os.AsyncTask;
import android.os.Build.VERSION;

/* renamed from: android.support.v4.d.a */
public class C0081a {
    public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> m383a(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        if (asyncTask == null) {
            throw new IllegalArgumentException("task can not be null");
        }
        if (VERSION.SDK_INT >= 11) {
            C0082b.m384a(asyncTask, paramsArr);
        } else {
            asyncTask.execute(paramsArr);
        }
        return asyncTask;
    }
}
