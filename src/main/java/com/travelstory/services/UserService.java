package com.travelstory.services;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDto;
import com.travelstory.dto.UserPicDto;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;

import java.io.IOException;

public interface UserService {
    void registrateUser(RegistrationDTO registrationDTO);

    User uploadProfilePicture(UserPicDto dto) throws IOException;

    boolean checkCredentials(LoginDTO loginDTO);

    User getUserByEmail(String email);

    UserDto getUserById(long userId);

    public TokenModel signIn(LoginDTO loginDTO);

}
