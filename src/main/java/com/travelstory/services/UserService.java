package com.travelstory.services;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.User;

import java.io.IOException;

public interface UserService {
    void registrateUser(RegistrationDTO registrationDTO);

    User updateAvatarUrl(Long userId, User userDetails) throws IOException;

    boolean checkCredentials(LoginDTO loginDTO);

    User getUserByEmail(String email);

    public String signIn(LoginDTO loginDTO);

}
