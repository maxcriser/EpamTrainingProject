package com.maxcriser.ownasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FalseAsyncTask falseAsyncTask = new FalseAsyncTask();
        falseAsyncTask.execute(new WorkerOperation(), "krakazabrik", new OnResultCallback<WorkerOperation.Result, Integer>() {
            @Override
            public void onSuccess(WorkerOperation.Result pResult) {
                Toast.makeText(MainActivity.this, "SUCCESS " + pResult.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception pE) {
                Toast.makeText(MainActivity.this, "ERROR " + pE.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onProgressChanged(Integer pInteger) {
                Toast.makeText(MainActivity.this, "PROGRESS " + pInteger.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
}