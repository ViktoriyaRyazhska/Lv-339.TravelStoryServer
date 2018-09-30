package com.travelstory.dto.messenger;

import com.travelstory.entity.Media;
import com.travelstory.entity.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private String messageContent;
    private MessageType messageType;
    private Media media;
    private Long chat_id;
    private MessengerUserDTO user;
}
