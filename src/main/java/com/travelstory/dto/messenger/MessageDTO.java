package com.travelstory.dto.messenger;

import com.travelstory.entity.Media;
import com.travelstory.entity.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private String messageContent;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
    private MessageType messageType;
    private Media media;
    private MessengerUserDTO user;
}
