package com.maxcriser.cards;


import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.model.CreditCard;
import com.maxcriser.cards.util.UniqueStringGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
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
    public void testRegex() {
        String text = "-#$YHEH#$Y#$weg23/324w@#Bank of AmericaT#/33John Smith312/32###Maxim Zaharenko###3g3maistro4g34/tg+#@(@#JGJosé BrasãoSkof01234 4444 1235 23463#(@kol3t[Bbqfs@@i3k3gMAXIM ZAHARENKO29i39jgVISAp";
        CreditCard creditCard = new CreditCard(text);
        assertEquals(creditCard.getValidCreditCard(), "12/32");
        assertEquals(creditCard.getCardholderCreditCard(), "MAXIM ZAHARENKO");
        assertEquals(creditCard.getNumberCreditCard(), "1234 4444 1235 2346");
        assertEquals(creditCard.getTypeCreditCard(), "visa");
        System.out.println(creditCard.getNameCreditCard());

        String text2 = "@PO#KT(*J)GO@<_)(*U)IGO_H#)(NU@32523523523rvos23t23GWO>T)#)(GUVOQWPgoiego432t244/44l21t3-j0gnui-oL)#)#MG)W_(I)9.t30g90i";
        CreditCard creditCard2 = new CreditCard(text2);
        assertEquals(creditCard2.getValidCreditCard(), "");
        assertEquals(creditCard2.getCardholderCreditCard(), "");
        assertEquals(creditCard2.getNumberCreditCard(), "");
        assertEquals(creditCard2.getTypeCreditCard(), "");
        System.out.println(creditCard2.getNameCreditCard());
    }
}