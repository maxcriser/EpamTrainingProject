package com.maxcriser.cards;


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

}