package com.travelstory.dto.converter.messenger;

import com.travelstory.dto.messenger.ChatDetailsDTO;
import com.travelstory.dto.messenger.MessengerUserDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.utils.modelmapper.ModelMapperDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatDetailsConverterImpl implements ChatDetailsConverter {

    private ModelMapperDecorator modelMapperDecorator;
    private ChatConverter chatConverter;

    @Autowired
    public ChatDetailsConverterImpl(ModelMapperDecorator modelMapperDecorator, ChatConverter chatConverter) {
        this.modelMapperDecorator = modelMapperDecorator;
        this.chatConverter = chatConverter;
    }

    @Override
    public ChatDetailsDTO convertToDto(Chat chat, Long currUserId) {
        ChatDetailsDTO chatDetailsDTO = modelMapperDecorator.map(chat, ChatDetailsDTO.class);

        List<MessengerUserDTO> messengerUserDTOs = modelMapperDecorator.mapAll(chat.getConnectedUsers(),
                MessengerUserDTO.class);
        MessengerUserDTO creator = modelMapperDecorator.map(chat.getCreator(), MessengerUserDTO.class);

        if (chatDetailsDTO.getChatType() == Chat.ChatType.PRIVATE_MESSAGES) {
            chatConverter.setInterlocutor(chat, chatDetailsDTO, currUserId);
        }
        chatConverter.setChatName(chatDetailsDTO);

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
