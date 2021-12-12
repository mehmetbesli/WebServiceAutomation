package com.testinium.model;

public class ListOnBoardResponse {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "ListOnBoard{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
