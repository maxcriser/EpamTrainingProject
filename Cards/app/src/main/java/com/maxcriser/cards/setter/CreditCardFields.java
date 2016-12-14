package com.maxcriser.cards.setter;

import com.maxcriser.cards.constant.Constants;

public class CreditCardFields {

    private String typeCreditCard = Constants.EMPTY_STRING;
    private String nameCreditCard = Constants.EMPTY_STRING;
    private String cardholderCreditCard = Constants.EMPTY_STRING;
    private String numberCreditCard = Constants.EMPTY_STRING;
    private String pinCreditCard = Constants.EMPTY_STRING;
    private String verificationNumberCreditCard = Constants.EMPTY_STRING;
    private String validCreditCard = Constants.EMPTY_STRING;

    public void setTypeCreditCard(String pTypeCreditCard) {
        if (pTypeCreditCard.toLowerCase().contains(Constants.Cards.VISA)) {
            typeCreditCard = Constants.Cards.VISA;
        } else if (pTypeCreditCard.toLowerCase().contains(Constants.Cards.AMEX)
                || pTypeCreditCard.toLowerCase().contains(Constants.Cards.AMEX2)) {
            typeCreditCard = Constants.Cards.AMEX;
        } else if (pTypeCreditCard.toLowerCase().contains(Constants.Cards.MASTERCARD)) {
            typeCreditCard = Constants.Cards.MASTERCARD;
        } else if (pTypeCreditCard.toLowerCase().contains(Constants.Cards.MAESTRO)) {
            typeCreditCard = Constants.Cards.MAESTRO;
        } else if (pTypeCreditCard.toLowerCase().contains(Constants.Cards.WESTERN_UNION)) {
            typeCreditCard = Constants.Cards.WESTERN_UNION;
        } else if (pTypeCreditCard.toLowerCase().contains(Constants.Cards.JCB)) {
            typeCreditCard = Constants.Cards.JCB;
        } else if (pTypeCreditCard.toLowerCase().contains(Constants.Cards.DINERS_CLUB)) {
            typeCreditCard = Constants.Cards.DINERS_CLUB;
        } else if (pTypeCreditCard.toLowerCase().contains(Constants.Cards.BELCARD)
                || pTypeCreditCard.toLowerCase().contains(Constants.Cards.BELCARD2)) {
            typeCreditCard = Constants.Cards.BELCARD;
        }

        typeCreditCard = pTypeCreditCard;
    }

    public void setNameCreditCard(String pNameCreditCard) {
        nameCreditCard = pNameCreditCard;
    }

    public void setCardholderCreditCard(String pCardholderCreditCard) {
        cardholderCreditCard = pCardholderCreditCard;
    }

    public void setNumberCreditCard(String pNumberCreditCard) {
        numberCreditCard = pNumberCreditCard;
    }

    public void setPinCreditCard(String pPinCreditCard) {
        pinCreditCard = pPinCreditCard;
    }

    public void setVerificationNumberCreditCard(String pVerificationNumberCreditCard) {
        verificationNumberCreditCard = pVerificationNumberCreditCard;
    }

    public void setValidCreditCard(String pValidCreditCard) {
        validCreditCard = pValidCreditCard;
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
}
