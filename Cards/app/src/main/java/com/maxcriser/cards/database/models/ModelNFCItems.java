package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.typeInteger;
import com.maxcriser.cards.database.annotations.typePrimaryKey;
import com.maxcriser.cards.database.annotations.typeString;

@Table(name = "nfcTable")
public final class ModelNFCItems {

    @typePrimaryKey
    @typeInteger
    public static final String ID = "_id";

    @typeString
    public static final String TITLE = "title";

    @typeString
    public static final String TAG = "tag";

    @typeString
    public static final String BACKGROUND_COLOR = "backgroundColor";
}