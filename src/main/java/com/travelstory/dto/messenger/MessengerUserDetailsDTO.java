package com.travelstory.dto.messenger;

import com.travelstory.entity.SocialNetworkNick;
import com.travelstory.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessengerUserDetailsDTO extends MessengerUserDTO {
    public String email;
    public User.UserState userState;
    public List<SocialNetworkNick> socialNetworkNick;
}
