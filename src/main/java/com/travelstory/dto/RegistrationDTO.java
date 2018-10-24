package com.travelstory.dto;

import com.travelstory.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private User.Gender gender;
}
