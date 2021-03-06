package com.maxcriser.cards.reader;

import java.util.ArrayList;
import java.util.List;

public class TypesCardsReader {

    private static TypesCardsReader sTypesCardsReader;

    private List<String> typesCards;
    private List<String> typesBankCards;
    private List<String> tickets;
    private List<String> discountCards;
    private List<String> openBankCards;

    public void setOpenBankCards() {
        openBankCards = new ArrayList<>();
        openBankCards.add("Belinvestbank");
        openBankCards.add("Priorbank");
        openBankCards.add("Belarusbank");
        openBankCards.add("Bank of America");
    }

    public void setDiscountCards() {
        discountCards = new ArrayList<>();
        discountCards.add("ALMI");
        discountCards.add("EUROOPT");
        discountCards.add("RODNUI KUY");
        discountCards.add("BELMARKET");
        discountCards.add("DOBRONOM");
        discountCards.add("GIPPO");
        discountCards.add("5 KOPEEK");
        discountCards.add("WOOL MARK");
        discountCards.add("STARBUCKS");
        discountCards.add("ВИОР");
        discountCards.add("ВИШНЕВЕЦ");
        discountCards.add("ВЕСТА");
        discountCards.add("СОЛНЕЧНЫЙ вкусный сыр");
        discountCards.add("ALMI");
        discountCards.add("ALMI");
        discountCards.add("EUROOPT");
        discountCards.add("RODNUI KUY");
        discountCards.add("BELMARKET");
        discountCards.add("DOBRONOM");
        discountCards.add("GIPPO");
        discountCards.add("5 KOPEEK");
        discountCards.add("WOOL MARK");
        discountCards.add("STARBUCKS");
        discountCards.add("ВИОР");
        discountCards.add("ВИШНЕВЕЦ");
        discountCards.add("ВЕСТА");
        discountCards.add("СОЛНЕЧНЫЙ вкусный сыр");
        discountCards.add("ALMI");
        discountCards.add("ALMI");
        discountCards.add("EUROOPT");
        discountCards.add("RODNUI KUY");
        discountCards.add("BELMARKET");
        discountCards.add("DOBRONOM");
        discountCards.add("GIPPO");
        discountCards.add("5 KOPEEK");
        discountCards.add("WOOL MARK");
        discountCards.add("STARBUCKS");
        discountCards.add("ВИОР");
        discountCards.add("ВИШНЕВЕЦ");
        discountCards.add("ВЕСТА");
        discountCards.add("СОЛНЕЧНЫЙ вкусный сыр");
        discountCards.add("ALMI");
    }


    public void setTickets() {
        tickets = new ArrayList<>();
//        tickets.add("Ticket to HOME");
//        tickets.add("ticket to ITALY");
//        tickets.add("Ticket to ASS");
    }

    public void setTypesCards() {
        typesCards = new ArrayList<>();
        typesCards.add("Bank cards");
        typesCards.add("Discount cards");
        typesCards.add("Tickets");
    }

    public void setTypesBankCards() {
        typesBankCards = new ArrayList<>();
        typesBankCards.add("Protected");
        typesBankCards.add("Open");
    }

    TypesCardsReader() {

    }

    public static TypesCardsReader getInstance() {
        if (sTypesCardsReader == null) {
            sTypesCardsReader = new TypesCardsReader();
        }
        return sTypesCardsReader;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public List<String> getTypesCards() {
        return typesCards;
    }

    public List<String> getTypesBankCards() {
        return typesBankCards;
    }

    public List<String> getDiscountCards() {
        return discountCards;
    }

    public List<String> getOpenBankCards() {
        return openBankCards;
    }
}