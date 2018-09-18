package com.travelstory.services;

import com.travelstory.dao.UserDAO;
import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.sequrity.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public void registrateUser(RegistrationDTO registrationDTO) {
        if (userDAO.existsByEmail(registrationDTO.getEmail())) {

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

    @Override
    public User updateAvatar(Long userId, User userDetails) {
        User user = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setAvatar(userDetails.getAvatar());

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
