package com.travelstory.services;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDto;
import com.travelstory.entity.User;

import java.io.IOException;

public interface UserService {
    void registrateUser(RegistrationDTO registrationDTO);

    User uploadProfilePicture(Long userId, UserDto userDetails) throws IOException;

    boolean checkCredentials(LoginDTO loginDTO);

    User getUserByEmail(String email);

    User getUserById(long userId);

    public String signIn(LoginDTO loginDTO);

}
