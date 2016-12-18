package com.maxcriser.cards.model;

import java.util.Map;

public class Request {

    private String url;
    private Map<String, String> headers;
    private String body;
    private String method;

    public String getMethod() {
        return method;
    }

    private void setMethod(final String mMethod) {
        this.method = mMethod;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(final String mUrl) {
        this.url = mUrl;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    private void setHeaders(final Map<String, String> mHeaders) {
        this.headers = mHeaders;
    }

    public String getBody() {
        return body;
    }

    private void setBody(final String mBody) {
        this.body = mBody;
    }

    public static class Builder {

        private final Request request = new Request();

        public Builder setUrl(final String pUrl) {
            this.request.setUrl(pUrl);
            return this;
        }

        public Builder setHeaders(final Map<String, String> pHeaders) {
            this.request.setHeaders(pHeaders);
            return this;
        }

        public Builder setBody(final String pBody) {
            this.request.setBody(pBody);
            return this;
        }

        public Builder setMethod(final String pMethod) {
            this.request.setMethod(pMethod);
            return this;
        }

        public Request build() {
            return request;
        }
    }
}