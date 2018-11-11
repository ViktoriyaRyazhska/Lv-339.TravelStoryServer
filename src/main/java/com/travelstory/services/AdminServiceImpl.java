package com.travelstory.services;

import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.CommentRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.travelstory.dto.ProfileDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User user;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TravelStoryRepository travelStoryRepository;

    @Override
    public boolean addUser(ProfileDTO userProfile) {
        boolean isSucceed = true;
        if (!userRepository.existsByEmail(userProfile.getEmail())) {
            userRepository.save(convertProfileDtoToUser(userProfile));
        } else {
            log.error("UserDTO with such email already exist!");
            isSucceed = false;
        }
        return isSucceed;
    }

    @Override
    public boolean editUser(ProfileDTO userProfile) {
        boolean isSucceed = true;
        if (userRepository.existsByEmailAndPassword(userProfile.getEmail(), userProfile.getPassword())) {
            User user = convertProfileDtoToUser(userProfile);
            user.setId(userProfile.getId());
            userRepository.save(user);
        } else {
            log.error("UserDTO with such credentials doesn`t exist!");
            isSucceed = false;
        }
        return isSucceed;
    }

    @Override
    public List<ProfileDTO> getAllUsers(int pageNumber, int quantity) {
        Page<ProfileDTO> page = userRepository.findAll(PageRequest.of(pageNumber, quantity))
                .map(user -> convertUserToProfileDTO(user));
        return page.getContent();
    }

    @Override
    public List<ProfileDTO> getAllAdmins(int pageNumber, int quantity) {
        Page<ProfileDTO> page = userRepository
                .findUsersByUserRoleEquals(UserRole.ROLE_ADMIN, PageRequest.of(pageNumber, quantity))
                .map(user -> convertUserToProfileDTO(user));
        return page.getContent();
    }

    @Override
    public ProfileDTO getUserById(long id) {
        if (userRepository.existsById(id)) {
            return convertUserToProfileDTO(userRepository.findUserById(id));
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    @Override
    public void deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    @Override
    public void markAsDeleted(long id) {
        if (userRepository.existsById(id)) {
            user = userRepository.findById(id).get();
            user.setUserStatus(User.UserStatus.DELETED);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    @Override
    public void markAsActive(long id) {
        if (userRepository.existsById(id)) {
            user = userRepository.findById(id).get();
            user.setUserStatus(User.UserStatus.ACTIVE);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    @Override
    public void markAsBanned(long id) {
        if (userRepository.existsById(id)) {
            user = userRepository.findById(id).get();
            user.setUserStatus(User.UserStatus.BANNED);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    @Override
    public void setAdminStatus(long id) {
        if (userRepository.existsById(id)) {
            user = userRepository.findById(id).get();
            user.setUserRole(UserRole.ROLE_ADMIN);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    @Override
    public void setUserStatus(long id) {
        if (userRepository.existsById(id)) {
            user = userRepository.findById(id).get();
            user.setUserRole(UserRole.ROLE_USER);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    @Override
    public void deleteComment(long id) {
        if (userRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    @Override
    public void deleteTravelStory(long id) {
        if (userRepository.existsById(id)) {
            travelStoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND);
        }
    }

    private User convertProfileDtoToUser(ProfileDTO userProfile) {
        User user = new User();
        user.setEmail(userProfile.getEmail());
        user.setFirstName(userProfile.getFirstName());
        user.setLastName(userProfile.getLastName());
        user.setPassword(userProfile.getPassword());
        user.setGender(userProfile.getGender());
        user.setUserRole(userProfile.getRole());
        user.setUserState(userProfile.getState());
        user.setUserStatus(userProfile.getStatus());
        user.setDateOfBirth(userProfile.getDateOfBirth());
        user.setRegistrationDate(userProfile.getRegistrationDate());
        user.setLastUpdateDate(LocalDateTime.now());
        return user;
    }

    private ProfileDTO convertUserToProfileDTO(User user) {
        ProfileDTO userProfile = new ProfileDTO();
        userProfile.setId(user.getId());
        userProfile.setProfilePic(user.getProfilePic());
        userProfile.setBackgroundPic(user.getBackgroundPic());
        userProfile.setHobbies(user.getBio());
        userProfile.setCountOfTravelStories((long) user.getTravelStories().size());
        userProfile.setEmail(user.getEmail());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setPassword(user.getPassword());
        userProfile.setGender(user.getGender());
        userProfile.setLocation(user.getLocation());
        userProfile.setRole(user.getUserRole());
        userProfile.setState(user.getUserState());
        userProfile.setStatus(user.getUserStatus());
        userProfile.setDateOfBirth(user.getDateOfBirth());
        userProfile.setRegistrationDate(user.getRegistrationDate());
        userProfile.setLastUpdateDate(LocalDate.from(LocalDateTime.now()));
        return userProfile;
    }
}
