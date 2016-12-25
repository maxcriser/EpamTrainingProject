package com.maxcriser.cards.http;

import android.os.AsyncTask;

import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpClient {

    private static final String HTTPS = "https";

    private String executeRequest(final Request request) throws Exception {
        InputStream inputStream = null;
        final HttpURLConnection connection;
        if (request.getUrl().contains(HTTPS)) {
            connection = (HttpsURLConnection) getConnection(request);
        } else {
            connection = (HttpURLConnection) getConnection(request);
        }
        try {
            inputStream = connection.getInputStream();
            if (inputStream == null) {
                throw new Exception("connection error");
            }
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } finally {
            if (inputStream != null) {
                //TODO IOUtils.closeSafely()
                inputStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public URLConnection getConnection(final Request request) throws Exception {
        final String pUrl = request.getUrl();
        if (pUrl == null) {
            throw new IllegalArgumentException("URL provided error");
        }
        final URL url = new URL(pUrl);
        final URLConnection connection = url.openConnection();
        if (connection == null) {
            throw new Exception("Connection error");
        }
        addProperties(request, connection);
        return connection;
    }

    private void addProperties(final Request request, final URLConnection connection) throws Exception {
        final Map<String, String> headers = request.getHeaders();
        final String bodyString = request.getBody();
        if (headers != null) {
            for (final String key : headers.keySet()) {
                connection.addRequestProperty(key, headers.get(key));
            }
        }
        if (bodyString != null) {
            final byte[] body = bodyString.getBytes("UTF-8");
            OutputStream stream = null;
            try {
                stream = connection.getOutputStream();
                stream.write(body);
            } catch (final IOException e) {
                if (stream != null) {
                    stream.close();
                }
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
        }
    }

    public void makeAsyncRequest(final Request pRequest, final OnResultCallback<String, Void> pCallback) {
        new AsyncTask<Request, Void, String>() {

            @Override
            protected String doInBackground(final Request... requests) {
                String response;
                try {
                    response = executeRequest(requests[0]);
                } catch (final Exception e) {
                    response = e.toString();
                }
                return response;
            }

            @Override
            protected void onPostExecute(final String pResult) {
                pCallback.onSuccess(pResult);
                super.onPostExecute(pResult);
            }
        }.execute(pRequest);
    }
}