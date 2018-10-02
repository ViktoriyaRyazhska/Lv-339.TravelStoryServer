package com.travelstory.dao;

import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.entity.TravelStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelStoryDAO extends JpaRepository<TravelStory, Long> {
    TravelStory findByHead(@Param("head") String head);

    List<TravelStory> findByUserOwnerId(long id);
}
