package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.MessengerUserDetailsDTO;
import com.travelstory.entity.User;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.SocialNetworkRepository;
import com.travelstory.repositories.UserRepository;
import com.travelstory.utils.modelMapper.ModelMapperDecorator;
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
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with id - " + userId
                                + " is not found in the db during getting it in MessengerUserService",
                        "Dear user, there is no such user. Don't by disappointed and try again =)",
                        MessengerUserService.class));

        return modelMapperDecorator.map(user, MessengerUserDetailsDTO.class);
    }
}
