package com.travelstory.controllers.messenger;

import com.travelstory.dto.messenger.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MessageWebSocketController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public MessageDTO sendMessage(MessageDTO message) throws Exception {
        Thread.sleep(200); // simulated delay

        LocalDateTime currTime = LocalDateTime.now();
        return message;
    }

    @MessageMapping("/messenger/{chatId}")
    @SendTo("/messenger/{chatId}/messages")
    public MessageDTO addChat(MessageDTO message) throws Exception {
        Thread.sleep(200); // simulated delay
        LocalDateTime currTime = LocalDateTime.now();
        return message;
    }
}
