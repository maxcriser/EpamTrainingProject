package com.maxcriser.cards.database;

import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.database.models.ModelNFCItems;
import com.maxcriser.cards.database.models.ModelTickets;

final class ModelList {

    static final Class<?>[] MODELS =
            {
                    ModelDiscountCards.class,
                    ModelBankCards.class,
                    ModelNFCItems.class,
                    ModelTickets.class,
            };
}
