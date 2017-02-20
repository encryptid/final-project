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

@Controller
public class GameController {
    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/user-input")
        public void greeting(Message message, Command input) throws Exception {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
        input.setUser(Room.players.get(sha.getSessionId()));

        switch (input.getType()) {

            case "chat":
                template.convertAndSendToUser(sha.getSessionId(), "/", new StoryOutput(input.getValue(), Room.players.get(sha.getSessionId())));
                for (String session : Room.players.keySet()) {
                    if (session.equals(sha.getSessionId())) {
                        continue;
                    }
                    break;
                }

            case "take":



                // send to everyone else.
                // for send to everyone, remove the if.
                for (String session : Room.players.keySet()) {
                    if (session.equals(sha.getSessionId())) {
                        continue;
                    }

                    template.convertAndSendToUser(session, "/", input);
                }

            default:

        }
    }

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
}

//    @MessageMapping("/hello/{channel}")
//    public void respond(@DestinationVariable String channel, StoryOutput message) throws Exception {
//        // TODO: server stuff here.
//
//        switch (input.type){
//
//            case "use":
//                template.convertAndSend("/user-input", new StoryOutput("You are doing it guy!"));
//
//                break;
//
//            case "take":
//                template.convertAndSend("/user-input", new StoryOutput("You now have" + input.value));
//itemInv.add(input.user, input.value);
// //TODO: Change this to db commands?


//                break;
//
//            case "search":
//                template.convertAndSend("/user-input", new StoryOutput(""));
//
//                break;
//
//            case "inventory":
//                template.convertAndSend("/user-input", new StoryOutput(""));

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
