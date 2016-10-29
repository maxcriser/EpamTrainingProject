package com.maxcriser.databasetester;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Second extends Activity {

    DB db;
    SimpleCursorAdapter scAdapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        db = new DB(this);
        db.open();
        String[] from = new String[] { DB.COLUMN_IMG, DB.COLUMN_TXT };
        int[] to = new int[] { R.id.ivImg, R.id.tvText };
        scAdapter = new SimpleCursorAdapter(this, R.layout.item, null, from, to, 0);

    }

    public void ADD(View view) {
        db.addRec("sometext " + (scAdapter.getCount() + 1), R.drawable.ic_launcher);

    }
}