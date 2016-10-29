package com.maxcriser.cards.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                new StringBuilder()
                        .append("create table ")
                        .append("mytable")
                        .append(" (")
                        .append("id integer primary key autoincrement,")
                        .append("name text,")
                        .append("email text")
                        .append(");")
                        .toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
