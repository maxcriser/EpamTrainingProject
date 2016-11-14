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

    private List<ColorCardsSG> previewColors;
    private List<String> typeCard;

    private static PreviewReader sPreviewReader;

    PreviewReader() {
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

    public void addPreviewColors(String name, String color){
        ColorCardsSG listTableItems = new ColorCardsSG();
        listTableItems.setNameColorCards(name);
        listTableItems.setCodeColorCards(color);

        previewColors.add(listTableItems);
    }

    public void setPreviewColors() {

        addPreviewColors("standard", "#0b8c8f");
        addPreviewColors("red", "#F44336");
        addPreviewColors("pink", "#E91E63");
        addPreviewColors("purple", "#9C27B0");
        addPreviewColors("deep purple", "#673AB7");
        addPreviewColors("indigo", "#3F51B5");
        addPreviewColors("blue", "#2196F3");
        addPreviewColors("light blue", "#03A9F4");
        addPreviewColors("cyan", "#00BCD4");
        addPreviewColors("teal", "#009688");
        addPreviewColors("green", "#4CAF50");
        addPreviewColors("light green", "#8BC34A");
        addPreviewColors("lime", "#CDDC39");
        addPreviewColors("amber", "#FFC107");
        addPreviewColors("orange", "#FF9800");
        addPreviewColors("deep orange", "#FF5722");
        addPreviewColors("brown", "#795548");
        addPreviewColors("grey", "#9E9E9E");
        addPreviewColors("blue grey", "#607D8B");
    }

    public List<ColorCardsSG> getPreviewColors() {
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