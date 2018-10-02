package com.travelstory.dto.messenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessengerUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePic;
}
