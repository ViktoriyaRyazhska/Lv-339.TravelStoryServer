package com.travelstory.services;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.User;

public interface UserService {
    void registrateUser(RegistrationDTO registrationDTO);

    User updateAvatar(Long userId, User userDetails);

    boolean checkCredentials(LoginDTO loginDTO);

    User getUserByEmail(String email);

    public String signIn(LoginDTO loginDTO);

}
