package com.theironyard;

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
                template.convertAndSend("/channel/lincoln", new EventResponse("You have moved " +message.getMessage()));

                break;

            case "use":
                break;

            case "take":
                break;

            case "search":
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
