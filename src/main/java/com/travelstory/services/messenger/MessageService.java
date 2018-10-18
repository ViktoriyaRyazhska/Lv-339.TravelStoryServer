package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.MessageDTO;

import java.util.List;

public interface MessageService {
    public long save(MessageDTO messageDTO, Long chatId);

    public void delete(MessageDTO messageDTO);

    public MessageDTO get(Long chatId);

    public List<MessageDTO> getNext30Messages(long chatId, int pageNumber);
}
