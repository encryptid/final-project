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
    public void Greeting(@DestinationVariable String channel, ChatMessage message) throws Exception {
        template.convertAndSend(String.format("/topic/%s", channel), new Greeting("" + message.getMessage()));
        System.out.println(message);
    }
}