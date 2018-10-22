package com.travelstory.dto;

import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private LocalDateTime registrationDate;
    private LocalDate lastUpdateDate;
    private String profilePic;
    private String backgroundPic;
    private Long countOfTravelStories;
    private String location;
    private String hobbies;
    private User.Gender gender;
    private User.UserStatus status;
    private User.UserState state;
    private UserRole role;
}
