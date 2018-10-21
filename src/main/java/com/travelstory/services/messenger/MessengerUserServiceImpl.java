package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.MessengerUserDetailsDTO;
import com.travelstory.entity.User;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.SocialNetworkRepository;
import com.travelstory.repositories.UserRepository;
import com.travelstory.utils.modelmapper.ModelMapperDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessengerUserServiceImpl implements MessengerUserService {
    private UserRepository userRepository;
    private ModelMapperDecorator modelMapperDecorator;

    @Autowired
    public MessengerUserServiceImpl(UserRepository userRepository, ModelMapperDecorator modelMapperDecorator,
            SocialNetworkRepository socialNetworkRepository) {
        this.userRepository = userRepository;
        this.modelMapperDecorator = modelMapperDecorator;
    }

    @Override
    public void saveUserDetails(MessengerUserDetailsDTO messengerUserDetailsDTO) {
        User user = modelMapperDecorator.map(messengerUserDetailsDTO, User.class);
        userRepository.save(user);
    }

    @Override
    public MessengerUserDetailsDTO getUserDetails(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id - " + userId
                                + " is not found in the db during getting it in MessengerUserService",
                        ExceptionCode.USER_NOT_FOUND));

        return modelMapperDecorator.map(user, MessengerUserDetailsDTO.class);
    }
}
