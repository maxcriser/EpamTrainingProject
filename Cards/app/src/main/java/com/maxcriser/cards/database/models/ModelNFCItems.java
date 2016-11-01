package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "nfcTable")
public final class ModelNFCItems {

    @dbInteger
    public static final String NFC_ID = "_id";

    @dbString
    public static final String NFC_TITLE = "title";

    @dbString
    public static final String NFC_NFC = "nfc";

    @dbString
    public static final String NFC_BACKGROUND_COLOR = "backgroundColor";
}