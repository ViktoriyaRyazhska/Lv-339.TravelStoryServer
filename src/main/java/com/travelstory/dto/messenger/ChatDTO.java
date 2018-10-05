package com.travelstory.dto.messenger;

import com.travelstory.entity.messenger.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    Chat.ChatType chatType;
    private Long id;
    private String chatName;
    private MessageDTO lastMessage;
    private String avatar;
    private MessengerUserDTO interlocutor; // !null if chatType==PRIVATE_MESSAGES
}
