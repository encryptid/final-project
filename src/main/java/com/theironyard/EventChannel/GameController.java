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
                broadcastToUsers(ChatOut);

                break;                                                                 

            case "take":
                String takeItem = input.getValue();

                Optional<Item> item = Room.items.stream().filter(i -> i.getName().equalsIgnoreCase(takeItem)).findFirst();

                User u = Room.players.get(sha.getSessionId());


                if (item.isPresent()) {
                    StoryOutput TakeOut = new StoryOutput(item.get().getName(), "event", Room.players.get(sha.getSessionId()));
                    broadcastToUsers(TakeOut);

                    String insiderMsg = String.format(item.get().getTakeText(), "You");
                    String outsiderMsg = String.format(item.get().getTakeText(), u.getName());


                    Room.items.remove(item.get());
                    u.getInv().add(item.get());
                    broadcastToUsers(TakeOut);
                } else {
                    template.convertAndSendToUser(sha.getSessionId(), "/", "You don't see that item in the room.");
                }
                break;

            case "use":
                String UseItem = input.getValue();

                Optional<Item> item1 = User.inv.stream().filter(i -> i.getName().equalsIgnoreCase(UseItem)).findFirst();

            case "search":
                String searchItem = input.getValue();

                Optional<Item> item2 = Room.items.stream().filter(i -> i.getName().equalsIgnoreCase(searchItem)).findFirst();


            case "inventory":

                template.convertAndSendToUser(sha.getSessionId(), "/", User.inv);

            case "help":

                template.convertAndSendToUser(sha.getSessionId(), "/", "The commands take, use, and search can be used on any item.\n" +
                        "The command inventory will display the items you have.\nAnd this is, of course the help command... not much help really.");





            default:

        }
    }



    // send to everyone else.
    // for send to everyone, remove the if.
//                for (String session : Room.players.keySet()) {
//                    if (session.equals(sha.getSessionId())) {
//                        continue;
//                   template.convertAndSendToUser(session, "/", input);


    private void broadcastToUsers(StoryOutput out) {
        for (String session : Room.players.keySet()) {
            template.convertAndSendToUser(session, "/", out);

        }
    }
}
