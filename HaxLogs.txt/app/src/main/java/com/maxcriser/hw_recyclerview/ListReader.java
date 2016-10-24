package com.maxcriser.hw_recyclerview;

import java.util.ArrayList;
import java.util.List;

public class ListReader {

    List<String> myItems = new ArrayList<>();
    String title = "My item: ";

    public ListReader(){
        setMyItems();
    }

    public void setMyItems(){
        for (int i = 0; i < 100; i++) {
            myItems.add(title + i);
        }
        // some method
    }

    public List<String> getMyItems() {
        return myItems;
    }
}
