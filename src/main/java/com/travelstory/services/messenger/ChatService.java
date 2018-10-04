package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.ChatDTO;
import com.travelstory.dto.messenger.ChatDetailsDTO;

import java.util.List;

public interface ChatService {
    public void save(ChatDTO chatDTO);

    public void delete(ChatDTO chatDTO);

    public ChatDetailsDTO get(Long chatId);

    public List<ChatDTO> getAllByUserId(Long userId);
}
