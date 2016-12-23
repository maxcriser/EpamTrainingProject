package com.maxcriser.cards.database;

import com.maxcriser.cards.database.models.ModelBankCards;

public class Sql {

    public static String getSqlWithQuery(final Class model) {
        return "SELECT * FROM " + DatabaseHelperImpl.getTableName(model) + " WHERE " + ModelBankCards.TITLE + " LIKE ? ";
    }

    public static String getSqlAllItems(final Class model) {
        return "SELECT * FROM " + DatabaseHelperImpl.getTableName(model);
    }

}
