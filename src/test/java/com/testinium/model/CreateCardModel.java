package com.testinium.model;

public class CreateCardModel {
    private String idList;
    private String name;
    private String key;
    private String token;

    @Override
    public String toString() {
        return "CreateCardModel{" +
                "idList='" + idList + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }
}
