package com.travelstory.services;

import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelStoryServiceImpl implements TravelStoryService {
    @Autowired
    TravelStoryRepository travelStoryRepository;

    @Override
    public TravelStory addTravelStory(TravelStory travelStory) {
        TravelStory travelStory1 = travelStoryRepository.saveAndFlush(travelStory);
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
        return null;
        // return travelStoryDAO.findById(id).orElseThrow(() ->
        // new EntityNotFoundException(String.format("TravelStory with id '%s' not found", id,"sdknfiosdv")));
    }

    @Override
    public List<TravelStory> getByUserOwner(User userOwner) {
        return (List<TravelStory>) travelStoryRepository.findByUserOwner(userOwner);
    }
}
