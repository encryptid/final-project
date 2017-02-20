package com.theironyard.models;


import java.util.ArrayList;

public class Item {
    private String name;
    private String takeText;
    private String useText;
    private String searchText;
    private boolean isTaken;
    private boolean isUsed;




    public Item(String name, String takeText, String useText, String searchText, boolean isTaken, boolean isUsed) {
        this.name = name;
        this.takeText = takeText;
        this.useText = useText;
        this.searchText = searchText;
        this.isTaken = isTaken;
        this.isUsed = isUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTakeText() {
        return takeText;
    }

    public void setTakeText(String takeText) {
        this.takeText = takeText;
    }

    public String getUseText() {
        return useText;
    }

    public void setUseText(String useText) {
        this.useText = useText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
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



    public static Item peanuts = new Item("peanuts", "You have the peanuts!",
            "Mmmm peanuts!",
            "Just some peanuts",
            false, false);
}

