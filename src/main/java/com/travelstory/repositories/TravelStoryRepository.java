package com.travelstory.repositories;

import com.travelstory.entity.TravelStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.travelstory.entity.User;
import com.travelstory.entity.TravelStoryStatus;

import java.util.List;

@Repository
public interface TravelStoryRepository extends JpaRepository<TravelStory, Long> {

    TravelStory findByHead(String head);

    public List<TravelStory> findByUserOwnerId(long id);

    public TravelStory findByUserOwner(User userOwner);

    public TravelStory findTravelStoryById(long id);

    public Long countTravelStoriesByUserOwner(User userOwner);

    public List<TravelStory> findAllByUserOwnerIdAndTravelStoryStatus(long id, TravelStoryStatus status);
}
