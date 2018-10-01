package com.travelstory.dto.converter.messenger;

import com.travelstory.dto.messenger.MessengerUserDetailsDTO;
import com.travelstory.dto.messenger.SocialNetworksDTO;
import com.travelstory.entity.User;
import com.travelstory.repositories.UserRepository;
import com.travelstory.utils.ModelMapperUtils;

public class MessengerUserDetailsConverterImpl implements MessengerUserDetailsConverter {
    private ModelMapperUtils modelMapperUtils;
    private UserRepository userRepository;

    @Override
    public MessengerUserDetailsDTO convertToDto(User user) {
        MessengerUserDetailsDTO messengerUserDetailsDTO = modelMapperUtils.map(user, MessengerUserDetailsDTO.class);

        for (int i = 0; i < user.getSocialNetworks().size(); ++i) {
            SocialNetworksDTO socialNetworksDTO;
        }

        return messengerUserDetailsDTO;
    }

    @Override
    public User convertToEntity(MessengerUserDetailsDTO messengerUserDetailsDTO) {
        return modelMapperUtils.map(messengerUserDetailsDTO, User.class);
    }
}
