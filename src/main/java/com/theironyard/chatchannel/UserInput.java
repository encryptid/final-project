package com.theironyard.chatchannel;

import com.theironyard.entities.User;

public class UserInput {
    private String message;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = message;
    }
}
