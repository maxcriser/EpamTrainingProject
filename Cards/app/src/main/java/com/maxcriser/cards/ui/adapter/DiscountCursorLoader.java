package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import android.support.v4.content.CursorLoader;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelDiscountCards;

public class DiscountCursorLoader extends CursorLoader {

    private DatabaseHelper db;

    public DiscountCursorLoader(Context context) {
        super(context);
        this.db = DatabaseHelper.getInstance(context, 1);
    }

    @Override
    public Cursor loadInBackground() {
        final SQLiteDatabase database = db.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabaseHelper.getTableName(ModelDiscountCards.class);
        return database.rawQuery(sql, null);
    }
}