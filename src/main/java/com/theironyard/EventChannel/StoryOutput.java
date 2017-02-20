package com.theironyard.EventChannel;

import com.theironyard.entities.User;

public class StoryOutput {

    private String value;
    private User user;

    public StoryOutput(String value, User user) {
        this.value = value;
        this.user = user;
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
