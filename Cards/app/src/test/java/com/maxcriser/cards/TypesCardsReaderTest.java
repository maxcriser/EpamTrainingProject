package com.maxcriser.cards;


import com.maxcriser.cards.constant.ListConstants;
import com.maxcriser.cards.model.CreditCard;
import com.maxcriser.cards.utils.UniqueStringGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TypesCardsReaderTest {

    @Test
    public void uniqueStringGenerator() {
        final String first = UniqueStringGenerator.getUniqueString();
        final String second = UniqueStringGenerator.getUniqueString();
        System.out.println(first);
        System.out.println(second);
        assertNotEquals(first, second);
    }


    private boolean isCharacterOrSpace(final char ch) {
        final String chStr = ch + ListConstants.EMPTY_STRING;
        if ((chStr).matches("^[A-Za-z]+$") || (chStr).matches("^[А-Яа-я]+$") || ch == ' ' || ch == '.' || ch == '-' || ch == '&') {
            return true;
        }
        return false;
    }

    @Test
    public void testRegex() {
        final String text = "#$YHEH#$Y#$weg23/324w@##/33John Smith312/32###Maxim Zaharenko###3g3maistro4g34/tg+#@(@#JGJosé#Priorbank@ BrasãoSkof01234 4444 1235 23463#(@kol3t[Bbqfs@@i3k3gMAXIM ZAHARENKO29i39jgVISAp@";
        final CreditCard creditCard = new CreditCard(text);
        assertEquals(creditCard.getValidCreditCard(), "12/32");
        assertEquals(creditCard.getCardholderCreditCard(), "MAXIM ZAHARENKO");
        assertEquals(creditCard.getNumberCreditCard(), "1234 4444 1235 2346");
        assertEquals(creditCard.getTypeCreditCard(), "visa");
        assertEquals(creditCard.getNameCreditCard(), "Priorbank");

        final String text2 = "@PO#KT(*J)GO@<_)(*U)IGO_H#)(NU@32Priorank523523523rvos1111 3333 2222 111l23t23GWO>T)#)(GUVOQWPgoiego432t244/44l21t3-j0gnui-oL)#)#MG)W_(I)9.t30g90i";
        final CreditCard creditCard2 = new CreditCard(text2);
        assertEquals(creditCard2.getValidCreditCard(), "");
        assertEquals(creditCard2.getCardholderCreditCard(), "");
        assertEquals(creditCard2.getNumberCreditCard(), "");
        assertEquals(creditCard2.getTypeCreditCard(), "");
        assertEquals(creditCard2.getNameCreditCard(), "");
    }

    @Test
    public void isEmptyTest(){
        final String message = "";
    }
}