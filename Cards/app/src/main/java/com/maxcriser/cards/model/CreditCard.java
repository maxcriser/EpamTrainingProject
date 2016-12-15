package com.maxcriser.cards.model;

import com.maxcriser.cards.constant.Constants;

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

    private String typeCreditCard = Constants.EMPTY_STRING;
    private String nameCreditCard = Constants.EMPTY_STRING;
    private String cardholderCreditCard = Constants.EMPTY_STRING;
    private String numberCreditCard = Constants.EMPTY_STRING;
    private String pinCreditCard = Constants.EMPTY_STRING;
    private String verificationNumberCreditCard = Constants.EMPTY_STRING;
    private String validCreditCard = Constants.EMPTY_STRING;

    public void setTypeCreditCard() {
        if (text.toLowerCase().contains(Constants.Cards.VISA)) {
            typeCreditCard = Constants.Cards.VISA;
        } else if (text.toLowerCase().contains(Constants.Cards.AMEX)
                || text.toLowerCase().contains(Constants.Cards.AMEX2)) {
            typeCreditCard = Constants.Cards.AMEX;
        } else if (text.toLowerCase().contains(Constants.Cards.MASTERCARD)) {
            typeCreditCard = Constants.Cards.MASTERCARD;
        } else if (text.toLowerCase().contains(Constants.Cards.MAESTRO)) {
            typeCreditCard = Constants.Cards.MAESTRO;
        } else if (text.toLowerCase().contains(Constants.Cards.WESTERN_UNION)) {
            typeCreditCard = Constants.Cards.WESTERN_UNION;
        } else if (text.toLowerCase().contains(Constants.Cards.JCB)) {
            typeCreditCard = Constants.Cards.JCB;
        } else if (text.toLowerCase().contains(Constants.Cards.DINERS_CLUB)) {
            typeCreditCard = Constants.Cards.DINERS_CLUB;
        } else if (text.toLowerCase().contains(Constants.Cards.BELCARD)
                || text.toLowerCase().contains(Constants.Cards.BELCARD2)) {
            typeCreditCard = Constants.Cards.BELCARD;
        }
    }

    private boolean isCharacterOrSpace(char ch) {
        String chStr = ch + Constants.EMPTY_STRING;
        if ((chStr).matches("^[A-Za-z]+$") || (chStr).matches("^[А-Яа-я]+$") || ch == ' ' || ch == '.' || ch == '-' || ch == '&') {
            return true;
        }
        return false;
    }

    public void setNameCreditCard() {
        String bank = "bank";
        String handleText = text;
        if (this.text.toLowerCase().contains(bank)) {
            StringBuilder builder = new StringBuilder(bank);
            int index = handleText.indexOf(bank);
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
            if (handleText.length() != index + 4) {
                for (int i = index + 4; i < handleText.length(); i++) {
                    char ch = handleText.charAt(i);
                    if (isCharacterOrSpace(ch)) {
                        builder.append(ch);
                    } else {
                        break;
                    }
                }
            }
            nameCreditCard = builder.toString();
            nameCreditCard = nameCreditCard.trim();
        } else {
            nameCreditCard = Constants.EMPTY_STRING;
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

        return (matcher.find() ? matcher.group() : Constants.EMPTY_STRING);
    }
}