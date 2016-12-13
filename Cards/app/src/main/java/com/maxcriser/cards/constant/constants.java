package com.maxcriser.cards.constant;

public class Constants {

    public interface REQUESTS {
        byte REQUEST_CAMERA = 0;
        byte REQUEST_FRONT_CAMERA = 1;
        byte REQUEST_BACK_CAMERA = 2;
        byte REQUEST_WRITE_STORAGE = 3;
        byte REQUEST_CALENDAR = 4;
        int CAPTURE_IMAGE_FRONT = 1001;
        int CAPTURE_IMAGE_BACK = 1010;
        int EDIT_IMAGE_FRONT = 1011;
        int EDIT_IMAGE_BACK = 1100;
    }

    public interface CARDS {
        String VISA = "visa";
        String MASTERCARD = "mastercard";
        String AMEX = "amex";
        String MAESTRO = "maestro";
        String WESTERN_UNION = "western_union";
        String JCB = "jcb";
        String DINERS_CLUB = "diners_club";
        String BELCARD = "belcard";
    }

    public interface KEYBOARD {
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

    public interface TITLES {
        String BANK_TITLE = "Credit cards";
        String DISCOUNT_TITLE = "Discount cards";
        String TICKETS_TITLE = "Tickets";
        String NFC_TITLE = "NFC items";
    }

    public interface NEW_TITLES {
        String NEW_DISCOUNT_TITLE = "New discount card";
        String NEW_BANK_TITLE = "New credit card";
        String NEW_TICKET_TITLE = "New ticket";
        String NEW_NFC_TITLE = "New NFC item";
    }

    public interface ID_PAGERS {
        int ID_BANK_CARD_ITEM = 0;
        int ID_BANK_CARD_ITEM_TYPE = 1; // visa, mastercard and etc.
        int ID_DISCOUNT_ITEM = 2;
        int ID_TICKET_ITEM = 3;
    }

    public static final String URL_JSON_LOCATION = "http://ip-api.com/json";
    public static final String EMPTY_STRING = "";
    public static final String BEG_FILE_NAME = "ticket-";
    public static final int PAGER_MARGIN_PREVIEW = 16;
}