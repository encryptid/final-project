package com.theironyard.models;

import com.theironyard.models.User;

public class StoryOutput {

    private String value;
    private String type;
    private User user;

    public StoryOutput(String value) {
        this.value = value;
    }

    public StoryOutput(String value, String type, User user) {
        this.value = value;
        this.type = type;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
