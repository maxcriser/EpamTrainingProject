package com.maxcriser.cards.model;

import com.maxcriser.cards.constant.constants;

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

    private String typeCreditCard = constants.EMPTY_STRING;
    private String nameCreditCard = constants.EMPTY_STRING;
    private String cardholderCreditCard = constants.EMPTY_STRING;
    private String numberCreditCard = constants.EMPTY_STRING;
    private String validCreditCard = constants.EMPTY_STRING;

    private void setTypeCreditCard() {
        if (text.toLowerCase().contains(constants.Cards.VISA)) {
            typeCreditCard = constants.Cards.VISA;
        } else if (text.toLowerCase().contains(constants.Cards.AMEX)
                || text.toLowerCase().contains(constants.Cards.AMEX2)) {
            typeCreditCard = constants.Cards.AMEX;
        } else if (text.toLowerCase().contains(constants.Cards.MASTERCARD)) {
            typeCreditCard = constants.Cards.MASTERCARD;
        } else if (text.toLowerCase().contains(constants.Cards.MAESTRO)) {
            typeCreditCard = constants.Cards.MAESTRO;
        } else if (text.toLowerCase().contains(constants.Cards.WESTERN_UNION)) {
            typeCreditCard = constants.Cards.WESTERN_UNION;
        } else if (text.toLowerCase().contains(constants.Cards.JCB)) {
            typeCreditCard = constants.Cards.JCB;
        } else if (text.toLowerCase().contains(constants.Cards.DINERS_CLUB)) {
            typeCreditCard = constants.Cards.DINERS_CLUB;
        } else if (text.toLowerCase().contains(constants.Cards.BELCARD)
                || text.toLowerCase().contains(constants.Cards.BELCARD2)) {
            typeCreditCard = constants.Cards.BELCARD;
        }
    }

    private boolean isCharacterOrSpace(final char ch) {
        final String chStr = ch + constants.EMPTY_STRING;
        return (chStr).matches("^[A-Za-z]+$") || (chStr).matches("^[А-Яа-я]+$") || ch == ' ' || ch == '.' || ch == '-' || ch == '&';
    }

    private void setNameCreditCard() {
        //TODO hardcode
        final String bank = "bank";
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
            nameCreditCard = constants.EMPTY_STRING;
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

        return (matcher.find() ? matcher.group() : constants.EMPTY_STRING);
    }
}