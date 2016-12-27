package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.typeInteger;
import com.maxcriser.cards.database.annotations.typePrimaryKey;
import com.maxcriser.cards.database.annotations.typeString;

@Table(name = "ticketsTable")
public final class ModelTickets {

    @typePrimaryKey
    @typeInteger
    public static final String ID = "_id";

    @typeString
    public static final String TITLE = "title";

    @typeString
    public static final String CARDHOLDER = "cardholder";

    @typeString
    public static final String DATE = "date";

    @typeString
    public static final String TIME = "time";

    @typeString
    public static final String PHOTO_FIRST = "firstPhoto";

    @typeString
    public static final String PHOTO_SECOND = "secondPhoto";

    @typeString
    public static final String BACKGROUND_COLOR = "backgroundColor";
}