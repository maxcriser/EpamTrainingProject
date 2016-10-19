package com.maxcriser.cards.reader;

import com.maxcriser.cards.ui.PinProtectedActivity;

public class CorrectPassword {

    private static CorrectPassword sCorrectPassword;

    private String password;

    CorrectPassword() {

    }

    public static CorrectPassword getInstance() {
        if (sCorrectPassword == null) {
            sCorrectPassword = new CorrectPassword();
        }
        return sCorrectPassword;
    }

    public void setPassword() {
        //some method of read password
        password = "1234";
    }

    public boolean getPassword() {
        return (PinProtectedActivity.editTextPin.getText().toString().equals(password));
    }
}
