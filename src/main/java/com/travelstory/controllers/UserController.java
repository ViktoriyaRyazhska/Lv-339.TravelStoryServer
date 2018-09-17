package com.travelstory.controllers;

import com.travelstory.dao.UserDAO;
import com.travelstory.entity.User;
import com.travelstory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/avatar/{id}")
    User updateAvatar(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) {
        return userService.updateAvatar(userId, userDetails);
    }
}
