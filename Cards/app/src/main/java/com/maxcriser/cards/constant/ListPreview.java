package com.maxcriser.cards.constant;

import com.maxcriser.cards.model.PreviewColor;

import java.util.ArrayList;
import java.util.List;

public final class ListPreview {

    private static final String COLOR_TEAL = "#009688";
    private static final String TEAL = "TEAL";
    private static final String COLOR_CYAN = "#00BCD4";
    private static final String CYAN = "CYAN";
    private static final String COLOR_BLUE = "#4f81b6";
    private static final String BLUE = "BLUE";
    private static final String COLOR_GRAY = "#78909c";
    private static final String GRAY = "GRAY";
    private static final String COLOR_RED = "#da513f";
    private static final String RED = "RED";

    public static final List<PreviewColor> colors = new ArrayList<PreviewColor>() {

        {
            add(new PreviewColor(TEAL, COLOR_TEAL));
            add(new PreviewColor(CYAN, COLOR_CYAN));
            add(new PreviewColor(BLUE, COLOR_BLUE));
            add(new PreviewColor(GRAY, COLOR_GRAY));
            add(new PreviewColor(RED, COLOR_RED));
        }
    };

    public static final List<String> types = new ArrayList<String>() {

        {
            add(ListConstants.Cards.VISA);
            add(ListConstants.Cards.MASTERCARD);
            add(ListConstants.Cards.AMEX);
            add(ListConstants.Cards.MAESTRO);
            add(ListConstants.Cards.WESTERN_UNION);
            add(ListConstants.Cards.JCB);
            add(ListConstants.Cards.DINERS_CLUB);
            add(ListConstants.Cards.BELCARD);
        }
    };
}