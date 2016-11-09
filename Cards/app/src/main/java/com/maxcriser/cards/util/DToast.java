package com.maxcriser.cards.util;

import android.content.Context;
import android.widget.Toast;

public class DToast {

    public static void m(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
