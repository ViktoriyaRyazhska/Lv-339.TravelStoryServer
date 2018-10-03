package com.travelstory.services.messenger;

import com.travelstory.dto.messenger.MessengerUserDetailsDTO;

public interface MessengerUserService {
    public void saveUserDetails(MessengerUserDetailsDTO messengerUserDetailsDTO);

    public MessengerUserDetailsDTO getUserDetails(long userId);

}
