package com.travelstory.controllers.messenger;

import com.travelstory.dto.messenger.MessageDTO;
import com.travelstory.dto.messenger.MessengerUserDTO;
import com.travelstory.entity.MessageType;
import com.travelstory.services.messenger.MessageService;
import com.travelstory.utils.ModelMapperDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Map;

import static java.lang.String.format;

@Controller
public class TestWebSocketController {
    // @MessageMapping("/hello")
    // @SendTo("/topic/messages")
    // public MessageDTO sendMessage(MessageDTO message) throws Exception {
    // Thread.sleep(200); // simulated delay
    //
    // LocalDateTime currTime = LocalDateTime.now();
    // return message;
    // }
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ModelMapperDecorator modelMapperDecorator;

    @MessageMapping("/{chatId}/message")
    // @SendTo("/chat/{chatId}/messages")
    public void sendMessageString(@DestinationVariable Long chatId, String message,
            @RequestBody Map<String, Object> payload) throws Exception {
        Thread.sleep(200); // simulated delay
        LocalDateTime currTime = LocalDateTime.now();

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessageType(MessageType.valueOf((String) payload.get("messageType")));
        messageDTO.setCreatedAt(LocalDateTime.now());
        messageDTO.setMessageContent((String) payload.get("messageContent"));
        messageDTO.setUser(modelMapperDecorator.map(payload.get("user"), MessengerUserDTO.class));

        messageService.save(messageDTO, chatId);

        messagingTemplate.convertAndSend(format("/chat/%s/messages", chatId), message);
    }

    // @MessageMapping("/messenger/message/{chatId}")
    // @SendTo("/messenger/{chatId}/messages")
    // public MessageDTO addChat(MessageDTO message) throws Exception {
    // Thread.sleep(200); // simulated delay
    // LocalDateTime currTime = LocalDateTime.now();
    // return message;
    // }
}
