package com.maxcriser.cards.database.models;

import android.content.ContentValues;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbPrimaryKey;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "ticketsTable")
public final class ModelTickets {

    @dbPrimaryKey
    @dbInteger
    public static final String ID = "_id";

    @dbString
    public static final String TITLE = "title";

    @dbString
    public static final String CARDHOLDER = "cardholder";

    @dbString
    public static final String DATE = "date";

    @dbString
    public static final String TIME = "time";

    @dbString
    public static final String PHOTO_FIRST = "firstPhoto";

    @dbString
    public static final String PHOTO_SECOND = "secondPhoto";

    @dbString
    public static final String BACKGROUND_COLOR = "backgroundColor";
}