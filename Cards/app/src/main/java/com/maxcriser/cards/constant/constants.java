package com.maxcriser.cards.constant;

public class Constants {

    public interface Requests {
        byte REQUEST_CAMERA = 0;
        byte REQUEST_FRONT_CAMERA = 1;
        byte REQUEST_BACK_CAMERA = 2;
        byte REQUEST_WRITE_STORAGE = 3;
        byte REQUEST_CALENDAR = 4;
        byte REQUEST_WRITE_STORAGE_FRONT = 5;
        byte REQUEST_WRITE_STORAGE_BACK = 6;
        int CAPTURE_IMAGE_FRONT = 1001;
        int CAPTURE_IMAGE_BACK = 1010;
        int EDIT_IMAGE_FRONT = 1011;
        int EDIT_IMAGE_BACK = 1100;
    }

    public interface PagerTypesID {
        int VISA = 0;
        int MASTERCAD = 1;
        int AMEX = 2;
        int MAESTRO = 3;
        int WESTERN_UNION = 4;
        int JCB = 5;
        int DINERS_CLUB = 6;
        int BELCARD = 7;
    }

    public interface Cards {
        String VISA = "visa";
        String MASTERCARD = "mastercard";
        String AMEX = "amex";
        String AMEX2 = "american express";
        String MAESTRO = "maestro";
        String WESTERN_UNION = "western union";
        String JCB = "jcb";
        String DINERS_CLUB = "diners club";
        String BELCARD = "belcard";
        String BELCARD2 = "белкарт";
    }

    public interface Keyboard {
        String BUTTON_ZERO = "0";
        String BUTTON_ONE = "1";
        String BUTTON_TWO = "2";
        String BUTTON_THREE = "3";
        String BUTTON_FOUR = "4";
        String BUTTON_FIVE = "5";
        String BUTTON_SIX = "6";
        String BUTTON_SEVEN = "7";
        String BUTTON_EIGHT = "8";
        String BUTTON_NINE = "9";
    }

    public interface Titles {
        String BANK_TITLE = "Credit cards";
        String DISCOUNT_TITLE = "Discount cards";
        String TICKETS_TITLE = "Tickets";
        String NFC_TITLE = "NFC items";
    }

    public interface TitlesNew {
        String NEW_DISCOUNT_TITLE = "New discount card";
        String NEW_BANK_TITLE = "New credit card";
        String NEW_TICKET_TITLE = "New ticket";
        String NEW_NFC_TITLE = "New NFC item";
    }

    public interface PagerIDs {
        int ID_BANK_CARD_ITEM = 0;
        int ID_BANK_CARD_ITEM_TYPE = 1; // visa, mastercard and etc.
        int ID_DISCOUNT_ITEM = 2;
        int ID_TICKET_ITEM = 3;
        int ID_NFC_ITEM = 4;
    }

    public static final String URL_JSON_LOCATION = "http://ip-api.com/json";
    public static final String EMPTY_STRING = "";
    public static final String BEG_FILE_NAME_BANK = "credit-card-";
    public static final String BEG_FILE_NAME_TICKET = "ticket-";
    public static final int PAGER_MARGIN_PREVIEW = 16;
    public static final String APP_TAG = "thecrisertakephoto";
}