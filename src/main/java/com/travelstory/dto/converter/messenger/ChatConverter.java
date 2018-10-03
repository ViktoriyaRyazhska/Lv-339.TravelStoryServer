package com.travelstory.dto.converter.messenger;

import com.travelstory.dto.messenger.ChatDTO;
import com.travelstory.entity.messenger.Chat;

import java.util.List;

public interface ChatConverter {

    public ChatDTO convertToDto(Chat chat);

    public Chat convertToEntity(ChatDTO chatDTO);

    public List<ChatDTO> convertToDtos(List<Chat> chat);

    public List<Chat> convertToEntities(List<ChatDTO> chatDTO);
}
