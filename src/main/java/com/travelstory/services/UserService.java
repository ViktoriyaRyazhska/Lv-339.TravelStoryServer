package com.travelstory.services;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDTO;
import com.travelstory.dto.UserPicDTO;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;

import java.io.IOException;

public interface UserService {
    void registrateUser(RegistrationDTO registrationDTO);

    User uploadProfilePicture(UserPicDTO dto) throws IOException;

    boolean checkCredentials(LoginDTO loginDTO);

    User getUserByEmail(String email);

    UserDTO getUserById(long userId);

    public TokenModel signIn(LoginDTO loginDTO);

}
