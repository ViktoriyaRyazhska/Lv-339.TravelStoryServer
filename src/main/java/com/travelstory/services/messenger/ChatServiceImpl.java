package com.travelstory.services.messenger;

import com.travelstory.dto.converter.messenger.ChatConverter;
import com.travelstory.dto.converter.messenger.ChatDetailsConverter;
import com.travelstory.dto.messenger.ChatDTO;
import com.travelstory.dto.messenger.ChatDetailsDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.messenger.ChatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;
    private ChatDetailsConverter chatDetailsConverter;
    private ChatConverter chatConverter;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, ChatDetailsConverter chatDetailsConverter,
            ChatConverter chatConverter) {
        this.chatRepository = chatRepository;
        this.chatDetailsConverter = chatDetailsConverter;
        this.chatConverter = chatConverter;
    }

    @Override
    public void save(ChatDTO chatDTO) {
        chatRepository.save(chatConverter.convertToEntity(chatDTO));
    }

    @Transactional
    @Override
    public void delete(ChatDTO chatDTO) {
        Chat chat = chatConverter.convertToEntity(chatDTO);

        chat.setDeleted(true);
        chat.setDeletedAt(LocalDateTime.now());

        chatRepository.save(chat);
    }

    @Override
    public ChatDetailsDTO get(Long chatId) {

        Chat chat = Optional.of(chatRepository.getOne(chatId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Chat entity is not found during getting it in chatService class",
                        ExceptionCode.CHAT_NOT_FOUND));
        log.debug("Getting chat by id");
        return chatDetailsConverter.convertToDto(chat, chatId);
    }

    @Override
    public List<ChatDTO> getAllByUserId(Long userId) {
        User user = new User();
        user.setId(userId);

        log.debug("Getting list of chats for user with id - " + userId);
        List<Chat> chats = chatRepository.findByConnectedUsers(user);

        return chatConverter.convertToDtos(chats, userId);
    }

}
