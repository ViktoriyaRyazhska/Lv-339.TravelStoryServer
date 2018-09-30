package com.travelstory.services;

import com.travelstory.dto.chat.MessageDTO;
import com.travelstory.entity.Chat;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    public void saveMessage(MessageDTO messageDTO);

    public void getNext50Messages(int chatId, int pageNumber); //107 374 182 350 - max number of messages =)
}
