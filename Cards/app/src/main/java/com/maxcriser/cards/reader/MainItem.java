package com.maxcriser.cards.reader;

public class MainItem {

    private String titleMainItem;
    private String subheadMainItem;

    MainItem(String title, String subhead){
        this.titleMainItem = title;
        this.subheadMainItem = subhead;
    }

    public void setSubheadMainItem(String pSubheadMainItem) {
        subheadMainItem = pSubheadMainItem;
    }

    public void setTitleMainItem(String pTitleMainItem) {

        titleMainItem = pTitleMainItem;
    }

    public String getTitleMainItem() {
        return titleMainItem;
    }

    public String getSubheadMainItem() {
        return subheadMainItem;
    }
}
