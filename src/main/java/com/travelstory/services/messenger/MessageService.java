package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.MessageDTO;

public interface MessageService {
    public void save(MessageDTO messageDTO);

    public void delete(MessageDTO messageDTO);

    public MessageDTO get(Long chatId);

    public void getNext50Messages(int chatId, int pageNumber); // 107 374 182 350 - max number of messages =)
}
