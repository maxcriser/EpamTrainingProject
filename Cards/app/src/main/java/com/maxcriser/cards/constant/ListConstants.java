package com.maxcriser.cards.constant;

public final class ListConstants {

    public interface Requests {

        byte REQUEST_CAMERA = 0;
        byte REQUEST_FRONT_CAMERA = 1;
        byte REQUEST_BACK_CAMERA = 2;
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

    public interface PagerIDs {

        int ID_BANK_CARD_ITEM_TYPE = 1;
        int ID_DISCOUNT_ITEM = 2;
        int ID_TICKET_ITEM = 3;
    }

    public interface Database {
        String SQL_TABLE_CREATE_TEMPLATE = "CREATE TABLE IF NOT EXISTS %s (%s);";
        String SQL_TABLE_CREATE_FIELD_TEMPLATE = "%s %s%s";
        String mDatabaseName = "database.cards.thecriser";
        int dbVersion = 1;
    }

    public static final String URL_JSON_LOCATION = "http://ip-api.com/json";
    public static final String URL_JSON_SETTINGS = "http://ckeeperservletbackend.appspot.com/";
    public static final double RATIO_CREDIT_CARD = 1.5818181818181818181818181818182;
    public static final String CONFIG = "config";
    public static final String EMPTY_STRING = "";
    public static final String BEG_FILE_NAME_BANK = "credit-card-";
    public static final String BEG_FILE_NAME_TICKET = "ticket-";
    public static final int PAGER_MARGIN_PREVIEW = 16;
    public static final String APP_TAG = "thecrisertakephoto";
    public static final String STATUS_PHOTOEDITOR = "status_photoeditor";
    public static final String STATUS_PHOTOEEDITOR_CREDIT_CARD = "status_credit_card_crop";
    public static final String STATUS_PHOTOEEDITOR_TICKET = "status_ticket_crop";
    public static final String SETUP_PIN = "setup_pin";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String UNDEFENDED = "undefended";
    public static final String PASSWORD_TAG = "shared_password";
    public static final String BANK = "bank";
    public static final String HTTPS = "https";
    public static final String FILE = "file://";
    public static final String HTTP = "http";
    public static final String ARGUMENT_PAGE_NUMBER_DISCOUNT = "arg_page_number";
    public static final String CREDIT_CARD = "credit_card";
    public static final String KEY_NAME = "finger_key";
    public static final String TAG_BARCODE = "barcode";
    public static final String Y_800 = "Y800";
    public static final String TYPE_LOCKED_SCREEN = "type_locked_screen";
    public static final String ARGUMENT_PAGE_NUMBER_TYPE = "arg_page_number_type";

}