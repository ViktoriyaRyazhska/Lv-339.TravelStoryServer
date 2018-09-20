package com.travelstory.dao;

import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TravelStoryDAO extends JpaRepository<TravelStory, Long> {
    TravelStory findByHead(@Param("head") String head);

    List<TravelStory> findByUserOwnerId(long id);
}
