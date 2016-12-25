package com.maxcriser.cards.model;

public class PreviewColor {

    private final String nameColorCards;
    private final String codeColorCards;

    public PreviewColor(final String name, final String code) {
        this.nameColorCards = name;
        this.codeColorCards = code;
    }

    public String getNameColorCards() {
        return nameColorCards;
    }

    public String getCodeColorCards() {
        return codeColorCards;
    }
}