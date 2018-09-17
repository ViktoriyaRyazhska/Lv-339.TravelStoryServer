package com.travelstory.services;

import com.travelstory.dao.UserDAO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public void registrateUser(RegistrationDTO registrationDTO) {
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
