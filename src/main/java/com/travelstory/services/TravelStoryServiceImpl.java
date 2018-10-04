package com.travelstory.services;

import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.entity.TravelStory;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TravelStoryServiceImpl implements TravelStoryService {
    @Autowired
    TravelStoryRepository travelStoryRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public TravelStoryDTO addTravelStory(TravelStoryDTO travelStory) {
        TravelStory travelStoryBase = new TravelStory();
        travelStoryBase.setHead(travelStory.getHead());
        travelStoryBase.setDescription(travelStory.getDescription());
        travelStoryBase.setUserOwner(userRepository.findUserById(travelStory.getOwnerId()));
        travelStoryBase.setCreatedDate(LocalDateTime.now());
        travelStoryRepository.saveAndFlush(travelStoryBase);
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
    public TravelStoryDTO editTravelStory(TravelStoryDTO travelStory) {
        TravelStory travelStoryBase = new TravelStory();
        travelStoryBase.setHead(travelStory.getHead());
        travelStoryBase.setDescription(travelStory.getDescription());
        travelStoryBase.setUserOwner(userRepository.findUserById(travelStory.getOwnerId()));
        travelStoryBase.setCreatedDate(LocalDateTime.now());
        travelStoryRepository.saveAndFlush(travelStoryBase);
        return travelStory;
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
