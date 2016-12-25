package com.maxcriser.cards.async.task;

import android.util.Log;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;
import com.maxcriser.cards.constant.ListConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonParser implements Task<String, Void, String> {

    @Override
    public String doInBackground(final String pUrl, final ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        String resultJson = ListConstants.EMPTY_STRING;
        try {
            final URL url = new URL(pUrl);

            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            final InputStream inputStream = urlConnection.getInputStream();
            final StringBuilder buffer = new StringBuilder();

            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();
            inputStream.close();

        } catch (final Exception e) {
            Log.d("Connection time out", e.toString());
        }
        return resultJson;
    }
}