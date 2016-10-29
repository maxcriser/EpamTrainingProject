package com.maxcriser.ownasynctask;

import android.util.Log;

public class WorkerOperation implements Task<String, Integer, WorkerOperation.Result> {

    public static final int N = 3;

    @Override
    public Result doInBackground(String pS, ProgressCallback<Integer> pIntegerProgressCallback) throws Exception {
        for (int i = 0; i < N; i++) {
            try {
                Thread.currentThread().sleep(1000L);
            }catch (InterruptedException e){
                throw  new Exception(e);
            }
            Log.d("TAG", pS + i);
        }

        Result result = new Result();
        result.n = N;
        result.result = "Test my result" + pS + " " + N;
        return result;
    }

    public static class Result {
        private int n;

        private String result;

        public int getN() {
            return n;
        }

        public String getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "Result{" + "n=" + n + ", result='" + result + '\'' + "}";
        }
    }

}
