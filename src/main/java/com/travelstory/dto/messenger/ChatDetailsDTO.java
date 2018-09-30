package com.travelstory.dto.messenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDetailsDTO extends ChatDTO {
    private LocalDateTime createdAt;
    private String description;
    private List<MessengerUserDTO> users;
    private MessengerUserDTO creator;
    private List<MessageDTO> messages;

}
