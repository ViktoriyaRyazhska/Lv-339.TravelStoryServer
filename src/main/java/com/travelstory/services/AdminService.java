package com.travelstory.services;

import com.travelstory.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    public void addUser(User user);

    public User getUserById(long id);

    public void deleteUser(long id);

    public void markAsDeleted(long id);

    public void markAsActive(long id);

    public void markAsBanned(long id);

    public void setAdminStatus(long id);

    public void setUserStatus(long id);

    public void deleteComment(long id);

    public void deleteTravelStory(long id);
}
