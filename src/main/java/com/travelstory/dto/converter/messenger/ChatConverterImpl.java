package com.travelstory.dto.converter.messenger;

import com.travelstory.dto.messenger.ChatDTO;
import com.travelstory.dto.messenger.MessageDTO;
import com.travelstory.dto.messenger.MessengerUserDTO;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.entity.messenger.Message;
import com.travelstory.repositories.messenger.MessageRepository;
import com.travelstory.utils.modelmapper.ModelMapperDecorator;
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

        Message lastMessage = messageRepository.findTopByChatOrderByCreatedAtDesc(chat);
        if (lastMessage == null) {
            lastMessage = new Message();
            lastMessage.setId(Long.MAX_VALUE);
            lastMessage.setMessageContent("");
        }
        chatDTO.setLastMessage(modelMapperDecorator.map(lastMessage, MessageDTO.class));

        if (chat.getChatType() == Chat.ChatType.PRIVATE_MESSAGES) {
            setInterlocutor(chat, chatDTO, currUserId);
        }
        setChatName(chatDTO);

        return chatDTO;
    }

    @Override
    public Chat convertToEntity(ChatDTO chatDTO) {
        return modelMapperDecorator.map(chatDTO, Chat.class);
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
        if (chat.getConnectedUsers().get(0).getId().equals(currUserId)) {
            chatDTO.setInterlocutor(modelMapperDecorator.map(chat.getConnectedUsers().get(1), MessengerUserDTO.class));
        } else {
            chatDTO.setInterlocutor(modelMapperDecorator.map(chat.getConnectedUsers().get(0), MessengerUserDTO.class));
        }
    }

    public void setChatName(ChatDTO chatDTO) {
        if (chatDTO.getChatName() == null) {
            chatDTO.setChatName(
                    chatDTO.getInterlocutor().getFirstName() + " " + chatDTO.getInterlocutor().getLastName());
        }
    }

}
