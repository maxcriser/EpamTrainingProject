package com.maxcriser.cards.model;

import com.maxcriser.cards.constant.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCard {

    private String text;

    public CreditCard(String text) {
        this.text = text;
        setCardholderCreditCard();
        setNameCreditCard();
        setNumberCreditCard();
        setTypeCreditCard();
        setValidCreditCard();
        setVerificationNumberCreditCard();
    }

    private String typeCreditCard = constants.EMPTY_STRING;
    private String nameCreditCard = constants.EMPTY_STRING;
    private String cardholderCreditCard = constants.EMPTY_STRING;
    private String numberCreditCard = constants.EMPTY_STRING;
    private String pinCreditCard = constants.EMPTY_STRING;
    private String verificationNumberCreditCard = constants.EMPTY_STRING;
    private String validCreditCard = constants.EMPTY_STRING;

    public void setTypeCreditCard() {
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

    private boolean isCharacterOrSpace(char ch) {
        String chStr = ch + constants.EMPTY_STRING;
        if ((chStr).matches("^[A-Za-z]+$") || (chStr).matches("^[А-Яа-я]+$") || ch == ' ' || ch == '.' || ch == '-' || ch == '&') {
            return true;
        }
        return false;
    }

    public void setNameCreditCard() {
        //TODO hardcode
        String bank = "bank";
        String handleText = text;
        if (text.toLowerCase().contains(bank)) {
            StringBuilder builder = new StringBuilder();
            int index = handleText.toLowerCase().indexOf(bank);
            if (index != 0) {
                for (int i = index - 1; i >= 0; i--) {
                    char ch = handleText.charAt(i);
                    if (isCharacterOrSpace(ch)) {
                        builder.insert(0, ch);
                    } else {
                        break;
                    }
                }
            }
            for (int i = index; i < handleText.length(); i++) {
                char ch = handleText.charAt(i);
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

    public void setCardholderCreditCard() {
        cardholderCreditCard = getRegexString(text, "[A-Z]{2,50} [A-Z]{2,50}");
    }

    public void setNumberCreditCard() {
        numberCreditCard = getRegexString(text,
                "\\d\\d\\d\\d \\d\\d\\d\\d \\d\\d\\d\\d \\d\\d\\d\\d");
    }

    public void setPinCreditCard() {
//        pinCreditCard = text;
    }

    public void setVerificationNumberCreditCard() {
//        verificationNumberCreditCard = text;
    }

    public void setValidCreditCard() {
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

    public String getPinCreditCard() {
        return pinCreditCard;
    }

    public String getVerificationNumberCreditCard() {
        return verificationNumberCreditCard;
    }

    public String getValidCreditCard() {
        return validCreditCard;
    }

    private String getRegexString(String text, String regex) {
        String regexp = regex;
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);

        return (matcher.find() ? matcher.group() : constants.EMPTY_STRING);
    }
}