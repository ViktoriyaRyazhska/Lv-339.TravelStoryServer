package com.travelstory.dto.converter.messenger;

import com.travelstory.dto.messenger.ChatDTO;
import com.travelstory.dto.messenger.MessageDTO;
import com.travelstory.dto.messenger.MessengerUserDTO;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.repositories.messenger.MessageRepository;
import com.travelstory.utils.ModelMapperDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatConverterImpl implements ChatConverter {

    private ModelMapperDecorator modelMapperDecorator;
    private MessageRepository messageRepository;

    @Autowired
    public ChatConverterImpl(ModelMapperDecorator modelMapperDecorator, MessageRepository messageRepository) {
        this.modelMapperDecorator = modelMapperDecorator;
        this.messageRepository = messageRepository;
    }

    @Override
    public ChatDTO convertToDto(Chat chat, Long currUserId) {
        ChatDTO chatDTO = modelMapperDecorator.map(chat, ChatDTO.class);

        chatDTO.setLastMessage(
                modelMapperDecorator.map(messageRepository.findTopByChatOrderByCreatedAtDesc(chat), MessageDTO.class));

        setInterlocutor(chat, chatDTO, currUserId);
        setChatName(chatDTO);

        return chatDTO;
    }

    @Override
    public Chat convertToEntity(ChatDTO chatDTO) {
        Chat chat = modelMapperDecorator.map(chatDTO, Chat.class);

        // User creator = userRepository.findById(creatorToFind.getId())
        // .orElseThrow(() -> new EntityNotFoundException("User isn't found in the DB." +
        // " Exception occurred while converting from ChatDTO to Chat", "sdf", ChatConverter.class));

        return chat;
    }

    @Override
    public List<ChatDTO> convertToDtos(List<Chat> chats, Long currUserId) {
        List<ChatDTO> chatDTOS = new ArrayList<>();

        for (Chat chat : chats) {
            chatDTOS.add(convertToDto(chat, currUserId));
        }

        return chatDTOS;
    }

    @Override
    public List<Chat> convertToEntities(List<ChatDTO> chatDTOs) {
        List<Chat> chats = new ArrayList<>();

        for (ChatDTO chatDTO : chatDTOs) {
            chats.add(convertToEntity(chatDTO));
        }

        return chats;
    }

    public void setInterlocutor(Chat chat, ChatDTO chatDTO, Long currUserId) {
        if (chat.getChatType() == Chat.ChatType.PRIVATE_MESSAGES) {
            if (chat.getConnectedUsers().get(0).getId().equals(currUserId)) {
                chatDTO.setInterlocutor(
                        modelMapperDecorator.map(chat.getConnectedUsers().get(1), MessengerUserDTO.class));
            } else {
                chatDTO.setInterlocutor(
                        modelMapperDecorator.map(chat.getConnectedUsers().get(0), MessengerUserDTO.class));
            }
        }
    }

    public void setChatName(ChatDTO chatDTO) {
        if (chatDTO.getChatName() == null) {
            chatDTO.setChatName(
                    chatDTO.getInterlocutor().getFirstName() + " " + chatDTO.getInterlocutor().getLastName());
        }
    }

}
