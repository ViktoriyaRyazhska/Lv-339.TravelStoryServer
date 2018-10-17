package com.travelstory.controllers.messenger;

import com.travelstory.dto.messenger.ChatDTO;
import com.travelstory.dto.messenger.ChatDetailsDTO;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.services.messenger.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/messenger")
public class ChatController {
    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{id}/chats")
    public List<ChatDTO> getChatsByUserId(@PathVariable Long id) {
        log.debug("test");
        if (true) {
            throw new ResourceNotFoundException("", ExceptionCode.CHAT_NOT_FOUND);
        }
        return chatService.getAllByUserId(id);
    }

    @GetMapping("/chat/{id}")
    public ChatDetailsDTO getChatById(@PathVariable Long id) {
        return chatService.get(id);
    }

    /*
     * @GetMapping("/chat/{id}") public ChatDetailsDTO getChatById(@PathVariable Long id , @RequestParam("userId") Long
     * userId ) {
     * 
     *//*
        * if (userId == null) { userId = (long) 1; // TODO later I will take this data from token }
        *//*
           * return chatService.get(id); }
           */

}
