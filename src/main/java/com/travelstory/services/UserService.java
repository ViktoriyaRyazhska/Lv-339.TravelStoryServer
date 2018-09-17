package com.travelstory.services;

import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.User;

public interface UserService {
    void registrateUser(RegistrationDTO registrationDTO);

    User updateAvatar(Long userId, User userDetails);
}
