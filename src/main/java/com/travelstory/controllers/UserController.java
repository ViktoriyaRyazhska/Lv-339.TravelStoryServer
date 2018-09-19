package com.travelstory.controllers;

import com.travelstory.dao.UserDAO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.User;
import com.travelstory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserDAO userDao;

    @Autowired
    public UserController(UserService userService, UserDAO userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userDao.findAll();
    }

    @PutMapping("/avatar/{id}")
    User updateAvatar(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) throws IOException {
        return userService.updateAvatarUrl(userId, userDetails);
    }

    @PostMapping("/registrate")
    public void registrateUser(@RequestBody RegistrationDTO registrationDTO) {
        userService.registrateUser(registrationDTO);
    }
}
