package com.maxcriser.cards.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;

public class CardsCursorLoader extends CursorLoader {

    private static String SQL_WITH_QUERY;// = "SELECT * FROM " + DatabaseHelperImpl.getTableName(ModelBankCards.class) + " WHERE " + ModelBankCards.TITLE + " LIKE ? ";
    private static String SQL_ALL_ITEMS;// = "SELECT * FROM " + DatabaseHelperImpl.getTableName(ModelBankCards.class);
    private DatabaseHelperImpl db;
    private String mQuery;

    public CardsCursorLoader(Context context, String pQuery, Class modelClass) {
        super(context);
        //TODO move to some sql utils
        SQL_WITH_QUERY = "SELECT * FROM " + DatabaseHelperImpl.getTableName(modelClass) + " WHERE " + ModelBankCards.TITLE + " LIKE ? ";
        SQL_ALL_ITEMS = "SELECT * FROM " + DatabaseHelperImpl.getTableName(modelClass);
        this.db = DatabaseHelperImpl.getInstance(context);
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