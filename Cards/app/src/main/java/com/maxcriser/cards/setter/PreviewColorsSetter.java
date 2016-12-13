package com.maxcriser.cards.setter;

public class PreviewColorsSetter {

    private String nameColorCards;
    private String codeColorCards;

    void setNameColorCards(String pNameColorCards) {
        nameColorCards = pNameColorCards;
    }

    void setCodeColorCards(String pCodeColorCards) {
        codeColorCards = pCodeColorCards;
    }

    public String getNameColorCards() {
        return nameColorCards;
    }

    public String getCodeColorCards() {
        return codeColorCards;
    }
}