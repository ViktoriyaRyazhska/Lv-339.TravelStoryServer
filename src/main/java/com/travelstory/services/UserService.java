package com.travelstory.services;

import com.travelstory.dto.*;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface UserService {
    public void registrateUser(RegistrationDTO registrationDTO);

    public User uploadProfilePicture(UserPicDTO dto) throws IOException;

    public boolean checkCredentials(LoginDTO loginDTO);

    public User getUserByEmail(String email);

    public User resetProfilePic(long id);

    public UserDTO getUserById(long userId);

    public TokenModel signIn(LoginDTO loginDTO);

    public Page<UserSearchDTO> getUsersByTerm(String term, int page, int size);

    public Page<UserSearchDTO> getFollowers(Long userId, int page, int size);

    public Page<UserSearchDTO> getFollowing(Long userId, int page, int size);

    public User uploadBackgroundPicture(UserPicDTO dto);

    public User updateSettings(UserSettingsDTO dto);
}
