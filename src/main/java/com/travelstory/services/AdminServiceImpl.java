package com.travelstory.services;


import com.travelstory.entity.*;
import com.travelstory.repositories.CommentRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addUser(User user) {
        userRepository.save(user);
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
        user.setUserStatus(UserStatus.DELETED);
        userRepository.save(user);
    }

    @Override
    public void markAsActive(long id) {
        user = userRepository.findById(id).get();
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void markAsBanned(long id) {
        user = userRepository.findById(id).get();
        user.setUserStatus(UserStatus.BANNED);
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
}
