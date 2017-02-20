package com.theironyard.models;


public class Item {
    private String name;
    private boolean isTaken;
    private boolean isUsed;

    public Item(String name, boolean isTaken, boolean isUsed) {
        this.name = name;
        this.isTaken = isTaken;
        this.isUsed = isUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
    Item peanuts = new Item("peanuts", false, false);




}

