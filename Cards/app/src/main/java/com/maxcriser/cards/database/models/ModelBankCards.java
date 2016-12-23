package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbPrimaryKey;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "bankTable")
public final class ModelBankCards {

    @dbPrimaryKey
    @dbInteger
    public static final String ID = "_id";

    @dbString
    public static final String TYPE = "type"; // VISA / MASTERCARD and e.t.c.

    @dbString
    public static final String TITLE = "title";

    @dbString
    public static final String VERIFICATION_NUMBER = "verification_number";

    @dbString
    public static final String CARDHOLDER = "cardholder";

    @dbString
    public static final String PHOTO_FRONT = "frontPhoto";

    @dbString
    public static final String PHOTO_BACK = "backPhoto";

    @dbString
    public static final String NUMBER = "number";

    @dbString
    public static final String VALID = "valid";

    @dbString
    public static final String BACKGROUND_COLOR = "backgroundColor";

    @dbInteger
    public static final String PIN = "pin";
}