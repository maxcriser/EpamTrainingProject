package com.maxcriser.hw_jsonpareser;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxcriser.hw_jsonpareser.gson.DateConverter;
import com.maxcriser.hw_jsonpareser.gson.GSONParser;
import com.maxcriser.hw_jsonpareser.gson.GSONParserUserInfo;
import com.maxcriser.hw_jsonpareser.json.JSONParser;
import com.maxcriser.hw_jsonpareser.json.JSONParserUserInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

class JSONReader extends AsyncTask<Object, Object, String> {

    public static final String SOLUTION_TAG = "Solution: ";
    HttpURLConnection mHttpURLConnection;
    BufferedReader inputReader;
    StringBuffer mStringBuffer;

    String urlPage;
    String row;

    URL mURL;

    public JSONReader(String str) {
        urlPage = str;
    }

    @Override
    protected String doInBackground(Object... params) {

        mStringBuffer = new StringBuffer();

        try {
            mURL = new URL(urlPage);

            mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.connect();

            InputStream inputStream = mHttpURLConnection.getInputStream();

            inputReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((row = inputReader.readLine()) != null) {
                mStringBuffer.append(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mStringBuffer.toString();
    }

    @Override
    protected void onPostExecute(String pS) {
        super.onPostExecute(pS);

        Log.d(SOLUTION_TAG, "Parse is done - " + pS);

        JSONParser friendsInfo = new JSONParser(pS);
        List<JSONParserUserInfo> listJSON = friendsInfo.getJSONParserUserInfo();

        // listJSON (type: JSONParserUserInfo) consist of Getters: name (getName), city(getCity), email(getEmail)

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateConverter()).create();

        GSONParser twitterSearchResponse = gson.fromJson(pS, GSONParser.class);
        List<GSONParserUserInfo> listGSON = twitterSearchResponse.getGSONParserUserInfo();

        // listGSON (type: GSONParserUserInfo) consist of Getters: name (getName), city(getCity), email(getEmail)
    }
}