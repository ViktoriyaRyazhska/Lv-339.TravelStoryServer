package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.MessengerUserDetailsDTO;
import com.travelstory.entity.User;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.SocialNetworkRepository;
import com.travelstory.repositories.UserRepository;
import com.travelstory.utils.ModelMapperDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessengerUserServiceImpl implements MessengerUserService {
    private UserRepository userRepository;
    private ModelMapperDecorator modelMapperDecorator;

    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    @Autowired
    public MessengerUserServiceImpl(UserRepository userRepository, ModelMapperDecorator modelMapperDecorator) {
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
        // List<SocialNetwork> socialNetworks = socialNetworkRepository.getAllByUser(user);
        MessengerUserDetailsDTO messengerUserDetailsDTO = modelMapperDecorator.map(user, MessengerUserDetailsDTO.class);
        //
        // // messengerUserDetailsDTO.setSocialNetworks(modelMapperUtils.mapAll(socialNetworks,
        // SocialNetworksDTO.class));
        //
        // for (int i = 0; i < socialNetworks.size(); ++i) {
        // SocialNetworksDTO tmp = modelMapperUtils.map(socialNetworks.get(i), SocialNetworksDTO.class);
        // messengerUserDetailsDTO.getSocialNetworks().set(i, tmp);
        // }

        return messengerUserDetailsDTO;
    }
}
