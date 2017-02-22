package com.theironyard.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String sessionId;
    public String name;
    public ArrayList<Item> inv = new ArrayList<>();

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
}