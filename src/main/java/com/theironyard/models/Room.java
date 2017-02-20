package com.theironyard.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    public String name;
    public String description;
    public static Map<String, User> players = new HashMap<>();
    public static List<Item> items = new ArrayList<>();



}
