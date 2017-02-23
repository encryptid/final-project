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

    public static Item i1 = new Item("peanuts",
            "You have the mofo peanuts!",
            "%s just picked up a bag of peanuts, with luck they'll share.",
            "You ate the mofo peanuts",
            "%s ate all the peanuts. Mmmm peanuts.",
            "You search the peanuts. What exactly are you looking for?",
            "%s looks carefully inside the peanuts. What a weirdo.");

    public static Item i2 = new Item("LAMP",
            "You could take the lamp but it kinda needs to be plugged in... so you can't take the lamp!",
            "You see %s near the desk trying to steal a lamp?",
            "YOU ARE NOW ENLIGHTENED... wait no the room just got brighter because you turned the lamp on.",
            "All of the sudden you can see much better because %s turned on a lamp.",
            "Yep, that's a lamp all right.",
            "You can't help but notice %s is super interested in the desk lamp.");

    public static Item i3 = new Item("LETTER OPENER",
            "You now have a letter opener because let's face it opening mail needs tools.",
            "Careful, %s just picked up a dull knife like object! (It's just a letter opener",
            "No mail for you, the letter opener does nothing!",
            "%s seems to be fighting invisible penguins with a letter opener?",
            "It has all the qualities you’d look for in a letter opener; it’s pointy, and it has a handle.",
            "%s is over there squinting at a letter opener?");

    public static Item i4 = new Item("CALENDAR",
            "Really? You're going to steal some dude's calendar?",
            "You see %s guiltily staring at something on the desk.",
            "You write 'rhinoplasty 9 a.m.' on every Friday this month.",
            "%s is scribbling on something.",
            "This guy doesn't get out much...",
            "%s is over there flipping through pages on what could be a calendar.");

    public static Item i5 = new Item("PEN CUP",
            "You take a pen for it is mightier than the sword!",
            "%s just stole some dudes pen!",
            "You have drawn a stick figure on your hand...",
            "Looks like %s is drawing on themselves... glad you're there with them and not me",
            "You think you see a pencil in there. What kind of monster would do such a thing?",
            "%s is messing with pens in a cup on the desk AS NOISILY AS POSSIBLE!");

    public static Item i6 = new Item("MATCHBOOK",
            "",
            "",
            "",
            "",
            "",
            "");

    public static Item i7 = new Item("CIGAR CUTTER",
            "",
            "",
            "",
            "",
            "You can’t help but feel the hole is perfectly finger-sized… Never mind, shake it off!",
            "");

    public static Item i8 = new Item("PHONOGRAPH",
            "",
            "",
            "",
            "",
            "",
            "");

    public static Item i9 = new Item("TEA KETTLE",
            "",
            "",
            "",
            "",
            "",
            "");

    public static Item i10 = new Item("PAINTING",
            "",
            "",
            "",
            "",
            "The painting seems to be on hinges. You open it, revealing a safe. It’s a little cliche, but the possibility of finding swag is appealing!",
            "It’s a Depression-era landscape, probably fine art, but to you, it's literally kinda depressing. There’s something odd about it.");

    public static Item i11 = new Item("GRANDFATHER CLOCK",
            "",
            "",
            "",
            "",
            "You open the cabinet. It chimes… nuts. You were really hoping for that bird.",
            "You wonder if it cuckoos or chimes. Hopefully, it cuckoos. You like birds.");

    public static Item i12 = new Item("glass bowl",
            "",
            "",
            "",
            "",
            "",
            "");

    public static Item i13 = new Item("",
            "",
            "",
            "",
            "",
            "",
            "");

    public static Item i14 = new Item("",
            "",
            "",
            "",
            "",
            "",
            "");

    public static Item i15 = new Item("",
            "",
            "",
            "",
            "",
            "",
            "");



    public static void createRoom(){
        Room.items.add(i1); Room.items.add(i2); Room.items.add(i3); Room.items.add(i4); Room.items.add(i5);
        Room.items.add(i6); Room.items.add(i7); Room.items.add(i8); Room.items.add(i9); Room.items.add(i10);
        Room.items.add(i11); Room.items.add(i12); Room.items.add(i13); Room.items.add(i14); Room.items.add(i15);
    }



}

