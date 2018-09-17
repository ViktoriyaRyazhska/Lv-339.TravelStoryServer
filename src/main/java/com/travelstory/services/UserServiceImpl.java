package com.travelstory.services;

import com.travelstory.dao.UserDAO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import com.travelstory.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

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
}
