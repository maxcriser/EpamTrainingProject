package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.typeInteger;
import com.maxcriser.cards.database.annotations.typePrimaryKey;
import com.maxcriser.cards.database.annotations.typeString;

@Table(name = "bankTable")
public final class ModelBankCards {

    @typePrimaryKey
    @typeInteger
    public static final String ID = "_id";

    @typeString
    public static final String TYPE = "type";

    @typeString
    public static final String TITLE = "title";

    @typeString
    public static final String VERIFICATION_NUMBER = "verification_number";

    @typeString
    public static final String CARDHOLDER = "cardholder";

    @typeString
    public static final String PHOTO_FRONT = "frontPhoto";

    @typeString
    public static final String PHOTO_BACK = "backPhoto";

    @typeString
    public static final String NUMBER = "number";

    @typeString
    public static final String VALID = "valid";

    @typeString
    public static final String BACKGROUND_COLOR = "backgroundColor";

    @typeInteger
    public static final String PIN = "pin";
}