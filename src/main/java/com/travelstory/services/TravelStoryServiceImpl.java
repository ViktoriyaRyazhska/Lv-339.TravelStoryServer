package com.travelstory.services;

import com.travelstory.dao.TravelStoryDAO;
import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.entity.TravelStory;
import com.travelstory.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelStoryServiceImpl implements TravelStoryService {
    @Autowired
    TravelStoryDAO travelStoryDAO;

    @Override
    public TravelStoryDTO addTravelStory(TravelStoryDTO travelStory) {
        TravelStory travelStory1 = new TravelStory();
        travelStory.setHead(travelStory.getHead());
        travelStory.setDescription(travelStory.getDescription());
        travelStory.setCreatedDate(travelStory.getCreatedDate());
        travelStoryDAO.saveAndFlush(travelStory1);
        return travelStory;
    }

    @Override
    public void deleteTravelStory(long id) {
        travelStoryDAO.deleteById(id);
    }

    @Override
    public TravelStory getByHead(String head) {
        return travelStoryDAO.findByHead(head);
    }

    @Override
    public TravelStory editTravelStory(TravelStory travelStory) {
        return travelStoryDAO.saveAndFlush(travelStory);
    }

    @Override
    public List<TravelStory> getAll() {
        return travelStoryDAO.findAll();
    }

    @Override
    public TravelStory getById(long id) {
        return travelStoryDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Travel story not found",
                "Dear customer,no such user in the database", TravelStoryServiceImpl.class));
    }

    @Override
    public List<TravelStory> getByUserOwner(long id) {
        return travelStoryDAO.findByUserOwnerId(id);
    }
}
