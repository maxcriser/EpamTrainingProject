package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.typeInteger;
import com.maxcriser.cards.database.annotations.typePrimaryKey;
import com.maxcriser.cards.database.annotations.typeString;

@Table(name = "discountTable")
public final class ModelDiscountCards {

    @typePrimaryKey
    @typeInteger
    public static final String ID = "_id";

    @typeString
    public static final String TITLE = "title";

    @typeString
    public static final String PHOTO_FRONT = "frontPhoto";

    @typeString
    public static final String PHOTO_BACK = "backPhoto";

    @typeString
    public static final String BARCODE = "barcode";
}