package com.travelstory.services;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDTO;
import com.travelstory.dto.UserPicDTO;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;

import java.io.IOException;

public interface UserService {
    public void registrateUser(RegistrationDTO registrationDTO);

    public User uploadProfilePicture(UserPicDTO dto) throws IOException;

    public boolean checkCredentials(LoginDTO loginDTO);

    public User getUserByEmail(String email);

    public User resetProfilePic(long id);

    public UserDTO getUserById(long userId);

    public TokenModel signIn(LoginDTO loginDTO);

}
