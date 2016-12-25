package com.maxcriser.cards.model;

import com.maxcriser.cards.constant.ListConstants;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingsJson {

    private String bodyShare = ListConstants.EMPTY_STRING;
    private String appVersion = ListConstants.EMPTY_STRING;
    private String titleShare = ListConstants.EMPTY_STRING;
    private String about = ListConstants.EMPTY_STRING;
    private String googlePlayUrl = ListConstants.EMPTY_STRING;
    private String message = ListConstants.EMPTY_STRING;
    private boolean flagMessage;

    public SettingsJson(final String json) {
        final JSONObject dataJsonObj;
        try {
            dataJsonObj = new JSONObject(json);

            final String body_share = "body_share";
            final String title_share = "title_share";
            final String about = "about";
            final String google_play_url = "google_play_url";
            final String message = "message";
            final String appVersion = "app_version";
            final String flag_message = "flag_message";

            setBodyShare(dataJsonObj.getString(body_share));
            setTitleShare(dataJsonObj.getString(title_share));
            setAbout(dataJsonObj.getString(about));
            setGooglePlayUrl(dataJsonObj.getString(google_play_url));
            setMessage(dataJsonObj.getString(message));
            setFlagMessage(dataJsonObj.getBoolean(flag_message));
            setAppVersion(dataJsonObj.getString(appVersion));

        } catch (final JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBodyShare() {
        return bodyShare;
    }

    public String getTitleShare() {
        return titleShare;
    }

    public String getAbout() {
        return about;
    }

    public String getGooglePlayUrl() {
        return googlePlayUrl;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFlagMessage() {
        return flagMessage;
    }

    private void setAppVersion(final String pAppVersion) {
        appVersion = pAppVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    private void setBodyShare(final String pBodyShare) {
        bodyShare = pBodyShare;
    }

    private void setTitleShare(final String pTitleShare) {
        titleShare = pTitleShare;
    }

    private void setAbout(final String pAbout) {
        about = pAbout;
    }

    private void setGooglePlayUrl(final String pGooglePlayUrl) {
        googlePlayUrl = pGooglePlayUrl;
    }

    public void setMessage(final String pMessage) {
        message = pMessage;
    }

    private void setFlagMessage(final boolean pFlagMessage) {
        flagMessage = pFlagMessage;
    }
}
