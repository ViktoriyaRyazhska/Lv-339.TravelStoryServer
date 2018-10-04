package com.travelstory.repositories;

import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelStoryRepository extends JpaRepository<TravelStory, Long> {
    TravelStory findByHead(String head);

    TravelStory findByUserOwner(User userOwner);

    Long countTravelStoriesByUserOwner(User userOwner);
}
