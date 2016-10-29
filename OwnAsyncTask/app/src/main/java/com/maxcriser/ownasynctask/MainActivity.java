package com.maxcriser.ownasynctask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends Activity {

    Button start;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button)findViewById(R.id.btnStart);

        FalseAsyncTask threadManager = new FalseAsyncTask();
        threadManager.execute(new DBOperation(), 2, new OnResultCallback<String, Integer>() {
            @Override
            public void onProgressChanged(final Integer integer) {
                Toast.makeText(MainActivity.this, "progress " + integer, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(final String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                start.setText(result);
            }

            @Override
            public void onError(final Exception e) {
                Toast.makeText(MainActivity.this, "error " + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}