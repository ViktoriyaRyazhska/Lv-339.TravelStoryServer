package com.travelstory.services;

import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;

import java.util.List;

public interface TravelStoryService {
    TravelStory addTravelStory(TravelStory travelStory);

    void deleteTravelStory(long id);

    TravelStory getByHead(String head);

    TravelStory editTravelStory(TravelStory travelStory);

    List<TravelStory> getAll();

    TravelStory getById(long id);
    List<TravelStory> getByUserOwner(User userOwner);
}
