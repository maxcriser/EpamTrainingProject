package com.maxcriser.cards.reader.PreviewColor;

import com.maxcriser.cards.database.custom.ListTableItems;

import java.util.ArrayList;
import java.util.List;

public class PreviewColorReader {

    private List<ListTableItems> previewColors;

    private static PreviewColorReader sPreviewColorReader;

    PreviewColorReader() {
        previewColors = new ArrayList<>();
    }



    public void setPreviewColors() {

        ListTableItems listTableItems = new ListTableItems();
        listTableItems.setNameColorTable("name1");
        listTableItems.setCodeColorTable("#0b8c8f");

        ListTableItems listTableItems2 = new ListTableItems();
        listTableItems2.setNameColorTable("name2");
        listTableItems2.setCodeColorTable("#E91E63");

        ListTableItems listTableItems3 = new ListTableItems();
        listTableItems3.setNameColorTable("name3");
        listTableItems3.setCodeColorTable("#F44336");

        ListTableItems listTableItems4 = new ListTableItems();
        listTableItems4.setNameColorTable("name4");
        listTableItems4.setCodeColorTable("E91E63");

        previewColors.add(listTableItems);
        previewColors.add(listTableItems2);
        previewColors.add(listTableItems3);
        previewColors.add(listTableItems4);

//        previewColors.add("#0b8c8f");
//        previewColors.add("#E91E63");
//        previewColors.add("#F44336");
//        previewColors.add("#E91E63");
//        previewColors.add("#9C27B0");
//        previewColors.add("#673AB7");
//        previewColors.add("#3F51B5");
//        previewColors.add("#2196F3");
//        previewColors.add("#03A9F4");
//        previewColors.add("#00BCD4");
//        previewColors.add("#00BCD4");
//        previewColors.add("#4CAF50");
//        previewColors.add("#8BC34A");
//        previewColors.add("#CDDC39");
//        previewColors.add("#FFEB3B");
//        previewColors.add("#FFC107");
//        previewColors.add("#FF9800");
//        previewColors.add("#FF5722");
//        previewColors.add("#795548");
//        previewColors.add("#9E9E9E");
//        previewColors.add("#607D8B");
    }

    public List<ListTableItems> getPreviewColors() {
        return previewColors;
    }

    public static PreviewColorReader getInstance() {
        if (sPreviewColorReader == null) {
            sPreviewColorReader = new PreviewColorReader();
        }
        return sPreviewColorReader;
    }

}