package com.theironyard.EventChannel;

import com.theironyard.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    SimpMessagingTemplate template;

    @EventListener
    public void stompEventHandler(AbstractSubProtocolEvent ev) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(ev.getMessage());

        if (ev instanceof SessionConnectEvent) {
            System.out.println("Connect event [sessionId: " + sha.getSessionId() + "]");
            String name = sha.getNativeHeader("name").get(0);
            System.out.println(name);
            Room.players.put(sha.getSessionId(), new User(sha.getSessionId(), name));
        } else if (ev instanceof SessionDisconnectEvent) {
            System.out.println("Disconnect Event: [sessionId: " + sha.getSessionId() + "]");
            Room.players.remove(sha.getSessionId());
        } else if (ev instanceof SessionSubscribeEvent) {
            System.out.println("Client Channel Subscription");
            StoryOutput intro = new StoryOutput("You find yourself in a dimly lit room." +
                    "It appears to be an office of some sort." +
                    "Looking around, you see a Victorian-era PORTRAIT on one wall," +
                    "as well as a DESK and a BOOKSHELF. Along another wall, you see a " +
                    "TABLE with a comfy looking CHAIR nearby. You'll probably have to explore... \n" +
                    "if you want to get out...\n The actions TAKE, USE and SEARCH can be used with any ITEM.\n Type GET on action HELP to see these again", "event", Room.players.get(sha.getSessionId()));
            broadcastToSingleUser(intro);
            StoryOutput joined = new StoryOutput("A new player has joined the game!\n" +
                    "Send them a message with the box below","event", Room.players.get(sha.getSessionId()));
            broadcastToOtherUsers(joined);

        } else {
            System.out.println("Other Event:" + ev.getClass());
        }
    }

    @MessageMapping("/user-input")
    public void greeting(Message message, Command input) throws Exception {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
        input.setUser(Room.players.get(sha.getSessionId()));
        User u = Room.players.get(sha.getSessionId());

        switch (input.getType()) {

            case "chat":
                StoryOutput ChatOut = new StoryOutput(input.getValue(), "chat", Room.players.get(sha.getSessionId()));
                broadcastToOtherUsers(ChatOut);
                broadcastToSingleUser(ChatOut);
                //This responds to command of type chat by sending story output that contains the message to all users.

                break;

            case "take":
                String takeItem = input.getValue();
                Item contextItem = null;


                Optional<Item> item = Room.items.stream().filter(i -> i.getName().equalsIgnoreCase(takeItem)).findFirst();


                if (item.isPresent()) {
                    StoryOutput takeOut = new StoryOutput(takeItem, "event", Room.players.get(sha.getSessionId()));

                    takeOut.setValue(String.format(item.get().getoTakeText(), takeOut.getUser().getName()));
                    broadcastToOtherUsers(takeOut);

                    takeOut.setValue(item.get().getuTakeText());
                    broadcastToSingleUser(takeOut);

                    u.getInv().add(item.get());

                } else {
                    StoryOutput noTake = new StoryOutput("You don't see that item in the room.", "event", Room.players.get(sha.getSessionId()));
                    broadcastToSingleUser(noTake);

                }
                break;

            case "use":
                String useItem = input.getValue();

                Optional<Item> item1 = Room.players.get(sha.getSessionId()).getInv().stream().filter(i -> i.getName().equalsIgnoreCase(useItem)).findFirst();

                if (item1.isPresent()) {
                    StoryOutput useOut = new StoryOutput(useItem, "event", Room.players.get(sha.getSessionId()));

                    useOut.setValue(String.format(item1.get().getoUseText(), useOut.getUser().getName()));
                    broadcastToOtherUsers(useOut);

                    useOut.setValue(item1.get().getuUseText());
                    broadcastToSingleUser(useOut);

                } else {
                    StoryOutput noUse = new StoryOutput("If I could use things I don't have I wouldn't be a computer programmer.", "event", Room.players.get(sha.getSessionId()));
                    broadcastToSingleUser(noUse);
                }
                break;
            case "search":
                String searchItem = input.getValue();
                Item contextItem2 = null;

                Optional<Item> itemInInv = Room.players.get(sha.getSessionId()).getInv()
                        .stream()
                        .filter(i -> i.getName().equalsIgnoreCase(searchItem))
                        .findFirst();

                Optional<Item> itemInRoom = Room.getItems()
                        .stream()
                        .filter(i -> i.getName().equalsIgnoreCase(searchItem))
                        .findFirst();

                if (itemInInv.isPresent()) {
                    contextItem2 = itemInInv.get();
                } else if (itemInRoom.isPresent()) {
                    contextItem2 = itemInRoom.get();
                }

                if (contextItem2 != null) {
                    StoryOutput searchOut = new StoryOutput(searchItem, "event", Room.players.get(sha.getSessionId()));

                    searchOut.setValue(String.format(contextItem2.getoSearchText(), searchOut.getUser().getName()));
                    broadcastToOtherUsers(searchOut);

                    searchOut.setValue(contextItem2.getuSearchText());
                    broadcastToSingleUser(searchOut);

                } else {
                    StoryOutput noSearch = new StoryOutput("Whatever you are searching for isn't here, but I hope you find it.", "event", Room.players.get(sha.getSessionId()));
                    broadcastToSingleUser(noSearch);
                }
                break;

            case "get":

                StoryOutput help = new StoryOutput("The commands TAKE, USE, and SEARCH can be used on any item.\n" +
                        "And this is, of course the help command... not much help really.",
                        "event", Room.players.get(sha.getSessionId()));
                broadcastToSingleUser(help);
                break;


            default:

        }
     }


    private void broadcastToOtherUsers(StoryOutput out) {
        for (String session : Room.players.keySet()) {
            if (session.equals(out.getUser().getSessionId())) {
                continue;
            }

            template.convertAndSendToUser(session, "/", out);
        }
    }

    private void broadcastToSingleUser(StoryOutput out) {
        template.convertAndSendToUser(out.getUser().getSessionId(), "/", out);
    }

}


// send to everyone else.
// for send to everyone, remove the if.
//                for (String session : Room.players.keySet()) {
//                    if (session.equals(sha.getSessionId())) {
//                        continue;
//                   template.convertAndSendToUser(session, "/", input);



