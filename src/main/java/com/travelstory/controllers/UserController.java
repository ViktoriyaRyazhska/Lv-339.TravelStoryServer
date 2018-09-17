package com.travelstory.controllers;

import com.travelstory.dao.UserDAO;
import com.travelstory.entity.User;
import com.travelstory.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping("/avatar/{id}")
    User updateAvatar(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {
        User user = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setAvatar(userDetails.getAvatar());

        return userDAO.save(user);
    }
}
