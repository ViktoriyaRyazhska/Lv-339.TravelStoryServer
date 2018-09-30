package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.MessageDTO;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public void save(MessageDTO messageDTO) {

    }

    @Override
    public void delete(MessageDTO messageDTO) {

    }

    @Override
    public MessageDTO get(Long chatId) {
        return null;
    }

    @Override
    public void getNext50Messages(int chatId, int pageNumber) {

    }
}
