package com.travelstory.entity;

public class TokenModel {
    private String accessToken;

    public TokenModel() {
    }

    public TokenModel(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
