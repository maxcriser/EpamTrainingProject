package com.maxcriser.hw_jsonpareser.gson;

import com.google.gson.annotations.SerializedName;

public class GSONParserUserInfo {

    // I have not field date, so
    //@SerializedName("date")
    //private Date date;

    @SerializedName("name")
    private String name;

    @SerializedName("city")
    private String city;

    @SerializedName("email")
    private String email;

    //public Date getDate() {
    //    return date;
    //}

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
