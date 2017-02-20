package com.theironyard.EventChannel;

import com.theironyard.entities.User;
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

import java.util.HashMap;
import java.util.Map;

@Controller
public class EventController {
    Map<String, User> usersList = new HashMap<>();

    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/user-input")
    public void greeting(Message message, Command input) throws Exception {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
        input.setUser(usersList.get(sha.getSessionId()));

        // send to everyone else.
        // for send to everyone, remove the if.
        for(String session : usersList.keySet()) {
            if (session.equals(sha.getSessionId())) {
                continue;
            }

            template.convertAndSendToUser(session, "/", input);
        }
    }

    @EventListener
    public void stompEventHandler(AbstractSubProtocolEvent ev) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(ev.getMessage());

        if (ev instanceof SessionConnectEvent) {
            System.out.println("Connect event [sessionId: " + sha.getSessionId() +"]");
            String name = sha.getNativeHeader("name").get(0);

            usersList.put(sha.getSessionId(), new User(sha.getSessionId(), name));
        } else if (ev instanceof SessionDisconnectEvent) {
            System.out.println("Disconnect Event: [sessionId: " + sha.getSessionId() +"]");
            usersList.remove(sha.getSessionId());
        } else if (ev instanceof SessionSubscribeEvent) {
            System.out.println("Client Channel Subscription");
        } else {
            System.out.println("Other Event:" + ev.getClass());
        }
    }
}

//@Controller
//public class EventController {
//
//    public static HashMap<String, User> userSessions = new HashMap<>();
//
//    @Autowired
//    SimpMessagingTemplate template;
//
//    @MessageMapping("/hello/{channel}")
//    public void Greeting(@DestinationVariable String channel, EventMessage message) throws Exception {
//        // TODO: server stuff here.
//
//        switch (message.type){
//            case "move":
//                Direction d = new Direction();
//                d.name = message.getMessage();
//                template.convertAndSend("/channel/lincoln", new EventResponse("You are walking " +d.name));
//
//
//                break;
//
//            case "use":
//                template.convertAndSend("/channel/lincoln", new EventResponse("You are doing it guy!"));
//
//                break;
//
//            case "take":
//                template.convertAndSend("/channel/lincoln", new EventResponse("You have aquired " + message.getMessage()));
//
//                break;
//
//            case "search":
//                template.convertAndSend("/channel/lincoln", new EventResponse("You have searched the " + message.getMessage()));
//
//                break;
//
//            case "inventory":
//                break;
//
//            default:
//        }
//
//
//        }

        // First arg: where to send it (channel name)
        // Second arg: what to send
        //template.convertAndSend("/channel/lincoln", new EventResponse("I hear ya: " + message.getMessage()));
//        System.out.println(message.getMessage());


//    @MessageMapping("/hello/{channel}")
//    public void EventResponse(@DestinationVariable String channel, EventMessage message) throws Exception {
//        template.convertAndSend(String.format("/channel/%s", channel), new EventResponse("" + message.getMessage()));
//        System.out.println(message.getMessage());
//    }
