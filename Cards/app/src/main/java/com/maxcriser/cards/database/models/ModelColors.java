package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbPrimaryKey;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "colorTable")
public final class ModelColors {

    @dbPrimaryKey
    @dbInteger
    public static final String COLOR_ID = "_id";

    @dbString
    public static final String COLOR_NAME = "name";

    @dbString
    public static final String COLOR_CODE = "code";
}