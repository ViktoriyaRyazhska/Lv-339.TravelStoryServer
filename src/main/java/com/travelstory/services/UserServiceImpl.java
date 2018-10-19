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
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.travelstory.utils.MediaUtils.cleanBase64String;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    FollowRepository followRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TravelStoryRepository travelStoryRepository;

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void registrateUser(RegistrationDTO registrationDTO) {

        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            log.error("There is a user with such email. Cannot register!");

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

    public User uploadProfilePicture(UserPicDTO dto) throws IOException {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("UserPicDTO not found",
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
    public User resetProfilePic(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserPicDTO not found",
                "Dear customer, no such user in the database", UserServiceImpl.class));
        user.setProfilePic(
                "https://res.cloudinary.com/travelstory/image/upload/v1538575861/default/default_avatar.jpg");
        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("UserPicDTO not found",
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

    public void sendNewPassword(String email) {
        User user = userRepository.findByEmail(email);
        String randomPass = RandomStringUtils.randomAlphanumeric(10);
        user.setPassword(randomPass);
        user = userRepository.saveAndFlush(user);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom("kiiko.dmytro@gmail.com");
        simpleMailMessage.setSubject("Password recovery");
        simpleMailMessage.setText("Your new password is: " + user.getPassword());
        javaMailSender.send(simpleMailMessage);
    }
}
