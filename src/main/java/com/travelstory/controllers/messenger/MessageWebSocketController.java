package com.travelstory.controllers.messenger;

import com.travelstory.dto.messenger.MessageDTO;
import com.travelstory.dto.messenger.MessengerUserDTO;
import com.travelstory.entity.MessageType;
import com.travelstory.services.messenger.MessageService;
import com.travelstory.utils.modelmapper.ModelMapperDecorator;
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
public class MessageWebSocketController {

    private SimpMessageSendingOperations messagingTemplate;

    private MessageService messageService;

    private ModelMapperDecorator modelMapperDecorator;

    @Autowired
    public MessageWebSocketController(SimpMessageSendingOperations messagingTemplate, MessageService messageService,
            ModelMapperDecorator modelMapperDecorator) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.modelMapperDecorator = modelMapperDecorator;
    }

    @MessageMapping("/{chatId}/message")
    public void sendMessageString(@DestinationVariable Long chatId, @RequestBody Map<String, Object> jsonBody) {
        LocalDateTime currTime = LocalDateTime.now();
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setMessageType(MessageType.valueOf((String) jsonBody.get("messageType")));
        messageDTO.setCreatedAt(LocalDateTime.now());
        messageDTO.setMessageContent((String) jsonBody.get("messageContent"));
        messageDTO.setUser(modelMapperDecorator.map(jsonBody.get("user"), MessengerUserDTO.class));

        messageDTO.setId(messageService.save(messageDTO, chatId));

        messagingTemplate.convertAndSend(format("/chat/%s/messages", chatId), messageDTO);
    }
    //
    // @MessageMapping("/{chatId}/testmessage") TODO why this doesn't work!???

    // public void testSendMessage(@DestinationVariable Long chatId, String m) throws Exception {
    // messagingTemplate.convertAndSend(format("/chat/%s/testmessages", chatId), m);
    // }
}
