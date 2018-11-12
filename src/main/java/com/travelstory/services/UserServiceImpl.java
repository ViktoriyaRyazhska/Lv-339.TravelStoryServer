package com.travelstory.services;

import com.travelstory.dto.*;
import com.travelstory.dto.converter.UserSearchConverter;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.exceptions.validation.IncorrectStringException;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import com.travelstory.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TokenProvider tokenProvider;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserSearchConverter userSearchConverter;

    @Value("${default_profile_pic}")
    private String defaultProfileUserPicture;

    @Value("${default_background_pic}")
    private String defaultBackgroundPicture;

    @Value("${spring.mail.username}")
    private String senderEmail;

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
            user.setProfilePic(defaultProfileUserPicture);
            user.setBackgroundPic(defaultBackgroundPicture);
            userRepository.save(user);
        }
    }

    public User uploadProfilePicture(UserPicDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND));
        user.setProfilePic(dto.getPictureUrl());
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND));
        user.setProfilePic(defaultProfileUserPicture);
        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND));
        long countOfTrStories = travelStoryRepository.countTravelStoriesByUserOwner(user);
        UserDTO map = modelMapper.map(user, UserDTO.class);
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

    public void messageSender(User user, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom(senderEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);

    }

    public void sendNewPassword(String email) {
        User user = userRepository.findByEmail(email);
        String randomPass = RandomStringUtils.randomAlphanumeric(10);
        user.setPassword(randomPass);
        user = userRepository.saveAndFlush(user);
        messageSender(user, "TravelStory password recovery", "Your new password is: " + user.getPassword());
    }

    @Override
    public Page<UserSearchDTO> getUsersByTerm(String term, int page, int size) {
        Page<com.travelstory.entity.User> userPage = null;
        Integer enteredWordsCounter = 0;
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(term);
        while (matcher.find()) {
            enteredWordsCounter++;
        }
        matcher.reset();
        if (enteredWordsCounter == 0) {
            throw new IncorrectStringException("inappropriate name for user search ",
                    ExceptionCode.STRING_NOT_APPROPRIATE);
        }
        if (enteredWordsCounter == 1) {
            String searchingTerm1 = (matcher.find()) ? term.substring(matcher.start(), matcher.end()) : null;
            userPage = userRepository.findByFirstNameIsStartingWithOrLastNameIsStartingWith(searchingTerm1,
                    searchingTerm1, PageRequest.of(page, size));
        }
        if (enteredWordsCounter >= 2) {
            String searchingTerm1 = (matcher.find()) ? term.substring(matcher.start(), matcher.end()) : null;
            String searchingTerm2 = (matcher.find()) ? term.substring(matcher.start(), matcher.end()) : null;
            userPage = userRepository.findByFirstNameIsStartingWithOrLastNameIsStartingWith(searchingTerm1,
                    searchingTerm2, PageRequest.of(page, size));

        }
        return userPage.map(user -> userSearchConverter.convertToDto(user));
    }

    @Override
    public Page<UserSearchDTO> getFollowers(Long userId, int page, int size) {
        if (userRepository.existsById(userId) == false) {
            throw new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND);
        }
        return userRepository.findAllByFollowersId(userId, PageRequest.of(page, size))
                .map(user -> userSearchConverter.convertToDto(user));
    }

    @Override
    public Page<UserSearchDTO> getFollowing(Long userId, int page, int size) {
        if (userRepository.existsById(userId) == false) {
            throw new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND);
        }
        return userRepository.findAllByFollowingId(userId, PageRequest.of(page, size))
                .map(user -> userSearchConverter.convertToDto(user));
    }

    public User uploadBackgroundPicture(UserPicDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_PIC_NOT_FOUND));
        user.setBackgroundPic(dto.getPictureUrl());
        return userRepository.save(user);
    }

    @Override
    public User updateSettings(UserSettingsDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND));
        user = modelMapper.map(dto, User.class);
        return userRepository.save(user);
    }

}
