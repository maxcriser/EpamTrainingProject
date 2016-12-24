package com.maxcriser.cards.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.maxcriser.cards.constant.constants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.maxcriser.cards.constant.constants.URL_JSON_LOCATION;

public class SettingsParser extends AsyncTask<Void, Void, String> {

    private String resultJson = constants.EMPTY_STRING;

    @Override
    protected String doInBackground(final Void... params) {
        try {
            final URL url = new URL(URL_JSON_LOCATION);

            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            final InputStream inputStream = urlConnection.getInputStream();
            final StringBuilder buffer = new StringBuilder();

            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                Log.d("LINE", line);
            }

            resultJson = buffer.toString();
            inputStream.close();

        } catch (final Exception e) {
            Log.d("Connection time out", e.toString());
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(final String strJson) {
        super.onPostExecute(strJson);
        Log.d("JSON RELUST", strJson);
/*
        final JSONObject dataJsonObj;
        try {
            dataJsonObj = new JSONObject(strJson);

            final String COUNTRY_ID = "country";
//            pCountry = dataJsonObj.getString(COUNTRY_ID);
            final String COUNTRY_CODE_ID = "countryCode";
//            pCountryCode = dataJsonObj.getString(COUNTRY_CODE_ID);
            final String ISP_ID = "isp";
//            pIsp = dataJsonObj.getString(ISP_ID);
            final String QUERY_ID = "query";
//            pQuery = dataJsonObj.getString(QUERY_ID);
            final String TIMEZONE_ID = "timezone";
//            pTimezone = dataJsonObj.getString(TIMEZONE_ID);

        } catch (final JSONException e) {
            throw new RuntimeException(e);
        }
        */
    }
}
