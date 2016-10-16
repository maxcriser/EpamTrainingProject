package com.maxcriser.cards;

public class TypesCardsReader {

    private static TypesCardsReader sTypesCardsReader;

    private String[] types;

    private TypesCardsReader() {

    }

    public static TypesCardsReader getInstance() {
        if (sTypesCardsReader == null) {
            sTypesCardsReader = new TypesCardsReader();
        }
        return sTypesCardsReader;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes() {
        types = new String[]{
                "Bank cards",
                "Discount cards",
                "Tickets"
        };

        // some method of read types of cards (database)
    }
}
