package com.theironyard.EventChannel;

import com.theironyard.EventChannel.Objects.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/hello/{channel}")
    public void Greeting(@DestinationVariable String channel, EventMessage message) throws Exception {
        // TODO: server stuff here.

        switch (message.type){
            case "move":
                Direction d = new Direction();
                d.name = message.getMessage();
                template.convertAndSend("/channel/lincoln", new EventResponse("You are walking " +d.name));


                break;

            case "use":
                template.convertAndSend("/channel/lincoln", new EventResponse("You are doing it guy!"));

                break;

            case "take":
                template.convertAndSend("/channel/lincoln", new EventResponse("You have aquired " + message.getMessage()));

                break;

            case "search":
                template.convertAndSend("/channel/lincoln", new EventResponse("You have searched the " + message.getMessage()));

                break;

            case "inventory":
                break;

            default:
        }


        }

        // First arg: where to send it (channel name)
        // Second arg: what to send
        //template.convertAndSend("/channel/lincoln", new EventResponse("I hear ya: " + message.getMessage()));
//        System.out.println(message.getMessage());
    }

//    @MessageMapping("/hello/{channel}")
//    public void EventResponse(@DestinationVariable String channel, EventMessage message) throws Exception {
//        template.convertAndSend(String.format("/channel/%s", channel), new EventResponse("" + message.getMessage()));
//        System.out.println(message.getMessage());
//    }
