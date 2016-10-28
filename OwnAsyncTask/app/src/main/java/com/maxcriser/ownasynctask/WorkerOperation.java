package com.maxcriser.ownasynctask;

public class WorkerOperation implements Task<String, Integer, WorkerOperation.Result> {

    public static final int N = 3;

    @Override
    public Result doInBackground(String pS, ProgressCallback<Integer> pIntegerProgressCallback) throws Exception {
        return null;
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

        public String toString() {
            return "Result{" + "n=" + n + ", result='" + result + '\'' + "}";
        }
    }

}
