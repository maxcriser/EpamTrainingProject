package com.maxcriser.cards.reader;

import java.util.ArrayList;
import java.util.List;

public class deleteTypesCardsReader {

    private static deleteTypesCardsReader sDeleteTypesCardsReader;

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
        tickets.add("Home");
        tickets.add("United States");
        tickets.add("Belarus, Minsk");
        tickets.add("Rome");
        tickets.add("Antananarivo");
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

    public List<String> getBankCards() {
        return bankCards;
    }

    public List<String> getNfcCards() {
        return nfcCards;
    }
}