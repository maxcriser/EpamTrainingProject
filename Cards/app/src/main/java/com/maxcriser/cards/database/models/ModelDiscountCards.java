package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "discountTable")
public final class ModelDiscountCards {

    @dbInteger
    public static final String DISCOUNT_ID = "_id";

    @dbString
    public static final String DISCOUNT_TITLE = "title";

    @dbString
    public static final String DISCOUNT_BARCODE = "barcode";

    @dbString
    public static final String DISCOUNT_BACKGROUND_COLOR = "backgroundColor";
}