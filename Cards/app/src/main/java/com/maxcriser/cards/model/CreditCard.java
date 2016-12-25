package com.maxcriser.cards.model;

import com.maxcriser.cards.constant.ListConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCard {

    private final String text;

    public CreditCard(final String text) {
        this.text = text;
        setCardholderCreditCard();
        setNameCreditCard();
        setNumberCreditCard();
        setTypeCreditCard();
        setValidCreditCard();
    }

    private String typeCreditCard = ListConstants.EMPTY_STRING;
    private String nameCreditCard = ListConstants.EMPTY_STRING;
    private String cardholderCreditCard = ListConstants.EMPTY_STRING;
    private String numberCreditCard = ListConstants.EMPTY_STRING;
    private String validCreditCard = ListConstants.EMPTY_STRING;

    private void setTypeCreditCard() {
        if (text.toLowerCase().contains(ListConstants.Cards.VISA)) {
            typeCreditCard = ListConstants.Cards.VISA;
        } else if (text.toLowerCase().contains(ListConstants.Cards.AMEX)
                || text.toLowerCase().contains(ListConstants.Cards.AMEX2)) {
            typeCreditCard = ListConstants.Cards.AMEX;
        } else if (text.toLowerCase().contains(ListConstants.Cards.MASTERCARD)) {
            typeCreditCard = ListConstants.Cards.MASTERCARD;
        } else if (text.toLowerCase().contains(ListConstants.Cards.MAESTRO)) {
            typeCreditCard = ListConstants.Cards.MAESTRO;
        } else if (text.toLowerCase().contains(ListConstants.Cards.WESTERN_UNION)) {
            typeCreditCard = ListConstants.Cards.WESTERN_UNION;
        } else if (text.toLowerCase().contains(ListConstants.Cards.JCB)) {
            typeCreditCard = ListConstants.Cards.JCB;
        } else if (text.toLowerCase().contains(ListConstants.Cards.DINERS_CLUB)) {
            typeCreditCard = ListConstants.Cards.DINERS_CLUB;
        } else if (text.toLowerCase().contains(ListConstants.Cards.BELCARD)
                || text.toLowerCase().contains(ListConstants.Cards.BELCARD2)) {
            typeCreditCard = ListConstants.Cards.BELCARD;
        }
    }

    private boolean isCharacterOrSpace(final char ch) {
        final String chStr = ch + ListConstants.EMPTY_STRING;
        return (chStr).matches("^[A-Za-z]+$") || (chStr).matches("^[А-Яа-я]+$") || ch == ' ' || ch == '.' || ch == '-' || ch == '&';
    }

    private void setNameCreditCard() {
        final String bank = ListConstants.BANK;
        final String handleText = text;
        if (text.toLowerCase().contains(bank)) {
            final StringBuilder builder = new StringBuilder();
            final int index = handleText.toLowerCase().indexOf(bank);
            if (index != 0) {
                for (int i = index - 1; i >= 0; i--) {
                    final char ch = handleText.charAt(i);
                    if (isCharacterOrSpace(ch)) {
                        builder.insert(0, ch);
                    } else {
                        break;
                    }
                }
            }
            for (int i = index; i < handleText.length(); i++) {
                final char ch = handleText.charAt(i);
                if (isCharacterOrSpace(ch)) {
                    builder.append(ch);
                } else {
                    break;
                }
            }
            nameCreditCard = builder.toString();
            nameCreditCard = nameCreditCard.trim();
        } else {
            nameCreditCard = ListConstants.EMPTY_STRING;
        }
    }

    private void setCardholderCreditCard() {
        cardholderCreditCard = getRegexString(text, "[A-Z]{2,50} [A-Z]{2,50}");
    }

    private void setNumberCreditCard() {
        numberCreditCard = getRegexString(text,
                "\\d\\d\\d\\d \\d\\d\\d\\d \\d\\d\\d\\d \\d\\d\\d\\d");
    }

    private void setValidCreditCard() {
        validCreditCard = getRegexString(text, "[0-1][0-9]/\\d\\d");
    }

    public String getTypeCreditCard() {
        return typeCreditCard;
    }

    public String getNameCreditCard() {
        return nameCreditCard;
    }

    public String getCardholderCreditCard() {
        return cardholderCreditCard;
    }

    public String getNumberCreditCard() {
        return numberCreditCard;
    }

    public String getValidCreditCard() {
        return validCreditCard;
    }

    private String getRegexString(final CharSequence text, final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(text);

        return (matcher.find() ? matcher.group() : ListConstants.EMPTY_STRING);
    }
}