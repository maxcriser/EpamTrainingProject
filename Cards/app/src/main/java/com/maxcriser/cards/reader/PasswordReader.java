package com.maxcriser.cards.reader;

public class PasswordReader {

    private static PasswordReader sPasswordReader;

    private String mPassword;

    PasswordReader() {

    }

    public static PasswordReader getInstance() {
        if (sPasswordReader == null) {
            sPasswordReader = new PasswordReader();
        }
        return sPasswordReader;
    }

    public void setPassword() {
        //some method of read password
        mPassword = "1234";
    }

    public String getPassword() {
        return mPassword;
    }
}
