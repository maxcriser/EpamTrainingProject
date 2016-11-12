package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbPrimaryKey;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "ticketsTable")
public final class ModelTickets {

    @dbPrimaryKey
    @dbInteger
    public static final String TICKETS_ID = "_id";

    @dbString
    public static final String TICKETS_TITLE = "title";

    @dbString
    public static final String TICKETS_PHOTO_FIRST = "firstPhoto";

    @dbString
    public static final String TICKETS_PHOTO_SECOND = "secondPhoto";

    @dbString
    public static final String TICKETS_BACKGROUND_COLOR = "backgroundColor";
}