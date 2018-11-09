package com.travelstory.services;

import org.springframework.stereotype.Service;
import com.travelstory.dto.ProfileDTO;
import java.util.List;

@Service
public interface AdminService {

    public boolean addUser(ProfileDTO user);

    public boolean editUser(ProfileDTO user);

    public ProfileDTO getUserById(long id);

    public List<ProfileDTO> getAllUsers(int position, int quantity);

    public List<ProfileDTO> getAllAdmins(int position, int quantity);

    public void deleteUser(long id);

    public void markAsDeleted(long id);

    public void markAsActive(long id);

    public void markAsBanned(long id);

    public void setAdminStatus(long id);

    public void setUserStatus(long id);

    public void deleteComment(long id);

    public void deleteTravelStory(long id);
}
