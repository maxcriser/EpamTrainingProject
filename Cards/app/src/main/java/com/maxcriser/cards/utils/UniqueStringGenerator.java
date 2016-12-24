package com.maxcriser.cards.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class UniqueStringGenerator {

    public static String getUniqueString() {
        //TODO md5?
        //TODO sha256
        //TODO some magic
        final Random random = new Random();
        final Integer rand = random.nextInt(1000000000);
        final String date = new SimpleDateFormat("dd-MM-yyyy", Locale.US).format(new Date());
        return System.currentTimeMillis() + "-" + date + "-" + rand + "-" + rand.toString().length() + "-";
    }
}