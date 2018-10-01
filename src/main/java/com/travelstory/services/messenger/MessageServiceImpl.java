package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.MessageDTO;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.entity.messenger.Message;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.messenger.MessageRepository;
import com.travelstory.utils.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    MessageRepository messageRepository;
    ModelMapperUtils modelMapperUtils;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapperUtils modelMapperUtils) {
        this.messageRepository = messageRepository;
        this.modelMapperUtils = modelMapperUtils;
    }

    @Override
    public void save(MessageDTO messageDTO) {
        Message message = modelMapperUtils.map(messageDTO, Message.class);
        messageRepository.save(message);
    }

    @Override
    public void delete(MessageDTO messageDTO) {
        Message message = modelMapperUtils.map(messageDTO, Message.class);

        message.setDeleted(true);
        message.setDeletedAt(LocalDateTime.now());

        messageRepository.save(message);
    }

    @Override
    public MessageDTO get(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Message with id " + messageId + " not found in the db during getting it in messageService",
                        "Dear user, there is not such message", MessageService.class));

        return modelMapperUtils.map(message, MessageDTO.class);
    }

    @Override
    public List<MessageDTO> getNext30Messages(long chatId, int pageNumber) { // max number of messages =)
        Chat chat = new Chat();
        chat.setId(chatId);
        List<Message> messages = messageRepository.findAllByChatOrderByCreatedAt(chat, PageRequest.of(pageNumber, 30));
        return modelMapperUtils.mapAll(messages, MessageDTO.class);
    }
}
