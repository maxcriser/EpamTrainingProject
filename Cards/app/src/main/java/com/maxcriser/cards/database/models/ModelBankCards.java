package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbPrimaryKey;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "bankTable")
public final class ModelBankCards {

    @dbPrimaryKey
    @dbInteger
    public static final String BANK_ID = "_id";

    @dbString
    public static final String BANK_TYPE = "type"; // VISA / MASTERCARD and e.t.c.

    @dbString
    public static final String BANK_TITLE = "title";

    @dbString
    public static final String BANK_CARDHOLDER = "cardholder";

    @dbString
    public static final String BANK_NUMBER = "number";

    @dbString
    public static final String BANK_VALID = "valid";

    @dbString
    public static final String BANK_BACKGROUND_COLOR = "backgroundColor";

    @dbInteger
    public static final String BANK_PIN = "pin";

    @dbString
    public static final String BANK_NFC = "nfc";
}