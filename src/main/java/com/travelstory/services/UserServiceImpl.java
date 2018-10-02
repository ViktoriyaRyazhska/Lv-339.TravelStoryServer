package com.travelstory.services;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDto;
import com.travelstory.dto.UserPicDto;
import com.travelstory.entity.Follow;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.FollowRepository;
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
    FollowRepository followRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void registrateUser(RegistrationDTO registrationDTO) {

        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            log.error("There is no user with such email");

        } else {
            User user = new User();
            user.setEmail(registrationDTO.getEmail());
            user.setFirstName(registrationDTO.getFirstName());
            user.setLastName(registrationDTO.getLastName());
            user.setPassword(registrationDTO.getPassword());
            user.setGender(registrationDTO.getGender());
            user.setUserRole(UserRole.ROLE_USER);
            userRepository.save(user);
        }
    }

    public User uploadProfilePicture(UserPicDto dto) throws IOException {
        User user = userRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("User not found",
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
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto getUserById(long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found",
                "Dear customer, no such user in the database", UserServiceImpl.class));

        List<Follow> follows = followRepository.getFollowByUserId(userId);
        List<Long> followsFiltered = new ArrayList<>();
        for (Follow follow : follows) {
            followsFiltered.add(follow.getId());
        }
        UserDto map = modelMapper.map(user, UserDto.class);
        map.setUsersFollows(followsFiltered);
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
