package com.maxcriser.cards.reader.PreviewColor;

import java.util.ArrayList;
import java.util.List;

public class PreviewColorReader {


    private static PreviewColorReader sPreviewColorReader;

    private List<String> previewColors;

    PreviewColorReader() {
    }

    public void setPreviewColors() {
        previewColors = new ArrayList<>();
        previewColors.add("#0b8c8f");
        previewColors.add("#E91E63");
        previewColors.add("#F44336");
        previewColors.add("#E91E63");
        previewColors.add("#9C27B0");
        previewColors.add("#673AB7");
        previewColors.add("#3F51B5");
        previewColors.add("#2196F3");
        previewColors.add("#03A9F4");
        previewColors.add("#00BCD4");
        previewColors.add("#00BCD4");
        previewColors.add("#4CAF50");
        previewColors.add("#8BC34A");
        previewColors.add("#CDDC39");
        previewColors.add("#FFEB3B");
        previewColors.add("#FFC107");
        previewColors.add("#FF9800");
        previewColors.add("#FF5722");
        previewColors.add("#795548");
        previewColors.add("#9E9E9E");
        previewColors.add("#607D8B");
    }

    public List<String> getPreviewColors() {
        return previewColors;
    }

    public static PreviewColorReader getInstance() {
        if (sPreviewColorReader == null) {
            sPreviewColorReader = new PreviewColorReader();
        }
        return sPreviewColorReader;
    }

}