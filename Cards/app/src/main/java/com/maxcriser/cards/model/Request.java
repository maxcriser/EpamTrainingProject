package com.maxcriser.cards.model;

import java.util.Map;

public class Request {

    private String url;
    private Map<String, String> headers;
    private String body;
    private String method;

    private void setMethod(final String method) {
        this.method = method;
    }

    private void setUrl(final String url) {
        this.url = url;
    }

    private void setHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    private void setBody(final String body) {
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {

        private final Request request = new Request();

        public Builder setUrl(final String url) {
            this.request.setUrl(url);
            return this;
        }

        public Builder setHeaders(final Map<String, String> headers) {
            this.request.setHeaders(headers);
            return this;
        }

        public Builder setBody(final String body) {
            this.request.setBody(body);
            return this;
        }

        public Builder setMethod(final String method) {
            this.request.setMethod(method);
            return this;
        }

        public Request build() {
            return request;
        }
    }
}