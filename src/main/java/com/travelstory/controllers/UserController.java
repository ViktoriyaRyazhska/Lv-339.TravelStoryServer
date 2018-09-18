package com.travelstory.controllers;

import com.travelstory.dao.UserDAO;
import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;
import com.travelstory.sequrity.TokenProvider;
import com.travelstory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserDAO userDao;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserController(UserService userService, UserDAO userDao, TokenProvider tokenProvider) {
        this.userService = userService;
        this.userDao = userDao;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userDao.findAll();
    }

    @PostMapping("/avatar/{id}")
    User updateAvatar(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {
        return userService.updateAvatar(userId, userDetails);
    }

    @PostMapping("/registrate")
    public void registrateUser(@RequestBody RegistrationDTO registrationDTO) {
        userService.registrateUser(registrationDTO);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        String token = null;
        if (userService.checkCredentials(loginDTO)) {
            token = userService.signIn(loginDTO);
        } else {

        }

        return ResponseEntity.ok(new TokenModel(token));
    }
}
