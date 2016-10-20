package com.maxcriser.hw_jsonpareser.gson;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GSONParser {

    @SerializedName("friends")
    private List<GSONParserUserInfo> mGSONParserUserInfo;

    public List<GSONParserUserInfo> getGSONParserUserInfo() {
        return mGSONParserUserInfo;
    }
}
