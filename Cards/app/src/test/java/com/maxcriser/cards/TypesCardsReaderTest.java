package com.maxcriser.cards;


import com.maxcriser.cards.model.CreditCard;
import com.maxcriser.cards.util.UniqueStringGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TypesCardsReaderTest {


    @Test
    public void uniqueStringGenerator() {
        String first = UniqueStringGenerator.getUniqueString();
        String second = UniqueStringGenerator.getUniqueString();
        assertNotEquals(first, second);
    }

    @Test
    public void testSFSD() {
        CreditCard.setValidCreditCard("SGKPSIH123feps/4o3ijowkk4i01/12o-r9uK_@T_");
    }
}