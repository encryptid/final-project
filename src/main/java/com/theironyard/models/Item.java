package com.theironyard.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Item {
    private String name;

    @JsonIgnore
    private String uTakeText;

    @JsonIgnore
    private String oTakeText;

    @JsonIgnore
    private String uUseText;

    @JsonIgnore
    private String oUseText;

    @JsonIgnore
    private String uSearchText;

    @JsonIgnore
    private String oSearchText;

    public Item(String name, String uTakeText, String oTakeText, String uUseText, String oUseText, String uSearchText, String oSearchText) {
        this.name = name;
        this.uTakeText = uTakeText;
        this.oTakeText = oTakeText;
        this.uUseText = uUseText;
        this.oUseText = oUseText;
        this.uSearchText = uSearchText;
        this.oSearchText = oSearchText;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuTakeText() {
        return uTakeText;
    }

    public void setuTakeText(String uTakeText) {
        this.uTakeText = uTakeText;
    }

    public String getoTakeText() {
        return oTakeText;
    }

    public void setoTakeText(String oTakeText) {
        this.oTakeText = oTakeText;
    }

    public String getuUseText() {
        return uUseText;
    }

    public void setuUseText(String uUseText) {
        this.uUseText = uUseText;
    }

    public String getoUseText() {
        return oUseText;
    }

    public void setoUseText(String oUseText) {
        this.oUseText = oUseText;
    }

    public String getuSearchText() {
        return uSearchText;
    }

    public void setuSearchText(String uSearchText) {
        this.uSearchText = uSearchText;
    }

    public String getoSearchText() {
        return oSearchText;
    }

    public void setoSearchText(String oSearchText) {
        this.oSearchText = oSearchText;
    }

    public static Item peanuts = new Item("peanuts",
            "You have the mofo peanuts!",
            "%s just picked up a bag of peanuts, with luck they'll share.",
            "You ate the mofo peanuts",
            "%s ate all the peanuts. Mmmm peanuts.",
            "You search the peanuts. What exactly are you looking for?",
            "%s looks carefully inside the peanuts. What a weirdo.");

//    public static Item lamp = new Item("lamp",
//            "You could take the lamp but it kinda needs to be plugged in... so you can't take the lamp!",
//            "YOU ARE NOW ENLIGHTENED... wait no the room just got brighter because %s turned on a lamp",
//            "Lamp");
//    public static Item letterOpener = new Item("letter opener",
//            "Careful, %s just picked up a dull knife like object!",
//    "No mail for %s the letter opener does nothing!",
//            "It has all the qualities you’d look for in a letter opener; it’s pointy, and it has a handle");
//    public static Item deskCalendar = new Item("calendar",
//            "",
//            "",
//            "This guy doesn't get out much...");
//    public static Item penCup = new Item("pen cup",
//            "%s took the pen for it is mightier than the sword!",
//            "You have drawn a stick figure on your hand...",
//            "You think you see a pencil in there. What kind of monster would do such a thing?");
//    public static Item matchbook = new Item("matchbook",
//            "",
//            "",
//            "");
//    public static Item cigarCutter = new Item("cigar cutter",
//            "",
//            "",
//            "You can’t help but feel the hole is perfectly finger-sized… Never mind, shake it off!");
//    public static Item phonograph = new Item("phonograph",
//            "",
//            "",
//            "");
//    public static Item teaStation = new Item("calendar",
//            "",
//            "",
//            "");
//    public static Item painting = new Item("painting",
//            "",
//            "The painting seems to be on hinges. You can open it, revealing a safe. It’s a little cliche, but the possibility of finding swag is appealing!",
//            "It’s a Depression-era landscape, probably fine art, but to you, it's literally kinda depressing. There’s something odd about it.");
//    public static Item grandFatherClock = new Item("Grandfather Clock",
//            "",
//            "You open the cabinet. It chimes… nuts. You were really hoping for that bird.",
//            "You wonder if it cuckoos or chimes. Hopefully, it cuckoos. You like birds.");
//    public static Item glassBowl = new Item("glass bowl",
//            "",
//            "",
//            "");





    public static void createRoom(){
        Room.items.add(peanuts);

    }



}

