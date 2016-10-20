package com.maxcriser.hw_jsonpareser.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private List<JSONParserUserInfo> mJSONParserUserInfo = new ArrayList<>();

    public JSONParser(String strToParse) {
        try {
            JSONObject json = new JSONObject(strToParse);

            if (json.has("friends")) {
                JSONArray friendsArray = new JSONArray("friends");
                for (int i = 0; i < friendsArray.length(); i++) {

                    JSONObject jsonObject = friendsArray.getJSONObject(i);

                    JSONParserUserInfo jsonParserUserInfo = new JSONParserUserInfo(jsonObject);
                    mJSONParserUserInfo.add(jsonParserUserInfo);
                }
            }
        } catch (JSONException pE) {
            pE.printStackTrace();
        }
    }

    public List<JSONParserUserInfo> getJSONParserUserInfo() {
        return mJSONParserUserInfo;
    }
}