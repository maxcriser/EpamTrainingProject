package com.maxcriser.cards.database.models;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbString;

@Table(name = "previewColors")
public final class ModelPreviewColors {

    @dbInteger
    public static final String COLORS_ID = "_id";

    @dbString
    public static final String COLOR_NAME = "colorName";

    @dbString
    public static final String COLOR_CODE = "colorCode";
}