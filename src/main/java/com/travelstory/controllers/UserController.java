package com.travelstory.controllers;

import com.travelstory.repositories.UserRepository;
import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDto;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;
import com.travelstory.security.TokenProvider;
import com.travelstory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, TokenProvider tokenProvider) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/updateProfilePic")
    User uploadProfilePicture(@RequestBody UserDto userDetails) throws IOException {
        return userService.uploadProfilePicture(userDetails.getId(), userDetails);
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
