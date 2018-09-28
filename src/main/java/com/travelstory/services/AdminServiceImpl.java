package com.travelstory.services;

import com.travelstory.dao.CommentDAO;
import com.travelstory.dao.TravelStoryDAO;
import com.travelstory.dao.UserDAO;
import com.travelstory.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    User user;

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    TravelStoryDAO travelStoryDAO;

    @Override
    public void addUser(User user) {
        userDAO.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.findById(id).get();
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteById(id);
    }

    @Override
    public void markAsDeleted(long id) {
        user = userDAO.findById(id).get();
        user.setUserStatus(UserStatus.DELETED);
        userDAO.save(user);
    }

    @Override
    public void markAsActive(long id) {
        user = userDAO.findById(id).get();
        user.setUserStatus(UserStatus.ACTIVE);
        userDAO.save(user);
    }

    @Override
    public void markAsBanned(long id) {
        user = userDAO.findById(id).get();
        user.setUserStatus(UserStatus.BANNED);
        userDAO.save(user);
    }

    @Override
    public void setAdminStatus(long id) {
        user = userDAO.findById(id).get();
        user.setUserRole(UserRole.ROLE_ADMIN);
        userDAO.save(user);
    }

    @Override
    public void setUserStatus(long id) {
        user = userDAO.findById(id).get();
        user.setUserRole(UserRole.ROLE_USER);
        userDAO.save(user);
    }

    @Override
    public void deleteComment(long id) {
        commentDAO.deleteById(id);
    }

    @Override
    public void deleteTravelStory(long id) {
        travelStoryDAO.deleteById(id);
    }
}
