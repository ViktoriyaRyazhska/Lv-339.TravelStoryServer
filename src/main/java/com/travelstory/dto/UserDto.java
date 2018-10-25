package com.travelstory.dto;

import com.travelstory.entity.TravelStory;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO extends UserSettingsDTO {
    private Long countOfTravelStories;
    private List<Long> usersFollows;
    private List<TravelStory> travelStories;
}
