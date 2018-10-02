package com.travelstory.dto.messenger;

import com.travelstory.entity.messenger.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private Long id;
    private String chatName;
    Chat.ChatType chatType;
    private MessageDTO lastMessage;
    private String avatar;
    private MessengerUserDTO interlocutor; // !null if chatType==PRIVATE_MESSAGES
}
