package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelDiscountCards;

public class DiscountCursorLoader extends CursorLoader {

    private static String SQL_WITH_QUERY = "SELECT * FROM " + DatabaseHelper.getTableName(ModelDiscountCards.class) + " WHERE " + ModelDiscountCards.DISCOUNT_TITLE + " LIKE ? ";
    private static String SQL_ALL_ITEMS = "SELECT * FROM " + DatabaseHelper.getTableName(ModelDiscountCards.class);
    private DatabaseHelper db;
    private String mQuery;

    public DiscountCursorLoader(Context context, String pQuery) {
        super(context);
        this.db = DatabaseHelper.getInstance(context, 1);
        mQuery = pQuery;
    }

    @Override
    public Cursor loadInBackground() {
        final SQLiteDatabase database = db.getReadableDatabase();
        if (mQuery == null || mQuery.isEmpty()) {
            return database.rawQuery(SQL_ALL_ITEMS, null);
        } else {
            return database.rawQuery(SQL_WITH_QUERY, new String[]{"%" + mQuery + "%"});
        }
    }
}