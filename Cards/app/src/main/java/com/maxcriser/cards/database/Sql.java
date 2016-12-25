package com.maxcriser.cards.database;

import com.maxcriser.cards.database.models.ModelBankCards;

import java.lang.reflect.AnnotatedElement;

public final class Sql {

    public static String getSqlWithQuery(final AnnotatedElement model) {
        return "SELECT * FROM " + DatabaseHelperImpl.getTableName(model) + " WHERE " + ModelBankCards.TITLE + " LIKE ? ";
    }

    public static String getSqlAllItems(final AnnotatedElement model) {
        return "SELECT * FROM " + DatabaseHelperImpl.getTableName(model);
    }

}
