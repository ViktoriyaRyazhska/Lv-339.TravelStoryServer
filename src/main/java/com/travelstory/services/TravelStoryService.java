package com.travelstory.services;

import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.entity.TravelStory;

import java.util.List;

public interface TravelStoryService {
    TravelStoryDTO addTravelStory(TravelStoryDTO travelStory);

    void deleteTravelStory(long id);

    TravelStory getByHead(String head);

    TravelStoryDTO editTravelStory(TravelStoryDTO travelStory, long id);

    List<TravelStory> getAll();

    TravelStory getById(long id);

    List<TravelStoryDTO> getByUserOwner(long id);

}
