package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbPrimaryKey;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "nfcTable")
public final class ModelNFCItems {

    @dbPrimaryKey
    @dbInteger
    public static final String ID = "_id";

    @dbString
    public static final String TITLE = "title";

    @dbString
    public static final String TAG = "tag";

    @dbString
    public static final String BACKGROUND_COLOR = "backgroundColor";
}