package com.travelstory.services;

import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.repositories.CommentRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelstory.dto.ProfileDTO;

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
        boolean isSucceed;
        if (!userRepository.existsByEmail(userProfile.getEmail())) {
            userRepository.save(updateData(userProfile));
            isSucceed = true;
        } else {
            log.error("UserDTO with such email already exist!");
            isSucceed = false;
        }
        return isSucceed;
    }

    @Override
    public boolean editUser(ProfileDTO userProfile) {
        boolean isSucceed;
        if (userRepository.existsByEmailAndPassword(userProfile.getEmail(), userProfile.getPassword())) {
            User user = updateData(userProfile);
            user.setId(userProfile.getId());
            userRepository.save(user);
            isSucceed = true;
        } else {
            log.error("UserDTO with such credentials doesn`t exist!");
            isSucceed = false;
        }
        return isSucceed;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllBy();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void markAsDeleted(long id) {
        user = userRepository.findById(id).get();
        user.setUserStatus(User.UserStatus.DELETED);
        userRepository.save(user);
    }

    @Override
    public void markAsActive(long id) {
        user = userRepository.findById(id).get();
        user.setUserStatus(User.UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void markAsBanned(long id) {
        user = userRepository.findById(id).get();
        user.setUserStatus(User.UserStatus.BANNED);
        userRepository.save(user);
    }

    @Override
    public void setAdminStatus(long id) {
        user = userRepository.findById(id).get();
        user.setUserRole(UserRole.ROLE_ADMIN);
        userRepository.save(user);
    }

    @Override
    public void setUserStatus(long id) {
        user = userRepository.findById(id).get();
        user.setUserRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteTravelStory(long id) {
        travelStoryRepository.deleteById(id);
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
