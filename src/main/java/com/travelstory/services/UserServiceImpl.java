package com.travelstory.services;

import com.travelstory.dao.UserDAO;
import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDto;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.security.TokenProvider;
import com.travelstory.utils.MediaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public void registrateUser(RegistrationDTO registrationDTO) {
        if (userDAO.existsByEmail(registrationDTO.getEmail())) {
            log.error("There is no user with such email");
        } else {
            User user = new User();
            user.setEmail(registrationDTO.getEmail());
            user.setFirstName(registrationDTO.getFirstName());
            user.setLastName(registrationDTO.getLastName());
            user.setPassword(registrationDTO.getPassword());
            user.setGender(registrationDTO.getGender());
            user.setUserRole(UserRole.ROLE_USER);
            userDAO.save(user);
        }
    }

    public User uploadProfilePicture(Long userId, UserDto userDetails) throws IOException {
        User user = userDAO.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found",
                "Dear customer, no such user in the database", UserServiceImpl.class));

        String imgBase64 = userDetails.getProfilePic();
        String imgUrl = MediaUtils.uploadMediaOnCloud(imgBase64, "profile_pic");
        user.setProfilePic(imgUrl);

        return userDAO.save(user);
    }

    @Override
    public boolean checkCredentials(LoginDTO loginDTO) {
        return userDAO.existsByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public String signIn(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        return tokenProvider.createToken(email, userDAO.findByEmail(email).getUserRole(),
                userDAO.findByEmail(email).getId());
    }

}
