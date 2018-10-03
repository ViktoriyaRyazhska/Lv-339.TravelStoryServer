package com.travelstory.dto;

import com.travelstory.entity.Gender;
import com.travelstory.entity.TravelStory;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String profilePic;
    private String backgroundPic;
    private Gender gender;
    private List<Long> usersFollows;
    private List<TravelStory> travelStories;
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
