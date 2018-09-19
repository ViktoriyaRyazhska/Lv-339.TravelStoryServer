package com.travelstory.dao;

import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelStoryDAO extends JpaRepository<TravelStory, Long> {
    TravelStory findByHead(String head);

    TravelStory findByUserOwner(User userOwner);
}
