package com.travelstory.services;

import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.CommentRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelstory.dto.ProfileDTO;

import java.util.LinkedList;
import java.util.List;
import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    User user;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TravelStoryRepository travelStoryRepository;

    @Override
    public boolean addUser(ProfileDTO userProfile) {
        boolean isSucceed = true;
        if (!userRepository.existsByEmail(userProfile.getEmail())) {
            userRepository.save(updateData(userProfile));
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
            User user = updateData(userProfile);
            user.setId(userProfile.getId());
            userRepository.save(user);
        } else {
            log.error("UserDTO with such credentials doesn`t exist!");
            isSucceed = false;
        }
        return isSucceed;
    }

    @Override
    public List<User> getAllUsers(int position, int quantity) {
        List<User> list = new LinkedList<>();
        for (int i = position; i < position + quantity; i++) {
            list.add(userRepository.findUserById((long) i));
        }
        return list;
    }

    @Override
    public List<User> getAllAdmins(int position, int quantity) {
        List<User> list = new LinkedList<>();
        for (int i = position; i < position + quantity; i++) {
            list.add(userRepository.findUserById((long) i));
        }
        return list;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with such id not found", ExceptionCode.USER_NOT_FOUND));
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

    private User updateData(ProfileDTO userProfile) {
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
}
