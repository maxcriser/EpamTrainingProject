package com.maxcriser.cards.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class UniqueStringGenerator {

    public static String getUniqueString() {
        Random random = new Random();
        Integer rand = random.nextInt(1000000000);
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.US).format(new Date());
        return System.currentTimeMillis() + "-" + date + "-" + rand.toString() + "-" + rand.toString().length() + "-";
    }
}