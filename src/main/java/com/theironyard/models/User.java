package com.theironyard.models;

import java.util.List;

public class User {
    private String sessionId;
    public String name;
    public static List<Item> inv;

    public User() {}

    public User(String sessionId, String name) {
        this.sessionId = sessionId;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getInv() {
        return inv;
    }

    public void setInv(List<Item> inv) {
        this.inv = inv;
    }
}