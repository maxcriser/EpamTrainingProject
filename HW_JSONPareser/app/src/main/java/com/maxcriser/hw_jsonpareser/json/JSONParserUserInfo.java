package com.maxcriser.hw_jsonpareser.json;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParserUserInfo {

    public JSONParserUserInfo(JSONObject jsonFriend) throws JSONException {

        name = jsonFriend.getString("name");
        city = jsonFriend.getString("city");

        JSONObject infoFriend = jsonFriend.getJSONObject("contacts");
        if (infoFriend != null) {
            email = infoFriend.getString("email");
        }
    }

    private String name;

    private String city;

    private String email;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }
}
