package com.travelstory.dto.converter.messenger;

import com.travelstory.dto.messenger.ChatDetailsDTO;
import com.travelstory.dto.messenger.MessengerUserDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.repositories.UserRepository;
import com.travelstory.utils.ModelMapperDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatDetailsConverterImpl implements ChatDetailsConverter {

    private ModelMapperDecorator modelMapperDecorator;
    private UserRepository userRepository;
    private ChatConverter chatConverter;

    @Autowired
    public ChatDetailsConverterImpl(ModelMapperDecorator modelMapperDecorator, UserRepository userRepository,
            ChatConverter chatConverter) {
        this.modelMapperDecorator = modelMapperDecorator;
        this.userRepository = userRepository;
        this.chatConverter = chatConverter;
    }

    @Override
    public ChatDetailsDTO convertToDto(Chat chat) {
        ChatDetailsDTO chatDetailsDTO = modelMapperDecorator.map(chat, ChatDetailsDTO.class);

        List<MessengerUserDTO> messengerUserDTOs = modelMapperDecorator.mapAll(chat.getConnectedUsers(),
                MessengerUserDTO.class);
        MessengerUserDTO creator = modelMapperDecorator.map(chat.getCreator(), MessengerUserDTO.class);

        // set interlocutor
        if (chat.getChatType() == Chat.ChatType.PRIVATE_MESSAGES) {
            if (chat.getConnectedUsers().get(0).getId().equals(chat.getCreator().getId())) {
                chatDetailsDTO.setInterlocutor(
                        modelMapperDecorator.map(chat.getConnectedUsers().get(1), MessengerUserDTO.class));
            } else {
                chatDetailsDTO.setInterlocutor(
                        modelMapperDecorator.map(chat.getConnectedUsers().get(0), MessengerUserDTO.class));
            }
        }

        chatDetailsDTO.setCreator(creator);
        chatDetailsDTO.setUsers(messengerUserDTOs);
        return chatDetailsDTO;
    }

    @Override
    public Chat convertToEntity(ChatDetailsDTO chatDetailsDTO) {
        Chat chat = chatConverter.convertToEntity(chatDetailsDTO);

        List<User> membersToSearch = modelMapperDecorator.mapAll(chatDetailsDTO.getUsers(), User.class);
        User creator = modelMapperDecorator.map(chatDetailsDTO.getCreator(), User.class);

        chat.setCreator(creator);

        chat.setConnectedUsers(membersToSearch);

        return chat;
    }
}