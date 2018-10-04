package com.travelstory.controllers;

import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.dto.UserDTO;
import com.travelstory.dto.UserPicDTO;
import com.travelstory.entity.TokenModel;
import com.travelstory.entity.User;
import com.travelstory.repositories.UserRepository;
import com.travelstory.security.TokenProvider;
import com.travelstory.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

<<<<<<< HEAD
    /*
     * @PutMapping("/updateProfilePic") User uploadProfilePicture(@RequestBody UserDto userDetails) throws IOException {
     * return userService.uploadProfilePicture(userDetails.getId(), userDetails); }
     */
=======
    @PutMapping("/uploadProfilePic")
    User uploadProfilePicture(@RequestBody UserPicDTO dto) throws IOException {
        return userService.uploadProfilePicture(dto);
    }
>>>>>>> 9a55b1c508401e8326915c46835826480f992a3c

    @PostMapping("/registrate")
    public ResponseEntity registrateUser(@RequestBody RegistrationDTO registrationDTO) {
        userService.registrateUser(registrationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")

    public UserDTO getUserById(@PathVariable long id) {

        return userService.getUserById(id);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        TokenModel token = null;
        if (userService.checkCredentials(loginDTO)) {
            token = userService.signIn(loginDTO);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
<<<<<<< HEAD
            // log.error("There is no user with such credentials");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
=======
            log.error("There is no user with such credentials");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
>>>>>>> 9a55b1c508401e8326915c46835826480f992a3c
        }
    }
}
