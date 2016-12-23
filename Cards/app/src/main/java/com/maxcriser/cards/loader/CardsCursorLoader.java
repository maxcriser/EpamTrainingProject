package com.maxcriser.cards.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

import com.maxcriser.cards.database.DatabaseHelperImpl;

import static com.maxcriser.cards.database.Sql.getSqlAllItems;
import static com.maxcriser.cards.database.Sql.getSqlWithQuery;

public class CardsCursorLoader extends CursorLoader {

    private final String SQL_WITH_QUERY;// = "SELECT * FROM " + DatabaseHelperImpl.getTableName(ModelBankCards.class) + " WHERE " + ModelBankCards.TITLE + " LIKE ? ";
    private final String SQL_ALL_ITEMS;// = "SELECT * FROM " + DatabaseHelperImpl.getTableName(ModelBankCards.class);
    private final DatabaseHelperImpl db;
    private final String mQuery;

    public CardsCursorLoader(final Context context, final String pQuery, final Class modelClass) {
        super(context);
        SQL_WITH_QUERY = getSqlWithQuery(modelClass);
        SQL_ALL_ITEMS = getSqlAllItems(modelClass);
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