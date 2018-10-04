package com.travelstory.dto;

import com.travelstory.entity.TravelStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelStoryStatistic extends JpaRepository<TravelStory, Long> {
    @Query(value = "SELECT COUNT(id) FROM travel_story", nativeQuery = true)
    Long countTravelStories();

    @Query(value = "SELECT count(id) from travel_story where MONTH (created_date) =:num", nativeQuery = true)
    Long countTravelStoriesByCreatedAtMonth(@Param("num") Integer numberOfMonth);

    @Query(value = "SELECT count(id) from travel_story where  travel_story_status = 'STATUS_ACTIVE'", nativeQuery = true)
    Long countActiveTravelStories();

    @Query(value = "SELECT count(id) from travel_story where  travel_story_status = 'STATUS_INACTIVE'", nativeQuery = true)
    Long countInactiveTravelStories();

}
