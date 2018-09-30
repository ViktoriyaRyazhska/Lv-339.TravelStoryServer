package com.travelstory.services.messenger;

import com.travelstory.dto.converter.messenger.ChatConverter;
import com.travelstory.dto.converter.messenger.ChatDetailsConverter;
import com.travelstory.dto.messenger.ChatDTO;
import com.travelstory.dto.messenger.ChatDetailsDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.ChatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void delete(ChatDTO chatDTO) {
        chatRepository.delete(chatConverter.convertToEntity(chatDTO));
    }

    @Override
    public ChatDetailsDTO get(Long chatId) {
        Chat chat = Optional.of(chatRepository.getOne(chatId))
                .orElseThrow(() -> new EntityNotFoundException(
                        "Chat entity is not found during getting it in chatService class",
                        "Dear user, there is no such chat.", ChatServiceImpl.class));
        log.debug("Getting chat by id");
        return chatDetailsConverter.convertToDto(chat);
    }

    @Override
    public List<ChatDTO> getAllByUserId(Long userId) {
        User user = new User();
        user.setId(userId);

        log.debug("Getting list of chats for user with id - " + userId);
        List<Chat> chats = chatRepository.findByConnectedUsers(user);

        return chatConverter.convertToDtos(chats);
    }

}
