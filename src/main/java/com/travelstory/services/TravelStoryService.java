package com.travelstory.services;

import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.entity.TravelStory;

import java.util.List;

public interface TravelStoryService {
    TravelStoryDTO addTravelStory(TravelStoryDTO travelStory);

    void deleteTravelStory(long id);

    TravelStory getByHead(String head);

    TravelStory editTravelStory(TravelStory travelStory);

    List<TravelStory> getAll();

    TravelStory getById(long id);

    List<TravelStory> getByUserOwner(long id);
}
