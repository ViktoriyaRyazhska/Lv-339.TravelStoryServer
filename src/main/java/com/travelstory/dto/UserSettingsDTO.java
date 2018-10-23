package com.travelstory.dto;

import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserSettingsDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String profilePic;
    private String backgroundPic;
    private String location;
    private String bio;
    private User.Gender gender;
    private UserRole userRole;
}
