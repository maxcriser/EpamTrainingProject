package com.maxcriser.cards.reader;

import java.util.ArrayList;
import java.util.List;

public class TypesCardsReader {

    private static TypesCardsReader sTypesCardsReader;

    private List<String> typesCards;

    public void setTypesCards() {
        typesCards = new ArrayList<>();
        typesCards.add("Start a group chat");
        typesCards.add("Discount cards");
        typesCards.add("Tickets");
        typesCards.add("NFC items");
    }

    private TypesCardsReader() {

    }

    public static TypesCardsReader getInstance() {
        if (sTypesCardsReader == null) {
            sTypesCardsReader = new TypesCardsReader();
        }
        return sTypesCardsReader;
    }

    public List<String> getTypesCards() {
        return typesCards;
    }
}