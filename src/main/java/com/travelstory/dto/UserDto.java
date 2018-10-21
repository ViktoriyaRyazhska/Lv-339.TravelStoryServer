package com.travelstory.dto;

import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String profilePic;
    private String backgroundPic;
    private Long countOfTravelStories;
    private String location;
    private String hobbies;
    private User.Gender gender;
    private List<Long> usersFollows;
    private List<TravelStory> travelStories;

}
