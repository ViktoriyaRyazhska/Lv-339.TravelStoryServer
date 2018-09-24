package com.travelstory.controllers;

import com.travelstory.dto.chat.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MessageController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public MessageDTO greeting(MessageDTO message) throws Exception {
        Thread.sleep(500); // simulated delay
        LocalDateTime currTime = LocalDateTime.now();
        return message;
    }
}
