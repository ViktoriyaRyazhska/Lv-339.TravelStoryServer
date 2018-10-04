package com.travelstory.dto.converter.messenger;

import com.travelstory.dto.messenger.MessengerUserDetailsDTO;
import com.travelstory.entity.User;

public interface MessengerUserDetailsConverter {
    public MessengerUserDetailsDTO convertToDto(User user);

    public User convertToEntity(MessengerUserDetailsDTO messengerUserDetailsDTO);
}
