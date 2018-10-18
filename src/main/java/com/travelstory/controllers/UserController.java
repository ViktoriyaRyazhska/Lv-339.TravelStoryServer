package com.travelstory.controllers;

import com.travelstory.dto.*;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;
import com.travelstory.repositories.UserRepository;
import com.travelstory.security.TokenProvider;
import com.travelstory.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
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

    @GetMapping("/users/{term}/{page}/{size}")
    public Page<UserSearchDTO> getUsersByTerm(@PathVariable(value = "term") String term,
            @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        return userService.getUsersByTerm(term, page, size);
    }

    @PutMapping("/uploadProfilePic")
    public User uploadProfilePicture(@RequestBody UserPicDTO dto) throws IOException {
        return userService.uploadProfilePicture(dto);
    }

    @PutMapping("/uploadProfilePic")
    public User uploadBackgroundPicture(@RequestBody UserPicDTO dto) throws IOException {
        return userService.uploadBackgroundPicture(dto);
    }

    @PostMapping("/registrate")
    public ResponseEntity registrateUser(@RequestBody RegistrationDTO registrationDTO) {
        if (userService.getUserByEmail(registrationDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            userService.registrateUser(registrationDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/resetProfilePic")
    public com.travelstory.entity.User resetProfilePic(@RequestBody long id) {
        return userService.resetProfilePic(id);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        TokenModel token = null;
        if (userService.checkCredentials(loginDTO)) {
            token = userService.signIn(loginDTO);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            log.error("There is no user with such credentials");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
