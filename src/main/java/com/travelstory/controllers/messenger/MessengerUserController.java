package com.travelstory.controllers.messenger;

import com.travelstory.dto.messenger.MessengerUserDetailsDTO;
import com.travelstory.services.messenger.MessengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messenger")
public class MessengerUserController {

    MessengerUserService messengerUserService;

    @Autowired
    public MessengerUserController(MessengerUserService messengerUserService) {
        this.messengerUserService = messengerUserService;
    }

    @GetMapping("/user/{userId}")
    public MessengerUserDetailsDTO getFullInfoUser(@PathVariable int userId) {
        return messengerUserService.getUserDetails(userId);
    }
}
