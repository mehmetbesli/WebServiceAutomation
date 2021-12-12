package com.testinium.model;

public class AuthModel {
    private String key;
    private String token;

    @Override
    public String toString() {
        return "AuthModel{" +
                "key='" + key + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
