package com.maxcriser.cards.model;

import com.maxcriser.cards.constant.ListConstants;

import java.util.ArrayList;
import java.util.List;

public final class PreviewListColorsSetter {

    private static final String TEAL = "TEAL";
    private static final String COLOR_TEAL = "#009688";
    private static final String CYAN = "CYAN";
    private static final String COLOR_CYAN = "#00BCD4";
    private static final String BLUE = "BLUE";
    private static final String GRAY = "GRAY";
    private static final String COLOR_BLUE = "#4f81b6";
    private static final String COLOR_GRAY = "#78909c";
    private static final String RED = "RED";
    private static final String COLOR_RED = "#da513f";
    private final List<PreviewColor> mPreviewColorSetters;
    private List<String> typeCard;

    private static PreviewListColorsSetter sPreviewListColorsSetter;

    private PreviewListColorsSetter() {
        mPreviewColorSetters = new ArrayList<>();
    }

    public void setTypeCard() {
        typeCard = new ArrayList<>();
        typeCard.add(ListConstants.Cards.VISA);
        typeCard.add(ListConstants.Cards.MASTERCARD);
        typeCard.add(ListConstants.Cards.AMEX);
        typeCard.add(ListConstants.Cards.MAESTRO);
        typeCard.add(ListConstants.Cards.WESTERN_UNION);
        typeCard.add(ListConstants.Cards.JCB);
        typeCard.add(ListConstants.Cards.DINERS_CLUB);
        typeCard.add(ListConstants.Cards.BELCARD);
    }

    private void addPreviewColors(final String name, final String color) {
        final PreviewColor listTableItems = new PreviewColor();
        listTableItems.setNameColorCards(name);
        listTableItems.setCodeColorCards(color);
        mPreviewColorSetters.add(listTableItems);
    }

    public void setPreviewColors() {
        addPreviewColors(TEAL, COLOR_TEAL);
        addPreviewColors(CYAN, COLOR_CYAN);
        addPreviewColors(BLUE, COLOR_BLUE);
        addPreviewColors(GRAY, COLOR_GRAY);
        addPreviewColors(RED, COLOR_RED);
    }

    public List<PreviewColor> getPreviewColorSetters() {
        return mPreviewColorSetters;
    }

    public List<String> getTypeCard() {
        return typeCard;
    }

    //TODO static
    public static PreviewListColorsSetter getInstance() {
        if (sPreviewListColorsSetter == null) {
            sPreviewListColorsSetter = new PreviewListColorsSetter();
        }
        return sPreviewListColorsSetter;
    }
}