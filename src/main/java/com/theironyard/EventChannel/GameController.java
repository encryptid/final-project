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
            StoryOutput intro = new StoryOutput("Intro text", "event", Room.players.get(sha.getSessionId()));
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

        switch (input.getType()) {

            case "chat":
                StoryOutput ChatOut = new StoryOutput(input.getValue(), "chat", Room.players.get(sha.getSessionId()));
                broadcastToOtherUsers(ChatOut);
                broadcastToSingleUser(ChatOut);
                //This responds to command of type chat by sending story output that contains the message to all users.

                break;

            case "take":
                String takeItem = input.getValue();

                Optional<Item> item = Room.items.stream().filter(i -> i.getName().equalsIgnoreCase(takeItem)).findFirst();

                User u = Room.players.get(sha.getSessionId());

                if (item.isPresent()) {
                    StoryOutput takeOut = new StoryOutput(takeItem, "event", Room.players.get(sha.getSessionId()));
                    Room.items.remove(item.get());
                    u.getInv().add(item.get());

                    takeOut.setValue(String.format(item.get().getoTakeText(), takeOut.getUser().getName()));
                    broadcastToOtherUsers(takeOut);

                    takeOut.setValue(item.get().getuTakeText());
                    broadcastToSingleUser(takeOut);

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
                    StoryOutput noTake = new StoryOutput("If I could use things I don't have I wouldn't be a computer programmer.", "event", Room.players.get(sha.getSessionId()));
                    broadcastToSingleUser(noTake);
                }
                break;
            case "search":
                String searchItem = input.getValue();

                Optional<Item> item2 = Room.players.get(sha.getSessionId()).getInv().stream().filter(i -> i.getName().equalsIgnoreCase(searchItem)).findFirst();

                if (item2.isPresent()) {
                    StoryOutput searchOut = new StoryOutput(searchItem, "event", Room.players.get(sha.getSessionId()));

                    searchOut.setValue(String.format(item2.get().getoSearchText(), searchOut.getUser().getName()));
                    broadcastToOtherUsers(searchOut);

                    searchOut.setValue(item2.get().getuSearchText());
                    broadcastToSingleUser(searchOut);

                } else {
                    StoryOutput noTake = new StoryOutput("If I could use things I don't have I wouldn't be a computer programmer.", "event", Room.players.get(sha.getSessionId()));
                    broadcastToSingleUser(noTake);
                }
                break;

            case "get":

                StoryOutput help = new StoryOutput("The commands take, use, and search can be used on any item.\n" +
                        "The command inventory will display the items you have.\nAnd this is, of course the help command... not much help really.",
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



