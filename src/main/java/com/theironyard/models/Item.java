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
            "YOU ARE NOW ENLIGHTENED... wait no the room just got brighter because you turned the lamp on.\n " +
                    "The added light reveals a PAINTING in the corner beside an old GRANDFATHER CLOCK.",
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
            "Never know when you'll need to set some fires!",
            "%s just snagged a book of matches!",
            "You light up every match\n" +
                    "In an over-sized pack letting each one burn\n" +
                    "Down to your thick fingers before blowing and\n" +
                    "Cursing them out...",
            "%s is playing with fire! No literally they have lit matches!",
            "It says MacArthur park?",
            "%s is looking at a MATCHBOOK like a crazy person...");

    public static Item i7 = new Item("CIGAR CUTTER",
            "Not really sure why but you now have a cigar guillotine, though I myself prefer a punch",
            "You could swear %s just put something in their pocket!",
            "How about no!",
            "Did %s just try to cut their own finger off?",
            "You can’t help but feel the hole is perfectly \"finger\"-sized… Never mind, shake it off!",
            "%s is exceedingly strange");

    public static Item i8 = new Item("PHONOGRAPH",
            "You can't just TAKE one of these. They weigh, like, 85 pounds.",
            "%s is trying to take the phonograph. It would be funny if it wasn't so sad...",
            "Where's the remote for this thing?",
            "%s is attempting to use the phonograph. It seems apparent that they don't know what they're doing.",
            "It’s one of those old-timey record players, with the big horn on it. Dude… I bet Dark Side of the Moon would sound trippy on this!",
            "What is %s doing over by the phonograph? They better not be adjusting the speed...");

    public static Item i9 = new Item("TEA KETTLE",
            "You don't want to spend the rest of the time here lugging an iron kettle around.",
            "If %s wants to lug around an iron kettle for the rest of their time here, they can be your guest.",
            "You probably wouldn't even get the water boiling in time!",
            "You really wish %s was helping you look for a way out of here instead of trying to make tea right now.",
            "It’s smaller than a normal kettle and cast-iron. It has an oriental look to it. Somebody either takes tea very seriously or is kind of a hipster. It’s a fine line these days.",
            "%s is staring intently at the tea kettle.");

    public static Item i10 = new Item("PAINTING",
            "It might be valuable, but you don't have pockets THAT big.",
            "%s seems to be contemplating the best way to make off with the painting.",
            "The PAINTING seems to be on hinges. You open it, revealing a safe. It’s a little cliche, but the possibility of finding swag is appealing!",
            "%s is getting up close and personal with that painting over there...",
            "It’s a Depression-era landscape, probably fine art, but to you, it's literally kinda depressing. There’s something odd about it.",
            " %s seems transfixed by the painting.");

    public static Item i11 = new Item("GRANDFATHER CLOCK",
            "It looks pretty good where it is.",
            "You hope %s isn't planning on trying to move that clock...",
            "You open the cabinet. It chimes… nuts. You were really hoping for that bird.",
            "%s is getting handsy with the grandfather clock.",
            "You wonder if it cuckoos or chimes. Hopefully, it cuckoos. You like birds.",
            "%s must really be interested in the time.");

    public static Item i12 = new Item("GLASS BOWL",
            "I mean, I guess you could take one of the decorative balls out, if you must.",
            "What is %s even doing over there?",
            "What would you use it for? It's utterly useless. You hate it.",
            "You should really tell %s to quit messing around with that stupid decorative bowl.",
            "It’s filled with those stupid useless decorative balls that look like they’re made out of tiny tumbleweeds or something. You hate it, and by extension, whoever put it here.",
            "%s is poking around the decorative glass bowl. Bully for them.");

    public static Item i13 = new Item("TABLE",
            "Yeah, that's probably not going to happen. Why do you make me say it? Do you want me to be that person? Is that what you want? I hate what you've turned me into.",
            "%s isn't letting a little thing like 'impossible' stop them from trying to take the table with them.",
            "I'm sorry, I didn't mean what I said before. You're a good person, I just get antsy sometimes. Oh, and you can't use a table. Come on.",
            "You weren't sure you knew what 'using' a table looked like before you saw %s try to do it.",
            "It's a pretty nice table, you guess. It's long and kind of tall. You think it's called a buffet, which only reminds you of how hungry you are.\n" +
                    "Scattered across it you see a GLASS BOWL, a PHONOGRAPH, and a TEA KETTLE.",
            "Is that a... buffet table? Well, anyway, %s is looking at it.");

    public static Item i14 = new Item("DESK",
            "Bruh, you can't take the desk!",
            "Looks like %s is trying to put the desk in their pocket?",
            "You can't use a desk. What would you even use it for?",
            "Who knows what %s is doing?",
            "A large desk. It would be rather intimidating to face your boss on the other side of this desk. It is littered with the typical desk miscellany, including a LAMP, a LETTER OPENER, a DESK CALENDAR and a PEN CUP.",
            "%s is rummaging through the desk. Hopefully they're finding something useful.");

    public static Item i15 = new Item("DRAWER",
            "I mean, let's be real; what are the chances you're going to need a desk drawer in the future? We're just going to leave that here.",
            "You can't even begin to guess what %s is doing.",
            "There's not much use for desk drawers. Unless you just enjoy pushing and pulling things.",
            "You can say with certainty that %s is using a desk drawer. Hm. So that's what that look's like.",
            "If it’s one thing you don’t need, it’s more paperclips. Wait, there’s something interesting in here… A key!",
            "%s is rummaging through the drawers. They better not have found cooler stuff than you!");

    public static Item i16 = new Item("BOOKSHELF",
            "Dude. Seriously? We're not taking a bookshelf.",
            "It looks like %s is about to pull a bookshelf down on themselves.",
            "A bookshelf is useful for one thing; holding books. It's doing that fine without your intervention.",
            "When you first saw %s, you had them pegged as someone who really appreciated shelving. Judging by their interest in that bookshelf, you were right. Again.",
            "It smells of rich mahogany and there are many leather-bound books. They’re probably valuable. One BOOK stands apart from the others.",
            "You see %s staring confusedly at the BOOKSHELF, wonder what their taste in books is like...'");

    public static Item i17 = new Item("BOOK",
            "Ooh, a book.",
            "It's hard to say for certain, but it looks like %s just stuffed a book in their pocket.",
            "Books are for readin', not for usin'.",
            "Who can say what %s is doing over there with that book?",
            "It's called 'Talking to Your Dog About Communism'. That sounds delightful.",
            "%s is reading or   trying    to");
    public static Item i18 = new Item("KEY",
            "Yay, a key!",
            "Aw, man! %s has a key! What have you found? Just dumb stuff. You should feel bad about yourself.",
            "It's not much use on its own...",
            "Watching %s use a key on nothing has left you feeling somewhat empty inside...",
            "It has a shiny quality to it.",
            "Aw. %s found a key. You've only found stupid stuff.");
    public static Item i19 = new Item("CHAIR",
            "What is your obsession with trying to take furniture, huh?",
            "%s is never going to be able to take that chair, but that won't stop you from watching them try.",
            "As much as you deserve a rest, you have things to do.",
            "%s is taking a load off.",
            "It’s leather and grand. It looks quite old and comfy, but well taken care of. You could imagine listening to a jazz record while smoking a cigar in it. And you don’t even like any of those things!\n" +
                    "There is a smoker's stand on the chair arm holding a MATCHBOOK and CIGAR CUTTER. ",
            "%s is giving that old chair a good looking-at.");
    public static Item i20 = new Item("PORTRAIT",
            "I give up; you want this painting, take it. See if I care.",
            "It looks like %s is giving taking that portrait the ol' college try. You envy their spirit.",
            "You drew a mustache upon the man in the painting. That ought to take him down a notch or two!",
            "%s is drawing a mustache on that portrait over there. Take that, portrait!",
            "there is a large painting of stately man with a far away look in his eye. He’s doing that Napoleon thing where his hand is tucked into his jacket. Is this guy for real?",
            "%s is having a staring contest with the portrait. The dude in the portrait is winning.");



    public static void createRoom(){
        Room.items.add(i1); Room.items.add(i2); Room.items.add(i3); Room.items.add(i4); Room.items.add(i5);
        Room.items.add(i6); Room.items.add(i7); Room.items.add(i8); Room.items.add(i9); Room.items.add(i10);
        Room.items.add(i11); Room.items.add(i12); Room.items.add(i13); Room.items.add(i14); Room.items.add(i15);
        Room.items.add(i16); Room.items.add(i17); Room.items.add(i18); Room.items.add(i19); Room.items.add(i20);
    }



}

