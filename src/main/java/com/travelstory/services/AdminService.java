package com.travelstory.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.travelstory.dto.ProfileDTO;

@Service
public interface AdminService {

    public boolean addUser(ProfileDTO user);

    public boolean editUser(ProfileDTO user);

    public ProfileDTO getUserById(long id);

    public Page<ProfileDTO> getAllUsers(int position, int quantity);

    public Page<ProfileDTO> getAllAdmins(int position, int quantity);

    public void deleteUser(long id);

    public void markAsDeleted(long id);

    public void markAsActive(long id);

    public void markAsBanned(long id);

    public void setAdminStatus(long id);

    public void setUserStatus(long id);

    public void deleteComment(long id);

    public void deleteTravelStory(long id);
}
