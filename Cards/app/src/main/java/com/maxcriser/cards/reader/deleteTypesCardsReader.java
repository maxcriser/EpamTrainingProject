package com.maxcriser.cards.reader;

import java.util.ArrayList;
import java.util.List;

public class deleteTypesCardsReader {

    private static deleteTypesCardsReader sDeleteTypesCardsReader;

    private List<String> typesCards;
    private List<String> tickets;
    private List<String> bankCards;
    private List<String> nfcCards;

    public void setBankCards() {
        bankCards = new ArrayList<>();
        bankCards.add("Belinvestbank");
        bankCards.add("Priorbank");
        bankCards.add("Belarusbank");
        bankCards.add("Belarusbank");
        bankCards.add("Belarusbank");
        bankCards.add("Belarusbank");
        bankCards.add("Belarusbank");
        bankCards.add("Bank of America");
    }

    public void setTickets() {
        tickets = new ArrayList<>();
        tickets.add("Ticket to HOME");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("ticket to ITALY");
        tickets.add("Ticket to ASS");
    }

    public void setTypesCards() {
        typesCards = new ArrayList<>();
        typesCards.add("Credit cards");
        typesCards.add("Discount cards");
        typesCards.add("Tickets");
        typesCards.add("NFC items");
    }

    public void setNfcCards() {
        nfcCards = new ArrayList<>();
        nfcCards.add("NFC 1");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
        nfcCards.add("NFC 2");
    }

    private deleteTypesCardsReader() {

    }

    public static deleteTypesCardsReader getInstance() {
        if (sDeleteTypesCardsReader == null) {
            sDeleteTypesCardsReader = new deleteTypesCardsReader();
        }
        return sDeleteTypesCardsReader;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public List<String> getTypesCards() {
        return typesCards;
    }

    public List<String> getBankCards() {
        return bankCards;
    }

    public List<String> getNfcCards() {
        return nfcCards;
    }
}