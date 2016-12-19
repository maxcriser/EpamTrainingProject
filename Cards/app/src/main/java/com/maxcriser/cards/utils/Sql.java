package com.maxcriser.cards.utils;

import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;

public class Sql {

    public static String getSqlWithQuery(Class model) {
        return "SELECT * FROM " + DatabaseHelperImpl.getTableName(model) + " WHERE " + ModelBankCards.TITLE + " LIKE ? ";
    }

    public static String getSqlAllItems(Class model) {
        return "SELECT * FROM " + DatabaseHelperImpl.getTableName(model);
    }
}
