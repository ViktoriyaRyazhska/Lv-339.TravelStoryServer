package com.travelstory.services;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDTO;
import com.travelstory.dto.UserPicDTO;
import com.travelstory.entity.Follow;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.FollowRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import com.travelstory.security.TokenProvider;
import com.travelstory.utils.MediaUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.travelstory.utils.MediaUtils.cleanBase64String;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TravelStoryRepository travelStoryRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void registrateUser(RegistrationDTO registrationDTO) {

        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            log.error("There is a user with such email. Cannot register!");

        } else {
            com.travelstory.entity.User user = new com.travelstory.entity.User();
            user.setEmail(registrationDTO.getEmail());
            user.setFirstName(registrationDTO.getFirstName());
            user.setLastName(registrationDTO.getLastName());
            user.setPassword(registrationDTO.getPassword());
            user.setGender(registrationDTO.getGender());
            user.setUserRole(UserRole.ROLE_USER);
            userRepository.save(user);
        }
    }

    public com.travelstory.entity.User uploadProfilePicture(UserPicDTO dto) throws IOException {
        com.travelstory.entity.User user = userRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("UserPicDTO not found",
                "Dear customer, no such user in the database", UserServiceImpl.class));

        String imgBase64 = dto.getProfilePic();
        String filteredImgBase64 = cleanBase64String(imgBase64);
        String imgUrl = MediaUtils.uploadMediaOnCloud(filteredImgBase64, "profile_pic");
        user.setProfilePic(imgUrl);

        return userRepository.save(user);
    }

    @Override
    public boolean checkCredentials(LoginDTO loginDTO) {
        return userRepository.existsByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
    }

    @Override
    public com.travelstory.entity.User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User resetProfilePic(long id) {
        com.travelstory.entity.User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserPicDTO not found",
                "Dear customer, no such user in the database", UserServiceImpl.class));
        user.setProfilePic
                ("https://res.cloudinary.com/travelstory/image/upload/v1538575861/default/default_avatar.jpg");
        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(long userId) {

        com.travelstory.entity.User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("UserPicDTO not found",
                "Dear customer, no such user in the database", UserServiceImpl.class));
        long countOfTrStories = travelStoryRepository.countTravelStoriesByUserOwner(user);
        List<Follow> follows = followRepository.getFollowByUserId(userId);
        List<Long> followsFiltered = new ArrayList<>();
        for (Follow follow : follows) {
            followsFiltered.add(follow.getId());
        }
        UserDTO map = modelMapper.map(user, UserDTO.class);
        map.setUsersFollows(followsFiltered);
        map.setCountOfTravelStories(countOfTrStories);
        return map;
    }

    @Override
    public TokenModel signIn(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        TokenModel tokenModel = new TokenModel();
        tokenModel.setAccessToken(tokenProvider.createAccessToken(email,
                userRepository.findByEmail(email).getUserRole(), userRepository.findByEmail(email).getId()));
        tokenModel.setRefreshToken(tokenProvider.createRefreshToken(loginDTO.getEmail()));
        return tokenModel;
    }

}
