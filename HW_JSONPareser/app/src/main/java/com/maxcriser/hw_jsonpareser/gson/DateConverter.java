package com.maxcriser.hw_jsonpareser.gson;

import android.annotation.SuppressLint;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements JsonDeserializer<Date> {

//            different date formats:

//            "yyyy.MM.dd G 'at' HH:mm:ss z"
//            "EEE, MMM d, ''yy""h:mm a"
//            "hh 'o''clock' a, zzzz"
//            "K:mm a, z"
//            "yyyyy.MMMMM.dd GGG hh:mm aaa"
//            "EEE, d MMM yyyy HH:mm:ss Z"
//            "yyMMddHHmmssZ"
//            "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
//            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
//            "YYYY-'W'ww-u"

    // Yesterday 13:31
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE h:mm");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return dateFormat.parse(json.getAsString());
        } catch (ParseException pE) {
            pE.printStackTrace();
        }

        return null;
    }
}
