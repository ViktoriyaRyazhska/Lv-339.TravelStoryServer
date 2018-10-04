package com.travelstory.dto.converter.messenger;

import com.travelstory.dto.messenger.ChatDetailsDTO;
import com.travelstory.entity.messenger.Chat;

public interface ChatDetailsConverter {

    public ChatDetailsDTO convertToDto(Chat chat, Long currUserId);

    public Chat convertToEntity(ChatDetailsDTO chatDTO);
}
