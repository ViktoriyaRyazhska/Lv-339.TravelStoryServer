package com.travelstory.repositories;

import com.travelstory.entity.TravelStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelStoryRepository extends JpaRepository<TravelStory, Long> {
    TravelStory findByHead(String head);

    List<TravelStory> findByUserOwnerId(long id);
}
