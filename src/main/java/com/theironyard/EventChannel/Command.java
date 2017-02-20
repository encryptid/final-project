package com.theironyard.EventChannel;

import com.theironyard.entities.Item;
import com.theironyard.entities.User;

public class Command {
    private String type;
    private Item value;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item getValue() {
        return value;
    }

    public void setValue(Item value) {
        this.value = value;
    }
}