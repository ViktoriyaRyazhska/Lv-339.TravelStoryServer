package com.travelstory.services;

import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.entity.TravelStory;
import com.travelstory.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelStoryServiceImpl implements TravelStoryService {
    @Autowired
    TravelStoryRepository travelStoryRepository;

    @Override
    public TravelStoryDTO addTravelStory(TravelStoryDTO travelStory) {
        TravelStory travelStory1 = new TravelStory();
        travelStory.setHead(travelStory.getHead());
        travelStory.setDescription(travelStory.getDescription());
        travelStory.setCreatedDate(travelStory.getCreatedDate());
        travelStoryRepository.saveAndFlush(travelStory1);
        return travelStory;
    }

    @Override
    public void deleteTravelStory(long id) {
        travelStoryRepository.deleteById(id);
    }

    @Override
    public TravelStory getByHead(String head) {
        return travelStoryRepository.findByHead(head);
    }

    @Override
    public TravelStory editTravelStory(TravelStory travelStory) {
        return travelStoryRepository.saveAndFlush(travelStory);
    }

    @Override
    public List<TravelStory> getAll() {
        return travelStoryRepository.findAll();
    }

    @Override
    public TravelStory getById(long id) {
        return travelStoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Travel story not found",
                        "Dear customer,no such user in the database", TravelStoryServiceImpl.class));
    }

    @Override
    public List<TravelStory> getByUserOwner(long id) {
        return travelStoryRepository.findByUserOwnerId(id);
    }
}
