package com.travelstory.dao;

import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TravelStoryDAO extends JpaRepository<TravelStory, Long> {
    @Query("select t from TravelStory t where t.head = :head")
    TravelStory findByHead(@Param("head") String head);
    @Query("select t from TravelStory t where t.userOwner = :userOwner")
    TravelStory findByUserOwner(@Param("userOwner")User userOwner);
}
