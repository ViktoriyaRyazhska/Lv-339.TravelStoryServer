package com.travelstory.controllers.messenger;

import com.travelstory.dto.messenger.ChatDTO;
import com.travelstory.dto.messenger.ChatDetailsDTO;
import com.travelstory.services.messenger.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return chatService.getAllByUserId(id);
    }

    @GetMapping("/chat/{id}")
    public ChatDetailsDTO getChatById(@PathVariable Long id) {
        return chatService.get(id);
    }
    // @PutMapping("/updateProfilePic")
    // User uploadProfilePicture(@RequestBody UserDTO userDetails) throws IOException {
    // return userService.uploadProfilePicture(userDetails.getId(), userDetails);
    // }

}
