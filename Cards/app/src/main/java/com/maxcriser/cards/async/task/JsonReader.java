package com.maxcriser.cards.async.task;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;
import com.maxcriser.cards.barcode.BarcodeBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonReader implements Task<String, String, String> {

    @Override
    public String doInBackground(String pS, ProgressCallback<String> pStringProgressCallback) throws Exception {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        try {
            URL url = new URL("http://ip-api.com/json");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }
}
