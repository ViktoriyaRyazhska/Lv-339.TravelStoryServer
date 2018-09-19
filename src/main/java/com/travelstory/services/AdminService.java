package com.travelstory.services;

import com.travelstory.entity.User;

public interface AdminService {

    public void addUser(User user);

    public User getUserById(long id);

    public void editUser(long id);

    public void deleteUser(long id);

    public void blockUser(long id);

    public void banUser(long id);

    public void setAdminStatus(long id);

    public void deleteComment(long id);

    public void deleteTravelStory(long id);
}
