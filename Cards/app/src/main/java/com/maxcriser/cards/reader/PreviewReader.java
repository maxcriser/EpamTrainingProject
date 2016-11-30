package com.maxcriser.cards.reader;

import java.util.ArrayList;
import java.util.List;

import static com.maxcriser.cards.constant.BankCardTypes.AMEX;
import static com.maxcriser.cards.constant.BankCardTypes.BELCARD;
import static com.maxcriser.cards.constant.BankCardTypes.DINERS_CLUB;
import static com.maxcriser.cards.constant.BankCardTypes.JCB;
import static com.maxcriser.cards.constant.BankCardTypes.MAESTRO;
import static com.maxcriser.cards.constant.BankCardTypes.MASTERCARD;
import static com.maxcriser.cards.constant.BankCardTypes.VISA;
import static com.maxcriser.cards.constant.BankCardTypes.WESTERN_UNION;

public class PreviewReader {

    private List<Colors> previewColors;
    private List<String> typeCard;

    private static PreviewReader sPreviewReader;

    private PreviewReader() {
        previewColors = new ArrayList<>();
    }

    public void setTypeCard() {
        typeCard = new ArrayList<>();
        typeCard.add(VISA);
        typeCard.add(MASTERCARD);
        typeCard.add(AMEX);
        typeCard.add(MAESTRO);
        typeCard.add(WESTERN_UNION);
        typeCard.add(JCB);
        typeCard.add(DINERS_CLUB);
        typeCard.add(BELCARD);
    }

    private void addPreviewColors(String name, String color) {
        Colors listTableItems = new Colors();
        listTableItems.setNameColorCards(name);
        listTableItems.setCodeColorCards(color);

        previewColors.add(listTableItems);
    }

    public void setPreviewColors() {
        addPreviewColors("Standard", "#00abbf");
        addPreviewColors("Pink", "#e71e62");
        addPreviewColors("Purple", "#663ab5");
        addPreviewColors("Blue", "#03A9F4");
        addPreviewColors("Cyan", "#00BCD4");
        addPreviewColors("Teal", "#009688");
        addPreviewColors("Lime", "#CDDC39");
    }

    public List<Colors> getPreviewColors() {
        return previewColors;
    }

    public List<String> getTypeCard() {
        return typeCard;
    }

    public static PreviewReader getInstance() {
        if (sPreviewReader == null) {
            sPreviewReader = new PreviewReader();
        }
        return sPreviewReader;
    }

}