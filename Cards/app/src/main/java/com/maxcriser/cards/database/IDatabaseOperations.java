package com.maxcriser.cards.database;

import com.maxcriser.cards.database.custom.ListTableItems;

public interface IDatabaseOperations {

    void push();

    void editbyID();

    void deletebyID();

    ListTableItems fingbyID();

}
