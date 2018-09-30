package com.travelstory.dto.converter.messenger.impl;

import com.travelstory.dto.converter.messenger.ChatConverter;
import com.travelstory.dto.converter.messenger.ChatDetailsConverter;
import com.travelstory.dto.messenger.ChatDetailsDTO;
import com.travelstory.dto.messenger.MessageDTO;
import com.travelstory.dto.messenger.MessengerUserDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.entity.messenger.Message;
import com.travelstory.repositories.UserRepository;
import com.travelstory.utils.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatDetailsConverterImpl implements ChatDetailsConverter {

    private ModelMapperUtils modelMapperUtils;
    private UserRepository userRepository;
    private ChatConverter chatConverter;

    @Autowired
    public ChatDetailsConverterImpl(ModelMapperUtils modelMapperUtils, UserRepository userRepository,
            ChatConverter chatConverter) {
        this.modelMapperUtils = modelMapperUtils;
        this.userRepository = userRepository;
        this.chatConverter = chatConverter;
    }

    @Override
    public ChatDetailsDTO convertToDto(Chat chat) {
        ChatDetailsDTO chatDetailsDTO = modelMapperUtils.map(chat, ChatDetailsDTO.class);
        ;
        List<MessengerUserDTO> messengerUserDTOs = modelMapperUtils.mapAll(chat.getConnectedUsers(),
                MessengerUserDTO.class);
        List<MessageDTO> messageDTOs = modelMapperUtils.mapAll(chat.getMessages(), MessageDTO.class);
        MessengerUserDTO creator = modelMapperUtils.map(chat.getCreator(), MessengerUserDTO.class);

        chatDetailsDTO.setCreator(creator);
        chatDetailsDTO.setMessages(messageDTOs);
        chatDetailsDTO.setUsers(messengerUserDTOs);
        return chatDetailsDTO;
    }

    @Override
    public Chat convertToEntity(ChatDetailsDTO chatDetailsDTO) {
        Chat chat = chatConverter.convertToEntity(chatDetailsDTO);

        List<User> membersToSearch = modelMapperUtils.mapAll(chatDetailsDTO.getUsers(), User.class);
        User creator = modelMapperUtils.map(chatDetailsDTO.getCreator(), User.class);
        List<Message> messages = modelMapperUtils.mapAll(chatDetailsDTO.getMessages(), Message.class);

        chat.setMessages(messages);
        chat.setCreator(creator);

        chat.setConnectedUsers(membersToSearch);

        return chat;
    }
}
